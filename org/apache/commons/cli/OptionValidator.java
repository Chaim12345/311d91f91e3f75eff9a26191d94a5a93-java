package org.apache.commons.cli;
/* loaded from: classes3.dex */
class OptionValidator {
    OptionValidator() {
    }

    private static boolean isValidChar(char c2) {
        return Character.isJavaIdentifierPart(c2);
    }

    private static boolean isValidOpt(char c2) {
        return isValidChar(c2) || c2 == ' ' || c2 == '?' || c2 == '@';
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validateOption(String str) {
        if (str == null) {
            return;
        }
        if (str.length() == 1) {
            char charAt = str.charAt(0);
            if (isValidOpt(charAt)) {
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("illegal option value '");
            stringBuffer.append(charAt);
            stringBuffer.append("'");
            throw new IllegalArgumentException(stringBuffer.toString());
        }
        char[] charArray = str.toCharArray();
        for (int i2 = 0; i2 < charArray.length; i2++) {
            if (!isValidChar(charArray[i2])) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("opt contains illegal character value '");
                stringBuffer2.append(charArray[i2]);
                stringBuffer2.append("'");
                throw new IllegalArgumentException(stringBuffer2.toString());
            }
        }
    }
}
