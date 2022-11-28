package org.bouncycastle.cert.crmf;
/* loaded from: classes3.dex */
public interface EncryptedValuePadder {
    byte[] getPaddedData(byte[] bArr);

    byte[] getUnpaddedData(byte[] bArr);
}
