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
@ApiModel(description = "Asset information Model")
public class AssetDetailsModel {
    
    @JsonProperty("code")
    @ApiModelProperty(name = "code", value = " ", required = true)
    private String assetcode;
    
    @JsonProperty("name")
    @ApiModelProperty(name = "name", value = " ", required = true)
    private String assetname;
    
    @JsonProperty("type")
    @ApiModelProperty(name = "type", value = " ", required = true)
    private String assettype;
    
    @JsonProperty("manufacture")
    @ApiModelProperty(name = "manufacture", value = " ", required = true)
    private String manufacture;
    
    @JsonProperty("model")
    @ApiModelProperty(name = "model", value = " ", required = true)
    private String model;
    
    @JsonProperty("vendor")
    @ApiModelProperty(name = "vendor", value = " ", required = true)
    private String vendorname;
    
    @JsonProperty("note")
    @ApiModelProperty(name = "note", value = " ", required = true)
    private String note;
    
    @JsonProperty("register_date")
    @ApiModelProperty(name = "register_date", value = " ", required = true)
    private String created;

    public AssetDetailsModel() {
    }

    public AssetDetailsModel(String assetcode, String assetname, String assettype, String manufacture, String model, String vendorname, String note, String created) {
        this.assetcode = assetcode;
        this.assetname = assetname;
        this.assettype = assettype;
        this.manufacture = manufacture;
        this.model = model;
        this.vendorname = vendorname;
        this.note = note;
        this.created = created;
    }

    public String getAssetcode() {
        return assetcode;
    }

    public void setAssetcode(String assetcode) {
        this.assetcode = assetcode;
    }

    public String getAssetname() {
        return assetname;
    }

    public void setAssetname(String assetname) {
        this.assetname = assetname;
    }

    public String getAssettype() {
        return assettype;
    }

    public void setAssettype(String assettype) {
        this.assettype = assettype;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVendorname() {
        return vendorname;
    }

    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
    
    
    
    
    
}
