package com.google.android.play.core.missingsplits;

import android.app.Application;
@Deprecated
/* loaded from: classes2.dex */
public class MissingSplitsDetectingApplication extends Application {
    private boolean onCreateCalled = false;

    @Override // android.app.Application
    public final void onCreate() {
        if (this.onCreateCalled) {
            throw new IllegalStateException("The onCreate method must be invoked at most once.");
        }
        this.onCreateCalled = true;
        if (MissingSplitsManagerFactory.create(this).disableAppIfMissingRequiredSplits()) {
            return;
        }
        super.onCreate();
        onCreateCustom();
    }

    @Deprecated
    public void onCreateCustom() {
    }
}
