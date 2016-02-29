package com.dataup.ccc.core.dao;

import org.springframework.stereotype.Repository;

import autonavi.online.framework.sharding.dao.constant.ReservedWord;
import autonavi.online.framework.sharding.entry.aspect.annotation.Author;
import autonavi.online.framework.sharding.entry.aspect.annotation.Insert;
import autonavi.online.framework.sharding.entry.aspect.annotation.Select;
import autonavi.online.framework.sharding.entry.aspect.annotation.Shard;
import autonavi.online.framework.sharding.entry.aspect.annotation.SingleDataSource;
import autonavi.online.framework.sharding.entry.aspect.annotation.SqlParameter;
import autonavi.online.framework.sharding.entry.aspect.annotation.Update;
import autonavi.online.framework.sharding.entry.entity.CollectionType;

import com.dataup.ccc.api.entity.Enterprise;
/**
 * @Title: EnterpriseDao.java
 * @Package com.dataup.ccc.core.dao
 * @Description: 企业
 * @author xusheng.liu
 * @date 2015年9月18日 下午5:28:04
 * @version V1.0
 */
@Repository
public class EnterpriseDao {
	
	@Author("xusheng.liu")
	@SingleDataSource(keyName = "dsKey")
	@Select(collectionType = CollectionType.bean, resultType = Enterprise.class)
	public Object queryEnterpriseByUserId(
			@SqlParameter("dsKey") Integer dsKey,
			@SqlParameter("enterprise") Enterprise enterprise) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select id,code,name,shortname,area,industry,belongsales,telephone,addr,postcode,url,"
						+ "mail,fax,auth,source,old_eid as oldEid,create_uname as createUname,create_date as createDate,"
						+ "comment,type,agenttype,belongagent,agentrank,agentflag,update_date as updateDate,mobile,userId ");
		sqlBuffer.append(" from ccc_custom_enterprise where userId=#{enterprise.userId}");
		return sqlBuffer.toString();
	}

	@Author("xusheng.liu")
	@Shard(indexName = "cc_custom_id_index", indexColumn = "name")
	@Insert
	public Object saveEnterprise(
			@SqlParameter("name")String name, 
			@SqlParameter("enterprise")Enterprise enterprise) {
			return "insert into	ccc_custom_enterprise"
					+ "(id,code,name,shortname,area,industry,belongsales,telephone,addr,"
					+ "postcode,url,mail,fax,auth,source,old_eid,create_uname,create_date,"
					+ "comment,type,agenttype,belongagent,update_date,userId,mobile) "
					+ "values"
					+ " (#{"
					+ ReservedWord.snowflake
					+ "},#{enterprise.code},#{enterprise.name},#{enterprise.shortname},#{enterprise.area},#{enterprise.industry},"
					+ "#{enterprise.belongsales},#{enterprise.telephone},#{enterprise.addr},#{enterprise.postcode},#{enterprise.url},#{enterprise.mail},"
					+ "#{enterprise.fax},#{enterprise.auth},#{enterprise.source},#{enterprise.oldEid},#{enterprise.createUname},unix_timestamp(SYSDATE())*1000,"
					+ "#{enterprise.comment},#{enterprise.type},#{enterprise.agenttype},#{enterprise.belongagent},#{enterprise.updateDate},#{enterprise.userId},#{enterprise.mobile})";
	}

	@Author("xusheng.liu")
	@Shard(indexName = "cc_custom_id_index", indexColumn = "name")
	@Update
	public Object updateEnterprise(
		@SqlParameter("name")String name, 
		@SqlParameter("enterprise")Enterprise enterprise) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("update ccc_custom_enterprise set update_date=unix_timestamp(sysdate())*1000");
		if (enterprise.getName() != null)
			sqlBuffer.append(",name=#{enterprise.name}");
		if (enterprise.getShortname() != null)
			sqlBuffer.append(",shortname=#{enterprise.shortname}");
		if (enterprise.getBelongsales() != null)
			sqlBuffer.append(",belongsales=#{enterprise.belongsales}");
		if (enterprise.getAddr() != null)
			sqlBuffer.append(",addr=#{enterprise.addr}");
		if (enterprise.getPostcode() != null)
			sqlBuffer.append(",postcode=#{enterprise.postcode}");
		if (enterprise.getTelephone() != null)
			sqlBuffer.append(",telephone=#{enterprise.telephone}");
		if (enterprise.getArea() != null)
			sqlBuffer.append(",area=#{enterprise.area}");
		if (enterprise.getIndustry() != null)
			sqlBuffer.append(",industry=#{enterprise.industry}");
		if (enterprise.getFax() != null)
			sqlBuffer.append(",fax=#{enterprise.fax}");
		if (enterprise.getComment() != null)
			sqlBuffer.append(",comment=#{enterprise.comment}");
		if (enterprise.getMail() != null)
			sqlBuffer.append(",mail=#{enterprise.mail}");
		if (enterprise.getAuth() != null)
			sqlBuffer.append(",auth=#{enterprise.auth}");
		if (enterprise.getMobile() != null)
			sqlBuffer.append(",mobile=#{enterprise.mobile}");
		if (enterprise.getUserId() != null)
			sqlBuffer.append(",userId=#{enterprise.userId}");
		if (enterprise.getUrl() != null)
			sqlBuffer.append(",url=#{enterprise.url}");
		sqlBuffer.append(" where id=#{enterprise.id}");
		return sqlBuffer.toString();
	}
}