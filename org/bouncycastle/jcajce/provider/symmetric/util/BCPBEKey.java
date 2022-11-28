package org.bouncycastle.jcajce.provider.symmetric.util;

import java.util.concurrent.atomic.AtomicBoolean;
import javax.crypto.interfaces.PBEKey;
import javax.crypto.spec.PBEKeySpec;
import javax.security.auth.Destroyable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
/* loaded from: classes3.dex */
public class BCPBEKey implements PBEKey, Destroyable {

    /* renamed from: a  reason: collision with root package name */
    String f13773a;

    /* renamed from: b  reason: collision with root package name */
    ASN1ObjectIdentifier f13774b;

    /* renamed from: c  reason: collision with root package name */
    int f13775c;

    /* renamed from: d  reason: collision with root package name */
    int f13776d;

    /* renamed from: e  reason: collision with root package name */
    int f13777e;

    /* renamed from: f  reason: collision with root package name */
    int f13778f;

    /* renamed from: g  reason: collision with root package name */
    boolean f13779g;
    private final AtomicBoolean hasBeenDestroyed;
    private final int iterationCount;
    private final CipherParameters param;
    private final char[] password;
    private final byte[] salt;

    public BCPBEKey(String str, ASN1ObjectIdentifier aSN1ObjectIdentifier, int i2, int i3, int i4, int i5, PBEKeySpec pBEKeySpec, CipherParameters cipherParameters) {
        this.hasBeenDestroyed = new AtomicBoolean(false);
        this.f13779g = false;
        this.f13773a = str;
        this.f13774b = aSN1ObjectIdentifier;
        this.f13775c = i2;
        this.f13776d = i3;
        this.f13777e = i4;
        this.f13778f = i5;
        this.password = pBEKeySpec.getPassword();
        this.iterationCount = pBEKeySpec.getIterationCount();
        this.salt = pBEKeySpec.getSalt();
        this.param = cipherParameters;
    }

    public BCPBEKey(String str, CipherParameters cipherParameters) {
        this.hasBeenDestroyed = new AtomicBoolean(false);
        this.f13779g = false;
        this.f13773a = str;
        this.param = cipherParameters;
        this.password = null;
        this.iterationCount = -1;
        this.salt = null;
    }

    static void a(Destroyable destroyable) {
        if (destroyable.isDestroyed()) {
            throw new IllegalStateException("key has been destroyed");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b() {
        a(this);
        return this.f13776d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int c() {
        a(this);
        return this.f13777e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int d() {
        a(this);
        return this.f13775c;
    }

    @Override // javax.security.auth.Destroyable
    public void destroy() {
        if (this.hasBeenDestroyed.getAndSet(true)) {
            return;
        }
        char[] cArr = this.password;
        if (cArr != null) {
            Arrays.fill(cArr, (char) 0);
        }
        byte[] bArr = this.salt;
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean e() {
        return this.f13779g;
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        a(this);
        return this.f13773a;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        a(this);
        CipherParameters cipherParameters = this.param;
        if (cipherParameters == null) {
            int i2 = this.f13775c;
            return i2 == 2 ? PBEParametersGenerator.PKCS12PasswordToBytes(this.password) : i2 == 5 ? PBEParametersGenerator.PKCS5PasswordToUTF8Bytes(this.password) : PBEParametersGenerator.PKCS5PasswordToBytes(this.password);
        }
        if (cipherParameters instanceof ParametersWithIV) {
            cipherParameters = ((ParametersWithIV) cipherParameters).getParameters();
        }
        return ((KeyParameter) cipherParameters).getKey();
    }

    @Override // java.security.Key
    public String getFormat() {
        return "RAW";
    }

    @Override // javax.crypto.interfaces.PBEKey
    public int getIterationCount() {
        a(this);
        return this.iterationCount;
    }

    public int getIvSize() {
        a(this);
        return this.f13778f;
    }

    public ASN1ObjectIdentifier getOID() {
        a(this);
        return this.f13774b;
    }

    public CipherParameters getParam() {
        a(this);
        return this.param;
    }

    @Override // javax.crypto.interfaces.PBEKey
    public char[] getPassword() {
        a(this);
        char[] cArr = this.password;
        if (cArr != null) {
            return Arrays.clone(cArr);
        }
        throw new IllegalStateException("no password available");
    }

    @Override // javax.crypto.interfaces.PBEKey
    public byte[] getSalt() {
        a(this);
        return Arrays.clone(this.salt);
    }

    @Override // javax.security.auth.Destroyable
    public boolean isDestroyed() {
        return this.hasBeenDestroyed.get();
    }

    public void setTryWrongPKCS12Zero(boolean z) {
        this.f13779g = z;
    }
}
