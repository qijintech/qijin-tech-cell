package tech.qijin.cell.im.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author michealyang
 * @date 2019-11-08
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Data
@Builder
@ApiModel(value = "发送消息请求实体", description = "发送消息需要传的参数")
public class MessageSendVO {

    @ApiModelProperty(value = "发送者的UID", example = "233333", required = true)
    @NotNull(message = "发送者uid不能为空")
    private Long uid;

    @NotNull(message = "接收者uid不能为空")
    @ApiModelProperty(value = "发送者的UID", example = "666666", required = true)
    private Long toUid;

    @NotNull(message = "消息类型不能为空")
    @ApiModelProperty(value = "消息类型", example = "TEXT", required = true)
    private MsgType msgType;

    @NotNull(message = "消息内容不能为空")
    @ApiModelProperty(value = "消息内容", example = "...", required = true)
    private AbstractContent content;

    // 扩展字段
    @ApiModelProperty(value = "扩展字段", example = "JSON字符串", required = true)
    private String ext;
}
