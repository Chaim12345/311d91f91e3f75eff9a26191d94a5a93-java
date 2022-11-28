package com.chuckerteam.chucker.internal.support;

import com.chuckerteam.chucker.api.ChuckerCollector;
import java.lang.Thread;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00018\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\tR\u0016\u0010\u000b\u001a\u00020\n8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/chuckerteam/chucker/internal/support/ChuckerCrashHandler;", "Ljava/lang/Thread$UncaughtExceptionHandler;", "Ljava/lang/Thread;", "thread", "", "throwable", "", "uncaughtException", "defaultHandler", "Ljava/lang/Thread$UncaughtExceptionHandler;", "Lcom/chuckerteam/chucker/api/ChuckerCollector;", "collector", "Lcom/chuckerteam/chucker/api/ChuckerCollector;", "<init>", "(Lcom/chuckerteam/chucker/api/ChuckerCollector;)V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class ChuckerCrashHandler implements Thread.UncaughtExceptionHandler {
    private final ChuckerCollector collector;
    private final Thread.UncaughtExceptionHandler defaultHandler;

    public ChuckerCrashHandler(@NotNull ChuckerCollector collector) {
        Intrinsics.checkNotNullParameter(collector, "collector");
        this.collector = collector;
        this.defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(@NotNull Thread thread, @NotNull Throwable throwable) {
        Intrinsics.checkNotNullParameter(thread, "thread");
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        ChuckerCollector chuckerCollector = this.collector;
        chuckerCollector.onError("Error caught on " + thread.getName() + " thread", throwable);
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.defaultHandler;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, throwable);
        }
    }
}
