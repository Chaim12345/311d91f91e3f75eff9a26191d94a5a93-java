package org.apache.commons.codec.language;

import androidx.exifinterface.media.ExifInterface;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import java.util.Locale;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.http.message.TokenParser;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
/* loaded from: classes3.dex */
public class DoubleMetaphone implements StringEncoder {
    private static final String VOWELS = "AEIOUY";
    private int maxCodeLen = 4;
    private static final String[] SILENT_START = {"GN", "KN", "PN", "WR", "PS"};
    private static final String[] L_R_N_M_B_H_F_V_W_SPACE = {"L", "R", "N", "M", "B", "H", "F", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, ExifInterface.LONGITUDE_WEST, " "};
    private static final String[] ES_EP_EB_EL_EY_IB_IL_IN_IE_EI_ER = {"ES", "EP", "EB", "EL", "EY", "IB", "IL", AppConstants.GEO_FENCE_TRANSITION_IN, "IE", "EI", "ER"};
    private static final String[] L_T_K_S_N_M_B_Z = {"L", ExifInterface.GPS_DIRECTION_TRUE, "K", ExifInterface.LATITUDE_SOUTH, "N", "M", "B", "Z"};

    /* loaded from: classes3.dex */
    public class DoubleMetaphoneResult {
        private final StringBuilder alternate;
        private final int maxLength;
        private final StringBuilder primary;

        public DoubleMetaphoneResult(int i2) {
            this.primary = new StringBuilder(DoubleMetaphone.this.getMaxCodeLen());
            this.alternate = new StringBuilder(DoubleMetaphone.this.getMaxCodeLen());
            this.maxLength = i2;
        }

        public void append(char c2) {
            appendPrimary(c2);
            appendAlternate(c2);
        }

        public void append(char c2, char c3) {
            appendPrimary(c2);
            appendAlternate(c3);
        }

        public void append(String str) {
            appendPrimary(str);
            appendAlternate(str);
        }

        public void append(String str, String str2) {
            appendPrimary(str);
            appendAlternate(str2);
        }

        public void appendAlternate(char c2) {
            if (this.alternate.length() < this.maxLength) {
                this.alternate.append(c2);
            }
        }

        public void appendAlternate(String str) {
            int length = this.maxLength - this.alternate.length();
            if (str.length() <= length) {
                this.alternate.append(str);
            } else {
                this.alternate.append(str.substring(0, length));
            }
        }

        public void appendPrimary(char c2) {
            if (this.primary.length() < this.maxLength) {
                this.primary.append(c2);
            }
        }

        public void appendPrimary(String str) {
            int length = this.maxLength - this.primary.length();
            if (str.length() <= length) {
                this.primary.append(str);
            } else {
                this.primary.append(str.substring(0, length));
            }
        }

        public String getAlternate() {
            return this.alternate.toString();
        }

        public String getPrimary() {
            return this.primary.toString();
        }

        public boolean isComplete() {
            return this.primary.length() >= this.maxLength && this.alternate.length() >= this.maxLength;
        }
    }

