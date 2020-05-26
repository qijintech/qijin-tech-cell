package tech.qijin.cell.im.helper.impl;

import org.springframework.stereotype.Service;
import tech.qijin.cell.im.helper.CellImUnreadHelper;

@Service
public class CellImUnreadHelperImpl implements CellImUnreadHelper {
    @Override
    public int incrUnread(long uid, long peerUid, int count) {
        return 0;
    }
}
