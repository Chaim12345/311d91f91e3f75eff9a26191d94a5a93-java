package kotlinx.serialization.json.internal;

import java.util.Arrays;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public class JsonStringBuilder {
    @JvmField
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    protected char[] f12457a;
    private int size;

    public JsonStringBuilder() {
        this(CharArrayPool.INSTANCE.take());
    }

    public JsonStringBuilder(@NotNull char[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        this.f12457a = array;
    }

    private final void appendStringSlowPath(int i2, int i3, String str) {
        int i4;
        int length = str.length();
        while (i2 < length) {
            int i5 = i2 + 1;
            int a2 = a(i3, 2);
            char charAt = str.charAt(i2);
            if (charAt < StringOpsKt.getESCAPE_MARKERS().length) {
                byte b2 = StringOpsKt.getESCAPE_MARKERS()[charAt];
                if (b2 == 0) {
                    i4 = a2 + 1;
                    this.f12457a[a2] = charAt;
                } else {
                    if (b2 == 1) {
                        String str2 = StringOpsKt.getESCAPE_STRINGS()[charAt];
                        Intrinsics.checkNotNull(str2);
                        int a3 = a(a2, str2.length());
                        str2.getChars(0, str2.length(), this.f12457a, a3);
                        i3 = a3 + str2.length();
                    } else {
                        char[] cArr = this.f12457a;
                        cArr[a2] = '\\';
                        cArr[a2 + 1] = (char) b2;
                        i3 = a2 + 2;
                    }
                    this.size = i3;
                    i2 = i5;
                }
            } else {
                i4 = a2 + 1;
                this.f12457a[a2] = charAt;
            }
            i2 = i5;
            i3 = i4;
        }
        int a4 = a(i3, 1);
        this.f12457a[a4] = '\"';
        this.size = a4 + 1;
    }

    private final void ensureAdditionalCapacity(int i2) {
        a(this.size, i2);
    }

    protected int a(int i2, int i3) {
        int coerceAtLeast;
        int i4 = i3 + i2;
        char[] cArr = this.f12457a;
        if (cArr.length <= i4) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(i4, i2 * 2);
            char[] copyOf = Arrays.copyOf(cArr, coerceAtLeast);
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            this.f12457a = copyOf;
        }
        return i2;
    }

    public final void append(char c2) {
        ensureAdditionalCapacity(1);
        char[] cArr = this.f12457a;
        int i2 = this.size;
        this.size = i2 + 1;
        cArr[i2] = c2;
    }

    public final void append(long j2) {
        append(String.valueOf(j2));
    }

    public final void append(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        int length = string.length();
        ensureAdditionalCapacity(length);
        string.getChars(0, string.length(), this.f12457a, this.size);
        this.size += length;
    }

    public final void appendQuoted(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        ensureAdditionalCapacity(string.length() + 2);
        char[] cArr = this.f12457a;
        int i2 = this.size;
        int i3 = i2 + 1;
        cArr[i2] = '\"';
        int length = string.length();
        string.getChars(0, length, cArr, i3);
        int i4 = length + i3;
        int i5 = i3;
        while (i5 < i4) {
            int i6 = i5 + 1;
            char c2 = cArr[i5];
            if (c2 < StringOpsKt.getESCAPE_MARKERS().length && StringOpsKt.getESCAPE_MARKERS()[c2] != 0) {
                appendStringSlowPath(i5 - i3, i5, string);
                return;
            }
            i5 = i6;
        }
        cArr[i4] = '\"';
        this.size = i4 + 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int b() {
        return this.size;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void c(int i2) {
        this.size = i2;
    }

    public void release() {
        CharArrayPool.INSTANCE.release(this.f12457a);
    }

    @NotNull
    public String toString() {
        return new String(this.f12457a, 0, this.size);
    }
}
