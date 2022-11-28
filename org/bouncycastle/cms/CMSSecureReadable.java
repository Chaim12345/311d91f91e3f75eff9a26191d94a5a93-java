package org.bouncycastle.cms;

import java.io.InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
/* loaded from: classes3.dex */
interface CMSSecureReadable {
    ASN1ObjectIdentifier getContentType();

    InputStream getInputStream();
}
