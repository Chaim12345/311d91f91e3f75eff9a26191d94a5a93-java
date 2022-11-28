package okhttp3.tls.internal.der;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class DerWriter {
    private boolean constructed;
    @NotNull
    private final List<String> path;
    @NotNull
    private final List<BufferedSink> stack;
    @NotNull
    private final List<Object> typeHintStack;

    public DerWriter(@NotNull BufferedSink sink) {
        List<BufferedSink> mutableListOf;
        Intrinsics.checkNotNullParameter(sink, "sink");
        mutableListOf = CollectionsKt__CollectionsKt.mutableListOf(sink);
        this.stack = mutableListOf;
        this.typeHintStack = new ArrayList();
        this.path = new ArrayList();
    }

    private final BufferedSink sink() {
        List<BufferedSink> list = this.stack;
        return list.get(list.size() - 1);
    }

    private final void writeVariableLengthLong(long j2) {
        BufferedSink sink = sink();
        int numberOfLeadingZeros = ((((64 - Long.numberOfLeadingZeros(j2)) + 6) / 7) - 1) * 7;
        int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(numberOfLeadingZeros, 0, -7);
        if (progressionLastElement > numberOfLeadingZeros) {
            return;
        }
        while (true) {
            int i2 = numberOfLeadingZeros - 7;
            sink.writeByte((numberOfLeadingZeros == 0 ? 0 : 128) | ((int) ((j2 >> numberOfLeadingZeros) & 127)));
            if (numberOfLeadingZeros == progressionLastElement) {
                return;
            }
            numberOfLeadingZeros = i2;
        }
    }

    public final boolean getConstructed() {
        return this.constructed;
    }

    @Nullable
    public final Object getTypeHint() {
        Object lastOrNull;
        lastOrNull = CollectionsKt___CollectionsKt.lastOrNull((List<? extends Object>) this.typeHintStack);
        return lastOrNull;
    }

    public final void setConstructed(boolean z) {
        this.constructed = z;
    }

    public final void setTypeHint(@Nullable Object obj) {
        List<Object> list = this.typeHintStack;
        list.set(list.size() - 1, obj);
    }

    @NotNull
    public String toString() {
        String joinToString$default;
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(this.path, " / ", null, null, 0, null, null, 62, null);
        return joinToString$default;
    }

    public final <T> T withTypeHint(@NotNull Function0<? extends T> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.typeHintStack.add(null);
        try {
            T invoke = block.invoke();
            List<Object> list = this.typeHintStack;
            list.remove(list.size() - 1);
            return invoke;
        } catch (Throwable th) {
            this.typeHintStack.remove(this.typeHintStack.size() - 1);
            throw th;
        }
    }

    public final void write(@NotNull String name, int i2, long j2, @NotNull Function1<? super BufferedSink, Unit> block) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(block, "block");
        Buffer buffer = new Buffer();
        this.stack.add(buffer);
        this.constructed = false;
        this.path.add(name);
        try {
            block.invoke(buffer);
            int i3 = this.constructed ? 32 : 0;
            this.constructed = true;
            List<BufferedSink> list = this.stack;
            list.remove(list.size() - 1);
            List<String> list2 = this.path;
            list2.remove(list2.size() - 1);
            BufferedSink sink = sink();
            int i4 = i2 | i3;
            if (j2 < 31) {
                sink.writeByte(i4 | ((int) j2));
            } else {
                sink.writeByte(i4 | 31);
                writeVariableLengthLong(j2);
            }
            long size = buffer.size();
            if (size < 128) {
                sink.writeByte((int) size);
            } else {
                int numberOfLeadingZeros = ((64 - Long.numberOfLeadingZeros(size)) + 7) / 8;
                sink.writeByte(numberOfLeadingZeros | 128);
                int i5 = (numberOfLeadingZeros - 1) * 8;
                int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(i5, 0, -8);
                if (progressionLastElement <= i5) {
                    while (true) {
                        int i6 = i5 - 8;
                        sink.writeByte((int) (size >> i5));
                        if (i5 == progressionLastElement) {
                            break;
                        }
                        i5 = i6;
                    }
                }
            }
            sink.writeAll(buffer);
        } catch (Throwable th) {
            List<BufferedSink> list3 = this.stack;
            list3.remove(list3.size() - 1);
            List<String> list4 = this.path;
            list4.remove(list4.size() - 1);
            throw th;
        }
    }

    public final void writeBigInteger(@NotNull BigInteger value) {
        Intrinsics.checkNotNullParameter(value, "value");
        BufferedSink sink = sink();
        byte[] byteArray = value.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray, "value.toByteArray()");
        sink.write(byteArray);
    }

    public final void writeBitString(@NotNull BitString bitString) {
        Intrinsics.checkNotNullParameter(bitString, "bitString");
        BufferedSink sink = sink();
        sink.writeByte(bitString.getUnusedBitsCount());
        sink.write(bitString.getByteString());
    }

    public final void writeBoolean(boolean z) {
        sink().writeByte(z ? -1 : 0);
    }

    public final void writeLong(long j2) {
        BufferedSink sink = sink();
        int numberOfLeadingZeros = ((((65 - (j2 < 0 ? Long.numberOfLeadingZeros(~j2) : Long.numberOfLeadingZeros(j2))) + 7) / 8) - 1) * 8;
        int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(numberOfLeadingZeros, 0, -8);
        if (progressionLastElement > numberOfLeadingZeros) {
            return;
        }
        while (true) {
            int i2 = numberOfLeadingZeros - 8;
            sink.writeByte((int) (j2 >> numberOfLeadingZeros));
            if (numberOfLeadingZeros == progressionLastElement) {
                return;
            }
            numberOfLeadingZeros = i2;
        }
    }

    public final void writeObjectIdentifier(@NotNull String s2) {
        Intrinsics.checkNotNullParameter(s2, "s");
        Buffer writeUtf8 = new Buffer().writeUtf8(s2);
        long readDecimalLong = writeUtf8.readDecimalLong();
        if (!(writeUtf8.readByte() == 46)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        long readDecimalLong2 = (readDecimalLong * 40) + writeUtf8.readDecimalLong();
        while (true) {
            writeVariableLengthLong(readDecimalLong2);
            if (writeUtf8.exhausted()) {
                return;
            }
            if (!(writeUtf8.readByte() == 46)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            readDecimalLong2 = writeUtf8.readDecimalLong();
        }
    }

    public final void writeOctetString(@NotNull ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        sink().write(byteString);
    }

    public final void writeRelativeObjectIdentifier(@NotNull String s2) {
        Intrinsics.checkNotNullParameter(s2, "s");
        Buffer writeUtf8 = new Buffer().writeByte(46).writeUtf8(s2);
        while (!writeUtf8.exhausted()) {
            if (!(writeUtf8.readByte() == 46)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            writeVariableLengthLong(writeUtf8.readDecimalLong());
        }
    }

    public final void writeUtf8(@NotNull String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        sink().writeUtf8(value);
    }
}
