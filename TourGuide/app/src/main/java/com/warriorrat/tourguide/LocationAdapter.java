package com.warriorrat.tourguide;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jake on 6/28/2016.
 */
public class LocationAdapter extends ArrayAdapter<AreaLocation> {

    // Resource ID for the background color for this list of words
    private int mColorResourceId;

    /**
     * @param context is the current context (i.e. Activity) that the adapter is being created in
     * @param name is the list of {@link AreaLocation}s to be displayed
     */

    public LocationAdapter(Activity context, ArrayList<AreaLocation> name, int colorResourceId) {
        super(context, 0, name);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_location, parent, false);
        }

        // Get the AreaLocation object located at this position in the list
        AreaLocation currentLocation = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID textOne
        TextView businessName = (TextView) listItemView.findViewById(R.id.textOne);

        // Get the business name from the AreaLocation object and set this text on the business name text view
        businessName.setText(currentLocation.getBusinessName());

        // Find the TextView in the list_item.xml layout with the ID textTwo
        TextView businessNumber = (TextView) listItemView.findViewById(R.id.textTwo);

        // Get the business number from the AreaLocation object and set this text on the phone number text view
        businessNumber.setText(currentLocation.getPhoneNumber());

        // Find the TextView in the list_item.xml layout with the ID textThree
        TextView businessAddress = (TextView) listItemView.findViewById(R.id.textThree);

        // Get the business address from the AreaLocation object and set this text on the business address text view
        businessAddress.setText(currentLocation.getAddress());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView imageUsed = (ImageView) listItemView.findViewById(R.id.image);

        if (currentLocation.hasImage()) {
            //Get the Image Resource ID from the current AreaLocation object and set the image to imageUsed
            imageUsed.setImageResource(currentLocation.getimageResource());

            // Make sure the view is visible
            imageUsed.setVisibility(View.VISIBLE);
        } else {
            // Otherwise hide the ImageView (set visibility to GONE)
            imageUsed.setVisibility(View.GONE);
        }

        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.list_item);

        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);

        // Set the background color of the list item View
        textContainer.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in the ListView
        return listItemView;
    }
}