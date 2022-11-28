package com.google.android.libraries.places.internal;

import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
final class zzcl {
    @Nullable
    private String description;
    @Nullable
    private Integer distanceMeters;
    @Nullable
    private zzb[] matchedSubstrings;
    @Nullable
    private String placeId;
    @Nullable
    private zza structuredFormatting;
    @Nullable
    private String[] types;

    /* loaded from: classes2.dex */
    class zza {
        @Nullable
        private String mainText;
        @Nullable
        private zzb[] mainTextMatchedSubstrings;
        @Nullable
        private String secondaryText;
        @Nullable
        private zzb[] secondaryTextMatchedSubstrings;

        zza() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Nullable
        public final zzhs zza() {
            zzb[] zzbVarArr = this.mainTextMatchedSubstrings;
            if (zzbVarArr != null) {
                return zzhs.zzl(zzbVarArr);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Nullable
        public final zzhs zzb() {
            zzb[] zzbVarArr = this.secondaryTextMatchedSubstrings;
            if (zzbVarArr != null) {
                return zzhs.zzl(zzbVarArr);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Nullable
        public final String zzc() {
            return this.mainText;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Nullable
        public final String zzd() {
            return this.secondaryText;
        }
    }

    /* loaded from: classes2.dex */
    class zzb {
        @Nullable
        Integer length;
        @Nullable
        Integer offset;

        zzb() {
        }
    }

    zzcl() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final zza zza() {
        return this.structuredFormatting;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final zzhs zzb() {
        zzb[] zzbVarArr = this.matchedSubstrings;
        if (zzbVarArr != null) {
            return zzhs.zzl(zzbVarArr);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final zzhs zzc() {
        String[] strArr = this.types;
        if (strArr != null) {
            return zzhs.zzl(strArr);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final Integer zzd() {
        return this.distanceMeters;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final String zze() {
        return this.description;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final String zzf() {
        return this.placeId;
    }
}
