package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
/* loaded from: classes.dex */
final class zaq implements PendingResultUtil.ResultConverter {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Response f5778a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zaq(Response response) {
        this.f5778a = response;
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* bridge */ /* synthetic */ Object convert(Result result) {
        this.f5778a.setResult(result);
        return this.f5778a;
    }
}
