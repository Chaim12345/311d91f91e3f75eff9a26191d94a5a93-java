package kotlinx.serialization.json.internal;

import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public class Composer {
    @JvmField
    @NotNull
    public final JsonStringBuilder sb;
    private boolean writingFirst;

    public Composer(@NotNull JsonStringBuilder sb) {
        Intrinsics.checkNotNullParameter(sb, "sb");
        this.sb = sb;
        this.writingFirst = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(boolean z) {
        this.writingFirst = z;
    }

    public final boolean getWritingFirst() {
        return this.writingFirst;
    }

    public void indent() {
        this.writingFirst = true;
    }

    public void nextItem() {
        this.writingFirst = false;
    }

    public void print(byte b2) {
        this.sb.append(b2);
    }

    public final void print(char c2) {
        this.sb.append(c2);
    }

    public void print(double d2) {
        this.sb.append(String.valueOf(d2));
    }

    public void print(float f2) {
        this.sb.append(String.valueOf(f2));
    }

    public void print(int i2) {
        this.sb.append(i2);
    }

    public void print(long j2) {
        this.sb.append(j2);
    }

    public final void print(@NotNull String v) {
        Intrinsics.checkNotNullParameter(v, "v");
        this.sb.append(v);
    }

    public void print(short s2) {
        this.sb.append(s2);
    }

    public void print(boolean z) {
        this.sb.append(String.valueOf(z));
    }

    public final void printQuoted(@NotNull String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.sb.appendQuoted(value);
    }

    public void space() {
    }

    public void unIndent() {
    }
}
