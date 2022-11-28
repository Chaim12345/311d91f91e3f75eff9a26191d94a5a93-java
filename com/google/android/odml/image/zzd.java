package com.google.android.odml.image;

import android.graphics.Bitmap;
/* loaded from: classes2.dex */
final /* synthetic */ class zzd {

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ int[] f7737a;

    static {
        int[] iArr = new int[Bitmap.Config.values().length];
        f7737a = iArr;
        try {
            iArr[Bitmap.Config.ALPHA_8.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f7737a[Bitmap.Config.ARGB_8888.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
    }
}
