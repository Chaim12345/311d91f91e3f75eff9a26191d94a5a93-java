package com.google.android.gms.internal.safetynet;

import android.text.TextUtils;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzi extends zzr {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ byte[] f6584e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ String f6585f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzi(GoogleApiClient googleApiClient, byte[] bArr, String str) {
        super(googleApiClient);
        this.f6584e = bArr;
        this.f6585f = str;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void b(Api.AnyClient anyClient) {
        zzaf zzafVar = (zzaf) anyClient;
        zzg zzgVar = this.f6594d;
        byte[] bArr = this.f6584e;
        String str = this.f6585f;
        if (TextUtils.isEmpty(str)) {
            str = zzafVar.E("com.google.android.safetynet.ATTEST_API_KEY");
        }
        ((zzh) zzafVar.getService()).zzc(zzgVar, bArr, str);
    }
}
