package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzab implements Continuation<Void, Task<List<Task<?>>>> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Collection f7079a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzab(Collection collection) {
        this.f7079a = collection;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* bridge */ /* synthetic */ Task<List<Task<?>>> then(@NonNull Task<Void> task) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f7079a);
        return Tasks.forResult(arrayList);
    }
}
