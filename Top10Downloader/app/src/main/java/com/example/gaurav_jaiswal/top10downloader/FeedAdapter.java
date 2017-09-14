package com.example.gaurav_jaiswal.top10downloader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gaurav_jaiswal on 4/3/17.
 */

public class FeedAdapter extends ArrayAdapter {
    private static final String TAG = "FeedAdapter";
    private final int layoutResourse;
    private final LayoutInflater layoutInflator;
    private List<FeedEntry> applications;

    public FeedAdapter(Context context, int resource, List<FeedEntry> applications) {
        super(context, resource);
        this.layoutResourse=resource;
        this.layoutInflator=LayoutInflater.from(context);


        this.applications = applications;
    }

    @Override
    public int getCount() {
        return applications.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            Log.d(TAG, "getView: called with null view");
            

            convertView=layoutInflator.inflate(layoutResourse,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            Log.d(TAG, "getView: provided a convertView");

            viewHolder=(ViewHolder)convertView.getTag();
        }
        TextView tvName=(TextView)convertView.findViewById(R.id.tvName);
        TextView tvArtist=(TextView)convertView.findViewById(R.id.tvArtist);
        TextView tvSummary=(TextView)convertView.findViewById(R.id.tvSummary);
        FeedEntry currentApp=applications.get(position);

        viewHolder.tvName.setText(currentApp.getName());
        viewHolder.tvSummary.setText(currentApp.getSummary());
        viewHolder.tvArtist.setText(currentApp.getArtist());
        return convertView;
    }

    private class ViewHolder{

        final TextView tvName;
        final TextView tvArtist;
        final TextView tvSummary;
        ViewHolder(View v){
            this.tvName=(TextView)v.findViewById(R.id.tvName);
            this.tvArtist=(TextView)v.findViewById(R.id.tvArtist);
            this.tvSummary=(TextView)v.findViewById(R.id.tvSummary);

        }


    }

}
