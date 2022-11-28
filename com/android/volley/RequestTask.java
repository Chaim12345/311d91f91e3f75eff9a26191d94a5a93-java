package com.android.volley;
/* loaded from: classes.dex */
public abstract class RequestTask<T> implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final Request f4513a;

    public RequestTask(Request<T> request) {
        this.f4513a = request;
    }

    public int compareTo(RequestTask<?> requestTask) {
        return this.f4513a.compareTo((Request) requestTask.f4513a);
    }
}
