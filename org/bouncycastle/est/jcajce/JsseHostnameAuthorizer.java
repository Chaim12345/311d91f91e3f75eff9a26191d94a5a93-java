package org.bouncycastle.est.jcajce;

import javax.net.ssl.SSLSession;
/* loaded from: classes3.dex */
public interface JsseHostnameAuthorizer {
    boolean verified(String str, SSLSession sSLSession);
}
