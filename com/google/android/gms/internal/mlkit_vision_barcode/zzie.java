package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
/* loaded from: classes2.dex */
final class zzie implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    static final zzie f6386a = new zzie();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("languageOption");
        zzdf zzdfVar = new zzdf();
        zzdfVar.zza(3);
        zzb = builder.withProperty(zzdfVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("isUsingLegacyApi");
        zzdf zzdfVar2 = new zzdf();
        zzdfVar2.zza(4);
        zzc = builder2.withProperty(zzdfVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("sdkVersion");
        zzdf zzdfVar3 = new zzdf();
        zzdfVar3.zza(5);
        zzd = builder3.withProperty(zzdfVar3.zzb()).build();
    }

    private zzie() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) {
        zzly zzlyVar = (zzly) obj;
        throw null;
    }
}
