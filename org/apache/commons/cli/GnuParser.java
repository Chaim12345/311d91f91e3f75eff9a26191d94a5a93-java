package org.apache.commons.cli;

import java.util.ArrayList;
/* loaded from: classes3.dex */
public class GnuParser extends Parser {
    @Override // org.apache.commons.cli.Parser
    protected String[] flatten(Options options, String[] strArr, boolean z) {
        int i2;
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        boolean z2 = false;
        while (i3 < strArr.length) {
            String str = strArr[i3];
            if (HelpFormatter.DEFAULT_LONG_OPT_PREFIX.equals(str)) {
                arrayList.add(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
                z2 = true;
            } else if (HelpFormatter.DEFAULT_OPT_PREFIX.equals(str)) {
                arrayList.add(HelpFormatter.DEFAULT_OPT_PREFIX);
            } else {
                if (str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX)) {
                    String stripLeadingHyphens = Util.stripLeadingHyphens(str);
                    if (!options.hasOption(stripLeadingHyphens)) {
                        if (stripLeadingHyphens.indexOf(61) == -1 || !options.hasOption(stripLeadingHyphens.substring(0, stripLeadingHyphens.indexOf(61)))) {
                            i2 = 2;
                            if (options.hasOption(str.substring(0, 2))) {
                                arrayList.add(str.substring(0, 2));
                            } else {
                                arrayList.add(str);
                                z2 = z;
                            }
                        } else {
                            arrayList.add(str.substring(0, str.indexOf(61)));
                            i2 = str.indexOf(61) + 1;
                        }
                        str = str.substring(i2);
                    }
                }
                arrayList.add(str);
            }
            if (z2) {
                while (true) {
                    i3++;
                    if (i3 < strArr.length) {
                        arrayList.add(strArr[i3]);
                    }
                }
            }
            i3++;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
