package com.nipperlabs.bailiff.policies;

import android.content.Context;

public interface BailiffPolicy {
    public boolean passes(Context context);
}
