package com.google.android.gms.internal.safetynet;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzk extends zzz {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ int[] f6588e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ int f6589f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ String f6590g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ String f6591h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzk(GoogleApiClient googleApiClient, int[] iArr, int i2, String str, String str2) {
        super(googleApiClient);
        this.f6588e = iArr;
        this.f6589f = i2;
        this.f6590g = str;
        this.f6591h = str2;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void b(Api.AnyClient anyClient) {
        zzaf zzafVar = (zzaf) anyClient;
        ArrayList arrayList = new ArrayList();
        for (int i2 : this.f6588e) {
            arrayList.add(Integer.valueOf(i2));
        }
        zzafVar.zzq(this.f6602d, arrayList, this.f6589f, this.f6590g, this.f6591h);
    }
}
