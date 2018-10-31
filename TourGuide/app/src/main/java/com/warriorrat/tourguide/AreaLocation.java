package com.warriorrat.tourguide;

/**
 * Created by Jake on 6/28/2016.
 */
public class AreaLocation {

    // Name of the business
    private String mBusinessName;

    // Phone number for the business
    private String mPhoneNumber;

    // Address for the business
    private String mAddress;

    // Image for the business
    private int mImageResource;

    // Constant just in case a photo could not be provided.
    private static final int NO_IMAGE_PROVIDED = -1;

    public AreaLocation(String businessName, String phoneNumber, String address, int imageResource) {
        mBusinessName = businessName;
        mPhoneNumber = phoneNumber;
        mAddress = address;
        mImageResource = imageResource;
    }

    public String getBusinessName() {
        return mBusinessName;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public String getAddress() {
        return mAddress;
    }

    public int getimageResource() {
        return mImageResource;
    }

    public boolean hasImage() {
        return mImageResource != NO_IMAGE_PROVIDED;
    }
}
