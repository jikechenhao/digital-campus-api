package com.hwedu.bookcross.po;

/**
 * 默认配置项PO
 * <p>
 * Created by lizhiqiang on 2016/4/18.
 */
public class ConfigPO {
    private String id;
    private String configJSON;
    private Boolean isDefault;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConfigJSON() {
        return configJSON;
    }

    public void setConfigJSON(String configJSON) {
        this.configJSON = configJSON;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }
}
