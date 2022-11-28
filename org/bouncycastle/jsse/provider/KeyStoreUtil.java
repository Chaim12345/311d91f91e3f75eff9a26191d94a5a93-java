package org.bouncycastle.jsse.provider;

import java.lang.reflect.Method;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.UnrecoverableKeyException;
/* loaded from: classes3.dex */
abstract class KeyStoreUtil {
    private static final Method getProtectionAlgorithm = ReflectionUtil.d("java.security.KeyStore$PasswordProtection", "getProtectionAlgorithm", new Class[0]);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Key a(KeyStore keyStore, String str, KeyStore.ProtectionParameter protectionParameter) {
        if (protectionParameter != null) {
            if (protectionParameter instanceof KeyStore.PasswordProtection) {
                KeyStore.PasswordProtection passwordProtection = (KeyStore.PasswordProtection) protectionParameter;
                Method method = getProtectionAlgorithm;
                if (method == null || ReflectionUtil.i(passwordProtection, method) == null) {
                    return keyStore.getKey(str, passwordProtection.getPassword());
                }
                throw new KeyStoreException("unsupported password protection algorithm");
            }
            throw new UnsupportedOperationException();
        }
        throw new UnrecoverableKeyException("requested key requires a password");
    }
}
