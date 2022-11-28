package org.bouncycastle.mime;

import java.io.InputStream;
/* loaded from: classes4.dex */
public interface MimeContext {
    InputStream applyContext(Headers headers, InputStream inputStream);
}
