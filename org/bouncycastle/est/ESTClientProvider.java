package org.bouncycastle.est;
/* loaded from: classes3.dex */
public interface ESTClientProvider {
    boolean isTrusted();

    ESTClient makeClient();
}
