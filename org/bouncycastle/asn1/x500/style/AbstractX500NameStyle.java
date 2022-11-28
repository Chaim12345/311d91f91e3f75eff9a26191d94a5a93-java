package org.bouncycastle.asn1.x500.style;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1ParsingException;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.x500.AttributeTypeAndValue;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameStyle;
/* loaded from: classes3.dex */
public abstract class AbstractX500NameStyle implements X500NameStyle {
    private int calcHashCode(ASN1Encodable aSN1Encodable) {
        return IETFUtils.canonicalString(aSN1Encodable).hashCode();
    }

    public static Hashtable copyHashTable(Hashtable hashtable) {
        Hashtable hashtable2 = new Hashtable();
        Enumeration keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            Object nextElement = keys.nextElement();
            hashtable2.put(nextElement, hashtable.get(nextElement));
        }
        return hashtable2;
    }

    private boolean foundMatch(boolean z, RDN rdn, RDN[] rdnArr) {
        if (z) {
            for (int length = rdnArr.length - 1; length >= 0; length--) {
                if (rdnArr[length] != null && b(rdn, rdnArr[length])) {
                    rdnArr[length] = null;
                    return true;
                }
            }
        } else {
            for (int i2 = 0; i2 != rdnArr.length; i2++) {
                if (rdnArr[i2] != null && b(rdn, rdnArr[i2])) {
                    rdnArr[i2] = null;
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ASN1Encodable a(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        return new DERUTF8String(str);
    }

    @Override // org.bouncycastle.asn1.x500.X500NameStyle
    public boolean areEqual(X500Name x500Name, X500Name x500Name2) {
        RDN[] rDNs = x500Name.getRDNs();
        RDN[] rDNs2 = x500Name2.getRDNs();
        if (rDNs.length != rDNs2.length) {
            return false;
        }
        boolean z = (rDNs[0].getFirst() == null || rDNs2[0].getFirst() == null) ? false : !rDNs[0].getFirst().getType().equals((ASN1Primitive) rDNs2[0].getFirst().getType());
        for (int i2 = 0; i2 != rDNs.length; i2++) {
            if (!foundMatch(z, rDNs[i2], rDNs2)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean b(RDN rdn, RDN rdn2) {
        return IETFUtils.rDNAreEqual(rdn, rdn2);
    }

    @Override // org.bouncycastle.asn1.x500.X500NameStyle
    public int calculateHashCode(X500Name x500Name) {
        RDN[] rDNs = x500Name.getRDNs();
        int i2 = 0;
        for (int i3 = 0; i3 != rDNs.length; i3++) {
            if (rDNs[i3].isMultiValued()) {
                AttributeTypeAndValue[] typesAndValues = rDNs[i3].getTypesAndValues();
                for (int i4 = 0; i4 != typesAndValues.length; i4++) {
                    i2 = (i2 ^ typesAndValues[i4].getType().hashCode()) ^ calcHashCode(typesAndValues[i4].getValue());
                }
            } else {
                i2 = (i2 ^ rDNs[i3].getFirst().getType().hashCode()) ^ calcHashCode(rDNs[i3].getFirst().getValue());
            }
        }
        return i2;
    }

    @Override // org.bouncycastle.asn1.x500.X500NameStyle
    public ASN1Encodable stringToValue(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        if (str.length() == 0 || str.charAt(0) != '#') {
            if (str.length() != 0 && str.charAt(0) == '\\') {
                str = str.substring(1);
            }
            return a(aSN1ObjectIdentifier, str);
        }
        try {
            return IETFUtils.valueFromHexString(str, 1);
        } catch (IOException unused) {
            throw new ASN1ParsingException("can't recode value for oid " + aSN1ObjectIdentifier.getId());
        }
    }
}
