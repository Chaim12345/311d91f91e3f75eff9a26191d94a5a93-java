package com.google.android.play.core.tasks;

import androidx.annotation.NonNull;
import com.google.android.play.core.internal.zzci;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes2.dex */
public final class Tasks {
    private Tasks() {
    }

    public static <ResultT> ResultT await(@NonNull Task<ResultT> task) {
        zzci.zza(task, "Task must not be null");
        if (task.isComplete()) {
            return (ResultT) zzc(task);
        }
        zzo zzoVar = new zzo(null);
        zzd(task, zzoVar);
        zzoVar.zza();
        return (ResultT) zzc(task);
    }

    public static <ResultT> ResultT await(@NonNull Task<ResultT> task, long j2, @NonNull TimeUnit timeUnit) {
        zzci.zza(task, "Task must not be null");
        zzci.zza(timeUnit, "TimeUnit must not be null");
        if (task.isComplete()) {
            return (ResultT) zzc(task);
        }
        zzo zzoVar = new zzo(null);
        zzd(task, zzoVar);
        if (zzoVar.zzb(j2, timeUnit)) {
            return (ResultT) zzc(task);
        }
        throw new TimeoutException("Timed out waiting for Task");
    }

    public static Task<Void> whenAll(Collection<? extends Task<?>> collection) {
        if (collection.isEmpty()) {
            return zzb(null);
        }
        for (Task<?> task : collection) {
            Objects.requireNonNull(task, "null tasks are not accepted");
        }
        zzm zzmVar = new zzm();
        zzq zzqVar = new zzq(collection.size(), zzmVar);
        for (Task<?> task2 : collection) {
            zzd(task2, zzqVar);
        }
        return zzmVar;
    }

    public static Task zza(Exception exc) {
        zzm zzmVar = new zzm();
        zzmVar.zza(exc);
        return zzmVar;
    }

    public static Task zzb(Object obj) {
        zzm zzmVar = new zzm();
        zzmVar.zzb(obj);
        return zzmVar;
    }

    private static Object zzc(Task task) {
        if (task.isSuccessful()) {
            return task.getResult();
        }
        throw new ExecutionException(task.getException());
    }

    private static void zzd(Task task, zzp zzpVar) {
        Executor executor = TaskExecutors.f7937a;
        task.addOnSuccessListener(executor, zzpVar);
        task.addOnFailureListener(executor, zzpVar);
    }
}
