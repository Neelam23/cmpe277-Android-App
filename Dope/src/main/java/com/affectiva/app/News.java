package com.affectiva.app;

/**
 * Created by NehaP on 10/4/2016.
 */
public class News {
    private String newsID;
    private String titleText;
    private String excerpt;
    private String detail;
    private String publisher;
    private String date;
    private String imgURL;


    public News(){}

    public News(String newsid, String title, String ex,String detail,String pub,String date,String img) {
        this.newsID = newsid;
        this.titleText = title;
        this.excerpt = ex;
        this.detail = detail;
        this.publisher = pub;
        this.date = date;
        this.imgURL = img;
    }


    public String getNewsID(){
        return newsID;
    }

    public void setNewsID(String ID){
        this.newsID = ID;
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

    public String getDetail(){
        return detail;
    }

    public void setDetail(String detail){
        this.detail = detail;
    }

    public String getpublisher(){
        return publisher;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getImage(){
        return imgURL;
    }

    public void setImage(String imgURL){
        this.imgURL = imgURL;
    }
}
