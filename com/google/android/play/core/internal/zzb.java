package com.google.android.play.core.internal;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
/* loaded from: classes2.dex */
final class zzb implements zzc {
    private final ByteBuffer zza;

    public zzb(ByteBuffer byteBuffer) {
        this.zza = byteBuffer.slice();
    }

    @Override // com.google.android.play.core.internal.zzc
    public final long zza() {
        return this.zza.capacity();
    }

    @Override // com.google.android.play.core.internal.zzc
    public final void zzb(MessageDigest[] messageDigestArr, long j2, int i2) {
        ByteBuffer slice;
        synchronized (this.zza) {
            int i3 = (int) j2;
            this.zza.position(i3);
            this.zza.limit(i3 + i2);
            slice = this.zza.slice();
        }
        for (MessageDigest messageDigest : messageDigestArr) {
            slice.position(0);
            messageDigest.update(slice);
        }
    }
}
