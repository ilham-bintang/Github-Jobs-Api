package com.lombok.iak.project_01networking.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lombok.iak.project_01networking.ItemActivity;
import com.lombok.iak.project_01networking.R;
import com.lombok.iak.project_01networking.model.Job;

import java.util.List;

/**
 * Created by nullphantom on 01/02/18.
 */

public class JobCardAdapter extends RecyclerView.Adapter<JobCardAdapter.MyViewHolder> {

    private Context mContext;
    private List<Job> jobList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title, created_at;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            created_at = itemView.findViewById(R.id.created_at);

            thumbnail = itemView.findViewById(R.id.thumbnail);
            overflow = itemView.findViewById(R.id.overflow);
        }
    }

    public JobCardAdapter(Context mContext, List<Job> jobList) {
        this.mContext = mContext;
        this.jobList = jobList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.job_card, parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Job job = jobList.get(position);

        holder.title.setText(job.getTitle());
        holder.created_at.setText(job.getCreatedAt());

        Glide.with(mContext).load(job.getCompanyLogo()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, ItemActivity.class);
                i.putExtra("id", job.getId());
                mContext.startActivity(i);
            }
        });
    }

    private void showPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater =  popup.getMenuInflater();
        inflater.inflate(R.menu.menu_job,popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuOnclickListener());
    }

    class MyMenuOnclickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuOnclickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.apply:
                    Toast.makeText(mContext, "Apply!", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.save_to_favorite :
                    Toast.makeText(mContext, "Saved to favorites!", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }
}
