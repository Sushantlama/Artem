package com.example.artem.LogIn.data;

public class User {
    String name,uid,profileimage,email;

    public User(){

    }

    public User(String name, String uid, String profileImage, String email) {
        this.name = name;
        this.uid = uid;
        this.profileimage = profileImage;
        this.email= email;
    }


    public String getname() {
        return name;
    }

    public String getuid() {
        return uid;
    }

    public String getprofileimage() {
        return profileimage;
    }

    public String getemail() {
        return email;
    }
}
