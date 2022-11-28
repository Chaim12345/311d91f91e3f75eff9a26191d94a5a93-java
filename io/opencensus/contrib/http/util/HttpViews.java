package io.opencensus.contrib.http.util;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import io.opencensus.stats.Stats;
import io.opencensus.stats.View;
import io.opencensus.stats.ViewManager;
/* loaded from: classes3.dex */
public final class HttpViews {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    static final ImmutableSet f10969a = ImmutableSet.of(HttpViewConstants.HTTP_SERVER_COMPLETED_COUNT_VIEW, HttpViewConstants.HTTP_SERVER_SENT_BYTES_VIEW, HttpViewConstants.HTTP_SERVER_RECEIVED_BYTES_VIEW, HttpViewConstants.HTTP_SERVER_LATENCY_VIEW);
    @VisibleForTesting

    /* renamed from: b  reason: collision with root package name */
    static final ImmutableSet f10970b = ImmutableSet.of(HttpViewConstants.HTTP_CLIENT_COMPLETED_COUNT_VIEW, HttpViewConstants.HTTP_CLIENT_RECEIVED_BYTES_VIEW, HttpViewConstants.HTTP_CLIENT_SENT_BYTES_VIEW, HttpViewConstants.HTTP_CLIENT_ROUNDTRIP_LATENCY_VIEW);

    private HttpViews() {
    }

    @VisibleForTesting
    static void a(ViewManager viewManager) {
        UnmodifiableIterator it = f10970b.iterator();
        while (it.hasNext()) {
            viewManager.registerView((View) it.next());
        }
    }

    @VisibleForTesting
    static void b(ViewManager viewManager) {
        UnmodifiableIterator it = f10969a.iterator();
        while (it.hasNext()) {
            viewManager.registerView((View) it.next());
        }
    }

    @VisibleForTesting
    static void c(ViewManager viewManager) {
        a(viewManager);
        b(viewManager);
    }

    public static final void registerAllClientViews() {
        a(Stats.getViewManager());
    }

    public static final void registerAllServerViews() {
        b(Stats.getViewManager());
    }

    public static final void registerAllViews() {
        c(Stats.getViewManager());
    }
}
