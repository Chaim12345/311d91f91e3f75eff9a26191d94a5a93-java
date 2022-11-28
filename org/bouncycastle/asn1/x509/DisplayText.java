package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1BMPString;
import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1IA5String;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1String;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.ASN1UTF8String;
import org.bouncycastle.asn1.ASN1VisibleString;
import org.bouncycastle.asn1.DERBMPString;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.DERVisibleString;
/* loaded from: classes3.dex */
public class DisplayText extends ASN1Object implements ASN1Choice {
    public static final int CONTENT_TYPE_BMPSTRING = 1;
    public static final int CONTENT_TYPE_IA5STRING = 0;
    public static final int CONTENT_TYPE_UTF8STRING = 2;
    public static final int CONTENT_TYPE_VISIBLESTRING = 3;
    public static final int DISPLAY_TEXT_MAXIMUM_SIZE = 200;

    /* renamed from: a  reason: collision with root package name */
    ASN1String f12948a;

    public DisplayText(int i2, String str) {
        str = str.length() > 200 ? str.substring(0, 200) : str;
        this.f12948a = i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? new DERUTF8String(str) : new DERVisibleString(str) : new DERUTF8String(str) : new DERBMPString(str) : new DERIA5String(str);
    }

    public DisplayText(String str) {
        this.f12948a = new DERUTF8String(str.length() > 200 ? str.substring(0, 200) : str);
    }

    private DisplayText(ASN1String aSN1String) {
        this.f12948a = aSN1String;
        if (!(aSN1String instanceof ASN1UTF8String) && !(aSN1String instanceof ASN1BMPString) && !(aSN1String instanceof ASN1IA5String) && !(aSN1String instanceof ASN1VisibleString)) {
            throw new IllegalArgumentException("unknown STRING type in DisplayText");
        }
    }

    public static DisplayText getInstance(Object obj) {
        if (obj instanceof ASN1String) {
            return new DisplayText((ASN1String) obj);
        }
        if (obj == null || (obj instanceof DisplayText)) {
            return (DisplayText) obj;
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    public static DisplayText getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(aSN1TaggedObject.getObject());
    }

    public String getString() {
        return this.f12948a.getString();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return (ASN1Primitive) this.f12948a;
    }
}
