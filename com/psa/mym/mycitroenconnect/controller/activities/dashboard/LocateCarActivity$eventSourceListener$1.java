package com.psa.mym.mycitroenconnect.controller.activities.dashboard;

import com.google.android.material.card.MaterialCardView;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.apache.http.message.TokenParser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes2.dex */
public final class LocateCarActivity$eventSourceListener$1 extends EventSourceListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ LocateCarActivity f10378a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocateCarActivity$eventSourceListener$1(LocateCarActivity locateCarActivity) {
        this.f10378a = locateCarActivity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onFailure$lambda-1  reason: not valid java name */
    public static final void m92onFailure$lambda1(LocateCarActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String string = this$0.getString(R.string.timeout);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.timeout)");
        ExtensionsKt.showToast(this$0, string);
        ((MaterialCardView) this$0._$_findCachedViewById(com.psa.mym.mycitroenconnect.R.id.cvRouteDetails)).setVisibility(0);
    }

    @Override // okhttp3.sse.EventSourceListener
    public void onClosed(@NotNull EventSource eventSource) {
        Intrinsics.checkNotNullParameter(eventSource, "eventSource");
        super.onClosed(eventSource);
        Logger.INSTANCE.d("Event source closed ....");
    }

    @Override // okhttp3.sse.EventSourceListener
    public void onEvent(@NotNull EventSource eventSource, @Nullable String str, @Nullable String str2, @NotNull String data) {
        Intrinsics.checkNotNullParameter(eventSource, "eventSource");
        Intrinsics.checkNotNullParameter(data, "data");
        super.onEvent(eventSource, str, str2, data);
        LocateCarActivity locateCarActivity = this.f10378a;
        locateCarActivity.setRetryCounter(0);
        locateCarActivity.showMap(data);
        Logger logger = Logger.INSTANCE;
        logger.d("id :" + str + ", type: " + str2 + ", data: " + data);
    }

    @Override // okhttp3.sse.EventSourceListener
    public void onFailure(@NotNull EventSource eventSource, @Nullable Throwable th, @Nullable Response response) {
        Intrinsics.checkNotNullParameter(eventSource, "eventSource");
        super.onFailure(eventSource, th, response);
        if (!this.f10378a.getShouldRetry() || this.f10378a.getRetryCounter() >= this.f10378a.getMaxRetryCount()) {
            final LocateCarActivity locateCarActivity = this.f10378a;
            locateCarActivity.runOnUiThread(new Runnable() { // from class: com.psa.mym.mycitroenconnect.controller.activities.dashboard.g
                @Override // java.lang.Runnable
                public final void run() {
                    LocateCarActivity$eventSourceListener$1.m92onFailure$lambda1(LocateCarActivity.this);
                }
            });
        } else {
            LocateCarActivity locateCarActivity2 = this.f10378a;
            locateCarActivity2.setRetryCounter(locateCarActivity2.getRetryCounter() + 1);
            this.f10378a.startTracking();
        }
        Logger logger = Logger.INSTANCE;
        StringBuilder sb = new StringBuilder();
        sb.append(response);
        sb.append(TokenParser.SP);
        sb.append(th);
        logger.e(sb.toString());
    }

    @Override // okhttp3.sse.EventSourceListener
    public void onOpen(@NotNull EventSource eventSource, @NotNull Response response) {
        Intrinsics.checkNotNullParameter(eventSource, "eventSource");
        Intrinsics.checkNotNullParameter(response, "response");
        super.onOpen(eventSource, response);
        Logger.INSTANCE.d("Event source opened ....");
    }
}
