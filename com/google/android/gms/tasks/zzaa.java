package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes2.dex */
final class zzaa implements Continuation<Void, List> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Collection f7078a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaa(Collection collection) {
        this.f7078a = collection;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* bridge */ /* synthetic */ List then(@NonNull Task<Void> task) {
        ArrayList arrayList = new ArrayList();
        for (Task task2 : this.f7078a) {
            arrayList.add(task2.getResult());
        }
        return arrayList;
    }
}
