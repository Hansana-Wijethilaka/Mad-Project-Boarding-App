package com.example.boadme;

public class Hostal {

    private  int id;
    private String owner_name,hostal_location,phone_num,email,address,num_of_rm,price;
    private long started,finished;

    public Hostal(){

    }

    public Hostal(int id, String owner_name, String hostal_location, String phone_num, String email, String address, String num_of_rm, String price, long started, long finished) {
        this.id = id;
        this.owner_name = owner_name;
        this.hostal_location = hostal_location;
        this.phone_num = phone_num;
        this.email = email;
        this.address = address;
        this.num_of_rm = num_of_rm;
        this.price = price;
        this.started = started;
        this.finished = finished;
    }

    public Hostal(String owner_name, String hostal_location, String phone_num, String email, String address, String num_of_rm, String price, long started, long finished) {
        this.owner_name = owner_name;
        this.hostal_location = hostal_location;
        this.phone_num = phone_num;
        this.email = email;
        this.address = address;
        this.num_of_rm = num_of_rm;
        this.price = price;
        this.started = started;
        this.finished = finished;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getHostal_location() {
        return hostal_location;
    }

    public void setHostal_location(String hostal_location) {
        this.hostal_location = hostal_location;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNum_of_rm() {
        return num_of_rm;
    }

    public void setNum_of_rm(String num_of_rm) {
        this.num_of_rm = num_of_rm;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
