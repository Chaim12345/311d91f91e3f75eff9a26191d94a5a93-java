package kotlinx.serialization.internal;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.modules.SerializersModule;
import kotlinx.serialization.modules.SerializersModuleKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@InternalSerializationApi
/* loaded from: classes3.dex */
public abstract class TaggedEncoder<Tag> implements Encoder, CompositeEncoder {
    @NotNull
    private final ArrayList<Tag> tagStack = new ArrayList<>();

    private final boolean encodeElement(SerialDescriptor serialDescriptor, int i2) {
        e(getTag(serialDescriptor, i2));
        return true;
    }

    protected void a(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Object b() {
        Object last;
        last = CollectionsKt___CollectionsKt.last((List<? extends Object>) this.tagStack);
        return last;
    }

    @Override // kotlinx.serialization.encoding.Encoder
    @NotNull
    public CompositeEncoder beginCollection(@NotNull SerialDescriptor serialDescriptor, int i2) {
        return Encoder.DefaultImpls.beginCollection(this, serialDescriptor, i2);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    @NotNull
    public CompositeEncoder beginStructure(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public final Object c() {
        Object lastOrNull;
        lastOrNull = CollectionsKt___CollectionsKt.lastOrNull((List<? extends Object>) this.tagStack);
        return lastOrNull;
    }

    protected final Object d() {
        int lastIndex;
        if (!this.tagStack.isEmpty()) {
            ArrayList<Tag> arrayList = this.tagStack;
            lastIndex = CollectionsKt__CollectionsKt.getLastIndex(arrayList);
            return arrayList.remove(lastIndex);
        }
        throw new SerializationException("No tag in stack for requested element");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void e(Object obj) {
        this.tagStack.add(obj);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeBoolean(boolean z) {
        encodeTaggedBoolean(d(), z);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeBooleanElement(@NotNull SerialDescriptor descriptor, int i2, boolean z) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeTaggedBoolean(getTag(descriptor, i2), z);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeByte(byte b2) {
        encodeTaggedByte(d(), b2);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeByteElement(@NotNull SerialDescriptor descriptor, int i2, byte b2) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeTaggedByte(getTag(descriptor, i2), b2);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeChar(char c2) {
        encodeTaggedChar(d(), c2);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeCharElement(@NotNull SerialDescriptor descriptor, int i2, char c2) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeTaggedChar(getTag(descriptor, i2), c2);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeDouble(double d2) {
        encodeTaggedDouble(d(), d2);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeDoubleElement(@NotNull SerialDescriptor descriptor, int i2, double d2) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeTaggedDouble(getTag(descriptor, i2), d2);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeEnum(@NotNull SerialDescriptor enumDescriptor, int i2) {
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        encodeTaggedEnum(d(), enumDescriptor, i2);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeFloat(float f2) {
        encodeTaggedFloat(d(), f2);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeFloatElement(@NotNull SerialDescriptor descriptor, int i2, float f2) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeTaggedFloat(getTag(descriptor, i2), f2);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    @NotNull
    public final Encoder encodeInline(@NotNull SerialDescriptor inlineDescriptor) {
        Intrinsics.checkNotNullParameter(inlineDescriptor, "inlineDescriptor");
        return encodeTaggedInline(d(), inlineDescriptor);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    @NotNull
    public final Encoder encodeInlineElement(@NotNull SerialDescriptor descriptor, int i2) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return encodeTaggedInline(getTag(descriptor, i2), descriptor.getElementDescriptor(i2));
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeInt(int i2) {
        encodeTaggedInt(d(), i2);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeIntElement(@NotNull SerialDescriptor descriptor, int i2, int i3) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeTaggedInt(getTag(descriptor, i2), i3);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeLong(long j2) {
        encodeTaggedLong(d(), j2);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeLongElement(@NotNull SerialDescriptor descriptor, int i2, long j2) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeTaggedLong(getTag(descriptor, i2), j2);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeNotNullMark() {
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeNull() {
        encodeTaggedNull(d());
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public <T> void encodeNullableSerializableElement(@NotNull SerialDescriptor descriptor, int i2, @NotNull SerializationStrategy<? super T> serializer, @Nullable T t2) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        if (encodeElement(descriptor, i2)) {
            encodeNullableSerializableValue(serializer, t2);
        }
    }

    @Override // kotlinx.serialization.encoding.Encoder
    @ExperimentalSerializationApi
    public <T> void encodeNullableSerializableValue(@NotNull SerializationStrategy<? super T> serializationStrategy, @Nullable T t2) {
        Encoder.DefaultImpls.encodeNullableSerializableValue(this, serializationStrategy, t2);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public <T> void encodeSerializableElement(@NotNull SerialDescriptor descriptor, int i2, @NotNull SerializationStrategy<? super T> serializer, T t2) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        if (encodeElement(descriptor, i2)) {
            encodeSerializableValue(serializer, t2);
        }
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public <T> void encodeSerializableValue(@NotNull SerializationStrategy<? super T> serializationStrategy, T t2) {
        Encoder.DefaultImpls.encodeSerializableValue(this, serializationStrategy, t2);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeShort(short s2) {
        encodeTaggedShort(d(), s2);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeShortElement(@NotNull SerialDescriptor descriptor, int i2, short s2) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        encodeTaggedShort(getTag(descriptor, i2), s2);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public final void encodeString(@NotNull String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        encodeTaggedString(d(), value);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeStringElement(@NotNull SerialDescriptor descriptor, int i2, @NotNull String value) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(value, "value");
        encodeTaggedString(getTag(descriptor, i2), value);
    }

    protected void encodeTaggedBoolean(Object obj, boolean z) {
        encodeTaggedValue(obj, Boolean.valueOf(z));
    }

    protected void encodeTaggedByte(Object obj, byte b2) {
        encodeTaggedValue(obj, Byte.valueOf(b2));
    }

    protected void encodeTaggedChar(Object obj, char c2) {
        encodeTaggedValue(obj, Character.valueOf(c2));
    }

    protected void encodeTaggedDouble(Object obj, double d2) {
        encodeTaggedValue(obj, Double.valueOf(d2));
    }

    protected void encodeTaggedEnum(Object obj, @NotNull SerialDescriptor enumDescriptor, int i2) {
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        encodeTaggedValue(obj, Integer.valueOf(i2));
    }

    protected void encodeTaggedFloat(Object obj, float f2) {
        encodeTaggedValue(obj, Float.valueOf(f2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public Encoder encodeTaggedInline(Object obj, @NotNull SerialDescriptor inlineDescriptor) {
        Intrinsics.checkNotNullParameter(inlineDescriptor, "inlineDescriptor");
        e(obj);
        return this;
    }

    protected void encodeTaggedInt(Object obj, int i2) {
        encodeTaggedValue(obj, Integer.valueOf(i2));
    }

    protected void encodeTaggedLong(Object obj, long j2) {
        encodeTaggedValue(obj, Long.valueOf(j2));
    }

    protected void encodeTaggedNull(Object obj) {
        throw new SerializationException("null is not supported");
    }

    protected void encodeTaggedShort(Object obj, short s2) {
        encodeTaggedValue(obj, Short.valueOf(s2));
    }

    protected void encodeTaggedString(Object obj, @NotNull String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        encodeTaggedValue(obj, value);
    }

    protected void encodeTaggedValue(Object obj, @NotNull Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        throw new SerializationException("Non-serializable " + Reflection.getOrCreateKotlinClass(value.getClass()) + " is not supported by " + Reflection.getOrCreateKotlinClass(getClass()) + " encoder");
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void endStructure(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (!this.tagStack.isEmpty()) {
            d();
        }
        a(descriptor);
    }

    @Override // kotlinx.serialization.encoding.Encoder, kotlinx.serialization.encoding.CompositeEncoder
    @NotNull
    public SerializersModule getSerializersModule() {
        return SerializersModuleKt.getEmptySerializersModule();
    }

    protected abstract Object getTag(@NotNull SerialDescriptor serialDescriptor, int i2);

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    @ExperimentalSerializationApi
    public boolean shouldEncodeElementDefault(@NotNull SerialDescriptor serialDescriptor, int i2) {
        return CompositeEncoder.DefaultImpls.shouldEncodeElementDefault(this, serialDescriptor, i2);
    }
}
