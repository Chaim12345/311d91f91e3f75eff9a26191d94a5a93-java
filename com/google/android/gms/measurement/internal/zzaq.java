package com.google.android.gms.measurement.internal;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import androidx.annotation.WorkerThread;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.internal.AccountType;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.apache.commons.cli.HelpFormatter;
/* loaded from: classes2.dex */
public final class zzaq extends zzhe {
    private long zza;
    private String zzb;
    private AccountManager zzc;
    private Boolean zzd;
    private long zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaq(zzgk zzgkVar) {
        super(zzgkVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzhe
    protected final boolean b() {
        Calendar calendar = Calendar.getInstance();
        this.zza = TimeUnit.MINUTES.convert(calendar.get(15) + calendar.get(16), TimeUnit.MILLISECONDS);
        Locale locale = Locale.getDefault();
        String language = locale.getLanguage();
        Locale locale2 = Locale.ENGLISH;
        String lowerCase = language.toLowerCase(locale2);
        String lowerCase2 = locale.getCountry().toLowerCase(locale2);
        this.zzb = lowerCase + HelpFormatter.DEFAULT_OPT_PREFIX + lowerCase2;
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final long e() {
        zzg();
        return this.zze;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final void f() {
        zzg();
        this.zzd = null;
        this.zze = 0L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final boolean g() {
        zzg();
        long currentTimeMillis = this.f6809a.zzav().currentTimeMillis();
        if (currentTimeMillis - this.zze > 86400000) {
            this.zzd = null;
        }
        Boolean bool = this.zzd;
        if (bool == null) {
            if (ContextCompat.checkSelfPermission(this.f6809a.zzau(), "android.permission.GET_ACCOUNTS") != 0) {
                this.f6809a.zzay().zzm().zza("Permission error checking for dasher/unicorn accounts");
            } else {
                if (this.zzc == null) {
                    this.zzc = AccountManager.get(this.f6809a.zzau());
                }
                try {
                    Account[] result = this.zzc.getAccountsByTypeAndFeatures(AccountType.GOOGLE, new String[]{"service_HOSTED"}, null, null).getResult();
                    if (result != null && result.length > 0) {
                        this.zzd = Boolean.TRUE;
                        this.zze = currentTimeMillis;
                        return true;
                    }
                    Account[] result2 = this.zzc.getAccountsByTypeAndFeatures(AccountType.GOOGLE, new String[]{"service_uca"}, null, null).getResult();
                    if (result2 != null && result2.length > 0) {
                        this.zzd = Boolean.TRUE;
                        this.zze = currentTimeMillis;
                        return true;
                    }
                } catch (AuthenticatorException | OperationCanceledException | IOException e2) {
                    this.f6809a.zzay().zzh().zzb("Exception checking account types", e2);
                }
            }
            this.zze = currentTimeMillis;
            this.zzd = Boolean.FALSE;
            return false;
        }
        return bool.booleanValue();
    }

    public final long zzb() {
        c();
        return this.zza;
    }

    public final String zzc() {
        c();
        return this.zzb;
    }
}
