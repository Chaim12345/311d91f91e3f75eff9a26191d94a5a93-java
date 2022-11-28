package org.bouncycastle.tls;
/* loaded from: classes4.dex */
public interface DatagramReceiver {
    int getReceiveLimit();

    int receive(byte[] bArr, int i2, int i3, int i4);
}
