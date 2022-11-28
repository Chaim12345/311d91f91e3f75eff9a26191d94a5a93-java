package org.bouncycastle.asn1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ASN1UniversalTypes {
    private ASN1UniversalTypes() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1UniversalType a(int i2) {
        switch (i2) {
            case 1:
                return ASN1Boolean.f12678a;
            case 2:
                return ASN1Integer.f12696a;
            case 3:
                return ASN1BitString.f12676b;
            case 4:
                return ASN1OctetString.f12702b;
            case 5:
                return ASN1Null.f12697a;
            case 6:
                return ASN1ObjectIdentifier.f12701a;
            case 7:
                return ASN1ObjectDescriptor.f12700a;
            case 8:
                return ASN1External.f12681f;
            case 9:
            case 11:
            case 14:
            case 15:
            case 29:
            default:
                return null;
            case 10:
                return ASN1Enumerated.f12680a;
            case 12:
                return ASN1UTF8String.f12728b;
            case 13:
                return ASN1RelativeOID.f12707a;
            case 16:
                return ASN1Sequence.f12708b;
            case 17:
                return ASN1Set.f12713c;
            case 18:
                return ASN1NumericString.f12698b;
            case 19:
                return ASN1PrintableString.f12705b;
            case 20:
                return ASN1T61String.f12719b;
            case 21:
                return ASN1VideotexString.f12732b;
            case 22:
                return ASN1IA5String.f12694b;
            case 23:
                return ASN1UTCTime.f12726b;
            case 24:
                return ASN1GeneralizedTime.f12689b;
            case 25:
                return ASN1GraphicString.f12692b;
            case 26:
                return ASN1VisibleString.f12734b;
            case 27:
                return ASN1GeneralString.f12687b;
            case 28:
                return ASN1UniversalString.f12730b;
            case 30:
                return ASN1BMPString.f12674b;
        }
    }
}
