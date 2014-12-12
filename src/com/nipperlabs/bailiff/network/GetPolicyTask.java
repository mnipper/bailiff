package com.nipperlabs.bailiff.network;

import java.io.IOException;

import org.apache.http.HttpException;
import org.json.JSONException;

import android.content.Context;
import android.os.AsyncTask;

import com.nipperlabs.bailiff.Bailiff;

public class GetPolicyTask extends AsyncTask<Void, Void, Void> {
    private Context mContext;
    
    public GetPolicyTask(Context context) {
        mContext = context;
    }
    
    protected Void doInBackground(Void... params) {
        PolicyFetchr policyFetchr = new PolicyFetchr(Bailiff.getEndpointUrl(), Bailiff.getApiKey(), Bailiff.getAppId());
        try {
            policyFetchr.fetch(mContext);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (HttpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Bailiff.getBuilder().addPolicies(policyFetchr.getPolicies());
        
        return null;
    }
}
