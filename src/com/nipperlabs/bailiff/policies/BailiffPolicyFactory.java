package com.nipperlabs.bailiff.policies;

/**
 * It wouldn't be java without a factory.
 *
 */
public class BailiffPolicyFactory {

    public static BailiffPolicy create(String key) {
        BailiffPolicy policy = null;
        
        if (key.equals("ENCRYPTION_POLICY")) {
            policy = new EncryptionPolicy();
        } else if (key.equals("REQUIRE_DEVICE_APPROVAL_POLICY")) {
            policy = new RequireDeviceApprovalPolicy();
        }
        
        return policy;
    }
}
