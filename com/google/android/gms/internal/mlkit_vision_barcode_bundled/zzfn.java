package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import org.apache.http.message.TokenParser;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzfn {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(zzfl zzflVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zzd(zzflVar, sb, 0);
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
                sb.append(zzgn.a(zzdb.zzs((String) obj)));
                sb.append('\"');
            } else if (obj instanceof zzdb) {
                sb.append(": \"");
                sb.append(zzgn.a((zzdb) obj));
                sb.append('\"');
            } else if (obj instanceof zzec) {
                sb.append(" {");
                zzd((zzec) obj, sb, i2 + 2);
                sb.append("\n");
                while (i3 < i2) {
                    sb.append(TokenParser.SP);
                    i3++;
                }
                sb.append("}");
            } else if (!(obj instanceof Map.Entry)) {
                sb.append(": ");
                sb.append(obj.toString());
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

    private static void zzd(zzfl zzflVar, StringBuilder sb, int i2) {
        Method[] declaredMethods;
        Object obj;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet();
        for (Method method : zzflVar.getClass().getDeclaredMethods()) {
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
                    b(sb, i2, zzc(concat), zzec.l(method2, zzflVar, new Object[0]));
                }
            }
            if (substring.endsWith("Map") && !substring.equals("Map")) {
                String valueOf3 = String.valueOf(substring.substring(0, 1).toLowerCase());
                String valueOf4 = String.valueOf(substring.substring(1, substring.length() - 3));
                String concat2 = valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3);
                Method method3 = (Method) hashMap.get(str);
                if (method3 != null && method3.getReturnType().equals(Map.class) && !method3.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method3.getModifiers())) {
                    b(sb, i2, zzc(concat2), zzec.l(method3, zzflVar, new Object[0]));
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
                    Object l2 = zzec.l(method4, zzflVar, new Object[0]);
                    if (method5 == null) {
                        if (l2 instanceof Boolean) {
                            if (((Boolean) l2).booleanValue()) {
                                b(sb, i2, zzc(concat3), l2);
                            }
                        } else if (l2 instanceof Integer) {
                            if (((Integer) l2).intValue() != 0) {
                                b(sb, i2, zzc(concat3), l2);
                            }
                        } else if (l2 instanceof Float) {
                            if (Float.floatToRawIntBits(((Float) l2).floatValue()) != 0) {
                                b(sb, i2, zzc(concat3), l2);
                            }
                        } else if (!(l2 instanceof Double)) {
                            if (l2 instanceof String) {
                                obj = "";
                            } else if (l2 instanceof zzdb) {
                                obj = zzdb.zzb;
                            } else if (!(l2 instanceof zzfl)) {
                                if ((l2 instanceof Enum) && ((Enum) l2).ordinal() == 0) {
                                }
                                b(sb, i2, zzc(concat3), l2);
                            } else if (l2 != ((zzfl) l2).zzX()) {
                                b(sb, i2, zzc(concat3), l2);
                            }
                            if (!l2.equals(obj)) {
                                b(sb, i2, zzc(concat3), l2);
                            }
                        } else if (Double.doubleToRawLongBits(((Double) l2).doubleValue()) != 0) {
                            b(sb, i2, zzc(concat3), l2);
                        }
                    } else if (((Boolean) zzec.l(method5, zzflVar, new Object[0])).booleanValue()) {
                        b(sb, i2, zzc(concat3), l2);
                    }
                }
            }
        }
        if (zzflVar instanceof zzdy) {
            Iterator zzf = ((zzdy) zzflVar).zza.zzf();
            while (zzf.hasNext()) {
                Map.Entry entry = (Map.Entry) zzf.next();
                int i3 = ((zzdz) entry.getKey()).f6418a;
                StringBuilder sb2 = new StringBuilder(13);
                sb2.append("[");
                sb2.append(i3);
                sb2.append("]");
                b(sb, i2, sb2.toString(), entry.getValue());
            }
        }
        zzgq zzgqVar = ((zzec) zzflVar).zzc;
        if (zzgqVar != null) {
            zzgqVar.c(sb, i2);
        }
    }
}
