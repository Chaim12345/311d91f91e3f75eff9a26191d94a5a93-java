package com.google.api.client.googleapis.apache;

import com.google.api.client.googleapis.GoogleUtils;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.util.SslUtils;
import java.net.ProxySelector;
import java.security.KeyStore;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;
/* loaded from: classes2.dex */
public final class GoogleApacheHttpTransport {
    private GoogleApacheHttpTransport() {
    }

    public static ApacheHttpTransport newTrustedTransport() {
        SocketConfig build = SocketConfig.custom().setRcvBufSize(8192).setSndBufSize(8192).build();
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(-1L, TimeUnit.MILLISECONDS);
        poolingHttpClientConnectionManager.setValidateAfterInactivity(-1);
        KeyStore certificateTrustStore = GoogleUtils.getCertificateTrustStore();
        SSLContext tlsSslContext = SslUtils.getTlsSslContext();
        SslUtils.initSslContext(tlsSslContext, certificateTrustStore, SslUtils.getPkixTrustManagerFactory());
        return new ApacheHttpTransport(HttpClientBuilder.create().useSystemProperties().setSSLSocketFactory(new SSLConnectionSocketFactory(tlsSslContext)).setDefaultSocketConfig(build).setMaxConnTotal(200).setMaxConnPerRoute(20).setRoutePlanner(new SystemDefaultRoutePlanner(ProxySelector.getDefault())).setConnectionManager(poolingHttpClientConnectionManager).disableRedirectHandling().disableAutomaticRetries().build());
    }
}
