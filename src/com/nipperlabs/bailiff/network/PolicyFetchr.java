package com.nipperlabs.bailiff.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.nipperlabs.bailiff.policies.BailiffPolicy;
import com.nipperlabs.bailiff.policies.BailiffPolicyFactory;

/**
 * Obtain all app policies via api.
 *
 */
public class PolicyFetchr {
    private static final String POLICY_NAME_KEY = "name";
    
    private String mEndpointUrl;
    private String mApiKey; 
    private String mAppId;
    
    private Map<String, BailiffPolicy> mPolicies;
    
    public PolicyFetchr(String endpointUrl, String apiKey, String appId) {
        mEndpointUrl = endpointUrl;
        mApiKey = apiKey;
        mAppId = appId;        
        mPolicies = new HashMap<String, BailiffPolicy>();
    }
    
    /**
     * Fetch policy info from endpoint url using app id and api key.
     * 
     * Loads the mPolicies map with policies on the server.
     * 
     * @param context
     * @throws IOException
     * @throws HttpException
     * @throws JSONException
     */
    public void fetch(Context context) throws IOException, HttpException, JSONException {
        HttpFetchr httpFetchr = new HttpFetchr();        
        JSONArray policiesJSON = new JSONArray(httpFetchr.getUrl(getPolicyUrl(), context));
        
        for (int i = 0; i < policiesJSON.length(); i++) {
            JSONObject policyJSON = policiesJSON.getJSONObject(i);
            
            String policyName = policyJSON.getString(POLICY_NAME_KEY);
            BailiffPolicy policy = BailiffPolicyFactory.create(policyName);
            
            mPolicies.put(policyName, policy);
        }
    }
    
    /**
     * Get a copy of the current policies.
     * 
     * @return a list of all current policies loaded from server
     */
    public List<BailiffPolicy> getPolicies() {
        List<BailiffPolicy> policies = new ArrayList<BailiffPolicy>();
        
        for (Entry<String, BailiffPolicy> policy : mPolicies.entrySet()) {
            policies.add(policy.getValue());
        }
        
        return policies;
    }
    
    private String getPolicyUrl() {
        return mEndpointUrl + "/api/v1/app/" + mAppId + "/policies?key=" + mApiKey; 
    }
}
