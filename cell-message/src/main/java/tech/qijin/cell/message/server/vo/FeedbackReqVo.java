package tech.qijin.cell.message.server.vo;

import lombok.Data;

import java.util.List;

@Data
public class FeedbackReqVo {
    private String text;
    private List<String> images;
}
