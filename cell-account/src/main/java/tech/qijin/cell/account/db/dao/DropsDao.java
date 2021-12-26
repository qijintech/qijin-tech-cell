package tech.qijin.cell.account.db.dao;

import org.apache.ibatis.annotations.Select;
import tech.qijin.cell.account.db.mapper.DropsMapper;
import tech.qijin.cell.account.db.mapper.DropsSqlProvider;
import tech.qijin.cell.account.db.model.Drops;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.InsertProvider;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: SYSTEM
 **/

public interface DropsDao extends DropsMapper {
	@Select({
			"select max(update_time) from drops"
	})
	Date getLastUpdatedAt();

	@InsertProvider(type = SqlProvider.class, method = "batchInsert")
	int batchInsert(@Param("records") List<Drops> records);

	class SqlProvider {
		private static final String VALUES = "VALUES";
		DropsSqlProvider provider = new DropsSqlProvider();

		public String batchInsert(Map<String, Object> param) {
			List<Drops> records = (List<Drops>) param.get("records");
			return genSql(records);
		}

		private String genSql(List<Drops> records) {
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
