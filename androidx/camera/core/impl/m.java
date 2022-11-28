package androidx.camera.core.impl;

import androidx.camera.core.impl.UseCaseAttachState;
/* loaded from: classes.dex */
public final /* synthetic */ class m implements UseCaseAttachState.AttachStateFilter {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ m f1175a = new m();

    private /* synthetic */ m() {
    }

    @Override // androidx.camera.core.impl.UseCaseAttachState.AttachStateFilter
    public final boolean filter(UseCaseAttachState.UseCaseAttachInfo useCaseAttachInfo) {
        boolean b2;
        b2 = useCaseAttachInfo.b();
        return b2;
    }
}
