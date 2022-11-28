package org.bouncycastle.i18n;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.TimeZone;
import org.bouncycastle.i18n.filter.Filter;
import org.bouncycastle.i18n.filter.TrustedInput;
import org.bouncycastle.i18n.filter.UntrustedInput;
import org.bouncycastle.i18n.filter.UntrustedUrlInput;
/* loaded from: classes3.dex */
public class LocalizedMessage {
    public static final String DEFAULT_ENCODING = "ISO-8859-1";

    /* renamed from: a  reason: collision with root package name */
    protected final String f13582a;

    /* renamed from: b  reason: collision with root package name */
    protected final String f13583b;

    /* renamed from: c  reason: collision with root package name */
    protected String f13584c;

    /* renamed from: d  reason: collision with root package name */
    protected FilteredArguments f13585d;

    /* renamed from: e  reason: collision with root package name */
    protected FilteredArguments f13586e;

    /* renamed from: f  reason: collision with root package name */
    protected Filter f13587f;

    /* renamed from: g  reason: collision with root package name */
    protected ClassLoader f13588g;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes3.dex */
    public class FilteredArguments {

        /* renamed from: a  reason: collision with root package name */
        protected Filter f13589a;

        /* renamed from: b  reason: collision with root package name */
        protected boolean[] f13590b;

        /* renamed from: c  reason: collision with root package name */
        protected int[] f13591c;

        /* renamed from: d  reason: collision with root package name */
        protected Object[] f13592d;

        /* renamed from: e  reason: collision with root package name */
        protected Object[] f13593e;

        /* renamed from: f  reason: collision with root package name */
        protected Object[] f13594f;

        FilteredArguments(LocalizedMessage localizedMessage) {
            this(localizedMessage, new Object[0]);
        }

        FilteredArguments(LocalizedMessage localizedMessage, Object[] objArr) {
            this.f13589a = null;
            this.f13592d = objArr;
            this.f13593e = new Object[objArr.length];
            this.f13594f = new Object[objArr.length];
            this.f13590b = new boolean[objArr.length];
            this.f13591c = new int[objArr.length];
            for (int i2 = 0; i2 < objArr.length; i2++) {
                if (objArr[i2] instanceof TrustedInput) {
                    this.f13593e[i2] = ((TrustedInput) objArr[i2]).getInput();
                    this.f13591c[i2] = 0;
                } else if (objArr[i2] instanceof UntrustedInput) {
                    this.f13593e[i2] = ((UntrustedInput) objArr[i2]).getInput();
                    if (objArr[i2] instanceof UntrustedUrlInput) {
                        this.f13591c[i2] = 2;
                    } else {
                        this.f13591c[i2] = 1;
                    }
                } else {
                    this.f13593e[i2] = objArr[i2];
                    this.f13591c[i2] = 1;
                }
                this.f13590b[i2] = this.f13593e[i2] instanceof LocaleString;
            }
        }

        private Object filter(int i2, Object obj) {
            Filter filter = this.f13589a;
            if (filter != null) {
                if (obj == null) {
                    obj = "null";
                }
                if (i2 != 0) {
                    if (i2 != 1) {
                        if (i2 != 2) {
                            return null;
                        }
                        return filter.doFilterUrl(obj.toString());
                    }
                    return filter.doFilter(obj.toString());
                }
            }
            return obj;
        }

        public Object[] getArguments() {
            return this.f13592d;
        }

        public Filter getFilter() {
            return this.f13589a;
        }

        public Object[] getFilteredArgs(Locale locale) {
            Object filter;
            Object[] objArr = new Object[this.f13593e.length];
            int i2 = 0;
            while (true) {
                Object[] objArr2 = this.f13593e;
                if (i2 >= objArr2.length) {
                    return objArr;
                }
                Object[] objArr3 = this.f13594f;
                if (objArr3[i2] != null) {
                    filter = objArr3[i2];
                } else {
                    Object obj = objArr2[i2];
                    if (this.f13590b[i2]) {
                        filter = filter(this.f13591c[i2], ((LocaleString) obj).getLocaleString(locale));
                    } else {
                        filter = filter(this.f13591c[i2], obj);
                        this.f13594f[i2] = filter;
                    }
                }
                objArr[i2] = filter;
                i2++;
            }
        }

        public boolean isEmpty() {
            return this.f13593e.length == 0;
        }

        public void setFilter(Filter filter) {
            if (filter != this.f13589a) {
                for (int i2 = 0; i2 < this.f13593e.length; i2++) {
                    this.f13594f[i2] = null;
                }
            }
            this.f13589a = filter;
        }
    }

    public LocalizedMessage(String str, String str2) {
        this.f13584c = "ISO-8859-1";
        this.f13586e = null;
        this.f13587f = null;
        this.f13588g = null;
        if (str == null || str2 == null) {
            throw null;
        }
        this.f13582a = str2;
        this.f13583b = str;
        this.f13585d = new FilteredArguments(this);
    }

    public LocalizedMessage(String str, String str2, String str3) {
        this.f13584c = "ISO-8859-1";
        this.f13586e = null;
        this.f13587f = null;
        this.f13588g = null;
        if (str == null || str2 == null) {
            throw null;
        }
        this.f13582a = str2;
        this.f13583b = str;
        this.f13585d = new FilteredArguments(this);
        if (Charset.isSupported(str3)) {
            this.f13584c = str3;
            return;
        }
        throw new UnsupportedEncodingException("The encoding \"" + str3 + "\" is not supported.");
    }

