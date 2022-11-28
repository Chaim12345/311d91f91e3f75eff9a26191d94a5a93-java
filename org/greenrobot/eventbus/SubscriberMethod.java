package org.greenrobot.eventbus;

import java.lang.reflect.Method;
/* loaded from: classes4.dex */
public class SubscriberMethod {

    /* renamed from: a  reason: collision with root package name */
    final Method f15177a;

    /* renamed from: b  reason: collision with root package name */
    final ThreadMode f15178b;

    /* renamed from: c  reason: collision with root package name */
    final Class f15179c;

    /* renamed from: d  reason: collision with root package name */
    final int f15180d;

    /* renamed from: e  reason: collision with root package name */
    final boolean f15181e;

    /* renamed from: f  reason: collision with root package name */
    String f15182f;

    public SubscriberMethod(Method method, Class<?> cls, ThreadMode threadMode, int i2, boolean z) {
        this.f15177a = method;
        this.f15178b = threadMode;
        this.f15179c = cls;
        this.f15180d = i2;
        this.f15181e = z;
    }

    private synchronized void checkMethodString() {
        if (this.f15182f == null) {
            StringBuilder sb = new StringBuilder(64);
            sb.append(this.f15177a.getDeclaringClass().getName());
            sb.append('#');
            sb.append(this.f15177a.getName());
            sb.append('(');
            sb.append(this.f15179c.getName());
            this.f15182f = sb.toString();
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SubscriberMethod) {
            checkMethodString();
            SubscriberMethod subscriberMethod = (SubscriberMethod) obj;
            subscriberMethod.checkMethodString();
            return this.f15182f.equals(subscriberMethod.f15182f);
        }
        return false;
    }

    public int hashCode() {
        return this.f15177a.hashCode();
    }
}
