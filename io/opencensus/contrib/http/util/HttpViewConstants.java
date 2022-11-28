package io.opencensus.contrib.http.util;

import com.google.common.annotations.VisibleForTesting;
import io.opencensus.stats.Aggregation;
import io.opencensus.stats.BucketBoundaries;
import io.opencensus.stats.Measure;
import io.opencensus.stats.View;
import io.opencensus.tags.TagKey;
import java.util.Arrays;
import java.util.Collections;
/* loaded from: classes3.dex */
public final class HttpViewConstants {
    public static final View HTTP_CLIENT_COMPLETED_COUNT_VIEW;
    public static final View HTTP_CLIENT_RECEIVED_BYTES_VIEW;
    public static final View HTTP_CLIENT_ROUNDTRIP_LATENCY_VIEW;
    public static final View HTTP_CLIENT_SENT_BYTES_VIEW;
    public static final View HTTP_SERVER_COMPLETED_COUNT_VIEW;
    public static final View HTTP_SERVER_LATENCY_VIEW;
    public static final View HTTP_SERVER_RECEIVED_BYTES_VIEW;
    public static final View HTTP_SERVER_SENT_BYTES_VIEW;
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    static final Aggregation f10966a;
    @VisibleForTesting

    /* renamed from: b  reason: collision with root package name */
    static final Aggregation f10967b;
    @VisibleForTesting

    /* renamed from: c  reason: collision with root package name */
    static final Aggregation f10968c;

    static {
        Aggregation.Count create = Aggregation.Count.create();
        f10966a = create;
        Double valueOf = Double.valueOf(0.0d);
        Aggregation.Distribution create2 = Aggregation.Distribution.create(BucketBoundaries.create(Collections.unmodifiableList(Arrays.asList(valueOf, Double.valueOf(1024.0d), Double.valueOf(2048.0d), Double.valueOf(4096.0d), Double.valueOf(16384.0d), Double.valueOf(65536.0d), Double.valueOf(262144.0d), Double.valueOf(1048576.0d), Double.valueOf(4194304.0d), Double.valueOf(1.6777216E7d), Double.valueOf(6.7108864E7d), Double.valueOf(2.68435456E8d), Double.valueOf(1.073741824E9d), Double.valueOf(4.294967296E9d)))));
        f10967b = create2;
        Aggregation.Distribution create3 = Aggregation.Distribution.create(BucketBoundaries.create(Collections.unmodifiableList(Arrays.asList(valueOf, Double.valueOf(1.0d), Double.valueOf(2.0d), Double.valueOf(3.0d), Double.valueOf(4.0d), Double.valueOf(5.0d), Double.valueOf(6.0d), Double.valueOf(8.0d), Double.valueOf(10.0d), Double.valueOf(13.0d), Double.valueOf(16.0d), Double.valueOf(20.0d), Double.valueOf(25.0d), Double.valueOf(30.0d), Double.valueOf(40.0d), Double.valueOf(50.0d), Double.valueOf(65.0d), Double.valueOf(80.0d), Double.valueOf(100.0d), Double.valueOf(130.0d), Double.valueOf(160.0d), Double.valueOf(200.0d), Double.valueOf(250.0d), Double.valueOf(300.0d), Double.valueOf(400.0d), Double.valueOf(500.0d), Double.valueOf(650.0d), Double.valueOf(800.0d), Double.valueOf(1000.0d), Double.valueOf(2000.0d), Double.valueOf(5000.0d), Double.valueOf(10000.0d), Double.valueOf(20000.0d), Double.valueOf(50000.0d), Double.valueOf(100000.0d)))));
        f10968c = create3;
        View.Name create4 = View.Name.create("opencensus.io/http/client/completed_count");
        Measure.MeasureDouble measureDouble = HttpMeasureConstants.HTTP_CLIENT_ROUNDTRIP_LATENCY;
        TagKey tagKey = HttpMeasureConstants.HTTP_CLIENT_METHOD;
        TagKey tagKey2 = HttpMeasureConstants.HTTP_CLIENT_STATUS;
        HTTP_CLIENT_COMPLETED_COUNT_VIEW = View.create(create4, "Count of client-side HTTP requests completed", measureDouble, create, Arrays.asList(tagKey, tagKey2));
        HTTP_CLIENT_SENT_BYTES_VIEW = View.create(View.Name.create("opencensus.io/http/client/sent_bytes"), "Size distribution of client-side HTTP request body", HttpMeasureConstants.HTTP_CLIENT_SENT_BYTES, create2, Arrays.asList(tagKey, tagKey2));
        HTTP_CLIENT_RECEIVED_BYTES_VIEW = View.create(View.Name.create("opencensus.io/http/client/received_bytes"), "Size distribution of client-side HTTP response body", HttpMeasureConstants.HTTP_CLIENT_RECEIVED_BYTES, create2, Arrays.asList(tagKey, tagKey2));
        HTTP_CLIENT_ROUNDTRIP_LATENCY_VIEW = View.create(View.Name.create("opencensus.io/http/client/roundtrip_latency"), "Roundtrip latency distribution of client-side HTTP requests", measureDouble, create3, Arrays.asList(tagKey, tagKey2));
        View.Name create5 = View.Name.create("opencensus.io/http/server/completed_count");
        Measure.MeasureDouble measureDouble2 = HttpMeasureConstants.HTTP_SERVER_LATENCY;
        TagKey tagKey3 = HttpMeasureConstants.HTTP_SERVER_METHOD;
        TagKey tagKey4 = HttpMeasureConstants.HTTP_SERVER_ROUTE;
        TagKey tagKey5 = HttpMeasureConstants.HTTP_SERVER_STATUS;
        HTTP_SERVER_COMPLETED_COUNT_VIEW = View.create(create5, "Count of HTTP server-side requests serving completed", measureDouble2, create, Arrays.asList(tagKey3, tagKey4, tagKey5));
        HTTP_SERVER_RECEIVED_BYTES_VIEW = View.create(View.Name.create("opencensus.io/http/server/received_bytes"), "Size distribution of server-side HTTP request body", HttpMeasureConstants.HTTP_SERVER_RECEIVED_BYTES, create2, Arrays.asList(tagKey3, tagKey4, tagKey5));
        HTTP_SERVER_SENT_BYTES_VIEW = View.create(View.Name.create("opencensus.io/http/server/sent_bytes"), "Size distribution of server-side HTTP response body", HttpMeasureConstants.HTTP_SERVER_SENT_BYTES, create2, Arrays.asList(tagKey3, tagKey4, tagKey5));
        HTTP_SERVER_LATENCY_VIEW = View.create(View.Name.create("opencensus.io/http/server/server_latency"), "Latency distribution of server-side HTTP requests serving", measureDouble2, create3, Arrays.asList(tagKey3, tagKey4, tagKey5));
    }

    private HttpViewConstants() {
    }
}
