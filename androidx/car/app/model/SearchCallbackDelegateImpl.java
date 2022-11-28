package androidx.car.app.model;

import android.annotation.SuppressLint;
import android.os.RemoteException;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.model.ISearchCallback;
import androidx.car.app.model.SearchCallbackDelegateImpl;
import androidx.car.app.model.SearchTemplate;
import androidx.car.app.utils.RemoteUtils;
import java.util.Objects;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class SearchCallbackDelegateImpl implements SearchCallbackDelegate {
    @Nullable
    @Keep
    private final ISearchCallback mStubCallback;

    /* JADX INFO: Access modifiers changed from: private */
    @Keep
    /* loaded from: classes.dex */
    public static class SearchCallbackStub extends ISearchCallback.Stub {
        private final SearchTemplate.SearchCallback mCallback;

        SearchCallbackStub(SearchTemplate.SearchCallback searchCallback) {
            this.mCallback = searchCallback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object lambda$onSearchSubmitted$1(String str) {
            this.mCallback.onSearchSubmitted(str);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object lambda$onSearchTextChanged$0(String str) {
            this.mCallback.onSearchTextChanged(str);
            return null;
        }

        @Override // androidx.car.app.model.ISearchCallback
        public void onSearchSubmitted(final String str, IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(iOnDoneCallback, "onSearchSubmitted", new RemoteUtils.HostCall() { // from class: androidx.car.app.model.g
                @Override // androidx.car.app.utils.RemoteUtils.HostCall
                public final Object dispatch() {
                    Object lambda$onSearchSubmitted$1;
                    lambda$onSearchSubmitted$1 = SearchCallbackDelegateImpl.SearchCallbackStub.this.lambda$onSearchSubmitted$1(str);
                    return lambda$onSearchSubmitted$1;
                }
            });
        }

        @Override // androidx.car.app.model.ISearchCallback
        public void onSearchTextChanged(final String str, IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(iOnDoneCallback, "onSearchTextChanged", new RemoteUtils.HostCall() { // from class: androidx.car.app.model.h
                @Override // androidx.car.app.utils.RemoteUtils.HostCall
                public final Object dispatch() {
                    Object lambda$onSearchTextChanged$0;
                    lambda$onSearchTextChanged$0 = SearchCallbackDelegateImpl.SearchCallbackStub.this.lambda$onSearchTextChanged$0(str);
                    return lambda$onSearchTextChanged$0;
                }
            });
        }
    }

    private SearchCallbackDelegateImpl() {
        this.mStubCallback = null;
    }

    private SearchCallbackDelegateImpl(@NonNull SearchTemplate.SearchCallback searchCallback) {
        this.mStubCallback = new SearchCallbackStub(searchCallback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @SuppressLint({"ExecutorRegistration"})
    public static SearchCallbackDelegate create(@NonNull SearchTemplate.SearchCallback searchCallback) {
        return new SearchCallbackDelegateImpl(searchCallback);
    }

    @Override // androidx.car.app.model.SearchCallbackDelegate
    public void sendSearchSubmitted(@NonNull String str, @NonNull OnDoneCallback onDoneCallback) {
        try {
            ISearchCallback iSearchCallback = this.mStubCallback;
            Objects.requireNonNull(iSearchCallback);
            iSearchCallback.onSearchSubmitted(str, RemoteUtils.createOnDoneCallbackStub(onDoneCallback));
        } catch (RemoteException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // androidx.car.app.model.SearchCallbackDelegate
    public void sendSearchTextChanged(@NonNull String str, @NonNull OnDoneCallback onDoneCallback) {
        try {
            ISearchCallback iSearchCallback = this.mStubCallback;
            Objects.requireNonNull(iSearchCallback);
            iSearchCallback.onSearchTextChanged(str, RemoteUtils.createOnDoneCallbackStub(onDoneCallback));
        } catch (RemoteException e2) {
            throw new RuntimeException(e2);
        }
    }
}
