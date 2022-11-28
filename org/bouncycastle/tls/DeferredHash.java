package org.bouncycastle.tls;

import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import org.bouncycastle.tls.crypto.TlsHash;
import org.bouncycastle.util.Integers;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class DeferredHash implements TlsHandshakeHash {

    /* renamed from: a  reason: collision with root package name */
    protected TlsContext f14758a;
    private DigestInputBuffer buf;
    private boolean forceBuffering;
    private Hashtable hashes;
    private boolean sealed;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DeferredHash(TlsContext tlsContext) {
        this.f14758a = tlsContext;
        this.buf = new DigestInputBuffer();
        this.hashes = new Hashtable();
        this.forceBuffering = false;
        this.sealed = false;
    }

    private DeferredHash(TlsContext tlsContext, Hashtable hashtable) {
        this.f14758a = tlsContext;
        this.buf = null;
        this.hashes = hashtable;
        this.forceBuffering = false;
        this.sealed = true;
    }

    protected Integer a(int i2) {
        return Integers.valueOf(i2);
    }

    protected void b() {
        if (this.forceBuffering || !this.sealed || this.buf == null || this.hashes.size() > 4) {
            return;
        }
        Enumeration elements = this.hashes.elements();
        while (elements.hasMoreElements()) {
            this.buf.b((TlsHash) elements.nextElement());
        }
        this.buf = null;
    }

    protected void c(int i2) {
        d(a(i2));
    }

    @Override // org.bouncycastle.tls.crypto.TlsHash
    public byte[] calculateHash() {
        throw new IllegalStateException("Use 'forkPRFHash' to get a definite hash");
    }

    @Override // org.bouncycastle.tls.crypto.TlsHash
    public TlsHash cloneHash() {
        throw new IllegalStateException("attempt to clone a DeferredHash");
    }

    @Override // org.bouncycastle.tls.TlsHandshakeHash
    public void copyBufferTo(OutputStream outputStream) {
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer == null) {
            throw new IllegalStateException("Not buffering");
        }
        digestInputBuffer.a(outputStream);
    }

    protected void d(Integer num) {
        if (this.hashes.containsKey(num)) {
            return;
        }
        this.hashes.put(num, this.f14758a.getCrypto().createHash(num.intValue()));
    }

    protected TlsHash e(int i2) {
        return f(a(i2));
    }

    protected TlsHash f(Integer num) {
        return ((TlsHash) this.hashes.get(num)).cloneHash();
    }

    @Override // org.bouncycastle.tls.TlsHandshakeHash
    public void forceBuffering() {
        if (this.sealed) {
            throw new IllegalStateException("Too late to force buffering");
        }
        this.forceBuffering = true;
    }

    @Override // org.bouncycastle.tls.TlsHandshakeHash
    public TlsHash forkPRFHash() {
        b();
        SecurityParameters securityParametersHandshake = this.f14758a.getSecurityParametersHandshake();
        int pRFAlgorithm = securityParametersHandshake.getPRFAlgorithm();
        TlsHash combinedHash = (pRFAlgorithm == 0 || pRFAlgorithm == 1) ? new CombinedHash(this.f14758a, e(1), e(2)) : e(securityParametersHandshake.getPRFCryptoHashAlgorithm());
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.b(combinedHash);
        }
        return combinedHash;
    }

    protected void g(Hashtable hashtable, int i2) {
        h(hashtable, a(i2));
    }

    @Override // org.bouncycastle.tls.TlsHandshakeHash
    public byte[] getFinalHash(int i2) {
        TlsHash tlsHash = (TlsHash) this.hashes.get(a(i2));
        if (tlsHash == null) {
            throw new IllegalStateException("CryptoHashAlgorithm." + i2 + " is not being tracked");
        }
        b();
        TlsHash cloneHash = tlsHash.cloneHash();
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.b(cloneHash);
        }
        return cloneHash.calculateHash();
    }

    protected void h(Hashtable hashtable, Integer num) {
        TlsHash f2 = f(num);
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.b(f2);
        }
        hashtable.put(num, f2);
    }

    @Override // org.bouncycastle.tls.TlsHandshakeHash
    public void notifyPRFDetermined() {
        int i2;
        SecurityParameters securityParametersHandshake = this.f14758a.getSecurityParametersHandshake();
        int pRFAlgorithm = securityParametersHandshake.getPRFAlgorithm();
        if (pRFAlgorithm == 0 || pRFAlgorithm == 1) {
            c(1);
            i2 = 2;
        } else {
            i2 = securityParametersHandshake.getPRFCryptoHashAlgorithm();
        }
        c(i2);
    }

    @Override // org.bouncycastle.tls.crypto.TlsHash
    public void reset() {
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.reset();
            return;
        }
        Enumeration elements = this.hashes.elements();
        while (elements.hasMoreElements()) {
            ((TlsHash) elements.nextElement()).reset();
        }
    }

    @Override // org.bouncycastle.tls.TlsHandshakeHash
    public void sealHashAlgorithms() {
        if (this.sealed) {
            throw new IllegalStateException("Already sealed");
        }
        this.sealed = true;
        b();
    }

    @Override // org.bouncycastle.tls.TlsHandshakeHash
    public TlsHandshakeHash stopTracking() {
        int i2;
        SecurityParameters securityParametersHandshake = this.f14758a.getSecurityParametersHandshake();
        Hashtable hashtable = new Hashtable();
        int pRFAlgorithm = securityParametersHandshake.getPRFAlgorithm();
        if (pRFAlgorithm == 0 || pRFAlgorithm == 1) {
            g(hashtable, 1);
            i2 = 2;
        } else {
            i2 = securityParametersHandshake.getPRFCryptoHashAlgorithm();
        }
        g(hashtable, i2);
        return new DeferredHash(this.f14758a, hashtable);
    }

    @Override // org.bouncycastle.tls.TlsHandshakeHash
    public void trackHashAlgorithm(int i2) {
        if (this.sealed) {
            throw new IllegalStateException("Too late to track more hash algorithms");
        }
        c(i2);
    }

    @Override // org.bouncycastle.tls.crypto.TlsHash
    public void update(byte[] bArr, int i2, int i3) {
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.write(bArr, i2, i3);
            return;
        }
        Enumeration elements = this.hashes.elements();
        while (elements.hasMoreElements()) {
            ((TlsHash) elements.nextElement()).update(bArr, i2, i3);
        }
    }
}
