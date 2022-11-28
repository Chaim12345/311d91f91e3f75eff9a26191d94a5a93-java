package com.google.android.play.core.internal;

import java.io.File;
import org.apache.commons.cli.HelpFormatter;
/* loaded from: classes2.dex */
public final class zzcj {
    public static String zza(File file) {
        if (file.getName().endsWith(".apk")) {
            String str = "";
            String replaceFirst = file.getName().replaceFirst("(_\\d+)?\\.apk", "");
            if (replaceFirst.equals("base-master") || replaceFirst.equals("base-main")) {
                return "";
            }
            String str2 = "base-";
            if (replaceFirst.startsWith("base-")) {
                str = "config.";
            } else {
                replaceFirst = replaceFirst.replace(HelpFormatter.DEFAULT_OPT_PREFIX, ".config.").replace(".config.master", "");
                str2 = ".config.main";
            }
            return replaceFirst.replace(str2, str);
        }
        throw new IllegalArgumentException("Non-apk found in splits directory.");
    }
}
