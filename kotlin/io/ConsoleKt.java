package kotlin.io;

import java.io.InputStream;
import java.nio.charset.Charset;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@JvmName(name = "ConsoleKt")
/* loaded from: classes3.dex */
public final class ConsoleKt {
    @InlineOnly
    private static final void print(byte b2) {
        System.out.print(Byte.valueOf(b2));
    }

    @InlineOnly
    private static final void print(char c2) {
        System.out.print(c2);
    }

    @InlineOnly
    private static final void print(double d2) {
        System.out.print(d2);
    }

    @InlineOnly
    private static final void print(float f2) {
        System.out.print(f2);
    }

    @InlineOnly
    private static final void print(int i2) {
        System.out.print(i2);
    }

    @InlineOnly
    private static final void print(long j2) {
        System.out.print(j2);
    }

    @InlineOnly
    private static final void print(Object obj) {
        System.out.print(obj);
    }

    @InlineOnly
    private static final void print(short s2) {
        System.out.print(Short.valueOf(s2));
    }

    @InlineOnly
    private static final void print(boolean z) {
        System.out.print(z);
    }

    @InlineOnly
    private static final void print(char[] message) {
        Intrinsics.checkNotNullParameter(message, "message");
        System.out.print(message);
    }

    @InlineOnly
    private static final void println() {
        System.out.println();
    }

    @InlineOnly
    private static final void println(byte b2) {
        System.out.println(Byte.valueOf(b2));
    }

    @InlineOnly
    private static final void println(char c2) {
        System.out.println(c2);
    }

    @InlineOnly
    private static final void println(double d2) {
        System.out.println(d2);
    }

    @InlineOnly
    private static final void println(float f2) {
        System.out.println(f2);
    }

    @InlineOnly
    private static final void println(int i2) {
        System.out.println(i2);
    }

    @InlineOnly
    private static final void println(long j2) {
        System.out.println(j2);
    }

    @InlineOnly
    private static final void println(Object obj) {
        System.out.println(obj);
    }

    @InlineOnly
    private static final void println(short s2) {
        System.out.println(Short.valueOf(s2));
    }

    @InlineOnly
    private static final void println(boolean z) {
        System.out.println(z);
    }

    @InlineOnly
    private static final void println(char[] message) {
        Intrinsics.checkNotNullParameter(message, "message");
        System.out.println(message);
    }

    @Nullable
    public static final String readLine() {
        LineReader lineReader = LineReader.INSTANCE;
        InputStream inputStream = System.in;
        Intrinsics.checkNotNullExpressionValue(inputStream, "`in`");
        Charset defaultCharset = Charset.defaultCharset();
        Intrinsics.checkNotNullExpressionValue(defaultCharset, "defaultCharset()");
        return lineReader.readLine(inputStream, defaultCharset);
    }

    @SinceKotlin(version = "1.6")
    @NotNull
    public static final String readln() {
        String readlnOrNull = readlnOrNull();
        if (readlnOrNull != null) {
            return readlnOrNull;
        }
        throw new ReadAfterEOFException("EOF has already been reached");
    }

    @SinceKotlin(version = "1.6")
    @Nullable
    public static final String readlnOrNull() {
        return readLine();
    }
}
