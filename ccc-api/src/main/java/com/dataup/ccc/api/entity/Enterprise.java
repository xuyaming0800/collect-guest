package com.dataup.ccc.api.entity;


// default package


/**
 * MapabcBossEnterprise entity. @author MyEclipse Persistence Tools
 */

public class Enterprise implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = -4437521047978998909L;
	private String id;
	private String name;
	private String shortname;
	private String area;
	private String addr;
	private String fax;
	private String userId;
	private String mobile;
	private String telephone;
	private String url;
	private String postcode;
	private Long createDate;
	private Long updateDate;
	private String code;
	private String industry;
	private Long belongsales;
	private String mail;
	private Integer auth;
	private Integer source;
	private String oldEid;
	private String createUname;
	private String comment;
	private Integer type;
	private String agenttype;
	private String belongagent;
	private Integer agentrank;
	private Integer agentflag;

	// Constructors

	/** default constructor */
	public Enterprise() {
	}

	public Enterprise(String code, String name, String shortname, String area,
			String industry, Long belongsales, String telephone, String addr,
			String postcode, String url, String mail, String fax, Integer auth,
			Integer source, String oldEid, String createUname, Long createDate,
			String comment, Integer type, String agenttype, String belongagent,
			Integer agentrank, Integer agentflag, Long updateDate,
			String mobile, String userId) {
		super();
		this.code = code;
		this.name = name;
		this.shortname = shortname;
		this.area = area;
		this.industry = industry;
		this.belongsales = belongsales;
		this.telephone = telephone;
		this.addr = addr;
		this.postcode = postcode;
		this.url = url;
		this.mail = mail;
		this.fax = fax;
		this.auth = auth;
		this.source = source;
		this.oldEid = oldEid;
		this.createUname = createUname;
		this.createDate = createDate;
		this.comment = comment;
		this.type = type;
		this.agenttype = agenttype;
		this.belongagent = belongagent;
		this.agentrank = agentrank;
		this.agentflag = agentflag;
		this.updateDate = updateDate;
		this.mobile = mobile;
		this.userId = userId;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public String getSid() {
		return String.valueOf(this.id);
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPra_id(String id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setPra_code(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPra_name(String name) {
		this.name = name;
	}

	public String getShortname() {
		return this.shortname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public void setPra_shortname(String shortname) {
		this.shortname = shortname;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setPra_area(String area) {
		this.area = area;
	}

	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public void setPra_industry(String industry) {
		this.industry = industry;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getBelongsales() {
		return this.belongsales;
	}

	public void setBelongsales(Long belongsales) {
		this.belongsales = belongsales;
	}

	public void setPra_belongsales(Long belongsales) {
		this.belongsales = belongsales;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setPra_telephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public void setPra_addr(String addr) {
		this.addr = addr;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPra_url(String url) {
		this.url = url;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setPra_mail(String mail) {
		this.mail = mail;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setPra_fax(String fax) {
		this.fax = fax;
	}

	public Integer getAuth() {
		return this.auth;
	}

	public void setAuth(Integer auth) {
		this.auth = auth;
	}

	public void setPra_auth(Integer auth) {
		this.auth = auth;
	}

	public Integer getSource() {
		return this.source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public void setPra_source(Integer source) {
		this.source = source;
	}

	public String getOldEid() {
		return this.oldEid;
	}

	public void setOldEid(String oldEid) {
		this.oldEid = oldEid;
	}

	public void setPra_oldEid(String oldEid) {
		this.oldEid = oldEid;
	}

	public String getCreateUname() {
		return this.createUname;
	}

	public void setCreateUname(String createUname) {
		this.createUname = createUname;
	}

	public void setPra_createUname(String createUname) {
		this.createUname = createUname;
	}

	public Long getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public void setPra_createDate(Long createDate) {
		this.createDate = createDate;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setPra_comment(String comment) {
		this.comment = comment;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setPra_type(Integer type) {
		this.type = type;
	}

	public String getAgenttype() {
		return this.agenttype;
	}

	public void setAgenttype(String agenttype) {
		this.agenttype = agenttype;
	}

	public void setPra_agenttype(String agenttype) {
		this.agenttype = agenttype;
	}

	public String getBelongagent() {
		return this.belongagent;
	}

	public void setBelongagent(String belongagent) {
		this.belongagent = belongagent;
	}

	public void setPra_belongagent(String belongagent) {
		this.belongagent = belongagent;
	}

	public Integer getAgentrank() {
		return this.agentrank;
	}

	public void setAgentrank(Integer agentrank) {
		this.agentrank = agentrank;
	}

	public void setPra_agentrank(Integer agentrank) {
		this.agentrank = agentrank;
	}

	public Integer getAgentflag() {
		return this.agentflag;
	}

	public void setAgentflag(Integer agentflag) {
		this.agentflag = agentflag;
	}

	public void setPra_agentflag(Integer agentflag) {
		this.agentflag = agentflag;
	}

	public Long getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Long updateDate) {
		this.updateDate = updateDate;
	}

	public void setPra_updateDate(Long updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "Enterprise [id=" + id + ", name=" + name + ", shortname="
				+ shortname + ", area=" + area + ", addr=" + addr + ", fax="
				+ fax + ", userId=" + userId + ", mobile=" + mobile
				+ ", telephone=" + telephone + ", url=" + url + ", postcode="
				+ postcode + ", createDate=" + createDate + ", updateDate="
				+ updateDate + ", code=" + code + ", industry=" + industry
				+ ", belongsales=" + belongsales + ", mail=" + mail + ", auth="
				+ auth + ", source=" + source + ", oldEid=" + oldEid
				+ ", createUname=" + createUname + ", comment=" + comment
				+ ", type=" + type + ", agenttype=" + agenttype
				+ ", belongagent=" + belongagent + ", agentrank=" + agentrank
				+ ", agentflag=" + agentflag + "]";
	}

}