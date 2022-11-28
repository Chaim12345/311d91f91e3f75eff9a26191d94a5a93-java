package org.bouncycastle.tls;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
/* loaded from: classes4.dex */
public final class ServerName {
    private final byte[] nameData;
    private final short nameType;

    public ServerName(short s2, byte[] bArr) {
        if (!TlsUtils.isValidUint8(s2)) {
            throw new IllegalArgumentException("'nameType' must be from 0 to 255");
        }
        Objects.requireNonNull(bArr, "'nameData' cannot be null");
        if (bArr.length < 1 || !TlsUtils.isValidUint16(bArr.length)) {
            throw new IllegalArgumentException("'nameData' must have length from 1 to 65535");
        }
        this.nameType = s2;
        this.nameData = bArr;
    }

    public static ServerName parse(InputStream inputStream) {
        return new ServerName(TlsUtils.readUint8(inputStream), TlsUtils.readOpaque16(inputStream, 1));
    }

    public void encode(OutputStream outputStream) {
        TlsUtils.writeUint8(this.nameType, outputStream);
        TlsUtils.writeOpaque16(this.nameData, outputStream);
    }

    public byte[] getNameData() {
        return this.nameData;
    }

    public short getNameType() {
        return this.nameType;
    }
}
