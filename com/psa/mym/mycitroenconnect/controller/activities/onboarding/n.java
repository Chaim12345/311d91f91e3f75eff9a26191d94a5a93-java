package com.psa.mym.mycitroenconnect.controller.activities.onboarding;

import com.google.android.gms.tasks.OnFailureListener;
/* loaded from: classes2.dex */
public final /* synthetic */ class n implements OnFailureListener {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ n f10431a = new n();

    private /* synthetic */ n() {
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception exc) {
        SplashActivity.m108sendSafetynetRequest$lambda6(exc);
    }
}
