package kotlinx.serialization.json.internal;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class JsonToWriterStringBuilder extends JsonStringBuilder {
    @NotNull
    private final Writer writer;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public JsonToWriterStringBuilder(@NotNull OutputStream os, @NotNull Charset charset) {
        this(r0 instanceof BufferedWriter ? (BufferedWriter) r0 : new BufferedWriter(r0, 262144));
        Intrinsics.checkNotNullParameter(os, "os");
        Intrinsics.checkNotNullParameter(charset, "charset");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os, charset);
    }

    public /* synthetic */ JsonToWriterStringBuilder(OutputStream outputStream, Charset charset, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(outputStream, (i2 & 2) != 0 ? Charsets.UTF_8 : charset);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonToWriterStringBuilder(@NotNull Writer writer) {
        super(new char[16384]);
        Intrinsics.checkNotNullParameter(writer, "writer");
        this.writer = writer;
    }

    static /* synthetic */ void d(JsonToWriterStringBuilder jsonToWriterStringBuilder, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = jsonToWriterStringBuilder.b();
        }
        jsonToWriterStringBuilder.flush(i2);
    }

    private final void flush(int i2) {
        this.writer.write(this.f12457a, 0, i2);
        c(0);
    }

    @Override // kotlinx.serialization.json.internal.JsonStringBuilder
    protected int a(int i2, int i3) {
        int coerceAtLeast;
        int i4 = i2 + i3;
        int length = this.f12457a.length;
        if (length <= i4) {
            flush(i2);
            if (i3 > length) {
                coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(i4, length * 2);
                this.f12457a = new char[coerceAtLeast];
            }
            return 0;
        }
        return i2;
    }

    @Override // kotlinx.serialization.json.internal.JsonStringBuilder
    public void release() {
        d(this, 0, 1, null);
        this.writer.flush();
    }
}
