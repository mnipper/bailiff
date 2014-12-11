package com.nipperlabs.bailiff;

public class Bailiff {
    private static String mEndpointUrl;
    
    public Bailiff(String url) {
        mEndpointUrl = url;
    }
    
    public static String getEndpointUrl() {
        return mEndpointUrl;
    }
}
