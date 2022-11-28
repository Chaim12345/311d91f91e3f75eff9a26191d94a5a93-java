package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.ApiKey;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.NonGmsServiceBrokerClient;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.common.api.internal.zaae;
import com.google.android.gms.common.api.internal.zabq;
import com.google.android.gms.common.api.internal.zabv;
import com.google.android.gms.common.api.internal.zact;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;
@KeepForSdk
/* loaded from: classes.dex */
public abstract class GoogleApi<O extends Api.ApiOptions> implements HasApiKey<O> {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    protected final GoogleApiManager f5622a;
    private final Context zab;
    @Nullable
    private final String zac;
    private final Api<O> zad;
    private final O zae;
    private final ApiKey<O> zaf;
    private final Looper zag;
    private final int zah;
    @NotOnlyInitialized
    private final GoogleApiClient zai;
    private final StatusExceptionMapper zaj;

    @KeepForSdk
    /* loaded from: classes.dex */
    public static class Settings {
        @NonNull
        @KeepForSdk
        public static final Settings DEFAULT_SETTINGS = new Builder().build();
        @NonNull
        public final StatusExceptionMapper zaa;
        @NonNull
        public final Looper zab;

        @KeepForSdk
        /* loaded from: classes.dex */
        public static class Builder {
            private StatusExceptionMapper zaa;
            private Looper zab;

            @NonNull
            @KeepForSdk
            public Settings build() {
                if (this.zaa == null) {
                    this.zaa = new ApiExceptionMapper();
                }
                if (this.zab == null) {
                    this.zab = Looper.getMainLooper();
                }
                return new Settings(this.zaa, this.zab);
            }

            @NonNull
            @KeepForSdk
            public Builder setLooper(@NonNull Looper looper) {
                Preconditions.checkNotNull(looper, "Looper must not be null.");
                this.zab = looper;
                return this;
            }

            @NonNull
            @KeepForSdk
            public Builder setMapper(@NonNull StatusExceptionMapper statusExceptionMapper) {
                Preconditions.checkNotNull(statusExceptionMapper, "StatusExceptionMapper must not be null.");
                this.zaa = statusExceptionMapper;
                return this;
            }
        }

        @KeepForSdk
        private Settings(StatusExceptionMapper statusExceptionMapper, Account account, Looper looper) {
            this.zaa = statusExceptionMapper;
            this.zab = looper;
        }
    }

