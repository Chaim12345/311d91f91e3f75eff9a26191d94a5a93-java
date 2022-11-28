package com.karumi.dexter.listener;

import androidx.annotation.NonNull;
/* loaded from: classes2.dex */
public final class PermissionRequest {
    private final String name;

    public PermissionRequest(@NonNull String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return "Permission name: " + this.name;
    }
}
