package com.chuckerteam.chucker.internal.ui.transaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Observer;
import com.chuckerteam.chucker.R;
import com.chuckerteam.chucker.databinding.ChuckerFragmentTransactionOverviewBinding;
import com.chuckerteam.chucker.internal.data.entity.HttpTransaction;
import com.chuckerteam.chucker.internal.support.LiveDataUtilsKt;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b \u0010!J\u001a\u0010\u0007\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0012\u0010\n\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016J&\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016J\u0018\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u0013H\u0016J\u001a\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u000f2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016R\u001d\u0010\u001c\u001a\u00020\u00178B@\u0002X\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001e\u001a\u00020\u001d8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u001e\u0010\u001f¨\u0006\""}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionOverviewFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;", "transaction", "", "encodeUrl", "", "populateUI", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/view/View;", "onCreateView", "Landroid/view/Menu;", "menu", "Landroid/view/MenuInflater;", "onCreateOptionsMenu", "view", "onViewCreated", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "getViewModel", "()Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionViewModel;", "viewModel", "Lcom/chuckerteam/chucker/databinding/ChuckerFragmentTransactionOverviewBinding;", "overviewBinding", "Lcom/chuckerteam/chucker/databinding/ChuckerFragmentTransactionOverviewBinding;", "<init>", "()V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class TransactionOverviewFragment extends Fragment {
    private ChuckerFragmentTransactionOverviewBinding overviewBinding;
    private final Lazy viewModel$delegate;

    public TransactionOverviewFragment() {
        Function0 function0 = TransactionOverviewFragment$viewModel$2.INSTANCE;
        this.viewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(TransactionViewModel.class), new TransactionOverviewFragment$$special$$inlined$activityViewModels$1(this), function0 == null ? new TransactionOverviewFragment$$special$$inlined$activityViewModels$2(this) : function0);
    }

    private final TransactionViewModel getViewModel() {
        return (TransactionViewModel) this.viewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void populateUI(HttpTransaction httpTransaction, boolean z) {
        TextView textView;
        int i2;
        ChuckerFragmentTransactionOverviewBinding chuckerFragmentTransactionOverviewBinding = this.overviewBinding;
        if (chuckerFragmentTransactionOverviewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overviewBinding");
        }
        TextView url = chuckerFragmentTransactionOverviewBinding.url;
        Intrinsics.checkNotNullExpressionValue(url, "url");
        url.setText(httpTransaction != null ? httpTransaction.getFormattedUrl(z) : null);
        TextView method = chuckerFragmentTransactionOverviewBinding.method;
        Intrinsics.checkNotNullExpressionValue(method, "method");
        method.setText(httpTransaction != null ? httpTransaction.getMethod() : null);
        TextView protocol = chuckerFragmentTransactionOverviewBinding.protocol;
        Intrinsics.checkNotNullExpressionValue(protocol, "protocol");
        protocol.setText(httpTransaction != null ? httpTransaction.getProtocol() : null);
        TextView status = chuckerFragmentTransactionOverviewBinding.status;
        Intrinsics.checkNotNullExpressionValue(status, "status");
        status.setText(String.valueOf(httpTransaction != null ? httpTransaction.getStatus() : null));
        TextView response = chuckerFragmentTransactionOverviewBinding.response;
        Intrinsics.checkNotNullExpressionValue(response, "response");
        response.setText(httpTransaction != null ? httpTransaction.getResponseSummaryText() : null);
        Boolean valueOf = httpTransaction != null ? Boolean.valueOf(httpTransaction.isSsl()) : null;
        if (valueOf == null) {
            Group sslGroup = chuckerFragmentTransactionOverviewBinding.sslGroup;
            Intrinsics.checkNotNullExpressionValue(sslGroup, "sslGroup");
            sslGroup.setVisibility(8);
        } else {
            if (Intrinsics.areEqual(valueOf, Boolean.TRUE)) {
                Group sslGroup2 = chuckerFragmentTransactionOverviewBinding.sslGroup;
                Intrinsics.checkNotNullExpressionValue(sslGroup2, "sslGroup");
                sslGroup2.setVisibility(0);
                textView = chuckerFragmentTransactionOverviewBinding.sslValue;
                i2 = R.string.chucker_yes;
            } else {
                Group sslGroup3 = chuckerFragmentTransactionOverviewBinding.sslGroup;
                Intrinsics.checkNotNullExpressionValue(sslGroup3, "sslGroup");
                sslGroup3.setVisibility(0);
                textView = chuckerFragmentTransactionOverviewBinding.sslValue;
                i2 = R.string.chucker_no;
            }
            textView.setText(i2);
        }
        if ((httpTransaction != null ? httpTransaction.getResponseTlsVersion() : null) != null) {
            TextView tlsVersionValue = chuckerFragmentTransactionOverviewBinding.tlsVersionValue;
            Intrinsics.checkNotNullExpressionValue(tlsVersionValue, "tlsVersionValue");
            tlsVersionValue.setText(httpTransaction.getResponseTlsVersion());
            Group tlsGroup = chuckerFragmentTransactionOverviewBinding.tlsGroup;
            Intrinsics.checkNotNullExpressionValue(tlsGroup, "tlsGroup");
            tlsGroup.setVisibility(0);
        }
        if ((httpTransaction != null ? httpTransaction.getResponseCipherSuite() : null) != null) {
            TextView cipherSuiteValue = chuckerFragmentTransactionOverviewBinding.cipherSuiteValue;
            Intrinsics.checkNotNullExpressionValue(cipherSuiteValue, "cipherSuiteValue");
            cipherSuiteValue.setText(httpTransaction.getResponseCipherSuite());
            Group cipherSuiteGroup = chuckerFragmentTransactionOverviewBinding.cipherSuiteGroup;
            Intrinsics.checkNotNullExpressionValue(cipherSuiteGroup, "cipherSuiteGroup");
            cipherSuiteGroup.setVisibility(0);
        }
        TextView requestTime = chuckerFragmentTransactionOverviewBinding.requestTime;
        Intrinsics.checkNotNullExpressionValue(requestTime, "requestTime");
        requestTime.setText(httpTransaction != null ? httpTransaction.getRequestDateString() : null);
        TextView responseTime = chuckerFragmentTransactionOverviewBinding.responseTime;
        Intrinsics.checkNotNullExpressionValue(responseTime, "responseTime");
        responseTime.setText(httpTransaction != null ? httpTransaction.getResponseDateString() : null);
        TextView duration = chuckerFragmentTransactionOverviewBinding.duration;
        Intrinsics.checkNotNullExpressionValue(duration, "duration");
        duration.setText(httpTransaction != null ? httpTransaction.getDurationString() : null);
        TextView requestSize = chuckerFragmentTransactionOverviewBinding.requestSize;
        Intrinsics.checkNotNullExpressionValue(requestSize, "requestSize");
        requestSize.setText(httpTransaction != null ? httpTransaction.getRequestSizeString() : null);
        TextView responseSize = chuckerFragmentTransactionOverviewBinding.responseSize;
        Intrinsics.checkNotNullExpressionValue(responseSize, "responseSize");
        responseSize.setText(httpTransaction != null ? httpTransaction.getResponseSizeString() : null);
        TextView totalSize = chuckerFragmentTransactionOverviewBinding.totalSize;
        Intrinsics.checkNotNullExpressionValue(totalSize, "totalSize");
        totalSize.setText(httpTransaction != null ? httpTransaction.getTotalSizeString() : null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(@NotNull final Menu menu, @NotNull MenuInflater inflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        MenuItem findItem = menu.findItem(R.id.save_body);
        Intrinsics.checkNotNullExpressionValue(findItem, "menu.findItem(R.id.save_body)");
        findItem.setVisible(false);
        getViewModel().getDoesUrlRequireEncoding().observe(getViewLifecycleOwner(), new Observer<Boolean>() { // from class: com.chuckerteam.chucker.internal.ui.transaction.TransactionOverviewFragment$onCreateOptionsMenu$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean it) {
                MenuItem findItem2 = menu.findItem(R.id.encode_url);
                Intrinsics.checkNotNullExpressionValue(findItem2, "menu.findItem(R.id.encode_url)");
                Intrinsics.checkNotNullExpressionValue(it, "it");
                findItem2.setVisible(it.booleanValue());
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ChuckerFragmentTransactionOverviewBinding inflate = ChuckerFragmentTransactionOverviewBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "ChuckerFragmentTransacti…flater, container, false)");
        this.overviewBinding = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("overviewBinding");
        }
        return inflate.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        LiveDataUtilsKt.combineLatest(getViewModel().getTransaction(), getViewModel().getEncodeUrl()).observe(getViewLifecycleOwner(), new Observer<Pair<? extends HttpTransaction, ? extends Boolean>>() { // from class: com.chuckerteam.chucker.internal.ui.transaction.TransactionOverviewFragment$onViewCreated$1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(Pair<? extends HttpTransaction, ? extends Boolean> pair) {
                onChanged2((Pair<HttpTransaction, Boolean>) pair);
            }

            /* renamed from: onChanged  reason: avoid collision after fix types in other method */
            public final void onChanged2(Pair<HttpTransaction, Boolean> pair) {
                boolean booleanValue = pair.component2().booleanValue();
                TransactionOverviewFragment.this.populateUI(pair.component1(), booleanValue);
            }
        });
    }
}
