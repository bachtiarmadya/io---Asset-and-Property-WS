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
@ApiModel(description = "Add province request Model")
public class AddProvinceRequest {

    @JsonProperty("provinceCode")
    @ApiModelProperty(name = "provinceCode", value = "Code of province", required = true)
    private String provincecode;

    @JsonProperty("provinceName")
    @ApiModelProperty(name = "provinceName", value = "Name of province", required = true)
    private String provincename;

    public AddProvinceRequest() {
    }

    public AddProvinceRequest(String provincecode, String provincename) {
        this.provincecode = provincecode;
        this.provincename = provincename;
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
    
    

}
