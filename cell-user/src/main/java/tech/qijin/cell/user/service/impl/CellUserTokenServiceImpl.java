package tech.qijin.cell.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.user.base.UserToken;
import tech.qijin.cell.user.service.CellUserTokenService;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.redis.RedisUtil;
import tech.qijin.util4j.utils.MAssert;
import tech.qijin.util4j.utils.NumberUtil;

import java.util.UUID;

@Service
public class CellUserTokenServiceImpl implements CellUserTokenService {
    private static final String CELL_TOKEN_KEY = "cell:user:token";
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public UserToken genUserToken(Long userId, int expire) {
        String token = UUID.randomUUID().toString();
        if (expire == 0) {
            redisUtil.setLong(getTokenKey(token), userId);
        } else {
            redisUtil.setLong(getTokenKey(token), userId, expire);
        }
        return UserToken.builder().token(token).build();
    }

    @Override
    public Long auth(String token) {
        Long userId = redisUtil.getLong(getTokenKey(token));
        MAssert.isTrue(NumberUtil.gtZero(userId), ResEnum.UNAUTHORIZED);
        return userId;
    }

    private String getTokenKey(String token) {
        return String.format("%s:%s", CELL_TOKEN_KEY, token);
    }
}
