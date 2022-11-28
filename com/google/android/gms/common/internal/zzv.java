package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.Parcel;
/* loaded from: classes.dex */
public final class zzv extends com.google.android.gms.internal.common.zza implements IAccountAccessor {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzv(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.IAccountAccessor");
    }

    @Override // com.google.android.gms.common.internal.IAccountAccessor
    public final Account zzb() {
        Parcel a2 = a(2, d());
        Account account = (Account) com.google.android.gms.internal.common.zzc.zza(a2, Account.CREATOR);
        a2.recycle();
        return account;
    }
}
