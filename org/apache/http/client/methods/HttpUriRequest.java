package org.apache.http.client.methods;

import java.net.URI;
import org.apache.http.HttpRequest;
/* loaded from: classes3.dex */
public interface HttpUriRequest extends HttpRequest {
    void abort();

    String getMethod();

    URI getURI();

    boolean isAborted();
}
