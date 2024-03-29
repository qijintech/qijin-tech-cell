package tech.qijin.cell.user.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Service;
import tech.qijin.cell.user.base.UserToken;
import tech.qijin.cell.user.service.CellUserTokenService;
import tech.qijin.util4j.kms.KmsBean;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.redis.RedisUtil;
import tech.qijin.util4j.utils.DateUtil;
import tech.qijin.util4j.utils.LogFormat;
import tech.qijin.util4j.utils.MAssert;
import tech.qijin.util4j.utils.NumberUtil;

import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class CellUserTokenServiceImpl implements CellUserTokenService {
    private static final String CELL_TOKEN_KEY = "cell:user:token";
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private KmsBean kmsBean;

    @Override
    public UserToken genUserToken(Long userId, int expire) {
        Optional<String> jwtSecretOpt = kmsBean.getRaw("jwt", "secret");
        MAssert.isTrue(jwtSecretOpt.isPresent(), ResEnum.BAD_GATEWAY);
        Date expireAt = null;
        // 过期时间是10天
        expire = 10 * DateUtil.SECONDS_PER_DAY;
        if (expire > 0) {
            expireAt = new Date(DateUtil.now().getTime() + expire * DateUtil.MILLI_PER_SECOND);
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecretOpt.get());
            String token = JWT.create()
                    .withClaim("userId", userId)
                    .withExpiresAt(expireAt)
                    .sign(algorithm);
            redisUtil.setString(userTokenKey(userId), token);
            return UserToken.builder()
                    .token(token)
                    .expire((long) expire)
                    .build();
        } catch (JWTCreationException exception) {
            log.error(LogFormat.builder().message("jwt error").build());
            MAssert.isTrue(false, ResEnum.BAD_GATEWAY);
        }
        return null;
    }

    @Override
    public Long auth(String token) {
        if (StringUtils.isBlank(token)) return 0L;
        Optional<String> jwtSecretOpt = kmsBean.getRaw("jwt", "secret");
        MAssert.isTrue(jwtSecretOpt.isPresent(), ResEnum.BAD_GATEWAY);

        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecretOpt.get());
            JWTVerifier verifier = JWT.require(algorithm)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claimMap = jwt.getClaims();
            Claim expClaim = claimMap.get("exp");
            Long expireAt = expClaim.asLong();
            if (NumberUtil.gtZero(expireAt) && DateUtil.now().getTime() / 1000 > expireAt) {
                log.warn(LogFormat.builder()
                        .message("JWT expired")
                        .put("token", token)
                        .put("exp", expireAt)
                        .build());
                return 0L;
            }
            Claim userIdClaim = claimMap.get("userId");
            if (userIdClaim == null) {
                log.error(LogFormat.builder()
                        .message("JWT error, claim is null")
                        .put("token", token)
                        .build());
                return 0L;
            }
            return checkToken(userIdClaim.asLong(), token);
        } catch (JWTVerificationException exception) {
            log.error(LogFormat.builder()
                    .message("JWT auth failed")
                    .put("token", token)
                    .put("ex", exception.getMessage())
                    .build());
            MAssert.isTrue(false, ResEnum.UNAUTHORIZED);
        }
        return null;
    }

    private Long checkToken(Long userId, String token) {
        String cachedToken = redisUtil.getString(userTokenKey(userId));
        if (!token.equals(cachedToken)) {
            log.warn(LogFormat.builder()
                    .message("JWT token overwrite")
                    .put("old token", cachedToken)
                    .put("new token", token)
                    .build());
            return 0L;
        }
        return userId;
    }

    private String userTokenKey(Long userId) {
        return String.format("user:token:%d", userId);
    }

    private String getTokenKey(String token) {
        return String.format("%s:%s", CELL_TOKEN_KEY, token);
    }
}
