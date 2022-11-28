package org.apache.http.client.utils;

import com.fasterxml.jackson.core.JsonPointer;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.conn.util.InetAddressUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.TextUtils;
/* loaded from: classes3.dex */
public class URIBuilder {
    private Charset charset;
    private String encodedAuthority;
    private String encodedFragment;
    private String encodedPath;
    private String encodedQuery;
    private String encodedSchemeSpecificPart;
    private String encodedUserInfo;
    private String fragment;
    private String host;
    private List<String> pathSegments;
    private int port;
    private String query;
    private List<NameValuePair> queryParams;
    private String scheme;
    private String userInfo;

    public URIBuilder() {
        this.port = -1;
    }

    public URIBuilder(String str) {
        this(new URI(str), (Charset) null);
    }

    public URIBuilder(String str, Charset charset) {
        this(new URI(str), charset);
    }

    public URIBuilder(URI uri) {
        this(uri, (Charset) null);
    }

    public URIBuilder(URI uri, Charset charset) {
        setCharset(charset);
        digestURI(uri);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00cb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private String buildString() {
        String str;
        String encodePath;
        String encodeUric;
        StringBuilder sb = new StringBuilder();
        String str2 = this.scheme;
        if (str2 != null) {
            sb.append(str2);
            sb.append(AbstractJsonLexerKt.COLON);
        }
        String str3 = this.encodedSchemeSpecificPart;
        if (str3 == null) {
            if (this.encodedAuthority != null) {
                sb.append("//");
                sb.append(this.encodedAuthority);
            } else if (this.host != null) {
                sb.append("//");
                String str4 = this.encodedUserInfo;
                if (str4 == null) {
                    String str5 = this.userInfo;
                    if (str5 != null) {
                        str4 = encodeUserInfo(str5);
                    }
                    if (InetAddressUtils.isIPv6Address(this.host)) {
                        str = this.host;
                    } else {
                        sb.append("[");
                        sb.append(this.host);
                        str = "]";
                    }
                    sb.append(str);
                    if (this.port >= 0) {
                        sb.append(":");
                        sb.append(this.port);
                    }
                }
                sb.append(str4);
                sb.append("@");
                if (InetAddressUtils.isIPv6Address(this.host)) {
                }
                sb.append(str);
                if (this.port >= 0) {
                }
            }
            String str6 = this.encodedPath;
            if (str6 != null) {
                encodePath = normalizePath(str6, sb.length() == 0);
            } else {
                List<String> list = this.pathSegments;
                if (list != null) {
                    encodePath = encodePath(list);
                }
                if (this.encodedQuery == null) {
                    sb.append("?");
                    str3 = this.encodedQuery;
                } else {
                    List<NameValuePair> list2 = this.queryParams;
                    if (list2 == null || list2.isEmpty()) {
                        if (this.query != null) {
                            sb.append("?");
                            str3 = encodeUric(this.query);
                        }
                        if (this.encodedFragment != null) {
                            if (this.fragment != null) {
                                sb.append("#");
                                encodeUric = encodeUric(this.fragment);
                            }
                            return sb.toString();
                        }
                        sb.append("#");
                        encodeUric = this.encodedFragment;
                        sb.append(encodeUric);
                        return sb.toString();
                    }
                    sb.append("?");
                    str3 = encodeUrlForm(this.queryParams);
                }
            }
            sb.append(encodePath);
            if (this.encodedQuery == null) {
            }
        }
        sb.append(str3);
        if (this.encodedFragment != null) {
        }
        sb.append(encodeUric);
        return sb.toString();
    }

    private void digestURI(URI uri) {
        this.scheme = uri.getScheme();
        this.encodedSchemeSpecificPart = uri.getRawSchemeSpecificPart();
        this.encodedAuthority = uri.getRawAuthority();
        this.host = uri.getHost();
        this.port = uri.getPort();
        this.encodedUserInfo = uri.getRawUserInfo();
        this.userInfo = uri.getUserInfo();
        this.encodedPath = uri.getRawPath();
        String rawPath = uri.getRawPath();
        Charset charset = this.charset;
        if (charset == null) {
            charset = Consts.UTF_8;
        }
        this.pathSegments = parsePath(rawPath, charset);
        this.encodedQuery = uri.getRawQuery();
        String rawQuery = uri.getRawQuery();
        Charset charset2 = this.charset;
        if (charset2 == null) {
            charset2 = Consts.UTF_8;
        }
        this.queryParams = parseQuery(rawQuery, charset2);
        this.encodedFragment = uri.getRawFragment();
        this.fragment = uri.getFragment();
    }

    private String encodePath(List<String> list) {
        Charset charset = this.charset;
        if (charset == null) {
            charset = Consts.UTF_8;
        }
        return URLEncodedUtils.formatSegments(list, charset);
    }

    private String encodeUric(String str) {
        Charset charset = this.charset;
        if (charset == null) {
            charset = Consts.UTF_8;
        }
        return URLEncodedUtils.encUric(str, charset);
    }

    private String encodeUrlForm(List<NameValuePair> list) {
        Charset charset = this.charset;
        if (charset == null) {
            charset = Consts.UTF_8;
        }
        return URLEncodedUtils.format(list, charset);
    }

    private String encodeUserInfo(String str) {
        Charset charset = this.charset;
        if (charset == null) {
            charset = Consts.UTF_8;
        }
        return URLEncodedUtils.encUserInfo(str, charset);
    }

    private static String normalizePath(String str, boolean z) {
        if (TextUtils.isBlank(str)) {
            return "";
        }
        if (z || str.startsWith("/")) {
            return str;
        }
        return "/" + str;
    }

    private List<String> parsePath(String str, Charset charset) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        return URLEncodedUtils.parsePathSegments(str, charset);
    }

