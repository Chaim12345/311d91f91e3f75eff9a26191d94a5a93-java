package org.bouncycastle.cms;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
/* loaded from: classes3.dex */
public interface CMSTypedData extends CMSProcessable {
    ASN1ObjectIdentifier getContentType();
}
