package com.chuckerteam.chucker.internal.support;

import android.app.PendingIntent;
import com.chuckerteam.chucker.api.Chucker;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0004\u001a\n \u0001*\u0004\u0018\u00010\u00000\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroid/app/PendingIntent;", "kotlin.jvm.PlatformType", "invoke", "()Landroid/app/PendingIntent;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class NotificationHelper$errorsScreenIntent$2 extends Lambda implements Function0<PendingIntent> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ NotificationHelper f4910a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationHelper$errorsScreenIntent$2(NotificationHelper notificationHelper) {
        super(0);
        this.f4910a = notificationHelper;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final PendingIntent invoke() {
        return PendingIntent.getActivity(this.f4910a.getContext(), 3546, Chucker.getLaunchIntent(this.f4910a.getContext(), 2), 134217728);
    }
}
