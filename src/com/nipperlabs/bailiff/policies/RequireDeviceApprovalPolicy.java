package com.nipperlabs.bailiff.policies;

import android.content.Context;

/**
 * Require device approval via api before passing.
 *
 */
public class RequireDeviceApprovalPolicy implements BailiffPolicy {

    @Override
    public boolean passes(Context context) {
        // TODO implement
        return true;
    }

}
