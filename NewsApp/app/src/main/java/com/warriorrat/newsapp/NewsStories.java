package com.warriorrat.newsapp;

/**
 * Created by Jake on 6/29/2016.
 */
public class NewsStories {

    private String mWebTitle;
    private String mWebUrl;
    private String mSectionName;

    public NewsStories(String webTitle, String webUrl, String sectionName) {
        mWebTitle = webTitle;
        mWebUrl = webUrl;
        mSectionName = sectionName;
    }

    public String getWebTitle() {
        return mWebTitle;
    }

    public String getWebUrl() {
        return mWebUrl;
    }

    public String getSectionName() {
        return mSectionName;
    }
}
