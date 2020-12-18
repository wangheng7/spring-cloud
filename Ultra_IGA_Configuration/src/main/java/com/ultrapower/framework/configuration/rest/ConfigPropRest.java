package com.ultrapower.framework.configuration.rest;

import com.ultrapower.framework.configuration.config.InitDataSourceConfiguration;
import com.ultrapower.framework.configuration.message.ConfigPropReq;
import com.ultrapower.framework.configuration.service.IConfigPropService;
import com.ultrapower.framework.core.domain.BatchResult;
import com.ultrapower.framework.core.domain.OperateResult;
import com.ultrapower.framework.core.exception.ServiceException;
import com.ultrapower.framework.core.util.ArrayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
* Created by LXS on 2018/11/22.
*/
@RestController
@RequestMapping("/config/props")
public class ConfigPropRest {

    private static final Logger logger = LoggerFactory.getLogger(ConfigPropRest.class);

    @Autowired
    IConfigPropService service;

    @RequestMapping("/add")
    @ResponseBody
    public BatchResult add(@RequestBody List<ConfigPropReq> reqs) {

        if (logger.isDebugEnabled()) {
            logger.debug("Execute ConfigPropRest add started!!!");
        }

        BatchResult<ConfigPropReq> result = new BatchResult<>();

        //验证参数是否合法
        if (ArrayUtil.isEmptyCollection(reqs)) {
            logger.error(String.format("An error occurred while accessing the [%s]", "/config/props/add"));
            result.setSuccess(false);
            return result;
        }

        List<OperateResult<ConfigPropReq>> failedList = new ArrayList<>();
        List<OperateResult<ConfigPropReq>> successList = new ArrayList<>();

        for (ConfigPropReq req : reqs) {
            try {
                OperateResult<ConfigPropReq> successOper = new OperateResult<>();
                // 依次去添加主账号组
                service.addProps(req);
                // 记录添加成功的数据
                successOper.setData(req);
                // 把操作成功的数据添加进入操作成功的集合中
                successList.add(successOper);
            } catch (ServiceException e) {
                logger.error(String.format("An error occurred while accessing the [%s]", "/config/props/add"), e);
                OperateResult<ConfigPropReq> failedOper = new OperateResult<>();
                // 记录操作失败的数据
                failedOper.setData(req);
                // 记录失败的编码
                failedOper.setErrorCode(e.getMessage());
                // 记录失败的原因
                failedOper.setErrorMsg(e.getMessage());
                // 把操作的数据添加进入失败的集合中
                failedOper.setSuccess(false);
                failedList.add(failedOper);
            }catch (Exception e) {
                logger.error(String.format("An error occurred while accessing the [%s]","/config/props/add"), e);
                OperateResult<ConfigPropReq> failedOper = new OperateResult<>();
                // 记录操作失败的数据
                failedOper.setData(req);
                // 把操作的数据添加进入失败的集合中
                failedOper.setSuccess(false);
                failedList.add(failedOper);
            }
        }

        result.setSuccessList(successList);
        result.setFailedList(failedList);

        if (ArrayUtil.isEmptyCollection(successList)) {
            result.setSuccess(false);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Execute ConfigPropRest add end");
        }

        return result;
    }

    @RequestMapping("/modify")
    @ResponseBody
    public BatchResult modify(@RequestBody List<ConfigPropReq> reqs) {

        if (logger.isDebugEnabled()) {
            logger.debug("Execute ConfigPropRest modify started!!!");
        }

        BatchResult<ConfigPropReq> result = new BatchResult<>();

        //验证参数是否合法
        if (ArrayUtil.isEmptyCollection(reqs)) {
            logger.error(String.format("An error occurred while accessing the [%s]", "/config/props/modify"));
            result.setSuccess(false);
            return result;
        }

        List<OperateResult<ConfigPropReq>> failedList = new ArrayList<>();
        List<OperateResult<ConfigPropReq>> successList = new ArrayList<>();

        for (ConfigPropReq req : reqs) {
            try {
                OperateResult<ConfigPropReq> successOper = new OperateResult<>();
                // 依次去添加配置
                service.modifyProps(req);
                // 记录添加成功的数据
                successOper.setData(req);
                // 把操作成功的数据添加进入操作成功的集合中
                successList.add(successOper);
            } catch (ServiceException e) {
                logger.error(String.format("An error occurred while accessing the [%s]", "/config/props/modify"), e);
                OperateResult<ConfigPropReq> failedOper = new OperateResult<>();
                // 记录操作失败的数据
                failedOper.setData(req);
                // 记录失败的编码
                failedOper.setErrorCode(e.getMessage());
                // 记录失败的原因
                failedOper.setErrorMsg(e.getMessage());
                // 把操作的数据添加进入失败的集合中
                failedOper.setSuccess(false);
                failedList.add(failedOper);
            }catch (Exception e) {
                logger.error(String.format("An error occurred while accessing the [%s]","/config/props/modify"), e);
                OperateResult<ConfigPropReq> failedOper = new OperateResult<>();
                // 记录操作失败的数据
                failedOper.setData(req);
                // 把操作的数据添加进入失败的集合中
                failedOper.setSuccess(false);
                failedList.add(failedOper);
            }
        }

        result.setSuccessList(successList);
        result.setFailedList(failedList);

        if (ArrayUtil.isEmptyCollection(successList)) {
            result.setSuccess(false);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Execute ConfigPropRest modify end");
        }

        return result;
    }

}
