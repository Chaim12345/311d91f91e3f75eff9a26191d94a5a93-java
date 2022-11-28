package org.bouncycastle.tls.crypto.impl.jcajce;
/* loaded from: classes4.dex */
class Exceptions {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static IllegalArgumentException a(String str, Throwable th) {
        return new IllegalArgumentException(str, th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static IllegalStateException b(String str, Throwable th) {
        return new IllegalStateException(str, th);
    }
}
