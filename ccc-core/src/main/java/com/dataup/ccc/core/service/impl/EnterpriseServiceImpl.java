package com.dataup.ccc.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataup.ccc.api.entity.Enterprise;
import com.dataup.ccc.api.service.EnterpriseService;
import com.dataup.ccc.core.dao.EnterpriseDao;

@Service("enterpriseService")
public class EnterpriseServiceImpl implements EnterpriseService {
	
	@Autowired
	private EnterpriseDao enterpriseDao;

	@Override
	public Enterprise queryEnterpriseByCondition(Integer username,Enterprise enterprise)
			throws Exception {
		Object enter = enterpriseDao.queryEnterpriseByUserId(username,enterprise);
		if(enter!=null)
			return (Enterprise) enter;
		return null;
	}

	@Override
	public boolean saveEnterpriseByCondition(String name,
			Enterprise enterprise) throws Exception {
		boolean resu = false;
		int count = 0;
		if(enterprise.getId()!=null&&!"".equals(enterprise.getId())){//修改
			count = (Integer)enterpriseDao.updateEnterprise(name,enterprise);
		}else{
			count = (Integer)enterpriseDao.saveEnterprise(name,enterprise);
		}
		if(count>0)
			resu = true;
		return resu;
	}
	
}
