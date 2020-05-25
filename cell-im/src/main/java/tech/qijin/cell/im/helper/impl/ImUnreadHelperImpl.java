package tech.qijin.cell.im.helper.impl;

import org.springframework.stereotype.Service;
import tech.qijin.cell.im.helper.ImUnreadHelper;

@Service
public class ImUnreadHelperImpl implements ImUnreadHelper {
    @Override
    public int incrUnread(long uid, long peerUid, int count) {
        return 0;
    }
}
