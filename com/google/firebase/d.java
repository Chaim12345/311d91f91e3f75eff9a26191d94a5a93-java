package com.google.firebase;

import android.content.Context;
import com.google.firebase.platforminfo.LibraryVersionComponent;
/* loaded from: classes2.dex */
public final /* synthetic */ class d implements LibraryVersionComponent.VersionExtractor {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ d f10028a = new d();

    private /* synthetic */ d() {
    }

    @Override // com.google.firebase.platforminfo.LibraryVersionComponent.VersionExtractor
    public final String extract(Object obj) {
        String lambda$getComponents$0;
        lambda$getComponents$0 = FirebaseCommonRegistrar.lambda$getComponents$0((Context) obj);
        return lambda$getComponents$0;
    }
}
