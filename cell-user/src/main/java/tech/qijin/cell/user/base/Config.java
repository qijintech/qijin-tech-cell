package tech.qijin.cell.user.base;

import com.google.common.collect.Lists;

import java.util.List;

public interface Config {
    List<Long> whitelist = Lists.newArrayList(10000L);
}
