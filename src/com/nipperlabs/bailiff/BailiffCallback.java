package com.nipperlabs.bailiff;
import android.content.Context;

public interface BailiffCallback {
    public void onPass(Context context);
    public void onFail(Context context);
}
