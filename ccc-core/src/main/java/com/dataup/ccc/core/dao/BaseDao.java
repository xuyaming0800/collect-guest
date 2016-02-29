package com.dataup.ccc.core.dao;

import org.springframework.stereotype.Repository;

import autonavi.online.framework.sharding.entry.aspect.annotation.Author;
import autonavi.online.framework.sharding.entry.aspect.annotation.Select;
import autonavi.online.framework.sharding.entry.aspect.annotation.Shard;
import autonavi.online.framework.sharding.entry.aspect.annotation.SqlParameter;
import autonavi.online.framework.sharding.entry.entity.CollectionType;

import com.dataup.ccc.api.entity.ProjectInfoEntity;

/**
 * 增加，删除，查询和修改组件信息
 * 
 * @author wenpeng.jin
 *
 */
@Repository
public class BaseDao {

	@Author("xusheng.liu")
	@Shard(indexName = "cc_custom_id_index", indexColumn = "projectInfoEntity.customId")
	@Select(collectionType = CollectionType.beanList, resultType = ProjectInfoEntity.class)
	public Object queryProjectByCustomId(@SqlParameter("projectInfoEntity") ProjectInfoEntity projectInfoEntity) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID,project_name AS projectName,project_type AS projectType,"
				+ "app_name AS appName, custom_id AS customId, project_leader_id AS projectLeaderId,"
				+ " STATUS, create_time AS createTime, update_time AS updateTime,"
				+ " project_desc AS projectDesc FROM `cc_project_info` cpi "
				+ "WHERE cpi.`CUSTOM_ID` = #{projectInfoEntity.customId} and cpi.STATUS=1");
		return sql.toString();
	}

}
