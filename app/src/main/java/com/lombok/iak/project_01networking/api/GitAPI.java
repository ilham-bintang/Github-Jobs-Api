package com.lombok.iak.project_01networking.api;

import com.lombok.iak.project_01networking.model.Job;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by hayi on 11/01/18.
 */

public interface GitAPI {

    /**
     * TODO - 04: Membuat Interface Endpoint.
     *
     */

    @GET("positions/{id}.json")
    Call<Job> getJob(@Path("id") String idJob);

    @GET("positions.json?search={query}")
    Call<List<Job>> getJobs(@Query("search") String query);
}
