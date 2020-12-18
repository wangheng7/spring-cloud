package com.ultrapower.framework.configuration.message;

/**
 * Created by LXS on 2018/11/22.
 */
public class ConfigPropReq {

    /**
     * 属性名称
     */
    private String key;

    /**
     * 属性值
     */
    private Object value;

    /**
     * 配置文件名称
     */
    private String application;

    /**
     * 活动配置文件，默认default
     */
    private String profile = "default";

    /**
     * 分支，默认master
     */
    private String label;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
