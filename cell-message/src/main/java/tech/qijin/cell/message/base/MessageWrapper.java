package tech.qijin.cell.message.base;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MessageWrapper {
    private MessageKind kind;
    private String content;
    private String brief;
    // 当站内信中有奖励时，drops才会有值
    private List<DropsWrapper> drops;
}
