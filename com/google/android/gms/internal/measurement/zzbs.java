package com.google.android.gms.internal.measurement;

import android.os.Build;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
/* loaded from: classes.dex */
public final class zzbs {
    public static final int zza;

    static {
        zza = Build.VERSION.SDK_INT >= 31 ? MediaHttpDownloader.MAXIMUM_CHUNK_SIZE : 0;
    }
}
