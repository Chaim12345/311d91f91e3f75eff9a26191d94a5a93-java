package com.android.volley;

import androidx.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public interface Cache {

    /* loaded from: classes.dex */
    public static class Entry {
        public List<Header> allResponseHeaders;
        public byte[] data;
        public String etag;
        public long lastModified;
        public Map<String, String> responseHeaders = Collections.emptyMap();
        public long serverDate;
        public long softTtl;
        public long ttl;

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean a(long j2) {
            return this.ttl < j2;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean b(long j2) {
            return this.softTtl < j2;
        }

        public boolean isExpired() {
            return a(System.currentTimeMillis());
        }

        public boolean refreshNeeded() {
            return b(System.currentTimeMillis());
        }
    }

    void clear();

    @Nullable
    Entry get(String str);

    void initialize();

    void invalidate(String str, boolean z);

    void put(String str, Entry entry);

    void remove(String str);
}
