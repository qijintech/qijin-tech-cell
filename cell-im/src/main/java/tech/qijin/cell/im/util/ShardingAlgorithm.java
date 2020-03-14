package tech.qijin.cell.im.util;

/**
 * @author michealyang
 * @date 2019-12-16
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public enum ShardingAlgorithm {
    DATABASE {
        @Override
        int doSharding(long source, int dbCount, int tableCount) {
            return (int) (source % dbCount);
        }
    },
    TABLE {
        @Override
        int doSharding(long source, int dbCount, int tableCount) {
            return (int) (source / dbCount % tableCount);
        }
    };

    /**
     * @param source     数据源，如uid
     * @param dbCount    分库数量
     * @param tableCount 分表数量
     * @return
     */
    abstract int doSharding(long source, int dbCount, int tableCount);
}