    private List<NameValuePair> parseQuery(String str, Charset charset) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        return URLEncodedUtils.parse(str, charset);
    }

    public URIBuilder addParameter(String str, String str2) {
        if (this.queryParams == null) {
            this.queryParams = new ArrayList();
        }
        this.queryParams.add(new BasicNameValuePair(str, str2));
        this.encodedQuery = null;
        this.encodedSchemeSpecificPart = null;
        this.query = null;
        return this;
    }

    public URIBuilder addParameters(List<NameValuePair> list) {
        if (this.queryParams == null) {
            this.queryParams = new ArrayList();
        }
        this.queryParams.addAll(list);
        this.encodedQuery = null;
        this.encodedSchemeSpecificPart = null;
        this.query = null;
        return this;
    }

    public URI build() {
        return new URI(buildString());
    }

    public URIBuilder clearParameters() {
        this.queryParams = null;
        this.encodedQuery = null;
        this.encodedSchemeSpecificPart = null;
        return this;
    }

    public Charset getCharset() {
        return this.charset;
    }

    public String getFragment() {
        return this.fragment;
    }

    public String getHost() {
        return this.host;
    }

    public String getPath() {
        if (this.pathSegments == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String str : this.pathSegments) {
            sb.append(JsonPointer.SEPARATOR);
            sb.append(str);
        }
        return sb.toString();
    }

    public List<String> getPathSegments() {
        return this.pathSegments != null ? new ArrayList(this.pathSegments) : Collections.emptyList();
    }

    public int getPort() {
        return this.port;
    }

    public List<NameValuePair> getQueryParams() {
        return this.queryParams != null ? new ArrayList(this.queryParams) : Collections.emptyList();
    }

    public String getScheme() {
        return this.scheme;
    }

    public String getUserInfo() {
        return this.userInfo;
    }

    public boolean isAbsolute() {
        return this.scheme != null;
    }

    public boolean isOpaque() {
        return this.pathSegments == null && this.encodedPath == null;
    }

    public boolean isPathEmpty() {
        String str;
        List<String> list = this.pathSegments;
        return (list == null || list.isEmpty()) && ((str = this.encodedPath) == null || str.isEmpty());
    }

    public boolean isQueryEmpty() {
        List<NameValuePair> list = this.queryParams;
        return (list == null || list.isEmpty()) && this.encodedQuery == null;
    }

    public URIBuilder removeQuery() {
        this.queryParams = null;
        this.query = null;
        this.encodedQuery = null;
        this.encodedSchemeSpecificPart = null;
        return this;
    }

    public URIBuilder setCharset(Charset charset) {
        this.charset = charset;
        return this;
    }

    public URIBuilder setCustomQuery(String str) {
        this.query = str;
        this.encodedQuery = null;
        this.encodedSchemeSpecificPart = null;
        this.queryParams = null;
        return this;
    }

    public URIBuilder setFragment(String str) {
        this.fragment = str;
        this.encodedFragment = null;
        return this;
    }

    public URIBuilder setHost(String str) {
        this.host = str;
        this.encodedSchemeSpecificPart = null;
        this.encodedAuthority = null;
        return this;
    }

    public URIBuilder setParameter(String str, String str2) {
        if (this.queryParams == null) {
            this.queryParams = new ArrayList();
        }
        if (!this.queryParams.isEmpty()) {
            Iterator<NameValuePair> it = this.queryParams.iterator();
            while (it.hasNext()) {
                if (it.next().getName().equals(str)) {
                    it.remove();
                }
            }
        }
        this.queryParams.add(new BasicNameValuePair(str, str2));
        this.encodedQuery = null;
        this.encodedSchemeSpecificPart = null;
        this.query = null;
        return this;
    }

    public URIBuilder setParameters(List<NameValuePair> list) {
        List<NameValuePair> list2 = this.queryParams;
        if (list2 == null) {
            this.queryParams = new ArrayList();
        } else {
            list2.clear();
        }
        this.queryParams.addAll(list);
        this.encodedQuery = null;
        this.encodedSchemeSpecificPart = null;
        this.query = null;
        return this;
    }

    public URIBuilder setParameters(NameValuePair... nameValuePairArr) {
        List<NameValuePair> list = this.queryParams;
        if (list == null) {
            this.queryParams = new ArrayList();
        } else {
            list.clear();
        }
        for (NameValuePair nameValuePair : nameValuePairArr) {
            this.queryParams.add(nameValuePair);
        }
        this.encodedQuery = null;
        this.encodedSchemeSpecificPart = null;
        this.query = null;
        return this;
    }

    public URIBuilder setPath(String str) {
        return setPathSegments(str != null ? URLEncodedUtils.splitPathSegments(str) : null);
    }

    public URIBuilder setPathSegments(List<String> list) {
        this.pathSegments = (list == null || list.size() <= 0) ? null : new ArrayList(list);
        this.encodedSchemeSpecificPart = null;
        this.encodedPath = null;
        return this;
    }

    public URIBuilder setPathSegments(String... strArr) {
        this.pathSegments = strArr.length > 0 ? Arrays.asList(strArr) : null;
        this.encodedSchemeSpecificPart = null;
        this.encodedPath = null;
        return this;
    }

    public URIBuilder setPort(int i2) {
        if (i2 < 0) {
            i2 = -1;
        }
        this.port = i2;
        this.encodedSchemeSpecificPart = null;
        this.encodedAuthority = null;
        return this;
    }

    @Deprecated
    public URIBuilder setQuery(String str) {
        Charset charset = this.charset;
        if (charset == null) {
            charset = Consts.UTF_8;
        }
        this.queryParams = parseQuery(str, charset);
        this.query = null;
        this.encodedQuery = null;
        this.encodedSchemeSpecificPart = null;
        return this;
    }

    public URIBuilder setScheme(String str) {
        this.scheme = str;
        return this;
    }

    public URIBuilder setUserInfo(String str) {
        this.userInfo = str;
        this.encodedSchemeSpecificPart = null;
        this.encodedAuthority = null;
        this.encodedUserInfo = null;
        return this;
    }

    public URIBuilder setUserInfo(String str, String str2) {
        return setUserInfo(str + AbstractJsonLexerKt.COLON + str2);
    }

    public String toString() {
        return buildString();
    }
}
