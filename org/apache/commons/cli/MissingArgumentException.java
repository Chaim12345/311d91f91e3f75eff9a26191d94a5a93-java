package org.apache.commons.cli;
/* loaded from: classes3.dex */
public class MissingArgumentException extends ParseException {
    private Option option;

    public MissingArgumentException(String str) {
        super(str);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public MissingArgumentException(Option option) {
        this(r0.toString());
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Missing argument for option: ");
        stringBuffer.append(option.getKey());
        this.option = option;
    }

    public Option getOption() {
        return this.option;
    }
}
