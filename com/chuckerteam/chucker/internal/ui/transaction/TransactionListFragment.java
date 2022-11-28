package com.chuckerteam.chucker.internal.ui.transaction;

import android.content.Context;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import com.chuckerteam.chucker.R;
import com.chuckerteam.chucker.databinding.ChuckerFragmentTransactionListBinding;
import com.chuckerteam.chucker.internal.data.entity.HttpTransactionTuple;
import com.chuckerteam.chucker.internal.data.model.DialogData;
import com.chuckerteam.chucker.internal.support.ContextExtKt;
import com.chuckerteam.chucker.internal.ui.MainViewModel;
import com.chuckerteam.chucker.internal.ui.transaction.TransactionActivity;
import com.chuckerteam.chucker.internal.ui.transaction.TransactionAdapter;
import com.google.android.gms.actions.SearchIntents;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import java.util.List;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 62\u00020\u00012\u00020\u00022\u00020\u0003:\u00016B\u0007¢\u0006\u0004\b4\u00105J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\b\u0010\t\u001a\u00020\bH\u0002J\b\u0010\u000b\u001a\u00020\nH\u0002J\b\u0010\f\u001a\u00020\nH\u0002J\u0012\u0010\u000f\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0016J&\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0016J\u001a\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00142\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0016J\u0018\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0018H\u0016J\u0010\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001aH\u0016J\u0010\u0010 \u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u001eH\u0016J\u0010\u0010\"\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u001eH\u0016J\u0018\u0010'\u001a\u00020\u00062\u0006\u0010$\u001a\u00020#2\u0006\u0010&\u001a\u00020%H\u0016R\u001d\u0010-\u001a\u00020(8B@\u0002X\u0082\u0084\u0002¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,R\u0016\u0010/\u001a\u00020.8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b/\u00100R\u0016\u00102\u001a\u0002018\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b2\u00103¨\u00067"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionListFragment;", "Landroidx/fragment/app/Fragment;", "Landroidx/appcompat/widget/SearchView$OnQueryTextListener;", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionAdapter$TransactionClickListListener;", "Landroid/view/Menu;", "menu", "", "setUpSearch", "Lkotlinx/coroutines/Job;", "exportTransactions", "Lcom/chuckerteam/chucker/internal/data/model/DialogData;", "getClearDialogData", "getExportDialogData", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/view/View;", "onCreateView", "view", "onViewCreated", "Landroid/view/MenuInflater;", "onCreateOptionsMenu", "Landroid/view/MenuItem;", "item", "", "onOptionsItemSelected", "", SearchIntents.EXTRA_QUERY, "onQueryTextSubmit", "newText", "onQueryTextChange", "", "transactionId", "", AppConstants.ARG_POSITION, "onTransactionClick", "Lcom/chuckerteam/chucker/internal/ui/MainViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "getViewModel", "()Lcom/chuckerteam/chucker/internal/ui/MainViewModel;", "viewModel", "Lcom/chuckerteam/chucker/databinding/ChuckerFragmentTransactionListBinding;", "transactionsBinding", "Lcom/chuckerteam/chucker/databinding/ChuckerFragmentTransactionListBinding;", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionAdapter;", "transactionsAdapter", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionAdapter;", "<init>", "()V", "Companion", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class TransactionListFragment extends Fragment implements SearchView.OnQueryTextListener, TransactionAdapter.TransactionClickListListener {
    public static final Companion Companion = new Companion(null);
    private static final String EXPORT_FILE_NAME = "transactions.txt";
    private TransactionAdapter transactionsAdapter;
    private ChuckerFragmentTransactionListBinding transactionsBinding;
    private final Lazy viewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(MainViewModel.class), new TransactionListFragment$$special$$inlined$viewModels$2(new TransactionListFragment$$special$$inlined$viewModels$1(this)), null);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\u0003\u001a\u00020\u0002R\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionListFragment$Companion;", "", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionListFragment;", "newInstance", "", "EXPORT_FILE_NAME", "Ljava/lang/String;", "<init>", "()V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final TransactionListFragment newInstance() {
            return new TransactionListFragment();
        }
    }

    public static final /* synthetic */ TransactionAdapter access$getTransactionsAdapter$p(TransactionListFragment transactionListFragment) {
        TransactionAdapter transactionAdapter = transactionListFragment.transactionsAdapter;
        if (transactionAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transactionsAdapter");
        }
        return transactionAdapter;
    }

    public static final /* synthetic */ ChuckerFragmentTransactionListBinding access$getTransactionsBinding$p(TransactionListFragment transactionListFragment) {
        ChuckerFragmentTransactionListBinding chuckerFragmentTransactionListBinding = transactionListFragment.transactionsBinding;
        if (chuckerFragmentTransactionListBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transactionsBinding");
        }
        return chuckerFragmentTransactionListBinding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Job exportTransactions() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new TransactionListFragment$exportTransactions$1(this, null), 3, null);
        return launch$default;
    }

    private final DialogData getClearDialogData() {
        int i2 = R.string.chucker_clear;
        String string = getString(i2);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.chucker_clear)");
        String string2 = getString(R.string.chucker_clear_http_confirmation);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.chuck…_clear_http_confirmation)");
        return new DialogData(string, string2, getString(i2), getString(R.string.chucker_cancel));
    }

    private final DialogData getExportDialogData() {
        int i2 = R.string.chucker_export;
        String string = getString(i2);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.chucker_export)");
        String string2 = getString(R.string.chucker_export_http_confirmation);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.chuck…export_http_confirmation)");
        return new DialogData(string, string2, getString(i2), getString(R.string.chucker_cancel));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MainViewModel getViewModel() {
        return (MainViewModel) this.viewModel$delegate.getValue();
    }

    private final void setUpSearch(Menu menu) {
        MenuItem searchMenuItem = menu.findItem(R.id.search);
        Intrinsics.checkNotNullExpressionValue(searchMenuItem, "searchMenuItem");
        View actionView = searchMenuItem.getActionView();
        Objects.requireNonNull(actionView, "null cannot be cast to non-null type androidx.appcompat.widget.SearchView");
        SearchView searchView = (SearchView) actionView;
        searchView.setOnQueryTextListener(this);
        searchView.setIconifiedByDefault(true);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(@NotNull Menu menu, @NotNull MenuInflater inflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        inflater.inflate(R.menu.chucker_transactions_list, menu);
        setUpSearch(menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ChuckerFragmentTransactionListBinding inflate = ChuckerFragmentTransactionListBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "ChuckerFragmentTransacti…flater, container, false)");
        this.transactionsBinding = inflate;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.transactionsAdapter = new TransactionAdapter(requireContext, this);
        ChuckerFragmentTransactionListBinding chuckerFragmentTransactionListBinding = this.transactionsBinding;
        if (chuckerFragmentTransactionListBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transactionsBinding");
        }
        TextView tutorialLink = chuckerFragmentTransactionListBinding.tutorialLink;
        Intrinsics.checkNotNullExpressionValue(tutorialLink, "tutorialLink");
        tutorialLink.setMovementMethod(LinkMovementMethod.getInstance());
        RecyclerView recyclerView = chuckerFragmentTransactionListBinding.transactionsRecyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), 1));
        TransactionAdapter transactionAdapter = this.transactionsAdapter;
        if (transactionAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transactionsAdapter");
        }
        recyclerView.setAdapter(transactionAdapter);
        ChuckerFragmentTransactionListBinding chuckerFragmentTransactionListBinding2 = this.transactionsBinding;
        if (chuckerFragmentTransactionListBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transactionsBinding");
        }
        return chuckerFragmentTransactionListBinding2.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        Context requireContext;
        DialogData exportDialogData;
        Function0 transactionListFragment$onOptionsItemSelected$2;
        Intrinsics.checkNotNullParameter(item, "item");
        int itemId = item.getItemId();
        if (itemId == R.id.clear) {
            requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            exportDialogData = getClearDialogData();
            transactionListFragment$onOptionsItemSelected$2 = new TransactionListFragment$onOptionsItemSelected$1(this);
        } else if (itemId != R.id.export) {
            return super.onOptionsItemSelected(item);
        } else {
            requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            exportDialogData = getExportDialogData();
            transactionListFragment$onOptionsItemSelected$2 = new TransactionListFragment$onOptionsItemSelected$2(this);
        }
        ContextExtKt.showDialog(requireContext, exportDialogData, transactionListFragment$onOptionsItemSelected$2, null);
        return true;
    }

    @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
    public boolean onQueryTextChange(@NotNull String newText) {
        Intrinsics.checkNotNullParameter(newText, "newText");
        getViewModel().updateItemsFilter(newText);
        return true;
    }

    @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
    public boolean onQueryTextSubmit(@NotNull String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        return true;
    }

    @Override // com.chuckerteam.chucker.internal.ui.transaction.TransactionAdapter.TransactionClickListListener
    public void onTransactionClick(long j2, int i2) {
        TransactionActivity.Companion companion = TransactionActivity.Companion;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        companion.start(requireActivity, j2);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        getViewModel().getTransactions().observe(getViewLifecycleOwner(), new Observer<List<? extends HttpTransactionTuple>>() { // from class: com.chuckerteam.chucker.internal.ui.transaction.TransactionListFragment$onViewCreated$1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(List<? extends HttpTransactionTuple> list) {
                onChanged2((List<HttpTransactionTuple>) list);
            }

            /* renamed from: onChanged  reason: avoid collision after fix types in other method */
            public final void onChanged2(List<HttpTransactionTuple> transactionTuples) {
                TransactionAdapter access$getTransactionsAdapter$p = TransactionListFragment.access$getTransactionsAdapter$p(TransactionListFragment.this);
                Intrinsics.checkNotNullExpressionValue(transactionTuples, "transactionTuples");
                access$getTransactionsAdapter$p.setData(transactionTuples);
                LinearLayout linearLayout = TransactionListFragment.access$getTransactionsBinding$p(TransactionListFragment.this).tutorialView;
                Intrinsics.checkNotNullExpressionValue(linearLayout, "transactionsBinding.tutorialView");
                linearLayout.setVisibility(transactionTuples.isEmpty() ? 0 : 8);
            }
        });
    }
}
