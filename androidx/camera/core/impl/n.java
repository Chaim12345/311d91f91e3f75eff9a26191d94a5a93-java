package androidx.camera.core.impl;

import androidx.camera.core.impl.UseCaseAttachState;
/* loaded from: classes.dex */
public final /* synthetic */ class n implements UseCaseAttachState.AttachStateFilter {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ n f1176a = new n();

    private /* synthetic */ n() {
    }

    @Override // androidx.camera.core.impl.UseCaseAttachState.AttachStateFilter
    public final boolean filter(UseCaseAttachState.UseCaseAttachInfo useCaseAttachInfo) {
        boolean lambda$getActiveAndAttachedSessionConfigs$1;
        lambda$getActiveAndAttachedSessionConfigs$1 = UseCaseAttachState.lambda$getActiveAndAttachedSessionConfigs$1(useCaseAttachInfo);
        return lambda$getActiveAndAttachedSessionConfigs$1;
    }
}
