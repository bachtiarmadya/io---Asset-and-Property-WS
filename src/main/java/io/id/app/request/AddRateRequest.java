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
@ApiModel(description = "Add rate request Model")
public class AddRateRequest {

    @JsonProperty("name")
    @ApiModelProperty(name = "name", value = "name of rate", required = true)
    private String ratename;

    @JsonProperty("value")
    @ApiModelProperty(name = "value", value = "value of rate", required = true)
    private String value;

    public AddRateRequest() {
    }

    public AddRateRequest(String ratename, String value) {
        this.ratename = ratename;
        this.value = value;
    }

    public String getRatename() {
        return ratename;
    }

    public void setRatename(String ratename) {
        this.ratename = ratename;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    

}
