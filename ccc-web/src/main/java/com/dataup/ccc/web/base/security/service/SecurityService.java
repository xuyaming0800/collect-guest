package com.dataup.ccc.web.base.security.service;

import com.dataup.ccc.web.base.security.entity.LoginDTO;
import com.dataup.ccc.web.base.security.entity.MapabcBossUser;


public interface SecurityService {

	public MapabcBossUser login(LoginDTO loginDTO) throws Exception;

	public void logout() throws Exception;

}
