package com.google.android.play.core.splitinstall;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/* loaded from: classes2.dex */
public class SplitInstallRequest {
    private final List zza;
    private final List zzb;

    /* loaded from: classes2.dex */
    public static class Builder {
        private final List zza = new ArrayList();
        private final List zzb = new ArrayList();

        private Builder() {
        }

        /* synthetic */ Builder(zzai zzaiVar) {
        }

        @NonNull
        public Builder addLanguage(@Nullable Locale locale) {
            this.zzb.add(locale);
            return this;
        }

        public Builder addModule(String str) {
            this.zza.add(str);
            return this;
        }

        @NonNull
        public SplitInstallRequest build() {
            return new SplitInstallRequest(this, null);
        }
    }

    /* synthetic */ SplitInstallRequest(Builder builder, zzaj zzajVar) {
        this.zza = new ArrayList(builder.zza);
        this.zzb = new ArrayList(builder.zzb);
    }

    @NonNull
    public static Builder newBuilder() {
        return new Builder(null);
    }

    public List<Locale> getLanguages() {
        return this.zzb;
    }

    public List<String> getModuleNames() {
        return this.zza;
    }

    public String toString() {
        return String.format("SplitInstallRequest{modulesNames=%s,languages=%s}", this.zza, this.zzb);
    }
}
