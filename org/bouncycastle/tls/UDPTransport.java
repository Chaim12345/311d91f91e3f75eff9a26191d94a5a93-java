package org.bouncycastle.tls;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
/* loaded from: classes4.dex */
public class UDPTransport implements DatagramTransport {

    /* renamed from: a  reason: collision with root package name */
    protected final DatagramSocket f14905a;

    /* renamed from: b  reason: collision with root package name */
    protected final int f14906b;

    /* renamed from: c  reason: collision with root package name */
    protected final int f14907c;

    public UDPTransport(DatagramSocket datagramSocket, int i2) {
        if (!datagramSocket.isBound() || !datagramSocket.isConnected()) {
            throw new IllegalArgumentException("'socket' must be bound and connected");
        }
        this.f14905a = datagramSocket;
        this.f14906b = (i2 - 20) - 8;
        this.f14907c = (i2 - 84) - 8;
    }

    @Override // org.bouncycastle.tls.TlsCloseable
    public void close() {
        this.f14905a.close();
    }

    @Override // org.bouncycastle.tls.DatagramReceiver
    public int getReceiveLimit() {
        return this.f14906b;
    }

    @Override // org.bouncycastle.tls.DatagramSender
    public int getSendLimit() {
        return this.f14907c;
    }

    @Override // org.bouncycastle.tls.DatagramReceiver
    public int receive(byte[] bArr, int i2, int i3, int i4) {
        this.f14905a.setSoTimeout(i4);
        DatagramPacket datagramPacket = new DatagramPacket(bArr, i2, i3);
        this.f14905a.receive(datagramPacket);
        return datagramPacket.getLength();
    }

    @Override // org.bouncycastle.tls.DatagramSender
    public void send(byte[] bArr, int i2, int i3) {
        if (i3 > getSendLimit()) {
            throw new TlsFatalAlert((short) 80);
        }
        this.f14905a.send(new DatagramPacket(bArr, i2, i3));
    }
}
