package com.nipperlabs.bailiff.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpException;

import android.content.Context;
import android.net.ConnectivityManager;

public class HttpFetchr {
    /**
     * Obtain data via get request to url
     * 
     * @param urlSpec The url
     * @param context The current context, for detecting if network is present
     * @return data obtained from url
     * @throws IOException error obtaining data from url
     * @throws HttpException A http code besides 200 (ok) was received from url endpoint
     */
    public String getUrl(String urlSpec, Context context) throws IOException, HttpException {
        if (!isNetworkAvailable(context)) {
            throw new IOException("Network is not available on this device");
        }
        
        return new String(getUrlBytes(urlSpec));
    }
    
    private byte[] getUrlBytes(String urlSpec) throws IOException, HttpException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new HttpException("HTTP code " + connection.getResponseCode() + " from " +
                    urlSpec + " with message: " + connection.getResponseMessage());
            }
            
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            
            out.close();
            
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }
    
    @SuppressWarnings("deprecation")
    private static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getBackgroundDataSetting() && cm.getActiveNetworkInfo() != null;
    }
}
