package tech.qijin.cell.feed.server.vo;

import lombok.Data;
import tech.qijin.util4j.lang.vo.PageVo;

import javax.validation.constraints.NotNull;

@Data
public class CommentReqVo extends PageVo {
    @NotNull
    private Long feedId;
    private Long commentId;
    private Long subCommentId;
    private String text;
    private String image;
}
