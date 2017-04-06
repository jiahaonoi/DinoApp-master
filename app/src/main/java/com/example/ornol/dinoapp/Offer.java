package com.example.ornol.dinoapp;

import java.util.Date;

/**
 * Created by ornol on 20.3.2017.
 */

public class Offer {
    private int id,price,restId;
    private String name,type,description,photo,restName,timeTo,timeFrom;
    private Date startDate,endDate;
    private int[] weekdays;
    Offer(int id, String name, String type, int price, Date startDate, String description, Date endDate,String photo,int restId,String restName, String timeFrom,String timeTo, int[] weekdays){
        this.id=id;
        this.name=name;
        this.type=type;
        this.price=price;
        this.startDate=startDate;
        this.endDate=endDate;
        this.description=description;
        this.photo=photo;
        this.restId=restId;
        this.restName=restName;
        this.timeFrom=timeFrom;
        this.timeTo=timeTo;
        this.weekdays=weekdays;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRestId() {
        return restId;
    }

    public void setRestId(int restId) {
        this.restId = restId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int[] getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(int[] weekdays) {
        this.weekdays = weekdays;
    }
}