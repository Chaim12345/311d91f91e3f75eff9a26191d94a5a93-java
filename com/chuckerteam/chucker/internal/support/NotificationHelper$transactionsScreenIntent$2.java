package com.chuckerteam.chucker.internal.support;

import android.app.PendingIntent;
import com.chuckerteam.chucker.api.Chucker;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0004\u001a\n \u0001*\u0004\u0018\u00010\u00000\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroid/app/PendingIntent;", "kotlin.jvm.PlatformType", "invoke", "()Landroid/app/PendingIntent;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class NotificationHelper$transactionsScreenIntent$2 extends Lambda implements Function0<PendingIntent> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ NotificationHelper f4911a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationHelper$transactionsScreenIntent$2(NotificationHelper notificationHelper) {
        super(0);
        this.f4911a = notificationHelper;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final PendingIntent invoke() {
        return PendingIntent.getActivity(this.f4911a.getContext(), 1138, Chucker.getLaunchIntent(this.f4911a.getContext()), 134217728);
    }
}
