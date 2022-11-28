package org.apache.commons.cli;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
/* loaded from: classes3.dex */
public class CommandLine implements Serializable {
    private static final long serialVersionUID = 1;
    private List args = new LinkedList();
    private List options = new ArrayList();

    /* JADX WARN: Removed duplicated region for block: B:5:0x0010  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private Option resolveOption(String str) {
        String stripLeadingHyphens = Util.stripLeadingHyphens(str);
        for (Option option : this.options) {
            if (stripLeadingHyphens.equals(option.getOpt()) || stripLeadingHyphens.equals(option.getLongOpt())) {
                return option;
            }
            while (r0.hasNext()) {
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addArg(String str) {
        this.args.add(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addOption(Option option) {
        this.options.add(option);
    }

    public List getArgList() {
        return this.args;
    }

    public String[] getArgs() {
        String[] strArr = new String[this.args.size()];
        this.args.toArray(strArr);
        return strArr;
    }

    public Object getOptionObject(char c2) {
        return getOptionObject(String.valueOf(c2));
    }

    public Object getOptionObject(String str) {
        try {
            return getParsedOptionValue(str);
        } catch (ParseException e2) {
            PrintStream printStream = System.err;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Exception found converting ");
            stringBuffer.append(str);
            stringBuffer.append(" to desired type: ");
            stringBuffer.append(e2.getMessage());
            printStream.println(stringBuffer.toString());
            return null;
        }
    }

    public Properties getOptionProperties(String str) {
        Properties properties = new Properties();
        for (Option option : this.options) {
            if (str.equals(option.getOpt()) || str.equals(option.getLongOpt())) {
                List valuesList = option.getValuesList();
                if (valuesList.size() >= 2) {
                    properties.put(valuesList.get(0), valuesList.get(1));
                } else if (valuesList.size() == 1) {
                    properties.put(valuesList.get(0), "true");
                }
            }
        }
        return properties;
    }

    public String getOptionValue(char c2) {
        return getOptionValue(String.valueOf(c2));
    }

    public String getOptionValue(char c2, String str) {
        return getOptionValue(String.valueOf(c2), str);
    }

    public String getOptionValue(String str) {
        String[] optionValues = getOptionValues(str);
        if (optionValues == null) {
            return null;
        }
        return optionValues[0];
    }

    public String getOptionValue(String str, String str2) {
        String optionValue = getOptionValue(str);
        return optionValue != null ? optionValue : str2;
    }

    public String[] getOptionValues(char c2) {
        return getOptionValues(String.valueOf(c2));
    }

    public String[] getOptionValues(String str) {
        ArrayList arrayList = new ArrayList();
        for (Option option : this.options) {
            if (str.equals(option.getOpt()) || str.equals(option.getLongOpt())) {
                arrayList.addAll(option.getValuesList());
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public Option[] getOptions() {
        List list = this.options;
        return (Option[]) list.toArray(new Option[list.size()]);
    }

    public Object getParsedOptionValue(String str) {
        String optionValue = getOptionValue(str);
        Option resolveOption = resolveOption(str);
        if (resolveOption == null) {
            return null;
        }
        Object type = resolveOption.getType();
        if (optionValue == null) {
            return null;
        }
        return TypeHandler.createValue(optionValue, type);
    }

    public boolean hasOption(char c2) {
        return hasOption(String.valueOf(c2));
    }

    public boolean hasOption(String str) {
        return this.options.contains(resolveOption(str));
    }

    public Iterator iterator() {
        return this.options.iterator();
    }
}
