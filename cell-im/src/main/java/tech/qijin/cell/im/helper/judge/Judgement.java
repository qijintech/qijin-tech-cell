package tech.qijin.cell.im.helper.judge;

import lombok.Data;

@Data
public class Judgement {
    public Judgement(JudgementType type) {
        this.type = type;
    }

    public Judgement(int buzCode, String message, JudgementType type) {
        this.buzCode = buzCode;
        this.message = message;
        this.type = type;
    }

    private int buzCode;
    private String message;
    private JudgementType type;

    public static enum JudgementType {
        // 通过
        PASS,
        // 禁止
        FORBIDDEN,
        // 悄悄发送，但是接收方收不到
        SILENT,
    }

    public static Judgement defaultJudgement() {
        return new Judgement(0, "正常", JudgementType.PASS);
    }
}
