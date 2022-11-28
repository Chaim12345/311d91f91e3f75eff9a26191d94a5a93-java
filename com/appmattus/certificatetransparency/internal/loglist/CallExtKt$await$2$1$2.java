package com.appmattus.certificatetransparency.internal.loglist;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import okhttp3.Call;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
final class CallExtKt$await$2$1$2 extends Lambda implements Function1<Throwable, Unit> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Call f4604a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CallExtKt$await$2$1$2(Call call) {
        super(1);
        this.f4604a = call;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@Nullable Throwable th) {
        try {
            this.f4604a.cancel();
        } catch (Throwable unused) {
        }
    }
}
