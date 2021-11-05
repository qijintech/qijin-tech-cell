package tech.qijin.cell.feed.helper;

public class CommonHelper {
    protected String orderBy(String column, String direction, Integer pageNo, Integer pageSize) {
        return String.format("%s %s limit %d, %d", column, direction, (pageNo - 1) * pageSize, pageSize);
    }
}
