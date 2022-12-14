package org.apache.commons.cli;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes3.dex */
public class OptionGroup implements Serializable {
    private static final long serialVersionUID = 1;
    private Map optionMap = new HashMap();
    private boolean required;
    private String selected;

    public OptionGroup addOption(Option option) {
        this.optionMap.put(option.getKey(), option);
        return this;
    }

    public Collection getNames() {
        return this.optionMap.keySet();
    }

    public Collection getOptions() {
        return this.optionMap.values();
    }

    public String getSelected() {
        return this.selected;
    }

    public boolean isRequired() {
        return this.required;
    }

    public void setRequired(boolean z) {
        this.required = z;
    }

    public void setSelected(Option option) {
        String str = this.selected;
        if (str != null && !str.equals(option.getOpt())) {
            throw new AlreadySelectedException(this, option);
        }
        this.selected = option.getOpt();
    }

    public String toString() {
        String longOpt;
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it = getOptions().iterator();
        String str = "[";
        while (true) {
            stringBuffer.append(str);
            while (it.hasNext()) {
                Option option = (Option) it.next();
                if (option.getOpt() != null) {
                    stringBuffer.append(HelpFormatter.DEFAULT_OPT_PREFIX);
                    longOpt = option.getOpt();
                } else {
                    stringBuffer.append(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
                    longOpt = option.getLongOpt();
                }
                stringBuffer.append(longOpt);
                stringBuffer.append(" ");
                stringBuffer.append(option.getDescription());
                if (it.hasNext()) {
                    break;
                }
            }
            stringBuffer.append("]");
            return stringBuffer.toString();
            str = ", ";
        }
    }
}
