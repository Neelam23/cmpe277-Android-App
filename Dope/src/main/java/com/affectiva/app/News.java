package com.affectiva.app;

/**
 * Created by NehaP on 10/4/2016.
 */
public class News {
    private String titleText;
    private String excerpt;
    private String imgURL;

    public News(){}

    public News(String title, String ex, String imgurl) {
        this.titleText = title;
        this.excerpt = ex;
        this.imgURL = imgurl;
    }


    public String getTitle(){
        return titleText;
    }

    public void setTitle(String title){
        this.titleText = title;
    }

    public String getExcerpt(){
        return excerpt;
    }

    public void setExcerpt(String ex){
        this.excerpt = ex;
    }

    public String getImage(){
        return imgURL;
    }

    public void setImage(String imgURL){
        this.imgURL = imgURL;
    }
}
