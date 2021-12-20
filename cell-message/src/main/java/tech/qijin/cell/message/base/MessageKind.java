package tech.qijin.cell.message.base;

import tech.qijin.util4j.lang.constant.EnumValue;

/**
 * 消息类型
 */
public enum MessageKind implements EnumValue<String> {
    SYSTEM("系统通知"){
        @Override
        public String title() {
            return "系统通知";
        }

        @Override
        public String icon() {
            return "https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTL7owiabvx35vA388oLicY16a78WuEkVIhXY3ibvYicR8jkEdr1a1ibUIzQotKToiadk9e0icmI86GMEqvfQ/132";
        }
    },
    ;


    MessageKind(String description) {
        this.description = description;
    }

    public abstract String title();
    public abstract String icon();

    private String description;

    @Override
    public String value() {
        return this.name();
    }

    @Override
    public String desc() {
        return this.description;
    }
}
