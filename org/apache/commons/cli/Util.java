package org.apache.commons.cli;
/* loaded from: classes3.dex */
class Util {
    Util() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String stripLeadingAndTrailingQuotes(String str) {
        if (str.startsWith("\"")) {
            str = str.substring(1, str.length());
        }
        return str.endsWith("\"") ? str.substring(0, str.length() - 1) : str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String stripLeadingHyphens(String str) {
        int i2;
        if (str == null) {
            return null;
        }
        if (str.startsWith(HelpFormatter.DEFAULT_LONG_OPT_PREFIX)) {
            i2 = 2;
        } else if (!str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX)) {
            return str;
        } else {
            i2 = 1;
        }
        return str.substring(i2, str.length());
    }
}
