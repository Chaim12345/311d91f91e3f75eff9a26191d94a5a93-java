package org.greenrobot.eventbus.meta;

import org.greenrobot.eventbus.SubscriberMethod;
/* loaded from: classes4.dex */
public class SimpleSubscriberInfo extends AbstractSubscriberInfo {
    private final SubscriberMethodInfo[] methodInfos;

    public SimpleSubscriberInfo(Class cls, boolean z, SubscriberMethodInfo[] subscriberMethodInfoArr) {
        super(cls, null, z);
        this.methodInfos = subscriberMethodInfoArr;
    }

    @Override // org.greenrobot.eventbus.meta.SubscriberInfo
    public synchronized SubscriberMethod[] getSubscriberMethods() {
        SubscriberMethod[] subscriberMethodArr;
        int length = this.methodInfos.length;
        subscriberMethodArr = new SubscriberMethod[length];
        for (int i2 = 0; i2 < length; i2++) {
            SubscriberMethodInfo subscriberMethodInfo = this.methodInfos[i2];
            subscriberMethodArr[i2] = a(subscriberMethodInfo.f15193a, subscriberMethodInfo.f15195c, subscriberMethodInfo.f15194b, subscriberMethodInfo.f15196d, subscriberMethodInfo.f15197e);
        }
        return subscriberMethodArr;
    }
}
