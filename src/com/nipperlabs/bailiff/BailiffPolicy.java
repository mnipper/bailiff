package com.nipperlabs.bailiff;

import android.content.Context;

public interface BailiffPolicy {
    public boolean passes(Context context);
}
