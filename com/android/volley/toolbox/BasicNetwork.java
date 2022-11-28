package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.Header;
import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public class BasicNetwork implements Network {
    private static final int DEFAULT_POOL_SIZE = 4096;
    @Deprecated

    /* renamed from: a  reason: collision with root package name */
    protected final HttpStack f4535a;

    /* renamed from: b  reason: collision with root package name */
    protected final ByteArrayPool f4536b;
    private final BaseHttpStack mBaseHttpStack;

    public BasicNetwork(BaseHttpStack baseHttpStack) {
        this(baseHttpStack, new ByteArrayPool(4096));
    }

    public BasicNetwork(BaseHttpStack baseHttpStack, ByteArrayPool byteArrayPool) {
        this.mBaseHttpStack = baseHttpStack;
        this.f4536b = byteArrayPool;
    }

    @Deprecated
    public BasicNetwork(HttpStack httpStack) {
        this(httpStack, new ByteArrayPool(4096));
    }

    @Deprecated
    public BasicNetwork(HttpStack httpStack, ByteArrayPool byteArrayPool) {
        this.f4535a = httpStack;
        this.mBaseHttpStack = new AdaptedHttpStack(httpStack);
        this.f4536b = byteArrayPool;
    }

    @Override // com.android.volley.Network
    public NetworkResponse performRequest(Request<?> request) {
        IOException iOException;
        HttpResponse httpResponse;
        byte[] bArr;
        HttpResponse executeRequest;
        int statusCode;
        List<Header> headers;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        while (true) {
            Collections.emptyList();
            try {
                executeRequest = this.mBaseHttpStack.executeRequest(request, HttpHeaderParser.c(request.getCacheEntry()));
                try {
                    statusCode = executeRequest.getStatusCode();
                    headers = executeRequest.getHeaders();
                    break;
                } catch (IOException e2) {
                    bArr = null;
                    httpResponse = executeRequest;
                    iOException = e2;
                }
            } catch (IOException e3) {
                iOException = e3;
                httpResponse = null;
                bArr = null;
            }
            NetworkUtility.a(request, NetworkUtility.e(request, iOException, elapsedRealtime, httpResponse, bArr));
        }
        if (statusCode == 304) {
            return NetworkUtility.b(request, SystemClock.elapsedRealtime() - elapsedRealtime, headers);
        }
        InputStream content = executeRequest.getContent();
        byte[] c2 = content != null ? NetworkUtility.c(content, executeRequest.getContentLength(), this.f4536b) : new byte[0];
        NetworkUtility.d(SystemClock.elapsedRealtime() - elapsedRealtime, request, c2, statusCode);
        if (statusCode < 200 || statusCode > 299) {
            throw new IOException();
        }
        return new NetworkResponse(statusCode, c2, false, SystemClock.elapsedRealtime() - elapsedRealtime, headers);
    }
}
