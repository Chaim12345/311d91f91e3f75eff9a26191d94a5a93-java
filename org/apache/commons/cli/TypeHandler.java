package org.apache.commons.cli;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
/* loaded from: classes3.dex */
public class TypeHandler {
    public static Class createClass(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Unable to find the class: ");
            stringBuffer.append(str);
            throw new ParseException(stringBuffer.toString());
        }
    }

    public static Date createDate(String str) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static File createFile(String str) {
        return new File(str);
    }

    public static File[] createFiles(String str) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static Number createNumber(String str) {
        try {
            return str.indexOf(46) != -1 ? Double.valueOf(str) : Long.valueOf(str);
        } catch (NumberFormatException e2) {
            throw new ParseException(e2.getMessage());
        }
    }

    public static Object createObject(String str) {
        try {
            try {
                return Class.forName(str).newInstance();
            } catch (Exception e2) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(e2.getClass().getName());
                stringBuffer.append("; Unable to create an instance of: ");
                stringBuffer.append(str);
                throw new ParseException(stringBuffer.toString());
            }
        } catch (ClassNotFoundException unused) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Unable to find the class: ");
            stringBuffer2.append(str);
            throw new ParseException(stringBuffer2.toString());
        }
    }

    public static URL createURL(String str) {
        try {
            return new URL(str);
        } catch (MalformedURLException unused) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Unable to parse the URL: ");
            stringBuffer.append(str);
            throw new ParseException(stringBuffer.toString());
        }
    }

    public static Object createValue(String str, Class cls) {
        if (PatternOptionBuilder.STRING_VALUE == cls) {
            return str;
        }
        if (PatternOptionBuilder.OBJECT_VALUE == cls) {
            return createObject(str);
        }
        if (PatternOptionBuilder.NUMBER_VALUE == cls) {
            return createNumber(str);
        }
        if (PatternOptionBuilder.DATE_VALUE == cls) {
            return createDate(str);
        }
        if (PatternOptionBuilder.CLASS_VALUE == cls) {
            return createClass(str);
        }
        if (PatternOptionBuilder.FILE_VALUE != cls && PatternOptionBuilder.EXISTING_FILE_VALUE != cls) {
            if (PatternOptionBuilder.FILES_VALUE == cls) {
                return createFiles(str);
            }
            if (PatternOptionBuilder.URL_VALUE == cls) {
                return createURL(str);
            }
            return null;
        }
        return createFile(str);
    }

    public static Object createValue(String str, Object obj) {
        return createValue(str, (Class) obj);
    }
}
