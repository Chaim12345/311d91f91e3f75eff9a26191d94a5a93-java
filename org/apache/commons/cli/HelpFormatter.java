package org.apache.commons.cli;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.http.message.TokenParser;
/* loaded from: classes3.dex */
public class HelpFormatter {
    public static final String DEFAULT_ARG_NAME = "arg";
    public static final int DEFAULT_DESC_PAD = 3;
    public static final int DEFAULT_LEFT_PAD = 1;
    public static final String DEFAULT_LONG_OPT_PREFIX = "--";
    public static final String DEFAULT_OPT_PREFIX = "-";
    public static final String DEFAULT_SYNTAX_PREFIX = "usage: ";
    public static final int DEFAULT_WIDTH = 74;
    public int defaultWidth = 74;
    public int defaultLeftPad = 1;
    public int defaultDescPad = 3;
    public String defaultSyntaxPrefix = DEFAULT_SYNTAX_PREFIX;
    public String defaultNewLine = System.getProperty("line.separator");
    public String defaultOptPrefix = DEFAULT_OPT_PREFIX;
    public String defaultLongOptPrefix = DEFAULT_LONG_OPT_PREFIX;
    public String defaultArgName = DEFAULT_ARG_NAME;
    protected Comparator optionComparator = new OptionComparator();

