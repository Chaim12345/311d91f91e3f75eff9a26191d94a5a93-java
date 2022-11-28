package org.apache.commons.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;
/* loaded from: classes3.dex */
public abstract class Parser implements CommandLineParser {
    protected CommandLine cmd;
    private Options options;
    private List requiredOptions;

    protected void checkRequiredOptions() {
        if (!getRequiredOptions().isEmpty()) {
            throw new MissingOptionException(getRequiredOptions());
        }
    }

    protected abstract String[] flatten(Options options, String[] strArr, boolean z);

    protected Options getOptions() {
        return this.options;
    }

    protected List getRequiredOptions() {
        return this.requiredOptions;
    }

    @Override // org.apache.commons.cli.CommandLineParser
    public CommandLine parse(Options options, String[] strArr) {
        return parse(options, strArr, null, false);
    }

    public CommandLine parse(Options options, String[] strArr, Properties properties) {
        return parse(options, strArr, properties, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0080, code lost:
        if (r9 != false) goto L14;
     */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0085 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0037 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public CommandLine parse(Options options, String[] strArr, Properties properties, boolean z) {
        for (Option option : options.helpOptions()) {
            option.clearValues();
        }
        setOptions(options);
        this.cmd = new CommandLine();
        boolean z2 = false;
        if (strArr == null) {
            strArr = new String[0];
        }
        ListIterator listIterator = Arrays.asList(flatten(getOptions(), strArr, z)).listIterator();
        while (listIterator.hasNext()) {
            String str = (String) listIterator.next();
            if (!HelpFormatter.DEFAULT_LONG_OPT_PREFIX.equals(str)) {
                if (HelpFormatter.DEFAULT_OPT_PREFIX.equals(str)) {
                    if (!z) {
                        this.cmd.addArg(str);
                        if (z2) {
                            while (listIterator.hasNext()) {
                                String str2 = (String) listIterator.next();
                                if (!HelpFormatter.DEFAULT_LONG_OPT_PREFIX.equals(str2)) {
                                    this.cmd.addArg(str2);
                                }
                            }
                        }
                    }
                } else if (!str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX)) {
                    this.cmd.addArg(str);
                } else if (!z || getOptions().hasOption(str)) {
                    processOption(str, listIterator);
                    if (z2) {
                    }
                } else {
                    this.cmd.addArg(str);
                }
            }
            z2 = true;
            if (z2) {
            }
        }
        processProperties(properties);
        checkRequiredOptions();
        return this.cmd;
    }

    @Override // org.apache.commons.cli.CommandLineParser
    public CommandLine parse(Options options, String[] strArr, boolean z) {
        return parse(options, strArr, null, z);
    }

    public void processArgs(Option option, ListIterator listIterator) {
        while (listIterator.hasNext()) {
            String str = (String) listIterator.next();
            if (!getOptions().hasOption(str) || !str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX)) {
                try {
                    option.addValueForProcessing(Util.stripLeadingAndTrailingQuotes(str));
                } catch (RuntimeException unused) {
                }
            }
            listIterator.previous();
        }
        if (option.getValues() == null && !option.hasOptionalArg()) {
            throw new MissingArgumentException(option);
        }
    }

    protected void processOption(String str, ListIterator listIterator) {
        if (!getOptions().hasOption(str)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Unrecognized option: ");
            stringBuffer.append(str);
            throw new UnrecognizedOptionException(stringBuffer.toString(), str);
        }
        Option option = (Option) getOptions().getOption(str).clone();
        if (option.isRequired()) {
            getRequiredOptions().remove(option.getKey());
        }
        if (getOptions().getOptionGroup(option) != null) {
            OptionGroup optionGroup = getOptions().getOptionGroup(option);
            if (optionGroup.isRequired()) {
                getRequiredOptions().remove(optionGroup);
            }
            optionGroup.setSelected(option);
        }
        if (option.hasArg()) {
            processArgs(option, listIterator);
        }
        this.cmd.addOption(option);
    }

    protected void processProperties(Properties properties) {
        if (properties == null) {
            return;
        }
        Enumeration<?> propertyNames = properties.propertyNames();
        while (propertyNames.hasMoreElements()) {
            String obj = propertyNames.nextElement().toString();
            if (!this.cmd.hasOption(obj)) {
                Option option = getOptions().getOption(obj);
                String property = properties.getProperty(obj);
                if (option.hasArg()) {
                    if (option.getValues() == null || option.getValues().length == 0) {
                        try {
                            option.addValueForProcessing(property);
                        } catch (RuntimeException unused) {
                        }
                    }
                } else if (!"yes".equalsIgnoreCase(property) && !"true".equalsIgnoreCase(property) && !"1".equalsIgnoreCase(property)) {
                    return;
                }
                this.cmd.addOption(option);
            }
        }
    }

    protected void setOptions(Options options) {
        this.options = options;
        this.requiredOptions = new ArrayList(options.getRequiredOptions());
    }
}
