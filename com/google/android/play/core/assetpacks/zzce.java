package com.google.android.play.core.assetpacks;

import com.google.android.play.core.assetpacks.model.AssetPackStatus;
import java.io.InputStream;
/* loaded from: classes2.dex */
final class zzce extends zzdg {

    /* renamed from: c  reason: collision with root package name */
    final int f7804c;

    /* renamed from: d  reason: collision with root package name */
    final long f7805d;

    /* renamed from: e  reason: collision with root package name */
    final String f7806e;

    /* renamed from: f  reason: collision with root package name */
    final String f7807f;

    /* renamed from: g  reason: collision with root package name */
    final int f7808g;

    /* renamed from: h  reason: collision with root package name */
    final int f7809h;

    /* renamed from: i  reason: collision with root package name */
    final int f7810i;

    /* renamed from: j  reason: collision with root package name */
    final long f7811j;
    @AssetPackStatus

    /* renamed from: k  reason: collision with root package name */
    final int f7812k;

    /* renamed from: l  reason: collision with root package name */
    final InputStream f7813l;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzce(int i2, String str, int i3, long j2, String str2, String str3, int i4, int i5, int i6, long j3, @AssetPackStatus int i7, InputStream inputStream) {
        super(i2, str);
        this.f7804c = i3;
        this.f7805d = j2;
        this.f7806e = str2;
        this.f7807f = str3;
        this.f7808g = i4;
        this.f7809h = i5;
        this.f7810i = i6;
        this.f7811j = j3;
        this.f7812k = i7;
        this.f7813l = inputStream;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean a() {
        return this.f7809h + 1 == this.f7810i;
    }
}
