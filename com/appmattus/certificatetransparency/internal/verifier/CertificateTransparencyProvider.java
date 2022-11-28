package com.appmattus.certificatetransparency.internal.verifier;

import java.security.Provider;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public final class CertificateTransparencyProvider extends Provider {
    public CertificateTransparencyProvider() {
        super("CertificateTransparencyProvider", 1.0d, "");
        put("TrustManagerFactory.PKIX", CertificateTransparencyTrustManagerFactory.class.getName());
        put("Alg.Alias.TrustManagerFactory.X509", "PKIX");
    }

    @Override // java.security.Provider, java.util.Hashtable, java.util.Map
    public final /* bridge */ Set<Map.Entry<Object, Object>> entrySet() {
        return getEntries();
    }

    public /* bridge */ Set<Map.Entry<Object, Object>> getEntries() {
        return super.entrySet();
    }

    public /* bridge */ Set<Object> getKeys() {
        return super.keySet();
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ Collection<Object> getValues() {
        return super.values();
    }

    @Override // java.security.Provider, java.util.Hashtable, java.util.Map
    public final /* bridge */ Set<Object> keySet() {
        return getKeys();
    }

    @Override // java.util.Hashtable, java.util.Dictionary, java.util.Map
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.security.Provider, java.util.Hashtable, java.util.Map
    public final /* bridge */ Collection<Object> values() {
        return getValues();
    }
}
