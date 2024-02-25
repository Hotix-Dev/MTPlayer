package com.tm.mtplayer.retrofit;

import com.tm.mtplayer.models.AppValidity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RetrofitInterface {

    /***
     ** GET *********************************************************************************************
     **/

    //Get Application Validity service call
    @GET
    Call <AppValidity> getApplicationValidityQuery(@Url String URL,
                                                   @Query("macAdress") String macAdress);


    /***
     ** POST ********************************************************************************************
     **/

}