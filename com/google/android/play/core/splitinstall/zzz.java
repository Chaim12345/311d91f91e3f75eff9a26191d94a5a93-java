package com.google.android.play.core.splitinstall;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import com.google.android.play.core.common.IntentSenderForResultStarter;
/* loaded from: classes2.dex */
final class zzz implements IntentSenderForResultStarter {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Activity f7936a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzz(zzaa zzaaVar, Activity activity) {
        this.f7936a = activity;
    }

    @Override // com.google.android.play.core.common.IntentSenderForResultStarter
    public final void startIntentSenderForResult(IntentSender intentSender, int i2, Intent intent, int i3, int i4, int i5, Bundle bundle) {
        this.f7936a.startIntentSenderForResult(intentSender, i2, intent, i3, i4, i5);
    }
}
