package org.greenrobot.eventbus;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes4.dex */
final class PendingPost {
    private static final List<PendingPost> pendingPostPool = new ArrayList();

    /* renamed from: a  reason: collision with root package name */
    Object f15174a;

    /* renamed from: b  reason: collision with root package name */
    Subscription f15175b;

    /* renamed from: c  reason: collision with root package name */
    PendingPost f15176c;

    private PendingPost(Object obj, Subscription subscription) {
        this.f15174a = obj;
        this.f15175b = subscription;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PendingPost a(Subscription subscription, Object obj) {
        List<PendingPost> list = pendingPostPool;
        synchronized (list) {
            int size = list.size();
            if (size > 0) {
                PendingPost remove = list.remove(size - 1);
                remove.f15174a = obj;
                remove.f15175b = subscription;
                remove.f15176c = null;
                return remove;
            }
            return new PendingPost(obj, subscription);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(PendingPost pendingPost) {
        pendingPost.f15174a = null;
        pendingPost.f15175b = null;
        pendingPost.f15176c = null;
        List<PendingPost> list = pendingPostPool;
        synchronized (list) {
            if (list.size() < 10000) {
                list.add(pendingPost);
            }
        }
    }
}
