package com.android.volley.toolbox;
/* loaded from: classes.dex */
public interface Authenticator {
    String getAuthToken();

    void invalidateAuthToken(String str);
}
