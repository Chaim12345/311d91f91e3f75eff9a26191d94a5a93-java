package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
/* loaded from: classes.dex */
final class zzes implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    static final zzes f6189a = new zzes();
    private static final FieldDescriptor zzA;
    private static final FieldDescriptor zzB;
    private static final FieldDescriptor zzC;
    private static final FieldDescriptor zzD;
    private static final FieldDescriptor zzE;
    private static final FieldDescriptor zzF;
    private static final FieldDescriptor zzG;
    private static final FieldDescriptor zzH;
    private static final FieldDescriptor zzI;
    private static final FieldDescriptor zzJ;
    private static final FieldDescriptor zzK;
    private static final FieldDescriptor zzL;
    private static final FieldDescriptor zzM;
    private static final FieldDescriptor zzN;
    private static final FieldDescriptor zzO;
    private static final FieldDescriptor zzP;
    private static final FieldDescriptor zzQ;
    private static final FieldDescriptor zzR;
    private static final FieldDescriptor zzS;
    private static final FieldDescriptor zzT;
    private static final FieldDescriptor zzU;
    private static final FieldDescriptor zzV;
    private static final FieldDescriptor zzW;
    private static final FieldDescriptor zzX;
    private static final FieldDescriptor zzY;
    private static final FieldDescriptor zzZ;
    private static final FieldDescriptor zzaa;
    private static final FieldDescriptor zzab;
    private static final FieldDescriptor zzac;
    private static final FieldDescriptor zzad;
    private static final FieldDescriptor zzae;
    private static final FieldDescriptor zzaf;
    private static final FieldDescriptor zzag;
    private static final FieldDescriptor zzah;
    private static final FieldDescriptor zzai;
    private static final FieldDescriptor zzaj;
    private static final FieldDescriptor zzak;
    private static final FieldDescriptor zzal;
    private static final FieldDescriptor zzam;
    private static final FieldDescriptor zzan;
    private static final FieldDescriptor zzao;
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;
    private static final FieldDescriptor zzf;
    private static final FieldDescriptor zzg;
    private static final FieldDescriptor zzh;
    private static final FieldDescriptor zzi;
    private static final FieldDescriptor zzj;
    private static final FieldDescriptor zzk;
    private static final FieldDescriptor zzl;
    private static final FieldDescriptor zzm;
    private static final FieldDescriptor zzn;
    private static final FieldDescriptor zzo;
    private static final FieldDescriptor zzp;
    private static final FieldDescriptor zzq;
    private static final FieldDescriptor zzr;
    private static final FieldDescriptor zzs;
    private static final FieldDescriptor zzt;
    private static final FieldDescriptor zzu;
    private static final FieldDescriptor zzv;
    private static final FieldDescriptor zzw;
    private static final FieldDescriptor zzx;
    private static final FieldDescriptor zzy;
    private static final FieldDescriptor zzz;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("systemInfo");
        zzbe zzbeVar = new zzbe();
        zzbeVar.zza(1);
        zzb = builder.withProperty(zzbeVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("eventName");
        zzbe zzbeVar2 = new zzbe();
        zzbeVar2.zza(2);
        zzc = builder2.withProperty(zzbeVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("isThickClient");
        zzbe zzbeVar3 = new zzbe();
        zzbeVar3.zza(37);
        zzd = builder3.withProperty(zzbeVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("clientType");
        zzbe zzbeVar4 = new zzbe();
        zzbeVar4.zza(61);
        zze = builder4.withProperty(zzbeVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("modelDownloadLogEvent");
        zzbe zzbeVar5 = new zzbe();
        zzbeVar5.zza(3);
        zzf = builder5.withProperty(zzbeVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("customModelLoadLogEvent");
        zzbe zzbeVar6 = new zzbe();
        zzbeVar6.zza(20);
        zzg = builder6.withProperty(zzbeVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("customModelInferenceLogEvent");
        zzbe zzbeVar7 = new zzbe();
        zzbeVar7.zza(4);
        zzh = builder7.withProperty(zzbeVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("customModelCreateLogEvent");
        zzbe zzbeVar8 = new zzbe();
        zzbeVar8.zza(29);
        zzi = builder8.withProperty(zzbeVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("onDeviceFaceDetectionLogEvent");
        zzbe zzbeVar9 = new zzbe();
        zzbeVar9.zza(5);
        zzj = builder9.withProperty(zzbeVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("onDeviceFaceLoadLogEvent");
        zzbe zzbeVar10 = new zzbe();
        zzbeVar10.zza(59);
        zzk = builder10.withProperty(zzbeVar10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder("onDeviceTextDetectionLogEvent");
        zzbe zzbeVar11 = new zzbe();
        zzbeVar11.zza(6);
        zzl = builder11.withProperty(zzbeVar11.zzb()).build();
        FieldDescriptor.Builder builder12 = FieldDescriptor.builder("onDeviceBarcodeDetectionLogEvent");
        zzbe zzbeVar12 = new zzbe();
        zzbeVar12.zza(7);
        zzm = builder12.withProperty(zzbeVar12.zzb()).build();
        FieldDescriptor.Builder builder13 = FieldDescriptor.builder("onDeviceBarcodeLoadLogEvent");
        zzbe zzbeVar13 = new zzbe();
        zzbeVar13.zza(58);
        zzn = builder13.withProperty(zzbeVar13.zzb()).build();
        FieldDescriptor.Builder builder14 = FieldDescriptor.builder("onDeviceImageLabelCreateLogEvent");
        zzbe zzbeVar14 = new zzbe();
        zzbeVar14.zza(48);
        zzo = builder14.withProperty(zzbeVar14.zzb()).build();
        FieldDescriptor.Builder builder15 = FieldDescriptor.builder("onDeviceImageLabelLoadLogEvent");
        zzbe zzbeVar15 = new zzbe();
        zzbeVar15.zza(49);
        zzp = builder15.withProperty(zzbeVar15.zzb()).build();
        FieldDescriptor.Builder builder16 = FieldDescriptor.builder("onDeviceImageLabelDetectionLogEvent");
        zzbe zzbeVar16 = new zzbe();
        zzbeVar16.zza(18);
        zzq = builder16.withProperty(zzbeVar16.zzb()).build();
        FieldDescriptor.Builder builder17 = FieldDescriptor.builder("onDeviceObjectCreateLogEvent");
        zzbe zzbeVar17 = new zzbe();
        zzbeVar17.zza(26);
        zzr = builder17.withProperty(zzbeVar17.zzb()).build();
        FieldDescriptor.Builder builder18 = FieldDescriptor.builder("onDeviceObjectLoadLogEvent");
        zzbe zzbeVar18 = new zzbe();
        zzbeVar18.zza(27);
        zzs = builder18.withProperty(zzbeVar18.zzb()).build();
        FieldDescriptor.Builder builder19 = FieldDescriptor.builder("onDeviceObjectInferenceLogEvent");
        zzbe zzbeVar19 = new zzbe();
        zzbeVar19.zza(28);
        zzt = builder19.withProperty(zzbeVar19.zzb()).build();
        FieldDescriptor.Builder builder20 = FieldDescriptor.builder("onDevicePoseDetectionLogEvent");
        zzbe zzbeVar20 = new zzbe();
        zzbeVar20.zza(44);
        zzu = builder20.withProperty(zzbeVar20.zzb()).build();
        FieldDescriptor.Builder builder21 = FieldDescriptor.builder("onDeviceSegmentationLogEvent");
        zzbe zzbeVar21 = new zzbe();
        zzbeVar21.zza(45);
        zzv = builder21.withProperty(zzbeVar21.zzb()).build();
        FieldDescriptor.Builder builder22 = FieldDescriptor.builder("onDeviceSmartReplyLogEvent");
        zzbe zzbeVar22 = new zzbe();
        zzbeVar22.zza(19);
        zzw = builder22.withProperty(zzbeVar22.zzb()).build();
        FieldDescriptor.Builder builder23 = FieldDescriptor.builder("onDeviceLanguageIdentificationLogEvent");
        zzbe zzbeVar23 = new zzbe();
        zzbeVar23.zza(21);
        zzx = builder23.withProperty(zzbeVar23.zzb()).build();
        FieldDescriptor.Builder builder24 = FieldDescriptor.builder("onDeviceTranslationLogEvent");
        zzbe zzbeVar24 = new zzbe();
        zzbeVar24.zza(22);
        zzy = builder24.withProperty(zzbeVar24.zzb()).build();
        FieldDescriptor.Builder builder25 = FieldDescriptor.builder("cloudFaceDetectionLogEvent");
        zzbe zzbeVar25 = new zzbe();
        zzbeVar25.zza(8);
        zzz = builder25.withProperty(zzbeVar25.zzb()).build();
        FieldDescriptor.Builder builder26 = FieldDescriptor.builder("cloudCropHintDetectionLogEvent");
        zzbe zzbeVar26 = new zzbe();
        zzbeVar26.zza(9);
        zzA = builder26.withProperty(zzbeVar26.zzb()).build();
        FieldDescriptor.Builder builder27 = FieldDescriptor.builder("cloudDocumentTextDetectionLogEvent");
        zzbe zzbeVar27 = new zzbe();
        zzbeVar27.zza(10);
        zzB = builder27.withProperty(zzbeVar27.zzb()).build();
        FieldDescriptor.Builder builder28 = FieldDescriptor.builder("cloudImagePropertiesDetectionLogEvent");
        zzbe zzbeVar28 = new zzbe();
        zzbeVar28.zza(11);
        zzC = builder28.withProperty(zzbeVar28.zzb()).build();
        FieldDescriptor.Builder builder29 = FieldDescriptor.builder("cloudImageLabelDetectionLogEvent");
        zzbe zzbeVar29 = new zzbe();
        zzbeVar29.zza(12);
        zzD = builder29.withProperty(zzbeVar29.zzb()).build();
        FieldDescriptor.Builder builder30 = FieldDescriptor.builder("cloudLandmarkDetectionLogEvent");
        zzbe zzbeVar30 = new zzbe();
        zzbeVar30.zza(13);
        zzE = builder30.withProperty(zzbeVar30.zzb()).build();
        FieldDescriptor.Builder builder31 = FieldDescriptor.builder("cloudLogoDetectionLogEvent");
        zzbe zzbeVar31 = new zzbe();
        zzbeVar31.zza(14);
        zzF = builder31.withProperty(zzbeVar31.zzb()).build();
        FieldDescriptor.Builder builder32 = FieldDescriptor.builder("cloudSafeSearchDetectionLogEvent");
        zzbe zzbeVar32 = new zzbe();
        zzbeVar32.zza(15);
        zzG = builder32.withProperty(zzbeVar32.zzb()).build();
        FieldDescriptor.Builder builder33 = FieldDescriptor.builder("cloudTextDetectionLogEvent");
        zzbe zzbeVar33 = new zzbe();
        zzbeVar33.zza(16);
        zzH = builder33.withProperty(zzbeVar33.zzb()).build();
        FieldDescriptor.Builder builder34 = FieldDescriptor.builder("cloudWebSearchDetectionLogEvent");
        zzbe zzbeVar34 = new zzbe();
        zzbeVar34.zza(17);
        zzI = builder34.withProperty(zzbeVar34.zzb()).build();
        FieldDescriptor.Builder builder35 = FieldDescriptor.builder("automlImageLabelingCreateLogEvent");
        zzbe zzbeVar35 = new zzbe();
        zzbeVar35.zza(23);
        zzJ = builder35.withProperty(zzbeVar35.zzb()).build();
        FieldDescriptor.Builder builder36 = FieldDescriptor.builder("automlImageLabelingLoadLogEvent");
        zzbe zzbeVar36 = new zzbe();
        zzbeVar36.zza(24);
        zzK = builder36.withProperty(zzbeVar36.zzb()).build();
        FieldDescriptor.Builder builder37 = FieldDescriptor.builder("automlImageLabelingInferenceLogEvent");
        zzbe zzbeVar37 = new zzbe();
        zzbeVar37.zza(25);
        zzL = builder37.withProperty(zzbeVar37.zzb()).build();
        FieldDescriptor.Builder builder38 = FieldDescriptor.builder("isModelDownloadedLogEvent");
        zzbe zzbeVar38 = new zzbe();
        zzbeVar38.zza(39);
        zzM = builder38.withProperty(zzbeVar38.zzb()).build();
        FieldDescriptor.Builder builder39 = FieldDescriptor.builder("deleteModelLogEvent");
        zzbe zzbeVar39 = new zzbe();
        zzbeVar39.zza(40);
        zzN = builder39.withProperty(zzbeVar39.zzb()).build();
        FieldDescriptor.Builder builder40 = FieldDescriptor.builder("aggregatedAutomlImageLabelingInferenceLogEvent");
        zzbe zzbeVar40 = new zzbe();
        zzbeVar40.zza(30);
        zzO = builder40.withProperty(zzbeVar40.zzb()).build();
        FieldDescriptor.Builder builder41 = FieldDescriptor.builder("aggregatedCustomModelInferenceLogEvent");
        zzbe zzbeVar41 = new zzbe();
        zzbeVar41.zza(31);
        zzP = builder41.withProperty(zzbeVar41.zzb()).build();
        FieldDescriptor.Builder builder42 = FieldDescriptor.builder("aggregatedOnDeviceFaceDetectionLogEvent");
        zzbe zzbeVar42 = new zzbe();
        zzbeVar42.zza(32);
        zzQ = builder42.withProperty(zzbeVar42.zzb()).build();
        FieldDescriptor.Builder builder43 = FieldDescriptor.builder("aggregatedOnDeviceBarcodeDetectionLogEvent");
        zzbe zzbeVar43 = new zzbe();
        zzbeVar43.zza(33);
        zzR = builder43.withProperty(zzbeVar43.zzb()).build();
        FieldDescriptor.Builder builder44 = FieldDescriptor.builder("aggregatedOnDeviceImageLabelDetectionLogEvent");
        zzbe zzbeVar44 = new zzbe();
        zzbeVar44.zza(34);
        zzS = builder44.withProperty(zzbeVar44.zzb()).build();
        FieldDescriptor.Builder builder45 = FieldDescriptor.builder("aggregatedOnDeviceObjectInferenceLogEvent");
        zzbe zzbeVar45 = new zzbe();
        zzbeVar45.zza(35);
        zzT = builder45.withProperty(zzbeVar45.zzb()).build();
        FieldDescriptor.Builder builder46 = FieldDescriptor.builder("aggregatedOnDeviceTextDetectionLogEvent");
        zzbe zzbeVar46 = new zzbe();
        zzbeVar46.zza(36);
        zzU = builder46.withProperty(zzbeVar46.zzb()).build();
        FieldDescriptor.Builder builder47 = FieldDescriptor.builder("aggregatedOnDevicePoseDetectionLogEvent");
        zzbe zzbeVar47 = new zzbe();
        zzbeVar47.zza(46);
        zzV = builder47.withProperty(zzbeVar47.zzb()).build();
        FieldDescriptor.Builder builder48 = FieldDescriptor.builder("aggregatedOnDeviceSegmentationLogEvent");
        zzbe zzbeVar48 = new zzbe();
        zzbeVar48.zza(47);
        zzW = builder48.withProperty(zzbeVar48.zzb()).build();
        FieldDescriptor.Builder builder49 = FieldDescriptor.builder("pipelineAccelerationInferenceEvents");
        zzbe zzbeVar49 = new zzbe();
        zzbeVar49.zza(69);
        zzX = builder49.withProperty(zzbeVar49.zzb()).build();
        FieldDescriptor.Builder builder50 = FieldDescriptor.builder("remoteConfigLogEvent");
        zzbe zzbeVar50 = new zzbe();
        zzbeVar50.zza(42);
        zzY = builder50.withProperty(zzbeVar50.zzb()).build();
        FieldDescriptor.Builder builder51 = FieldDescriptor.builder("inputImageConstructionLogEvent");
        zzbe zzbeVar51 = new zzbe();
        zzbeVar51.zza(50);
        zzZ = builder51.withProperty(zzbeVar51.zzb()).build();
        FieldDescriptor.Builder builder52 = FieldDescriptor.builder("leakedHandleEvent");
        zzbe zzbeVar52 = new zzbe();
        zzbeVar52.zza(51);
        zzaa = builder52.withProperty(zzbeVar52.zzb()).build();
        FieldDescriptor.Builder builder53 = FieldDescriptor.builder("cameraSourceLogEvent");
        zzbe zzbeVar53 = new zzbe();
        zzbeVar53.zza(52);
        zzab = builder53.withProperty(zzbeVar53.zzb()).build();
        FieldDescriptor.Builder builder54 = FieldDescriptor.builder("imageLabelOptionalModuleLogEvent");
        zzbe zzbeVar54 = new zzbe();
        zzbeVar54.zza(53);
        zzac = builder54.withProperty(zzbeVar54.zzb()).build();
        FieldDescriptor.Builder builder55 = FieldDescriptor.builder("languageIdentificationOptionalModuleLogEvent");
        zzbe zzbeVar55 = new zzbe();
        zzbeVar55.zza(54);
        zzad = builder55.withProperty(zzbeVar55.zzb()).build();
        FieldDescriptor.Builder builder56 = FieldDescriptor.builder("faceDetectionOptionalModuleLogEvent");
        zzbe zzbeVar56 = new zzbe();
        zzbeVar56.zza(60);
        zzae = builder56.withProperty(zzbeVar56.zzb()).build();
        FieldDescriptor.Builder builder57 = FieldDescriptor.builder("nlClassifierOptionalModuleLogEvent");
        zzbe zzbeVar57 = new zzbe();
        zzbeVar57.zza(55);
        zzaf = builder57.withProperty(zzbeVar57.zzb()).build();
        FieldDescriptor.Builder builder58 = FieldDescriptor.builder("nlClassifierClientLibraryLogEvent");
        zzbe zzbeVar58 = new zzbe();
        zzbeVar58.zza(56);
        zzag = builder58.withProperty(zzbeVar58.zzb()).build();
        FieldDescriptor.Builder builder59 = FieldDescriptor.builder("accelerationAllowlistLogEvent");
        zzbe zzbeVar59 = new zzbe();
        zzbeVar59.zza(57);
        zzah = builder59.withProperty(zzbeVar59.zzb()).build();
        FieldDescriptor.Builder builder60 = FieldDescriptor.builder("toxicityDetectionCreateEvent");
        zzbe zzbeVar60 = new zzbe();
        zzbeVar60.zza(62);
        zzai = builder60.withProperty(zzbeVar60.zzb()).build();
        FieldDescriptor.Builder builder61 = FieldDescriptor.builder("toxicityDetectionLoadEvent");
        zzbe zzbeVar61 = new zzbe();
        zzbeVar61.zza(63);
        zzaj = builder61.withProperty(zzbeVar61.zzb()).build();
        FieldDescriptor.Builder builder62 = FieldDescriptor.builder("toxicityDetectionInferenceEvent");
        zzbe zzbeVar62 = new zzbe();
        zzbeVar62.zza(64);
        zzak = builder62.withProperty(zzbeVar62.zzb()).build();
        FieldDescriptor.Builder builder63 = FieldDescriptor.builder("barcodeDetectionOptionalModuleLogEvent");
        zzbe zzbeVar63 = new zzbe();
        zzbeVar63.zza(65);
        zzal = builder63.withProperty(zzbeVar63.zzb()).build();
        FieldDescriptor.Builder builder64 = FieldDescriptor.builder("customImageLabelOptionalModuleLogEvent");
        zzbe zzbeVar64 = new zzbe();
        zzbeVar64.zza(66);
        zzam = builder64.withProperty(zzbeVar64.zzb()).build();
        FieldDescriptor.Builder builder65 = FieldDescriptor.builder("codeScannerScanApiEvent");
        zzbe zzbeVar65 = new zzbe();
        zzbeVar65.zza(67);
        zzan = builder65.withProperty(zzbeVar65.zzb()).build();
        FieldDescriptor.Builder builder66 = FieldDescriptor.builder("codeScannerOptionalModuleEvent");
        zzbe zzbeVar66 = new zzbe();
        zzbeVar66.zza(68);
        zzao = builder66.withProperty(zzbeVar66.zzb()).build();
    }

    private zzes() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) {
        zzih zzihVar = (zzih) obj;
        ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
        objectEncoderContext2.add(zzb, zzihVar.zzf());
        objectEncoderContext2.add(zzc, zzihVar.zzd());
        objectEncoderContext2.add(zzd, (Object) null);
        objectEncoderContext2.add(zze, (Object) null);
        objectEncoderContext2.add(zzf, zzihVar.zze());
        objectEncoderContext2.add(zzg, (Object) null);
        objectEncoderContext2.add(zzh, (Object) null);
        objectEncoderContext2.add(zzi, (Object) null);
        objectEncoderContext2.add(zzj, (Object) null);
        objectEncoderContext2.add(zzk, (Object) null);
        objectEncoderContext2.add(zzl, (Object) null);
        objectEncoderContext2.add(zzm, (Object) null);
        objectEncoderContext2.add(zzn, (Object) null);
        objectEncoderContext2.add(zzo, (Object) null);
        objectEncoderContext2.add(zzp, (Object) null);
        objectEncoderContext2.add(zzq, (Object) null);
        objectEncoderContext2.add(zzr, (Object) null);
        objectEncoderContext2.add(zzs, (Object) null);
        objectEncoderContext2.add(zzt, (Object) null);
        objectEncoderContext2.add(zzu, (Object) null);
        objectEncoderContext2.add(zzv, (Object) null);
        objectEncoderContext2.add(zzw, (Object) null);
        objectEncoderContext2.add(zzx, (Object) null);
        objectEncoderContext2.add(zzy, (Object) null);
        objectEncoderContext2.add(zzz, (Object) null);
        objectEncoderContext2.add(zzA, (Object) null);
        objectEncoderContext2.add(zzB, (Object) null);
        objectEncoderContext2.add(zzC, (Object) null);
        objectEncoderContext2.add(zzD, (Object) null);
        objectEncoderContext2.add(zzE, (Object) null);
        objectEncoderContext2.add(zzF, (Object) null);
        objectEncoderContext2.add(zzG, (Object) null);
        objectEncoderContext2.add(zzH, (Object) null);
        objectEncoderContext2.add(zzI, (Object) null);
        objectEncoderContext2.add(zzJ, (Object) null);
        objectEncoderContext2.add(zzK, (Object) null);
        objectEncoderContext2.add(zzL, (Object) null);
        objectEncoderContext2.add(zzM, zzihVar.zzb());
        objectEncoderContext2.add(zzN, zzihVar.zza());
        objectEncoderContext2.add(zzO, (Object) null);
        objectEncoderContext2.add(zzP, (Object) null);
        objectEncoderContext2.add(zzQ, (Object) null);
        objectEncoderContext2.add(zzR, (Object) null);
        objectEncoderContext2.add(zzS, (Object) null);
        objectEncoderContext2.add(zzT, (Object) null);
        objectEncoderContext2.add(zzU, (Object) null);
        objectEncoderContext2.add(zzV, (Object) null);
        objectEncoderContext2.add(zzW, (Object) null);
        objectEncoderContext2.add(zzX, (Object) null);
        objectEncoderContext2.add(zzY, (Object) null);
        objectEncoderContext2.add(zzZ, (Object) null);
        objectEncoderContext2.add(zzaa, zzihVar.zzc());
        objectEncoderContext2.add(zzab, (Object) null);
        objectEncoderContext2.add(zzac, (Object) null);
        objectEncoderContext2.add(zzad, (Object) null);
        objectEncoderContext2.add(zzae, (Object) null);
        objectEncoderContext2.add(zzaf, (Object) null);
        objectEncoderContext2.add(zzag, (Object) null);
        objectEncoderContext2.add(zzah, (Object) null);
        objectEncoderContext2.add(zzai, (Object) null);
        objectEncoderContext2.add(zzaj, (Object) null);
        objectEncoderContext2.add(zzak, (Object) null);
        objectEncoderContext2.add(zzal, (Object) null);
        objectEncoderContext2.add(zzam, (Object) null);
        objectEncoderContext2.add(zzan, (Object) null);
        objectEncoderContext2.add(zzao, (Object) null);
    }
}
