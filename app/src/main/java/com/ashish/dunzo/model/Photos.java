package com.ashish.dunzo.model;

import java.util.List;

public class Photos
{
    private int page;

    private int pages;

    private int perPage;

    private int total;

    private List<Photo> photo;

    public void setPage(int page){
        this.page = page;
    }
    public int getPage(){
        return this.page;
    }
    public void setPages(int pages){
        this.pages = pages;
    }
    public int getPages(){
        return this.pages;
    }
    public void setPerPage(int perPage){
        this.perPage = perPage;
    }
    public int getPerPage(){
        return this.perPage;
    }
    public void setTotal(int total){
        this.total = total;
    }
    public int getTotal(){
        return this.total;
    }
    public void setPhoto(List<Photo> photo){
        this.photo = photo;
    }
    public List<Photo> getPhoto(){
        return this.photo;
    }
}
