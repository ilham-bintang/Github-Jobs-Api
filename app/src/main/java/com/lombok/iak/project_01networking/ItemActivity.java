package com.lombok.iak.project_01networking;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.lombok.iak.project_01networking.api.Repository;
import com.lombok.iak.project_01networking.model.Job;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemActivity extends AppCompatActivity {

    TextView title, created_at, location, type, description, company;
    ImageView thumbnail;
    Job job;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        title = findViewById(R.id.judul);
        created_at = findViewById(R.id.created_at);
        location= findViewById(R.id.location);
        type = findViewById(R.id.type);
        description = findViewById(R.id.description);
        company = findViewById(R.id.company);

        thumbnail = findViewById(R.id.thumbnail);

        String id = getIntent().getStringExtra("id");

        new Repository().getJob(id, new Callback<Job>() {
            @Override
            public void onResponse(Call<Job> call, Response<Job> response) {
                job = response.body();

                title.setText(job.getTitle());
                created_at.setText(job.getCreatedAt());
                location.setText(job.getLocation());
                type.setText(job.getType());
                description.setText(Html.fromHtml(job.getDescription()));
                company.setText(job.getCompany());

                Glide.with(ItemActivity.this).load(job.getCompanyLogo()).into(thumbnail);
            }

            @Override
            public void onFailure(Call<Job> call, Throwable t) {

            }
        });
    }

    public void original (View v) {

    }
    public void company (View v) {

    }
}

