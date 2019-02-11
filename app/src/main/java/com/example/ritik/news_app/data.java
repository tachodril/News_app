package com.example.ritik.news_app;
public class data {
    private String title;
    private String url;
    private String url_img;
    private String date;
    private String name;
    private String content;
    private String description;

    public data(String qtitle,String qurl_img,String qurl,String qname,String qdate,String qcontent,String qdesc){
        title=qtitle;
        url=qurl;
        url_img=qurl_img;
        name=qname;
        date=qdate;
        content=qcontent;
        description=qdesc;
    }
    public String geturl() {
        return url;
    }
    public String getdescription() {
        return description;
    }
    public String getcontent() {
        return content;
    }
    public String getdate(){
        return date;
    }
    public String gettitle(){
        return title;
    }
    public String geturl_img(){
        return url_img;
    }
    public String getName(){
        return name;
    }
}
