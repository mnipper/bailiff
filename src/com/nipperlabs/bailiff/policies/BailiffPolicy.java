package com.nipperlabs.bailiff.policies;

import android.content.Context;

public interface BailiffPolicy {
    /**
     * A policy that returns true if it should be allowed to pass.
     * 
     * @param context The current context
     * @return True if this policy passes, false if this policy fails
     */
    public boolean passes(Context context);
}
