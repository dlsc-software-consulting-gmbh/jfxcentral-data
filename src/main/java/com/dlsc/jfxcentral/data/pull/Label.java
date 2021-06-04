package com.dlsc.jfxcentral.data.pull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//      {
//              "id": 1593661169,
//              "node_id": "MDU6TGFiZWwxNTkzNjYxMTY5",
//              "url": "https://api.github.com/repos/openjdk/jfx/labels/integrated",
//              "name": "integrated",
//              "color": "76f297",
//              "default": false,
//              "description": "Pull request has been integrated"
//              }
public class Label {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("color")
    @Expose
    private String color;

    @SerializedName("description")
    @Expose
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
