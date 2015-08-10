package com.nytimes.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by alexio on 8/8/15.
 */

/**
 * Stringer entity for data layer
 */
public class Stringer {

    public class Location {
        String type;
        float[] coordinates;
    }

    @SerializedName("ID")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("desk")
    private String desk;

    @SerializedName("notes")
    private String notes;

    @SerializedName("phone")
    private String phone;

    @SerializedName("email")
    private String email;

    @SerializedName("contract")
    private String contract;

    @SerializedName("languages")
    private String[] languages;

    @SerializedName("skilss")
    private String[] skills;

    @SerializedName("baseLocation")
    private Location baseLocation;

    @SerializedName("currentLocation")
    private Location currentLocation;

    @SerializedName("available")
    boolean available;

    public Stringer(){
    }

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

    public String getDesk() {
        return desk;
    }

    public void setDesk(String desk) {
        this.desk = desk;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public Location getBaseLocation() {
        return baseLocation;
    }

    public void setBaseLocation(Location baseLocation) {
        this.baseLocation = baseLocation;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Stringer - Id: " + getId() + " , Name: " + getName();
    }

}