package org.bouncycastle.jsse.provider;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Security;
import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes3.dex */
class PropertyUtils {
    private static final Logger LOG = Logger.getLogger(PropertyUtils.class.getName());

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(String str, boolean z) {
        Logger logger;
        Level level;
        StringBuilder sb;
        boolean z2;
        String d2 = d(str);
        if (d2 != null) {
            if ("true".equalsIgnoreCase(d2)) {
                logger = LOG;
                level = Level.INFO;
                sb = new StringBuilder();
                sb.append("Found boolean security property [");
                sb.append(str);
                sb.append("]: ");
                z2 = true;
            } else if ("false".equalsIgnoreCase(d2)) {
                logger = LOG;
                level = Level.INFO;
                sb = new StringBuilder();
                sb.append("Found boolean security property [");
                sb.append(str);
                sb.append("]: ");
                z2 = false;
            } else {
                Logger logger2 = LOG;
                Level level2 = Level.WARNING;
                logger2.log(level2, "Unrecognized value for boolean security property [" + str + "]: " + d2);
            }
            sb.append(z2);
            logger.log(level, sb.toString());
            return z2;
        }
        Logger logger3 = LOG;
        Level level3 = Level.FINE;
        logger3.log(level3, "Boolean security property [" + str + "] defaulted to: " + z);
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean b(String str, boolean z) {
        Logger logger;
        Level level;
        StringBuilder sb;
        boolean z2;
        String k2 = k(str);
        if (k2 != null) {
            if ("true".equalsIgnoreCase(k2)) {
                logger = LOG;
                level = Level.INFO;
                sb = new StringBuilder();
                sb.append("Found boolean system property [");
                sb.append(str);
                sb.append("]: ");
                z2 = true;
            } else if ("false".equalsIgnoreCase(k2)) {
                logger = LOG;
                level = Level.INFO;
                sb = new StringBuilder();
                sb.append("Found boolean system property [");
                sb.append(str);
                sb.append("]: ");
                z2 = false;
            } else {
                Logger logger2 = LOG;
                Level level2 = Level.WARNING;
                logger2.log(level2, "Unrecognized value for boolean system property [" + str + "]: " + k2);
            }
            sb.append(z2);
            logger.log(level, sb.toString());
            return z2;
        }
        Logger logger3 = LOG;
        Level level3 = Level.FINE;
        logger3.log(level3, "Boolean system property [" + str + "] defaulted to: " + z);
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int c(String str, int i2, int i3, int i4) {
        String k2 = k(str);
        if (k2 != null) {
            try {
                int parseInt = Integer.parseInt(k2);
                if (parseInt >= i3 && parseInt <= i4) {
                    Logger logger = LOG;
                    Level level = Level.INFO;
                    logger.log(level, "Found integer system property [" + str + "]: " + parseInt);
                    return parseInt;
                }
                Logger logger2 = LOG;
                Level level2 = Level.WARNING;
                if (logger2.isLoggable(level2)) {
                    String rangeString = getRangeString(i3, i4);
                    logger2.log(level2, "Out-of-range (" + rangeString + ") integer system property [" + str + "]: " + k2);
                }
            } catch (Exception unused) {
                Logger logger3 = LOG;
                Level level3 = Level.WARNING;
                logger3.log(level3, "Unrecognized value for integer system property [" + str + "]: " + k2);
            }
        }
        Logger logger4 = LOG;
        Level level4 = Level.FINE;
        logger4.log(level4, "Integer system property [" + str + "] defaulted to: " + i2);
        return i2;
    }

    static String d(final String str) {
        return (String) AccessController.doPrivileged(new PrivilegedAction<String>() { // from class: org.bouncycastle.jsse.provider.PropertyUtils.1
            @Override // java.security.PrivilegedAction
            public String run() {
                return Security.getProperty(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String e(String str) {
        String k2 = k(str);
        if (k2 != null) {
            Logger logger = LOG;
            logger.info("Found sensitive string system property [" + str + "]");
            return k2;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[] f(String str, String str2) {
        return parseStringArray(i(str, str2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[] g(String str) {
        return parseStringArray(j(str));
    }

    private static String getRangeString(int i2, int i3) {
        StringBuilder sb = new StringBuilder(32);
        if (Integer.MIN_VALUE != i2) {
            sb.append(i2);
            sb.append(" <= ");
        }
        sb.append('x');
        if (Integer.MAX_VALUE != i3) {
            sb.append(" <= ");
            sb.append(i3);
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String h(String str) {
        String d2 = d(str);
        if (d2 != null) {
            Logger logger = LOG;
            Level level = Level.INFO;
            logger.log(level, "Found string security property [" + str + "]: " + d2);
            return d2;
        }
        return null;
    }

    static String i(String str, String str2) {
        String d2 = d(str);
        if (d2 != null) {
            Logger logger = LOG;
            Level level = Level.INFO;
            logger.log(level, "Found string security property [" + str + "]: " + d2);
            return d2;
        }
        Logger logger2 = LOG;
        Level level2 = Level.WARNING;
        logger2.log(level2, "String security property [" + str + "] defaulted to: " + str2);
        return str2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String j(String str) {
        String k2 = k(str);
        if (k2 != null) {
            Logger logger = LOG;
            Level level = Level.INFO;
            logger.log(level, "Found string system property [" + str + "]: " + k2);
            return k2;
        }
        return null;
    }

    static String k(final String str) {
        try {
            return (String) AccessController.doPrivileged(new PrivilegedAction<String>() { // from class: org.bouncycastle.jsse.provider.PropertyUtils.2
                @Override // java.security.PrivilegedAction
                public String run() {
                    return System.getProperty(str);
                }
            });
        } catch (RuntimeException e2) {
            LOG.log(Level.WARNING, "Failed to get system property", (Throwable) e2);
            return null;
        }
    }

    private static String[] parseStringArray(String str) {
        if (str == null) {
            return null;
        }
        String[] split = JsseUtils.W(str.trim()).split(",");
        String[] strArr = new String[split.length];
        int i2 = 0;
        for (String str2 : split) {
            String trim = str2.trim();
            if (trim.length() >= 1) {
                strArr[i2] = trim;
                i2++;
            }
        }
        return JsseUtils.V(strArr, i2);
    }
}
