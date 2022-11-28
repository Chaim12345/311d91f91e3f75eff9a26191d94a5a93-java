package com.psa.mym.mycitroenconnect.api.retrofit;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
/* loaded from: classes.dex */
public class SSlUtils {
    public static KeyStore getKeyStore(Context context, String str) {
        KeyStore keyStore = null;
        try {
            AssetManager assets = context.getAssets();
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            InputStream open = assets.open(str);
            try {
                Certificate generateCertificate = certificateFactory.generateCertificate(open);
                open.close();
                KeyStore keyStore2 = KeyStore.getInstance(KeyStore.getDefaultType());
                try {
                    keyStore2.load(null, null);
                    keyStore2.setCertificateEntry("ca", generateCertificate);
                    return keyStore2;
                } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e2) {
                    e = e2;
                    keyStore = keyStore2;
                    e.printStackTrace();
                    return keyStore;
                }
            } catch (Throwable th) {
                open.close();
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
        } catch (KeyStoreException e4) {
            e = e4;
        } catch (NoSuchAlgorithmException e5) {
            e = e5;
        } catch (CertificateException e6) {
            e = e6;
        }
    }
}
