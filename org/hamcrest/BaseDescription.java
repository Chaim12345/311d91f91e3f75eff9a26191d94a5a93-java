package org.hamcrest;

import java.util.Arrays;
import java.util.Iterator;
import kotlin.text.Typography;
import org.hamcrest.internal.ArrayIterator;
import org.hamcrest.internal.SelfDescribingValueIterator;
/* loaded from: classes4.dex */
public abstract class BaseDescription implements Description {
    private Description appendList(String str, String str2, String str3, Iterator<? extends SelfDescribing> it) {
        b(str);
        boolean z = false;
        while (it.hasNext()) {
            if (z) {
                b(str2);
            }
            appendDescriptionOf(it.next());
            z = true;
        }
        b(str3);
        return this;
    }

    private <T> Description appendValueList(String str, String str2, String str3, Iterator<T> it) {
        return appendList(str, str2, str3, new SelfDescribingValueIterator(it));
    }

    private String descriptionOf(Object obj) {
        try {
            return String.valueOf(obj);
        } catch (Exception unused) {
            return obj.getClass().getName() + "@" + Integer.toHexString(obj.hashCode());
        }
    }

    private void toJavaSyntax(char c2) {
        String str;
        if (c2 == '\t') {
            str = "\\t";
        } else if (c2 == '\n') {
            str = "\\n";
        } else if (c2 == '\r') {
            str = "\\r";
        } else if (c2 == '\"') {
            str = "\\\"";
        } else if (c2 != '\\') {
            a(c2);
            return;
        } else {
            str = "\\\\";
        }
        b(str);
    }

    private void toJavaSyntax(String str) {
        a('\"');
        for (int i2 = 0; i2 < str.length(); i2++) {
            toJavaSyntax(str.charAt(i2));
        }
        a('\"');
    }

    protected abstract void a(char c2);

    @Override // org.hamcrest.Description
    public Description appendDescriptionOf(SelfDescribing selfDescribing) {
        selfDescribing.describeTo(this);
        return this;
    }

    @Override // org.hamcrest.Description
    public Description appendList(String str, String str2, String str3, Iterable<? extends SelfDescribing> iterable) {
        return appendList(str, str2, str3, iterable.iterator());
    }

    @Override // org.hamcrest.Description
    public Description appendText(String str) {
        b(str);
        return this;
    }

    @Override // org.hamcrest.Description
    public Description appendValue(Object obj) {
        String str;
        if (obj != null) {
            if (obj instanceof String) {
                toJavaSyntax((String) obj);
            } else if (obj instanceof Character) {
                a('\"');
                toJavaSyntax(((Character) obj).charValue());
                a('\"');
            } else if (obj instanceof Byte) {
                a(Typography.less);
                b(descriptionOf(obj));
                str = "b>";
            } else if (obj instanceof Short) {
                a(Typography.less);
                b(descriptionOf(obj));
                str = "s>";
            } else if (obj instanceof Long) {
                a(Typography.less);
                b(descriptionOf(obj));
                str = "L>";
            } else if (obj instanceof Float) {
                a(Typography.less);
                b(descriptionOf(obj));
                str = "F>";
            } else if (obj.getClass().isArray()) {
                appendValueList("[", ", ", "]", new ArrayIterator(obj));
            } else {
                a(Typography.less);
                b(descriptionOf(obj));
                a(Typography.greater);
            }
            return this;
        }
        str = "null";
        b(str);
        return this;
    }

    @Override // org.hamcrest.Description
    public <T> Description appendValueList(String str, String str2, String str3, Iterable<T> iterable) {
        return appendValueList(str, str2, str3, iterable.iterator());
    }

    @Override // org.hamcrest.Description
    @SafeVarargs
    public final <T> Description appendValueList(String str, String str2, String str3, T... tArr) {
        return appendValueList(str, str2, str3, Arrays.asList(tArr));
    }

    protected void b(String str) {
        for (int i2 = 0; i2 < str.length(); i2++) {
            a(str.charAt(i2));
        }
    }
}
