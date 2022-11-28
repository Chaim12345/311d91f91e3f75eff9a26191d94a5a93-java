package org.bouncycastle.tls;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.tls.crypto.TlsAgreement;
import org.bouncycastle.tls.crypto.TlsCryptoParameters;
import org.bouncycastle.tls.crypto.TlsDHConfig;
import org.bouncycastle.tls.crypto.TlsECConfig;
import org.bouncycastle.tls.crypto.TlsEncryptor;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class TlsPSKKeyExchange extends AbstractTlsKeyExchange {

    /* renamed from: c  reason: collision with root package name */
    protected TlsPSKIdentity f14851c;

    /* renamed from: d  reason: collision with root package name */
    protected TlsPSKIdentityManager f14852d;

    /* renamed from: e  reason: collision with root package name */
    protected TlsDHGroupVerifier f14853e;

    /* renamed from: f  reason: collision with root package name */
    protected byte[] f14854f;

    /* renamed from: g  reason: collision with root package name */
    protected byte[] f14855g;

    /* renamed from: h  reason: collision with root package name */
    protected TlsDHConfig f14856h;

    /* renamed from: i  reason: collision with root package name */
    protected TlsECConfig f14857i;

    /* renamed from: j  reason: collision with root package name */
    protected TlsAgreement f14858j;

    /* renamed from: k  reason: collision with root package name */
    protected TlsCredentialedDecryptor f14859k;

    /* renamed from: l  reason: collision with root package name */
    protected TlsEncryptor f14860l;

    /* renamed from: m  reason: collision with root package name */
    protected TlsSecret f14861m;

    public TlsPSKKeyExchange(int i2, TlsPSKIdentity tlsPSKIdentity, TlsDHGroupVerifier tlsDHGroupVerifier) {
        this(i2, tlsPSKIdentity, null, tlsDHGroupVerifier, null, null);
    }

    private TlsPSKKeyExchange(int i2, TlsPSKIdentity tlsPSKIdentity, TlsPSKIdentityManager tlsPSKIdentityManager, TlsDHGroupVerifier tlsDHGroupVerifier, TlsDHConfig tlsDHConfig, TlsECConfig tlsECConfig) {
        super(checkKeyExchange(i2));
        this.f14854f = null;
        this.f14855g = null;
        this.f14859k = null;
        this.f14851c = tlsPSKIdentity;
        this.f14852d = tlsPSKIdentityManager;
        this.f14853e = tlsDHGroupVerifier;
        this.f14856h = tlsDHConfig;
        this.f14857i = tlsECConfig;
    }

    public TlsPSKKeyExchange(int i2, TlsPSKIdentityManager tlsPSKIdentityManager, TlsDHConfig tlsDHConfig, TlsECConfig tlsECConfig) {
        this(i2, null, tlsPSKIdentityManager, null, tlsDHConfig, tlsECConfig);
    }

    private static int checkKeyExchange(int i2) {
        if (i2 != 24) {
            switch (i2) {
                case 13:
                case 14:
                case 15:
                    break;
                default:
                    throw new IllegalArgumentException("unsupported key exchange algorithm");
            }
        }
        return i2;
    }

    protected void a(OutputStream outputStream) {
        TlsUtils.writeOpaque16(this.f14858j.generateEphemeral(), outputStream);
    }

    protected void b(OutputStream outputStream) {
        TlsUtils.writeOpaque8(this.f14858j.generateEphemeral(), outputStream);
    }

    protected byte[] c(int i2) {
        TlsAgreement tlsAgreement;
        TlsSecret tlsSecret;
        int i3 = this.f14671a;
        if (i3 == 13) {
            return new byte[i2];
        }
        if ((i3 == 14 || i3 == 24) && (tlsAgreement = this.f14858j) != null) {
            return tlsAgreement.calculateSecret().extract();
        }
        if (i3 != 15 || (tlsSecret = this.f14861m) == null) {
            throw new TlsFatalAlert((short) 80);
        }
        return tlsSecret.extract();
    }

    protected void d(byte[] bArr) {
        this.f14858j.receivePeerValue(bArr);
    }

    protected void e(byte[] bArr) {
        TlsECCUtils.checkPointEncoding(this.f14857i.getNamedGroup(), bArr);
        this.f14858j.receivePeerValue(bArr);
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public void generateClientKeyExchange(OutputStream outputStream) {
        byte[] bArr = this.f14854f;
        if (bArr == null) {
            this.f14851c.skipIdentityHint();
        } else {
            this.f14851c.notifyIdentityHint(bArr);
        }
        byte[] pSKIdentity = this.f14851c.getPSKIdentity();
        if (pSKIdentity == null) {
            throw new TlsFatalAlert((short) 80);
        }
        byte[] psk = this.f14851c.getPSK();
        this.f14855g = psk;
        if (psk == null) {
            throw new TlsFatalAlert((short) 80);
        }
        TlsUtils.writeOpaque16(pSKIdentity, outputStream);
        this.f14672b.getSecurityParametersHandshake().x = Arrays.clone(pSKIdentity);
        int i2 = this.f14671a;
        if (i2 == 14) {
            a(outputStream);
        } else if (i2 == 24) {
            b(outputStream);
        } else if (i2 == 15) {
            this.f14861m = TlsUtils.generateEncryptedPreMasterSecret(this.f14672b, this.f14860l, outputStream);
        }
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public TlsSecret generatePreMasterSecret() {
        byte[] c2 = c(this.f14855g.length);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(c2.length + 4 + this.f14855g.length);
        TlsUtils.writeOpaque16(c2, byteArrayOutputStream);
        TlsUtils.writeOpaque16(this.f14855g, byteArrayOutputStream);
        Arrays.fill(this.f14855g, (byte) 0);
        this.f14855g = null;
        return this.f14672b.getCrypto().createSecret(byteArrayOutputStream.toByteArray());
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public byte[] generateServerKeyExchange() {
        byte[] hint = this.f14852d.getHint();
        this.f14854f = hint;
        if (hint != null || requiresServerKeyExchange()) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = this.f14854f;
            if (bArr == null) {
                TlsUtils.writeOpaque16(TlsUtils.EMPTY_BYTES, byteArrayOutputStream);
            } else {
                TlsUtils.writeOpaque16(bArr, byteArrayOutputStream);
            }
            int i2 = this.f14671a;
            if (i2 == 14) {
                TlsDHConfig tlsDHConfig = this.f14856h;
                if (tlsDHConfig == null) {
                    throw new TlsFatalAlert((short) 80);
                }
                TlsDHUtils.writeDHConfig(tlsDHConfig, byteArrayOutputStream);
                this.f14858j = this.f14672b.getCrypto().createDHDomain(this.f14856h).createDH();
                a(byteArrayOutputStream);
            } else if (i2 == 24) {
                TlsECConfig tlsECConfig = this.f14857i;
                if (tlsECConfig == null) {
                    throw new TlsFatalAlert((short) 80);
                }
                TlsECCUtils.writeECConfig(tlsECConfig, byteArrayOutputStream);
                this.f14858j = this.f14672b.getCrypto().createECDomain(this.f14857i).createECDH();
                b(byteArrayOutputStream);
            }
            return byteArrayOutputStream.toByteArray();
        }
        return null;
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public void processClientCredentials(TlsCredentials tlsCredentials) {
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public void processClientKeyExchange(InputStream inputStream) {
        byte[] readOpaque16 = TlsUtils.readOpaque16(inputStream);
        byte[] psk = this.f14852d.getPSK(readOpaque16);
        this.f14855g = psk;
        if (psk == null) {
            throw new TlsFatalAlert(AlertDescription.unknown_psk_identity);
        }
        this.f14672b.getSecurityParametersHandshake().x = readOpaque16;
        int i2 = this.f14671a;
        if (i2 == 14) {
            d(TlsUtils.readOpaque16(inputStream, 1));
        } else if (i2 == 24) {
            e(TlsUtils.readOpaque8(inputStream, 1));
        } else if (i2 == 15) {
            this.f14861m = this.f14859k.decrypt(new TlsCryptoParameters(this.f14672b), TlsUtils.m0(this.f14672b, inputStream));
        }
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public void processServerCertificate(Certificate certificate) {
        if (this.f14671a != 15) {
            throw new TlsFatalAlert((short) 10);
        }
        this.f14860l = certificate.getCertificateAt(0).createEncryptor(3);
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public void processServerCredentials(TlsCredentials tlsCredentials) {
        if (this.f14671a != 15) {
            throw new TlsFatalAlert((short) 80);
        }
        this.f14859k = TlsUtils.q0(tlsCredentials);
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public void processServerKeyExchange(InputStream inputStream) {
        this.f14854f = TlsUtils.readOpaque16(inputStream);
        int i2 = this.f14671a;
        if (i2 == 14) {
            this.f14856h = TlsDHUtils.receiveDHConfig(this.f14672b, this.f14853e, inputStream);
            byte[] readOpaque16 = TlsUtils.readOpaque16(inputStream, 1);
            this.f14858j = this.f14672b.getCrypto().createDHDomain(this.f14856h).createDH();
            d(readOpaque16);
        } else if (i2 == 24) {
            this.f14857i = TlsECCUtils.receiveECDHConfig(this.f14672b, inputStream);
            byte[] readOpaque8 = TlsUtils.readOpaque8(inputStream, 1);
            this.f14858j = this.f14672b.getCrypto().createECDomain(this.f14857i).createECDH();
            e(readOpaque8);
        }
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public boolean requiresServerKeyExchange() {
        int i2 = this.f14671a;
        return i2 == 14 || i2 == 24;
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public void skipServerCredentials() {
        if (this.f14671a == 15) {
            throw new TlsFatalAlert((short) 80);
        }
    }
}