    private String cleanInput(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.length() == 0) {
            return null;
        }
        return trim.toUpperCase(Locale.ENGLISH);
    }

    private boolean conditionC0(String str, int i2) {
        if (contains(str, i2, 4, "CHIA")) {
            return true;
        }
        if (i2 <= 1) {
            return false;
        }
        int i3 = i2 - 2;
        if (!isVowel(charAt(str, i3)) && contains(str, i2 - 1, 3, "ACH")) {
            char charAt = charAt(str, i2 + 2);
            return !(charAt == 'I' || charAt == 'E') || contains(str, i3, 6, "BACHER", "MACHER");
        }
        return false;
    }

    private boolean conditionCH0(String str, int i2) {
        if (i2 != 0) {
            return false;
        }
        int i3 = i2 + 1;
        return (contains(str, i3, 5, "HARAC", "HARIS") || contains(str, i3, 3, "HOR", "HYM", "HIA", "HEM")) && !contains(str, 0, 5, "CHORE");
    }

    private boolean conditionCH1(String str, int i2) {
        if (!contains(str, 0, 4, "VAN ", "VON ") && !contains(str, 0, 3, "SCH") && !contains(str, i2 - 2, 6, "ORCHES", "ARCHIT", "ORCHID")) {
            int i3 = i2 + 2;
            if (!contains(str, i3, 1, ExifInterface.GPS_DIRECTION_TRUE, ExifInterface.LATITUDE_SOUTH)) {
                if (!contains(str, i2 - 1, 1, ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "O", "U", ExifInterface.LONGITUDE_EAST) && i2 != 0) {
                    return false;
                }
                if (!contains(str, i3, 1, L_R_N_M_B_H_F_V_W_SPACE) && i2 + 1 != str.length() - 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean conditionL0(String str, int i2) {
        if (i2 == str.length() - 3 && contains(str, i2 - 1, 4, "ILLO", "ILLA", "ALLE")) {
            return true;
        }
        return (contains(str, str.length() - 2, 2, "AS", "OS") || contains(str, str.length() - 1, 1, ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "O")) && contains(str, i2 - 1, 4, "ALLE");
    }

    private boolean conditionM0(String str, int i2) {
        int i3 = i2 + 1;
        if (charAt(str, i3) == 'M') {
            return true;
        }
        return contains(str, i2 + (-1), 3, "UMB") && (i3 == str.length() - 1 || contains(str, i2 + 2, 2, "ER"));
    }

    protected static boolean contains(String str, int i2, int i3, String... strArr) {
        int i4;
        if (i2 < 0 || (i4 = i3 + i2) > str.length()) {
            return false;
        }
        String substring = str.substring(i2, i4);
        for (String str2 : strArr) {
            if (substring.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    private int handleAEIOUY(DoubleMetaphoneResult doubleMetaphoneResult, int i2) {
        if (i2 == 0) {
            doubleMetaphoneResult.append('A');
        }
        return i2 + 1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x00b7, code lost:
        if (contains(r12, r14, 3, "CIO", "CIE", "CIA") != false) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int handleC(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2) {
        if (!conditionC0(str, i2)) {
            if (i2 != 0 || !contains(str, i2, 6, "CAESAR")) {
                if (contains(str, i2, 2, "CH")) {
                    return handleCH(str, doubleMetaphoneResult, i2);
                }
                if (!contains(str, i2, 2, "CZ") || contains(str, i2 - 2, 4, "WICZ")) {
                    int i3 = i2 + 1;
                    if (contains(str, i3, 3, "CIA")) {
                        doubleMetaphoneResult.append('X');
                    } else if (contains(str, i2, 2, "CC") && (i2 != 1 || charAt(str, 0) != 'M')) {
                        return handleCC(str, doubleMetaphoneResult, i2);
                    } else {
                        if (!contains(str, i2, 2, "CK", "CG", "CQ")) {
                            if (!contains(str, i2, 2, "CI", "CE", "CY")) {
                                doubleMetaphoneResult.append('K');
                                if (!contains(str, i3, 2, " C", " Q", " G")) {
                                    if (!contains(str, i3, 1, "C", "K", "Q") || contains(str, i3, 2, "CE", "CI")) {
                                        return i3;
                                    }
                                    return i2 + 2;
                                }
                            }
                        }
                    }
                    return i2 + 3;
                }
                doubleMetaphoneResult.append('S', 'X');
                return i2 + 2;
            }
            doubleMetaphoneResult.append('S');
            return i2 + 2;
        }
        doubleMetaphoneResult.append('K');
        return i2 + 2;
    }

    private int handleCC(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2) {
        int i3 = i2 + 2;
        if (!contains(str, i3, 1, "I", ExifInterface.LONGITUDE_EAST, "H") || contains(str, i3, 2, "HU")) {
            doubleMetaphoneResult.append('K');
            return i3;
        }
        if ((i2 == 1 && charAt(str, i2 - 1) == 'A') || contains(str, i2 - 1, 5, "UCCEE", "UCCES")) {
            doubleMetaphoneResult.append("KS");
        } else {
            doubleMetaphoneResult.append('X');
        }
        return i2 + 3;
    }

    private int handleCH(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2) {
        if (i2 > 0 && contains(str, i2, 4, "CHAE")) {
            doubleMetaphoneResult.append('K', 'X');
        } else if (!conditionCH0(str, i2) && !conditionCH1(str, i2)) {
            if (i2 <= 0) {
                doubleMetaphoneResult.append('X');
            } else if (contains(str, 0, 2, "MC")) {
                doubleMetaphoneResult.append('K');
            } else {
                doubleMetaphoneResult.append('X', 'K');
            }
            return i2 + 2;
        } else {
            doubleMetaphoneResult.append('K');
        }
        return i2 + 2;
    }

    private int handleD(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2) {
        if (!contains(str, i2, 2, "DG")) {
            boolean contains = contains(str, i2, 2, "DT", "DD");
            doubleMetaphoneResult.append('T');
            return contains ? i2 + 2 : i2 + 1;
        }
        int i3 = i2 + 2;
        if (contains(str, i3, 1, "I", ExifInterface.LONGITUDE_EAST, "Y")) {
            doubleMetaphoneResult.append('J');
            return i2 + 3;
        }
        doubleMetaphoneResult.append("TK");
        return i3;
    }

    private int handleG(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2, boolean z) {
        int i3 = i2 + 1;
        if (charAt(str, i3) == 'H') {
            return handleGH(str, doubleMetaphoneResult, i2);
        }
        if (charAt(str, i3) == 'N') {
            if (i2 == 1 && isVowel(charAt(str, 0)) && !z) {
                doubleMetaphoneResult.append("KN", "N");
            } else if (contains(str, i2 + 2, 2, "EY") || charAt(str, i3) == 'Y' || z) {
                doubleMetaphoneResult.append("KN");
            } else {
                doubleMetaphoneResult.append("N", "KN");
            }
        } else if (contains(str, i3, 2, "LI") && !z) {
            doubleMetaphoneResult.append("KL", "L");
        } else if (i2 != 0 || (charAt(str, i3) != 'Y' && !contains(str, i3, 2, ES_EP_EB_EL_EY_IB_IL_IN_IE_EI_ER))) {
            if ((contains(str, i3, 2, "ER") || charAt(str, i3) == 'Y') && !contains(str, 0, 6, "DANGER", "RANGER", "MANGER")) {
                int i4 = i2 - 1;
                if (!contains(str, i4, 1, ExifInterface.LONGITUDE_EAST, "I") && !contains(str, i4, 3, "RGY", "OGY")) {
                    doubleMetaphoneResult.append('K', 'J');
                    return i2 + 2;
                }
            }
            if (!contains(str, i3, 1, ExifInterface.LONGITUDE_EAST, "I", "Y") && !contains(str, i2 - 1, 4, "AGGI", "OGGI")) {
                if (charAt(str, i3) == 'G') {
                    i3 = i2 + 2;
                }
                doubleMetaphoneResult.append('K');
                return i3;
            }
            if (contains(str, 0, 4, "VAN ", "VON ") || contains(str, 0, 3, "SCH") || contains(str, i3, 2, "ET")) {
                doubleMetaphoneResult.append('K');
            } else if (contains(str, i3, 3, "IER")) {
                doubleMetaphoneResult.append('J');
            } else {
                doubleMetaphoneResult.append('J', 'K');
            }
            return i2 + 2;
        } else {
            doubleMetaphoneResult.append('K', 'J');
        }
        return i2 + 2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0090, code lost:
        if (charAt(r11, r13 - 1) != 'I') goto L5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int handleGH(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2) {
        if (i2 <= 0 || isVowel(charAt(str, i2 - 1))) {
            if (i2 == 0) {
                int i3 = i2 + 2;
                if (charAt(str, i3) == 'I') {
                    doubleMetaphoneResult.append('J');
                    return i3;
                }
                doubleMetaphoneResult.append('K');
                return i3;
            }
            if ((i2 <= 1 || !contains(str, i2 - 2, 1, "B", "H", "D")) && ((i2 <= 2 || !contains(str, i2 - 3, 1, "B", "H", "D")) && (i2 <= 3 || !contains(str, i2 - 4, 1, "B", "H")))) {
                if (i2 > 2 && charAt(str, i2 - 1) == 'U' && contains(str, i2 - 3, 1, "C", "G", "L", "R", ExifInterface.GPS_DIRECTION_TRUE)) {
                    doubleMetaphoneResult.append('F');
                } else if (i2 > 0) {
                }
            }
            return i2 + 2;
        }
        doubleMetaphoneResult.append('K');
        return i2 + 2;
    }

    private int handleH(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2) {
        if ((i2 == 0 || isVowel(charAt(str, i2 - 1))) && isVowel(charAt(str, i2 + 1))) {
            doubleMetaphoneResult.append('H');
            return i2 + 2;
        }
        return i2 + 1;
    }

    private int handleJ(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2, boolean z) {
        if (contains(str, i2, 4, "JOSE") || contains(str, 0, 4, "SAN ")) {
            if ((i2 == 0 && charAt(str, i2 + 4) == ' ') || str.length() == 4 || contains(str, 0, 4, "SAN ")) {
                doubleMetaphoneResult.append('H');
            } else {
                doubleMetaphoneResult.append('J', 'H');
            }
            return i2 + 1;
        }
        if (i2 != 0 || contains(str, i2, 4, "JOSE")) {
            int i3 = i2 - 1;
            if (isVowel(charAt(str, i3)) && !z) {
                int i4 = i2 + 1;
                if (charAt(str, i4) == 'A' || charAt(str, i4) == 'O') {
                    doubleMetaphoneResult.append('J', 'H');
                }
            }
            if (i2 == str.length() - 1) {
                doubleMetaphoneResult.append('J', TokenParser.SP);
            } else if (!contains(str, i2 + 1, 1, L_T_K_S_N_M_B_Z) && !contains(str, i3, 1, ExifInterface.LATITUDE_SOUTH, "K", "L")) {
                doubleMetaphoneResult.append('J');
            }
        } else {
            doubleMetaphoneResult.append('J', 'A');
        }
        int i5 = i2 + 1;
        return charAt(str, i5) == 'J' ? i2 + 2 : i5;
    }

    private int handleL(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2) {
        int i3 = i2 + 1;
        if (charAt(str, i3) != 'L') {
            doubleMetaphoneResult.append(Matrix.MATRIX_TYPE_RANDOM_LT);
            return i3;
        }
        if (conditionL0(str, i2)) {
            doubleMetaphoneResult.appendPrimary(Matrix.MATRIX_TYPE_RANDOM_LT);
        } else {
            doubleMetaphoneResult.append(Matrix.MATRIX_TYPE_RANDOM_LT);
        }
        return i2 + 2;
    }

    private int handleP(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2) {
        int i3 = i2 + 1;
        if (charAt(str, i3) == 'H') {
            doubleMetaphoneResult.append('F');
            return i2 + 2;
        }
        doubleMetaphoneResult.append('P');
        if (contains(str, i3, 1, "P", "B")) {
            i3 = i2 + 2;
        }
        return i3;
    }

    private int handleR(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2, boolean z) {
        if (i2 != str.length() - 1 || z || !contains(str, i2 - 2, 2, "IE") || contains(str, i2 - 4, 2, "ME", "MA")) {
            doubleMetaphoneResult.append(Matrix.MATRIX_TYPE_RANDOM_REGULAR);
        } else {
            doubleMetaphoneResult.appendAlternate(Matrix.MATRIX_TYPE_RANDOM_REGULAR);
        }
        int i3 = i2 + 1;
        return charAt(str, i3) == 'R' ? i2 + 2 : i3;
    }

    private int handleS(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2, boolean z) {
        if (!contains(str, i2 - 1, 3, "ISL", "YSL")) {
            if (i2 != 0 || !contains(str, i2, 5, "SUGAR")) {
                if (contains(str, i2, 2, "SH")) {
                    if (contains(str, i2 + 1, 4, "HEIM", "HOEK", "HOLM", "HOLZ")) {
                        doubleMetaphoneResult.append('S');
                    } else {
                        doubleMetaphoneResult.append('X');
                    }
                } else if (contains(str, i2, 3, "SIO", "SIA") || contains(str, i2, 4, "SIAN")) {
                    if (z) {
                        doubleMetaphoneResult.append('S');
                    } else {
                        doubleMetaphoneResult.append('S', 'X');
                    }
                    return i2 + 3;
                } else {
                    if (i2 != 0 || !contains(str, i2 + 1, 1, "M", "N", "L", ExifInterface.LONGITUDE_WEST)) {
                        int i3 = i2 + 1;
                        if (!contains(str, i3, 1, "Z")) {
                            if (contains(str, i2, 2, "SC")) {
                                return handleSC(str, doubleMetaphoneResult, i2);
                            }
                            if (i2 == str.length() - 1 && contains(str, i2 - 2, 2, "AI", "OI")) {
                                doubleMetaphoneResult.appendAlternate('S');
                            } else {
                                doubleMetaphoneResult.append('S');
                            }
                            if (!contains(str, i3, 1, ExifInterface.LATITUDE_SOUTH, "Z")) {
                                return i3;
                            }
                        }
                    }
                    doubleMetaphoneResult.append('S', 'X');
                    int i4 = i2 + 1;
                    if (!contains(str, i4, 1, "Z")) {
                        return i4;
                    }
                }
                return i2 + 2;
            }
            doubleMetaphoneResult.append('X', 'S');
        }
        return i2 + 1;
    }

    private int handleSC(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2) {
        int i3 = i2 + 2;
        if (charAt(str, i3) == 'H') {
            int i4 = i2 + 3;
            if (contains(str, i4, 2, "OO", "ER", "EN", "UY", "ED", "EM")) {
                if (contains(str, i4, 2, "ER", "EN")) {
                    doubleMetaphoneResult.append("X", "SK");
                }
                doubleMetaphoneResult.append("SK");
            } else if (i2 != 0 || isVowel(charAt(str, 3)) || charAt(str, 3) == 'W') {
                doubleMetaphoneResult.append('X');
            } else {
                doubleMetaphoneResult.append('X', 'S');
            }
        } else {
            if (contains(str, i3, 1, "I", ExifInterface.LONGITUDE_EAST, "Y")) {
                doubleMetaphoneResult.append('S');
            }
            doubleMetaphoneResult.append("SK");
        }
        return i2 + 3;
    }

    private int handleT(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2) {
        if (contains(str, i2, 4, "TION") || contains(str, i2, 3, "TIA", "TCH")) {
            doubleMetaphoneResult.append('X');
            return i2 + 3;
        } else if (!contains(str, i2, 2, "TH") && !contains(str, i2, 3, "TTH")) {
            doubleMetaphoneResult.append('T');
            int i3 = i2 + 1;
            return contains(str, i3, 1, ExifInterface.GPS_DIRECTION_TRUE, "D") ? i2 + 2 : i3;
        } else {
            int i4 = i2 + 2;
            if (contains(str, i4, 2, "OM", "AM") || contains(str, 0, 4, "VAN ", "VON ") || contains(str, 0, 3, "SCH")) {
                doubleMetaphoneResult.append('T');
                return i4;
            }
            doubleMetaphoneResult.append('0', 'T');
            return i4;
        }
    }

    private int handleW(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2) {
        int i3 = 2;
        if (!contains(str, i2, 2, "WR")) {
            if (i2 == 0) {
                int i4 = i2 + 1;
                if (isVowel(charAt(str, i4)) || contains(str, i2, 2, "WH")) {
                    if (isVowel(charAt(str, i4))) {
                        doubleMetaphoneResult.append('A', 'F');
                    } else {
                        doubleMetaphoneResult.append('A');
                    }
                    return i4;
                }
            }
            if ((i2 == str.length() - 1 && isVowel(charAt(str, i2 - 1))) || contains(str, i2 - 1, 5, "EWSKI", "EWSKY", "OWSKI", "OWSKY") || contains(str, 0, 3, "SCH")) {
                doubleMetaphoneResult.appendAlternate('F');
            } else {
                i3 = 4;
                if (contains(str, i2, 4, "WICZ", "WITZ")) {
                    doubleMetaphoneResult.append("TS", "FX");
                }
            }
            return i2 + 1;
        }
        doubleMetaphoneResult.append(Matrix.MATRIX_TYPE_RANDOM_REGULAR);
        return i2 + i3;
    }

    private int handleX(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2) {
        if (i2 == 0) {
            doubleMetaphoneResult.append('S');
            return i2 + 1;
        }
        if (i2 != str.length() - 1 || (!contains(str, i2 - 3, 3, "IAU", "EAU") && !contains(str, i2 - 2, 2, "AU", "OU"))) {
            doubleMetaphoneResult.append("KS");
        }
        int i3 = i2 + 1;
        return contains(str, i3, 1, "C", "X") ? i2 + 2 : i3;
    }

    private int handleZ(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i2, boolean z) {
        int i3 = i2 + 1;
        if (charAt(str, i3) == 'H') {
            doubleMetaphoneResult.append('J');
            return i2 + 2;
        }
        if (contains(str, i3, 2, "ZO", "ZI", "ZA") || (z && i2 > 0 && charAt(str, i2 - 1) != 'T')) {
            doubleMetaphoneResult.append(ExifInterface.LATITUDE_SOUTH, "TS");
        } else {
            doubleMetaphoneResult.append('S');
        }
        if (charAt(str, i3) == 'Z') {
            i3 = i2 + 2;
        }
        return i3;
    }

    private boolean isSilentStart(String str) {
        for (String str2 : SILENT_START) {
            if (str.startsWith(str2)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSlavoGermanic(String str) {
        return str.indexOf(87) > -1 || str.indexOf(75) > -1 || str.indexOf("CZ") > -1 || str.indexOf("WITZ") > -1;
    }

    private boolean isVowel(char c2) {
        return VOWELS.indexOf(c2) != -1;
    }

    protected char charAt(String str, int i2) {
        if (i2 < 0 || i2 >= str.length()) {
            return (char) 0;
        }
        return str.charAt(i2);
    }

    public String doubleMetaphone(String str) {
        return doubleMetaphone(str, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v10, types: [int] */
    /* JADX WARN: Type inference failed for: r1v11, types: [int] */
    /* JADX WARN: Type inference failed for: r1v12, types: [int] */
    /* JADX WARN: Type inference failed for: r1v13, types: [int] */
    /* JADX WARN: Type inference failed for: r1v14, types: [int] */
    /* JADX WARN: Type inference failed for: r1v15, types: [int] */
    /* JADX WARN: Type inference failed for: r1v16, types: [int] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18, types: [int] */
    /* JADX WARN: Type inference failed for: r1v19, types: [int] */
    /* JADX WARN: Type inference failed for: r1v2, types: [int] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [int] */
    /* JADX WARN: Type inference failed for: r1v5, types: [int] */
    /* JADX WARN: Type inference failed for: r1v6, types: [int] */
    /* JADX WARN: Type inference failed for: r1v7, types: [int] */
    /* JADX WARN: Type inference failed for: r1v8, types: [int] */
    /* JADX WARN: Type inference failed for: r1v9, types: [int] */
    /* JADX WARN: Type inference failed for: r7v0, types: [org.apache.commons.codec.language.DoubleMetaphone] */
    /* JADX WARN: Type inference failed for: r8v1, types: [java.lang.String] */
    public String doubleMetaphone(String str, boolean z) {
        int i2;
        ?? cleanInput = cleanInput(str);
        if (cleanInput == 0) {
            return null;
        }
        boolean isSlavoGermanic = isSlavoGermanic(cleanInput);
        ?? isSilentStart = isSilentStart(cleanInput);
        DoubleMetaphoneResult doubleMetaphoneResult = new DoubleMetaphoneResult(getMaxCodeLen());
        while (!doubleMetaphoneResult.isComplete() && isSilentStart <= cleanInput.length() - 1) {
            char charAt = cleanInput.charAt(isSilentStart);
            if (charAt == 199) {
                doubleMetaphoneResult.append('S');
            } else if (charAt != 209) {
                switch (charAt) {
                    case 'A':
                    case 'E':
                    case 'I':
                    case 'O':
                    case 'U':
                    case 'Y':
                        isSilentStart = handleAEIOUY(doubleMetaphoneResult, isSilentStart);
                        break;
                    case 'B':
                        doubleMetaphoneResult.append('P');
                        i2 = isSilentStart + 1;
                        if (charAt(cleanInput, i2) != 'B') {
                            isSilentStart = i2;
                            break;
                        } else {
                            isSilentStart += 2;
                            break;
                        }
                    case 'C':
                        isSilentStart = handleC(cleanInput, doubleMetaphoneResult, isSilentStart);
                        break;
                    case 'D':
                        isSilentStart = handleD(cleanInput, doubleMetaphoneResult, isSilentStart);
                        break;
                    case 'F':
                        doubleMetaphoneResult.append('F');
                        i2 = isSilentStart + 1;
                        if (charAt(cleanInput, i2) != 'F') {
                            isSilentStart = i2;
                            break;
                        } else {
                            isSilentStart += 2;
                            break;
                        }
                    case 'G':
                        isSilentStart = handleG(cleanInput, doubleMetaphoneResult, isSilentStart, isSlavoGermanic);
                        break;
                    case 'H':
                        isSilentStart = handleH(cleanInput, doubleMetaphoneResult, isSilentStart);
                        break;
                    case 'J':
                        isSilentStart = handleJ(cleanInput, doubleMetaphoneResult, isSilentStart, isSlavoGermanic);
                        break;
                    case 'K':
                        doubleMetaphoneResult.append('K');
                        i2 = isSilentStart + 1;
                        if (charAt(cleanInput, i2) != 'K') {
                            isSilentStart = i2;
                            break;
                        } else {
                            isSilentStart += 2;
                            break;
                        }
                    case 'L':
                        isSilentStart = handleL(cleanInput, doubleMetaphoneResult, isSilentStart);
                        break;
                    case 'M':
                        doubleMetaphoneResult.append('M');
                        if (!conditionM0(cleanInput, isSilentStart)) {
                            break;
                        } else {
                            isSilentStart += 2;
                            break;
                        }
                    case 'N':
                        doubleMetaphoneResult.append('N');
                        i2 = isSilentStart + 1;
                        if (charAt(cleanInput, i2) != 'N') {
                            isSilentStart = i2;
                            break;
                        } else {
                            isSilentStart += 2;
                            break;
                        }
                    case 'P':
                        isSilentStart = handleP(cleanInput, doubleMetaphoneResult, isSilentStart);
                        break;
                    case 'Q':
                        doubleMetaphoneResult.append('K');
                        i2 = isSilentStart + 1;
                        if (charAt(cleanInput, i2) != 'Q') {
                            isSilentStart = i2;
                            break;
                        } else {
                            isSilentStart += 2;
                            break;
                        }
                    case 'R':
                        isSilentStart = handleR(cleanInput, doubleMetaphoneResult, isSilentStart, isSlavoGermanic);
                        break;
                    case 'S':
                        isSilentStart = handleS(cleanInput, doubleMetaphoneResult, isSilentStart, isSlavoGermanic);
                        break;
                    case 'T':
                        isSilentStart = handleT(cleanInput, doubleMetaphoneResult, isSilentStart);
                        break;
                    case 'V':
                        doubleMetaphoneResult.append('F');
                        i2 = isSilentStart + 1;
                        if (charAt(cleanInput, i2) != 'V') {
                            isSilentStart = i2;
                            break;
                        } else {
                            isSilentStart += 2;
                            break;
                        }
                    case 'W':
                        isSilentStart = handleW(cleanInput, doubleMetaphoneResult, isSilentStart);
                        break;
                    case 'X':
                        isSilentStart = handleX(cleanInput, doubleMetaphoneResult, isSilentStart);
                        break;
                    case 'Z':
                        isSilentStart = handleZ(cleanInput, doubleMetaphoneResult, isSilentStart, isSlavoGermanic);
                        break;
                }
            } else {
                doubleMetaphoneResult.append('N');
            }
            isSilentStart++;
        }
        return z ? doubleMetaphoneResult.getAlternate() : doubleMetaphoneResult.getPrimary();
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) {
        if (obj instanceof String) {
            return doubleMetaphone((String) obj);
        }
        throw new EncoderException("DoubleMetaphone encode parameter is not of type String");
    }

    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String str) {
        return doubleMetaphone(str);
    }

    public int getMaxCodeLen() {
        return this.maxCodeLen;
    }

    public boolean isDoubleMetaphoneEqual(String str, String str2) {
        return isDoubleMetaphoneEqual(str, str2, false);
    }

    public boolean isDoubleMetaphoneEqual(String str, String str2, boolean z) {
        return StringUtils.equals(doubleMetaphone(str, z), doubleMetaphone(str2, z));
    }

    public void setMaxCodeLen(int i2) {
        this.maxCodeLen = i2;
    }
}
