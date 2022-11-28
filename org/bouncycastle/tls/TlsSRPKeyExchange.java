package org.bouncycastle.tls;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import org.bouncycastle.tls.crypto.TlsCertificate;
import org.bouncycastle.tls.crypto.TlsSRP6Client;
import org.bouncycastle.tls.crypto.TlsSRP6Server;
import org.bouncycastle.tls.crypto.TlsSRPConfig;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.io.TeeInputStream;
/* loaded from: classes4.dex */
public class TlsSRPKeyExchange extends AbstractTlsKeyExchange {

    /* renamed from: c  reason: collision with root package name */
    protected TlsSRPIdentity f14885c;

    /* renamed from: d  reason: collision with root package name */
    protected TlsSRPConfigVerifier f14886d;

    /* renamed from: e  reason: collision with root package name */
    protected TlsCertificate f14887e;

    /* renamed from: f  reason: collision with root package name */
    protected byte[] f14888f;

    /* renamed from: g  reason: collision with root package name */
    protected TlsSRP6Client f14889g;

    /* renamed from: h  reason: collision with root package name */
    protected TlsSRPLoginParameters f14890h;

    /* renamed from: i  reason: collision with root package name */
    protected TlsCredentialedSigner f14891i;

    /* renamed from: j  reason: collision with root package name */
    protected TlsSRP6Server f14892j;

    /* renamed from: k  reason: collision with root package name */
    protected BigInteger f14893k;

    public TlsSRPKeyExchange(int i2, TlsSRPIdentity tlsSRPIdentity, TlsSRPConfigVerifier tlsSRPConfigVerifier) {
        super(checkKeyExchange(i2));
        this.f14887e = null;
        this.f14888f = null;
        this.f14889g = null;
        this.f14891i = null;
        this.f14892j = null;
        this.f14893k = null;
        this.f14885c = tlsSRPIdentity;
        this.f14886d = tlsSRPConfigVerifier;
    }

    public TlsSRPKeyExchange(int i2, TlsSRPLoginParameters tlsSRPLoginParameters) {
        super(checkKeyExchange(i2));
        this.f14887e = null;
        this.f14888f = null;
        this.f14889g = null;
        this.f14891i = null;
        this.f14892j = null;
        this.f14893k = null;
        this.f14890h = tlsSRPLoginParameters;
    }

    protected static BigInteger a(BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger mod = bigInteger2.mod(bigInteger);
        if (mod.equals(BigInteger.ZERO)) {
            throw new TlsFatalAlert((short) 47);
        }
        return mod;
    }

    private static int checkKeyExchange(int i2) {
        switch (i2) {
            case 21:
            case 22:
            case 23:
                return i2;
            default:
                throw new IllegalArgumentException("unsupported key exchange algorithm");
        }
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public void generateClientKeyExchange(OutputStream outputStream) {
        byte[] sRPIdentity = this.f14885c.getSRPIdentity();
        TlsSRPUtils.writeSRPParameter(this.f14889g.generateClientCredentials(this.f14888f, sRPIdentity, this.f14885c.getSRPPassword()), outputStream);
        this.f14672b.getSecurityParametersHandshake().y = Arrays.clone(sRPIdentity);
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public TlsSecret generatePreMasterSecret() {
        TlsSRP6Server tlsSRP6Server = this.f14892j;
        return this.f14672b.getCrypto().createSecret(BigIntegers.asUnsignedByteArray(tlsSRP6Server != null ? tlsSRP6Server.calculateSecret(this.f14893k) : this.f14889g.calculateSecret(this.f14893k)));
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public byte[] generateServerKeyExchange() {
        TlsSRPConfig config = this.f14890h.getConfig();
        TlsSRP6Server createSRP6Server = this.f14672b.getCrypto().createSRP6Server(config, this.f14890h.getVerifier());
        this.f14892j = createSRP6Server;
        BigInteger generateServerCredentials = createSRP6Server.generateServerCredentials();
        BigInteger[] explicitNG = config.getExplicitNG();
        ServerSRPParams serverSRPParams = new ServerSRPParams(explicitNG[0], explicitNG[1], this.f14890h.getSalt(), generateServerCredentials);
        DigestInputBuffer digestInputBuffer = new DigestInputBuffer();
        serverSRPParams.encode(digestInputBuffer);
        TlsCredentialedSigner tlsCredentialedSigner = this.f14891i;
        if (tlsCredentialedSigner != null) {
            TlsUtils.F(this.f14672b, tlsCredentialedSigner, null, digestInputBuffer);
        }
        return digestInputBuffer.toByteArray();
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public void processClientCredentials(TlsCredentials tlsCredentials) {
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public void processClientKeyExchange(InputStream inputStream) {
        this.f14893k = a(this.f14890h.getConfig().getExplicitNG()[0], TlsSRPUtils.readSRPParameter(inputStream));
        this.f14672b.getSecurityParametersHandshake().y = Arrays.clone(this.f14890h.getIdentity());
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public void processServerCertificate(Certificate certificate) {
        if (this.f14671a == 21) {
            throw new TlsFatalAlert((short) 80);
        }
        this.f14887e = certificate.getCertificateAt(0);
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public void processServerCredentials(TlsCredentials tlsCredentials) {
        if (this.f14671a == 21) {
            throw new TlsFatalAlert((short) 80);
        }
        this.f14891i = TlsUtils.r0(tlsCredentials);
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public void processServerKeyExchange(InputStream inputStream) {
        InputStream inputStream2;
        DigestInputBuffer digestInputBuffer;
        if (this.f14671a != 21) {
            digestInputBuffer = new DigestInputBuffer();
            inputStream2 = new TeeInputStream(inputStream, digestInputBuffer);
        } else {
            inputStream2 = inputStream;
            digestInputBuffer = null;
        }
        ServerSRPParams parse = ServerSRPParams.parse(inputStream2);
        if (digestInputBuffer != null) {
            TlsUtils.L0(this.f14672b, inputStream, this.f14887e, null, digestInputBuffer);
        }
        TlsSRPConfig tlsSRPConfig = new TlsSRPConfig();
        tlsSRPConfig.setExplicitNG(new BigInteger[]{parse.getN(), parse.getG()});
        if (!this.f14886d.accept(tlsSRPConfig)) {
            throw new TlsFatalAlert((short) 71);
        }
        this.f14888f = parse.getS();
        this.f14893k = a(parse.getN(), parse.getB());
        this.f14889g = this.f14672b.getCrypto().createSRP6Client(tlsSRPConfig);
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public boolean requiresServerKeyExchange() {
        return true;
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public void skipServerCredentials() {
        if (this.f14671a != 21) {
            throw new TlsFatalAlert((short) 80);
        }
    }
}
