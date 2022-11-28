package org.bouncycastle.voms;

import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.asn1.ASN1IA5String;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.x509.Attribute;
import org.bouncycastle.asn1.x509.IetfAttrSyntax;
import org.bouncycastle.cert.X509AttributeCertificateHolder;
/* loaded from: classes4.dex */
public class VOMSAttribute {
    public static final String VOMS_ATTR_OID = "1.3.6.1.4.1.8005.100.100.4";
    private X509AttributeCertificateHolder myAC;
    private String myHostPort;
    private String myVo;
    private List myStringList = new ArrayList();
    private List myFQANs = new ArrayList();

    /* loaded from: classes4.dex */
    public class FQAN {

        /* renamed from: a  reason: collision with root package name */
        String f15118a;

        /* renamed from: b  reason: collision with root package name */
        String f15119b;

        /* renamed from: c  reason: collision with root package name */
        String f15120c;

        /* renamed from: d  reason: collision with root package name */
        String f15121d;

        public FQAN(VOMSAttribute vOMSAttribute, String str) {
            this.f15118a = str;
        }

        public FQAN(VOMSAttribute vOMSAttribute, String str, String str2, String str3) {
            this.f15119b = str;
            this.f15120c = str2;
            this.f15121d = str3;
        }

        protected void a() {
            this.f15118a.length();
            int indexOf = this.f15118a.indexOf("/Role=");
            if (indexOf < 0) {
                return;
            }
            this.f15119b = this.f15118a.substring(0, indexOf);
            int i2 = indexOf + 6;
            int indexOf2 = this.f15118a.indexOf("/Capability=", i2);
            String str = this.f15118a;
            String substring = indexOf2 < 0 ? str.substring(i2) : str.substring(i2, indexOf2);
            String str2 = null;
            if (substring.length() == 0) {
                substring = null;
            }
            this.f15120c = substring;
            String substring2 = indexOf2 < 0 ? null : this.f15118a.substring(indexOf2 + 12);
            if (substring2 != null && substring2.length() != 0) {
                str2 = substring2;
            }
            this.f15121d = str2;
        }

        public String getCapability() {
            if (this.f15119b == null && this.f15118a != null) {
                a();
            }
            return this.f15121d;
        }

        public String getFQAN() {
            String str = this.f15118a;
            if (str != null) {
                return str;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(this.f15119b);
            sb.append("/Role=");
            String str2 = this.f15120c;
            String str3 = "";
            if (str2 == null) {
                str2 = "";
            }
            sb.append(str2);
            if (this.f15121d != null) {
                str3 = "/Capability=" + this.f15121d;
            }
            sb.append(str3);
            String sb2 = sb.toString();
            this.f15118a = sb2;
            return sb2;
        }

        public String getGroup() {
            if (this.f15119b == null && this.f15118a != null) {
                a();
            }
            return this.f15119b;
        }

        public String getRole() {
            if (this.f15119b == null && this.f15118a != null) {
                a();
            }
            return this.f15120c;
        }

        public String toString() {
            return getFQAN();
        }
    }

    public VOMSAttribute(X509AttributeCertificateHolder x509AttributeCertificateHolder) {
        if (x509AttributeCertificateHolder == null) {
            throw new IllegalArgumentException("VOMSAttribute: AttributeCertificate is NULL");
        }
        this.myAC = x509AttributeCertificateHolder;
        Attribute[] attributes = x509AttributeCertificateHolder.getAttributes(new ASN1ObjectIdentifier(VOMS_ATTR_OID));
        if (attributes == null) {
            return;
        }
        for (int i2 = 0; i2 != attributes.length; i2++) {
            try {
                IetfAttrSyntax ietfAttrSyntax = IetfAttrSyntax.getInstance(attributes[i2].getAttributeValues()[0]);
                String string = ((ASN1IA5String) ietfAttrSyntax.getPolicyAuthority().getNames()[0].getName()).getString();
                int indexOf = string.indexOf("://");
                if (indexOf < 0 || indexOf == string.length() - 1) {
                    throw new IllegalArgumentException("Bad encoding of VOMS policyAuthority : [" + string + "]");
                }
                this.myVo = string.substring(0, indexOf);
                this.myHostPort = string.substring(indexOf + 3);
                if (ietfAttrSyntax.getValueType() != 1) {
                    throw new IllegalArgumentException("VOMS attribute values are not encoded as octet strings, policyAuthority = " + string);
                }
                ASN1OctetString[] aSN1OctetStringArr = (ASN1OctetString[]) ietfAttrSyntax.getValues();
                for (int i3 = 0; i3 != aSN1OctetStringArr.length; i3++) {
                    String str = new String(aSN1OctetStringArr[i3].getOctets());
                    FQAN fqan = new FQAN(this, str);
                    if (!this.myStringList.contains(str)) {
                        if (str.startsWith("/" + this.myVo + "/")) {
                            this.myStringList.add(str);
                            this.myFQANs.add(fqan);
                        }
                    }
                }
            } catch (IllegalArgumentException e2) {
                throw e2;
            } catch (Exception unused) {
                throw new IllegalArgumentException("Badly encoded VOMS extension in AC issued by " + x509AttributeCertificateHolder.getIssuer());
            }
        }
    }

    public X509AttributeCertificateHolder getAC() {
        return this.myAC;
    }

    public List getFullyQualifiedAttributes() {
        return this.myStringList;
    }

    public String getHostPort() {
        return this.myHostPort;
    }

    public List getListOfFQAN() {
        return this.myFQANs;
    }

    public String getVO() {
        return this.myVo;
    }

    public String toString() {
        return "VO      :" + this.myVo + "\nHostPort:" + this.myHostPort + "\nFQANs   :" + this.myFQANs;
    }
}
