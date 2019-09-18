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
public class Mastertype {

    private Integer typeid;

    private String assettype;

    private String description;

    private int categoryid;

    private short isactive;

    public Mastertype() {
    }

    public Mastertype(Integer typeid, String assettype, String description, int categoryid, short isactive) {
        this.typeid = typeid;
        this.assettype = assettype;
        this.description = description;
        this.categoryid = categoryid;
        this.isactive = isactive;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getAssettype() {
        return assettype;
    }

    public void setAssettype(String assettype) {
        this.assettype = assettype;
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
