package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.internal.base.zak;
/* loaded from: classes.dex */
public abstract class zag {

    /* renamed from: a  reason: collision with root package name */
    final zad f5742a;

    /* renamed from: b  reason: collision with root package name */
    protected int f5743b;

    public zag(Uri uri, int i2) {
        this.f5743b = 0;
        this.f5742a = new zad(uri);
        this.f5743b = i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void a(@Nullable Drawable drawable, boolean z, boolean z2, boolean z3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b(Context context, zak zakVar, boolean z) {
        int i2 = this.f5743b;
        a(i2 != 0 ? context.getResources().getDrawable(i2) : null, z, false, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void c(Context context, Bitmap bitmap, boolean z) {
        Asserts.checkNotNull(bitmap);
        a(new BitmapDrawable(context.getResources(), bitmap), false, false, true);
    }
}
