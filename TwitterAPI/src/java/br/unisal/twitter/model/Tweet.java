/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisal.twitter.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JETHER
 */
@Entity
@Table(name = "tweets")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(propOrder={"idPessoa", "nome", "email"})
public class Tweet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String userName;
    @Column
    private String locationUser;
    @Column
    private String text;
    @Column
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date created;
    @Column
    private double lat;
    @Column
    private double lon;

    public Tweet() {
    }

    public Tweet(String userName, String locationUser, String text, Date created, double lat, double lon) {
        this.userName = userName;
        this.locationUser = locationUser;
        this.text = text;
        this.created = created;
        this.lat = lat;
        this.lon = lon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLocationUser() {
        return locationUser;
    }

    public void setLocationUser(String locationUser) {
        this.locationUser = locationUser;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.userName);
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.lat) ^ (Double.doubleToLongBits(this.lat) >>> 32));
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.lon) ^ (Double.doubleToLongBits(this.lon) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tweet other = (Tweet) obj;
        if (Double.doubleToLongBits(this.lat) != Double.doubleToLongBits(other.lat)) {
            return false;
        }
        if (Double.doubleToLongBits(this.lon) != Double.doubleToLongBits(other.lon)) {
            return false;
        }
        return Objects.equals(this.userName, other.userName);
    }

    @Override
    public String toString() {
        return "Tweet{" + "userName=" + userName + ", locationUser=" + locationUser + ", text=" + text + ", created=" + created + ", lat=" + lat + ", lon=" + lon + '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
