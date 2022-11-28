package org.apache.http.client.protocol;

import java.util.List;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.protocol.HttpContext;
@Contract(threading = ThreadingBehavior.IMMUTABLE)
/* loaded from: classes3.dex */
public class RequestAcceptEncoding implements HttpRequestInterceptor {
    private final String acceptEncoding;

    public RequestAcceptEncoding() {
        this(null);
    }

    public RequestAcceptEncoding(List<String> list) {
        String str;
        if (list == null || list.isEmpty()) {
            str = "gzip,deflate";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < list.size(); i2++) {
                if (i2 > 0) {
                    sb.append(",");
                }
                sb.append(list.get(i2));
            }
            str = sb.toString();
        }
        this.acceptEncoding = str;
    }

    @Override // org.apache.http.HttpRequestInterceptor
    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        RequestConfig requestConfig = HttpClientContext.adapt(httpContext).getRequestConfig();
        if (httpRequest.containsHeader("Accept-Encoding") || !requestConfig.isContentCompressionEnabled()) {
            return;
        }
        httpRequest.addHeader("Accept-Encoding", this.acceptEncoding);
    }
}
