package com.google.api.client.auth.oauth;

import com.google.api.client.util.Beta;
@Beta
/* loaded from: classes2.dex */
public interface OAuthSigner {
    String computeSignature(String str);

    String getSignatureMethod();
}
