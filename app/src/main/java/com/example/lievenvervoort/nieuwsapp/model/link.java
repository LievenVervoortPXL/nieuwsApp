package com.example.lievenvervoort.nieuwsapp.model;

/**
 * Created by LievenVervoort on 28/01/2018.
 */

public class link {
    private String type;
    private String url;
    private String suggested_link_text;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSuggested_link_text() {
        return suggested_link_text;
    }

    public void setSuggested_link_text(String suggested_link_text) {
        this.suggested_link_text = suggested_link_text;
    }
}
