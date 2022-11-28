package org.apache.commons.logging.impl;

import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogConfigurationException;
/* loaded from: classes3.dex */
public class SimpleLog implements Log, Serializable {
    protected static final String DEFAULT_DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss:SSS zzz";
    public static final int LOG_LEVEL_ALL = 0;
    public static final int LOG_LEVEL_DEBUG = 2;
    public static final int LOG_LEVEL_ERROR = 5;
    public static final int LOG_LEVEL_FATAL = 6;
    public static final int LOG_LEVEL_INFO = 3;
    public static final int LOG_LEVEL_OFF = 7;
    public static final int LOG_LEVEL_TRACE = 1;
    public static final int LOG_LEVEL_WARN = 4;
    static /* synthetic */ Class class$java$lang$Thread = null;
    static /* synthetic */ Class class$org$apache$commons$logging$impl$SimpleLog = null;
    protected static DateFormat dateFormatter = null;
    protected static volatile String dateTimeFormat = null;
    private static final long serialVersionUID = 136942970684951178L;
    protected static volatile boolean showDateTime = false;
    protected static volatile boolean showLogName = false;
    protected static volatile boolean showShortName = false;
    protected static final Properties simpleLogProps;
    protected static final String systemPrefix = "org.apache.commons.logging.simplelog.";
    protected volatile int currentLogLevel;
    protected volatile String logName;
    private volatile String shortLogName = null;

    static {
        Properties properties = new Properties();
        simpleLogProps = properties;
        showLogName = false;
        showShortName = true;
        showDateTime = false;
        dateTimeFormat = DEFAULT_DATE_TIME_FORMAT;
        dateFormatter = null;
        InputStream resourceAsStream = getResourceAsStream("simplelog.properties");
        if (resourceAsStream != null) {
            try {
                properties.load(resourceAsStream);
                resourceAsStream.close();
            } catch (IOException unused) {
            }
        }
        showLogName = getBooleanProperty("org.apache.commons.logging.simplelog.showlogname", showLogName);
        showShortName = getBooleanProperty("org.apache.commons.logging.simplelog.showShortLogname", showShortName);
        showDateTime = getBooleanProperty("org.apache.commons.logging.simplelog.showdatetime", showDateTime);
        if (showDateTime) {
            dateTimeFormat = getStringProperty("org.apache.commons.logging.simplelog.dateTimeFormat", dateTimeFormat);
            try {
                dateFormatter = new SimpleDateFormat(dateTimeFormat);
            } catch (IllegalArgumentException unused2) {
                dateTimeFormat = DEFAULT_DATE_TIME_FORMAT;
                dateFormatter = new SimpleDateFormat(dateTimeFormat);
            }
        }
    }

