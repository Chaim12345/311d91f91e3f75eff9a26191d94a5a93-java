package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
@GwtCompatible
/* loaded from: classes2.dex */
public enum BoundType {
    OPEN(false),
    CLOSED(true);

    BoundType(boolean z) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BoundType a(boolean z) {
        return z ? CLOSED : OPEN;
    }
}
