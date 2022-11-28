package org.bouncycastle.jsse.provider;

import java.math.BigInteger;
import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CertificateParsingException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLPermission;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionBindingEvent;
import javax.net.ssl.SSLSessionBindingListener;
import javax.net.ssl.SSLSessionContext;
import javax.security.auth.x500.X500Principal;
import javax.security.cert.X509Certificate;
import org.bouncycastle.jsse.BCExtendedSSLSession;
import org.bouncycastle.tls.Certificate;
import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCrypto;
import org.bouncycastle.util.Arrays;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public abstract class ProvSSLSessionBase extends BCExtendedSSLSession {

    /* renamed from: a  reason: collision with root package name */
    protected final Map f13950a = Collections.synchronizedMap(new HashMap());

    /* renamed from: b  reason: collision with root package name */
    protected final AtomicReference f13951b;

    /* renamed from: c  reason: collision with root package name */
    protected final boolean f13952c;

    /* renamed from: d  reason: collision with root package name */
    protected final JcaTlsCrypto f13953d;

    /* renamed from: e  reason: collision with root package name */
    protected final String f13954e;

    /* renamed from: f  reason: collision with root package name */
    protected final int f13955f;

    /* renamed from: g  reason: collision with root package name */
    protected final long f13956g;

    /* renamed from: h  reason: collision with root package name */
    protected final SSLSession f13957h;

    /* renamed from: i  reason: collision with root package name */
    protected final AtomicLong f13958i;

    /* loaded from: classes3.dex */
    private static class X509CertificateWrapper extends X509Certificate {

        /* renamed from: c  reason: collision with root package name */
        private final java.security.cert.X509Certificate f13959c;

        private X509CertificateWrapper(java.security.cert.X509Certificate x509Certificate) {
            this.f13959c = x509Certificate;
        }

        @Override // javax.security.cert.X509Certificate
        public void checkValidity() {
            try {
                this.f13959c.checkValidity();
            } catch (CertificateExpiredException e2) {
                throw new javax.security.cert.CertificateExpiredException(e2.getMessage());
            } catch (CertificateNotYetValidException e3) {
                throw new javax.security.cert.CertificateNotYetValidException(e3.getMessage());
            }
        }

        @Override // javax.security.cert.X509Certificate
        public void checkValidity(Date date) {
            try {
                this.f13959c.checkValidity(date);
            } catch (CertificateExpiredException e2) {
                throw new javax.security.cert.CertificateExpiredException(e2.getMessage());
            } catch (CertificateNotYetValidException e3) {
                throw new javax.security.cert.CertificateNotYetValidException(e3.getMessage());
            }
        }

        @Override // javax.security.cert.Certificate
        public byte[] getEncoded() {
            try {
                return this.f13959c.getEncoded();
            } catch (CertificateEncodingException e2) {
                throw new javax.security.cert.CertificateEncodingException(e2.getMessage());
            }
        }

        @Override // javax.security.cert.X509Certificate
        public Principal getIssuerDN() {
            return this.f13959c.getIssuerX500Principal();
        }

        @Override // javax.security.cert.X509Certificate
        public Date getNotAfter() {
            return this.f13959c.getNotAfter();
        }

        @Override // javax.security.cert.X509Certificate
        public Date getNotBefore() {
            return this.f13959c.getNotBefore();
        }

        @Override // javax.security.cert.Certificate
        public PublicKey getPublicKey() {
            return this.f13959c.getPublicKey();
        }

        @Override // javax.security.cert.X509Certificate
        public BigInteger getSerialNumber() {
            return this.f13959c.getSerialNumber();
        }

        @Override // javax.security.cert.X509Certificate
        public String getSigAlgName() {
            return this.f13959c.getSigAlgName();
        }

        @Override // javax.security.cert.X509Certificate
        public String getSigAlgOID() {
            return this.f13959c.getSigAlgOID();
        }

        @Override // javax.security.cert.X509Certificate
        public byte[] getSigAlgParams() {
            return this.f13959c.getSigAlgParams();
        }

        @Override // javax.security.cert.X509Certificate
        public Principal getSubjectDN() {
            return this.f13959c.getSubjectX500Principal();
        }

        @Override // javax.security.cert.X509Certificate
        public int getVersion() {
            return this.f13959c.getVersion() - 1;
        }

        @Override // javax.security.cert.Certificate
        public String toString() {
            return this.f13959c.toString();
        }

        @Override // javax.security.cert.Certificate
        public void verify(PublicKey publicKey) {
            try {
                this.f13959c.verify(publicKey);
            } catch (CertificateEncodingException e2) {
                throw new javax.security.cert.CertificateEncodingException(e2.getMessage());
            } catch (CertificateExpiredException e3) {
                throw new javax.security.cert.CertificateExpiredException(e3.getMessage());
            } catch (CertificateNotYetValidException e4) {
                throw new javax.security.cert.CertificateNotYetValidException(e4.getMessage());
            } catch (CertificateParsingException e5) {
                throw new javax.security.cert.CertificateParsingException(e5.getMessage());
            } catch (CertificateException e6) {
                throw new javax.security.cert.CertificateException(e6.getMessage());
            }
        }

        @Override // javax.security.cert.Certificate
        public void verify(PublicKey publicKey, String str) {
            try {
                this.f13959c.verify(publicKey, str);
            } catch (CertificateEncodingException e2) {
                throw new javax.security.cert.CertificateEncodingException(e2.getMessage());
            } catch (CertificateExpiredException e3) {
                throw new javax.security.cert.CertificateExpiredException(e3.getMessage());
            } catch (CertificateNotYetValidException e4) {
                throw new javax.security.cert.CertificateNotYetValidException(e4.getMessage());
            } catch (CertificateParsingException e5) {
                throw new javax.security.cert.CertificateParsingException(e5.getMessage());
            } catch (CertificateException e6) {
                throw new javax.security.cert.CertificateException(e6.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvSSLSessionBase(ProvSSLSessionContext provSSLSessionContext, String str, int i2) {
        this.f13951b = new AtomicReference(provSSLSessionContext);
        this.f13952c = provSSLSessionContext == null ? false : provSSLSessionContext.d().p();
        this.f13953d = provSSLSessionContext == null ? null : provSSLSessionContext.c();
        this.f13954e = str;
        this.f13955f = i2;
        long currentTimeMillis = System.currentTimeMillis();
        this.f13956g = currentTimeMillis;
        this.f13957h = SSLSessionUtil.a(this);
        this.f13958i = new AtomicLong(currentTimeMillis);
    }

    private void implInvalidate(boolean z) {
        if (z) {
            ProvSSLSessionContext provSSLSessionContext = (ProvSSLSessionContext) this.f13951b.getAndSet(null);
            if (provSSLSessionContext != null) {
                provSSLSessionContext.g(d());
            }
        } else {
            this.f13951b.set(null);
        }
        h();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(long j2) {
        long j3 = this.f13958i.get();
        if (j2 > j3) {
            this.f13958i.compareAndSet(j3, j2);
        }
    }

    protected abstract int b();

    /* JADX INFO: Access modifiers changed from: package-private */
    public SSLSession c() {
        return this.f13957h;
    }

    protected abstract byte[] d();

    protected abstract Certificate e();

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ProvSSLSessionBase) {
            return Arrays.areEqual(d(), ((ProvSSLSessionBase) obj).d());
        }
        return false;
    }

    protected abstract Certificate f();

    protected abstract ProtocolVersion g();

    @Override // javax.net.ssl.SSLSession
    public int getApplicationBufferSize() {
        return 16384;
    }

    @Override // javax.net.ssl.SSLSession
    public String getCipherSuite() {
        return ProvSSLContextSpi.d(b());
    }

    @Override // javax.net.ssl.SSLSession
    public long getCreationTime() {
        return this.f13956g;
    }

    @Override // javax.net.ssl.SSLSession
    public byte[] getId() {
        byte[] d2 = d();
        return TlsUtils.isNullOrEmpty(d2) ? TlsUtils.EMPTY_BYTES : (byte[]) d2.clone();
    }

    @Override // javax.net.ssl.SSLSession
    public long getLastAccessedTime() {
        return this.f13958i.get();
    }

    @Override // javax.net.ssl.SSLSession
    public java.security.cert.Certificate[] getLocalCertificates() {
        java.security.cert.X509Certificate[] O;
        JcaTlsCrypto jcaTlsCrypto = this.f13953d;
        if (jcaTlsCrypto == null || (O = JsseUtils.O(jcaTlsCrypto, e())) == null || O.length <= 0) {
            return null;
        }
        return O;
    }

    @Override // javax.net.ssl.SSLSession
    public Principal getLocalPrincipal() {
        JcaTlsCrypto jcaTlsCrypto = this.f13953d;
        if (jcaTlsCrypto != null) {
            return JsseUtils.L(jcaTlsCrypto, e());
        }
        return null;
    }

    @Override // javax.net.ssl.SSLSession
    public int getPacketBufferSize() {
        ProtocolVersion g2 = g();
        if (g2 == null || !TlsUtils.isTLSv12(g2)) {
            return 18443;
        }
        return TlsUtils.isTLSv13(g2) ? 16911 : 17413;
    }

    @Override // javax.net.ssl.SSLSession
    public X509Certificate[] getPeerCertificateChain() {
        java.security.cert.X509Certificate[] x509CertificateArr = (java.security.cert.X509Certificate[]) getPeerCertificates();
        X509Certificate[] x509CertificateArr2 = new X509Certificate[x509CertificateArr.length];
        for (int i2 = 0; i2 < x509CertificateArr.length; i2++) {
            try {
                if (this.f13952c) {
                    x509CertificateArr2[i2] = new X509CertificateWrapper(x509CertificateArr[i2]);
                } else {
                    x509CertificateArr2[i2] = X509Certificate.getInstance(x509CertificateArr[i2].getEncoded());
                }
            } catch (Exception e2) {
                throw new SSLPeerUnverifiedException(e2.getMessage());
            }
        }
        return x509CertificateArr2;
    }

    @Override // javax.net.ssl.SSLSession
    public java.security.cert.Certificate[] getPeerCertificates() {
        java.security.cert.X509Certificate[] O;
        JcaTlsCrypto jcaTlsCrypto = this.f13953d;
        if (jcaTlsCrypto == null || (O = JsseUtils.O(jcaTlsCrypto, f())) == null || O.length <= 0) {
            throw new SSLPeerUnverifiedException("No peer identity established");
        }
        return O;
    }

    @Override // javax.net.ssl.SSLSession
    public String getPeerHost() {
        return this.f13954e;
    }

    @Override // javax.net.ssl.SSLSession
    public int getPeerPort() {
        return this.f13955f;
    }

    @Override // javax.net.ssl.SSLSession
    public Principal getPeerPrincipal() {
        X500Principal L;
        JcaTlsCrypto jcaTlsCrypto = this.f13953d;
        if (jcaTlsCrypto == null || (L = JsseUtils.L(jcaTlsCrypto, f())) == null) {
            throw new SSLPeerUnverifiedException("No peer identity established");
        }
        return L;
    }

    @Override // javax.net.ssl.SSLSession
    public String getProtocol() {
        return ProvSSLContextSpi.k(g());
    }

    @Override // javax.net.ssl.SSLSession
    public SSLSessionContext getSessionContext() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new SSLPermission("getSSLSessionContext"));
        }
        return (SSLSessionContext) this.f13951b.get();
    }

    @Override // javax.net.ssl.SSLSession
    public Object getValue(String str) {
        return this.f13950a.get(str);
    }

    @Override // javax.net.ssl.SSLSession
    public String[] getValueNames() {
        String[] strArr;
        synchronized (this.f13950a) {
            strArr = (String[]) this.f13950a.keySet().toArray(new String[this.f13950a.size()]);
        }
        return strArr;
    }

    protected abstract void h();

    public int hashCode() {
        return Arrays.hashCode(d());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void i() {
        implInvalidate(false);
    }

    @Override // javax.net.ssl.SSLSession
    public final void invalidate() {
        implInvalidate(true);
    }

    @Override // javax.net.ssl.SSLSession
    public boolean isValid() {
        if (this.f13951b.get() == null) {
            return false;
        }
        return !TlsUtils.isNullOrEmpty(d());
    }

    protected void j(String str, Object obj) {
        if (obj instanceof SSLSessionBindingListener) {
            ((SSLSessionBindingListener) obj).valueBound(new SSLSessionBindingEvent(this, str));
        }
    }

    protected void k(String str, Object obj) {
        if (obj instanceof SSLSessionBindingListener) {
            ((SSLSessionBindingListener) obj).valueUnbound(new SSLSessionBindingEvent(this, str));
        }
    }

    @Override // javax.net.ssl.SSLSession
    public void putValue(String str, Object obj) {
        k(str, this.f13950a.put(str, obj));
        j(str, obj);
    }

    @Override // javax.net.ssl.SSLSession
    public void removeValue(String str) {
        k(str, this.f13950a.remove(str));
    }

    public String toString() {
        return "Session(" + getCreationTime() + "|" + getCipherSuite() + ")";
    }
}
