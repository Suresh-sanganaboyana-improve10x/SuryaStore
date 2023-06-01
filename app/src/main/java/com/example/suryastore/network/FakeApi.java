package com.example.suryastore.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FakeApi {

    public FakeApiService createFakeApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FakeApiService fakeApiService = retrofit.create(FakeApiService.class);
        return fakeApiService;
    }
}
