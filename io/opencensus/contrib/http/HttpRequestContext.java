package io.opencensus.contrib.http;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import io.opencensus.tags.TagContext;
import io.opencensus.tags.TagMetadata;
import io.opencensus.trace.Span;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes3.dex */
public class HttpRequestContext {

    /* renamed from: h  reason: collision with root package name */
    static final TagMetadata f10954h = TagMetadata.create(TagMetadata.TagTtl.NO_PROPAGATION);
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    final long f10955a;
    @VisibleForTesting

    /* renamed from: b  reason: collision with root package name */
    final Span f10956b;
    @VisibleForTesting

    /* renamed from: c  reason: collision with root package name */
    AtomicLong f10957c = new AtomicLong();
    @VisibleForTesting

    /* renamed from: d  reason: collision with root package name */
    AtomicLong f10958d = new AtomicLong();
    @VisibleForTesting

    /* renamed from: e  reason: collision with root package name */
    AtomicLong f10959e = new AtomicLong();
    @VisibleForTesting

    /* renamed from: f  reason: collision with root package name */
    AtomicLong f10960f = new AtomicLong();
    @VisibleForTesting

    /* renamed from: g  reason: collision with root package name */
    final TagContext f10961g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpRequestContext(Span span, TagContext tagContext) {
        Preconditions.checkNotNull(span, "span");
        Preconditions.checkNotNull(tagContext, "tagContext");
        this.f10956b = span;
        this.f10961g = tagContext;
        this.f10955a = System.nanoTime();
    }
}
