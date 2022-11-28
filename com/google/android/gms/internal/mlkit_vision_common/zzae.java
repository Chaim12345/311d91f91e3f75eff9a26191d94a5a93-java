package com.google.android.gms.internal.mlkit_vision_common;

import androidx.annotation.NonNull;
import java.io.OutputStream;
/* loaded from: classes2.dex */
final class zzae extends OutputStream {
    private long zza = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long a() {
        return this.zza;
    }

    @Override // java.io.OutputStream
    public final void write(int i2) {
        this.zza++;
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr) {
        this.zza += bArr.length;
    }

    @Override // java.io.OutputStream
    public final void write(@NonNull byte[] bArr, int i2, int i3) {
        int length;
        int i4;
        if (i2 < 0 || i2 > (length = bArr.length) || i3 < 0 || (i4 = i2 + i3) > length || i4 < 0) {
            throw new IndexOutOfBoundsException();
        }
        this.zza += i3;
    }
}
