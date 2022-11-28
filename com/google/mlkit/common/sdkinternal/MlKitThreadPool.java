package com.google.mlkit.common.sdkinternal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzbb;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
@KeepForSdk
/* loaded from: classes2.dex */
public class MlKitThreadPool extends zzbb {
    private static final ThreadLocal zza = new ThreadLocal();
    private final ThreadPoolExecutor zzb;

    public MlKitThreadPool() {
        final ThreadFactory defaultThreadFactory = Executors.defaultThreadFactory();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(availableProcessors, availableProcessors, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactory() { // from class: com.google.mlkit.common.sdkinternal.zzk
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(final Runnable runnable) {
                return defaultThreadFactory.newThread(new Runnable() { // from class: com.google.mlkit.common.sdkinternal.zzj
                    @Override // java.lang.Runnable
                    public final void run() {
                        MlKitThreadPool.d(runnable);
                    }
                });
            }
        });
        this.zzb = threadPoolExecutor;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void d(Runnable runnable) {
        zza.set(new ArrayDeque());
        runnable.run();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zze(Deque deque, Runnable runnable) {
        Preconditions.checkNotNull(deque);
        deque.add(runnable);
        if (deque.size() <= 1) {
            do {
                runnable.run();
                deque.removeFirst();
                runnable = (Runnable) deque.peekFirst();
            } while (runnable != null);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzbb, com.google.android.gms.internal.mlkit_common.zzaf
    @NonNull
    protected final /* synthetic */ Object a() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzbb
    @NonNull
    protected final ExecutorService b() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzbb, java.util.concurrent.Executor
    public final void execute(@NonNull final Runnable runnable) {
        Deque deque = (Deque) zza.get();
        if (deque == null || deque.size() > 1) {
            this.zzb.execute(new Runnable() { // from class: com.google.mlkit.common.sdkinternal.zzi
                @Override // java.lang.Runnable
                public final void run() {
                    MlKitThreadPool.zze((Deque) MlKitThreadPool.zza.get(), runnable);
                }
            });
        } else {
            zze(deque, runnable);
        }
    }
}
