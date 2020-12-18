package com.ultrapower.framework.configuration.service.impl;

import com.ultrapower.framework.configuration.message.ConfigPropReq;
import com.ultrapower.framework.configuration.service.IConfigPropService;
import com.ultrapower.framework.configuration.util.TransferUtils;
import com.ultrapower.framework.configuration.util.YamlUtils;
import com.ultrapower.framework.core.constants.CommonErrorCode;
import com.ultrapower.framework.core.exception.ServiceException;
import com.ultrapower.framework.core.util.ArrayUtil;
import com.ultrapower.framework.core.util.JsonUtils;
import com.ultrapower.framework.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.cloud.config.server.environment.SearchPathLocator;
import org.springframework.cloud.config.server.environment.SvnKitEnvironmentRepository;
import org.springframework.cloud.config.server.resource.ResourceRepository;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.wc17.SvnConflictReport;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNStatus;
import org.tmatesoft.svn.core.wc2.SvnCommit;
import org.tmatesoft.svn.core.wc2.SvnOperationFactory;
import org.tmatesoft.svn.core.wc2.SvnTarget;
import org.tmatesoft.svn.core.wc2.SvnUpdate;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.*;

import static org.springframework.util.StringUtils.hasText;

@Service
@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "subversion")
public class SVNConfigPropServiceImpl implements IConfigPropService {

    private static final Logger logger = LoggerFactory.getLogger(SVNConfigPropServiceImpl.class);

    @Autowired
    SvnKitEnvironmentRepository svnKitEnvironmentRepository;

    @Override
    public void addProps(ConfigPropReq req) throws ServiceException {
        addOrModifyProp(req);

    }

    @Override
    public void modifyProps(ConfigPropReq req) throws ServiceException {
        addOrModifyProp(req);
    }

    private void addOrModifyProp(ConfigPropReq req) throws ServiceException {
        if (StrUtil.isEquateNullStr(req.getKey()) || req.getValue() == null || StrUtil.isEquateNullStr(req.getApplication())) {
            throw new ServiceException(CommonErrorCode.INVALID_ARGUMENT.getCode());
        }

        if (StrUtil.isEquateNullStr(req.getLabel()) || "master".equalsIgnoreCase(req.getLabel())) {
            req.setLabel("trunk");
        }

        if (StrUtil.isEquateNullStr(req.getProfile())) {
            req.setProfile("default");
        }

        SearchPathLocator.Locations pathLocations = this.svnKitEnvironmentRepository.getLocations(req.getApplication(), req.getProfile(), req.getLabel());
        String[] locations = pathLocations.getLocations();
        Environment environment = this.svnKitEnvironmentRepository.findOne(req.getApplication(), req.getProfile(), req.getLabel());
        List<PropertySource> propertySources = environment.getPropertySources();
        List<String> fileNames = new ArrayList<>();
        if (ArrayUtil.isNotEmptyCollection(propertySources)) {
            for (PropertySource propertySource : propertySources) {
                String name = propertySource.getName();
                fileNames.add(name.substring(name.lastIndexOf("/") + 1));
            }
        }
        SvnOperationFactory svnOperationFactory = new SvnOperationFactory();
        if (hasText(this.svnKitEnvironmentRepository.getUsername())) {
            svnOperationFactory.setAuthenticationManager(new DefaultSVNAuthenticationManager(null, false, this.svnKitEnvironmentRepository.getUsername(), this.svnKitEnvironmentRepository.getPassword()));
        }

        for (String location : locations) {
            for (String fileName : fileNames) {
                if (location.startsWith("file:")) {
                    location = location.substring(5);
                }
                File file = new File(location, fileName);
                logger.info("location:" + location + ",fileName:" + fileName + "exist:" + file.exists());

                File tempFile = new File(location, System.currentTimeMillis() + ".properties");
                logger.info("tempFile:" + tempFile.getPath());
                if (file.exists()) {
                    logger.info("file existes : " + file.exists());
                    try {
                        if (fileName.endsWith(".properties")) {


                        } else {
                            logger.info("Started process file : " + file.getPath());
                            Resource resource = new PathResource(file.getPath());
                            Properties properties = YamlUtils.yaml2Properties(resource);
                            Properties newProps = new Properties();
                            Set<Object> keySet = properties.keySet();
                            for (Object obj : keySet) {
                                String key = String.valueOf(obj);
                                newProps.put(key,String.valueOf(properties.getProperty(key)));
                            }
                            newProps.put(req.getKey(), String.valueOf(req.getValue()));
                            FileWriter fw = new FileWriter(tempFile);
                            newProps.store(fw, "temp store");
                            fw.flush();
                            fw.close();
                            logger.info("store properties : " + tempFile.getPath());
                            TransferUtils.properties2Yaml(tempFile.getPath(), file.getPath());
                            logger.info("Transferutil properties2Yaml ");
                            final SvnCommit commit = svnOperationFactory.createCommit();
                            commit.setSingleTarget(SvnTarget.fromFile(file));
                            SVNCommitInfo info = commit.run();
                            logger.info("Commit info  : " + JsonUtils.object2Json(info));
                        }
                    } catch (FileNotFoundException e) {
                        logger.error("读取配置文件失败", e);
                        throw new ServiceException("");
                    } catch (IOException e) {
                        logger.error("写入配置文件失败", e);
                        throw new ServiceException("");
                    } catch (SVNException e) {
                        logger.error("文件上传失败", e);
                        throw new ServiceException("");
                    } finally {
                        if (tempFile.exists()) {
                            tempFile.delete();
                        }
                    }
                }

            }
        }
    }
}
