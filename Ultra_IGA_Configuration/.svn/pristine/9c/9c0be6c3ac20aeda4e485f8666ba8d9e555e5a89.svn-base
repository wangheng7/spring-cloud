package com.ultrapower.framework.configuration.service.impl;

import com.ultrapower.framework.configuration.message.ConfigPropReq;
import com.ultrapower.framework.configuration.service.IConfigPropService;
import com.ultrapower.framework.core.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "native")
public class NativeConfigPropServiceImpl implements IConfigPropService {

    private static final Logger logger = LoggerFactory.getLogger(NativeConfigPropServiceImpl.class);

    @Override
    public void addProps(ConfigPropReq req) throws ServiceException {
        logger.info("NativeConfigPropServiceImpl  addProps started");
        logger.info("NativeConfigPropServiceImpl  addProps started");
    }

    @Override
    public void modifyProps(ConfigPropReq req) throws ServiceException {
        logger.info("NativeConfigPropServiceImpl  modifyProps started");
        logger.info("NativeConfigPropServiceImpl  modifyProps started");
    }
}
