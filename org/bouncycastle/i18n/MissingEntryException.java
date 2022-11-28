package org.bouncycastle.i18n;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
/* loaded from: classes3.dex */
public class MissingEntryException extends RuntimeException {

    /* renamed from: a  reason: collision with root package name */
    protected final String f13595a;

    /* renamed from: b  reason: collision with root package name */
    protected final String f13596b;

    /* renamed from: c  reason: collision with root package name */
    protected final ClassLoader f13597c;

    /* renamed from: d  reason: collision with root package name */
    protected final Locale f13598d;
    private String debugMsg;

    public MissingEntryException(String str, String str2, String str3, Locale locale, ClassLoader classLoader) {
        super(str);
        this.f13595a = str2;
        this.f13596b = str3;
        this.f13598d = locale;
        this.f13597c = classLoader;
    }

    public MissingEntryException(String str, Throwable th, String str2, String str3, Locale locale, ClassLoader classLoader) {
        super(str, th);
        this.f13595a = str2;
        this.f13596b = str3;
        this.f13598d = locale;
        this.f13597c = classLoader;
    }

    public ClassLoader getClassLoader() {
        return this.f13597c;
    }

    public String getDebugMsg() {
        URL[] uRLs;
        if (this.debugMsg == null) {
            this.debugMsg = "Can not find entry " + this.f13596b + " in resource file " + this.f13595a + " for the locale " + this.f13598d + ".";
            ClassLoader classLoader = this.f13597c;
            if (classLoader instanceof URLClassLoader) {
                this.debugMsg += " The following entries in the classpath were searched: ";
                for (int i2 = 0; i2 != ((URLClassLoader) classLoader).getURLs().length; i2++) {
                    this.debugMsg += uRLs[i2] + " ";
                }
            }
        }
        return this.debugMsg;
    }

    public String getKey() {
        return this.f13596b;
    }

    public Locale getLocale() {
        return this.f13598d;
    }

    public String getResource() {
        return this.f13595a;
    }
}
