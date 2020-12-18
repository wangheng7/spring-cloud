package com.ultrapower.framework.configuration.service.impl;

import com.ultrapower.framework.configuration.config.InitDataSourceConfiguration;
import com.ultrapower.framework.configuration.message.ConfigPropReq;
import com.ultrapower.framework.configuration.service.IConfigPropService;
import com.ultrapower.framework.core.constants.CommonErrorCode;
import com.ultrapower.framework.core.exception.ServiceException;
import com.ultrapower.framework.core.util.StrUtil;
import com.ultrapower.framework.core.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by LXS on 2018/11/22.
 */
@Service
@ConditionalOnBean(InitDataSourceConfiguration.class)
public class DBConfigPropServiceImpl implements IConfigPropService {

    private static final Logger logger = LoggerFactory.getLogger(DBConfigPropServiceImpl.class);

    @Autowired
    JdbcTemplate template;

    @Override
    public void addProps(ConfigPropReq req) throws ServiceException {

        if (logger.isDebugEnabled()) {
            logger.debug("DBConfigPropServiceImpl  addProps started");
        }

        if (StrUtil.isEquateNullStr(req.getKey()) || null == req.getValue() || StrUtil.isEquateNullStr(req.getApplication())) {
            throw new ServiceException(CommonErrorCode.INVALID_ARGUMENT.getCode());
        }

        if (StrUtil.isEquateNullStr(req.getLabel())) {
            req.setLabel("master");
        }
        if (StrUtil.isEquateNullStr(req.getProfile())) {
            req.setProfile("default");
        }

        try {
            String insertSql = "INSERT INTO U_PROPERTIES(ID,U_KEY,U_VALUE,APPLICATION,U_PROFILE,LABEL) VALUES (?,?,?,?,?,?)";
            Object[] insertParam = new Object[]{UUIDUtil.getUUID(), req.getKey(), req.getValue(), req.getApplication(), req.getProfile(), req.getLabel()};
            int i = template.update(insertSql, insertParam);
            logger.info("执行数据{" + insertParam + "}插入，成功数量:" + i);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("新增属性配置文件失败", e);
            throw new ServiceException("1000101");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("DBConfigPropServiceImpl  addProps started");
        }
    }

    @Override
    public void modifyProps(ConfigPropReq req) throws ServiceException {

        if (logger.isDebugEnabled()) {
            logger.debug("DBConfigPropServiceImpl  modifyProps started");
        }

        if (StrUtil.isEquateNullStr(req.getKey()) || null == req.getValue() || StrUtil.isEquateNullStr(req.getApplication())) {
            throw new ServiceException(CommonErrorCode.INVALID_ARGUMENT.getCode());
        }

        if (StrUtil.isEquateNullStr(req.getLabel())) {
            req.setLabel("master");
        }
        if (StrUtil.isEquateNullStr(req.getProfile())) {
            req.setProfile("default");
        }

        try {
            String updateSql = "UPDATE U_PROPERTIES SET U_VALUE = ? WHERE U_KEY=? AND APPLICATION=? AND U_PROFILE=? AND LABEL=?";
            Object[] updateParam = new Object[]{req.getValue(), req.getKey(), req.getApplication(), req.getProfile(), req.getLabel()};
            int i = template.update(updateSql, updateParam);

            logger.info("执行数据{" + updateParam + "}更新，成功数量:" + i);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("新增属性配置文件失败", e);
            throw new ServiceException("1000101");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("DBConfigPropServiceImpl  modifyProps ended");
        }

    }
}
