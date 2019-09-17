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

public class Mastermemberlevel {


    private String levelcode;
    private String levelname;
    private String description;
    private short isactive;
    private Integer memberlevelid;

    public Mastermemberlevel() {
    }

    public Mastermemberlevel(String levelcode, String levelname, String description, short isactive, Integer memberlevelid) {
        this.levelcode = levelcode;
        this.levelname = levelname;
        this.description = description;
        this.isactive = isactive;
        this.memberlevelid = memberlevelid;
    }

    public String getLevelcode() {
        return levelcode;
    }

    public void setLevelcode(String levelcode) {
        this.levelcode = levelcode;
    }

    public String getLevelname() {
        return levelname;
    }

    public void setLevelname(String levelname) {
        this.levelname = levelname;
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

    public Integer getMemberlevelid() {
        return memberlevelid;
    }

    public void setMemberlevelid(Integer memberlevelid) {
        this.memberlevelid = memberlevelid;
    }

   
}
