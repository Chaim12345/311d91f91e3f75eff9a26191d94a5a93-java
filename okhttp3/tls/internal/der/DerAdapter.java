package okhttp3.tls.internal.der;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.tls.internal.der.BasicDerAdapter;
import okio.Buffer;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public interface DerAdapter<T> {

    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        @NotNull
        public static <T> BasicDerAdapter<List<T>> asSequenceOf(@NotNull final DerAdapter<T> derAdapter, @NotNull String name, int i2, long j2) {
            Intrinsics.checkNotNullParameter(derAdapter, "this");
            Intrinsics.checkNotNullParameter(name, "name");
            return new BasicDerAdapter<>(name, i2, j2, new BasicDerAdapter.Codec<List<? extends T>>() { // from class: okhttp3.tls.internal.der.DerAdapter$asSequenceOf$codec$1
                @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
                @NotNull
                public List<T> decode(@NotNull DerReader reader) {
                    Intrinsics.checkNotNullParameter(reader, "reader");
                    ArrayList arrayList = new ArrayList();
                    while (reader.hasNext()) {
                        arrayList.add(DerAdapter.this.fromDer(reader));
                    }
                    return arrayList;
                }

                @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
                public void encode(@NotNull DerWriter writer, @NotNull List<? extends T> value) {
                    Intrinsics.checkNotNullParameter(writer, "writer");
                    Intrinsics.checkNotNullParameter(value, "value");
                    Iterator it = value.iterator();
                    while (it.hasNext()) {
                        DerAdapter.this.toDer(writer, it.next());
                    }
                }
            }, false, null, false, 112, null);
        }

        public static /* synthetic */ BasicDerAdapter asSequenceOf$default(DerAdapter derAdapter, String str, int i2, long j2, int i3, Object obj) {
            if (obj == null) {
                if ((i3 & 1) != 0) {
                    str = "SEQUENCE OF";
                }
                if ((i3 & 2) != 0) {
                    i2 = 0;
                }
                if ((i3 & 4) != 0) {
                    j2 = 16;
                }
                return derAdapter.asSequenceOf(str, i2, j2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: asSequenceOf");
        }

        @NotNull
        public static <T> BasicDerAdapter<List<T>> asSetOf(@NotNull DerAdapter<T> derAdapter) {
            Intrinsics.checkNotNullParameter(derAdapter, "this");
            return derAdapter.asSequenceOf("SET OF", 0, 17L);
        }

        public static <T> T fromDer(@NotNull DerAdapter<T> derAdapter, @NotNull ByteString byteString) {
            Intrinsics.checkNotNullParameter(derAdapter, "this");
            Intrinsics.checkNotNullParameter(byteString, "byteString");
            return derAdapter.fromDer(new DerReader(new Buffer().write(byteString)));
        }

        @NotNull
        public static <T> ByteString toDer(@NotNull DerAdapter<T> derAdapter, T t2) {
            Intrinsics.checkNotNullParameter(derAdapter, "this");
            Buffer buffer = new Buffer();
            derAdapter.toDer(new DerWriter(buffer), t2);
            return buffer.readByteString();
        }

        @NotNull
        public static <T> BasicDerAdapter<T> withExplicitBox(@NotNull final DerAdapter<T> derAdapter, int i2, long j2, @Nullable final Boolean bool) {
            Intrinsics.checkNotNullParameter(derAdapter, "this");
            return new BasicDerAdapter<>("EXPLICIT", i2, j2, new BasicDerAdapter.Codec<T>() { // from class: okhttp3.tls.internal.der.DerAdapter$withExplicitBox$codec$1
                /* JADX WARN: Type inference failed for: r2v1, types: [T, java.lang.Object] */
                @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
                public T decode(@NotNull DerReader reader) {
                    Intrinsics.checkNotNullParameter(reader, "reader");
                    return DerAdapter.this.fromDer(reader);
                }

                @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
                public void encode(@NotNull DerWriter writer, T t2) {
                    Intrinsics.checkNotNullParameter(writer, "writer");
                    DerAdapter.this.toDer(writer, t2);
                    Boolean bool2 = bool;
                    if (bool2 != null) {
                        writer.setConstructed(bool2.booleanValue());
                    }
                }
            }, false, null, false, 112, null);
        }

        public static /* synthetic */ BasicDerAdapter withExplicitBox$default(DerAdapter derAdapter, int i2, long j2, Boolean bool, int i3, Object obj) {
            if (obj == null) {
                if ((i3 & 1) != 0) {
                    i2 = 128;
                }
                if ((i3 & 4) != 0) {
                    bool = null;
                }
                return derAdapter.withExplicitBox(i2, j2, bool);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: withExplicitBox");
        }
    }

    @NotNull
    BasicDerAdapter<List<T>> asSequenceOf(@NotNull String str, int i2, long j2);

    @NotNull
    BasicDerAdapter<List<T>> asSetOf();

    T fromDer(@NotNull DerReader derReader);

    T fromDer(@NotNull ByteString byteString);

    boolean matches(@NotNull DerHeader derHeader);

    @NotNull
    ByteString toDer(T t2);

    void toDer(@NotNull DerWriter derWriter, T t2);

    @NotNull
    BasicDerAdapter<T> withExplicitBox(int i2, long j2, @Nullable Boolean bool);
}
