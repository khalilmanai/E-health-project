package com.example.demo.models;


public class Articles {
    private int article_id;
    private String title;
    private String topic;
    private String date;
    private int views;
    private int specialiste_id;

    private String content;
  public Articles(){}

    public Articles(int article_id, String title, String topic, String date, int views, int specialiste_id,String content) {
        this.article_id = article_id;
        this.title = title;
        this.topic = topic;
        this.date = date;
        this.views = views;
        this.specialiste_id = specialiste_id;
        this.content =content;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getSpecialiste_id() {
        return specialiste_id;
    }

    public void setSpecialiste_id(int specialiste_id) {
        this.specialiste_id = specialiste_id;
    }

    public String getContent(){return content;}

    public void setContent(String content){this.content=content;}

    @Override
    public String toString() {
        return "Articles{" +
                "article_id=" + article_id +
                ", title='" + title + '\'' +
                ", topic='" + topic + '\'' +
                ", date='" + date + '\'' +
                ", views=" + views +
                ", specialiste_id=" + specialiste_id +
                '}';
    }
}
