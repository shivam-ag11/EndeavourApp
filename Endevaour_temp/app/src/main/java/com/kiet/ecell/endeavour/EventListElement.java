package com.kiet.ecell.endeavour;

/**
 * Created by USER on 01/06/2016.
 */
public class EventListElement {
    String Time;
    String Title;
    String OneLine;
    String Place;
    int ID;
    public EventListElement(String Time,String Title,String OneLine,String Place,int ID)
    {
        this.Time=Time;
        this.Title=Title;
        this.OneLine=OneLine;
        this.Place=Place;
        this.ID=ID;
    }

    public String getTime() {
        return Time;
    }

    public String getTitle() {
        return Title;
    }

    public String getOneLine() {
        return OneLine;
    }

    public String getPlace() {
        return Place;
    }

    public int getID() {
        return ID;
    }
}
