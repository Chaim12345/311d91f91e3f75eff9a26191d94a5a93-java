package okhttp3.tls.internal.der;

import com.fasterxml.jackson.core.JsonPointer;
import java.net.ProtocolException;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okhttp3.tls.internal.der.DerAdapter;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class BasicDerAdapter<T> implements DerAdapter<T> {
    @NotNull
    private final Codec<T> codec;
    @Nullable
    private final T defaultValue;
    private final boolean isOptional;
    @NotNull
    private final String name;
    private final long tag;
    private final int tagClass;
    private final boolean typeHint;

    /* loaded from: classes3.dex */
    public interface Codec<T> {
        T decode(@NotNull DerReader derReader);

        void encode(@NotNull DerWriter derWriter, T t2);
    }

    public BasicDerAdapter(@NotNull String name, int i2, long j2, @NotNull Codec<T> codec, boolean z, @Nullable T t2, boolean z2) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(codec, "codec");
        this.name = name;
        this.tagClass = i2;
        this.tag = j2;
        this.codec = codec;
        this.isOptional = z;
        this.defaultValue = t2;
        this.typeHint = z2;
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(j2 >= 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public /* synthetic */ BasicDerAdapter(String str, int i2, long j2, Codec codec, boolean z, Object obj, boolean z2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i2, j2, codec, (i3 & 16) != 0 ? false : z, (i3 & 32) != 0 ? null : obj, (i3 & 64) != 0 ? false : z2);
    }

    private final String component1() {
        return this.name;
    }

    private final Codec<T> component4() {
        return this.codec;
    }

    private final boolean component7() {
        return this.typeHint;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BasicDerAdapter copy$default(BasicDerAdapter basicDerAdapter, String str, int i2, long j2, Codec codec, boolean z, Object obj, boolean z2, int i3, Object obj2) {
        return basicDerAdapter.copy((i3 & 1) != 0 ? basicDerAdapter.name : str, (i3 & 2) != 0 ? basicDerAdapter.tagClass : i2, (i3 & 4) != 0 ? basicDerAdapter.tag : j2, (i3 & 8) != 0 ? basicDerAdapter.codec : codec, (i3 & 16) != 0 ? basicDerAdapter.isOptional : z, (i3 & 32) != 0 ? basicDerAdapter.defaultValue : obj, (i3 & 64) != 0 ? basicDerAdapter.typeHint : z2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BasicDerAdapter optional$default(BasicDerAdapter basicDerAdapter, Object obj, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            obj = null;
        }
        return basicDerAdapter.optional(obj);
    }

    public static /* synthetic */ BasicDerAdapter withTag$default(BasicDerAdapter basicDerAdapter, int i2, long j2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = 128;
        }
        return basicDerAdapter.withTag(i2, j2);
    }

    @Override // okhttp3.tls.internal.der.DerAdapter
    @NotNull
    public BasicDerAdapter<List<T>> asSequenceOf(@NotNull String str, int i2, long j2) {
        return DerAdapter.DefaultImpls.asSequenceOf(this, str, i2, j2);
    }

    @Override // okhttp3.tls.internal.der.DerAdapter
    @NotNull
    public BasicDerAdapter<List<T>> asSetOf() {
        return DerAdapter.DefaultImpls.asSetOf(this);
    }

    @NotNull
    public final BasicDerAdapter<T> asTypeHint() {
        return copy$default(this, null, 0, 0L, null, false, null, true, 63, null);
    }

    public final int component2() {
        return this.tagClass;
    }

    public final long component3() {
        return this.tag;
    }

    public final boolean component5() {
        return this.isOptional;
    }

    @Nullable
    public final T component6() {
        return this.defaultValue;
    }

    @NotNull
    public final BasicDerAdapter<T> copy(@NotNull String name, int i2, long j2, @NotNull Codec<T> codec, boolean z, @Nullable T t2, boolean z2) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(codec, "codec");
        return new BasicDerAdapter<>(name, i2, j2, codec, z, t2, z2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BasicDerAdapter) {
            BasicDerAdapter basicDerAdapter = (BasicDerAdapter) obj;
            return Intrinsics.areEqual(this.name, basicDerAdapter.name) && this.tagClass == basicDerAdapter.tagClass && this.tag == basicDerAdapter.tag && Intrinsics.areEqual(this.codec, basicDerAdapter.codec) && this.isOptional == basicDerAdapter.isOptional && Intrinsics.areEqual(this.defaultValue, basicDerAdapter.defaultValue) && this.typeHint == basicDerAdapter.typeHint;
        }
        return false;
    }

    @Override // okhttp3.tls.internal.der.DerAdapter
    public T fromDer(@NotNull DerReader reader) {
        Intrinsics.checkNotNullParameter(reader, "reader");
        DerHeader peekHeader = reader.peekHeader();
        if (peekHeader == null || peekHeader.getTagClass() != this.tagClass || peekHeader.getTag() != this.tag) {
            if (this.isOptional) {
                return this.defaultValue;
            }
            throw new ProtocolException("expected " + this + " but was " + peekHeader + " at " + reader);
        }
        String str = this.name;
        if (reader.hasNext()) {
            DerHeader access$getPeekedHeader$p = DerReader.access$getPeekedHeader$p(reader);
            Intrinsics.checkNotNull(access$getPeekedHeader$p);
            DerReader.access$setPeekedHeader$p(reader, null);
            long access$getLimit$p = DerReader.access$getLimit$p(reader);
            boolean access$getConstructed$p = DerReader.access$getConstructed$p(reader);
            long access$getByteCount = access$getPeekedHeader$p.getLength() != -1 ? DerReader.access$getByteCount(reader) + access$getPeekedHeader$p.getLength() : -1L;
            if (access$getLimit$p == -1 || access$getByteCount <= access$getLimit$p) {
                DerReader.access$setLimit$p(reader, access$getByteCount);
                DerReader.access$setConstructed$p(reader, access$getPeekedHeader$p.getConstructed());
                if (str != null) {
                    DerReader.access$getPath$p(reader).add(str);
                }
                try {
                    T decode = this.codec.decode(reader);
                    if (access$getByteCount != -1 && DerReader.access$getByteCount(reader) > access$getByteCount) {
                        throw new ProtocolException(Intrinsics.stringPlus("unexpected byte count at ", reader));
                    }
                    if (this.typeHint) {
                        reader.setTypeHint(decode);
                    }
                    return decode;
                } finally {
                    DerReader.access$setPeekedHeader$p(reader, null);
                    DerReader.access$setLimit$p(reader, access$getLimit$p);
                    DerReader.access$setConstructed$p(reader, access$getConstructed$p);
                    if (str != null) {
                        DerReader.access$getPath$p(reader).remove(DerReader.access$getPath$p(reader).size() - 1);
                    }
                }
            }
            throw new ProtocolException("enclosed object too large");
        }
        throw new ProtocolException("expected a value");
    }

    @Override // okhttp3.tls.internal.der.DerAdapter
    public T fromDer(@NotNull ByteString byteString) {
        return (T) DerAdapter.DefaultImpls.fromDer(this, byteString);
    }

    @Nullable
    public final T getDefaultValue() {
        return this.defaultValue;
    }

    public final long getTag() {
        return this.tag;
    }

    public final int getTagClass() {
        return this.tagClass;
    }

    public int hashCode() {
        int hashCode = (((((((((this.name.hashCode() + 0) * 31) + this.tagClass) * 31) + ((int) this.tag)) * 31) + this.codec.hashCode()) * 31) + (this.isOptional ? 1 : 0)) * 31;
        T t2 = this.defaultValue;
        return ((hashCode + (t2 != null ? t2.hashCode() : 0)) * 31) + (this.typeHint ? 1 : 0);
    }

    public final boolean isOptional() {
        return this.isOptional;
    }

    @Override // okhttp3.tls.internal.der.DerAdapter
    public boolean matches(@NotNull DerHeader header) {
        Intrinsics.checkNotNullParameter(header, "header");
        return header.getTagClass() == this.tagClass && header.getTag() == this.tag;
    }

    @NotNull
    public final BasicDerAdapter<T> optional(@Nullable T t2) {
        return copy$default(this, null, 0, 0L, null, true, t2, false, 79, null);
    }

    @Override // okhttp3.tls.internal.der.DerAdapter
    @NotNull
    public ByteString toDer(T t2) {
        return DerAdapter.DefaultImpls.toDer(this, t2);
    }

    @Override // okhttp3.tls.internal.der.DerAdapter
    public void toDer(@NotNull DerWriter writer, T t2) {
        Intrinsics.checkNotNullParameter(writer, "writer");
        if (this.typeHint) {
            writer.setTypeHint(t2);
        }
        if (this.isOptional && Intrinsics.areEqual(t2, this.defaultValue)) {
            return;
        }
        writer.write(this.name, this.tagClass, this.tag, new BasicDerAdapter$toDer$1(this, writer, t2));
    }

    @NotNull
    public String toString() {
        return this.name + " [" + this.tagClass + JsonPointer.SEPARATOR + this.tag + AbstractJsonLexerKt.END_LIST;
    }

    @Override // okhttp3.tls.internal.der.DerAdapter
    @NotNull
    public BasicDerAdapter<T> withExplicitBox(int i2, long j2, @Nullable Boolean bool) {
        return DerAdapter.DefaultImpls.withExplicitBox(this, i2, j2, bool);
    }

    @NotNull
    public final BasicDerAdapter<T> withTag(int i2, long j2) {
        return copy$default(this, null, i2, j2, null, false, null, false, 121, null);
    }
}
