package org.bouncycastle.tls;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class ServerSRPParams {

    /* renamed from: a  reason: collision with root package name */
    protected BigInteger f14819a;

    /* renamed from: b  reason: collision with root package name */
    protected BigInteger f14820b;

    /* renamed from: c  reason: collision with root package name */
    protected BigInteger f14821c;

    /* renamed from: d  reason: collision with root package name */
    protected byte[] f14822d;

    public ServerSRPParams(BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr, BigInteger bigInteger3) {
        this.f14819a = bigInteger;
        this.f14820b = bigInteger2;
        this.f14822d = Arrays.clone(bArr);
        this.f14821c = bigInteger3;
    }

    public static ServerSRPParams parse(InputStream inputStream) {
        return new ServerSRPParams(TlsSRPUtils.readSRPParameter(inputStream), TlsSRPUtils.readSRPParameter(inputStream), TlsUtils.readOpaque8(inputStream, 1), TlsSRPUtils.readSRPParameter(inputStream));
    }

    public void encode(OutputStream outputStream) {
        TlsSRPUtils.writeSRPParameter(this.f14819a, outputStream);
        TlsSRPUtils.writeSRPParameter(this.f14820b, outputStream);
        TlsUtils.writeOpaque8(this.f14822d, outputStream);
        TlsSRPUtils.writeSRPParameter(this.f14821c, outputStream);
    }

    public BigInteger getB() {
        return this.f14821c;
    }

    public BigInteger getG() {
        return this.f14820b;
    }

    public BigInteger getN() {
        return this.f14819a;
    }

    public byte[] getS() {
        return this.f14822d;
    }
}
