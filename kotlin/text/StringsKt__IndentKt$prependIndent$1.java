package kotlin.text;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class StringsKt__IndentKt$prependIndent$1 extends Lambda implements Function1<String, String> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f11251a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StringsKt__IndentKt$prependIndent$1(String str) {
        super(1);
        this.f11251a = str;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final String invoke(@NotNull String it) {
        boolean isBlank;
        Intrinsics.checkNotNullParameter(it, "it");
        isBlank = StringsKt__StringsJVMKt.isBlank(it);
        if (isBlank) {
            return it.length() < this.f11251a.length() ? this.f11251a : it;
        }
        return this.f11251a + it;
    }
}
