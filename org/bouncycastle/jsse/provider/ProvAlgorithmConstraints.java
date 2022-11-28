package org.bouncycastle.jsse.provider;

import java.security.AlgorithmParameters;
import java.security.Key;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.jsse.java.security.BCAlgorithmConstraints;
import org.bouncycastle.jsse.java.security.BCCryptoPrimitive;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class ProvAlgorithmConstraints extends AbstractAlgorithmConstraints {
    private final BCAlgorithmConstraints configAlgorithmConstraints;
    private final boolean enableX509Constraints;
    private final Set<String> supportedSignatureAlgorithms;
    private static final Logger LOG = Logger.getLogger(ProvAlgorithmConstraints.class.getName());
    private static final String PROPERTY_TLS_DISABLED_ALGORITHMS = "jdk.tls.disabledAlgorithms";
    private static final String DEFAULT_TLS_DISABLED_ALGORITHMS = "SSLv3, TLSv1, TLSv1.1, RC4, DES, MD5withRSA, DH keySize < 1024, EC keySize < 224, 3DES_EDE_CBC, anon, NULL, include jdk.disabled.namedCurves";
    private static final DisabledAlgorithmConstraints provTlsDisabledAlgorithms = DisabledAlgorithmConstraints.i(ProvAlgorithmDecomposer.f13913b, PROPERTY_TLS_DISABLED_ALGORITHMS, DEFAULT_TLS_DISABLED_ALGORITHMS);
    private static final String PROPERTY_CERTPATH_DISABLED_ALGORITHMS = "jdk.certpath.disabledAlgorithms";
    private static final String DEFAULT_CERTPATH_DISABLED_ALGORITHMS = "MD2, MD5, SHA1 jdkCA & usage TLSServer, RSA keySize < 1024, DSA keySize < 1024, EC keySize < 224, include jdk.disabled.namedCurves";
    private static final DisabledAlgorithmConstraints provX509DisabledAlgorithms = DisabledAlgorithmConstraints.i(ProvAlgorithmDecomposer.f13914c, PROPERTY_CERTPATH_DISABLED_ALGORITHMS, DEFAULT_CERTPATH_DISABLED_ALGORITHMS);

    /* renamed from: b  reason: collision with root package name */
    static final ProvAlgorithmConstraints f13911b = new ProvAlgorithmConstraints(null, true);

    /* renamed from: c  reason: collision with root package name */
    static final ProvAlgorithmConstraints f13912c = new ProvAlgorithmConstraints(null, false);

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvAlgorithmConstraints(BCAlgorithmConstraints bCAlgorithmConstraints, boolean z) {
        super(null);
        this.configAlgorithmConstraints = bCAlgorithmConstraints;
        this.supportedSignatureAlgorithms = null;
        this.enableX509Constraints = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvAlgorithmConstraints(BCAlgorithmConstraints bCAlgorithmConstraints, String[] strArr, boolean z) {
        super(null);
        this.configAlgorithmConstraints = bCAlgorithmConstraints;
        this.supportedSignatureAlgorithms = AbstractAlgorithmConstraints.b(strArr);
        this.enableX509Constraints = z;
    }

    private String getAlgorithm(String str) {
        int indexOf = str.indexOf(58);
        return indexOf < 0 ? str : str.substring(0, indexOf);
    }

    private boolean isSupportedSignatureAlgorithm(String str) {
        return g(this.supportedSignatureAlgorithms, str);
    }

    @Override // org.bouncycastle.jsse.java.security.BCAlgorithmConstraints
    public boolean permits(Set<BCCryptoPrimitive> set, String str, AlgorithmParameters algorithmParameters) {
        DisabledAlgorithmConstraints disabledAlgorithmConstraints;
        e(set);
        c(str);
        if (this.supportedSignatureAlgorithms != null) {
            String algorithm = getAlgorithm(str);
            if (!isSupportedSignatureAlgorithm(str)) {
                Logger logger = LOG;
                if (logger.isLoggable(Level.FINEST)) {
                    logger.finest("Signature algorithm '" + str + "' not in supported signature algorithms");
                }
                return false;
            }
            str = algorithm;
        }
        BCAlgorithmConstraints bCAlgorithmConstraints = this.configAlgorithmConstraints;
        if (bCAlgorithmConstraints == null || bCAlgorithmConstraints.permits(set, str, algorithmParameters)) {
            DisabledAlgorithmConstraints disabledAlgorithmConstraints2 = provTlsDisabledAlgorithms;
            if (disabledAlgorithmConstraints2 == null || disabledAlgorithmConstraints2.permits(set, str, algorithmParameters)) {
                return !this.enableX509Constraints || (disabledAlgorithmConstraints = provX509DisabledAlgorithms) == null || disabledAlgorithmConstraints.permits(set, str, algorithmParameters);
            }
            return false;
        }
        return false;
    }

    @Override // org.bouncycastle.jsse.java.security.BCAlgorithmConstraints
    public boolean permits(Set<BCCryptoPrimitive> set, String str, Key key, AlgorithmParameters algorithmParameters) {
        DisabledAlgorithmConstraints disabledAlgorithmConstraints;
        e(set);
        c(str);
        d(key);
        if (this.supportedSignatureAlgorithms != null) {
            String algorithm = getAlgorithm(str);
            if (!isSupportedSignatureAlgorithm(str)) {
                Logger logger = LOG;
                if (logger.isLoggable(Level.FINEST)) {
                    logger.finest("Signature algorithm '" + str + "' not in supported signature algorithms");
                }
                return false;
            }
            str = algorithm;
        }
        BCAlgorithmConstraints bCAlgorithmConstraints = this.configAlgorithmConstraints;
        if (bCAlgorithmConstraints == null || bCAlgorithmConstraints.permits(set, str, key, algorithmParameters)) {
            DisabledAlgorithmConstraints disabledAlgorithmConstraints2 = provTlsDisabledAlgorithms;
            if (disabledAlgorithmConstraints2 == null || disabledAlgorithmConstraints2.permits(set, str, key, algorithmParameters)) {
                return !this.enableX509Constraints || (disabledAlgorithmConstraints = provX509DisabledAlgorithms) == null || disabledAlgorithmConstraints.permits(set, str, key, algorithmParameters);
            }
            return false;
        }
        return false;
    }

    @Override // org.bouncycastle.jsse.java.security.BCAlgorithmConstraints
    public boolean permits(Set<BCCryptoPrimitive> set, Key key) {
        DisabledAlgorithmConstraints disabledAlgorithmConstraints;
        e(set);
        d(key);
        BCAlgorithmConstraints bCAlgorithmConstraints = this.configAlgorithmConstraints;
        if (bCAlgorithmConstraints == null || bCAlgorithmConstraints.permits(set, key)) {
            DisabledAlgorithmConstraints disabledAlgorithmConstraints2 = provTlsDisabledAlgorithms;
            if (disabledAlgorithmConstraints2 == null || disabledAlgorithmConstraints2.permits(set, key)) {
                return !this.enableX509Constraints || (disabledAlgorithmConstraints = provX509DisabledAlgorithms) == null || disabledAlgorithmConstraints.permits(set, key);
            }
            return false;
        }
        return false;
    }
}
