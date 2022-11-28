package org.bouncycastle.asn1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import org.apache.commons.cli.HelpFormatter;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;
import org.slf4j.Marker;
/* loaded from: classes3.dex */
public class ASN1GeneralizedTime extends ASN1Primitive {

    /* renamed from: b  reason: collision with root package name */
    static final ASN1UniversalType f12689b = new ASN1UniversalType(ASN1GeneralizedTime.class, 24) { // from class: org.bouncycastle.asn1.ASN1GeneralizedTime.1
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.bouncycastle.asn1.ASN1UniversalType
        public ASN1Primitive d(DEROctetString dEROctetString) {
            return ASN1GeneralizedTime.h(dEROctetString.getOctets());
        }
    };

    /* renamed from: a  reason: collision with root package name */
    final byte[] f12690a;

    public ASN1GeneralizedTime(String str) {
        this.f12690a = Strings.toByteArray(str);
        try {
            getDate();
        } catch (ParseException e2) {
            throw new IllegalArgumentException("invalid date string: " + e2.getMessage());
        }
    }

    public ASN1GeneralizedTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'", DateUtil.f12748a);
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.f12690a = Strings.toByteArray(simpleDateFormat.format(date));
    }

    public ASN1GeneralizedTime(Date date, Locale locale) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'", locale);
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.f12690a = Strings.toByteArray(simpleDateFormat.format(date));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1GeneralizedTime(byte[] bArr) {
        if (bArr.length < 4) {
            throw new IllegalArgumentException("GeneralizedTime string too short");
        }
        this.f12690a = bArr;
        if (!isDigit(0) || !isDigit(1) || !isDigit(2) || !isDigit(3)) {
            throw new IllegalArgumentException("illegal characters in GeneralizedTime string");
        }
    }

    private SimpleDateFormat calculateGMTDateFormat() {
        SimpleDateFormat simpleDateFormat = i() ? new SimpleDateFormat("yyyyMMddHHmmss.SSSz") : k() ? new SimpleDateFormat("yyyyMMddHHmmssz") : j() ? new SimpleDateFormat("yyyyMMddHHmmz") : new SimpleDateFormat("yyyyMMddHHz");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        return simpleDateFormat;
    }

    private String calculateGMTOffset(String str) {
        String str2;
        TimeZone timeZone = TimeZone.getDefault();
        int rawOffset = timeZone.getRawOffset();
        if (rawOffset < 0) {
            rawOffset = -rawOffset;
            str2 = HelpFormatter.DEFAULT_OPT_PREFIX;
        } else {
            str2 = Marker.ANY_NON_NULL_MARKER;
        }
        int i2 = rawOffset / 3600000;
        int i3 = (rawOffset - (((i2 * 60) * 60) * 1000)) / 60000;
        try {
            if (timeZone.useDaylightTime()) {
                if (i()) {
                    str = pruneFractionalSeconds(str);
                }
                SimpleDateFormat calculateGMTDateFormat = calculateGMTDateFormat();
                if (timeZone.inDaylightTime(calculateGMTDateFormat.parse(str + "GMT" + str2 + convert(i2) + ":" + convert(i3)))) {
                    i2 += str2.equals(Marker.ANY_NON_NULL_MARKER) ? 1 : -1;
                }
            }
        } catch (ParseException unused) {
        }
        return "GMT" + str2 + convert(i2) + ":" + convert(i3);
    }

    private String convert(int i2) {
        if (i2 < 10) {
            return "0" + i2;
        }
        return Integer.toString(i2);
    }

    public static ASN1GeneralizedTime getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1GeneralizedTime)) {
            return (ASN1GeneralizedTime) obj;
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
            if (aSN1Primitive instanceof ASN1GeneralizedTime) {
                return (ASN1GeneralizedTime) aSN1Primitive;
            }
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (ASN1GeneralizedTime) f12689b.b((byte[]) obj);
        } catch (Exception e2) {
            throw new IllegalArgumentException("encoding error in getInstance: " + e2.toString());
        }
    }

    public static ASN1GeneralizedTime getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return (ASN1GeneralizedTime) f12689b.e(aSN1TaggedObject, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1GeneralizedTime h(byte[] bArr) {
        return new ASN1GeneralizedTime(bArr);
    }

    private boolean isDigit(int i2) {
        byte[] bArr = this.f12690a;
        return bArr.length > i2 && bArr[i2] >= 48 && bArr[i2] <= 57;
    }

    private String pruneFractionalSeconds(String str) {
        String str2;
        StringBuilder sb;
        char charAt;
        String substring = str.substring(14);
        int i2 = 1;
        while (i2 < substring.length() && '0' <= (charAt = substring.charAt(i2)) && charAt <= '9') {
            i2++;
        }
        int i3 = i2 - 1;
        if (i3 > 3) {
            str2 = substring.substring(0, 4) + substring.substring(i2);
            sb = new StringBuilder();
        } else if (i3 == 1) {
            str2 = substring.substring(0, i2) + "00" + substring.substring(i2);
            sb = new StringBuilder();
        } else if (i3 != 2) {
            return str;
        } else {
            str2 = substring.substring(0, i2) + "0" + substring.substring(i2);
            sb = new StringBuilder();
        }
        sb.append(str.substring(0, 14));
        sb.append(str2);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean b(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof ASN1GeneralizedTime) {
            return Arrays.areEqual(this.f12690a, ((ASN1GeneralizedTime) aSN1Primitive).f12690a);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void c(ASN1OutputStream aSN1OutputStream, boolean z) {
        aSN1OutputStream.m(z, 24, this.f12690a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public final boolean d() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int e(boolean z) {
        return ASN1OutputStream.e(z, this.f12690a.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive f() {
        return new DERGeneralizedTime(this.f12690a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive g() {
        return new DERGeneralizedTime(this.f12690a);
    }

    public Date getDate() {
        SimpleDateFormat calculateGMTDateFormat;
        String fromByteArray = Strings.fromByteArray(this.f12690a);
        if (fromByteArray.endsWith("Z")) {
            calculateGMTDateFormat = i() ? new SimpleDateFormat("yyyyMMddHHmmss.SSS'Z'") : k() ? new SimpleDateFormat("yyyyMMddHHmmss'Z'") : j() ? new SimpleDateFormat("yyyyMMddHHmm'Z'") : new SimpleDateFormat("yyyyMMddHH'Z'");
            calculateGMTDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        } else if (fromByteArray.indexOf(45) > 0 || fromByteArray.indexOf(43) > 0) {
            fromByteArray = getTime();
            calculateGMTDateFormat = calculateGMTDateFormat();
        } else {
            calculateGMTDateFormat = i() ? new SimpleDateFormat("yyyyMMddHHmmss.SSS") : k() ? new SimpleDateFormat("yyyyMMddHHmmss") : j() ? new SimpleDateFormat("yyyyMMddHHmm") : new SimpleDateFormat("yyyyMMddHH");
            calculateGMTDateFormat.setTimeZone(new SimpleTimeZone(0, TimeZone.getDefault().getID()));
        }
        if (i()) {
            fromByteArray = pruneFractionalSeconds(fromByteArray);
        }
        return DateUtil.a(calculateGMTDateFormat.parse(fromByteArray));
    }

    public String getTime() {
        int length;
        String fromByteArray = Strings.fromByteArray(this.f12690a);
        if (fromByteArray.charAt(fromByteArray.length() - 1) == 'Z') {
            return fromByteArray.substring(0, fromByteArray.length() - 1) + "GMT+00:00";
        }
        int length2 = fromByteArray.length() - 6;
        char charAt = fromByteArray.charAt(length2);
        if ((charAt == '-' || charAt == '+') && fromByteArray.indexOf("GMT") == length2 - 3) {
            return fromByteArray;
        }
        int length3 = fromByteArray.length() - 5;
        char charAt2 = fromByteArray.charAt(length3);
        if (charAt2 == '-' || charAt2 == '+') {
            StringBuilder sb = new StringBuilder();
            sb.append(fromByteArray.substring(0, length3));
            sb.append("GMT");
            int i2 = length3 + 3;
            sb.append(fromByteArray.substring(length3, i2));
            sb.append(":");
            sb.append(fromByteArray.substring(i2));
            return sb.toString();
        }
        char charAt3 = fromByteArray.charAt(fromByteArray.length() - 3);
        if (charAt3 != '-' && charAt3 != '+') {
            return fromByteArray + calculateGMTOffset(fromByteArray);
        }
        return fromByteArray.substring(0, length) + "GMT" + fromByteArray.substring(length) + ":00";
    }

    public String getTimeString() {
        return Strings.fromByteArray(this.f12690a);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        return Arrays.hashCode(this.f12690a);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean i() {
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f12690a;
            if (i2 == bArr.length) {
                return false;
            }
            if (bArr[i2] == 46 && i2 == 14) {
                return true;
            }
            i2++;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean j() {
        return isDigit(10) && isDigit(11);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean k() {
        return isDigit(12) && isDigit(13);
    }
}
