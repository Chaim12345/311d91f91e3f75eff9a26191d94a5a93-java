package com.google.android.gms.internal.mlkit_vision_barcode;

import java.io.Serializable;
/* loaded from: classes2.dex */
final class zzby extends zzbf implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    final Object f6282a;

    /* renamed from: b  reason: collision with root package name */
    final Object f6283b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzby(Object obj, Object obj2) {
        this.f6282a = obj;
        this.f6283b = obj2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbf, java.util.Map.Entry
    public final Object getKey() {
        return this.f6282a;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbf, java.util.Map.Entry
    public final Object getValue() {
        return this.f6283b;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbf, java.util.Map.Entry
    public final Object setValue(Object obj) {
        throw new UnsupportedOperationException();
    }
}
