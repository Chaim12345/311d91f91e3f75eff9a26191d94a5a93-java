package com.google.android.play.core.review;

import com.google.android.play.core.tasks.zzj;
import java.util.Locale;
/* loaded from: classes2.dex */
public class ReviewException extends zzj {
    private final int zza;

    public ReviewException(int i2) {
        super(String.format(Locale.getDefault(), "Review Error(%d): %s", Integer.valueOf(i2), com.google.android.play.core.review.model.zza.zza(i2)));
        this.zza = i2;
    }

    @Override // com.google.android.play.core.tasks.zzj
    public int getErrorCode() {
        return this.zza;
    }
}
