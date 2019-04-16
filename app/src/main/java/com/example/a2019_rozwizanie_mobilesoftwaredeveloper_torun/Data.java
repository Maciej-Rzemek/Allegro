package com.example.a2019_rozwizanie_mobilesoftwaredeveloper_torun;


import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Data {

    private String name;

    @SerializedName("updated_at")
    private Date date;

    // Getters
    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }
}
