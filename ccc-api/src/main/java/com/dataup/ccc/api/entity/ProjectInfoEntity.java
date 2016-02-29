package com.dataup.ccc.api.entity;

/**
 * @Title: ProjectInfoEntity.java
 * @Package com.dataup.ccc.api.entity
 * @Description: 项目实体
 * @author xusheng.liu
 * @date 2015年9月21日 下午9:37:50
 * @version V1.0
 */
public class ProjectInfoEntity  implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5689296779003562006L;
	
	
	private String id;//ID
	private String projectType;//项目类型（0：简单；1：复杂）
	private String appName;//应用名称
	private String projectLeaderId;//项目负责人ID
	private String status;//项目状态
	private String createTime;//创建时间
	private String updateTime;//修改时间
	private String projectDesc;//项目描述
	private String customId;//客户ID
	private String customName;//客户名称
	private String projectName;//项目名称
	private String blackWhiteListType;//黑白名单类型
	
	private int pageNo;//当前页码
	private int  limit;//每页记录数
	
	
	//单一数据源的key
	private Integer dskey = 1;
	
	public ProjectInfoEntity() {
		super();
	}
	
	public ProjectInfoEntity(String id, String projectType, String appName,
			String projectLeaderId, String status, String createTime,
			String updateTime, String projectDesc, String customId,
			String customName, String projectName, String blackWhiteListType,
			int pageNo, int limit) {
		super();
		this.id = id;
		this.projectType = projectType;
		this.appName = appName;
		this.projectLeaderId = projectLeaderId;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.projectDesc = projectDesc;
		this.customId = customId;
		this.customName = customName;
		this.projectName = projectName;
		this.blackWhiteListType = blackWhiteListType;
		this.pageNo = pageNo;
		this.limit = limit;
	}

	public Integer getDskey() {
		return dskey;
	}

	public void setDskey(Integer dskey) {
		this.dskey = dskey;
	}

	public String getCustomId() {
		return customId;
	}

	public void setCustomId(String customId) {
		this.customId = customId;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getBlackWhiteListType() {
		return blackWhiteListType;
	}

	public void setBlackWhiteListType(String blackWhiteListType) {
		this.blackWhiteListType = blackWhiteListType;
	}

	public String getId() {
		return id;
	}

	public String getProjectType() {
		return projectType;
	}

	public String getAppName() {
		return appName;
	}

	public String getProjectLeaderId() {
		return projectLeaderId;
	}

	public String getStatus() {
		return status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public void setProjectLeaderId(String projectLeaderId) {
		this.projectLeaderId = projectLeaderId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	@Override
	public String toString() {
		return "ProjectInfoEntity [id=" + id + ", projectType=" + projectType
				+ ", appName=" + appName + ", projectLeaderId="
				+ projectLeaderId + ", status=" + status + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", projectDesc="
				+ projectDesc + ", customId=" + customId + ", customName="
				+ customName + ", projectName=" + projectName
				+ ", blackWhiteListType=" + blackWhiteListType + ", pageNo="
				+ pageNo + ", limit=" + limit + "]";
	}
}