    public SimpleLog(String str) {
        int i2;
        this.logName = null;
        this.logName = str;
        setLevel(3);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("org.apache.commons.logging.simplelog.log.");
        stringBuffer.append(this.logName);
        String stringProperty = getStringProperty(stringBuffer.toString());
        String valueOf = String.valueOf(str);
        while (true) {
            int lastIndexOf = valueOf.lastIndexOf(".");
            if (stringProperty != null || lastIndexOf <= -1) {
                break;
            }
            str = str.substring(0, lastIndexOf);
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("org.apache.commons.logging.simplelog.log.");
            stringBuffer2.append(str);
            stringProperty = getStringProperty(stringBuffer2.toString());
            valueOf = String.valueOf(str);
        }
        stringProperty = stringProperty == null ? getStringProperty("org.apache.commons.logging.simplelog.defaultlog") : stringProperty;
        if ("all".equalsIgnoreCase(stringProperty)) {
            setLevel(0);
            return;
        }
        if ("trace".equalsIgnoreCase(stringProperty)) {
            i2 = 1;
        } else if ("debug".equalsIgnoreCase(stringProperty)) {
            i2 = 2;
        } else if ("info".equalsIgnoreCase(stringProperty)) {
            setLevel(3);
            return;
        } else if ("warn".equalsIgnoreCase(stringProperty)) {
            i2 = 4;
        } else if (Constants.IPC_BUNDLE_KEY_SEND_ERROR.equalsIgnoreCase(stringProperty)) {
            i2 = 5;
        } else if ("fatal".equalsIgnoreCase(stringProperty)) {
            i2 = 6;
        } else if (!"off".equalsIgnoreCase(stringProperty)) {
            return;
        } else {
            i2 = 7;
        }
        setLevel(i2);
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e2) {
            throw new NoClassDefFoundError(e2.getMessage());
        }
    }

    private static boolean getBooleanProperty(String str, boolean z) {
        String stringProperty = getStringProperty(str);
        return stringProperty == null ? z : "true".equalsIgnoreCase(stringProperty);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ClassLoader getContextClassLoader() {
        ClassLoader classLoader = null;
        try {
            Class cls = class$java$lang$Thread;
            if (cls == null) {
                cls = class$("java.lang.Thread");
                class$java$lang$Thread = cls;
            }
            try {
                classLoader = (ClassLoader) cls.getMethod("getContextClassLoader", null).invoke(Thread.currentThread(), null);
            } catch (InvocationTargetException e2) {
                if (!(e2.getTargetException() instanceof SecurityException)) {
                    throw new LogConfigurationException("Unexpected InvocationTargetException", e2.getTargetException());
                }
            }
        } catch (IllegalAccessException | NoSuchMethodException unused) {
        }
        if (classLoader == null) {
            Class cls2 = class$org$apache$commons$logging$impl$SimpleLog;
            if (cls2 == null) {
                cls2 = class$("org.apache.commons.logging.impl.SimpleLog");
                class$org$apache$commons$logging$impl$SimpleLog = cls2;
            }
            return cls2.getClassLoader();
        }
        return classLoader;
    }

    private static InputStream getResourceAsStream(final String str) {
        return (InputStream) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.apache.commons.logging.impl.SimpleLog.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                ClassLoader contextClassLoader = SimpleLog.getContextClassLoader();
                return contextClassLoader != null ? contextClassLoader.getResourceAsStream(str) : ClassLoader.getSystemResourceAsStream(str);
            }
        });
    }

    private static String getStringProperty(String str) {
        String str2;
        try {
            str2 = System.getProperty(str);
        } catch (SecurityException unused) {
            str2 = null;
        }
        return str2 == null ? simpleLogProps.getProperty(str) : str2;
    }

    private static String getStringProperty(String str, String str2) {
        String stringProperty = getStringProperty(str);
        return stringProperty == null ? str2 : stringProperty;
    }

    @Override // org.apache.commons.logging.Log
    public final void debug(Object obj) {
        if (isLevelEnabled(2)) {
            log(2, obj, null);
        }
    }

    @Override // org.apache.commons.logging.Log
    public final void debug(Object obj, Throwable th) {
        if (isLevelEnabled(2)) {
            log(2, obj, th);
        }
    }

    @Override // org.apache.commons.logging.Log
    public final void error(Object obj) {
        if (isLevelEnabled(5)) {
            log(5, obj, null);
        }
    }

    @Override // org.apache.commons.logging.Log
    public final void error(Object obj, Throwable th) {
        if (isLevelEnabled(5)) {
            log(5, obj, th);
        }
    }

    @Override // org.apache.commons.logging.Log
    public final void fatal(Object obj) {
        if (isLevelEnabled(6)) {
            log(6, obj, null);
        }
    }

    @Override // org.apache.commons.logging.Log
    public final void fatal(Object obj, Throwable th) {
        if (isLevelEnabled(6)) {
            log(6, obj, th);
        }
    }

    public int getLevel() {
        return this.currentLogLevel;
    }

    @Override // org.apache.commons.logging.Log
    public final void info(Object obj) {
        if (isLevelEnabled(3)) {
            log(3, obj, null);
        }
    }

    @Override // org.apache.commons.logging.Log
    public final void info(Object obj, Throwable th) {
        if (isLevelEnabled(3)) {
            log(3, obj, th);
        }
    }

    @Override // org.apache.commons.logging.Log
    public final boolean isDebugEnabled() {
        return isLevelEnabled(2);
    }

    @Override // org.apache.commons.logging.Log
    public final boolean isErrorEnabled() {
        return isLevelEnabled(5);
    }

    @Override // org.apache.commons.logging.Log
    public final boolean isFatalEnabled() {
        return isLevelEnabled(6);
    }

    @Override // org.apache.commons.logging.Log
    public final boolean isInfoEnabled() {
        return isLevelEnabled(3);
    }

    protected boolean isLevelEnabled(int i2) {
        return i2 >= this.currentLogLevel;
    }

    @Override // org.apache.commons.logging.Log
    public final boolean isTraceEnabled() {
        return isLevelEnabled(1);
    }

    @Override // org.apache.commons.logging.Log
    public final boolean isWarnEnabled() {
        return isLevelEnabled(4);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0080  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void log(int i2, Object obj, Throwable th) {
        String str;
        String str2;
        String format;
        StringBuffer stringBuffer = new StringBuffer();
        if (showDateTime) {
            Date date = new Date();
            synchronized (dateFormatter) {
                format = dateFormatter.format(date);
            }
            stringBuffer.append(format);
            stringBuffer.append(" ");
        }
        switch (i2) {
            case 1:
                str2 = "[TRACE] ";
                stringBuffer.append(str2);
                if (!showShortName) {
                    if (showLogName) {
                        str = this.logName;
                    }
                    stringBuffer.append(String.valueOf(obj));
                    if (th != null) {
                        stringBuffer.append(" <");
                        stringBuffer.append(th.toString());
                        stringBuffer.append(">");
                        StringWriter stringWriter = new StringWriter(1024);
                        PrintWriter printWriter = new PrintWriter(stringWriter);
                        th.printStackTrace(printWriter);
                        printWriter.close();
                        stringBuffer.append(stringWriter.toString());
                    }
                    write(stringBuffer);
                    return;
                }
                if (this.shortLogName == null) {
                    String substring = this.logName.substring(this.logName.lastIndexOf(".") + 1);
                    this.shortLogName = substring.substring(substring.lastIndexOf("/") + 1);
                }
                str = this.shortLogName;
                stringBuffer.append(String.valueOf(str));
                stringBuffer.append(" - ");
                stringBuffer.append(String.valueOf(obj));
                if (th != null) {
                }
                write(stringBuffer);
                return;
            case 2:
                str2 = "[DEBUG] ";
                stringBuffer.append(str2);
                if (!showShortName) {
                }
                stringBuffer.append(String.valueOf(str));
                stringBuffer.append(" - ");
                stringBuffer.append(String.valueOf(obj));
                if (th != null) {
                }
                write(stringBuffer);
                return;
            case 3:
                str2 = "[INFO] ";
                stringBuffer.append(str2);
                if (!showShortName) {
                }
                stringBuffer.append(String.valueOf(str));
                stringBuffer.append(" - ");
                stringBuffer.append(String.valueOf(obj));
                if (th != null) {
                }
                write(stringBuffer);
                return;
            case 4:
                str2 = "[WARN] ";
                stringBuffer.append(str2);
                if (!showShortName) {
                }
                stringBuffer.append(String.valueOf(str));
                stringBuffer.append(" - ");
                stringBuffer.append(String.valueOf(obj));
                if (th != null) {
                }
                write(stringBuffer);
                return;
            case 5:
                str2 = "[ERROR] ";
                stringBuffer.append(str2);
                if (!showShortName) {
                }
                stringBuffer.append(String.valueOf(str));
                stringBuffer.append(" - ");
                stringBuffer.append(String.valueOf(obj));
                if (th != null) {
                }
                write(stringBuffer);
                return;
            case 6:
                str2 = "[FATAL] ";
                stringBuffer.append(str2);
                if (!showShortName) {
                }
                stringBuffer.append(String.valueOf(str));
                stringBuffer.append(" - ");
                stringBuffer.append(String.valueOf(obj));
                if (th != null) {
                }
                write(stringBuffer);
                return;
            default:
                if (!showShortName) {
                }
                stringBuffer.append(String.valueOf(str));
                stringBuffer.append(" - ");
                stringBuffer.append(String.valueOf(obj));
                if (th != null) {
                }
                write(stringBuffer);
                return;
        }
    }

    public void setLevel(int i2) {
        this.currentLogLevel = i2;
    }

    @Override // org.apache.commons.logging.Log
    public final void trace(Object obj) {
        if (isLevelEnabled(1)) {
            log(1, obj, null);
        }
    }

    @Override // org.apache.commons.logging.Log
    public final void trace(Object obj, Throwable th) {
        if (isLevelEnabled(1)) {
            log(1, obj, th);
        }
    }

    @Override // org.apache.commons.logging.Log
    public final void warn(Object obj) {
        if (isLevelEnabled(4)) {
            log(4, obj, null);
        }
    }

    @Override // org.apache.commons.logging.Log
    public final void warn(Object obj, Throwable th) {
        if (isLevelEnabled(4)) {
            log(4, obj, th);
        }
    }

    protected void write(StringBuffer stringBuffer) {
        System.err.println(stringBuffer.toString());
    }
}
