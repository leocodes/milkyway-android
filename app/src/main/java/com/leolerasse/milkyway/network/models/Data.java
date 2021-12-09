
package com.leolerasse.milkyway.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    public List<String> album;
    public String center;
    public String title;
    public List<String> keywords;
    public String location;
    public String nasaId;
    @SerializedName("date_created")
    public String dateCreated;
    public String mediaType;
    public String description;
}
