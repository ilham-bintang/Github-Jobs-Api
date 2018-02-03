package com.lombok.iak.project_01networking.api;

import com.lombok.iak.project_01networking.model.Job;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hayi on 11/01/18.
 */

public class Repository {
    /**
     * TODO - 05: Membuat Caller dan callback untuk Retrofit
     */

    String BASE_URL = "https://jobs.github.com";
    Retrofit retrofit;

    public Repository() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.build();
    }

    GitAPI getApiClient() {
        return retrofit.create(GitAPI.class);
    }


    /**
     * TODO - 06: Lakukan eksekusi secara asyncronous (dibelakang layar)
     */
    public void getJob(String id, Callback<Job> callback) {
        Call<Job> apiCall = getApiClient().getJob(id);
        apiCall.enqueue(callback);
    }

    public void getJobs(Callback<List<Job>> callback) {
        Call<List<Job>> apiCall = getApiClient().getJobs("android");
        apiCall.enqueue(callback);
    }

}
