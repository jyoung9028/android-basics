package com.warriorrat.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jake on 6/29/2016.
 */
public class NewsAdapter extends ArrayAdapter<NewsStories> {

    public NewsAdapter(Context context, ArrayList<NewsStories> newsStories) {
        super(context, 0, newsStories);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the NewsStories object located at this position in the list
        NewsStories currentNewsStories = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID section_name
        TextView authorTextView = (TextView) listItemView.findViewById(R.id.section_name);

        // Set the text to be the section_name
        authorTextView.setText(currentNewsStories.getSectionName());

        // Find the TextVie in the list_item.xml layout with the ID title
        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title);

        // Set the text to be the title
        titleTextView.setText(currentNewsStories.getWebTitle());

        return listItemView;

    }
}
