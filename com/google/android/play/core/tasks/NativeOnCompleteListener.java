package com.google.android.play.core.tasks;

import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
public class NativeOnCompleteListener implements OnCompleteListener<Object> {
    private final long zza;
    private final int zzb;

    public NativeOnCompleteListener(long j2, int i2) {
        this.zza = j2;
        this.zzb = i2;
    }

    public native void nativeOnComplete(long j2, int i2, @Nullable Object obj, int i3);

    @Override // com.google.android.play.core.tasks.OnCompleteListener
    public void onComplete(Task<Object> task) {
        if (!task.isComplete()) {
            int i2 = this.zzb;
            StringBuilder sb = new StringBuilder(50);
            sb.append("onComplete called for incomplete task: ");
            sb.append(i2);
            throw new IllegalStateException(sb.toString());
        } else if (task.isSuccessful()) {
            nativeOnComplete(this.zza, this.zzb, task.getResult(), 0);
        } else {
            Exception exception = task.getException();
            if (!(exception instanceof zzj)) {
                nativeOnComplete(this.zza, this.zzb, null, -100);
                return;
            }
            int errorCode = ((zzj) exception).getErrorCode();
            if (errorCode != 0) {
                nativeOnComplete(this.zza, this.zzb, null, errorCode);
                return;
            }
            int i3 = this.zzb;
            StringBuilder sb2 = new StringBuilder(51);
            sb2.append("TaskException has error code 0 on task: ");
            sb2.append(i3);
            throw new IllegalStateException(sb2.toString());
        }
    }
}
