package org.bouncycastle.tls.crypto.impl.jcajce;

import com.google.android.gms.stats.CodePackage;
import java.security.AccessController;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.PrivilegedAction;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.cms.GCMParameters;
import org.bouncycastle.jcajce.spec.AEADParameterSpec;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl;
/* loaded from: classes4.dex */
public class JceAEADCipherImpl implements TlsAEADCipherImpl {
    private static final boolean canDoAEAD = checkForAEAD();
    private final String algorithm;
    private final String algorithmParamsName;
    private final Cipher cipher;
    private final int cipherMode;
    private final JcaJceHelper helper;
    private SecretKey key;
    private final int keySize;

    public JceAEADCipherImpl(JcaJceHelper jcaJceHelper, String str, String str2, int i2, boolean z) {
        this.helper = jcaJceHelper;
        this.cipher = jcaJceHelper.createCipher(str);
        this.algorithm = str2;
        this.keySize = i2;
        this.cipherMode = z ? 1 : 2;
        this.algorithmParamsName = getAlgParamsName(jcaJceHelper, str);
    }

    private static boolean checkForAEAD() {
        return ((Boolean) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.bouncycastle.tls.crypto.impl.jcajce.JceAEADCipherImpl.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                try {
                    boolean z = true;
                    if (Cipher.class.getMethod("updateAAD", byte[].class) == null) {
                        z = false;
                    }
                    return Boolean.valueOf(z);
                } catch (Exception unused) {
                    return Boolean.FALSE;
                }
            }
        })).booleanValue();
    }

    private static String getAlgParamsName(JcaJceHelper jcaJceHelper, String str) {
        String str2 = "CCM";
        try {
            if (!str.contains("CCM")) {
                str2 = CodePackage.GCM;
            }
            jcaJceHelper.createAlgorithmParameters(str2);
            return str2;
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl
    public int doFinal(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        try {
            return this.cipher.doFinal(bArr, i2, i3, bArr2, i4);
        } catch (GeneralSecurityException e2) {
            throw Exceptions.b("", e2);
        }
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl
    public int getOutputSize(int i2) {
        return this.cipher.getOutputSize(i2);
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl
    public void init(byte[] bArr, int i2, byte[] bArr2) {
        String str;
        try {
            if (!canDoAEAD || (str = this.algorithmParamsName) == null) {
                this.cipher.init(this.cipherMode, this.key, new AEADParameterSpec(bArr, i2 * 8, bArr2), (SecureRandom) null);
                return;
            }
            AlgorithmParameters createAlgorithmParameters = this.helper.createAlgorithmParameters(str);
            createAlgorithmParameters.init(new GCMParameters(bArr, i2).getEncoded());
            this.cipher.init(this.cipherMode, this.key, createAlgorithmParameters, (SecureRandom) null);
            if (bArr2 == null || bArr2.length <= 0) {
                return;
            }
            this.cipher.updateAAD(bArr2);
        } catch (Exception e2) {
            throw Exceptions.b(e2.getMessage(), e2);
        }
    }

    @Override // org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl
    public void setKey(byte[] bArr, int i2, int i3) {
        if (this.keySize != i3) {
            throw new IllegalStateException();
        }
        this.key = new SecretKeySpec(bArr, i2, i3, this.algorithm);
    }
}
