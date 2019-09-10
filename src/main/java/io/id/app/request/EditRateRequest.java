/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author permadi
 */
public class EditRateRequest {

    @JsonProperty("id")
    @ApiModelProperty(name = "id", value = "id of rate", required = true)
    private int rateid;

    @JsonProperty("name")
    @ApiModelProperty(name = "name", value = "name of rate", required = true)
    private String ratename;

    @JsonProperty("value")
    @ApiModelProperty(name = "value", value = "value of rate", required = true)
    private String value;

    public EditRateRequest() {
    }

    public EditRateRequest(int rateid, String ratename, String value) {
        this.rateid = rateid;
        this.ratename = ratename;
        this.value = value;
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
    
    
}
