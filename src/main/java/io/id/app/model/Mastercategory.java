/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.model;

/**
 *
 * @author permadi
 */

public class Mastercategory {


    private Integer categoryid;

    private String assetcategory;

    private String description;
    
    private short isactive;

    public Mastercategory() {
    }

    public Mastercategory(Integer categoryid, String assetcategory, String description, short isactive) {
        this.categoryid = categoryid;
        this.assetcategory = assetcategory;
        this.description = description;
        this.isactive = isactive;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getAssetcategory() {
        return assetcategory;
    }

    public void setAssetcategory(String assetcategory) {
        this.assetcategory = assetcategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public short getIsactive() {
        return isactive;
    }

    public void setIsactive(short isactive) {
        this.isactive = isactive;
    }

    
}
