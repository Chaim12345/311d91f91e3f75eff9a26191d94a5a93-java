package com.google.android.libraries.places.internal;

import java.util.LinkedHashMap;
import java.util.Map;
/* loaded from: classes2.dex */
final class zzeu extends LinkedHashMap {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeu(int i2, float f2, boolean z) {
        super(16, 0.75f, true);
    }

    @Override // java.util.LinkedHashMap
    protected final boolean removeEldestEntry(Map.Entry entry) {
        return size() > 10;
    }
}
