package com.dataup.ccc.web.base.security.entity;

import java.io.Serializable;

public class MapabcBossUser implements Serializable {

	private static final long serialVersionUID = -1359477686434905923L;
	private String id;
	private String name;
	private String pwd;
	private String truename;
	private String sex;
	private Integer age;
	private String mail;
	private Long birthday;
	private String telephone;
	private String mobile;
	private String entId;
	private String belongsales;
	private String belongboss;
	private String belongbossname;
	private String createUname;
	private Long createDate;
	private String roleId;
	private String department;
	private String duties;
	private Integer source;
	private String addr;
	private Integer type;
	private Long enable;
	private String emailsverify;
	private String ustatus;
	private Long updatedate;
	private String alipayaccount;
	private String parent;
	private Long agenttype;
	private String alipaytruename;
	private Long userlevel;
	private Long accounttype;
	private String propwd;
	private String pwd1;
	private String userpath;
	private String idnumber;
	private String idname;
	private String qq;
	private String taobaoId;
	private String taobaoaccount;
	private String verifystatus;
	private String alipayNo;

	private String roleCode;
	private String roleNames;
	private String ecode;
	private String ename;

	private String bsId;// 业务系统ID

	// 上海专用字段开始
	private String productCode;

	private String userId;

	private String orgid;

	// 上海专用字段结束

	// Constructors

	/** default constructor */
	public MapabcBossUser() {
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getTruename() {
		return this.truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Long getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Long birthday) {
		this.birthday = birthday;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	// 因为后台主键是number型，所以为空设置默认值
	public String getEntId() {
		if (this.entId == null || "".equals(this.entId))
			return "0";
		return String.valueOf(this.entId);
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	public String getBelongsales() {
		return this.belongsales;
	}

	public void setBelongsales(String belongsales) {
		this.belongsales = belongsales;
	}

	public String getBelongboss() {
		return this.belongboss;
	}

	public void setBelongboss(String belongboss) {
		this.belongboss = belongboss;
	}

	public String getBelongbossname() {
		return belongbossname;
	}

	public void setBelongbossname(String belongbossname) {
		this.belongbossname = belongbossname;
	}

	public String getCreateUname() {
		return this.createUname;
	}

	public void setCreateUname(String createUname) {
		this.createUname = createUname;
	}

	public Long getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDuties() {
		return this.duties;
	}

	public void setDuties(String duties) {
		this.duties = duties;
	}

	public Integer getSource() {
		return this.source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getEnable() {
		return this.enable;
	}

	public void setEnable(Long enable) {
		this.enable = enable;
	}

	public String getEmailsverify() {
		return this.emailsverify;
	}

	public void setEmailsverify(String emailsverify) {
		this.emailsverify = emailsverify;
	}

	public String getUstatus() {
		return this.ustatus;
	}

	public void setUstatus(String ustatus) {
		this.ustatus = ustatus;
	}

	public Long getUpdatedate() {
		return this.updatedate;
	}

	public void setUpdatedate(Long updatedate) {
		this.updatedate = updatedate;
	}

	public String getAlipayaccount() {
		return this.alipayaccount;
	}

	public void setAlipayaccount(String alipayaccount) {
		this.alipayaccount = alipayaccount;
	}

	public String getParent() {
		return this.parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public Long getAgenttype() {
		return this.agenttype;
	}

	public void setAgenttype(Long agenttype) {
		this.agenttype = agenttype;
	}

	public String getAlipaytruename() {
		return this.alipaytruename;
	}

	public void setAlipaytruename(String alipaytruename) {
		this.alipaytruename = alipaytruename;
	}

	public Long getUserlevel() {
		return this.userlevel;
	}

	public void setUserlevel(Long userlevel) {
		this.userlevel = userlevel;
	}

	public Long getAccounttype() {
		return this.accounttype;
	}

	public void setAccounttype(Long accounttype) {
		this.accounttype = accounttype;
	}

	public String getPropwd() {
		return this.propwd;
	}

	public void setPropwd(String propwd) {
		this.propwd = propwd;
	}

	public String getPwd1() {
		return this.pwd1;
	}

	public void setPwd1(String pwd1) {
		this.pwd1 = pwd1;
	}

	public String getUserpath() {
		return this.userpath;
	}

	public void setUserpath(String userpath) {
		this.userpath = userpath;
	}

	public String getIdnumber() {
		return this.idnumber;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	public String getIdname() {
		return this.idname;
	}

	public void setIdname(String idname) {
		this.idname = idname;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getTaobaoId() {
		return this.taobaoId;
	}

	public void setTaobaoId(String taobaoId) {
		this.taobaoId = taobaoId;
	}

	public String getTaobaoaccount() {
		return this.taobaoaccount;
	}

	public void setTaobaoaccount(String taobaoaccount) {
		this.taobaoaccount = taobaoaccount;
	}

	public String getVerifystatus() {
		return this.verifystatus;
	}

	public void setVerifystatus(String verifystatus) {
		this.verifystatus = verifystatus;
	}

	public String getAlipayNo() {
		return this.alipayNo;
	}

	public void setAlipayNo(String alipayNo) {
		this.alipayNo = alipayNo;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getEcode() {
		return ecode;
	}

	public void setEcode(String ecode) {
		this.ecode = ecode;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public String getBsId() {
		return bsId;
	}

	public void setBsId(String bsId) {
		this.bsId = bsId;
	}

}