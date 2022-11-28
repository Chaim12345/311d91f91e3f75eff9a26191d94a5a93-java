package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzcw extends zzcz {
    private final int zzc;
    private final int zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcw(byte[] bArr, int i2, int i3) {
        super(bArr);
        zzdb.i(i2, i2 + i3, bArr.length);
        this.zzc = i2;
        this.zzd = i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcz, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final byte a(int i2) {
        return this.f6411a[this.zzc + i2];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcz, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final void b(byte[] bArr, int i2, int i3, int i4) {
        System.arraycopy(this.f6411a, this.zzc + i2, bArr, i3, i4);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcz
    protected final int m() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcz, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final byte zza(int i2) {
        zzdb.k(i2, this.zzd);
        return this.f6411a[this.zzc + i2];
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcz, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final int zzd() {
        return this.zzd;
    }
}
