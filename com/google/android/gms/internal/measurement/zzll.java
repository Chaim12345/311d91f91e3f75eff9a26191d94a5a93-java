package com.google.android.gms.internal.measurement;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import org.apache.http.message.TokenParser;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzll {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(zzlj zzljVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zzd(zzljVar, sb, 0);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final void b(StringBuilder sb, int i2, String str, Object obj) {
        if (obj instanceof List) {
            for (Object obj2 : (List) obj) {
                b(sb, i2, str, obj2);
            }
        } else if (obj instanceof Map) {
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                b(sb, i2, str, entry);
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
                sb.append(zzmj.a(zzjb.zzm((String) obj)));
                sb.append('\"');
            } else if (obj instanceof zzjb) {
                sb.append(": \"");
                sb.append(zzmj.a((zzjb) obj));
                sb.append('\"');
            } else if (obj instanceof zzkc) {
                sb.append(" {");
                zzd((zzkc) obj, sb, i2 + 2);
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
                b(sb, i5, "key", entry2.getKey());
                b(sb, i5, "value", entry2.getValue());
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

    private static void zzd(zzlj zzljVar, StringBuilder sb, int i2) {
        Method[] declaredMethods;
        Object obj;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet();
        for (Method method : zzljVar.getClass().getDeclaredMethods()) {
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
                String concat = String.valueOf(substring.substring(0, 1).toLowerCase()).concat(String.valueOf(substring.substring(1, substring.length() - 4)));
                Method method2 = (Method) hashMap.get(str);
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    b(sb, i2, zzc(concat), zzkc.k(method2, zzljVar, new Object[0]));
                }
            }
            if (substring.endsWith("Map") && !substring.equals("Map")) {
                String concat2 = String.valueOf(substring.substring(0, 1).toLowerCase()).concat(String.valueOf(substring.substring(1, substring.length() - 3)));
                Method method3 = (Method) hashMap.get(str);
                if (method3 != null && method3.getReturnType().equals(Map.class) && !method3.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method3.getModifiers())) {
                    b(sb, i2, zzc(concat2), zzkc.k(method3, zzljVar, new Object[0]));
                }
            }
            if (((Method) hashMap2.get("set".concat(substring))) != null && (!substring.endsWith("Bytes") || !hashMap.containsKey("get".concat(String.valueOf(substring.substring(0, substring.length() - 5)))))) {
                String concat3 = String.valueOf(substring.substring(0, 1).toLowerCase()).concat(String.valueOf(substring.substring(1)));
                Method method4 = (Method) hashMap.get("get".concat(substring));
                Method method5 = (Method) hashMap.get("has".concat(substring));
                if (method4 != null) {
                    Object k2 = zzkc.k(method4, zzljVar, new Object[0]);
                    if (method5 == null) {
                        if (k2 instanceof Boolean) {
                            if (((Boolean) k2).booleanValue()) {
                                b(sb, i2, zzc(concat3), k2);
                            }
                        } else if (k2 instanceof Integer) {
                            if (((Integer) k2).intValue() != 0) {
                                b(sb, i2, zzc(concat3), k2);
                            }
                        } else if (k2 instanceof Float) {
                            if (Float.floatToRawIntBits(((Float) k2).floatValue()) != 0) {
                                b(sb, i2, zzc(concat3), k2);
                            }
                        } else if (!(k2 instanceof Double)) {
                            if (k2 instanceof String) {
                                obj = "";
                            } else if (k2 instanceof zzjb) {
                                obj = zzjb.zzb;
                            } else if (!(k2 instanceof zzlj)) {
                                if ((k2 instanceof Enum) && ((Enum) k2).ordinal() == 0) {
                                }
                                b(sb, i2, zzc(concat3), k2);
                            } else if (k2 != ((zzlj) k2).zzbR()) {
                                b(sb, i2, zzc(concat3), k2);
                            }
                            if (!k2.equals(obj)) {
                                b(sb, i2, zzc(concat3), k2);
                            }
                        } else if (Double.doubleToRawLongBits(((Double) k2).doubleValue()) != 0) {
                            b(sb, i2, zzc(concat3), k2);
                        }
                    } else if (((Boolean) zzkc.k(method5, zzljVar, new Object[0])).booleanValue()) {
                        b(sb, i2, zzc(concat3), k2);
                    }
                }
            }
        }
        if (zzljVar instanceof zzjz) {
            zzjz zzjzVar = (zzjz) zzljVar;
            throw null;
        }
        zzmm zzmmVar = ((zzkc) zzljVar).zzc;
        if (zzmmVar != null) {
            zzmmVar.c(sb, i2);
        }
    }
}
