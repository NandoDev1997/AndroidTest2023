package com.nandodev1997.androidtestfernandomorales.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "beer")
public class Beer {
    @PrimaryKey
    Long id;
    String name;
    String tagline;
    String first_brewed;
    String description;
    String image_url;
    Double abv;
    Double ibu;
    Double target_fg;
    Double target_og;
    Double ebc;
    Double srm;
    Double ph;
    String brewers_tips;
    String contributed_by;
    Boolean isFavorite;

    public Beer(Long id, String name, String tagline, String first_brewed, String description, String image_url, Double abv, Double ibu, Double target_fg, Double target_og, Double ebc, Double srm, Double ph, String brewers_tips, String contributed_by, Boolean isFavorite) {
        this.id             = id;
        this.name           = name;
        this.tagline        = tagline;
        this.first_brewed   = first_brewed;
        this.description    = description;
        this.image_url      = image_url;
        this.abv            = abv;
        this.ibu            = ibu;
        this.target_fg      = target_fg;
        this.target_og      = target_og;
        this.ebc            = ebc;
        this.srm            = srm;
        this.ph             = ph;
        this.brewers_tips   = brewers_tips;
        this.contributed_by = contributed_by;
        this.isFavorite     = isFavorite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getFirst_brewed() {
        return first_brewed;
    }

    public void setFirst_brewed(String first_brewed) {
        this.first_brewed = first_brewed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Double getAbv() {
        return abv;
    }

    public void setAbv(Double abv) {
        this.abv = abv;
    }

    public Double getIbu() {
        return ibu;
    }

    public void setIbu(Double ibu) {
        this.ibu = ibu;
    }

    public Double getTarget_fg() {
        return target_fg;
    }

    public void setTarget_fg(Double target_fg) {
        this.target_fg = target_fg;
    }

    public Double getTarget_og() {
        return target_og;
    }

    public void setTarget_og(Double target_og) {
        this.target_og = target_og;
    }

    public Double getEbc() {
        return ebc;
    }

    public void setEbc(Double ebc) {
        this.ebc = ebc;
    }

    public Double getSrm() {
        return srm;
    }

    public void setSrm(Double srm) {
        this.srm = srm;
    }

    public Double getPh() {
        return ph;
    }

    public void setPh(Double ph) {
        this.ph = ph;
    }

    public String getBrewers_tips() {
        return brewers_tips;
    }

    public void setBrewers_tips(String brewers_tips) {
        this.brewers_tips = brewers_tips;
    }

    public String getContributed_by() {
        return contributed_by;
    }

    public void setContributed_by(String contributed_by) {
        this.contributed_by = contributed_by;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }
}
