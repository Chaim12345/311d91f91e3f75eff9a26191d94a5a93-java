package com.psa.mym.mycitroenconnect.utils;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class Logger {
    @NotNull
    public static final Logger INSTANCE = new Logger();
    private static final int TRACE_CALLER_COUNT = 2;
    private static final boolean debug = false;

    private Logger() {
    }

    private final String getClassName() {
        try {
            String className = new Throwable().getStackTrace()[2].getClassName();
            Intrinsics.checkNotNullExpressionValue(className, "Throwable().stackTrace[T…E_CALLER_COUNT].className");
            return className;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private final String getFunctionName() {
        try {
            String methodName = new Throwable().getStackTrace()[2].getMethodName();
            Intrinsics.checkNotNullExpressionValue(methodName, "Throwable().stackTrace[T…_CALLER_COUNT].methodName");
            return methodName;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private final String nonNull(String str) {
        return str == null ? "(null)" : str;
    }

    public final void d() {
    }

    public final void d(@Nullable String str) {
    }

    public final void e(@Nullable String str) {
    }

    public final void e(@Nullable String str, @Nullable Throwable th) {
    }

    public final void i() {
    }

    public final void i(@Nullable String str) {
    }

    public final void v() {
    }

    public final void v(@Nullable String str) {
    }

    public final void w(@Nullable String str) {
    }

    public final void w(@Nullable String str, @Nullable Throwable th) {
    }
}
