package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
@GwtIncompatible
/* loaded from: classes2.dex */
public final class Closeables {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    static final Logger f9307a = Logger.getLogger(Closeables.class.getName());

    private Closeables() {
    }

    public static void close(@NullableDecl Closeable closeable, boolean z) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e2) {
            if (!z) {
                throw e2;
            }
            f9307a.log(Level.WARNING, "IOException thrown while closing Closeable.", (Throwable) e2);
        }
    }

    public static void closeQuietly(@NullableDecl InputStream inputStream) {
        try {
            close(inputStream, true);
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }

    public static void closeQuietly(@NullableDecl Reader reader) {
        try {
            close(reader, true);
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }
}
