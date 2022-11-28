package com.google.firebase;

import android.content.Context;
import com.google.firebase.platforminfo.LibraryVersionComponent;
/* loaded from: classes2.dex */
public final /* synthetic */ class e implements LibraryVersionComponent.VersionExtractor {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ e f10029a = new e();

    private /* synthetic */ e() {
    }

    @Override // com.google.firebase.platforminfo.LibraryVersionComponent.VersionExtractor
    public final String extract(Object obj) {
        String lambda$getComponents$1;
        lambda$getComponents$1 = FirebaseCommonRegistrar.lambda$getComponents$1((Context) obj);
        return lambda$getComponents$1;
    }
}
