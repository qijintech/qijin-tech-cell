package tech.qijin.cell.iam.base;

import tech.qijin.util4j.lang.constant.EnumValue;

/**
 * @author michealyang
 * @date 2020-01-05
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public enum IamAuth implements EnumValue<String> {

    // SOCIAL
    GROUP_CREATE("创建群组权限"),
    GROUP_MANAGE("管理群组权限"),
    GROUP_AUDIT("审核群组权限"),
    ORGANIZER_MANAGE("活动管理员管理权限"),
    ACTIVITY_MANAGE("创建/编辑活动权限"),
    ACTIVITY_INFO("读取活动权限"),
    ;


    IamAuth(String description) {
        this.description = description;
    }

    private String description;

    @Override
    public String value() {
        return this.name();
    }

    @Override
    public String desc() {
        return description;
    }
}
