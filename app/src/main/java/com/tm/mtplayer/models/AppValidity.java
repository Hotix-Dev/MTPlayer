package com.tm.mtplayer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppValidity {

    @SerializedName("Type")
    @Expose
    private Integer type;
    @SerializedName("DateExpiration")
    @Expose
    private String expirationDate;

    public AppValidity() {
    }

    public AppValidity(Integer type, String expirationDate) {
        this.type = type;
        this.expirationDate = expirationDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getDate() {
        try {

            SimpleDateFormat _FromFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            SimpleDateFormat _ToFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date _Date = _FromFormat.parse(this.expirationDate);
            return _ToFormat.format(_Date);
        } catch (ParseException e) {
            return "-";
        }
    }
}
