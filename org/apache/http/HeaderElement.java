package org.apache.http;
/* loaded from: classes3.dex */
public interface HeaderElement {
    String getName();

    NameValuePair getParameter(int i2);

    NameValuePair getParameterByName(String str);

    int getParameterCount();

    NameValuePair[] getParameters();

    String getValue();
}
