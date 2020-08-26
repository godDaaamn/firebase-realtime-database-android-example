package com.pillar.ind.model;

public class BadHistory{
    private String comission,first,img_link,link,rating_link,repeated,time;

    public BadHistory() {
    }

    public BadHistory(String comission, String first, String img_link, String link, String rating_link, String repeated, String time) {
        this.comission = comission;
        this.first = first;
        this.img_link = img_link;
        this.link = link;
        this.rating_link = rating_link;
        this.repeated = repeated;
        this.time = time;
    }

    public String getComission() {
        return comission;
    }

    public void setComission(String comission) {
        this.comission = comission;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getImg_link() {
        return img_link;
    }

    public void setImg_link(String img_link) {
        this.img_link = img_link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRating_link() {
        return rating_link;
    }

    public void setRating_link(String rating_link) {
        this.rating_link = rating_link;
    }

    public String getRepeated() {
        return repeated;
    }

    public void setRepeated(String repeated) {
        this.repeated = repeated;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
