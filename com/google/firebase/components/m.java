package com.google.firebase.components;

import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
/* loaded from: classes2.dex */
public final /* synthetic */ class m implements Deferred.DeferredHandler {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ m f9904a = new m();

    private /* synthetic */ m() {
    }

    @Override // com.google.firebase.inject.Deferred.DeferredHandler
    public final void handle(Provider provider) {
        OptionalProvider.lambda$static$0(provider);
    }
}
