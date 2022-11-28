package com.psa.mym.mycitroenconnect.controller.dialog;

import android.view.View;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ImagePreviewDialog$init$1 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ImagePreviewDialog f10493a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImagePreviewDialog$init$1(ImagePreviewDialog imagePreviewDialog) {
        super(0);
        this.f10493a = imagePreviewDialog;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2() {
        View progress = this.f10493a.findViewById(R.id.progress);
        Intrinsics.checkNotNullExpressionValue(progress, "progress");
        ExtensionsKt.hide(progress);
    }
}
