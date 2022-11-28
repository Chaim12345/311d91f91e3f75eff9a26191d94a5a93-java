package com.google.api.client.googleapis.batch;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.MultipartContent;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sleeper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.HelpFormatter;
/* loaded from: classes2.dex */
public final class BatchRequest {
    private static final String GLOBAL_BATCH_ENDPOINT = "https://www.googleapis.com/batch";
    private static final String GLOBAL_BATCH_ENDPOINT_WARNING = "You are using the global batch endpoint which will soon be shut down. Please instantiate your BatchRequest via your service client's `batch(HttpRequestInitializer)` method. For an example, please see https://github.com/googleapis/google-api-java-client#batching.";
    private static final Logger LOGGER = Logger.getLogger(BatchRequest.class.getName());
    private final HttpRequestFactory requestFactory;
    private GenericUrl batchUrl = new GenericUrl(GLOBAL_BATCH_ENDPOINT);

    /* renamed from: a  reason: collision with root package name */
    List f8001a = new ArrayList();
    private Sleeper sleeper = Sleeper.DEFAULT;

    /* loaded from: classes2.dex */
    class BatchInterceptor implements HttpExecuteInterceptor {
        private HttpExecuteInterceptor originalInterceptor;

        BatchInterceptor(HttpExecuteInterceptor httpExecuteInterceptor) {
            this.originalInterceptor = httpExecuteInterceptor;
        }

        @Override // com.google.api.client.http.HttpExecuteInterceptor
        public void intercept(HttpRequest httpRequest) {
            HttpExecuteInterceptor httpExecuteInterceptor = this.originalInterceptor;
            if (httpExecuteInterceptor != null) {
                httpExecuteInterceptor.intercept(httpRequest);
            }
            for (RequestInfo requestInfo : BatchRequest.this.f8001a) {
                HttpExecuteInterceptor interceptor = requestInfo.f8006d.getInterceptor();
                if (interceptor != null) {
                    interceptor.intercept(requestInfo.f8006d);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class RequestInfo<T, E> {

        /* renamed from: a  reason: collision with root package name */
        final BatchCallback f8003a;

        /* renamed from: b  reason: collision with root package name */
        final Class f8004b;

        /* renamed from: c  reason: collision with root package name */
        final Class f8005c;

        /* renamed from: d  reason: collision with root package name */
        final HttpRequest f8006d;

        RequestInfo(BatchCallback batchCallback, Class cls, Class cls2, HttpRequest httpRequest) {
            this.f8003a = batchCallback;
            this.f8004b = cls;
            this.f8005c = cls2;
            this.f8006d = httpRequest;
        }
    }

    @Deprecated
    public BatchRequest(HttpTransport httpTransport, HttpRequestInitializer httpRequestInitializer) {
        this.requestFactory = httpRequestInitializer == null ? httpTransport.createRequestFactory() : httpTransport.createRequestFactory(httpRequestInitializer);
    }

    public void execute() {
        boolean z;
        Preconditions.checkState(!this.f8001a.isEmpty());
        if (GLOBAL_BATCH_ENDPOINT.equals(this.batchUrl.toString())) {
            LOGGER.log(Level.WARNING, GLOBAL_BATCH_ENDPOINT_WARNING);
        }
        HttpRequest buildPostRequest = this.requestFactory.buildPostRequest(this.batchUrl, null);
        buildPostRequest.setInterceptor(new BatchInterceptor(buildPostRequest.getInterceptor()));
        int numberOfRetries = buildPostRequest.getNumberOfRetries();
        do {
            z = numberOfRetries > 0;
            MultipartContent multipartContent = new MultipartContent();
            multipartContent.getMediaType().setSubType("mixed");
            int i2 = 1;
            for (RequestInfo requestInfo : this.f8001a) {
                multipartContent.addPart(new MultipartContent.Part(new HttpHeaders().setAcceptEncoding(null).set("Content-ID", (Object) Integer.valueOf(i2)), new HttpRequestContent(requestInfo.f8006d)));
                i2++;
            }
            buildPostRequest.setContent(multipartContent);
            HttpResponse execute = buildPostRequest.execute();
            try {
                BatchUnparsedResponse batchUnparsedResponse = new BatchUnparsedResponse(execute.getContent(), HelpFormatter.DEFAULT_LONG_OPT_PREFIX + execute.getMediaType().getParameter("boundary"), this.f8001a, z);
                while (batchUnparsedResponse.f8007a) {
                    batchUnparsedResponse.a();
                }
                execute.disconnect();
                List list = batchUnparsedResponse.f8008b;
                if (list.isEmpty()) {
                    break;
                }
                this.f8001a = list;
                numberOfRetries--;
            } catch (Throwable th) {
                execute.disconnect();
                throw th;
            }
        } while (z);
        this.f8001a.clear();
    }

    public GenericUrl getBatchUrl() {
        return this.batchUrl;
    }

    public Sleeper getSleeper() {
        return this.sleeper;
    }

    public <T, E> BatchRequest queue(HttpRequest httpRequest, Class<T> cls, Class<E> cls2, BatchCallback<T, E> batchCallback) {
        Preconditions.checkNotNull(httpRequest);
        Preconditions.checkNotNull(batchCallback);
        Preconditions.checkNotNull(cls);
        Preconditions.checkNotNull(cls2);
        this.f8001a.add(new RequestInfo(batchCallback, cls, cls2, httpRequest));
        return this;
    }

    public BatchRequest setBatchUrl(GenericUrl genericUrl) {
        this.batchUrl = genericUrl;
        return this;
    }

    public BatchRequest setSleeper(Sleeper sleeper) {
        this.sleeper = (Sleeper) Preconditions.checkNotNull(sleeper);
        return this;
    }

    public int size() {
        return this.f8001a.size();
    }
}
