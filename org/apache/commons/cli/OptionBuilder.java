package org.apache.commons.cli;
/* loaded from: classes3.dex */
public final class OptionBuilder {
    private static String argName = null;
    private static String description = null;
    private static OptionBuilder instance = new OptionBuilder();
    private static String longopt = null;
    private static int numberOfArgs = -1;
    private static boolean optionalArg;
    private static boolean required;
    private static Object type;
    private static char valuesep;

    private OptionBuilder() {
    }

    public static Option create() {
        if (longopt != null) {
            return create((String) null);
        }
        reset();
        throw new IllegalArgumentException("must specify longopt");
    }

    public static Option create(char c2) {
        return create(String.valueOf(c2));
    }

    public static Option create(String str) {
        try {
            Option option = new Option(str, description);
            option.setLongOpt(longopt);
            option.setRequired(required);
            option.setOptionalArg(optionalArg);
            option.setArgs(numberOfArgs);
            option.setType(type);
            option.setValueSeparator(valuesep);
            option.setArgName(argName);
            return option;
        } finally {
            reset();
        }
    }

    public static OptionBuilder hasArg() {
        numberOfArgs = 1;
        return instance;
    }

    public static OptionBuilder hasArg(boolean z) {
        numberOfArgs = z ? 1 : -1;
        return instance;
    }

    public static OptionBuilder hasArgs() {
        numberOfArgs = -2;
        return instance;
    }

    public static OptionBuilder hasArgs(int i2) {
        numberOfArgs = i2;
        return instance;
    }

    public static OptionBuilder hasOptionalArg() {
        numberOfArgs = 1;
        optionalArg = true;
        return instance;
    }

    public static OptionBuilder hasOptionalArgs() {
        numberOfArgs = -2;
        optionalArg = true;
        return instance;
    }

    public static OptionBuilder hasOptionalArgs(int i2) {
        numberOfArgs = i2;
        optionalArg = true;
        return instance;
    }

    public static OptionBuilder isRequired() {
        required = true;
        return instance;
    }

    public static OptionBuilder isRequired(boolean z) {
        required = z;
        return instance;
    }

    private static void reset() {
        description = null;
        argName = HelpFormatter.DEFAULT_ARG_NAME;
        longopt = null;
        type = null;
        required = false;
        numberOfArgs = -1;
        optionalArg = false;
        valuesep = (char) 0;
    }

    public static OptionBuilder withArgName(String str) {
        argName = str;
        return instance;
    }

    public static OptionBuilder withDescription(String str) {
        description = str;
        return instance;
    }

    public static OptionBuilder withLongOpt(String str) {
        longopt = str;
        return instance;
    }

    public static OptionBuilder withType(Object obj) {
        type = obj;
        return instance;
    }

    public static OptionBuilder withValueSeparator() {
        valuesep = '=';
        return instance;
    }

    public static OptionBuilder withValueSeparator(char c2) {
        valuesep = c2;
        return instance;
    }
}
