package org.apache.http.cookie;

import java.util.List;
import org.apache.http.Header;
import org.apache.http.annotation.Obsolete;
/* loaded from: classes3.dex */
public interface CookieSpec {
    List<Header> formatCookies(List<Cookie> list);

    @Obsolete
    int getVersion();

    @Obsolete
    Header getVersionHeader();

    boolean match(Cookie cookie, CookieOrigin cookieOrigin);

    List<Cookie> parse(Header header, CookieOrigin cookieOrigin);

    void validate(Cookie cookie, CookieOrigin cookieOrigin);
}
