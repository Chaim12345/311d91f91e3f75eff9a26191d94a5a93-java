package com.appmattus.certificatetransparency.internal.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class ExceptionExtKt {
    @NotNull
    public static final String stringStackTrace(@NotNull Exception exc) {
        Intrinsics.checkNotNullParameter(exc, "<this>");
        StringWriter stringWriter = new StringWriter();
        try {
            PrintWriter printWriter = new PrintWriter(stringWriter);
            exc.printStackTrace(printWriter);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(printWriter, null);
            String stringWriter2 = stringWriter.toString();
            CloseableKt.closeFinally(stringWriter, null);
            Intrinsics.checkNotNullExpressionValue(stringWriter2, "StringWriter().use { str…stringWriter.toString()\n}");
            return stringWriter2;
        } finally {
        }
    }
}
