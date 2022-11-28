package org.bouncycastle.tls;

import java.math.BigInteger;
import org.bouncycastle.tls.crypto.SRP6Group;
import org.bouncycastle.tls.crypto.TlsCrypto;
import org.bouncycastle.tls.crypto.TlsHMAC;
import org.bouncycastle.tls.crypto.TlsMAC;
import org.bouncycastle.tls.crypto.TlsSRP6VerifierGenerator;
import org.bouncycastle.tls.crypto.TlsSRPConfig;
import org.bouncycastle.util.Strings;
/* loaded from: classes4.dex */
public class SimulatedTlsSRPIdentityManager implements TlsSRPIdentityManager {
    private static final byte[] PREFIX_PASSWORD = Strings.toByteArray("password");
    private static final byte[] PREFIX_SALT = Strings.toByteArray("salt");

    /* renamed from: a  reason: collision with root package name */
    protected SRP6Group f14825a;

    /* renamed from: b  reason: collision with root package name */
    protected TlsSRP6VerifierGenerator f14826b;

    /* renamed from: c  reason: collision with root package name */
    protected TlsMAC f14827c;

    public SimulatedTlsSRPIdentityManager(SRP6Group sRP6Group, TlsSRP6VerifierGenerator tlsSRP6VerifierGenerator, TlsMAC tlsMAC) {
        this.f14825a = sRP6Group;
        this.f14826b = tlsSRP6VerifierGenerator;
        this.f14827c = tlsMAC;
    }

    public static SimulatedTlsSRPIdentityManager getRFC5054Default(TlsCrypto tlsCrypto, SRP6Group sRP6Group, byte[] bArr) {
        TlsHMAC createHMAC = tlsCrypto.createHMAC(2);
        createHMAC.setKey(bArr, 0, bArr.length);
        TlsSRPConfig tlsSRPConfig = new TlsSRPConfig();
        tlsSRPConfig.setExplicitNG(new BigInteger[]{sRP6Group.getN(), sRP6Group.getG()});
        return new SimulatedTlsSRPIdentityManager(sRP6Group, tlsCrypto.createSRP6VerifierGenerator(tlsSRPConfig), createHMAC);
    }

    @Override // org.bouncycastle.tls.TlsSRPIdentityManager
    public TlsSRPLoginParameters getLoginParameters(byte[] bArr) {
        TlsMAC tlsMAC = this.f14827c;
        byte[] bArr2 = PREFIX_SALT;
        tlsMAC.update(bArr2, 0, bArr2.length);
        this.f14827c.update(bArr, 0, bArr.length);
        byte[] calculateMAC = this.f14827c.calculateMAC();
        TlsMAC tlsMAC2 = this.f14827c;
        byte[] bArr3 = PREFIX_PASSWORD;
        tlsMAC2.update(bArr3, 0, bArr3.length);
        this.f14827c.update(bArr, 0, bArr.length);
        BigInteger generateVerifier = this.f14826b.generateVerifier(calculateMAC, bArr, this.f14827c.calculateMAC());
        TlsSRPConfig tlsSRPConfig = new TlsSRPConfig();
        tlsSRPConfig.setExplicitNG(new BigInteger[]{this.f14825a.getN(), this.f14825a.getG()});
        return new TlsSRPLoginParameters(bArr, tlsSRPConfig, generateVerifier, calculateMAC);
    }
}
