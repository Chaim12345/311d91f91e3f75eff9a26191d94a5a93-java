package com.google.android.gms.internal.common;

import java.io.IOException;
import java.util.Iterator;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzv implements Iterable<String> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ CharSequence f5857a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzx f5858b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzv(zzx zzxVar, CharSequence charSequence) {
        this.f5858b = zzxVar;
        this.f5857a = charSequence;
    }

    @Override // java.lang.Iterable
    public final Iterator<String> iterator() {
        Iterator<String> zzh;
        zzh = this.f5858b.zzh(this.f5857a);
        return zzh;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(AbstractJsonLexerKt.BEGIN_LIST);
        Iterator<String> it = iterator();
        try {
            if (it.hasNext()) {
                CharSequence a2 = zzq.a(it.next(), ", ");
                while (true) {
                    sb.append(a2);
                    if (!it.hasNext()) {
                        break;
                    }
                    sb.append((CharSequence) ", ");
                    a2 = zzq.a(it.next(), ", ");
                }
            }
            sb.append(AbstractJsonLexerKt.END_LIST);
            return sb.toString();
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }
}
