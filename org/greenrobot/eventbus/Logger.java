package org.greenrobot.eventbus;

import android.os.Looper;
import java.io.PrintStream;
import java.util.logging.Level;
import org.greenrobot.eventbus.android.AndroidLogger;
/* loaded from: classes4.dex */
public interface Logger {

    /* loaded from: classes4.dex */
    public static class Default {
        static Object a() {
            try {
                return Looper.getMainLooper();
            } catch (RuntimeException unused) {
                return null;
            }
        }

        public static Logger get() {
            return (!AndroidLogger.isAndroidLogAvailable() || a() == null) ? new SystemOutLogger() : new AndroidLogger("EventBus");
        }
    }

    /* loaded from: classes4.dex */
    public static class JavaLogger implements Logger {

        /* renamed from: a  reason: collision with root package name */
        protected final java.util.logging.Logger f15173a;

        public JavaLogger(String str) {
            this.f15173a = java.util.logging.Logger.getLogger(str);
        }

        @Override // org.greenrobot.eventbus.Logger
        public void log(Level level, String str) {
            this.f15173a.log(level, str);
        }

        @Override // org.greenrobot.eventbus.Logger
        public void log(Level level, String str, Throwable th) {
            this.f15173a.log(level, str, th);
        }
    }

    /* loaded from: classes4.dex */
    public static class SystemOutLogger implements Logger {
        @Override // org.greenrobot.eventbus.Logger
        public void log(Level level, String str) {
            PrintStream printStream = System.out;
            printStream.println("[" + level + "] " + str);
        }

        @Override // org.greenrobot.eventbus.Logger
        public void log(Level level, String str, Throwable th) {
            PrintStream printStream = System.out;
            printStream.println("[" + level + "] " + str);
            th.printStackTrace(System.out);
        }
    }

    void log(Level level, String str);

    void log(Level level, String str, Throwable th);
}
