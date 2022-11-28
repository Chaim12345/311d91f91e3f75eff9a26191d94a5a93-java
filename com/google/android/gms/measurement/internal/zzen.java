package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamite.descriptors.com.google.mlkit.dynamite.barcode.ModuleDescriptor;
import com.psa.mym.mycitroenconnect.car_console.utils.AutoConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
@VisibleForTesting
/* loaded from: classes2.dex */
public final class zzen {
    public static final zzem zzA;
    public static final zzem zzB;
    public static final zzem zzC;
    public static final zzem zzD;
    public static final zzem zzE;
    public static final zzem zzF;
    public static final zzem zzG;
    public static final zzem zzH;
    public static final zzem zzI;
    public static final zzem zzJ;
    public static final zzem zzK;
    public static final zzem zzL;
    public static final zzem zzM;
    public static final zzem zzN;
    public static final zzem zzO;
    public static final zzem zzP;
    public static final zzem zzQ;
    public static final zzem zzR;
    public static final zzem zzS;
    public static final zzem zzT;
    public static final zzem zzU;
    public static final zzem zzV;
    public static final zzem zzW;
    public static final zzem zzX;
    public static final zzem zzY;
    public static final zzem zzZ;
    public static final zzem zzaA;
    public static final zzem zzaB;
    public static final zzem zzaC;
    public static final zzem zzaD;
    public static final zzem zzaE;
    public static final zzem zzaF;
    public static final zzem zzaG;
    public static final zzem zzaH;
    public static final zzem zzaI;
    public static final zzem zzaJ;
    public static final zzem zzaK;
    public static final zzem zzaL;
    public static final zzem zzaM;
    public static final zzem zzaN;
    public static final zzem zzaO;
    public static final zzem zzaa;
    public static final zzem zzab;
    public static final zzem zzac;
    public static final zzem zzad;
    public static final zzem zzae;
    public static final zzem zzaf;
    public static final zzem zzag;
    public static final zzem zzah;
    public static final zzem zzai;
    public static final zzem zzaj;
    public static final zzem zzak;
    public static final zzem zzal;
    public static final zzem zzam;
    public static final zzem zzan;
    public static final zzem zzao;
    public static final zzem zzap;
    public static final zzem zzaq;
    public static final zzem zzar;
    public static final zzem zzas;
    public static final zzem zzat;
    public static final zzem zzau;
    public static final zzem zzav;
    public static final zzem zzaw;
    public static final zzem zzax;
    public static final zzem zzay;
    public static final zzem zzaz;
    public static final zzem zzm;
    public static final zzem zzn;
    public static final zzem zzo;
    public static final zzem zzp;
    public static final zzem zzq;
    public static final zzem zzr;
    public static final zzem zzs;
    public static final zzem zzt;
    public static final zzem zzu;
    public static final zzem zzv;
    public static final zzem zzw;
    public static final zzem zzx;
    public static final zzem zzy;
    public static final zzem zzz;
    private static final List zzaP = Collections.synchronizedList(new ArrayList());
    private static final Set zzaQ = Collections.synchronizedSet(new HashSet());
    public static final zzem zza = a("measurement.ad_id_cache_time", 10000L, 10000L, zzay.zza);
    public static final zzem zzb = a("measurement.monitoring.sample_period_millis", 86400000L, 86400000L, zzba.zza);
    public static final zzem zzc = a("measurement.config.cache_time", 86400000L, 3600000L, zzbm.zza);
    public static final zzem zzd = a("measurement.config.url_scheme", "https", "https", zzby.zza);
    public static final zzem zze = a("measurement.config.url_authority", "app-measurement.com", "app-measurement.com", zzck.zza);
    public static final zzem zzf = a("measurement.upload.max_bundles", 100, 100, zzcw.zza);
    public static final zzem zzg = a("measurement.upload.max_batch_size", 65536, 65536, zzdi.zza);
    public static final zzem zzh = a("measurement.upload.max_bundle_size", 65536, 65536, zzdu.zza);
    public static final zzem zzi = a("measurement.upload.max_events_per_bundle", 1000, 1000, zzef.zza);
    public static final zzem zzj = a("measurement.upload.max_events_per_day", 100000, 100000, zzeg.zza);
    public static final zzem zzk = a("measurement.upload.max_error_events_per_day", 1000, 1000, zzbj.zza);
    public static final zzem zzl = a("measurement.upload.max_public_events_per_day", 50000, 50000, zzbu.zza);

