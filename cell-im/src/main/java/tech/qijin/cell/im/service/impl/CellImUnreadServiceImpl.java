package tech.qijin.cell.im.service.impl;

import org.springframework.stereotype.Service;
import tech.qijin.cell.im.service.CellImUnreadService;

@Service
public class CellImUnreadServiceImpl implements CellImUnreadService {
    @Override
    public int incrUnread(long uid, long peerUid, int count) {
        return 0;
    }

    @Override
    public boolean clearUnread(long uid, long peerUid, Long lastMsgId) {
        return false;
    }
}