    /* loaded from: classes3.dex */
    private static class OptionComparator implements Comparator {
        private OptionComparator() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ((Option) obj).getKey().compareToIgnoreCase(((Option) obj2).getKey());
        }
    }

    private static void appendOption(StringBuffer stringBuffer, Option option, boolean z) {
        String longOpt;
        if (!z) {
            stringBuffer.append("[");
        }
        if (option.getOpt() != null) {
            stringBuffer.append(DEFAULT_OPT_PREFIX);
            longOpt = option.getOpt();
        } else {
            stringBuffer.append(DEFAULT_LONG_OPT_PREFIX);
            longOpt = option.getLongOpt();
        }
        stringBuffer.append(longOpt);
        if (option.hasArg() && option.hasArgName()) {
            stringBuffer.append(" <");
            stringBuffer.append(option.getArgName());
            stringBuffer.append(">");
        }
        if (z) {
            return;
        }
        stringBuffer.append("]");
    }

    private void appendOptionGroup(StringBuffer stringBuffer, OptionGroup optionGroup) {
        if (!optionGroup.isRequired()) {
            stringBuffer.append("[");
        }
        ArrayList arrayList = new ArrayList(optionGroup.getOptions());
        Collections.sort(arrayList, getOptionComparator());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            appendOption(stringBuffer, (Option) it.next(), true);
            if (it.hasNext()) {
                stringBuffer.append(" | ");
            }
        }
        if (optionGroup.isRequired()) {
            return;
        }
        stringBuffer.append("]");
    }

    protected String createPadding(int i2) {
        StringBuffer stringBuffer = new StringBuffer(i2);
        for (int i3 = 0; i3 < i2; i3++) {
            stringBuffer.append(TokenParser.SP);
        }
        return stringBuffer.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0036, code lost:
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected int findWrapPos(String str, int i2, int i3) {
        char charAt;
        char charAt2;
        int indexOf = str.indexOf(10, i3);
        if ((indexOf == -1 || indexOf > i2) && ((indexOf = str.indexOf(9, i3)) == -1 || indexOf > i2)) {
            int i4 = i2 + i3;
            if (i4 >= str.length()) {
                return -1;
            }
            int i5 = i4;
            while (i5 >= i3 && (charAt2 = str.charAt(i5)) != ' ' && charAt2 != '\n' && charAt2 != '\r') {
                i5--;
            }
            while (i4 <= str.length() && (charAt = str.charAt(i4)) != ' ' && charAt != '\n' && charAt != '\r') {
                i4++;
            }
            if (i4 == str.length()) {
                return -1;
            }
            return i4;
        }
        return indexOf + 1;
    }

    public String getArgName() {
        return this.defaultArgName;
    }

    public int getDescPadding() {
        return this.defaultDescPad;
    }

    public int getLeftPadding() {
        return this.defaultLeftPad;
    }

    public String getLongOptPrefix() {
        return this.defaultLongOptPrefix;
    }

    public String getNewLine() {
        return this.defaultNewLine;
    }

    public String getOptPrefix() {
        return this.defaultOptPrefix;
    }

    public Comparator getOptionComparator() {
        return this.optionComparator;
    }

    public String getSyntaxPrefix() {
        return this.defaultSyntaxPrefix;
    }

    public int getWidth() {
        return this.defaultWidth;
    }

    public void printHelp(int i2, String str, String str2, Options options, String str3) {
        printHelp(i2, str, str2, options, str3, false);
    }

    public void printHelp(int i2, String str, String str2, Options options, String str3, boolean z) {
        PrintWriter printWriter = new PrintWriter(System.out);
        printHelp(printWriter, i2, str, str2, options, this.defaultLeftPad, this.defaultDescPad, str3, z);
        printWriter.flush();
    }

    public void printHelp(PrintWriter printWriter, int i2, String str, String str2, Options options, int i3, int i4, String str3) {
        printHelp(printWriter, i2, str, str2, options, i3, i4, str3, false);
    }

    public void printHelp(PrintWriter printWriter, int i2, String str, String str2, Options options, int i3, int i4, String str3, boolean z) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("cmdLineSyntax not provided");
        }
        if (z) {
            printUsage(printWriter, i2, str, options);
        } else {
            printUsage(printWriter, i2, str);
        }
        if (str2 != null && str2.trim().length() > 0) {
            printWrapped(printWriter, i2, str2);
        }
        printOptions(printWriter, i2, options, i3, i4);
        if (str3 == null || str3.trim().length() <= 0) {
            return;
        }
        printWrapped(printWriter, i2, str3);
    }

    public void printHelp(String str, String str2, Options options, String str3) {
        printHelp(str, str2, options, str3, false);
    }

    public void printHelp(String str, String str2, Options options, String str3, boolean z) {
        printHelp(this.defaultWidth, str, str2, options, str3, z);
    }

    public void printHelp(String str, Options options) {
        printHelp(this.defaultWidth, str, null, options, null, false);
    }

    public void printHelp(String str, Options options, boolean z) {
        printHelp(this.defaultWidth, str, null, options, null, z);
    }

    public void printOptions(PrintWriter printWriter, int i2, Options options, int i3, int i4) {
        StringBuffer stringBuffer = new StringBuffer();
        renderOptions(stringBuffer, i2, options, i3, i4);
        printWriter.println(stringBuffer.toString());
    }

    public void printUsage(PrintWriter printWriter, int i2, String str) {
        int length = this.defaultSyntaxPrefix.length() + str.indexOf(32) + 1;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.defaultSyntaxPrefix);
        stringBuffer.append(str);
        printWrapped(printWriter, i2, length, stringBuffer.toString());
    }

    public void printUsage(PrintWriter printWriter, int i2, String str, Options options) {
        StringBuffer stringBuffer = new StringBuffer(this.defaultSyntaxPrefix);
        stringBuffer.append(str);
        stringBuffer.append(" ");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList(options.getOptions());
        Collections.sort(arrayList2, getOptionComparator());
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            Option option = (Option) it.next();
            OptionGroup optionGroup = options.getOptionGroup(option);
            if (optionGroup == null) {
                appendOption(stringBuffer, option, option.isRequired());
            } else if (!arrayList.contains(optionGroup)) {
                arrayList.add(optionGroup);
                appendOptionGroup(stringBuffer, optionGroup);
            }
            if (it.hasNext()) {
                stringBuffer.append(" ");
            }
        }
        printWrapped(printWriter, i2, stringBuffer.toString().indexOf(32) + 1, stringBuffer.toString());
    }

    public void printWrapped(PrintWriter printWriter, int i2, int i3, String str) {
        StringBuffer stringBuffer = new StringBuffer(str.length());
        renderWrappedText(stringBuffer, i2, i3, str);
        printWriter.println(stringBuffer.toString());
    }

    public void printWrapped(PrintWriter printWriter, int i2, String str) {
        printWrapped(printWriter, i2, 0, str);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x009e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x001e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected StringBuffer renderOptions(StringBuffer stringBuffer, int i2, Options options, int i3, int i4) {
        String str;
        String createPadding = createPadding(i3);
        String createPadding2 = createPadding(i4);
        ArrayList arrayList = new ArrayList();
        List<Option> helpOptions = options.helpOptions();
        Collections.sort(helpOptions, getOptionComparator());
        int i5 = 0;
        int i6 = 0;
        for (Option option : helpOptions) {
            StringBuffer stringBuffer2 = new StringBuffer(8);
            String opt = option.getOpt();
            stringBuffer2.append(createPadding);
            if (opt == null) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("   ");
                stringBuffer3.append(this.defaultLongOptPrefix);
                str = stringBuffer3.toString();
            } else {
                stringBuffer2.append(this.defaultOptPrefix);
                stringBuffer2.append(option.getOpt());
                if (option.hasLongOpt()) {
                    stringBuffer2.append(AbstractJsonLexerKt.COMMA);
                    str = this.defaultLongOptPrefix;
                }
                if (option.hasArg()) {
                    if (option.hasArgName()) {
                        stringBuffer2.append(" <");
                        stringBuffer2.append(option.getArgName());
                        stringBuffer2.append(">");
                    } else {
                        stringBuffer2.append(TokenParser.SP);
                    }
                }
                arrayList.add(stringBuffer2);
                if (stringBuffer2.length() <= i6) {
                    i6 = stringBuffer2.length();
                }
            }
            stringBuffer2.append(str);
            stringBuffer2.append(option.getLongOpt());
            if (option.hasArg()) {
            }
            arrayList.add(stringBuffer2);
            if (stringBuffer2.length() <= i6) {
            }
        }
        Iterator it = helpOptions.iterator();
        while (it.hasNext()) {
            Option option2 = (Option) it.next();
            int i7 = i5 + 1;
            StringBuffer stringBuffer4 = new StringBuffer(arrayList.get(i5).toString());
            if (stringBuffer4.length() < i6) {
                stringBuffer4.append(createPadding(i6 - stringBuffer4.length()));
            }
            stringBuffer4.append(createPadding2);
            int i8 = i6 + i4;
            if (option2.getDescription() != null) {
                stringBuffer4.append(option2.getDescription());
            }
            renderWrappedText(stringBuffer, i2, i8, stringBuffer4.toString());
            if (it.hasNext()) {
                stringBuffer.append(this.defaultNewLine);
            }
            i5 = i7;
        }
        return stringBuffer;
    }

    protected StringBuffer renderWrappedText(StringBuffer stringBuffer, int i2, int i3, String str) {
        int findWrapPos = findWrapPos(str, i2, 0);
        if (findWrapPos == -1) {
            stringBuffer.append(rtrim(str));
            return stringBuffer;
        }
        stringBuffer.append(rtrim(str.substring(0, findWrapPos)));
        stringBuffer.append(this.defaultNewLine);
        if (i3 >= i2) {
            i3 = 1;
        }
        String createPadding = createPadding(i3);
        while (true) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(createPadding);
            stringBuffer2.append(str.substring(findWrapPos).trim());
            str = stringBuffer2.toString();
            findWrapPos = findWrapPos(str, i2, 0);
            if (findWrapPos == -1) {
                stringBuffer.append(str);
                return stringBuffer;
            }
            if (str.length() > i2 && findWrapPos == i3 - 1) {
                findWrapPos = i2;
            }
            stringBuffer.append(rtrim(str.substring(0, findWrapPos)));
            stringBuffer.append(this.defaultNewLine);
        }
    }

    protected String rtrim(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        int length = str.length();
        while (length > 0 && Character.isWhitespace(str.charAt(length - 1))) {
            length--;
        }
        return str.substring(0, length);
    }

    public void setArgName(String str) {
        this.defaultArgName = str;
    }

    public void setDescPadding(int i2) {
        this.defaultDescPad = i2;
    }

    public void setLeftPadding(int i2) {
        this.defaultLeftPad = i2;
    }

    public void setLongOptPrefix(String str) {
        this.defaultLongOptPrefix = str;
    }

    public void setNewLine(String str) {
        this.defaultNewLine = str;
    }

    public void setOptPrefix(String str) {
        this.defaultOptPrefix = str;
    }

    public void setOptionComparator(Comparator comparator) {
        if (comparator == null) {
            comparator = new OptionComparator();
        }
        this.optionComparator = comparator;
    }

    public void setSyntaxPrefix(String str) {
        this.defaultSyntaxPrefix = str;
    }

    public void setWidth(int i2) {
        this.defaultWidth = i2;
    }
}
