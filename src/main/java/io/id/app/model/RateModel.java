/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author permadi
 */
@ApiModel(description = "Rate information Model")
public class RateModel {
    
    @JsonProperty("rateId")
    @ApiModelProperty(name = "rateId", value = "Id of rate", required = true)
    private int rateid;
    
    @JsonProperty("rateName")
    @ApiModelProperty(name = "rateName", value = "Name of rate", required = true)
    private String ratename;
    
    @JsonProperty("value")
    @ApiModelProperty(name = "value", value = "valud of rate", required = true)
    private String value;
    
    @JsonProperty("created_date")
    @ApiModelProperty(name = "created_date", value = "created date of rate", required = true)
    private String created;

    public RateModel() {
    }

    public RateModel(int rateid, String ratename, String value, String created) {
        this.rateid = rateid;
        this.ratename = ratename;
        this.value = value;
        this.created = created;
    }

    public int getRateid() {
        return rateid;
    }

    public void setRateid(int rateid) {
        this.rateid = rateid;
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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
    
    
    
    
}
