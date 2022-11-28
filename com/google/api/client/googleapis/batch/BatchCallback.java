package com.google.api.client.googleapis.batch;

import com.google.api.client.http.HttpHeaders;
/* loaded from: classes2.dex */
public interface BatchCallback<T, E> {
    void onFailure(E e2, HttpHeaders httpHeaders);

    void onSuccess(T t2, HttpHeaders httpHeaders);
}
