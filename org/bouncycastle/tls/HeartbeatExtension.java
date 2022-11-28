package org.bouncycastle.tls;

import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes4.dex */
public class HeartbeatExtension {

    /* renamed from: a  reason: collision with root package name */
    protected short f14759a;

    public HeartbeatExtension(short s2) {
        if (!HeartbeatMode.isValid(s2)) {
            throw new IllegalArgumentException("'mode' is not a valid HeartbeatMode value");
        }
        this.f14759a = s2;
    }

    public static HeartbeatExtension parse(InputStream inputStream) {
        short readUint8 = TlsUtils.readUint8(inputStream);
        if (HeartbeatMode.isValid(readUint8)) {
            return new HeartbeatExtension(readUint8);
        }
        throw new TlsFatalAlert((short) 47);
    }

    public void encode(OutputStream outputStream) {
        TlsUtils.writeUint8(this.f14759a, outputStream);
    }

    public short getMode() {
        return this.f14759a;
    }
}
