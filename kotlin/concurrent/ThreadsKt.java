package kotlin.concurrent;

import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@JvmName(name = "ThreadsKt")
/* loaded from: classes3.dex */
public final class ThreadsKt {
    @InlineOnly
    private static final <T> T getOrSet(ThreadLocal<T> threadLocal, Function0<? extends T> function0) {
        Intrinsics.checkNotNullParameter(threadLocal, "<this>");
        Intrinsics.checkNotNullParameter(function0, "default");
        T t2 = threadLocal.get();
        if (t2 == null) {
            T invoke = function0.invoke();
            threadLocal.set(invoke);
            return invoke;
        }
        return t2;
    }

    @NotNull
    public static final Thread thread(boolean z, boolean z2, @Nullable ClassLoader classLoader, @Nullable String str, int i2, @NotNull final Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        Thread thread = new Thread() { // from class: kotlin.concurrent.ThreadsKt$thread$thread$1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Function0.this.invoke();
            }
        };
        if (z2) {
            thread.setDaemon(true);
        }
        if (i2 > 0) {
            thread.setPriority(i2);
        }
        if (str != null) {
            thread.setName(str);
        }
        if (classLoader != null) {
            thread.setContextClassLoader(classLoader);
        }
        if (z) {
            thread.start();
        }
        return thread;
    }

    public static /* synthetic */ Thread thread$default(boolean z, boolean z2, ClassLoader classLoader, String str, int i2, Function0 function0, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            z = true;
        }
        boolean z3 = z;
        if ((i3 & 2) != 0) {
            z2 = false;
        }
        boolean z4 = z2;
        ClassLoader classLoader2 = (i3 & 4) != 0 ? null : classLoader;
        String str2 = (i3 & 8) != 0 ? null : str;
        if ((i3 & 16) != 0) {
            i2 = -1;
        }
        return thread(z3, z4, classLoader2, str2, i2, function0);
    }
}
