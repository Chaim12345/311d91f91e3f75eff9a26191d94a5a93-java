package com.scottyab.rootbeer;

import com.scottyab.rootbeer.util.QLog;
/* loaded from: classes3.dex */
public class RootBeerNative {
    private static boolean libraryLoaded = false;

    static {
        try {
            System.loadLibrary("toolChecker");
            libraryLoaded = true;
        } catch (UnsatisfiedLinkError e2) {
            QLog.e(e2);
        }
    }

    public native int checkForRoot(Object[] objArr);

    public native int setLogDebugMessages(boolean z);

    public boolean wasNativeLibraryLoaded() {
        return libraryLoaded;
    }
}
