package com.dataup.ccc.core.dao;

import org.springframework.stereotype.Repository;

import autonavi.online.framework.sharding.entry.aspect.annotation.Author;
import autonavi.online.framework.sharding.entry.aspect.annotation.Select;
import autonavi.online.framework.sharding.entry.aspect.annotation.Shard;
import autonavi.online.framework.sharding.entry.aspect.annotation.SqlParameter;
import autonavi.online.framework.sharding.entry.entity.CollectionType;

import com.dataup.ccc.api.entity.ProjectInfoEntity;

/**
 * @Title: DwDataDao.java
 * @Package com.dataup.ccc.core.dao
 * @Description: 数据仓库数据
 * @author xusheng.liu
 * @date 2015年9月22日 下午2:15:45
 * @version V1.0
 */
@Repository
public class DwDataDao {

	@Author("xusheng.liu")
	@Shard(indexName = "cc_custom_id_index", indexColumn = "projectInfoEntity.customId")
	@Select(collectionType = CollectionType.beanList, resultType = ProjectInfoEntity.class)
	public Object queryProjectByCustomId(@SqlParameter("projectInfoEntity") ProjectInfoEntity projectInfoEntity) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID,project_name AS projectName,project_type AS projectType,"
				+ "app_name AS appName, custom_id AS customId, project_leader_id AS projectLeaderId,"
				+ " STATUS, create_time AS createTime, update_time AS updateTime,"
				+ " project_desc AS projectDesc FROM `cc_project_info` cpi "
				+ "WHERE cpi.`CUSTOM_ID` = #{projectInfoEntity.customId} ");
		return sql.toString();
	}

}
