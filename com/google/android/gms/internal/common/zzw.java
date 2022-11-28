package com.google.android.gms.internal.common;

import javax.annotation.CheckForNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class zzw extends zzj<String> {

    /* renamed from: a  reason: collision with root package name */
    final CharSequence f5859a;

    /* renamed from: b  reason: collision with root package name */
    final boolean f5860b;

    /* renamed from: c  reason: collision with root package name */
    int f5861c = 0;

    /* renamed from: d  reason: collision with root package name */
    int f5862d;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzw(zzx zzxVar, CharSequence charSequence) {
        boolean z;
        zzo unused;
        unused = zzxVar.zza;
        z = zzxVar.zzb;
        this.f5860b = z;
        this.f5862d = Integer.MAX_VALUE;
        this.f5859a = charSequence;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x002e, code lost:
        if (r0 >= r1) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0030, code lost:
        r5.f5859a.charAt(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0035, code lost:
        if (r0 >= r1) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0037, code lost:
        r5.f5859a.charAt(r1 - 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0040, code lost:
        if (r5.f5860b == false) goto L33;
     */
    @Override // com.google.android.gms.internal.common.zzj
    @CheckForNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected final /* bridge */ /* synthetic */ Object a() {
        int i2;
        int d2;
        int c2;
        do {
            i2 = this.f5861c;
            while (true) {
                int i3 = this.f5861c;
                if (i3 == -1) {
                    b();
                    return null;
                }
                d2 = d(i3);
                if (d2 == -1) {
                    d2 = this.f5859a.length();
                    this.f5861c = -1;
                    c2 = -1;
                } else {
                    c2 = c(d2);
                    this.f5861c = c2;
                }
                if (c2 != i2) {
                    break;
                }
                int i4 = c2 + 1;
                this.f5861c = i4;
                if (i4 > this.f5859a.length()) {
                    this.f5861c = -1;
                }
            }
        } while (i2 == d2);
        int i5 = this.f5862d;
        if (i5 == 1) {
            d2 = this.f5859a.length();
            this.f5861c = -1;
            if (d2 > i2) {
                this.f5859a.charAt(d2 - 1);
            }
        } else {
            this.f5862d = i5 - 1;
        }
        return this.f5859a.subSequence(i2, d2).toString();
    }

    abstract int c(int i2);

    abstract int d(int i2);
}
