package tech.qijin.cell.im.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "发送消息响应实体", description = "发送消息成功后的返回")
public class MessageSendDTO {
    @ApiModelProperty(value = "message ID", example = "", required = true)
    private Long msgID;
}
