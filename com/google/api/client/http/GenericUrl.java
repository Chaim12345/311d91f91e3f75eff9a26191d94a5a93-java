package com.google.api.client.http;

import com.fasterxml.jackson.core.JsonPointer;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.escape.CharEscapers;
import com.google.api.client.util.escape.Escaper;
import com.google.api.client.util.escape.PercentEscaper;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
/* loaded from: classes2.dex */
public class GenericUrl extends GenericData {
    private static final Escaper URI_FRAGMENT_ESCAPER = new PercentEscaper("=&-_.!~*'()@:$,;/?:");
    private String fragment;
    private String host;
    private List<String> pathParts;
    private int port;
    private String scheme;
    private String userInfo;
    private boolean verbatim;

    public GenericUrl() {
        this.port = -1;
    }

    public GenericUrl(String str) {
        this(str, false);
    }

    private GenericUrl(String str, String str2, int i2, String str3, String str4, String str5, String str6, boolean z) {
        this.port = -1;
        this.scheme = str.toLowerCase(Locale.US);
        this.host = str2;
        this.port = i2;
        this.pathParts = toPathParts(str3, z);
        this.verbatim = z;
        if (z) {
            this.fragment = str4;
            if (str5 != null) {
                UrlEncodedParser.parse(str5, (Object) this, false);
            }
            this.userInfo = str6;
            return;
        }
        this.fragment = str4 != null ? CharEscapers.decodeUri(str4) : null;
        if (str5 != null) {
            UrlEncodedParser.parse(str5, this);
        }
        this.userInfo = str6 != null ? CharEscapers.decodeUri(str6) : null;
    }

    public GenericUrl(String str, boolean z) {
        this(parseURL(str), z);
    }

    public GenericUrl(URI uri) {
        this(uri, false);
    }

    public GenericUrl(URI uri, boolean z) {
        this(uri.getScheme(), uri.getHost(), uri.getPort(), uri.getRawPath(), uri.getRawFragment(), uri.getRawQuery(), uri.getRawUserInfo(), z);
    }

    public GenericUrl(URL url) {
        this(url, false);
    }

    public GenericUrl(URL url, boolean z) {
        this(url.getProtocol(), url.getHost(), url.getPort(), url.getPath(), url.getRef(), url.getQuery(), url.getUserInfo(), z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Set set, StringBuilder sb, boolean z) {
        Iterator it = set.iterator();
        boolean z2 = true;
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object value = entry.getValue();
            if (value != null) {
                String str = (String) entry.getKey();
                if (!z) {
                    str = CharEscapers.escapeUriQuery(str);
                }
                if (value instanceof Collection) {
                    for (Object obj : (Collection) value) {
                        z2 = appendParam(z2, sb, str, obj, z);
                    }
                } else {
                    z2 = appendParam(z2, sb, str, value, z);
                }
            }
        }
    }

    private static boolean appendParam(boolean z, StringBuilder sb, String str, Object obj, boolean z2) {
        char c2;
        if (z) {
            z = false;
            c2 = '?';
        } else {
            c2 = Typography.amp;
        }
        sb.append(c2);
        sb.append(str);
        String obj2 = obj.toString();
        if (!z2) {
            obj2 = CharEscapers.escapeUriQuery(obj2);
        }
        if (obj2.length() != 0) {
            sb.append('=');
            sb.append(obj2);
        }
        return z;
    }

    private void appendRawPathFromParts(StringBuilder sb) {
        int size = this.pathParts.size();
        for (int i2 = 0; i2 < size; i2++) {
            String str = this.pathParts.get(i2);
            if (i2 != 0) {
                sb.append(JsonPointer.SEPARATOR);
            }
            if (str.length() != 0) {
                if (!this.verbatim) {
                    str = CharEscapers.escapeUriPath(str);
                }
                sb.append(str);
            }
        }
    }

