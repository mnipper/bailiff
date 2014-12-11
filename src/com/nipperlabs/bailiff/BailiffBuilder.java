package com.nipperlabs.bailiff;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.nipperlabs.bailiff.policies.BailiffPolicy;

public class BailiffBuilder {
    private ArrayList<BailiffPolicy> mPolicies;
    private BailiffCallback mCallback;
    private Context mContext;

    public BailiffBuilder(Context context) {
        mPolicies = new ArrayList<BailiffPolicy>();
        mContext = context;
    }

    public BailiffBuilder addPolicy(BailiffPolicy policy) {
        mPolicies.add(policy);
        return this;
    }
    
    public BailiffBuilder addPolicies(List<BailiffPolicy> policies) {
        for (BailiffPolicy policy : mPolicies) {
            mPolicies.add(policy);
        }
        
        return this;
    }

    public BailiffBuilder setCallbacks(BailiffCallback callback) {
        mCallback = callback;
        return this;
    }

    public boolean enforcePolicy() {
        for (BailiffPolicy policy : mPolicies) {
            if (!policy.passes(mContext)) {
                if (mCallback != null) {
                    mCallback.onFail(mContext);
                }
                
                return false;
            }
        }

        if (mCallback != null) {
            mCallback.onPass(mContext);
        }
        
        return true;
    }
}
