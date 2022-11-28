package org.apache.commons.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes3.dex */
public class PosixParser extends Parser {
    private Option currentOption;
    private boolean eatTheRest;
    private Options options;
    private List tokens = new ArrayList();

    private void gobble(Iterator it) {
        if (this.eatTheRest) {
            while (it.hasNext()) {
                this.tokens.add(it.next());
            }
        }
    }

    private void init() {
        this.eatTheRest = false;
        this.tokens.clear();
    }

    private void processNonOptionToken(String str, boolean z) {
        Option option;
        if (z && ((option = this.currentOption) == null || !option.hasArg())) {
            this.eatTheRest = true;
            this.tokens.add(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
        }
        this.tokens.add(str);
    }

    private void processOptionToken(String str, boolean z) {
        if (z && !this.options.hasOption(str)) {
            this.eatTheRest = true;
        }
        if (this.options.hasOption(str)) {
            this.currentOption = this.options.getOption(str);
        }
        this.tokens.add(str);
    }

    protected void burstToken(String str, boolean z) {
        List list;
        int i2;
        for (int i3 = 1; i3 < str.length(); i3++) {
            String valueOf = String.valueOf(str.charAt(i3));
            if (this.options.hasOption(valueOf)) {
                List list2 = this.tokens;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(HelpFormatter.DEFAULT_OPT_PREFIX);
                stringBuffer.append(valueOf);
                list2.add(stringBuffer.toString());
                Option option = this.options.getOption(valueOf);
                this.currentOption = option;
                if (option.hasArg() && str.length() != (i2 = i3 + 1)) {
                    list = this.tokens;
                    str = str.substring(i2);
                }
            } else if (z) {
                processNonOptionToken(str.substring(i3), true);
                return;
            } else {
                list = this.tokens;
            }
            list.add(str);
            return;
        }
    }

    @Override // org.apache.commons.cli.Parser
    protected String[] flatten(Options options, String[] strArr, boolean z) {
        init();
        this.options = options;
        Iterator it = Arrays.asList(strArr).iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str.startsWith(HelpFormatter.DEFAULT_LONG_OPT_PREFIX)) {
                int indexOf = str.indexOf(61);
                String substring = indexOf == -1 ? str : str.substring(0, indexOf);
                if (options.hasOption(substring)) {
                    this.currentOption = options.getOption(substring);
                    this.tokens.add(substring);
                    if (indexOf != -1) {
                        this.tokens.add(str.substring(indexOf + 1));
                    }
                }
                processNonOptionToken(str, z);
            } else if (HelpFormatter.DEFAULT_OPT_PREFIX.equals(str)) {
                this.tokens.add(str);
            } else {
                if (str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX)) {
                    if (str.length() == 2 || options.hasOption(str)) {
                        processOptionToken(str, z);
                    } else {
                        burstToken(str, z);
                    }
                }
                processNonOptionToken(str, z);
            }
            gobble(it);
        }
        List list = this.tokens;
        return (String[]) list.toArray(new String[list.size()]);
    }
}
