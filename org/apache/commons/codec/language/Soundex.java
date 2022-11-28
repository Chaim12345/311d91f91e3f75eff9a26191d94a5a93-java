package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
/* loaded from: classes3.dex */
public class Soundex implements StringEncoder {
    public static final char SILENT_MARKER = '-';
    @Deprecated
    private int maxLength;
    private final char[] soundexMapping;
    private final boolean specialCaseHW;
    public static final String US_ENGLISH_MAPPING_STRING = "01230120022455012623010202";
    private static final char[] US_ENGLISH_MAPPING = US_ENGLISH_MAPPING_STRING.toCharArray();
    public static final Soundex US_ENGLISH = new Soundex();
    public static final Soundex US_ENGLISH_SIMPLIFIED = new Soundex(US_ENGLISH_MAPPING_STRING, false);
    public static final Soundex US_ENGLISH_GENEALOGY = new Soundex("-123-12--22455-12623-1-2-2");

    public Soundex() {
        this.maxLength = 4;
        this.soundexMapping = US_ENGLISH_MAPPING;
        this.specialCaseHW = true;
    }

    public Soundex(String str) {
        this.maxLength = 4;
        char[] charArray = str.toCharArray();
        this.soundexMapping = charArray;
        this.specialCaseHW = !hasMarker(charArray);
    }

    public Soundex(String str, boolean z) {
        this.maxLength = 4;
        this.soundexMapping = str.toCharArray();
        this.specialCaseHW = z;
    }

    public Soundex(char[] cArr) {
        this.maxLength = 4;
        char[] cArr2 = new char[cArr.length];
        this.soundexMapping = cArr2;
        System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
        this.specialCaseHW = !hasMarker(cArr2);
    }

    private boolean hasMarker(char[] cArr) {
        for (char c2 : cArr) {
            if (c2 == '-') {
                return true;
            }
        }
        return false;
    }

    private char map(char c2) {
        int i2 = c2 - 'A';
        if (i2 >= 0) {
            char[] cArr = this.soundexMapping;
            if (i2 < cArr.length) {
                return cArr[i2];
            }
        }
        throw new IllegalArgumentException("The character is not mapped: " + c2 + " (index=" + i2 + ")");
    }

    public int difference(String str, String str2) {
        return SoundexUtils.difference(this, str, str2);
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) {
        if (obj instanceof String) {
            return soundex((String) obj);
        }
        throw new EncoderException("Parameter supplied to Soundex encode is not of type java.lang.String");
    }

    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String str) {
        return soundex(str);
    }

    @Deprecated
    public int getMaxLength() {
        return this.maxLength;
    }

    @Deprecated
    public void setMaxLength(int i2) {
        this.maxLength = i2;
    }

    public String soundex(String str) {
        char map;
        if (str == null) {
            return null;
        }
        String clean = SoundexUtils.clean(str);
        if (clean.length() == 0) {
            return clean;
        }
        char[] cArr = {'0', '0', '0', '0'};
        char charAt = clean.charAt(0);
        cArr[0] = charAt;
        char map2 = map(charAt);
        int i2 = 1;
        for (int i3 = 1; i3 < clean.length() && i2 < 4; i3++) {
            char charAt2 = clean.charAt(i3);
            if ((!this.specialCaseHW || (charAt2 != 'H' && charAt2 != 'W')) && (map = map(charAt2)) != '-') {
                if (map != '0' && map != map2) {
                    cArr[i2] = map;
                    i2++;
                }
                map2 = map;
            }
        }
        return new String(cArr);
    }
}
