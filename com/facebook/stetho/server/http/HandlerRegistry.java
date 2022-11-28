package com.facebook.stetho.server.http;

import androidx.annotation.Nullable;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class HandlerRegistry {
    private final ArrayList<PathMatcher> mPathMatchers = new ArrayList<>();
    private final ArrayList<HttpHandler> mHttpHandlers = new ArrayList<>();

    @Nullable
    public synchronized HttpHandler lookup(String str) {
        int size = this.mPathMatchers.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (this.mPathMatchers.get(i2).match(str)) {
                return this.mHttpHandlers.get(i2);
            }
        }
        return null;
    }

    public synchronized void register(PathMatcher pathMatcher, HttpHandler httpHandler) {
        this.mPathMatchers.add(pathMatcher);
        this.mHttpHandlers.add(httpHandler);
    }

    public synchronized boolean unregister(PathMatcher pathMatcher, HttpHandler httpHandler) {
        boolean z;
        int indexOf = this.mPathMatchers.indexOf(pathMatcher);
        if (indexOf < 0 || httpHandler != this.mHttpHandlers.get(indexOf)) {
            z = false;
        } else {
            this.mPathMatchers.remove(indexOf);
            this.mHttpHandlers.remove(indexOf);
            z = true;
        }
        return z;
    }
}
