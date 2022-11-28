package com.google.android.gms.common.api.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zaac implements OnCompleteListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ TaskCompletionSource f5634a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zaad f5635b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zaac(zaad zaadVar, TaskCompletionSource taskCompletionSource) {
        this.f5635b = zaadVar;
        this.f5634a = taskCompletionSource;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(@NonNull Task task) {
        Map map;
        map = this.f5635b.zab;
        map.remove(this.f5634a);
    }
}
