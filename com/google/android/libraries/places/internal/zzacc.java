package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzacb;
import com.google.android.libraries.places.internal.zzacc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public abstract class zzacc<MessageType extends zzacc<MessageType, BuilderType>, BuilderType extends zzacb<MessageType, BuilderType>> implements zzaer {
    protected int zza = 0;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public static void zzt(Iterable iterable, List list) {
        zzads.zze(iterable);
        if (list instanceof ArrayList) {
            ((ArrayList) list).ensureCapacity(list.size() + iterable.size());
        }
        int size = list.size();
        for (Object obj : iterable) {
            if (obj == null) {
                int size2 = list.size();
                StringBuilder sb = new StringBuilder(37);
                sb.append("Element at index ");
                sb.append(size2 - size);
                sb.append(" is null.");
                String sb2 = sb.toString();
                int size3 = list.size();
                while (true) {
                    size3--;
                    if (size3 < size) {
                        break;
                    }
                    list.remove(size3);
                }
                throw new NullPointerException(sb2);
            }
            list.add(obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int zzr() {
        throw null;
    }

    @Override // com.google.android.libraries.places.internal.zzaer
    public final zzacp zzs() {
        try {
            int zzv = zzv();
            zzacp zzacpVar = zzacp.zzb;
            byte[] bArr = new byte[zzv];
            zzacx zzC = zzacx.zzC(bArr);
            zzH(zzC);
            zzC.zzD();
            return new zzacm(bArr);
        } catch (IOException e2) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(name.length() + 72);
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ByteString threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void zzu(int i2) {
        throw null;
    }
}
