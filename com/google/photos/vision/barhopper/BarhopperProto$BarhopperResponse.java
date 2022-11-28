package com.google.photos.vision.barhopper;

import androidx.annotation.NonNull;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdn;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzek;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm;
import java.util.List;
/* loaded from: classes2.dex */
public final class BarhopperProto$BarhopperResponse extends zzec<BarhopperProto$BarhopperResponse, zzj> implements zzfm {
    private static final BarhopperProto$BarhopperResponse zza;
    private int zze;
    private int zzg;
    private byte zzj = 2;
    private zzek zzf = zzec.j();
    private String zzh = "";
    private zzdb zzi = zzdb.zzb;

    static {
        BarhopperProto$BarhopperResponse barhopperProto$BarhopperResponse = new BarhopperProto$BarhopperResponse();
        zza = barhopperProto$BarhopperResponse;
        zzec.n(BarhopperProto$BarhopperResponse.class, barhopperProto$BarhopperResponse);
    }

    private BarhopperProto$BarhopperResponse() {
    }

    public static BarhopperProto$BarhopperResponse zzb(byte[] bArr, zzdn zzdnVar) {
        return (BarhopperProto$BarhopperResponse) zzec.e(zza, bArr, zzdnVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    @NonNull
    public final Object p(int i2, @NonNull Object obj, @NonNull Object obj2) {
        int i3 = i2 - 1;
        if (i3 != 0) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 4) {
                        if (i3 != 5) {
                            this.zzj = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zza;
                    }
                    return new zzj(null);
                }
                return new BarhopperProto$BarhopperResponse();
            }
            return zzec.m(zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0002\u0001Л\u0002ᔌ\u0000\u0003ဈ\u0001\u0004ည\u0002", new Object[]{"zze", "zzf", zzc.class, "zzg", zzah.f10348a, "zzh", "zzi"});
        }
        return Byte.valueOf(this.zzj);
    }

    @NonNull
    public final List zzc() {
        return this.zzf;
    }
}
