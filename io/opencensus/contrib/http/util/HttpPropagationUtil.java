package io.opencensus.contrib.http.util;

import io.opencensus.trace.propagation.TextFormat;
/* loaded from: classes3.dex */
public class HttpPropagationUtil {
    private HttpPropagationUtil() {
    }

    public static TextFormat getCloudTraceFormat() {
        return new CloudTraceFormat();
    }
}
