package org.bouncycastle.est.jcajce;

import javax.net.ssl.SSLSocketFactory;
/* loaded from: classes3.dex */
public interface SSLSocketFactoryCreator {
    SSLSocketFactory createFactory();

    boolean isTrusted();
}
