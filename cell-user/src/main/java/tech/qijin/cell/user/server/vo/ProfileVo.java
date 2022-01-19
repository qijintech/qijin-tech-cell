package tech.qijin.cell.user.server.vo;

import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import tech.qijin.cell.user.base.Gender;
import tech.qijin.cell.user.db.model.UserProfile;
import tech.qijin.util4j.utils.ConvertUtil;
import tech.qijin.util4j.utils.DateUtil;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProfileVo {
    private Long userId;
    // 姓名
    private String name;
    // 身高
    private String height;
    // 性别
    private Gender gender;
    // 头像
    private String avatar;
    // 年龄
    private Integer age;
    // 生日
    private String birthday;
    private String birthdayYear;
    private String birthdayMonth;
    // 籍贯
    private String bornCity;
    // 居住城市
    private String liveCity;
    // 学校
    private String edu;
    // 学历
    private String eduDegree;
    // 工作
    private String job;
    // 星座
    private String constellation;
    // 手机号
    private String mobile;

    public static ProfileVo from(UserProfile profile) {
        ProfileVo profileVo = ConvertUtil.convert(profile, ProfileVo.class);
        if (profileVo == null) return null;
        if (profile.getBirthday() != null) {
            profileVo.setBirthday(DateUtil.formatStr(profile.getBirthday(), DateUtil.DATE_FORMAT));
            profileVo.setBirthdayYear(DateUtil.formatStr(profile.getBirthday(), "yyyy"));
            profileVo.setBirthdayMonth(DateUtil.formatStr(profile.getBirthday(), "yyyy-MM"));
        }
        profileVo.setAge(DateUtil.getAgeByBirth(profile.getBirthday()));
        profileVo.setConstellation(DateUtil.getConstellation(profile.getBirthday()));
        return profileVo;
    }

    public static List<ProfileVo> from(List<UserProfile> profiles) {
        if (CollectionUtils.isEmpty(profiles)) return Collections.emptyList();
        return profiles.stream().map(ProfileVo::from).collect(Collectors.toList());
    }
}
