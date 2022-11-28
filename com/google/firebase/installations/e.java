package com.google.firebase.installations;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
/* loaded from: classes2.dex */
public final /* synthetic */ class e implements ComponentFactory {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ e f10055a = new e();

    private /* synthetic */ e() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer componentContainer) {
        FirebaseInstallationsApi lambda$getComponents$0;
        lambda$getComponents$0 = FirebaseInstallationsRegistrar.lambda$getComponents$0(componentContainer);
        return lambda$getComponents$0;
    }
}
