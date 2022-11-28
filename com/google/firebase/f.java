package com.google.firebase;

import android.content.Context;
import com.google.firebase.platforminfo.LibraryVersionComponent;
/* loaded from: classes2.dex */
public final /* synthetic */ class f implements LibraryVersionComponent.VersionExtractor {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ f f10037a = new f();

    private /* synthetic */ f() {
    }

    @Override // com.google.firebase.platforminfo.LibraryVersionComponent.VersionExtractor
    public final String extract(Object obj) {
        String lambda$getComponents$2;
        lambda$getComponents$2 = FirebaseCommonRegistrar.lambda$getComponents$2((Context) obj);
        return lambda$getComponents$2;
    }
}
