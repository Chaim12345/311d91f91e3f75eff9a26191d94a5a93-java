package kotlinx.serialization.internal;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.DeserializationStrategy;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
final class TaggedDecoder$decodeNullableSerializableElement$1 extends Lambda implements Function0<T> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ TaggedDecoder f12442a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ DeserializationStrategy f12443b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Object f12444c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TaggedDecoder$decodeNullableSerializableElement$1(TaggedDecoder taggedDecoder, DeserializationStrategy deserializationStrategy, Object obj) {
        super(0);
        this.f12442a = taggedDecoder;
        this.f12443b = deserializationStrategy;
        this.f12444c = obj;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Void, T] */
    /* JADX WARN: Type inference failed for: r0v5, types: [T, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function0
    @Nullable
    public final T invoke() {
        return this.f12442a.decodeNotNullMark() ? this.f12442a.a(this.f12443b, this.f12444c) : this.f12442a.decodeNull();
    }
}