    private static URL parseURL(String str) {
        try {
            return new URL(str);
        } catch (MalformedURLException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public static List<String> toPathParts(String str) {
        return toPathParts(str, false);
    }

    public static List<String> toPathParts(String str, boolean z) {
        if (str == null || str.length() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        boolean z2 = true;
        while (z2) {
            int indexOf = str.indexOf(47, i2);
            boolean z3 = indexOf != -1;
            String substring = z3 ? str.substring(i2, indexOf) : str.substring(i2);
            if (!z) {
                substring = CharEscapers.decodeUriPath(substring);
            }
            arrayList.add(substring);
            i2 = indexOf + 1;
            z2 = z3;
        }
        return arrayList;
    }

    private static URI toURI(String str) {
        try {
            return new URI(str);
        } catch (URISyntaxException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public void appendRawPath(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        List<String> pathParts = toPathParts(str, this.verbatim);
        List<String> list = this.pathParts;
        if (list == null || list.isEmpty()) {
            this.pathParts = pathParts;
            return;
        }
        int size = this.pathParts.size();
        List<String> list2 = this.pathParts;
        int i2 = size - 1;
        list2.set(i2, this.pathParts.get(i2) + pathParts.get(0));
        this.pathParts.addAll(pathParts.subList(1, pathParts.size()));
    }

    public final String build() {
        return buildAuthority() + buildRelativeUrl();
    }

    public final String buildAuthority() {
        StringBuilder sb = new StringBuilder();
        sb.append((String) Preconditions.checkNotNull(this.scheme));
        sb.append("://");
        String str = this.userInfo;
        if (str != null) {
            if (!this.verbatim) {
                str = CharEscapers.escapeUriUserInfo(str);
            }
            sb.append(str);
            sb.append('@');
        }
        sb.append((String) Preconditions.checkNotNull(this.host));
        int i2 = this.port;
        if (i2 != -1) {
            sb.append(AbstractJsonLexerKt.COLON);
            sb.append(i2);
        }
        return sb.toString();
    }

    public final String buildRelativeUrl() {
        StringBuilder sb = new StringBuilder();
        if (this.pathParts != null) {
            appendRawPathFromParts(sb);
        }
        a(entrySet(), sb, this.verbatim);
        String str = this.fragment;
        if (str != null) {
            sb.append('#');
            if (!this.verbatim) {
                str = URI_FRAGMENT_ESCAPER.escape(str);
            }
            sb.append(str);
        }
        return sb.toString();
    }

    @Override // com.google.api.client.util.GenericData, java.util.AbstractMap
    public GenericUrl clone() {
        GenericUrl genericUrl = (GenericUrl) super.clone();
        if (this.pathParts != null) {
            genericUrl.pathParts = new ArrayList(this.pathParts);
        }
        return genericUrl;
    }

    @Override // com.google.api.client.util.GenericData, java.util.AbstractMap, java.util.Map
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (super.equals(obj) && (obj instanceof GenericUrl)) {
            return build().equals(((GenericUrl) obj).build());
        }
        return false;
    }

    public Collection<Object> getAll(String str) {
        Object obj = get(str);
        return obj == null ? Collections.emptySet() : obj instanceof Collection ? Collections.unmodifiableCollection((Collection) obj) : Collections.singleton(obj);
    }

    public Object getFirst(String str) {
        Object obj = get(str);
        if (obj instanceof Collection) {
            Iterator it = ((Collection) obj).iterator();
            if (it.hasNext()) {
                return it.next();
            }
            return null;
        }
        return obj;
    }

    public String getFragment() {
        return this.fragment;
    }

    public String getHost() {
        return this.host;
    }

    public List<String> getPathParts() {
        return this.pathParts;
    }

    public int getPort() {
        return this.port;
    }

    public String getRawPath() {
        if (this.pathParts == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        appendRawPathFromParts(sb);
        return sb.toString();
    }

    public final String getScheme() {
        return this.scheme;
    }

    public final String getUserInfo() {
        return this.userInfo;
    }

    @Override // com.google.api.client.util.GenericData, java.util.AbstractMap, java.util.Map
    public int hashCode() {
        return build().hashCode();
    }

    @Override // com.google.api.client.util.GenericData
    public GenericUrl set(String str, Object obj) {
        return (GenericUrl) super.set(str, obj);
    }

    public final void setFragment(String str) {
        this.fragment = str;
    }

    public final void setHost(String str) {
        this.host = (String) Preconditions.checkNotNull(str);
    }

    public void setPathParts(List<String> list) {
        this.pathParts = list;
    }

    public final void setPort(int i2) {
        Preconditions.checkArgument(i2 >= -1, "expected port >= -1");
        this.port = i2;
    }

    public void setRawPath(String str) {
        this.pathParts = toPathParts(str, this.verbatim);
    }

    public final void setScheme(String str) {
        this.scheme = (String) Preconditions.checkNotNull(str);
    }

    public final void setUserInfo(String str) {
        this.userInfo = str;
    }

    @Override // com.google.api.client.util.GenericData, java.util.AbstractMap
    public String toString() {
        return build();
    }

    public final URI toURI() {
        return toURI(build());
    }

    public final URL toURL() {
        return parseURL(build());
    }

    public final URL toURL(String str) {
        try {
            return new URL(toURL(), str);
        } catch (MalformedURLException e2) {
            throw new IllegalArgumentException(e2);
        }
    }
}
