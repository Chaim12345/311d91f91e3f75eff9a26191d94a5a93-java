package com.google.android.gms.internal.safetynet;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.List;
/* loaded from: classes2.dex */
final class zzj extends zzz {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ List f6586e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ String f6587f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzj(zzae zzaeVar, GoogleApiClient googleApiClient, List list, String str, String str2) {
        super(googleApiClient);
        this.f6586e = list;
        this.f6587f = str;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void b(Api.AnyClient anyClient) {
        ((zzaf) anyClient).zzq(this.f6602d, this.f6586e, 1, this.f6587f, null);
    }
}
