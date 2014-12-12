package com.nipperlabs.bailiff.policies;

import android.annotation.SuppressLint;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.os.Build;

/**
 * Ensure the current device has full-disk encryption enabled.
 *
 */
public class EncryptionPolicy implements BailiffPolicy {

    @SuppressLint("NewApi")
    @Override
    public boolean passes(Context context) {
        if (Build.VERSION.SDK_INT < 11) {
            // Full Disk Encryption is not available in Android prior to Honeycomb
            return false;
        }
        
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context
                .getSystemService(Context.DEVICE_POLICY_SERVICE);
        
        return devicePolicyManager.getStorageEncryptionStatus() == DevicePolicyManager.ENCRYPTION_STATUS_ACTIVE;
    }

}
