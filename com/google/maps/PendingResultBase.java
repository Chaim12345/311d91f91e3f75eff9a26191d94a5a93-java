package com.google.maps;

import com.google.maps.PendingResult;
import com.google.maps.PendingResultBase;
import com.google.maps.internal.ApiConfig;
import com.google.maps.internal.ApiResponse;
import com.google.maps.internal.StringJoin;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class PendingResultBase<T, A extends PendingResultBase<T, A, R>, R extends ApiResponse<T>> implements PendingResult<T> {
    private final ApiConfig config;
    private final GeoApiContext context;
    private PendingResult<T> delegate;
    private HashMap<String, List<String>> params = new HashMap<>();
    private Class<? extends R> responseClass;

    /* JADX INFO: Access modifiers changed from: protected */
    public PendingResultBase(GeoApiContext geoApiContext, ApiConfig apiConfig, Class cls) {
        this.context = geoApiContext;
        this.config = apiConfig;
        this.responseClass = cls;
    }

    private A getInstance() {
        return this;
    }

    private PendingResult<T> makeRequest() {
        PendingResult a2;
        if (this.delegate == null) {
            g();
            String str = this.config.requestVerb;
            str.hashCode();
            if (str.equals("GET")) {
                a2 = this.context.a(this.config, this.responseClass, this.params);
            } else if (!str.equals("POST")) {
                throw new IllegalStateException(String.format("Unexpected request method '%s'", this.config.requestVerb));
            } else {
                a2 = this.context.c(this.config, this.responseClass, this.params);
            }
            this.delegate = a2;
            return a2;
        }
        throw new IllegalStateException("'await', 'awaitIgnoreError' or 'setCallback' was already called.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PendingResultBase a(String str, int i2) {
        return c(str, Integer.toString(i2));
    }

    @Override // com.google.maps.PendingResult
    public T await() {
        return makeRequest().await();
    }

    @Override // com.google.maps.PendingResult
    public final T awaitIgnoreError() {
        return makeRequest().awaitIgnoreError();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PendingResultBase b(String str, StringJoin.UrlValue urlValue) {
        return urlValue != null ? c(str, urlValue.toUrlValue()) : getInstance();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PendingResultBase c(String str, String str2) {
        this.params.put(str, new ArrayList());
        return e(str, str2);
    }

    @Override // com.google.maps.PendingResult
    public final void cancel() {
        PendingResult<T> pendingResult = this.delegate;
        if (pendingResult == null) {
            return;
        }
        pendingResult.cancel();
    }

    public A channel(String str) {
        return (A) c("channel", str);
    }

    public A custom(String str, String str2) {
        return (A) c(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PendingResultBase d(String str, StringJoin.UrlValue urlValue) {
        return urlValue != null ? e(str, urlValue.toUrlValue()) : getInstance();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PendingResultBase e(String str, String str2) {
        if (this.params.get(str) == null) {
            this.params.put(str, new ArrayList());
        }
        this.params.get(str).add(str2);
        return getInstance();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Map f() {
        return Collections.unmodifiableMap(this.params);
    }

    protected abstract void g();

    public A language(String str) {
        return (A) c("language", str);
    }

    @Override // com.google.maps.PendingResult
    public final void setCallback(PendingResult.Callback<T> callback) {
        makeRequest().setCallback(callback);
    }
}
