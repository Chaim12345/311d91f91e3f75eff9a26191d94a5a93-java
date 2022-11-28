package org.bouncycastle.crypto;
/* loaded from: classes3.dex */
public interface SkippingCipher {
    long getPosition();

    long seekTo(long j2);

    long skip(long j2);
}