    public LocalizedMessage(String str, String str2, String str3, Object[] objArr) {
        this.f13584c = "ISO-8859-1";
        this.f13586e = null;
        this.f13587f = null;
        this.f13588g = null;
        if (str == null || str2 == null || objArr == null) {
            throw null;
        }
        this.f13582a = str2;
        this.f13583b = str;
        this.f13585d = new FilteredArguments(this, objArr);
        if (Charset.isSupported(str3)) {
            this.f13584c = str3;
            return;
        }
        throw new UnsupportedEncodingException("The encoding \"" + str3 + "\" is not supported.");
    }

    public LocalizedMessage(String str, String str2, Object[] objArr) {
        this.f13584c = "ISO-8859-1";
        this.f13586e = null;
        this.f13587f = null;
        this.f13588g = null;
        if (str == null || str2 == null || objArr == null) {
            throw null;
        }
        this.f13582a = str2;
        this.f13583b = str;
        this.f13585d = new FilteredArguments(this, objArr);
    }

    protected String a(String str, Locale locale) {
        if (this.f13586e != null) {
            StringBuffer stringBuffer = new StringBuffer(str);
            Object[] filteredArgs = this.f13586e.getFilteredArgs(locale);
            for (Object obj : filteredArgs) {
                stringBuffer.append(obj);
            }
            return stringBuffer.toString();
        }
        return str;
    }

    protected String b(String str, Object[] objArr, Locale locale, TimeZone timeZone) {
        MessageFormat messageFormat = new MessageFormat(" ");
        messageFormat.setLocale(locale);
        messageFormat.applyPattern(str);
        if (!timeZone.equals(TimeZone.getDefault())) {
            Format[] formats = messageFormat.getFormats();
            for (int i2 = 0; i2 < formats.length; i2++) {
                if (formats[i2] instanceof DateFormat) {
                    DateFormat dateFormat = (DateFormat) formats[i2];
                    dateFormat.setTimeZone(timeZone);
                    messageFormat.setFormat(i2, dateFormat);
                }
            }
        }
        return messageFormat.format(objArr);
    }

    public Object[] getArguments() {
        return this.f13585d.getArguments();
    }

    public ClassLoader getClassLoader() {
        return this.f13588g;
    }

    public String getEntry(String str, Locale locale, TimeZone timeZone) {
        String str2 = this.f13582a;
        if (str != null) {
            str2 = str2 + "." + str;
        }
        String str3 = str2;
        try {
            ClassLoader classLoader = this.f13588g;
            String string = (classLoader == null ? ResourceBundle.getBundle(this.f13583b, locale) : ResourceBundle.getBundle(this.f13583b, locale, classLoader)).getString(str3);
            if (!this.f13584c.equals("ISO-8859-1")) {
                string = new String(string.getBytes("ISO-8859-1"), this.f13584c);
            }
            if (!this.f13585d.isEmpty()) {
                string = b(string, this.f13585d.getFilteredArgs(locale), locale, timeZone);
            }
            return a(string, locale);
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException(e2);
        } catch (MissingResourceException unused) {
            String str4 = "Can't find entry " + str3 + " in resource file " + this.f13583b + ".";
            String str5 = this.f13583b;
            ClassLoader classLoader2 = this.f13588g;
            if (classLoader2 == null) {
                classLoader2 = getClassLoader();
            }
            throw new MissingEntryException(str4, str5, str3, locale, classLoader2);
        }
    }

    public Object[] getExtraArgs() {
        FilteredArguments filteredArguments = this.f13586e;
        if (filteredArguments == null) {
            return null;
        }
        return filteredArguments.getArguments();
    }

    public Filter getFilter() {
        return this.f13587f;
    }

    public String getId() {
        return this.f13582a;
    }

    public String getResource() {
        return this.f13583b;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.f13588g = classLoader;
    }

    public void setExtraArgument(Object obj) {
        setExtraArguments(new Object[]{obj});
    }

    public void setExtraArguments(Object[] objArr) {
        if (objArr == null) {
            this.f13586e = null;
            return;
        }
        FilteredArguments filteredArguments = new FilteredArguments(this, objArr);
        this.f13586e = filteredArguments;
        filteredArguments.setFilter(this.f13587f);
    }

    public void setFilter(Filter filter) {
        this.f13585d.setFilter(filter);
        FilteredArguments filteredArguments = this.f13586e;
        if (filteredArguments != null) {
            filteredArguments.setFilter(filter);
        }
        this.f13587f = filter;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Resource: \"");
        stringBuffer.append(this.f13583b);
        stringBuffer.append("\" Id: \"");
        stringBuffer.append(this.f13582a);
        stringBuffer.append("\"");
        stringBuffer.append(" Arguments: ");
        stringBuffer.append(this.f13585d.getArguments().length);
        stringBuffer.append(" normal");
        FilteredArguments filteredArguments = this.f13586e;
        if (filteredArguments != null && filteredArguments.getArguments().length > 0) {
            stringBuffer.append(", ");
            stringBuffer.append(this.f13586e.getArguments().length);
            stringBuffer.append(" extra");
        }
        stringBuffer.append(" Encoding: ");
        stringBuffer.append(this.f13584c);
        stringBuffer.append(" ClassLoader: ");
        stringBuffer.append(this.f13588g);
        return stringBuffer.toString();
    }
}
