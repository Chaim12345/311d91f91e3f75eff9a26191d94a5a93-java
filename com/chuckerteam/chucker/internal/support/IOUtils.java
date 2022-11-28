package com.chuckerteam.chucker.internal.support;

import android.content.Context;
import com.chuckerteam.chucker.R;
import java.io.EOFException;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import okio.Buffer;
import okio.BufferedSource;
import okio.GzipSource;
import okio.Okio;
import org.apache.http.protocol.HTTP;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0015\u0010\u0016J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u001e\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bJ\u0016\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0004J\u0010\u0010\u0011\u001a\u00020\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\nR\u0016\u0010\u0013\u001a\u00020\u00128\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014¨\u0006\u0017"}, d2 = {"Lcom/chuckerteam/chucker/internal/support/IOUtils;", "", "Lokio/Buffer;", "buffer", "", "isPlaintext", "Ljava/nio/charset/Charset;", "charset", "", "maxContentLength", "", "readFromBuffer", "Lokio/BufferedSource;", "input", "isGzipped", "getNativeSource", "contentEncoding", "bodyHasSupportedEncoding", "Landroid/content/Context;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class IOUtils {
    private final Context context;

    public IOUtils(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public final boolean bodyHasSupportedEncoding(@Nullable String str) {
        boolean equals;
        boolean equals2;
        if (!(str == null || str.length() == 0)) {
            equals = StringsKt__StringsJVMKt.equals(str, HTTP.IDENTITY_CODING, true);
            if (!equals) {
                equals2 = StringsKt__StringsJVMKt.equals(str, "gzip", true);
                if (!equals2) {
                    return false;
                }
            }
        }
        return true;
    }

    @NotNull
    public final BufferedSource getNativeSource(@NotNull BufferedSource input, boolean z) {
        Intrinsics.checkNotNullParameter(input, "input");
        if (z) {
            GzipSource gzipSource = new GzipSource(input);
            try {
                BufferedSource buffer = Okio.buffer(gzipSource);
                CloseableKt.closeFinally(gzipSource, null);
                Intrinsics.checkNotNullExpressionValue(buffer, "source.use { Okio.buffer(it) }");
                return buffer;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(gzipSource, th);
                    throw th2;
                }
            }
        }
        return input;
    }

    public final boolean isPlaintext(@NotNull Buffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        try {
            Buffer buffer2 = new Buffer();
            buffer.copyTo(buffer2, 0L, buffer.size() < 64 ? buffer.size() : 64L);
            for (int i2 = 0; i2 < 16; i2++) {
                if (buffer2.exhausted()) {
                    return true;
                }
                int readUtf8CodePoint = buffer2.readUtf8CodePoint();
                if (Character.isISOControl(readUtf8CodePoint) && !Character.isWhitespace(readUtf8CodePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }

    @NotNull
    public final String readFromBuffer(@NotNull Buffer buffer, @NotNull Charset charset, long j2) {
        String str;
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Intrinsics.checkNotNullParameter(charset, "charset");
        long size = buffer.size();
        try {
            str = buffer.readString(Math.min(size, j2), charset);
            Intrinsics.checkNotNullExpressionValue(str, "buffer.readString(maxBytes, charset)");
        } catch (EOFException unused) {
            str = "" + this.context.getString(R.string.chucker_body_unexpected_eof);
        }
        if (size > j2) {
            return str + this.context.getString(R.string.chucker_body_content_truncated);
        }
        return str;
    }
}
