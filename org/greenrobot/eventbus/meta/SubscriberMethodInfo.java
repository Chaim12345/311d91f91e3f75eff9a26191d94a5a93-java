package org.greenrobot.eventbus.meta;

import org.greenrobot.eventbus.ThreadMode;
/* loaded from: classes4.dex */
public class SubscriberMethodInfo {

    /* renamed from: a  reason: collision with root package name */
    final String f15193a;

    /* renamed from: b  reason: collision with root package name */
    final ThreadMode f15194b;

    /* renamed from: c  reason: collision with root package name */
    final Class f15195c;

    /* renamed from: d  reason: collision with root package name */
    final int f15196d;

    /* renamed from: e  reason: collision with root package name */
    final boolean f15197e;

    public SubscriberMethodInfo(String str, Class<?> cls) {
        this(str, cls, ThreadMode.POSTING, 0, false);
    }

    public SubscriberMethodInfo(String str, Class<?> cls, ThreadMode threadMode) {
        this(str, cls, threadMode, 0, false);
    }

    public SubscriberMethodInfo(String str, Class<?> cls, ThreadMode threadMode, int i2, boolean z) {
        this.f15193a = str;
        this.f15194b = threadMode;
        this.f15195c = cls;
        this.f15196d = i2;
        this.f15197e = z;
    }
}
