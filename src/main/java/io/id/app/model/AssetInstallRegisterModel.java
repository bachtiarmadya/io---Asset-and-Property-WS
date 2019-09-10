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
@ApiModel(description = "Asset installtion registration information Model")
public class AssetInstallRegisterModel {
    
    @JsonProperty("locationName")
    @ApiModelProperty(name = "locationName", value = " ", required = true)
    private String locationname;
    
    @JsonProperty("assetCode")
    @ApiModelProperty(name = "assetCode", value = " ", required = true)
    private String assetcode;
    
    @JsonProperty("buildingName")
    @ApiModelProperty(name = "buildingName", value = " ", required = true)
    private String buildingname;
    
    @JsonProperty("memberCode")
    @ApiModelProperty(name = "memberCode", value = " ", required = true)
    private int membercode;
    
    @JsonProperty("rate")
    @ApiModelProperty(name = "rate", value = " ", required = true)
    private int rateid;
    
    @JsonProperty("geoLocation")
    @ApiModelProperty(name = "geoLocation", value = " ", required = true)
    private String geolocation;
    
    @JsonProperty("photo")
    @ApiModelProperty(name = "photo", value = "Separate with comma", required = true)
    private String photo;
    
    @JsonProperty("note")
    @ApiModelProperty(name = "note", value = " ", required = true)
    private String note;

    public AssetInstallRegisterModel() {
    }

    public AssetInstallRegisterModel(String locationname, String assetcode, String buildingname, int membercode, int rateid, String geolocation, String photo, String note) {
        this.locationname = locationname;
        this.assetcode = assetcode;
        this.buildingname = buildingname;
        this.membercode = membercode;
        this.rateid = rateid;
        this.geolocation = geolocation;
        this.photo = photo;
        this.note = note;
    }

    public String getLocationname() {
        return locationname;
    }

    public void setLocationname(String locationname) {
        this.locationname = locationname;
    }

    public String getAssetCode() {
        return assetcode;
    }

    public void setAssetCode(String assetcode) {
        this.assetcode = assetcode;
    }

    public String getBuildingname() {
        return buildingname;
    }

    public void setBuildingname(String buildingname) {
        this.buildingname = buildingname;
    }

    public int getMembercode() {
        return membercode;
    }

    public void setMembercode(int membercode) {
        this.membercode = membercode;
    }

    public int getRateid() {
        return rateid;
    }

    public void setRateid(int rateid) {
        this.rateid = rateid;
    }

    public String getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(String geolocation) {
        this.geolocation = geolocation;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    
}
