/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author permadi
 */
@ApiModel(description = "Edit province request Model")
public class EditProvinceRequest {
    
    @JsonProperty("provinceId")
    @ApiModelProperty(name = "provinceId", value = "Id of province", required = true)
    private int provinceid;
    
    @JsonProperty("provinceCode")
    @ApiModelProperty(name = "provinceCode", value = "Code of province", required = true)
    private String provincecode;

    @JsonProperty("provinceName")
    @ApiModelProperty(name = "provinceName", value = "Name of province", required = true)
    private String provincename;
    
    @JsonProperty("isActive")
    @ApiModelProperty(name = "isActive", value = "Is province activate", required = true)
    private int isactive;

    public EditProvinceRequest() {
    }

    public EditProvinceRequest(int provinceid, String provincecode, String provincename, int isactive) {
        this.provinceid = provinceid;
        this.provincecode = provincecode;
        this.provincename = provincename;
        this.isactive = isactive;
    }

    public int getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(int provinceid) {
        this.provinceid = provinceid;
    }

    public String getProvincecode() {
        return provincecode;
    }

    public void setProvincecode(String provincecode) {
        this.provincecode = provincecode;
    }

    public String getProvincename() {
        return provincename;
    }

    public void setProvincename(String provincename) {
        this.provincename = provincename;
    }

    public int getIsactive() {
        return isactive;
    }

    public void setIsactive(int isactive) {
        this.isactive = isactive;
    }
    
    
    
}
