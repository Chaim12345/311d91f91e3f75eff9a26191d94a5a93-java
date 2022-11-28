package org.greenrobot.eventbus;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public final class Subscription {

    /* renamed from: a  reason: collision with root package name */
    final Object f15190a;

    /* renamed from: b  reason: collision with root package name */
    final SubscriberMethod f15191b;

    /* renamed from: c  reason: collision with root package name */
    volatile boolean f15192c = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Subscription(Object obj, SubscriberMethod subscriberMethod) {
        this.f15190a = obj;
        this.f15191b = subscriberMethod;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Subscription) {
            Subscription subscription = (Subscription) obj;
            return this.f15190a == subscription.f15190a && this.f15191b.equals(subscription.f15191b);
        }
        return false;
    }

    public int hashCode() {
        return this.f15190a.hashCode() + this.f15191b.f15182f.hashCode();
    }
}
