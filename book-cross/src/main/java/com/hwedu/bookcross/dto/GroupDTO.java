package com.hwedu.bookcross.dto;

import com.hwedu.bookcross.dto.base.BaseDTO;

public class GroupDTO extends BaseDTO {
    private String id;

    private String name;

    private Boolean isDeleted;

    private Boolean isEnabled;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }


}