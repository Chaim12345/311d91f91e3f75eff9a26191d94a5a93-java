package com.google.android.play.core.splitinstall.testing;

import android.content.Context;
import androidx.annotation.Nullable;
import com.google.android.play.core.common.LocalTestingException;
import com.google.android.play.core.internal.zzco;
import com.google.android.play.core.splitcompat.SplitCompat;
import java.io.File;
/* loaded from: classes2.dex */
public final class FakeSplitInstallManagerFactory {
    @Nullable
    private static FakeSplitInstallManager zza;

    private FakeSplitInstallManagerFactory() {
    }

    public static FakeSplitInstallManager create(Context context) {
        try {
            File zzb = com.google.android.play.core.splitinstall.zzu.zza(context).zzb();
            if (zzb != null) {
                if (zzb.exists()) {
                    return create(context, zzb);
                }
                throw new LocalTestingException(String.format("Local testing directory not found: %s", zzb));
            }
            throw new LocalTestingException("Failed to retrieve local testing directory path");
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public static synchronized FakeSplitInstallManager create(Context context, File file) {
        FakeSplitInstallManager fakeSplitInstallManager;
        synchronized (FakeSplitInstallManagerFactory.class) {
            FakeSplitInstallManager fakeSplitInstallManager2 = zza;
            if (fakeSplitInstallManager2 == null) {
                zza = createNewInstance(context, file);
            } else if (!fakeSplitInstallManager2.a().getAbsolutePath().equals(file.getAbsolutePath())) {
                throw new RuntimeException(String.format("Different module directories used to initialize FakeSplitInstallManager: '%s' and '%s'", zza.a().getAbsolutePath(), file.getAbsolutePath()));
            }
            fakeSplitInstallManager = zza;
        }
        return fakeSplitInstallManager;
    }

    public static FakeSplitInstallManager createNewInstance(Context context, final File file) {
        SplitCompat.install(context);
        return new FakeSplitInstallManager(context, file, new com.google.android.play.core.splitinstall.zzs(context, context.getPackageName()), new zzco() { // from class: com.google.android.play.core.splitinstall.testing.zzq
            @Override // com.google.android.play.core.internal.zzco
            public final Object zza() {
                return zzy.zza(file);
            }
        });
    }
}
