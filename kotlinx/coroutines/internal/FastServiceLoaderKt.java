package kotlinx.coroutines.internal;

import kotlin.Result;
import kotlin.ResultKt;
/* loaded from: classes3.dex */
public final class FastServiceLoaderKt {
    private static final boolean ANDROID_DETECTED;

    static {
        Object m187constructorimpl;
        try {
            Result.Companion companion = Result.Companion;
            m187constructorimpl = Result.m187constructorimpl(Class.forName("android.os.Build"));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            m187constructorimpl = Result.m187constructorimpl(ResultKt.createFailure(th));
        }
        ANDROID_DETECTED = Result.m194isSuccessimpl(m187constructorimpl);
    }

    public static final boolean getANDROID_DETECTED() {
        return ANDROID_DETECTED;
    }
}
