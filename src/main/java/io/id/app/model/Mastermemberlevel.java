/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author permadi
 */
@Entity
@Table(name = "mastermemberlevel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mastermemberlevel.findAll", query = "SELECT m FROM Mastermemberlevel m"),
    @NamedQuery(name = "Mastermemberlevel.findByMemberlevelid", query = "SELECT m FROM Mastermemberlevel m WHERE m.memberlevelid = :memberlevelid"),
    @NamedQuery(name = "Mastermemberlevel.findByLevelcode", query = "SELECT m FROM Mastermemberlevel m WHERE m.levelcode = :levelcode"),
    @NamedQuery(name = "Mastermemberlevel.findByLevelname", query = "SELECT m FROM Mastermemberlevel m WHERE m.levelname = :levelname"),
    @NamedQuery(name = "Mastermemberlevel.findByDescription", query = "SELECT m FROM Mastermemberlevel m WHERE m.description = :description"),
    @NamedQuery(name = "Mastermemberlevel.findByIsactive", query = "SELECT m FROM Mastermemberlevel m WHERE m.isactive = :isactive")})
public class Mastermemberlevel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "memberlevelid")
    private Integer memberlevelid;
    @Size(max = 10)
    @Column(name = "levelcode")
    private String levelcode;
    @Size(max = 20)
    @Column(name = "levelname")
    private String levelname;
    @Size(max = 50)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isactive")
    private short isactive;

    public Mastermemberlevel() {
    }

    public Mastermemberlevel(Integer memberlevelid) {
        this.memberlevelid = memberlevelid;
    }

    public Mastermemberlevel(Integer memberlevelid, short isactive) {
        this.memberlevelid = memberlevelid;
        this.isactive = isactive;
    }

    public Integer getMemberlevelid() {
        return memberlevelid;
    }

    public void setMemberlevelid(Integer memberlevelid) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (memberlevelid != null ? memberlevelid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mastermemberlevel)) {
            return false;
        }
        Mastermemberlevel other = (Mastermemberlevel) object;
        if ((this.memberlevelid == null && other.memberlevelid != null) || (this.memberlevelid != null && !this.memberlevelid.equals(other.memberlevelid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "io.id.app.model.Mastermemberlevel[ memberlevelid=" + memberlevelid + " ]";
    }
    
}
