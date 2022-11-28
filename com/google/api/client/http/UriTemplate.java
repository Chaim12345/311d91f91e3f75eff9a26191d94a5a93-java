package com.google.api.client.http;

import com.fasterxml.jackson.core.JsonPointer;
import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Types;
import com.google.api.client.util.escape.CharEscapers;
import com.google.common.base.Splitter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.slf4j.Marker;
/* loaded from: classes2.dex */
public class UriTemplate {
    private static final String COMPOSITE_NON_EXPLODE_JOINER = ",";
    private static final Map<Character, CompositeOutput> COMPOSITE_PREFIXES = new HashMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public enum CompositeOutput {
        PLUS('+', "", UriTemplate.COMPOSITE_NON_EXPLODE_JOINER, false, true),
        HASH('#', "#", UriTemplate.COMPOSITE_NON_EXPLODE_JOINER, false, true),
        DOT('.', ".", ".", false, false),
        FORWARD_SLASH(Character.valueOf(JsonPointer.SEPARATOR), "/", "/", false, false),
        SEMI_COLON(';', ";", ";", true, false),
        QUERY('?', "?", "&", true, false),
        AMP(Character.valueOf(Typography.amp), "&", "&", true, false),
        SIMPLE(null, "", UriTemplate.COMPOSITE_NON_EXPLODE_JOINER, false, false);
        
        private final String explodeJoiner;
        private final String outputPrefix;
        private final Character propertyPrefix;
        private final boolean requiresVarAssignment;
        private final boolean reservedExpansion;

        CompositeOutput(Character ch, String str, String str2, boolean z, boolean z2) {
            this.propertyPrefix = ch;
            this.outputPrefix = (String) Preconditions.checkNotNull(str);
            this.explodeJoiner = (String) Preconditions.checkNotNull(str2);
            this.requiresVarAssignment = z;
            this.reservedExpansion = z2;
            if (ch != null) {
                UriTemplate.COMPOSITE_PREFIXES.put(ch, this);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getEncodedValue(String str) {
            return this.reservedExpansion ? CharEscapers.escapeUriPathWithoutReserved(str) : CharEscapers.escapeUriConformant(str);
        }

        String b() {
            return this.explodeJoiner;
        }

        String c() {
            return this.outputPrefix;
        }

        int d() {
            return this.propertyPrefix == null ? 0 : 1;
        }

        boolean e() {
            return this.requiresVarAssignment;
        }
    }

    static {
        CompositeOutput.values();
    }

    static CompositeOutput b(String str) {
        CompositeOutput compositeOutput = COMPOSITE_PREFIXES.get(Character.valueOf(str.charAt(0)));
        return compositeOutput == null ? CompositeOutput.SIMPLE : compositeOutput;
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x00c5, code lost:
        if (r9 != null) goto L35;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String expand(String str, Object obj, boolean z) {
        Iterator it;
        String mapPropertyValue;
        String str2;
        Map<String, Object> map = getMap(obj);
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            int indexOf = str.indexOf(123, i2);
            if (indexOf != -1) {
                sb.append(str.substring(i2, indexOf));
                int indexOf2 = str.indexOf(125, indexOf + 2);
                int i3 = indexOf2 + 1;
                String substring = str.substring(indexOf + 1, indexOf2);
                CompositeOutput b2 = b(substring);
                ListIterator<String> listIterator = Splitter.on((char) AbstractJsonLexerKt.COMMA).splitToList(substring).listIterator();
                boolean z2 = true;
                while (listIterator.hasNext()) {
                    String next = listIterator.next();
                    boolean endsWith = next.endsWith(Marker.ANY_MARKER);
                    int d2 = listIterator.nextIndex() == 1 ? b2.d() : 0;
                    int length2 = next.length();
                    if (endsWith) {
                        length2--;
                    }
                    String substring2 = next.substring(d2, length2);
                    Object remove = map.remove(substring2);
                    if (remove != null) {
                        if (z2) {
                            sb.append(b2.c());
                            z2 = false;
                        } else {
                            sb.append(b2.b());
                        }
                        if (remove instanceof Iterator) {
                            it = (Iterator) remove;
                        } else if ((remove instanceof Iterable) || remove.getClass().isArray()) {
                            it = Types.iterableOf(remove).iterator();
                        } else {
                            if (remove.getClass().isEnum()) {
                                str2 = FieldInfo.of((Enum) remove).getName();
                            } else if (!Data.isValueOfPrimitiveType(remove)) {
                                mapPropertyValue = getMapPropertyValue(substring2, getMap(remove), endsWith, b2);
                                sb.append((Object) mapPropertyValue);
                            }
                            str2 = remove.toString();
                            mapPropertyValue = getSimpleValue(substring2, str2, b2);
                            sb.append((Object) mapPropertyValue);
                        }
                        mapPropertyValue = getListPropertyValue(substring2, it, endsWith, b2);
                        sb.append((Object) mapPropertyValue);
                    }
                }
                i2 = i3;
            } else if (i2 == 0 && !z) {
                return str;
            } else {
                sb.append(str.substring(i2));
            }
        }
        if (z) {
            GenericUrl.a(map.entrySet(), sb, false);
        }
        return sb.toString();
    }

    public static String expand(String str, String str2, Object obj, boolean z) {
        GenericUrl genericUrl;
        if (str2.startsWith("/")) {
            new GenericUrl(str).setRawPath(null);
            str2 = genericUrl.build() + str2;
        } else if (!str2.startsWith("http://") && !str2.startsWith("https://")) {
            str2 = str + str2;
        }
        return expand(str2, obj, z);
    }

    private static String getListPropertyValue(String str, Iterator<?> it, boolean z, CompositeOutput compositeOutput) {
        String str2;
        if (it.hasNext()) {
            StringBuilder sb = new StringBuilder();
            if (z) {
                str2 = compositeOutput.b();
            } else {
                if (compositeOutput.e()) {
                    sb.append(CharEscapers.escapeUriPath(str));
                    sb.append("=");
                }
                str2 = COMPOSITE_NON_EXPLODE_JOINER;
            }
            while (it.hasNext()) {
                if (z && compositeOutput.e()) {
                    sb.append(CharEscapers.escapeUriPath(str));
                    sb.append("=");
                }
                sb.append(compositeOutput.getEncodedValue(it.next().toString()));
                if (it.hasNext()) {
                    sb.append(str2);
                }
            }
            return sb.toString();
        }
        return "";
    }

    private static Map<String, Object> getMap(Object obj) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, Object> entry : Data.mapOf(obj).entrySet()) {
            Object value = entry.getValue();
            if (value != null && !Data.isNull(value)) {
                linkedHashMap.put(entry.getKey(), value);
            }
        }
        return linkedHashMap;
    }

    private static String getMapPropertyValue(String str, Map<String, Object> map, boolean z, CompositeOutput compositeOutput) {
        if (map.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String str2 = "=";
        String str3 = COMPOSITE_NON_EXPLODE_JOINER;
        if (z) {
            str3 = compositeOutput.b();
        } else {
            if (compositeOutput.e()) {
                sb.append(CharEscapers.escapeUriPath(str));
                sb.append("=");
            }
            str2 = COMPOSITE_NON_EXPLODE_JOINER;
        }
        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> next = it.next();
            String encodedValue = compositeOutput.getEncodedValue(next.getKey());
            String encodedValue2 = compositeOutput.getEncodedValue(next.getValue().toString());
            sb.append(encodedValue);
            sb.append(str2);
            sb.append(encodedValue2);
            if (it.hasNext()) {
                sb.append(str3);
            }
        }
        return sb.toString();
    }

    private static String getSimpleValue(String str, String str2, CompositeOutput compositeOutput) {
        return compositeOutput.e() ? String.format("%s=%s", str, compositeOutput.getEncodedValue(str2)) : compositeOutput.getEncodedValue(str2);
    }
}
