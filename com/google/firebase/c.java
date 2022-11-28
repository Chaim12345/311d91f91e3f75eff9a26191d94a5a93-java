package com.google.firebase;

import android.content.Context;
import com.google.firebase.platforminfo.LibraryVersionComponent;
/* loaded from: classes2.dex */
public final /* synthetic */ class c implements LibraryVersionComponent.VersionExtractor {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ c f9886a = new c();

    private /* synthetic */ c() {
    }

    @Override // com.google.firebase.platforminfo.LibraryVersionComponent.VersionExtractor
    public final String extract(Object obj) {
        String lambda$getComponents$3;
        lambda$getComponents$3 = FirebaseCommonRegistrar.lambda$getComponents$3((Context) obj);
        return lambda$getComponents$3;
    }
}
