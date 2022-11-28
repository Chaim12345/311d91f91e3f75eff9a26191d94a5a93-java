package com.google.android.libraries.places.internal;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import org.apache.http.message.TokenParser;
/* loaded from: classes2.dex */
final class zzaet {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zza(zzaer zzaerVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zzd(zzaerVar, sb, 0);
        return sb.toString();
    }

    static final void zzb(StringBuilder sb, int i2, String str, Object obj) {
        if (obj instanceof List) {
            for (Object obj2 : (List) obj) {
                zzb(sb, i2, str, obj2);
            }
        } else if (obj instanceof Map) {
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                zzb(sb, i2, str, entry);
            }
        } else {
            sb.append('\n');
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                sb.append(TokenParser.SP);
            }
            sb.append(str);
            if (obj instanceof String) {
                sb.append(": \"");
                sb.append(zzafr.zza(zzacp.zzl((String) obj)));
                sb.append('\"');
            } else if (obj instanceof zzacp) {
                sb.append(": \"");
                sb.append(zzafr.zza((zzacp) obj));
                sb.append('\"');
            } else if (obj instanceof zzadk) {
                sb.append(" {");
                zzd((zzadk) obj, sb, i2 + 2);
                sb.append("\n");
                while (i3 < i2) {
                    sb.append(TokenParser.SP);
                    i3++;
                }
                sb.append("}");
            } else if (!(obj instanceof Map.Entry)) {
                sb.append(": ");
                sb.append(obj);
            } else {
                sb.append(" {");
                Map.Entry entry2 = (Map.Entry) obj;
                int i5 = i2 + 2;
                zzb(sb, i5, "key", entry2.getKey());
                zzb(sb, i5, "value", entry2.getValue());
                sb.append("\n");
                while (i3 < i2) {
                    sb.append(TokenParser.SP);
                    i3++;
                }
                sb.append("}");
            }
        }
    }

    private static final String zzc(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (Character.isUpperCase(charAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(charAt));
        }
        return sb.toString();
    }

    private static void zzd(zzaer zzaerVar, StringBuilder sb, int i2) {
        Method[] declaredMethods;
        Object obj;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet();
        for (Method method : zzaerVar.getClass().getDeclaredMethods()) {
            hashMap2.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                hashMap.put(method.getName(), method);
                if (method.getName().startsWith("get")) {
                    treeSet.add(method.getName());
                }
            }
        }
        for (String str : treeSet) {
            String substring = str.startsWith("get") ? str.substring(3) : str;
            if (substring.endsWith("List") && !substring.endsWith("OrBuilderList") && !substring.equals("List")) {
                String valueOf = String.valueOf(substring.substring(0, 1).toLowerCase());
                String valueOf2 = String.valueOf(substring.substring(1, substring.length() - 4));
                String concat = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                Method method2 = (Method) hashMap.get(str);
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    zzb(sb, i2, zzc(concat), zzadk.zzE(method2, zzaerVar, new Object[0]));
                }
            }
            if (substring.endsWith("Map") && !substring.equals("Map")) {
                String valueOf3 = String.valueOf(substring.substring(0, 1).toLowerCase());
                String valueOf4 = String.valueOf(substring.substring(1, substring.length() - 3));
                String concat2 = valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3);
                Method method3 = (Method) hashMap.get(str);
                if (method3 != null && method3.getReturnType().equals(Map.class) && !method3.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method3.getModifiers())) {
                    zzb(sb, i2, zzc(concat2), zzadk.zzE(method3, zzaerVar, new Object[0]));
                }
            }
            if (((Method) hashMap2.get(substring.length() != 0 ? "set".concat(substring) : new String("set"))) != null) {
                if (substring.endsWith("Bytes")) {
                    String valueOf5 = String.valueOf(substring.substring(0, substring.length() - 5));
                    if (!hashMap.containsKey(valueOf5.length() != 0 ? "get".concat(valueOf5) : new String("get"))) {
                    }
                }
                String valueOf6 = String.valueOf(substring.substring(0, 1).toLowerCase());
                String valueOf7 = String.valueOf(substring.substring(1));
                String concat3 = valueOf7.length() != 0 ? valueOf6.concat(valueOf7) : new String(valueOf6);
                Method method4 = (Method) hashMap.get(substring.length() != 0 ? "get".concat(substring) : new String("get"));
                Method method5 = (Method) hashMap.get(substring.length() != 0 ? "has".concat(substring) : new String("has"));
                if (method4 != null) {
                    Object zzE = zzadk.zzE(method4, zzaerVar, new Object[0]);
                    if (method5 == null) {
                        if (zzE instanceof Boolean) {
                            if (((Boolean) zzE).booleanValue()) {
                                zzb(sb, i2, zzc(concat3), zzE);
                            }
                        } else if (zzE instanceof Integer) {
                            if (((Integer) zzE).intValue() != 0) {
                                zzb(sb, i2, zzc(concat3), zzE);
                            }
                        } else if (zzE instanceof Float) {
                            if (Float.floatToRawIntBits(((Float) zzE).floatValue()) != 0) {
                                zzb(sb, i2, zzc(concat3), zzE);
                            }
                        } else if (!(zzE instanceof Double)) {
                            if (zzE instanceof String) {
                                obj = "";
                            } else if (zzE instanceof zzacp) {
                                obj = zzacp.zzb;
                            } else if (!(zzE instanceof zzaer)) {
                                if ((zzE instanceof Enum) && ((Enum) zzE).ordinal() == 0) {
                                }
                                zzb(sb, i2, zzc(concat3), zzE);
                            } else if (zzE != ((zzaer) zzE).zzw()) {
                                zzb(sb, i2, zzc(concat3), zzE);
                            }
                            if (!zzE.equals(obj)) {
                                zzb(sb, i2, zzc(concat3), zzE);
                            }
                        } else if (Double.doubleToRawLongBits(((Double) zzE).doubleValue()) != 0) {
                            zzb(sb, i2, zzc(concat3), zzE);
                        }
                    } else if (((Boolean) zzadk.zzE(method5, zzaerVar, new Object[0])).booleanValue()) {
                        zzb(sb, i2, zzc(concat3), zzE);
                    }
                }
            }
        }
        if (zzaerVar instanceof zzadi) {
            zzadi zzadiVar = (zzadi) zzaerVar;
            throw null;
        }
        zzafu zzafuVar = ((zzadk) zzaerVar).zzc;
        if (zzafuVar != null) {
            zzafuVar.zze(sb, i2);
        }
    }
}
