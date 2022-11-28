package com.google.android.gms.internal.measurement;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.message.TokenParser;
/* loaded from: classes.dex */
public final class zzat implements Iterable, zzap {
    private final String zza;

    public zzat(String str) {
        if (str == null) {
            throw new IllegalArgumentException("StringValue cannot be null.");
        }
        this.zza = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzat) {
            return this.zza.equals(((zzat) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new zzas(this);
    }

    public final String toString() {
        String str = this.zza;
        return "\"" + str + "\"";
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x03d3  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x041f  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x04af  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x04ff  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x055c  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x05b3  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x05fa  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0635  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x016f  */
    @Override // com.google.android.gms.internal.measurement.zzap
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzap zzbQ(String str, zzg zzgVar, List list) {
        String str2;
        String str3;
        String str4;
        String str5;
        char c2;
        String str6;
        String str7;
        String str8;
        String str9;
        zzat zzatVar;
        int i2;
        zzap zzahVar;
        double doubleValue;
        String str10;
        Matcher matcher;
        int i3;
        int i4;
        zzg zzgVar2;
        int i5;
        String str11;
        String str12;
        if ("charAt".equals(str) || "concat".equals(str) || "hasOwnProperty".equals(str) || "indexOf".equals(str) || "lastIndexOf".equals(str) || "match".equals(str) || "replace".equals(str) || FirebaseAnalytics.Event.SEARCH.equals(str) || "slice".equals(str) || "split".equals(str) || "substring".equals(str) || "toLowerCase".equals(str) || "toLocaleLowerCase".equals(str) || "toString".equals(str)) {
            str2 = "toLocaleUpperCase";
            str3 = "toUpperCase";
        } else {
            str3 = "toUpperCase";
            str2 = "toLocaleUpperCase";
            if (!str3.equals(str) && !str2.equals(str)) {
                str4 = "hasOwnProperty";
                if (!"trim".equals(str)) {
                    throw new IllegalArgumentException(String.format("%s is not a String function", str));
                }
                switch (str.hashCode()) {
                    case -1789698943:
                        String str13 = "charAt";
                        String str14 = str4;
                        str5 = "toString";
                        boolean equals = str.equals(str14);
                        str6 = str14;
                        str11 = str13;
                        if (equals) {
                            c2 = 2;
                            str7 = str14;
                            str8 = str13;
                            break;
                        }
                        c2 = 65535;
                        str7 = str6;
                        str8 = str11;
                        break;
                    case -1776922004:
                        String str15 = "charAt";
                        str5 = "toString";
                        if (str.equals(str5)) {
                            c2 = 14;
                            str7 = str4;
                            str8 = str15;
                            break;
                        } else {
                            str6 = str4;
                            str11 = str15;
                            c2 = 65535;
                            str7 = str6;
                            str8 = str11;
                            break;
                        }
                    case -1464939364:
                        String str16 = "charAt";
                        str12 = str16;
                        if (str.equals("toLocaleLowerCase")) {
                            c2 = '\f';
                            str9 = str16;
                            str7 = str4;
                            str5 = "toString";
                            str8 = str9;
                            break;
                        }
                        str6 = str4;
                        str5 = "toString";
                        str11 = str12;
                        c2 = 65535;
                        str7 = str6;
                        str8 = str11;
                        break;
                    case -1361633751:
                        String str17 = "charAt";
                        boolean equals2 = str.equals(str17);
                        str12 = str17;
                        if (equals2) {
                            str7 = str4;
                            str5 = "toString";
                            c2 = 0;
                            str8 = str17;
                            break;
                        }
                        str6 = str4;
                        str5 = "toString";
                        str11 = str12;
                        c2 = 65535;
                        str7 = str6;
                        str8 = str11;
                        break;
                    case -1354795244:
                        if (str.equals("concat")) {
                            str8 = "charAt";
                            str7 = str4;
                            str5 = "toString";
                            c2 = 1;
                            break;
                        }
                        str12 = "charAt";
                        str6 = str4;
                        str5 = "toString";
                        str11 = str12;
                        c2 = 65535;
                        str7 = str6;
                        str8 = str11;
                        break;
                    case -1137582698:
                        if (str.equals("toLowerCase")) {
                            c2 = TokenParser.CR;
                            str9 = "charAt";
                            str7 = str4;
                            str5 = "toString";
                            str8 = str9;
                            break;
                        }
                        str12 = "charAt";
                        str6 = str4;
                        str5 = "toString";
                        str11 = str12;
                        c2 = 65535;
                        str7 = str6;
                        str8 = str11;
                        break;
                    case -906336856:
                        if (str.equals(FirebaseAnalytics.Event.SEARCH)) {
                            c2 = 7;
                            str9 = "charAt";
                            str7 = str4;
                            str5 = "toString";
                            str8 = str9;
                            break;
                        }
                        str12 = "charAt";
                        str6 = str4;
                        str5 = "toString";
                        str11 = str12;
                        c2 = 65535;
                        str7 = str6;
                        str8 = str11;
                        break;
                    case -726908483:
                        if (str.equals(str2)) {
                            c2 = 11;
                            str9 = "charAt";
                            str7 = str4;
                            str5 = "toString";
                            str8 = str9;
                            break;
                        }
                        str12 = "charAt";
                        str6 = str4;
                        str5 = "toString";
                        str11 = str12;
                        c2 = 65535;
                        str7 = str6;
                        str8 = str11;
                        break;
                    case -467511597:
                        if (str.equals("lastIndexOf")) {
                            c2 = 4;
                            str9 = "charAt";
                            str7 = str4;
                            str5 = "toString";
                            str8 = str9;
                            break;
                        }
                        str12 = "charAt";
                        str6 = str4;
                        str5 = "toString";
                        str11 = str12;
                        c2 = 65535;
                        str7 = str6;
                        str8 = str11;
                        break;
                    case -399551817:
                        if (str.equals(str3)) {
                            c2 = 15;
                            str9 = "charAt";
                            str7 = str4;
                            str5 = "toString";
                            str8 = str9;
                            break;
                        }
                        str12 = "charAt";
                        str6 = str4;
                        str5 = "toString";
                        str11 = str12;
                        c2 = 65535;
                        str7 = str6;
                        str8 = str11;
                        break;
                    case 3568674:
                        if (str.equals("trim")) {
                            c2 = 16;
                            str9 = "charAt";
                            str7 = str4;
                            str5 = "toString";
                            str8 = str9;
                            break;
                        }
                        str12 = "charAt";
                        str6 = str4;
                        str5 = "toString";
                        str11 = str12;
                        c2 = 65535;
                        str7 = str6;
                        str8 = str11;
                        break;
                    case 103668165:
                        if (str.equals("match")) {
                            c2 = 5;
                            str9 = "charAt";
                            str7 = str4;
                            str5 = "toString";
                            str8 = str9;
                            break;
                        }
                        str12 = "charAt";
                        str6 = str4;
                        str5 = "toString";
                        str11 = str12;
                        c2 = 65535;
                        str7 = str6;
                        str8 = str11;
                        break;
                    case 109526418:
                        if (str.equals("slice")) {
                            c2 = '\b';
                            str9 = "charAt";
                            str7 = str4;
                            str5 = "toString";
                            str8 = str9;
                            break;
                        }
                        str12 = "charAt";
                        str6 = str4;
                        str5 = "toString";
                        str11 = str12;
                        c2 = 65535;
                        str7 = str6;
                        str8 = str11;
                        break;
                    case 109648666:
                        if (str.equals("split")) {
                            c2 = '\t';
                            str9 = "charAt";
                            str7 = str4;
                            str5 = "toString";
                            str8 = str9;
                            break;
                        }
                        str12 = "charAt";
                        str6 = str4;
                        str5 = "toString";
                        str11 = str12;
                        c2 = 65535;
                        str7 = str6;
                        str8 = str11;
                        break;
                    case 530542161:
                        if (str.equals("substring")) {
                            c2 = '\n';
                            str9 = "charAt";
                            str7 = str4;
                            str5 = "toString";
                            str8 = str9;
                            break;
                        }
                        str12 = "charAt";
                        str6 = str4;
                        str5 = "toString";
                        str11 = str12;
                        c2 = 65535;
                        str7 = str6;
                        str8 = str11;
                        break;
                    case 1094496948:
                        if (str.equals("replace")) {
                            c2 = 6;
                            str9 = "charAt";
                            str7 = str4;
                            str5 = "toString";
                            str8 = str9;
                            break;
                        }
                        str12 = "charAt";
                        str6 = str4;
                        str5 = "toString";
                        str11 = str12;
                        c2 = 65535;
                        str7 = str6;
                        str8 = str11;
                        break;
                    case 1943291465:
                        if (str.equals("indexOf")) {
                            c2 = 3;
                            str9 = "charAt";
                            str7 = str4;
                            str5 = "toString";
                            str8 = str9;
                            break;
                        }
                        str12 = "charAt";
                        str6 = str4;
                        str5 = "toString";
                        str11 = str12;
                        c2 = 65535;
                        str7 = str6;
                        str8 = str11;
                        break;
                    default:
                        str12 = "charAt";
                        str6 = str4;
                        str5 = "toString";
                        str11 = str12;
                        c2 = 65535;
                        str7 = str6;
                        str8 = str11;
                        break;
                }
                String str18 = str7;
                String str19 = str8;
                switch (c2) {
                    case 0:
                        zzh.zzj(str19, 1, list);
                        int zza = !list.isEmpty() ? (int) zzh.zza(zzgVar.zzb((zzap) list.get(0)).zzh().doubleValue()) : 0;
                        String str20 = this.zza;
                        return (zza < 0 || zza >= str20.length()) ? zzap.zzm : new zzat(String.valueOf(str20.charAt(zza)));
                    case 1:
                        zzatVar = this;
                        if (!list.isEmpty()) {
                            StringBuilder sb = new StringBuilder(zzatVar.zza);
                            for (int i6 = 0; i6 < list.size(); i6++) {
                                sb.append(zzgVar.zzb((zzap) list.get(i6)).zzi());
                            }
                            return new zzat(sb.toString());
                        }
                        return zzatVar;
                    case 2:
                        zzh.zzh(str18, 1, list);
                        String str21 = this.zza;
                        zzap zzb = zzgVar.zzb((zzap) list.get(0));
                        if (!"length".equals(zzb.zzi())) {
                            double doubleValue2 = zzb.zzh().doubleValue();
                            if (doubleValue2 != Math.floor(doubleValue2) || (i2 = (int) doubleValue2) < 0 || i2 >= str21.length()) {
                                return zzap.zzl;
                            }
                        }
                        return zzap.zzk;
                    case 3:
                        zzh.zzj("indexOf", 2, list);
                        zzahVar = new zzah(Double.valueOf(this.zza.indexOf(list.size() > 0 ? zzgVar.zzb((zzap) list.get(0)).zzi() : "undefined", (int) zzh.zza(list.size() < 2 ? 0.0d : zzgVar.zzb((zzap) list.get(1)).zzh().doubleValue()))));
                        return zzahVar;
                    case 4:
                        zzh.zzj("lastIndexOf", 2, list);
                        String str22 = this.zza;
                        String zzi = list.size() > 0 ? zzgVar.zzb((zzap) list.get(0)).zzi() : "undefined";
                        zzahVar = new zzah(Double.valueOf(str22.lastIndexOf(zzi, (int) (Double.isNaN(list.size() < 2 ? Double.NaN : zzgVar.zzb((zzap) list.get(1)).zzh().doubleValue()) ? Double.POSITIVE_INFINITY : zzh.zza(doubleValue)))));
                        return zzahVar;
                    case 5:
                        zzh.zzj("match", 1, list);
                        Matcher matcher2 = Pattern.compile(list.size() <= 0 ? "" : zzgVar.zzb((zzap) list.get(0)).zzi()).matcher(this.zza);
                        return matcher2.find() ? new zzae(Arrays.asList(new zzat(matcher2.group()))) : zzap.zzg;
                    case 6:
                        zzatVar = this;
                        zzh.zzj("replace", 2, list);
                        zzap zzapVar = zzap.zzf;
                        if (!list.isEmpty()) {
                            str10 = zzgVar.zzb((zzap) list.get(0)).zzi();
                            if (list.size() > 1) {
                                zzapVar = zzgVar.zzb((zzap) list.get(1));
                            }
                        }
                        String str23 = str10;
                        String str24 = zzatVar.zza;
                        int indexOf = str24.indexOf(str23);
                        if (indexOf >= 0) {
                            if (zzapVar instanceof zzai) {
                                zzapVar = ((zzai) zzapVar).zza(zzgVar, Arrays.asList(new zzat(str23), new zzah(Double.valueOf(indexOf)), zzatVar));
                            }
                            zzahVar = new zzat(str24.substring(0, indexOf) + zzapVar.zzi() + str24.substring(indexOf + str23.length()));
                            return zzahVar;
                        }
                        return zzatVar;
                    case 7:
                        zzh.zzj(FirebaseAnalytics.Event.SEARCH, 1, list);
                        return Pattern.compile(list.isEmpty() ? "undefined" : zzgVar.zzb((zzap) list.get(0)).zzi()).matcher(this.zza).find() ? new zzah(Double.valueOf(matcher.start())) : new zzah(Double.valueOf(-1.0d));
                    case '\b':
                        zzh.zzj("slice", 2, list);
                        String str25 = this.zza;
                        double zza2 = zzh.zza(!list.isEmpty() ? zzgVar.zzb((zzap) list.get(0)).zzh().doubleValue() : 0.0d);
                        int max = (int) (zza2 < 0.0d ? Math.max(str25.length() + zza2, 0.0d) : Math.min(zza2, str25.length()));
                        double zza3 = zzh.zza(list.size() > 1 ? zzgVar.zzb((zzap) list.get(1)).zzh().doubleValue() : str25.length());
                        zzahVar = new zzat(str25.substring(max, Math.max(0, ((int) (zza3 < 0.0d ? Math.max(str25.length() + zza3, 0.0d) : Math.min(zza3, str25.length()))) - max) + max));
                        return zzahVar;
                    case '\t':
                        zzh.zzj("split", 2, list);
                        String str26 = this.zza;
                        if (str26.length() == 0) {
                            return new zzae(Arrays.asList(this));
                        }
                        ArrayList arrayList = new ArrayList();
                        if (list.isEmpty()) {
                            arrayList.add(this);
                        } else {
                            String zzi2 = zzgVar.zzb((zzap) list.get(0)).zzi();
                            long zzd = list.size() > 1 ? zzh.zzd(zzgVar.zzb((zzap) list.get(1)).zzh().doubleValue()) : 2147483647L;
                            if (zzd == 0) {
                                return new zzae();
                            }
                            String[] split = str26.split(Pattern.quote(zzi2), ((int) zzd) + 1);
                            int length = split.length;
                            if (!zzi2.isEmpty() || length <= 0) {
                                i3 = length;
                                i4 = 0;
                            } else {
                                boolean isEmpty = split[0].isEmpty();
                                i3 = length - 1;
                                i4 = isEmpty;
                                if (!split[i3].isEmpty()) {
                                    i3 = length;
                                    i4 = isEmpty;
                                }
                            }
                            if (length > zzd) {
                                i3--;
                            }
                            while (i4 < i3) {
                                arrayList.add(new zzat(split[i4]));
                                i4++;
                            }
                        }
                        return new zzae(arrayList);
                    case '\n':
                        zzh.zzj("substring", 2, list);
                        String str27 = this.zza;
                        if (list.isEmpty()) {
                            zzgVar2 = zzgVar;
                            i5 = 0;
                        } else {
                            zzgVar2 = zzgVar;
                            i5 = (int) zzh.zza(zzgVar2.zzb((zzap) list.get(0)).zzh().doubleValue());
                        }
                        int zza4 = list.size() > 1 ? (int) zzh.zza(zzgVar2.zzb((zzap) list.get(1)).zzh().doubleValue()) : str27.length();
                        int min = Math.min(Math.max(i5, 0), str27.length());
                        int min2 = Math.min(Math.max(zza4, 0), str27.length());
                        zzahVar = new zzat(str27.substring(Math.min(min, min2), Math.max(min, min2)));
                        return zzahVar;
                    case 11:
                        zzh.zzh(str2, 0, list);
                        return new zzat(this.zza.toUpperCase());
                    case '\f':
                        zzh.zzh("toLocaleLowerCase", 0, list);
                        return new zzat(this.zza.toLowerCase());
                    case '\r':
                        zzh.zzh("toLowerCase", 0, list);
                        return new zzat(this.zza.toLowerCase(Locale.ENGLISH));
                    case 14:
                        zzatVar = this;
                        zzh.zzh(str5, 0, list);
                        return zzatVar;
                    case 15:
                        zzh.zzh(str3, 0, list);
                        return new zzat(this.zza.toUpperCase(Locale.ENGLISH));
                    case 16:
                        zzh.zzh(str3, 0, list);
                        return new zzat(this.zza.trim());
                    default:
                        throw new IllegalArgumentException("Command not supported");
                }
            }
        }
        str4 = "hasOwnProperty";
        switch (str.hashCode()) {
            case -1789698943:
                break;
            case -1776922004:
                break;
            case -1464939364:
                break;
            case -1361633751:
                break;
            case -1354795244:
                break;
            case -1137582698:
                break;
            case -906336856:
                break;
            case -726908483:
                break;
            case -467511597:
                break;
            case -399551817:
                break;
            case 3568674:
                break;
            case 103668165:
                break;
            case 109526418:
                break;
            case 109648666:
                break;
            case 530542161:
                break;
            case 1094496948:
                break;
            case 1943291465:
                break;
        }
        String str182 = str7;
        String str192 = str8;
        switch (c2) {
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final zzap zzd() {
        return new zzat(this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Boolean zzg() {
        return Boolean.valueOf(!this.zza.isEmpty());
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Double zzh() {
        double d2;
        if (this.zza.isEmpty()) {
            d2 = 0.0d;
        } else {
            try {
                return Double.valueOf(this.zza);
            } catch (NumberFormatException unused) {
                d2 = Double.NaN;
            }
        }
        return Double.valueOf(d2);
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final String zzi() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Iterator zzl() {
        return new zzar(this);
    }
}
