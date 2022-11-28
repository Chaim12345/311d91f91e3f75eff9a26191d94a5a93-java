package kotlinx.serialization.internal;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.DeserializationStrategy;
/* loaded from: classes3.dex */
final class TaggedDecoder$decodeSerializableElement$1 extends Lambda implements Function0<T> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ TaggedDecoder f12445a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ DeserializationStrategy f12446b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Object f12447c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TaggedDecoder$decodeSerializableElement$1(TaggedDecoder taggedDecoder, DeserializationStrategy deserializationStrategy, Object obj) {
        super(0);
        this.f12445a = taggedDecoder;
        this.f12446b = deserializationStrategy;
        this.f12447c = obj;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [T, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function0
    public final T invoke() {
        return this.f12445a.a(this.f12446b, this.f12447c);
    }
}
