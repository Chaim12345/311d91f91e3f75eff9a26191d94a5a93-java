package com.android.volley.toolbox;

import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.android.volley.AsyncNetwork;
import com.android.volley.AuthFailureError;
import com.android.volley.Header;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestTask;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.AsyncHttpStack;
import com.android.volley.toolbox.NetworkUtility;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
/* loaded from: classes.dex */
public class BasicAsyncNetwork extends AsyncNetwork {
    private final AsyncHttpStack mAsyncStack;
    private final ByteArrayPool mPool;

    /* loaded from: classes.dex */
    public static class Builder {
        private static final int DEFAULT_POOL_SIZE = 4096;
        @NonNull
        private AsyncHttpStack mAsyncStack;
        private ByteArrayPool mPool = null;

        public Builder(@NonNull AsyncHttpStack asyncHttpStack) {
            this.mAsyncStack = asyncHttpStack;
        }

        public BasicAsyncNetwork build() {
            if (this.mPool == null) {
                this.mPool = new ByteArrayPool(4096);
            }
            return new BasicAsyncNetwork(this.mAsyncStack, this.mPool);
        }

        public Builder setPool(ByteArrayPool byteArrayPool) {
            this.mPool = byteArrayPool;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class InvokeRetryPolicyTask<T> extends RequestTask<T> {

        /* renamed from: b  reason: collision with root package name */
        final Request f4523b;

        /* renamed from: c  reason: collision with root package name */
        final NetworkUtility.RetryInfo f4524c;

        /* renamed from: d  reason: collision with root package name */
        final AsyncNetwork.OnRequestComplete f4525d;

        InvokeRetryPolicyTask(Request request, NetworkUtility.RetryInfo retryInfo, AsyncNetwork.OnRequestComplete onRequestComplete) {
            super(request);
            this.f4523b = request;
            this.f4524c = retryInfo;
            this.f4525d = onRequestComplete;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                NetworkUtility.a(this.f4523b, this.f4524c);
                BasicAsyncNetwork.this.performRequest(this.f4523b, this.f4525d);
            } catch (VolleyError e2) {
                this.f4525d.onError(e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ResponseParsingTask<T> extends RequestTask<T> {

        /* renamed from: b  reason: collision with root package name */
        InputStream f4527b;

        /* renamed from: c  reason: collision with root package name */
        HttpResponse f4528c;

        /* renamed from: d  reason: collision with root package name */
        Request f4529d;

        /* renamed from: e  reason: collision with root package name */
        AsyncNetwork.OnRequestComplete f4530e;

        /* renamed from: f  reason: collision with root package name */
        long f4531f;

        /* renamed from: g  reason: collision with root package name */
        List f4532g;

        /* renamed from: h  reason: collision with root package name */
        int f4533h;

        ResponseParsingTask(InputStream inputStream, HttpResponse httpResponse, Request request, AsyncNetwork.OnRequestComplete onRequestComplete, long j2, List list, int i2) {
            super(request);
            this.f4527b = inputStream;
            this.f4528c = httpResponse;
            this.f4529d = request;
            this.f4530e = onRequestComplete;
            this.f4531f = j2;
            this.f4532g = list;
            this.f4533h = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                BasicAsyncNetwork.this.onResponseRead(this.f4531f, this.f4533h, this.f4528c, this.f4529d, this.f4530e, this.f4532g, NetworkUtility.c(this.f4527b, this.f4528c.getContentLength(), BasicAsyncNetwork.this.mPool));
            } catch (IOException e2) {
                BasicAsyncNetwork.this.onRequestFailed(this.f4529d, this.f4530e, e2, this.f4531f, this.f4528c, null);
            }
        }
    }

    private BasicAsyncNetwork(AsyncHttpStack asyncHttpStack, ByteArrayPool byteArrayPool) {
        this.mAsyncStack = asyncHttpStack;
        this.mPool = byteArrayPool;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRequestFailed(Request<?> request, AsyncNetwork.OnRequestComplete onRequestComplete, IOException iOException, long j2, @Nullable HttpResponse httpResponse, @Nullable byte[] bArr) {
        try {
            a().execute(new InvokeRetryPolicyTask(request, NetworkUtility.e(request, iOException, j2, httpResponse, bArr), onRequestComplete));
        } catch (VolleyError e2) {
            onRequestComplete.onError(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRequestSucceeded(Request<?> request, long j2, HttpResponse httpResponse, AsyncNetwork.OnRequestComplete onRequestComplete) {
        int statusCode = httpResponse.getStatusCode();
        List<Header> headers = httpResponse.getHeaders();
        if (statusCode == 304) {
            onRequestComplete.onSuccess(NetworkUtility.b(request, SystemClock.elapsedRealtime() - j2, headers));
            return;
        }
        byte[] contentBytes = httpResponse.getContentBytes();
        if (contentBytes == null && httpResponse.getContent() == null) {
            contentBytes = new byte[0];
        }
        byte[] bArr = contentBytes;
        if (bArr != null) {
            onResponseRead(j2, statusCode, httpResponse, request, onRequestComplete, headers, bArr);
            return;
        }
        a().execute(new ResponseParsingTask(httpResponse.getContent(), httpResponse, request, onRequestComplete, j2, headers, statusCode));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onResponseRead(long j2, int i2, HttpResponse httpResponse, Request<?> request, AsyncNetwork.OnRequestComplete onRequestComplete, List<Header> list, byte[] bArr) {
        NetworkUtility.d(SystemClock.elapsedRealtime() - j2, request, bArr, i2);
        if (i2 < 200 || i2 > 299) {
            onRequestFailed(request, onRequestComplete, new IOException(), j2, httpResponse, bArr);
        } else {
            onRequestComplete.onSuccess(new NetworkResponse(i2, bArr, false, SystemClock.elapsedRealtime() - j2, list));
        }
    }

    @Override // com.android.volley.AsyncNetwork
    public void performRequest(final Request<?> request, final AsyncNetwork.OnRequestComplete onRequestComplete) {
        if (a() == null) {
            throw new IllegalStateException("mBlockingExecuter must be set before making a request");
        }
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        this.mAsyncStack.executeRequest(request, HttpHeaderParser.c(request.getCacheEntry()), new AsyncHttpStack.OnRequestComplete() { // from class: com.android.volley.toolbox.BasicAsyncNetwork.1
            @Override // com.android.volley.toolbox.AsyncHttpStack.OnRequestComplete
            public void onAuthError(AuthFailureError authFailureError) {
                onRequestComplete.onError(authFailureError);
            }

            @Override // com.android.volley.toolbox.AsyncHttpStack.OnRequestComplete
            public void onError(IOException iOException) {
                BasicAsyncNetwork.this.onRequestFailed(request, onRequestComplete, iOException, elapsedRealtime, null, null);
            }

            @Override // com.android.volley.toolbox.AsyncHttpStack.OnRequestComplete
            public void onSuccess(HttpResponse httpResponse) {
                BasicAsyncNetwork.this.onRequestSucceeded(request, elapsedRealtime, httpResponse, onRequestComplete);
            }
        });
    }

    @Override // com.android.volley.AsyncNetwork
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setBlockingExecutor(ExecutorService executorService) {
        super.setBlockingExecutor(executorService);
        this.mAsyncStack.setBlockingExecutor(executorService);
    }

    @Override // com.android.volley.AsyncNetwork
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setNonBlockingExecutor(ExecutorService executorService) {
        super.setNonBlockingExecutor(executorService);
        this.mAsyncStack.setNonBlockingExecutor(executorService);
    }
}