    @KeepForSdk
    @MainThread
    public GoogleApi(@NonNull Activity activity, @NonNull Api<O> api, @NonNull O o2, @NonNull Settings settings) {
        this(activity, activity, api, o2, settings);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    @KeepForSdk
    @Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public GoogleApi(@NonNull Activity activity, @NonNull Api<O> api, @NonNull O o2, @NonNull StatusExceptionMapper statusExceptionMapper) {
        this(activity, (Api) api, (Api.ApiOptions) o2, r0.build());
        Settings.Builder builder = new Settings.Builder();
        builder.setMapper(statusExceptionMapper);
        builder.setLooper(activity.getMainLooper());
    }

    private GoogleApi(@NonNull Context context, @Nullable Activity activity, Api<O> api, O o2, Settings settings) {
        Preconditions.checkNotNull(context, "Null context is not permitted.");
        Preconditions.checkNotNull(api, "Api must not be null.");
        Preconditions.checkNotNull(settings, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        this.zab = context.getApplicationContext();
        String str = null;
        if (PlatformVersion.isAtLeastR()) {
            try {
                str = (String) Context.class.getMethod("getAttributionTag", new Class[0]).invoke(context, new Object[0]);
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            }
        }
        this.zac = str;
        this.zad = api;
        this.zae = o2;
        this.zag = settings.zab;
        ApiKey<O> zaa = ApiKey.zaa(api, o2, str);
        this.zaf = zaa;
        this.zai = new zabv(this);
        GoogleApiManager zam = GoogleApiManager.zam(this.zab);
        this.f5622a = zam;
        this.zah = zam.zaa();
        this.zaj = settings.zaa;
        if (activity != null && !(activity instanceof GoogleApiActivity) && Looper.myLooper() == Looper.getMainLooper()) {
            zaae.zad(activity, zam, zaa);
        }
        zam.zaB(this);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    @KeepForSdk
    @Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public GoogleApi(@NonNull Context context, @NonNull Api<O> api, @NonNull O o2, @NonNull Looper looper, @NonNull StatusExceptionMapper statusExceptionMapper) {
        this(context, api, o2, r0.build());
        Settings.Builder builder = new Settings.Builder();
        builder.setLooper(looper);
        builder.setMapper(statusExceptionMapper);
    }

    @KeepForSdk
    public GoogleApi(@NonNull Context context, @NonNull Api<O> api, @NonNull O o2, @NonNull Settings settings) {
        this(context, (Activity) null, api, o2, settings);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    @KeepForSdk
    @Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public GoogleApi(@NonNull Context context, @NonNull Api<O> api, @NonNull O o2, @NonNull StatusExceptionMapper statusExceptionMapper) {
        this(context, api, o2, r0.build());
        Settings.Builder builder = new Settings.Builder();
        builder.setMapper(statusExceptionMapper);
    }

    private final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T zad(int i2, @NonNull T t2) {
        t2.zak();
        this.f5622a.zaw(this, i2, t2);
        return t2;
    }

    private final <TResult, A extends Api.AnyClient> Task<TResult> zae(int i2, @NonNull TaskApiCall<A, TResult> taskApiCall) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.f5622a.zax(this, i2, taskApiCall, taskCompletionSource, this.zaj);
        return taskCompletionSource.getTask();
    }

    @NonNull
    @KeepForSdk
    protected ClientSettings.Builder a() {
        Account account;
        GoogleSignInAccount googleSignInAccount;
        GoogleSignInAccount googleSignInAccount2;
        ClientSettings.Builder builder = new ClientSettings.Builder();
        O o2 = this.zae;
        if (!(o2 instanceof Api.ApiOptions.HasGoogleSignInAccountOptions) || (googleSignInAccount2 = ((Api.ApiOptions.HasGoogleSignInAccountOptions) o2).getGoogleSignInAccount()) == null) {
            O o3 = this.zae;
            account = o3 instanceof Api.ApiOptions.HasAccountOptions ? ((Api.ApiOptions.HasAccountOptions) o3).getAccount() : null;
        } else {
            account = googleSignInAccount2.getAccount();
        }
        builder.zab(account);
        O o4 = this.zae;
        builder.zaa((!(o4 instanceof Api.ApiOptions.HasGoogleSignInAccountOptions) || (googleSignInAccount = ((Api.ApiOptions.HasGoogleSignInAccountOptions) o4).getGoogleSignInAccount()) == null) ? Collections.emptySet() : googleSignInAccount.getRequestedScopes());
        builder.zac(this.zab.getClass().getName());
        builder.setRealClientPackageName(this.zab.getPackageName());
        return builder;
    }

    @NonNull
    @KeepForSdk
    public GoogleApiClient asGoogleApiClient() {
        return this.zai;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    @KeepForSdk
    public String b() {
        return this.zac;
    }

    @NonNull
    @KeepForSdk
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T doBestEffortWrite(@NonNull T t2) {
        zad(2, t2);
        return t2;
    }

    @NonNull
    @KeepForSdk
    public <TResult, A extends Api.AnyClient> Task<TResult> doBestEffortWrite(@NonNull TaskApiCall<A, TResult> taskApiCall) {
        return zae(2, taskApiCall);
    }

    @NonNull
    @KeepForSdk
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T doRead(@NonNull T t2) {
        zad(0, t2);
        return t2;
    }

    @NonNull
    @KeepForSdk
    public <TResult, A extends Api.AnyClient> Task<TResult> doRead(@NonNull TaskApiCall<A, TResult> taskApiCall) {
        return zae(0, taskApiCall);
    }

    @NonNull
    @KeepForSdk
    @Deprecated
    public <A extends Api.AnyClient, T extends RegisterListenerMethod<A, ?>, U extends UnregisterListenerMethod<A, ?>> Task<Void> doRegisterEventListener(@NonNull T t2, @NonNull U u) {
        Preconditions.checkNotNull(t2);
        Preconditions.checkNotNull(u);
        Preconditions.checkNotNull(t2.getListenerKey(), "Listener has already been released.");
        Preconditions.checkNotNull(u.getListenerKey(), "Listener has already been released.");
        Preconditions.checkArgument(Objects.equal(t2.getListenerKey(), u.getListenerKey()), "Listener registration and unregistration methods must be constructed with the same ListenerHolder.");
        return this.f5622a.zaq(this, t2, u, zad.zaa);
    }

    @NonNull
    @KeepForSdk
    public <A extends Api.AnyClient> Task<Void> doRegisterEventListener(@NonNull RegistrationMethods<A, ?> registrationMethods) {
        Preconditions.checkNotNull(registrationMethods);
        Preconditions.checkNotNull(registrationMethods.register.getListenerKey(), "Listener has already been released.");
        Preconditions.checkNotNull(registrationMethods.zaa.getListenerKey(), "Listener has already been released.");
        return this.f5622a.zaq(this, registrationMethods.register, registrationMethods.zaa, registrationMethods.zab);
    }

    @NonNull
    @KeepForSdk
    public Task<Boolean> doUnregisterEventListener(@NonNull ListenerHolder.ListenerKey<?> listenerKey) {
        return doUnregisterEventListener(listenerKey, 0);
    }

    @NonNull
    @KeepForSdk
    public Task<Boolean> doUnregisterEventListener(@NonNull ListenerHolder.ListenerKey<?> listenerKey, int i2) {
        Preconditions.checkNotNull(listenerKey, "Listener key cannot be null.");
        return this.f5622a.zar(this, listenerKey, i2);
    }

    @NonNull
    @KeepForSdk
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T doWrite(@NonNull T t2) {
        zad(1, t2);
        return t2;
    }

    @NonNull
    @KeepForSdk
    public <TResult, A extends Api.AnyClient> Task<TResult> doWrite(@NonNull TaskApiCall<A, TResult> taskApiCall) {
        return zae(1, taskApiCall);
    }

    @Override // com.google.android.gms.common.api.HasApiKey
    @NonNull
    public final ApiKey<O> getApiKey() {
        return this.zaf;
    }

    @NonNull
    @KeepForSdk
    public O getApiOptions() {
        return this.zae;
    }

    @NonNull
    @KeepForSdk
    public Context getApplicationContext() {
        return this.zab;
    }

    @NonNull
    @KeepForSdk
    public Looper getLooper() {
        return this.zag;
    }

    @NonNull
    @KeepForSdk
    public <L> ListenerHolder<L> registerListener(@NonNull L l2, @NonNull String str) {
        return ListenerHolders.createListenerHolder(l2, this.zag, str);
    }

    public final int zaa() {
        return this.zah;
    }

    @WorkerThread
    public final Api.Client zab(Looper looper, zabq<O> zabqVar) {
        Api.Client buildClient = ((Api.AbstractClientBuilder) Preconditions.checkNotNull(this.zad.zaa())).buildClient(this.zab, looper, a().build(), (ClientSettings) this.zae, (GoogleApiClient.ConnectionCallbacks) zabqVar, (GoogleApiClient.OnConnectionFailedListener) zabqVar);
        String b2 = b();
        if (b2 != null && (buildClient instanceof BaseGmsClient)) {
            ((BaseGmsClient) buildClient).setAttributionTag(b2);
        }
        if (b2 != null && (buildClient instanceof NonGmsServiceBrokerClient)) {
            ((NonGmsServiceBrokerClient) buildClient).zac(b2);
        }
        return buildClient;
    }

    public final zact zac(Context context, Handler handler) {
        return new zact(context, handler, a().build());
    }
}
