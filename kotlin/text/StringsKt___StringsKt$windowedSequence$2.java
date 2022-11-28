package kotlin.text;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* loaded from: classes3.dex */
final class StringsKt___StringsKt$windowedSequence$2 extends Lambda implements Function1<Integer, R> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f11263a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ CharSequence f11264b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Function1 f11265c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StringsKt___StringsKt$windowedSequence$2(int i2, CharSequence charSequence, Function1 function1) {
        super(1);
        this.f11263a = i2;
        this.f11264b = charSequence;
        this.f11265c = function1;
    }

    /* JADX WARN: Type inference failed for: r4v2, types: [R, java.lang.Object] */
    public final R invoke(int i2) {
        int i3 = this.f11263a + i2;
        if (i3 < 0 || i3 > this.f11264b.length()) {
            i3 = this.f11264b.length();
        }
        return this.f11265c.invoke(this.f11264b.subSequence(i2, i3));
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Integer num) {
        return invoke(num.intValue());
    }
}
