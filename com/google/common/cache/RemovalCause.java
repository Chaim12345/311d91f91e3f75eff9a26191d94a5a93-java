package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
@GwtCompatible
/* loaded from: classes2.dex */
public enum RemovalCause {
    EXPLICIT { // from class: com.google.common.cache.RemovalCause.1
        @Override // com.google.common.cache.RemovalCause
        boolean a() {
            return false;
        }
    },
    REPLACED { // from class: com.google.common.cache.RemovalCause.2
        @Override // com.google.common.cache.RemovalCause
        boolean a() {
            return false;
        }
    },
    COLLECTED { // from class: com.google.common.cache.RemovalCause.3
        @Override // com.google.common.cache.RemovalCause
        boolean a() {
            return true;
        }
    },
    EXPIRED { // from class: com.google.common.cache.RemovalCause.4
        @Override // com.google.common.cache.RemovalCause
        boolean a() {
            return true;
        }
    },
    SIZE { // from class: com.google.common.cache.RemovalCause.5
        @Override // com.google.common.cache.RemovalCause
        boolean a() {
            return true;
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean a();
}
