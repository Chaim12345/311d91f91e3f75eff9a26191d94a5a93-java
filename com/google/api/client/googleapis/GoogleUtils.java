package com.google.api.client.googleapis;

import com.google.api.client.util.SecurityUtils;
import com.google.common.annotations.VisibleForTesting;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes2.dex */
public final class GoogleUtils {
    public static final Integer BUGFIX_VERSION;
    public static final Integer MAJOR_VERSION;
    public static final Integer MINOR_VERSION;
    public static final String VERSION;
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    static final Pattern f7983a;

    /* renamed from: b  reason: collision with root package name */
    static KeyStore f7984b;

    static {
        String version = getVersion();
        VERSION = version;
        Pattern compile = Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)(-SNAPSHOT)?");
        f7983a = compile;
        Matcher matcher = compile.matcher(version);
        matcher.find();
        MAJOR_VERSION = Integer.valueOf(Integer.parseInt(matcher.group(1)));
        MINOR_VERSION = Integer.valueOf(Integer.parseInt(matcher.group(2)));
        BUGFIX_VERSION = Integer.valueOf(Integer.parseInt(matcher.group(3)));
    }

    private GoogleUtils() {
    }

    public static synchronized KeyStore getCertificateTrustStore() {
        KeyStore keyStore;
        synchronized (GoogleUtils.class) {
            if (f7984b == null) {
                f7984b = SecurityUtils.getJavaKeyStore();
                SecurityUtils.loadKeyStore(f7984b, GoogleUtils.class.getResourceAsStream("google.jks"), "notasecret");
            }
            keyStore = f7984b;
        }
        return keyStore;
    }

    private static String getVersion() {
        String str = null;
        try {
            InputStream resourceAsStream = GoogleUtils.class.getResourceAsStream("google-api-client.properties");
            if (resourceAsStream != null) {
                Properties properties = new Properties();
                properties.load(resourceAsStream);
                str = properties.getProperty("google-api-client.version");
            }
            if (resourceAsStream != null) {
                resourceAsStream.close();
            }
        } catch (IOException unused) {
        }
        return str == null ? "unknown-version" : str;
    }
}
