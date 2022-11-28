package com.psa.mym.mycitroenconnect.controller.fragments;

import androidx.fragment.app.FragmentActivity;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.apache.http.message.TokenParser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class HomeFragment$eventSourceListener$1 extends EventSourceListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ HomeFragment f10505a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HomeFragment$eventSourceListener$1(HomeFragment homeFragment) {
        this.f10505a = homeFragment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onFailure$lambda-2$lambda-1  reason: not valid java name */
    public static final void m135onFailure$lambda2$lambda1(FragmentActivity it) {
        Intrinsics.checkNotNullParameter(it, "$it");
        ExtensionsKt.showToast(it, " Timeout ");
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
        HomeFragment homeFragment = this.f10505a;
        homeFragment.retryCounter = 0;
        homeFragment.displayIgnitionStatus(data);
        Logger logger = Logger.INSTANCE;
        logger.d("id :" + str + ", type: " + str2 + ", data: " + data);
    }

    @Override // okhttp3.sse.EventSourceListener
    public void onFailure(@NotNull EventSource eventSource, @Nullable Throwable th, @Nullable Response response) {
        boolean z;
        final FragmentActivity activity;
        int i2;
        int i3;
        EventSource eventSource2;
        int i4;
        Intrinsics.checkNotNullParameter(eventSource, "eventSource");
        super.onFailure(eventSource, th, response);
        z = this.f10505a.shouldRetry;
        if (z) {
            i2 = this.f10505a.retryCounter;
            i3 = this.f10505a.maxRetryCount;
            if (i2 < i3) {
                eventSource2 = this.f10505a.sseEventSource;
                if (eventSource2 != null) {
                    if (this.f10505a.getActivity() == null || !this.f10505a.isAdded()) {
                        return;
                    }
                    HomeFragment homeFragment = this.f10505a;
                    i4 = homeFragment.retryCounter;
                    homeFragment.retryCounter = i4 + 1;
                    this.f10505a.startStreaming();
                    return;
                }
            }
        }
        if (this.f10505a.isAdded() && this.f10505a.getActivity() != null && (activity = this.f10505a.getActivity()) != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.j
                @Override // java.lang.Runnable
                public final void run() {
                    HomeFragment$eventSourceListener$1.m135onFailure$lambda2$lambda1(FragmentActivity.this);
                }
            });
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
