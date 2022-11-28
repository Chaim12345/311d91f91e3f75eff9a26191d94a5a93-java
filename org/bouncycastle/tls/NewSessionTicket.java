package org.bouncycastle.tls;

import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes4.dex */
public class NewSessionTicket {

    /* renamed from: a  reason: collision with root package name */
    protected long f14765a;

    /* renamed from: b  reason: collision with root package name */
    protected byte[] f14766b;

    public NewSessionTicket(long j2, byte[] bArr) {
        this.f14765a = j2;
        this.f14766b = bArr;
    }

    public static NewSessionTicket parse(InputStream inputStream) {
        return new NewSessionTicket(TlsUtils.readUint32(inputStream), TlsUtils.readOpaque16(inputStream));
    }

    public void encode(OutputStream outputStream) {
        TlsUtils.writeUint32(this.f14765a, outputStream);
        TlsUtils.writeOpaque16(this.f14766b, outputStream);
    }

    public byte[] getTicket() {
        return this.f14766b;
    }

    public long getTicketLifetimeHint() {
        return this.f14765a;
    }
}
