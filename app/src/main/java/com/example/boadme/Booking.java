package com.example.boadme;

public class Booking {

    private  int id;
    private String name,contact,age,gender;
    private long started,finished;


    public Booking() {

    }

    public Booking(int id, String name, String contact, String age, String gender, long started, long finished) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.age = age;
        this.gender = gender;
        this.started = started;
        this.finished = finished;
    }

    public Booking(String name, String contact, String age, String gender, long started, long finished) {
        this.name = name;
        this.contact = contact;
        this.age = age;
        this.gender = gender;
        this.started = started;
        this.finished = finished;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getStarted() {
        return started;
    }

    public void setStarted(long started) {
        this.started = started;
    }

    public long getFinished() {
        return finished;
    }

    public void setFinished(long finished) {
        this.finished = finished;
    }
}
