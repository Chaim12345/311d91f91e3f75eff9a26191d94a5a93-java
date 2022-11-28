package org.bouncycastle.tls;

import java.util.Vector;
import org.bouncycastle.tls.crypto.TlsSecret;
/* loaded from: classes4.dex */
public class SecurityParameters {

    /* renamed from: a  reason: collision with root package name */
    int f14798a = -1;

    /* renamed from: b  reason: collision with root package name */
    boolean f14799b = false;

    /* renamed from: c  reason: collision with root package name */
    boolean f14800c = false;

    /* renamed from: d  reason: collision with root package name */
    int f14801d = 0;

    /* renamed from: e  reason: collision with root package name */
    short f14802e = -1;

    /* renamed from: f  reason: collision with root package name */
    int f14803f = -1;

    /* renamed from: g  reason: collision with root package name */
    int f14804g = -1;

    /* renamed from: h  reason: collision with root package name */
    short f14805h = -1;

    /* renamed from: i  reason: collision with root package name */
    int f14806i = -1;

    /* renamed from: j  reason: collision with root package name */
    int f14807j = -1;

    /* renamed from: k  reason: collision with root package name */
    TlsSecret f14808k = null;

    /* renamed from: l  reason: collision with root package name */
    TlsSecret f14809l = null;

    /* renamed from: m  reason: collision with root package name */
    TlsSecret f14810m = null;

    /* renamed from: n  reason: collision with root package name */
    TlsSecret f14811n = null;

    /* renamed from: o  reason: collision with root package name */
    TlsSecret f14812o = null;

    /* renamed from: p  reason: collision with root package name */
    TlsSecret f14813p = null;

    /* renamed from: q  reason: collision with root package name */
    TlsSecret f14814q = null;

    /* renamed from: r  reason: collision with root package name */
    TlsSecret f14815r = null;

    /* renamed from: s  reason: collision with root package name */
    TlsSecret f14816s = null;

    /* renamed from: t  reason: collision with root package name */
    byte[] f14817t = null;
    byte[] u = null;
    byte[] v = null;
    byte[] w = null;
    byte[] x = null;
    byte[] y = null;
    byte[] z = null;
    byte[] A = null;
    boolean B = false;
    boolean C = false;
    boolean D = false;
    boolean E = false;
    ProtocolName F = null;
    boolean G = false;
    short[] H = null;
    Vector I = null;
    Vector J = null;
    Vector K = null;
    int[] L = null;
    Vector M = null;
    Vector N = null;
    int[] O = null;
    int P = -1;
    Certificate Q = null;
    Certificate R = null;
    ProtocolVersion S = null;
    int T = 0;
    byte[] U = null;
    byte[] V = null;

    private static TlsSecret clearSecret(TlsSecret tlsSecret) {
        if (tlsSecret != null) {
            tlsSecret.destroy();
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        this.v = null;
        this.w = null;
        this.H = null;
        this.I = null;
        this.J = null;
        this.K = null;
        this.L = null;
        this.M = null;
        this.N = null;
        this.O = null;
        this.T = 0;
        this.f14808k = clearSecret(this.f14808k);
        this.f14809l = clearSecret(this.f14809l);
        this.f14810m = clearSecret(this.f14810m);
        this.f14811n = clearSecret(this.f14811n);
        this.f14812o = clearSecret(this.f14812o);
        this.f14813p = clearSecret(this.f14813p);
        this.f14814q = clearSecret(this.f14814q);
    }

    public ProtocolName getApplicationProtocol() {
        return this.F;
    }

    public TlsSecret getBaseKeyClient() {
        return this.f14808k;
    }

    public TlsSecret getBaseKeyServer() {
        return this.f14809l;
    }

    public int getCipherSuite() {
        return this.f14801d;
    }

    public short[] getClientCertTypes() {
        return this.H;
    }

    public byte[] getClientRandom() {
        return this.f14817t;
    }

    public Vector getClientServerNames() {
        return this.I;
    }

    public Vector getClientSigAlgs() {
        return this.J;
    }

    public Vector getClientSigAlgsCert() {
        return this.K;
    }

    public int[] getClientSupportedGroups() {
        return this.L;
    }

    public short getCompressionAlgorithm() {
        return (short) 0;
    }

    public TlsSecret getEarlyExporterMasterSecret() {
        return this.f14810m;
    }

    public TlsSecret getEarlySecret() {
        return this.f14811n;
    }

    public int getEntity() {
        return this.f14798a;
    }

    public TlsSecret getExporterMasterSecret() {
        return this.f14812o;
    }

    public TlsSecret getHandshakeSecret() {
        return this.f14813p;
    }

    public int getKeyExchangeAlgorithm() {
        return this.P;
    }

    public Certificate getLocalCertificate() {
        return this.Q;
    }

    public byte[] getLocalVerifyData() {
        return this.U;
    }

    public TlsSecret getMasterSecret() {
        return this.f14814q;
    }

    public short getMaxFragmentLength() {
        return this.f14802e;
    }

    public ProtocolVersion getNegotiatedVersion() {
        return this.S;
    }

    public int getPRFAlgorithm() {
        return this.f14803f;
    }

    public int getPRFCryptoHashAlgorithm() {
        return this.f14804g;
    }

    public short getPRFHashAlgorithm() {
        return this.f14805h;
    }

    public int getPRFHashLength() {
        return this.f14806i;
    }

    public byte[] getPSKIdentity() {
        return this.x;
    }

    public Certificate getPeerCertificate() {
        return this.R;
    }

    public byte[] getPeerVerifyData() {
        return this.V;
    }

    public int getPrfAlgorithm() {
        return this.f14803f;
    }

    public byte[] getSRPIdentity() {
        return this.y;
    }

    public byte[] getServerRandom() {
        return this.u;
    }

    public Vector getServerSigAlgs() {
        return this.M;
    }

    public Vector getServerSigAlgsCert() {
        return this.N;
    }

    public int[] getServerSupportedGroups() {
        return this.O;
    }

    public byte[] getSessionHash() {
        return this.v;
    }

    public byte[] getSessionID() {
        return this.w;
    }

    public int getStatusRequestVersion() {
        return this.T;
    }

    public byte[] getTLSServerEndPoint() {
        return this.z;
    }

    public byte[] getTLSUnique() {
        return this.A;
    }

    public TlsSecret getTrafficSecretClient() {
        return this.f14815r;
    }

    public TlsSecret getTrafficSecretServer() {
        return this.f14816s;
    }

    public int getVerifyDataLength() {
        return this.f14807j;
    }

    public boolean isApplicationProtocolSet() {
        return this.G;
    }

    public boolean isEncryptThenMAC() {
        return this.B;
    }

    public boolean isExtendedMasterSecret() {
        return this.C;
    }

    public boolean isExtendedPadding() {
        return this.D;
    }

    public boolean isRenegotiating() {
        return this.f14799b;
    }

    public boolean isSecureRenegotiation() {
        return this.f14800c;
    }

    public boolean isTruncatedHMac() {
        return this.E;
    }
}
