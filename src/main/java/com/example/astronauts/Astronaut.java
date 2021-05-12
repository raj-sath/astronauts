package com.example.astronauts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Astronaut {
    private String id;
    private String name;
    private String date_of_birth;
    private String bio;
    private String nationality;
    private String profile_image_thumbnail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getProfile_image_thumbnail() {
        return profile_image_thumbnail;
    }

    public void setProfile_image_thumbnail(String profile_image_thumbnail) {
        this.profile_image_thumbnail = profile_image_thumbnail;
    }
}
