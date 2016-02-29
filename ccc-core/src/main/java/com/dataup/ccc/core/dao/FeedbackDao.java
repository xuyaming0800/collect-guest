package com.dataup.ccc.core.dao;


import org.springframework.stereotype.Repository;

import autonavi.online.framework.sharding.dao.constant.ReservedWord;
import autonavi.online.framework.sharding.entry.aspect.annotation.Author;
import autonavi.online.framework.sharding.entry.aspect.annotation.Insert;
import autonavi.online.framework.sharding.entry.aspect.annotation.Shard;
import autonavi.online.framework.sharding.entry.aspect.annotation.SqlParameter;

import com.dataup.ccc.api.bean.FeedbackInfo;

/**
 * 增加，删除，查询和修改组件信息
 * 
 * @author wenpeng.jin
 *
 */
@Repository
public class FeedbackDao {
	
	 
	@Author("zhanqiao.huang")
	@Shard(indexName = "cc_custom_id_index", indexColumn = "feedbackInfo.customId")
	@Insert
	public Object saveFeedbackInfo(@SqlParameter("feedbackInfo") FeedbackInfo feedbackInfo) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ccc_feed_back (ID,context,create_time,create_user) VALUES (");
		sql.append("#{"+ ReservedWord.snowflake + "}");
		sql.append(" ,#{feedbackInfo.context}");
		sql.append(" ,UNIX_TIMESTAMP(NOW())");
		sql.append(" ,#{feedbackInfo.customId} )");
		System.out.println(sql.toString());
		return sql.toString();
	}
	 
	
}
