package com.springaop.entity;

public class Log {

    private String id;

    private String operaName;

    private String moduleName;

    private String modifyBefore;

    private String modifyAfter;

    //请求参数
    private String operateParams;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperaName() {
        return operaName;
    }

    public void setOperaName(String operaName) {
        this.operaName = operaName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModifyBefore() {
        return modifyBefore;
    }

    public void setModifyBefore(String modifyBefore) {
        this.modifyBefore = modifyBefore;
    }

    public String getModifyAfter() {
        return modifyAfter;
    }

    public void setModifyAfter(String modifyAfter) {
        this.modifyAfter = modifyAfter;
    }

    public String getOperateParams() {
        return operateParams;
    }

    public void setOperateParams(String operateParams) {
        this.operateParams = operateParams;
    }
}
