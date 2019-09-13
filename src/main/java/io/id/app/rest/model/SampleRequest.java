package io.id.app.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Sample Request")
public class SampleRequest {

    @JsonProperty("user_name")
    @ApiModelProperty(name = "user_name", value = "Username in AD", required = true)
    private String username;

    @ApiModelProperty(name = "password", value = "Password of AD Account", required = true)
    @JsonProperty("password")
    private String password;

    public SampleRequest() {
        // Empty Constructor
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
