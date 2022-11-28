package com.google.android.gms.internal.mlkit_common;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
/* loaded from: classes.dex */
final class zzgd implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    static final zzgd f6226a = new zzgd();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;
    private static final FieldDescriptor zzf;
    private static final FieldDescriptor zzg;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("pipelineNamespace");
        zzbe zzbeVar = new zzbe();
        zzbeVar.zza(1);
        zzb = builder.withProperty(zzbeVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder(AppMeasurementSdk.ConditionalUserProperty.NAME);
        zzbe zzbeVar2 = new zzbe();
        zzbeVar2.zza(2);
        zzc = builder2.withProperty(zzbeVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("clientLibraryName");
        zzbe zzbeVar3 = new zzbe();
        zzbeVar3.zza(3);
        zzd = builder3.withProperty(zzbeVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("clientLibraryVersion");
        zzbe zzbeVar4 = new zzbe();
        zzbeVar4.zza(4);
        zze = builder4.withProperty(zzbeVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("minClientLibraryVersion");
        zzbe zzbeVar5 = new zzbe();
        zzbeVar5.zza(5);
        zzf = builder5.withProperty(zzbeVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("maxClientLibraryVersion");
        zzbe zzbeVar6 = new zzbe();
        zzbeVar6.zza(6);
        zzg = builder6.withProperty(zzbeVar6.zzb()).build();
    }

    private zzgd() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) {
        zzkm zzkmVar = (zzkm) obj;
        throw null;
    }
}
