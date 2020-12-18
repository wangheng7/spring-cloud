package com.ultrapower.framework.configuration.service;

import com.ultrapower.framework.configuration.message.ConfigPropReq;
import com.ultrapower.framework.core.exception.ServiceException;

/**
* Created by LXS on 2018/11/22.
*/
public interface IConfigPropService {

    public void addProps(ConfigPropReq req) throws ServiceException;

    public void modifyProps(ConfigPropReq req) throws ServiceException;
}
