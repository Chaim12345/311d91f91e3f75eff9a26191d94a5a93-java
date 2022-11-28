package org.bouncycastle.tls;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
/* loaded from: classes4.dex */
public class KeyShareEntry {

    /* renamed from: a  reason: collision with root package name */
    protected final int f14763a;

    /* renamed from: b  reason: collision with root package name */
    protected final byte[] f14764b;

    public KeyShareEntry(int i2, byte[] bArr) {
        if (!TlsUtils.isValidUint16(i2)) {
            throw new IllegalArgumentException("'namedGroup' should be a uint16");
        }
        Objects.requireNonNull(bArr, "'keyExchange' cannot be null");
        if (!checkKeyExchangeLength(bArr.length)) {
            throw new IllegalArgumentException("'keyExchange' must have length from 1 to (2^16 - 1)");
        }
        this.f14763a = i2;
        this.f14764b = bArr;
    }

    private static boolean checkKeyExchangeLength(int i2) {
        return i2 > 0 && i2 < 65536;
    }

    public static KeyShareEntry parse(InputStream inputStream) {
        return new KeyShareEntry(TlsUtils.readUint16(inputStream), TlsUtils.readOpaque16(inputStream, 1));
    }

    public void encode(OutputStream outputStream) {
        TlsUtils.writeUint16(getNamedGroup(), outputStream);
        TlsUtils.writeOpaque16(getKeyExchange(), outputStream);
    }

    public byte[] getKeyExchange() {
        return this.f14764b;
    }

    public int getNamedGroup() {
        return this.f14763a;
    }
}
