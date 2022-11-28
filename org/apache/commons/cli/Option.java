package org.apache.commons.cli;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public class Option implements Cloneable, Serializable {
    public static final int UNINITIALIZED = -1;
    public static final int UNLIMITED_VALUES = -2;
    private static final long serialVersionUID = 1;
    private String argName;
    private String description;
    private String longOpt;
    private int numberOfArgs;
    private String opt;
    private boolean optionalArg;
    private boolean required;
    private Object type;
    private List values;
    private char valuesep;

    public Option(String str, String str2) {
        this(str, null, false, str2);
    }

    public Option(String str, String str2, boolean z, String str3) {
        this.argName = HelpFormatter.DEFAULT_ARG_NAME;
        this.numberOfArgs = -1;
        this.values = new ArrayList();
        OptionValidator.validateOption(str);
        this.opt = str;
        this.longOpt = str2;
        if (z) {
            this.numberOfArgs = 1;
        }
        this.description = str3;
    }

    public Option(String str, boolean z, String str2) {
        this(str, null, z, str2);
    }

    private void add(String str) {
        if (this.numberOfArgs > 0 && this.values.size() > this.numberOfArgs - 1) {
            throw new RuntimeException("Cannot add value, list full.");
        }
        this.values.add(str);
    }

    private boolean hasNoValues() {
        return this.values.isEmpty();
    }

    private void processValue(String str) {
        if (hasValueSeparator()) {
            char valueSeparator = getValueSeparator();
            while (true) {
                int indexOf = str.indexOf(valueSeparator);
                if (indexOf == -1 || this.values.size() == this.numberOfArgs - 1) {
                    break;
                }
                add(str.substring(0, indexOf));
                str = str.substring(indexOf + 1);
            }
        }
        add(str);
    }

    public boolean addValue(String str) {
        throw new UnsupportedOperationException("The addValue method is not intended for client use. Subclasses should use the addValueForProcessing method instead. ");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addValueForProcessing(String str) {
        if (this.numberOfArgs == -1) {
            throw new RuntimeException("NO_ARGS_ALLOWED");
        }
        processValue(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearValues() {
        this.values.clear();
    }

    public Object clone() {
        try {
            Option option = (Option) super.clone();
            option.values = new ArrayList(this.values);
            return option;
        } catch (CloneNotSupportedException e2) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("A CloneNotSupportedException was thrown: ");
            stringBuffer.append(e2.getMessage());
            throw new RuntimeException(stringBuffer.toString());
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Option option = (Option) obj;
        String str = this.opt;
        if (str == null ? option.opt == null : str.equals(option.opt)) {
            String str2 = this.longOpt;
            String str3 = option.longOpt;
            return str2 == null ? str3 == null : str2.equals(str3);
        }
        return false;
    }

    public String getArgName() {
        return this.argName;
    }

    public int getArgs() {
        return this.numberOfArgs;
    }

    public String getDescription() {
        return this.description;
    }

    public int getId() {
        return getKey().charAt(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getKey() {
        String str = this.opt;
        return str == null ? this.longOpt : str;
    }

    public String getLongOpt() {
        return this.longOpt;
    }

    public String getOpt() {
        return this.opt;
    }

    public Object getType() {
        return this.type;
    }

    public String getValue() {
        if (hasNoValues()) {
            return null;
        }
        return (String) this.values.get(0);
    }

    public String getValue(int i2) {
        if (hasNoValues()) {
            return null;
        }
        return (String) this.values.get(i2);
    }

    public String getValue(String str) {
        String value = getValue();
        return value != null ? value : str;
    }

    public char getValueSeparator() {
        return this.valuesep;
    }

    public String[] getValues() {
        if (hasNoValues()) {
            return null;
        }
        List list = this.values;
        return (String[]) list.toArray(new String[list.size()]);
    }

    public List getValuesList() {
        return this.values;
    }

    public boolean hasArg() {
        int i2 = this.numberOfArgs;
        return i2 > 0 || i2 == -2;
    }

    public boolean hasArgName() {
        String str = this.argName;
        return str != null && str.length() > 0;
    }

    public boolean hasArgs() {
        int i2 = this.numberOfArgs;
        return i2 > 1 || i2 == -2;
    }

    public boolean hasLongOpt() {
        return this.longOpt != null;
    }

    public boolean hasOptionalArg() {
        return this.optionalArg;
    }

    public boolean hasValueSeparator() {
        return this.valuesep > 0;
    }

    public int hashCode() {
        String str = this.opt;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.longOpt;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public boolean isRequired() {
        return this.required;
    }

    public void setArgName(String str) {
        this.argName = str;
    }

    public void setArgs(int i2) {
        this.numberOfArgs = i2;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setLongOpt(String str) {
        this.longOpt = str;
    }

    public void setOptionalArg(boolean z) {
        this.optionalArg = z;
    }

    public void setRequired(boolean z) {
        this.required = z;
    }

    public void setType(Object obj) {
        this.type = obj;
    }

    public void setValueSeparator(char c2) {
        this.valuesep = c2;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public String toString() {
        String str;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[ option: ");
        stringBuffer.append(this.opt);
        if (this.longOpt != null) {
            stringBuffer.append(" ");
            stringBuffer.append(this.longOpt);
        }
        stringBuffer.append(" ");
        if (!hasArgs()) {
            str = hasArg() ? " [ARG]" : " [ARG]";
            stringBuffer.append(" :: ");
            stringBuffer.append(this.description);
            if (this.type != null) {
                stringBuffer.append(" :: ");
                stringBuffer.append(this.type);
            }
            stringBuffer.append(" ]");
            return stringBuffer.toString();
        }
        str = "[ARG...]";
        stringBuffer.append(str);
        stringBuffer.append(" :: ");
        stringBuffer.append(this.description);
        if (this.type != null) {
        }
        stringBuffer.append(" ]");
        return stringBuffer.toString();
    }
}
