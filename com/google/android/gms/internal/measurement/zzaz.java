package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class zzaz extends zzaw {
    /* JADX INFO: Access modifiers changed from: protected */
    public zzaz() {
        this.f5933a.add(zzbl.APPLY);
        this.f5933a.add(zzbl.BLOCK);
        this.f5933a.add(zzbl.BREAK);
        this.f5933a.add(zzbl.CASE);
        this.f5933a.add(zzbl.DEFAULT);
        this.f5933a.add(zzbl.CONTINUE);
        this.f5933a.add(zzbl.DEFINE_FUNCTION);
        this.f5933a.add(zzbl.FN);
        this.f5933a.add(zzbl.IF);
        this.f5933a.add(zzbl.QUOTE);
        this.f5933a.add(zzbl.RETURN);
        this.f5933a.add(zzbl.SWITCH);
        this.f5933a.add(zzbl.TERNARY);
    }

    private static zzap zzc(zzg zzgVar, List list) {
        zzh.zzi(zzbl.FN.name(), 2, list);
        zzap zzb = zzgVar.zzb((zzap) list.get(0));
        zzap zzb2 = zzgVar.zzb((zzap) list.get(1));
        if (zzb2 instanceof zzae) {
            List zzm = ((zzae) zzb2).zzm();
            List arrayList = new ArrayList();
            if (list.size() > 2) {
                arrayList = list.subList(2, list.size());
            }
            return new zzao(zzb.zzi(), zzm, arrayList, zzgVar);
        }
        throw new IllegalArgumentException(String.format("FN requires an ArrayValue of parameter names found %s", zzb2.getClass().getCanonicalName()));
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x0123, code lost:
        if (r8.equals("continue") == false) goto L67;
     */
    @Override // com.google.android.gms.internal.measurement.zzaw
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzap zza(String str, zzg zzgVar, List list) {
        zzap zzb;
        zzbl zzblVar = zzbl.ADD;
        int ordinal = zzh.zze(str).ordinal();
        if (ordinal == 2) {
            zzh.zzh(zzbl.APPLY.name(), 3, list);
            zzap zzb2 = zzgVar.zzb((zzap) list.get(0));
            String zzi = zzgVar.zzb((zzap) list.get(1)).zzi();
            zzap zzb3 = zzgVar.zzb((zzap) list.get(2));
            if (zzb3 instanceof zzae) {
                if (zzi.isEmpty()) {
                    throw new IllegalArgumentException("Function name for apply is undefined");
                }
                return zzb2.zzbQ(zzi, zzgVar, ((zzae) zzb3).zzm());
            }
            throw new IllegalArgumentException(String.format("Function arguments for Apply are not a list found %s", zzb3.getClass().getCanonicalName()));
        } else if (ordinal == 15) {
            zzh.zzh(zzbl.BREAK.name(), 0, list);
            return zzap.zzh;
        } else if (ordinal != 25) {
            if (ordinal == 41) {
                zzh.zzi(zzbl.IF.name(), 2, list);
                zzap zzb4 = zzgVar.zzb((zzap) list.get(0));
                zzap zzb5 = zzgVar.zzb((zzap) list.get(1));
                zzap zzb6 = list.size() > 2 ? zzgVar.zzb((zzap) list.get(2)) : null;
                zzap zzapVar = zzap.zzf;
                zzap zzc = zzb4.zzg().booleanValue() ? zzgVar.zzc((zzae) zzb5) : zzb6 != null ? zzgVar.zzc((zzae) zzb6) : zzapVar;
                return zzc instanceof zzag ? zzc : zzapVar;
            } else if (ordinal != 54) {
                if (ordinal == 57) {
                    if (list.isEmpty()) {
                        return zzap.zzj;
                    }
                    zzh.zzh(zzbl.RETURN.name(), 1, list);
                    return new zzag("return", zzgVar.zzb((zzap) list.get(0)));
                }
                if (ordinal != 19) {
                    if (ordinal == 20) {
                        zzh.zzi(zzbl.DEFINE_FUNCTION.name(), 2, list);
                        zzap zzc2 = zzc(zzgVar, list);
                        zzai zzaiVar = (zzai) zzc2;
                        zzgVar.zzg(zzaiVar.zzc() == null ? "" : zzaiVar.zzc(), zzc2);
                        return zzc2;
                    } else if (ordinal == 60) {
                        zzh.zzh(zzbl.SWITCH.name(), 3, list);
                        zzap zzb7 = zzgVar.zzb((zzap) list.get(0));
                        zzap zzb8 = zzgVar.zzb((zzap) list.get(1));
                        zzap zzb9 = zzgVar.zzb((zzap) list.get(2));
                        if (zzb8 instanceof zzae) {
                            if (zzb9 instanceof zzae) {
                                zzae zzaeVar = (zzae) zzb8;
                                zzae zzaeVar2 = (zzae) zzb9;
                                int i2 = 0;
                                boolean z = false;
                                while (true) {
                                    if (i2 >= zzaeVar.zzc()) {
                                        if (zzaeVar.zzc() + 1 == zzaeVar2.zzc()) {
                                            zzb = zzgVar.zzb(zzaeVar2.zze(zzaeVar.zzc()));
                                            if (zzb instanceof zzag) {
                                                String zzc3 = ((zzag) zzb).zzc();
                                                if (!zzc3.equals("return")) {
                                                }
                                            }
                                        }
                                        return zzap.zzf;
                                    }
                                    if (z || zzb7.equals(zzgVar.zzb(zzaeVar.zze(i2)))) {
                                        zzb = zzgVar.zzb(zzaeVar2.zze(i2));
                                        if (!(zzb instanceof zzag)) {
                                            z = true;
                                        } else if (((zzag) zzb).zzc().equals("break")) {
                                            return zzap.zzf;
                                        }
                                    } else {
                                        z = false;
                                    }
                                    i2++;
                                }
                                return zzb;
                            }
                            throw new IllegalArgumentException("Malformed SWITCH statement, case statements are not a list");
                        }
                        throw new IllegalArgumentException("Malformed SWITCH statement, cases are not a list");
                    } else if (ordinal == 61) {
                        zzh.zzh(zzbl.TERNARY.name(), 3, list);
                        return zzgVar.zzb((zzap) (zzgVar.zzb((zzap) list.get(0)).zzg().booleanValue() ? list.get(1) : list.get(2)));
                    } else {
                        switch (ordinal) {
                            case 11:
                                return zzgVar.zza().zzc(new zzae(list));
                            case 12:
                                zzh.zzh(zzbl.BREAK.name(), 0, list);
                                return zzap.zzi;
                            case 13:
                                break;
                            default:
                                return super.a(str);
                        }
                    }
                }
                if (!list.isEmpty()) {
                    zzap zzb10 = zzgVar.zzb((zzap) list.get(0));
                    if (zzb10 instanceof zzae) {
                        return zzgVar.zzc((zzae) zzb10);
                    }
                }
                return zzap.zzf;
            } else {
                return new zzae(list);
            }
        } else {
            return zzc(zzgVar, list);
        }
    }
}
