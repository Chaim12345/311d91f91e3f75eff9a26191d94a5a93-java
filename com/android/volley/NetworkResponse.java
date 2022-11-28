package com.android.volley;

import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/* loaded from: classes.dex */
public class NetworkResponse {
    @Nullable
    public final List<Header> allHeaders;
    public final byte[] data;
    @Nullable
    public final Map<String, String> headers;
    public final long networkTimeMs;
    public final boolean notModified;
    public final int statusCode;

    private NetworkResponse(int i2, byte[] bArr, @Nullable Map<String, String> map, @Nullable List<Header> list, boolean z, long j2) {
        this.statusCode = i2;
        this.data = bArr;
        this.headers = map;
        this.allHeaders = list == null ? null : Collections.unmodifiableList(list);
        this.notModified = z;
        this.networkTimeMs = j2;
    }

    @Deprecated
    public NetworkResponse(int i2, byte[] bArr, @Nullable Map<String, String> map, boolean z) {
        this(i2, bArr, map, z, 0L);
    }

    @Deprecated
    public NetworkResponse(int i2, byte[] bArr, @Nullable Map<String, String> map, boolean z, long j2) {
        this(i2, bArr, map, toAllHeaderList(map), z, j2);
    }

    public NetworkResponse(int i2, byte[] bArr, boolean z, long j2, @Nullable List<Header> list) {
        this(i2, bArr, toHeaderMap(list), list, z, j2);
    }

    public NetworkResponse(byte[] bArr) {
        this(200, bArr, false, 0L, (List<Header>) Collections.emptyList());
    }

    @Deprecated
    public NetworkResponse(byte[] bArr, @Nullable Map<String, String> map) {
        this(200, bArr, map, false, 0L);
    }

    @Nullable
    private static List<Header> toAllHeaderList(@Nullable Map<String, String> map) {
        if (map == null) {
            return null;
        }
        if (map.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            arrayList.add(new Header(entry.getKey(), entry.getValue()));
        }
        return arrayList;
    }

    @Nullable
    private static Map<String, String> toHeaderMap(@Nullable List<Header> list) {
        if (list == null) {
            return null;
        }
        if (list.isEmpty()) {
            return Collections.emptyMap();
        }
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (Header header : list) {
            treeMap.put(header.getName(), header.getValue());
        }
        return treeMap;
    }
}
