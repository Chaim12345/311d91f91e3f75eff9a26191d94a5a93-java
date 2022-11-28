package org.bouncycastle.pqc.crypto.lms;
/* loaded from: classes4.dex */
public interface LMSContextBasedVerifier {
    LMSContext generateLMSContext(byte[] bArr);

    boolean verify(LMSContext lMSContext);
}
