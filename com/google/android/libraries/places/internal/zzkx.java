package com.google.android.libraries.places.internal;
/* loaded from: classes2.dex */
final class zzkx implements zzkw {
    @Override // com.google.android.libraries.places.internal.zzkw
    public final StackTraceElement zza(Class cls, int i2) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        String name = cls.getName();
        int i3 = 3;
        boolean z = false;
        while (true) {
            if (i3 >= stackTrace.length) {
                i3 = -1;
                break;
            }
            if (stackTrace[i3].getClassName().equals(name)) {
                z = true;
            } else if (z) {
                break;
            } else {
                z = false;
            }
            i3++;
        }
        if (i3 != -1) {
            return stackTrace[i3];
        }
        return null;
    }
}
