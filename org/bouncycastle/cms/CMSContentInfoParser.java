package org.bouncycastle.cms;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.ASN1StreamParser;
import org.bouncycastle.asn1.cms.ContentInfoParser;
/* loaded from: classes3.dex */
public class CMSContentInfoParser {

    /* renamed from: a  reason: collision with root package name */
    protected ContentInfoParser f13121a;

    /* renamed from: b  reason: collision with root package name */
    protected InputStream f13122b;

    /* JADX INFO: Access modifiers changed from: protected */
    public CMSContentInfoParser(InputStream inputStream) {
        this.f13122b = inputStream;
        try {
            ASN1SequenceParser aSN1SequenceParser = (ASN1SequenceParser) new ASN1StreamParser(inputStream).readObject();
            if (aSN1SequenceParser == null) {
                throw new CMSException("No content found.");
            }
            this.f13121a = new ContentInfoParser(aSN1SequenceParser);
        } catch (IOException e2) {
            throw new CMSException("IOException reading content.", e2);
        } catch (ClassCastException e3) {
            throw new CMSException("Unexpected object reading content.", e3);
        }
    }

    public void close() {
        this.f13122b.close();
    }
}
