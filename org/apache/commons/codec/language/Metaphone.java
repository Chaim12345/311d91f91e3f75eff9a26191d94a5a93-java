package org.apache.commons.codec.language;

import java.util.Locale;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
/* loaded from: classes3.dex */
public class Metaphone implements StringEncoder {
    private static final String FRONTV = "EIY";
    private static final String VARSON = "CSPTG";
    private static final String VOWELS = "AEIOU";
    private int maxCodeLen = 4;

    private boolean isLastChar(int i2, int i3) {
        return i3 + 1 == i2;
    }

    private boolean isNextChar(StringBuilder sb, int i2, char c2) {
        return i2 >= 0 && i2 < sb.length() - 1 && sb.charAt(i2 + 1) == c2;
    }

    private boolean isPreviousChar(StringBuilder sb, int i2, char c2) {
        return i2 > 0 && i2 < sb.length() && sb.charAt(i2 - 1) == c2;
    }

    private boolean isVowel(StringBuilder sb, int i2) {
        return VOWELS.indexOf(sb.charAt(i2)) >= 0;
    }

    private boolean regionMatch(StringBuilder sb, int i2, String str) {
        if (i2 < 0 || (str.length() + i2) - 1 >= sb.length()) {
            return false;
        }
        return sb.substring(i2, str.length() + i2).equals(str);
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) {
        if (obj instanceof String) {
            return metaphone((String) obj);
        }
        throw new EncoderException("Parameter supplied to Metaphone encode is not of type java.lang.String");
    }

    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String str) {
        return metaphone(str);
    }

    public int getMaxCodeLen() {
        return this.maxCodeLen;
    }

    public boolean isMetaphoneEqual(String str, String str2) {
        return metaphone(str).equals(metaphone(str2));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x020c, code lost:
        if (isNextChar(r2, r5, 'H') != false) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x0220, code lost:
        if (isVowel(r2, 2) != false) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x0230, code lost:
        if (isLastChar(r1, r5) != false) goto L134;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x0233, code lost:
        if (r5 == 0) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x006e, code lost:
        if (r1[1] == 'N') goto L144;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0075, code lost:
        if (r1[1] == 'E') goto L144;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00bf, code lost:
        if (isVowel(r2, r5 + 1) != false) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0108, code lost:
        if (regionMatch(r2, r5, "SIA") == false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0118, code lost:
        if (isNextChar(r2, r5, 'H') != false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0121, code lost:
        if (isPreviousChar(r2, r5, 'C') != false) goto L134;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public String metaphone(String str) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return "";
        }
        if (length == 1) {
            return str.toUpperCase(Locale.ENGLISH);
        }
        char[] charArray = str.toUpperCase(Locale.ENGLISH).toCharArray();
        StringBuilder sb = new StringBuilder(40);
        StringBuilder sb2 = new StringBuilder(10);
        int i2 = 0;
        char c2 = charArray[0];
        if (c2 != 'A') {
            if (c2 != 'G' && c2 != 'K' && c2 != 'P') {
                if (c2 == 'W') {
                    if (charArray[1] != 'R') {
                        if (charArray[1] == 'H') {
                            sb.append(charArray, 1, charArray.length - 1);
                            sb.setCharAt(0, 'W');
                        }
                    }
                    sb.append(charArray, 1, charArray.length - 1);
                } else if (c2 == 'X') {
                    charArray[0] = 'S';
                }
                sb.append(charArray);
            }
        }
        int length2 = sb.length();
        while (sb2.length() < getMaxCodeLen() && i2 < length2) {
            char charAt = sb.charAt(i2);
            if (charAt == 'C' || !isPreviousChar(sb, i2, charAt)) {
                switch (charAt) {
                    case 'B':
                        if (isPreviousChar(sb, i2, 'M')) {
                            break;
                        }
                        sb2.append(charAt);
                        break;
                    case 'C':
                        if (!isPreviousChar(sb, i2, 'S') || isLastChar(length2, i2) || FRONTV.indexOf(sb.charAt(i2 + 1)) < 0) {
                            if (!regionMatch(sb, i2, "CIA")) {
                                if (isLastChar(length2, i2) || FRONTV.indexOf(sb.charAt(i2 + 1)) < 0) {
                                    if (isPreviousChar(sb, i2, 'S')) {
                                        break;
                                    }
                                    if (isNextChar(sb, i2, 'H')) {
                                        if (i2 == 0) {
                                            if (length2 >= 3) {
                                                break;
                                            }
                                        }
                                    }
                                    sb2.append('K');
                                    break;
                                }
                                sb2.append('S');
                                break;
                            }
                            sb2.append('X');
                            break;
                        }
                        break;
                    case 'D':
                        if (!isLastChar(length2, i2 + 1) && isNextChar(sb, i2, 'G')) {
                            int i3 = i2 + 2;
                            if (FRONTV.indexOf(sb.charAt(i3)) >= 0) {
                                sb2.append('J');
                                i2 = i3;
                                break;
                            }
                        }
                        sb2.append('T');
                        break;
                    case 'F':
                    case 'J':
                    case 'L':
                    case 'M':
                    case 'N':
                    case 'R':
                        sb2.append(charAt);
                        break;
                    case 'G':
                        int i4 = i2 + 1;
                        if ((!isLastChar(length2, i4) || !isNextChar(sb, i2, 'H')) && ((isLastChar(length2, i4) || !isNextChar(sb, i2, 'H') || isVowel(sb, i2 + 2)) && (i2 <= 0 || (!regionMatch(sb, i2, "GN") && !regionMatch(sb, i2, "GNED"))))) {
                            boolean isPreviousChar = isPreviousChar(sb, i2, 'G');
                            if (!isLastChar(length2, i2) && FRONTV.indexOf(sb.charAt(i4)) >= 0 && !isPreviousChar) {
                                sb2.append('J');
                                break;
                            }
                            sb2.append('K');
                            break;
                        }
                        break;
                    case 'H':
                        if (!isLastChar(length2, i2) && ((i2 <= 0 || VARSON.indexOf(sb.charAt(i2 - 1)) < 0) && isVowel(sb, i2 + 1))) {
                            sb2.append('H');
                            break;
                        }
                        break;
                    case 'K':
                        if (i2 > 0) {
                            break;
                        }
                        sb2.append(charAt);
                        break;
                    case 'Q':
                        sb2.append('K');
                        break;
                    case 'S':
                        if (!regionMatch(sb, i2, "SH")) {
                            if (!regionMatch(sb, i2, "SIO")) {
                                break;
                            }
                        }
                        sb2.append('X');
                        break;
                    case 'T':
                        if (!regionMatch(sb, i2, "TIA") && !regionMatch(sb, i2, "TIO")) {
                            if (!regionMatch(sb, i2, "TCH")) {
                                if (regionMatch(sb, i2, "TH")) {
                                    sb2.append('0');
                                    break;
                                }
                                sb2.append('T');
                                break;
                            }
                        }
                        sb2.append('X');
                        break;
                    case 'V':
                        sb2.append('F');
                        break;
                    case 'W':
                    case 'Y':
                        if (!isLastChar(length2, i2)) {
                            break;
                        }
                        break;
                    case 'X':
                        sb2.append('K');
                        sb2.append('S');
                        break;
                    case 'Z':
                        sb2.append('S');
                        break;
                }
                i2++;
            } else {
                i2++;
            }
            if (sb2.length() > getMaxCodeLen()) {
                sb2.setLength(getMaxCodeLen());
            }
        }
        return sb2.toString();
    }

    public void setMaxCodeLen(int i2) {
        this.maxCodeLen = i2;
    }
}
