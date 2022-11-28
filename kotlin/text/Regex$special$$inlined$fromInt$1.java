package kotlin.text;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class Regex$special$$inlined$fromInt$1 extends Lambda implements Function1<RegexOption, Boolean> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f11239a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Regex$special$$inlined$fromInt$1(int i2) {
        super(1);
        this.f11239a = i2;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final Boolean invoke(RegexOption regexOption) {
        RegexOption regexOption2 = regexOption;
        return Boolean.valueOf((this.f11239a & regexOption2.getMask()) == regexOption2.getValue());
    }
}
