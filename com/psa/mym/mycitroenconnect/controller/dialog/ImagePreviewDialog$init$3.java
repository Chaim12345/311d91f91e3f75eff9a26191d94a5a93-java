package com.psa.mym.mycitroenconnect.controller.dialog;

import com.psa.mym.mycitroenconnect.utils.Logger;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* loaded from: classes3.dex */
final class ImagePreviewDialog$init$3 extends Lambda implements Function1<Float, Unit> {
    public static final ImagePreviewDialog$init$3 INSTANCE = new ImagePreviewDialog$init$3();

    ImagePreviewDialog$init$3() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Float f2) {
        invoke(f2.floatValue());
        return Unit.INSTANCE;
    }

    public final void invoke(float f2) {
        Logger logger = Logger.INSTANCE;
        logger.e("Progress : " + f2);
    }
}
