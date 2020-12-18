package com.ultrapower.framework.configuration.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.Resource;

import java.util.Map;
import java.util.Properties;

public class YamlUtils {
    private static final Logger logger = LoggerFactory.getLogger(YamlUtils.class);

    public static Map<String, Object> yaml2Map(Resource yamlSource) {
        try {
            YamlMapFactoryBean yaml = new YamlMapFactoryBean();
            yaml.setResources(yamlSource);
            return yaml.getObject();
        } catch (Exception e) {
            logger.error("Cannot read yaml", e);
            return null;
        }
    }

    public static Properties yaml2Properties(Resource yamlSource) {
        try {
            YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
            yaml.setResources(yamlSource);
            return yaml.getObject();
        } catch (Exception e) {
            logger.error("Cannot read yaml", e);
            return null;
        }
    }

}
