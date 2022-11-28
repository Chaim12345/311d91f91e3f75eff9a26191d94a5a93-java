package com.google.api.client.util.escape;
/* loaded from: classes2.dex */
final class Platform {
    private static final ThreadLocal<char[]> DEST_TL = new ThreadLocal<char[]>() { // from class: com.google.api.client.util.escape.Platform.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public char[] initialValue() {
            return new char[1024];
        }
    };

    private Platform() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static char[] a() {
        return DEST_TL.get();
    }
}
