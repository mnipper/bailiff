package com.nipperlabs.bailiff;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

import com.nipperlabs.bailiff.network.GetPolicyTask;

public class Bailiff {
    private static final String TAG = "Bailiff";
    
    private static final String ENDPOINT_URL_METADATA_KEY = "com.nipperlabs.bailiff.EndpointUrl";
    private static final String API_KEY_METADATA_KEY = "com.nipperlabs.bailiff.ApiKey";
    private static final String APP_ID_METADATA_KEY = "com.nipperlabs.bailiff.AppId";
    
    private static String mEndpointUrl;
    private static String mApiKey;
    private static String mAppId;
    private static BailiffBuilder mBailiffBuilder;
    
    public static void start(Context context) {
        mBailiffBuilder = new BailiffBuilder(context);
        mEndpointUrl = getMetadataValue(context, ENDPOINT_URL_METADATA_KEY);
        mApiKey = getMetadataValue(context, API_KEY_METADATA_KEY);
        mAppId = getMetadataValue(context, APP_ID_METADATA_KEY);

        new GetPolicyTask(context).execute();
    }
    
    public static boolean enforce() {
        return mBailiffBuilder.enforcePolicy();
    }
       
    private static String getMetadataValue(Context context, String key) {
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            return (String) appInfo.metaData.get(key);
        } catch (NameNotFoundException e) {
            Log.e(TAG, "Could not find value for key " + key + " in AndroidManifest meta-data!");
            return null;
        }
    }
    
    public static String getEndpointUrl() {
        return mEndpointUrl;
    }
    
    public static String getApiKey() {
        return mApiKey;
    }
    
    public static String getAppId() {
        return mAppId;
    }
    
    public static BailiffBuilder getBuilder() {
        return mBailiffBuilder;
    }
}
