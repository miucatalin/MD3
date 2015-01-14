package com.example.catalin.weatherb.Weather;

/**
 * Created by catalin on 10.01.2015.
 */
//Aceasta clada contine campurile pentru fiecare element al listei din explorer
public class Item {
    private String name;//nume fisier
    private int age;
    private int score;
    private int img;
    private String gender;
    public Item()
    {


    }

    public Item(String name, int age, int score, String gender) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.gender = gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public Item(String name, int age, int score, String gender,int img) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.img = img;
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getScore() {
        return score;
    }

    public int getImg() {
        return img;
    }
}