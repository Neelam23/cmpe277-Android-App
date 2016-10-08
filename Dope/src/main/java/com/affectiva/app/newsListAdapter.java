package com.affectiva.app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by NehaP on 10/4/2016.
 */
public class newsListAdapter extends BaseAdapter {

    private Activity context;
    private LayoutInflater inflater;
    private List<News> newsList;

    ImageLoader imageLoader = HttpHandler.getInstance().getImageLoader();


    public newsListAdapter(Activity context, List<News> newsList){
        this.context = context;
        this.newsList = newsList;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int location) {
        return newsList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null)
            view = inflater.inflate(R.layout.layout, null);

        if (imageLoader == null)
            imageLoader = HttpHandler.getInstance().getImageLoader();

        NetworkImageView thumbNail = (NetworkImageView) view
                .findViewById(R.id.img);

        TextView txtTitle = (TextView) view.findViewById(R.id.title);
        TextView exceptTxt = (TextView) view.findViewById(R.id.desc);

        News dopeNews = newsList.get(position);

        thumbNail.setImageUrl(dopeNews.getImage(), imageLoader);

        txtTitle.setText(dopeNews.getTitle());

        exceptTxt.setText(dopeNews.getExcerpt());

        return view;

    };
}
