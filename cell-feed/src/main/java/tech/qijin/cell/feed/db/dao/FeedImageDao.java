package tech.qijin.cell.feed.db.dao;

import tech.qijin.cell.feed.db.mapper.FeedImageMapper;
import tech.qijin.cell.feed.db.mapper.FeedImageSqlProvider;
import tech.qijin.cell.feed.db.model.FeedImage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.InsertProvider;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: SYSTEM
 **/

public interface FeedImageDao extends FeedImageMapper {

	@InsertProvider(type = SqlProvider.class, method = "batchInsert")
	int batchInsert(@Param("records") List<FeedImage> records);

	class SqlProvider {
		private static final String VALUES = "VALUES";
		FeedImageSqlProvider provider = new FeedImageSqlProvider();

		public String batchInsert(Map<String, Object> param) {
			List<FeedImage> records = (List<FeedImage>) param.get("records");
			return genSql(records);
		}

		private String genSql(List<FeedImage> records) {
			List<String> sqls = records.stream()
					.map(record -> provider.insertSelective(record))
					.collect(Collectors.toList());
			String[] arr = sqls.get(0).split(VALUES);
			String head = arr[0];
			String value = arr[1];
			List<String> values = Lists.newArrayList();
			for (int i = 0; i <= sqls.size() - 1; i++) {
				StringBuilder sb = new StringBuilder().append("#{records[").append(i).append("].");
				values.add(value.replace("#{", sb.toString()));
			}
			return new StringBuilder().append(head).append(" ").append(VALUES).append(" ")
					.append(StringUtils.join(values, ",")).toString();
		}
	}
}