    static {
        Integer valueOf = Integer.valueOf((int) ModuleDescriptor.MODULE_VERSION);
        zzm = a("measurement.upload.max_conversions_per_day", valueOf, valueOf, zzcf.zza);
        zzn = a("measurement.upload.max_realtime_events_per_day", 10, 10, zzcq.zza);
        zzo = a("measurement.store.max_stored_events_per_app", 100000, 100000, zzdb.zza);
        zzp = a("measurement.upload.url", "https://app-measurement.com/a", "https://app-measurement.com/a", zzdm.zza);
        zzq = a("measurement.upload.backoff_period", 43200000L, 43200000L, zzdx.zza);
        zzr = a("measurement.upload.window_interval", 3600000L, 3600000L, zzeh.zza);
        zzs = a("measurement.upload.interval", 3600000L, 3600000L, zzei.zza);
        zzt = a("measurement.upload.realtime_upload_interval", 10000L, 10000L, zzaz.zza);
        zzu = a("measurement.upload.debug_upload_interval", 1000L, 1000L, zzbb.zza);
        zzv = a("measurement.upload.minimum_delay", 500L, 500L, zzbc.zza);
        Long valueOf2 = Long.valueOf((long) AutoConstants.UPDATE_INTERVAL_IN_MILLISECONDS);
        zzw = a("measurement.alarm_manager.minimum_interval", valueOf2, valueOf2, zzbd.zza);
        zzx = a("measurement.upload.stale_data_deletion_interval", 86400000L, 86400000L, zzbe.zza);
        zzy = a("measurement.upload.refresh_blacklisted_config_interval", 604800000L, 604800000L, zzbf.zza);
        zzz = a("measurement.upload.initial_upload_delay_time", 15000L, 15000L, zzbg.zza);
        zzA = a("measurement.upload.retry_time", 1800000L, 1800000L, zzbh.zza);
        zzB = a("measurement.upload.retry_count", 6, 6, zzbi.zza);
        zzC = a("measurement.upload.max_queue_time", 2419200000L, 2419200000L, zzbk.zza);
        zzD = a("measurement.lifetimevalue.max_currency_tracked", 4, 4, zzbl.zza);
        zzE = a("measurement.audience.filter_result_max_count", 200, 200, zzbn.zza);
        zzF = a("measurement.upload.max_public_user_properties", 25, 25, null);
        zzG = a("measurement.upload.max_event_name_cardinality", 500, 500, null);
        zzH = a("measurement.upload.max_public_event_params", 25, 25, null);
        zzI = a("measurement.service_client.idle_disconnect_millis", 5000L, 5000L, zzbo.zza);
        Boolean bool = Boolean.FALSE;
        zzJ = a("measurement.test.boolean_flag", bool, bool, zzbp.zza);
        zzK = a("measurement.test.string_flag", "---", "---", zzbq.zza);
        zzL = a("measurement.test.long_flag", -1L, -1L, zzbr.zza);
        zzM = a("measurement.test.int_flag", -2, -2, zzbs.zza);
        Double valueOf3 = Double.valueOf(-3.0d);
        zzN = a("measurement.test.double_flag", valueOf3, valueOf3, zzbt.zza);
        zzO = a("measurement.experiment.max_ids", 50, 50, zzbv.zza);
        zzP = a("measurement.max_bundles_per_iteration", 100, 100, zzbw.zza);
        zzQ = a("measurement.sdk.attribution.cache.ttl", 604800000L, 604800000L, zzbx.zza);
        zzR = a("measurement.redaction.app_instance_id.ttl", 7200000L, 7200000L, zzbz.zza);
        Boolean bool2 = Boolean.TRUE;
        zzS = a("measurement.validation.internal_limits_internal_event_params", bool2, bool2, zzca.zza);
        zzT = a("measurement.collection.log_event_and_bundle_v2", bool2, bool2, zzcb.zza);
        zzU = a("measurement.quality.checksum", bool, bool, null);
        zzV = a("measurement.audience.use_bundle_end_timestamp_for_non_sequence_property_filters", bool, bool, zzcc.zza);
        zzW = a("measurement.audience.refresh_event_count_filters_timestamp", bool, bool, zzcd.zza);
        zzX = a("measurement.audience.use_bundle_timestamp_for_event_count_filters", bool, bool, zzce.zza);
        zzY = a("measurement.sdk.collection.retrieve_deeplink_from_bow_2", bool2, bool2, zzcg.zza);
        zzZ = a("measurement.sdk.collection.last_deep_link_referrer_campaign2", bool, bool, zzch.zza);
        zzaa = a("measurement.lifecycle.app_in_background_parameter", bool, bool, zzci.zza);
        zzab = a("measurement.integration.disable_firebase_instance_id", bool, bool, zzcj.zza);
        zzac = a("measurement.collection.service.update_with_analytics_fix", bool, bool, zzcl.zza);
        zzad = a("measurement.client.firebase_feature_rollout.v1.enable", bool2, bool2, zzcm.zza);
        zzae = a("measurement.client.sessions.check_on_reset_and_enable2", bool2, bool2, zzcn.zza);
        zzaf = a("measurement.scheduler.task_thread.cleanup_on_exit", bool, bool, zzco.zza);
        zzag = a("measurement.collection.synthetic_data_mitigation", bool, bool, zzcp.zza);
        zzah = a("measurement.androidId.delete_feature", bool2, bool2, zzcr.zza);
        zzai = a("measurement.service.storage_consent_support_version", 203600, 203600, zzcs.zza);
        zzaj = a("measurement.client.click_identifier_control.dev", bool, bool, zzct.zza);
        zzak = a("measurement.service.click_identifier_control", bool, bool, zzcu.zza);
        zzal = a("measurement.client.consent.gmpappid_worker_thread_fix", bool2, bool2, zzcv.zza);
        zzam = a("measurement.module.pixie.fix_array", bool2, bool2, zzcx.zza);
        zzan = a("measurement.adid_zero.service", bool2, bool2, zzcy.zza);
        zzao = a("measurement.adid_zero.remove_lair_if_adidzero_false", bool2, bool2, zzcz.zza);
        zzap = a("measurement.adid_zero.remove_lair_if_userid_cleared", bool2, bool2, zzda.zza);
        zzaq = a("measurement.adid_zero.remove_lair_on_id_value_change_only", bool2, bool2, zzdc.zza);
        zzar = a("measurement.adid_zero.adid_uid", bool2, bool2, zzdd.zza);
        zzas = a("measurement.adid_zero.app_instance_id_fix", bool2, bool2, zzde.zza);
        zzat = a("measurement.service.refactor.package_side_screen", bool2, bool2, zzdf.zza);
        zzau = a("measurement.enhanced_campaign.service", bool2, bool2, zzdg.zza);
        zzav = a("measurement.enhanced_campaign.client", bool2, bool2, zzdh.zza);
        zzaw = a("measurement.enhanced_campaign.srsltid.client", bool2, bool2, zzdj.zza);
        zzax = a("measurement.enhanced_campaign.srsltid.service", bool2, bool2, zzdk.zza);
        zzay = a("measurement.service.store_null_safelist", bool2, bool2, zzdl.zza);
        zzaz = a("measurement.service.store_safelist", bool2, bool2, zzdn.zza);
        zzaA = a("measurement.redaction.no_aiid_in_config_request", bool2, bool2, zzdo.zza);
        zzaB = a("measurement.redaction.config_redacted_fields", bool2, bool2, zzdp.zza);
        zzaC = a("measurement.redaction.upload_redacted_fields", bool2, bool2, zzdq.zza);
        zzaD = a("measurement.redaction.upload_subdomain_override", bool2, bool2, zzdr.zza);
        zzaE = a("measurement.redaction.device_info", bool2, bool2, zzds.zza);
        zzaF = a("measurement.redaction.user_id", bool2, bool2, zzdt.zza);
        zzaG = a("measurement.redaction.google_signals", bool2, bool2, zzdv.zza);
        zzaH = a("measurement.collection.enable_session_stitching_token.service", bool, bool, zzdw.zza);
        zzaI = a("measurement.collection.enable_session_stitching_token.client.dev", bool, bool, zzdy.zza);
        zzaJ = a("measurement.redaction.app_instance_id", bool2, bool2, zzdz.zza);
        zzaK = a("measurement.redaction.populate_ephemeral_app_instance_id", bool2, bool2, zzea.zza);
        zzaL = a("measurement.redaction.enhanced_uid", bool2, bool2, zzeb.zza);
        zzaM = a("measurement.redaction.e_tag", bool, bool, zzec.zza);
        zzaN = a("measurement.redaction.client_ephemeral_aiid_generation", bool2, bool2, zzed.zza);
        zzaO = a("measurement.audience.dynamic_filters.oob_fix", bool2, bool2, zzee.zza);
    }

    @VisibleForTesting
    static zzem a(String str, Object obj, Object obj2, zzej zzejVar) {
        zzem zzemVar = new zzem(str, obj, obj2, zzejVar, null);
        zzaP.add(zzemVar);
        return zzemVar;
    }

    public static Map zzc(Context context) {
        com.google.android.gms.internal.measurement.zzhe zza2 = com.google.android.gms.internal.measurement.zzhe.zza(context.getContentResolver(), com.google.android.gms.internal.measurement.zzho.zza("com.google.android.gms.measurement"));
        return zza2 == null ? Collections.emptyMap() : zza2.zzc();
    }
}