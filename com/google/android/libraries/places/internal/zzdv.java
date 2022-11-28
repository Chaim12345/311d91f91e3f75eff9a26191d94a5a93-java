package com.google.android.libraries.places.internal;

import androidx.annotation.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzdv {
    @Nullable
    private zza[] addressComponents;
    @Nullable
    private String businessStatus;
    @Nullable
    private String formattedAddress;
    @Nullable
    private zzb geometry;
    @Nullable
    private String icon;
    @Nullable
    private String iconBackgroundColor;
    @Nullable
    private String iconMaskBaseUri;
    @Nullable
    private String internationalPhoneNumber;
    @Nullable
    private String name;
    @Nullable
    private zzc openingHours;
    @Nullable
    private zzd[] photos;
    @Nullable
    private String placeId;
    @Nullable
    private zze plusCode;
    @Nullable
    private Integer priceLevel;
    @Nullable
    private Double rating;
    @Nullable
    private String[] types;
    @Nullable
    private Integer userRatingsTotal;
    @Nullable
    private Integer utcOffset;
    @Nullable
    private String website;

    /* loaded from: classes2.dex */
    class zza {
        @Nullable
        private String longName;
        @Nullable
        private String shortName;
        @Nullable
        private String[] types;

        zza() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Nullable
        public final zzhs zza() {
            String[] strArr = this.types;
            if (strArr != null) {
                return zzhs.zzl(strArr);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Nullable
        public final String zzb() {
            return this.longName;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Nullable
        public final String zzc() {
            return this.shortName;
        }
    }

    /* loaded from: classes2.dex */
    class zzb {
        @Nullable
        private zza location;
        @Nullable
        private C0018zzb viewport;

        /* loaded from: classes2.dex */
        class zza {
            @Nullable
            private Double lat;
            @Nullable
            private Double lng;

            zza() {
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Nullable
            public final Double zza() {
                return this.lat;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Nullable
            public final Double zzb() {
                return this.lng;
            }
        }

        /* renamed from: com.google.android.libraries.places.internal.zzdv$zzb$zzb  reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        class C0018zzb {
            @Nullable
            private zza northeast;
            @Nullable
            private zza southwest;

            C0018zzb() {
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Nullable
            public final zza zza() {
                return this.northeast;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Nullable
            public final zza zzb() {
                return this.southwest;
            }
        }

        zzb() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Nullable
        public final zza zza() {
            return this.location;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Nullable
        public final C0018zzb zzb() {
            return this.viewport;
        }
    }

    /* loaded from: classes2.dex */
    class zzc {
        @Nullable
        private zza[] periods;
        @Nullable
        private String[] weekdayText;

        /* loaded from: classes2.dex */
        class zza {
            @Nullable
            private zzb close;
            @Nullable
            private zzb open;

            zza() {
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Nullable
            public final zzb zza() {
                return this.close;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Nullable
            public final zzb zzb() {
                return this.open;
            }
        }

        /* loaded from: classes2.dex */
        class zzb {
            @Nullable
            private Integer day;
            @Nullable
            private String time;

            zzb() {
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Nullable
            public final Integer zza() {
                return this.day;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Nullable
            public final String zzb() {
                return this.time;
            }
        }

        zzc() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Nullable
        public final zzhs zza() {
            zza[] zzaVarArr = this.periods;
            if (zzaVarArr != null) {
                return zzhs.zzl(zzaVarArr);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Nullable
        public final zzhs zzb() {
            String[] strArr = this.weekdayText;
            if (strArr != null) {
                return zzhs.zzl(strArr);
            }
            return null;
        }
    }

    /* loaded from: classes2.dex */
    class zzd {
        @Nullable
        private Integer height;
        @Nullable
        private String[] htmlAttributions;
        @Nullable
        private String photoReference;
        @Nullable
        private Integer width;

        zzd() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Nullable
        public final zzhs zza() {
            String[] strArr = this.htmlAttributions;
            if (strArr != null) {
                return zzhs.zzl(strArr);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Nullable
        public final Integer zzb() {
            return this.height;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Nullable
        public final Integer zzc() {
            return this.width;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Nullable
        public final String zzd() {
            return this.photoReference;
        }
    }

    /* loaded from: classes2.dex */
    class zze {
        @Nullable
        private String compoundCode;
        @Nullable
        private String globalCode;

        zze() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Nullable
        public final String zza() {
            return this.compoundCode;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Nullable
        public final String zzb() {
            return this.globalCode;
        }
    }

    zzdv() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final zzb zza() {
        return this.geometry;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final zzc zzb() {
        return this.openingHours;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final zze zzc() {
        return this.plusCode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final zzhs zzd() {
        zza[] zzaVarArr = this.addressComponents;
        if (zzaVarArr != null) {
            return zzhs.zzl(zzaVarArr);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final zzhs zze() {
        zzd[] zzdVarArr = this.photos;
        if (zzdVarArr != null) {
            return zzhs.zzl(zzdVarArr);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final zzhs zzf() {
        String[] strArr = this.types;
        if (strArr != null) {
            return zzhs.zzl(strArr);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final Double zzg() {
        return this.rating;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final Integer zzh() {
        return this.priceLevel;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final Integer zzi() {
        return this.userRatingsTotal;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final Integer zzj() {
        return this.utcOffset;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final String zzk() {
        return this.businessStatus;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final String zzl() {
        return this.formattedAddress;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final String zzm() {
        return this.iconBackgroundColor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final String zzn() {
        return this.iconMaskBaseUri;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final String zzo() {
        return this.internationalPhoneNumber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final String zzp() {
        return this.name;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final String zzq() {
        return this.placeId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final String zzr() {
        return this.website;
    }
}
