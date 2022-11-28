package org.bouncycastle.jsse;

import java.net.Socket;
import java.security.Principal;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedKeyManager;
/* loaded from: classes3.dex */
public abstract class BCX509ExtendedKeyManager extends X509ExtendedKeyManager {
    protected abstract BCX509Key a(String str, String str2);

    public BCX509Key chooseClientKeyBC(String[] strArr, Principal[] principalArr, Socket socket) {
        if (strArr != null) {
            for (String str : strArr) {
                String chooseClientAlias = chooseClientAlias(new String[]{str}, principalArr, socket);
                if (chooseClientAlias != null) {
                    return a(str, chooseClientAlias);
                }
            }
            return null;
        }
        return null;
    }

    public BCX509Key chooseEngineClientKeyBC(String[] strArr, Principal[] principalArr, SSLEngine sSLEngine) {
        if (strArr != null) {
            for (String str : strArr) {
                String chooseEngineClientAlias = chooseEngineClientAlias(new String[]{str}, principalArr, sSLEngine);
                if (chooseEngineClientAlias != null) {
                    return a(str, chooseEngineClientAlias);
                }
            }
            return null;
        }
        return null;
    }

    public BCX509Key chooseEngineServerKeyBC(String[] strArr, Principal[] principalArr, SSLEngine sSLEngine) {
        if (strArr != null) {
            for (String str : strArr) {
                String chooseEngineServerAlias = chooseEngineServerAlias(str, principalArr, sSLEngine);
                if (chooseEngineServerAlias != null) {
                    return a(str, chooseEngineServerAlias);
                }
            }
            return null;
        }
        return null;
    }

    public BCX509Key chooseServerKeyBC(String[] strArr, Principal[] principalArr, Socket socket) {
        if (strArr != null) {
            for (String str : strArr) {
                String chooseServerAlias = chooseServerAlias(str, principalArr, socket);
                if (chooseServerAlias != null) {
                    return a(str, chooseServerAlias);
                }
            }
            return null;
        }
        return null;
    }
}
