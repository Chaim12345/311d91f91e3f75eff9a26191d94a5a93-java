package com.google.android.gms.dynamic;

import java.util.Iterator;
import java.util.LinkedList;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zaa implements OnDelegateCreatedListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ DeferredLifecycleHelper f5828a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zaa(DeferredLifecycleHelper deferredLifecycleHelper) {
        this.f5828a = deferredLifecycleHelper;
    }

    @Override // com.google.android.gms.dynamic.OnDelegateCreatedListener
    public final void onDelegateCreated(LifecycleDelegate lifecycleDelegate) {
        LinkedList linkedList;
        LinkedList linkedList2;
        LifecycleDelegate lifecycleDelegate2;
        this.f5828a.zaa = lifecycleDelegate;
        linkedList = this.f5828a.zac;
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            lifecycleDelegate2 = this.f5828a.zaa;
            ((zah) it.next()).zab(lifecycleDelegate2);
        }
        linkedList2 = this.f5828a.zac;
        linkedList2.clear();
        this.f5828a.zab = null;
    }
}
