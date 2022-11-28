package org.bouncycastle.est;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
/* loaded from: classes3.dex */
class HttpUtil {

    /* loaded from: classes3.dex */
    static class Headers extends HashMap<String, String[]> {
        private String actualKey(String str) {
            if (containsKey(str)) {
                return str;
            }
            for (String str2 : keySet()) {
                if (str.equalsIgnoreCase(str2)) {
                    return str2;
                }
            }
            return null;
        }

        private String[] copy(String[] strArr) {
            int length = strArr.length;
            String[] strArr2 = new String[length];
            System.arraycopy(strArr, 0, strArr2, 0, length);
            return strArr2;
        }

        private boolean hasHeader(String str) {
            return actualKey(str) != null;
        }

        public void add(String str, String str2) {
            put(str, HttpUtil.append(get(str), str2));
        }

        @Override // java.util.HashMap, java.util.AbstractMap
        public Object clone() {
            Headers headers = new Headers();
            for (Map.Entry<String, String[]> entry : entrySet()) {
                headers.put(entry.getKey(), copy(entry.getValue()));
            }
            return headers;
        }

        public void ensureHeader(String str, String str2) {
            if (containsKey(str)) {
                return;
            }
            set(str, str2);
        }

        public String getFirstValue(String str) {
            String[] values = getValues(str);
            if (values == null || values.length <= 0) {
                return null;
            }
            return values[0];
        }

        public String[] getValues(String str) {
            String actualKey = actualKey(str);
            if (actualKey == null) {
                return null;
            }
            return get(actualKey);
        }

        public void set(String str, String str2) {
            put(str, new String[]{str2});
        }
    }

    /* loaded from: classes3.dex */
    static class PartLexer {

        /* renamed from: a  reason: collision with root package name */
        int f13561a = 0;

        /* renamed from: b  reason: collision with root package name */
        int f13562b = 0;
        private final String src;

        PartLexer(String str) {
            this.src = str;
        }

        private String consumeAlpha() {
            char charAt = this.src.charAt(this.f13562b);
            while (this.f13562b < this.src.length() && ((charAt >= 'a' && charAt <= 'z') || (charAt >= 'A' && charAt <= 'Z'))) {
                int i2 = this.f13562b + 1;
                this.f13562b = i2;
                charAt = this.src.charAt(i2);
            }
            String substring = this.src.substring(this.f13561a, this.f13562b);
            this.f13561a = this.f13562b;
            return substring;
        }

        private boolean consumeIf(char c2) {
            if (this.f13562b >= this.src.length() || this.src.charAt(this.f13562b) != c2) {
                return false;
            }
            this.f13562b++;
            return true;
        }

        private String consumeUntil(char c2) {
            while (this.f13562b < this.src.length() && this.src.charAt(this.f13562b) != c2) {
                this.f13562b++;
            }
            String substring = this.src.substring(this.f13561a, this.f13562b);
            this.f13561a = this.f13562b;
            return substring;
        }

        private void discard() {
            this.f13561a = this.f13562b;
        }

        private void discard(int i2) {
            int i3 = this.f13562b + i2;
            this.f13562b = i3;
            this.f13561a = i3;
        }

        private void skipWhiteSpace() {
            while (this.f13562b < this.src.length() && this.src.charAt(this.f13562b) < '!') {
                this.f13562b++;
            }
            this.f13561a = this.f13562b;
        }

        Map a() {
            HashMap hashMap = new HashMap();
            while (this.f13562b < this.src.length()) {
                skipWhiteSpace();
                String consumeAlpha = consumeAlpha();
                if (consumeAlpha.length() == 0) {
                    throw new IllegalArgumentException("Expecting alpha label.");
                }
                skipWhiteSpace();
                if (!consumeIf('=')) {
                    throw new IllegalArgumentException("Expecting assign: '='");
                }
                skipWhiteSpace();
                if (!consumeIf('\"')) {
                    throw new IllegalArgumentException("Expecting start quote: '\"'");
                }
                discard();
                String consumeUntil = consumeUntil('\"');
                discard(1);
                hashMap.put(consumeAlpha, consumeUntil);
                skipWhiteSpace();
                if (!consumeIf(AbstractJsonLexerKt.COMMA)) {
                    break;
                }
                discard();
            }
            return hashMap;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(String str, Map map) {
        StringWriter stringWriter = new StringWriter();
        stringWriter.write(str);
        stringWriter.write(32);
        boolean z = false;
        for (Map.Entry entry : map.entrySet()) {
            if (z) {
                stringWriter.write(44);
            } else {
                z = true;
            }
            stringWriter.write((String) entry.getKey());
            stringWriter.write("=\"");
            stringWriter.write((String) entry.getValue());
            stringWriter.write(34);
        }
        return stringWriter.toString();
    }

    public static String[] append(String[] strArr, String str) {
        if (strArr == null) {
            return new String[]{str};
        }
        int length = strArr.length;
        String[] strArr2 = new String[length + 1];
        System.arraycopy(strArr, 0, strArr2, 0, length);
        strArr2[length] = str;
        return strArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map b(String str, String str2) {
        String trim = str2.trim();
        if (trim.startsWith(str)) {
            trim = trim.substring(str.length());
        }
        return new PartLexer(trim).a();
    }
}
