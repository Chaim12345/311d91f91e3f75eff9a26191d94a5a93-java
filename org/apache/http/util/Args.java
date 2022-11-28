package org.apache.http.util;

import java.util.Collection;
/* loaded from: classes3.dex */
public class Args {
    public static void check(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void check(boolean z, String str, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, obj));
        }
    }

    public static void check(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }

    public static <T extends CharSequence> T containsNoBlanks(T t2, String str) {
        if (t2 == null) {
            throw new IllegalArgumentException(str + " may not be null");
        } else if (t2.length() == 0) {
            throw new IllegalArgumentException(str + " may not be empty");
        } else if (TextUtils.containsBlanks(t2)) {
            throw new IllegalArgumentException(str + " may not contain blanks");
        } else {
            return t2;
        }
    }

    public static <T extends CharSequence> T notBlank(T t2, String str) {
        if (t2 == null) {
            throw new IllegalArgumentException(str + " may not be null");
        } else if (TextUtils.isBlank(t2)) {
            throw new IllegalArgumentException(str + " may not be blank");
        } else {
            return t2;
        }
    }

    public static <T extends CharSequence> T notEmpty(T t2, String str) {
        if (t2 == null) {
            throw new IllegalArgumentException(str + " may not be null");
        } else if (TextUtils.isEmpty(t2)) {
            throw new IllegalArgumentException(str + " may not be empty");
        } else {
            return t2;
        }
    }

    public static <E, T extends Collection<E>> T notEmpty(T t2, String str) {
        if (t2 == null) {
            throw new IllegalArgumentException(str + " may not be null");
        } else if (t2.isEmpty()) {
            throw new IllegalArgumentException(str + " may not be empty");
        } else {
            return t2;
        }
    }

    public static int notNegative(int i2, String str) {
        if (i2 >= 0) {
            return i2;
        }
        throw new IllegalArgumentException(str + " may not be negative");
    }

    public static long notNegative(long j2, String str) {
        if (j2 >= 0) {
            return j2;
        }
        throw new IllegalArgumentException(str + " may not be negative");
    }

    public static <T> T notNull(T t2, String str) {
        if (t2 != null) {
            return t2;
        }
        throw new IllegalArgumentException(str + " may not be null");
    }

    public static int positive(int i2, String str) {
        if (i2 > 0) {
            return i2;
        }
        throw new IllegalArgumentException(str + " may not be negative or zero");
    }

    public static long positive(long j2, String str) {
        if (j2 > 0) {
            return j2;
        }
        throw new IllegalArgumentException(str + " may not be negative or zero");
    }
}
