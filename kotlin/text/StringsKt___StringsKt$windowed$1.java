package kotlin.text;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class StringsKt___StringsKt$windowed$1 extends Lambda implements Function1<CharSequence, String> {
    public static final StringsKt___StringsKt$windowed$1 INSTANCE = new StringsKt___StringsKt$windowed$1();

    StringsKt___StringsKt$windowed$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final String invoke(@NotNull CharSequence it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.toString();
    }
}
