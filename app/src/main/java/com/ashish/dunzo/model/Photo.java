package com.ashish.dunzo.model;

public class Photo
{
    private String id;

    private String owner;

    private String secret;

    private String server;

    private int farm;

    private String title;

    private int isPublic;

    private int isFriend;

    private int isFamily;

    private String constructedURL;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setOwner(String owner){
        this.owner = owner;
    }
    public String getOwner(){
        return this.owner;
    }
    public void setSecret(String secret){
        this.secret = secret;
    }
    public String getSecret(){
        return this.secret;
    }
    public void setServer(String server){
        this.server = server;
    }
    public String getServer(){
        return this.server;
    }
    public void setFarm(int farm){
        this.farm = farm;
    }
    public int getFarm(){
        return this.farm;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setIsPublic(int isPublic){
        this.isPublic = isPublic;
    }
    public int getIsPublic(){
        return this.isPublic;
    }
    public void setIsFriend(int isFriend){
        this.isFriend = isFriend;
    }
    public int getIsFriend(){
        return this.isFriend;
    }
    public void setIsFamily(int isFamily){
        this.isFamily = isFamily;
    }
    public int getIsFamily(){
        return this.isFamily;
    }

    public String constructURL() {
      //  https://farm<farm>.staticflickr.com/<server>/<id>_<secret>_m.jpg
        if( constructedURL == null ) {

            constructedURL =  "https://farm" +
                    farm +
                    ".staticflickr.com/" +
                    server +
                    "/" +
                    id +
                    "_" +
                    secret +
                    ".jpg";
        }

        return constructedURL;
    }

}
