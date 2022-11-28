package com.google.firebase.messaging;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
/* loaded from: classes2.dex */
public final /* synthetic */ class v implements ComponentFactory {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ v f10116a = new v();

    private /* synthetic */ v() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer componentContainer) {
        FirebaseMessaging lambda$getComponents$0;
        lambda$getComponents$0 = FirebaseMessagingRegistrar.lambda$getComponents$0(componentContainer);
        return lambda$getComponents$0;
    }
}
