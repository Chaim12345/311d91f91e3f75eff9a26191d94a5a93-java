package com.google.firebase.crashlytics.internal;

import android.util.Log;
/* loaded from: classes2.dex */
public class Logger {
    public static final String TAG = "FirebaseCrashlytics";

    /* renamed from: a  reason: collision with root package name */
    static final Logger f9915a = new Logger(TAG);
    private int logLevel = 4;
    private final String tag;

    public Logger(String str) {
        this.tag = str;
    }

    private boolean canLog(int i2) {
        return this.logLevel <= i2 || Log.isLoggable(this.tag, i2);
    }

    public static Logger getLogger() {
        return f9915a;
    }

    public void d(String str) {
        d(str, null);
    }

    public void d(String str, Throwable th) {
        canLog(3);
    }

    public void e(String str) {
        e(str, null);
    }

    public void e(String str, Throwable th) {
        if (canLog(6)) {
            Log.e(this.tag, str, th);
        }
    }

    public void i(String str) {
        i(str, null);
    }

    public void i(String str, Throwable th) {
        canLog(4);
    }

    public void log(int i2, String str) {
        log(i2, str, false);
    }

    public void log(int i2, String str, boolean z) {
        if (z || canLog(i2)) {
            Log.println(i2, this.tag, str);
        }
    }

    public void v(String str) {
        v(str, null);
    }

    public void v(String str, Throwable th) {
        canLog(2);
    }

    public void w(String str) {
        w(str, null);
    }

    public void w(String str, Throwable th) {
        canLog(5);
    }
}
