package com.scottyab.rootbeer.util;
/* loaded from: classes3.dex */
public final class Utils {
    private Utils() {
        throw new InstantiationException("This class is not for instantiation");
    }

    public static boolean isSelinuxFlagInEnabled() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return "1".equals((String) cls.getMethod("get", String.class).invoke(cls, "ro.build.selinux"));
        } catch (Exception unused) {
            return false;
        }
    }
}
