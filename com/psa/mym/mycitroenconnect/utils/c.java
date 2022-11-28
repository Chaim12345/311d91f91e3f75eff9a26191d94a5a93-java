package com.psa.mym.mycitroenconnect.utils;

import android.text.InputFilter;
import android.text.Spanned;
/* loaded from: classes3.dex */
public final /* synthetic */ class c implements InputFilter {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ c f10752a = new c();

    private /* synthetic */ c() {
    }

    @Override // android.text.InputFilter
    public final CharSequence filter(CharSequence charSequence, int i2, int i3, Spanned spanned, int i4, int i5) {
        CharSequence m163disableEmoji$lambda1;
        m163disableEmoji$lambda1 = ExtensionsKt.m163disableEmoji$lambda1(charSequence, i2, i3, spanned, i4, i5);
        return m163disableEmoji$lambda1;
    }
}
