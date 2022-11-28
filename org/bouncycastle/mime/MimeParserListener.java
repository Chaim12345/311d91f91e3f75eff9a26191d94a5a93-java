package org.bouncycastle.mime;

import java.io.InputStream;
/* loaded from: classes4.dex */
public interface MimeParserListener {
    MimeContext createContext(MimeParserContext mimeParserContext, Headers headers);

    void object(MimeParserContext mimeParserContext, Headers headers, InputStream inputStream);
}
