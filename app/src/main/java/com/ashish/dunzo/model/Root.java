package com.ashish.dunzo.model;

public class Root
{
    private Photos photos;

    private String stat;

    public void setPhotos(Photos photos){
        this.photos = photos;
    }
    public Photos getPhotos(){
        return this.photos;
    }
    public void setStat(String stat){
        this.stat = stat;
    }
    public String getStat(){
        return this.stat;
    }
}
