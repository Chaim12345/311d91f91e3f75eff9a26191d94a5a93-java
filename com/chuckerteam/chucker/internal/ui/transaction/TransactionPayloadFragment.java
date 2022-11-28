package com.chuckerteam.chucker.internal.ui.transaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.Group;
import androidx.core.content.ContextCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.chuckerteam.chucker.R;
import com.chuckerteam.chucker.databinding.ChuckerFragmentTransactionPayloadBinding;
import com.chuckerteam.chucker.internal.data.entity.HttpTransaction;
import com.chuckerteam.chucker.internal.support.LiveDataUtilsKt;
import com.google.android.gms.actions.SearchIntents;
import java.util.List;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u0000 ?2\u00020\u00012\u00020\u0002:\u0001?B\u0007¢\u0006\u0004\b=\u0010>J\b\u0010\u0004\u001a\u00020\u0003H\u0002J\b\u0010\u0005\u001a\u00020\u0003H\u0002J\u0012\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002J\u0012\u0010\n\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002J\b\u0010\u000b\u001a\u00020\u0003H\u0003J\u0012\u0010\u000e\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0016J&\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0016J\u001a\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00132\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0016J\u0018\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u0019H\u0017J\u0010\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u001bH\u0016J\"\u0010#\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001e2\b\u0010\"\u001a\u0004\u0018\u00010!H\u0016J\u0010\u0010&\u001a\u00020\b2\u0006\u0010%\u001a\u00020$H\u0016J\u0010\u0010(\u001a\u00020\b2\u0006\u0010'\u001a\u00020$H\u0016R\u001d\u0010.\u001a\u00020)8B@\u0002X\u0082\u0084\u0002¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-R\u001d\u00103\u001a\u00020/8B@\u0002X\u0082\u0084\u0002¢\u0006\f\n\u0004\b0\u0010+\u001a\u0004\b1\u00102R\u0016\u00105\u001a\u0002048\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b5\u00106R\u0016\u00108\u001a\u0002078\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b8\u00109R\u0016\u0010:\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b:\u0010;R\u0016\u0010<\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b<\u0010;¨\u0006@"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadFragment;", "Landroidx/fragment/app/Fragment;", "Landroidx/appcompat/widget/SearchView$OnQueryTextListener;", "", "showEmptyState", "showPayloadState", "Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;", "transaction", "", "shouldShowSaveIcon", "shouldShowSearchIcon", "createFileToSaveBody", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/view/View;", "onCreateView", "view", "onViewCreated", "Landroid/view/Menu;", "menu", "Landroid/view/MenuInflater;", "onCreateOptionsMenu", "Landroid/content/Context;", "context", "onAttach", "", "requestCode", "resultCode", "Landroid/content/Intent;", "resultData", "onActivityResult", "", SearchIntents.EXTRA_QUERY, "onQueryTextSubmit", "newText", "onQueryTextChange", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "getViewModel", "()Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionViewModel;", "viewModel", "Lcom/chuckerteam/chucker/internal/ui/transaction/PayloadType;", "payloadType$delegate", "getPayloadType", "()Lcom/chuckerteam/chucker/internal/ui/transaction/PayloadType;", "payloadType", "Lcom/chuckerteam/chucker/databinding/ChuckerFragmentTransactionPayloadBinding;", "payloadBinding", "Lcom/chuckerteam/chucker/databinding/ChuckerFragmentTransactionPayloadBinding;", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionBodyAdapter;", "payloadAdapter", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionBodyAdapter;", "backgroundSpanColor", "I", "foregroundSpanColor", "<init>", "()V", "Companion", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class TransactionPayloadFragment extends Fragment implements SearchView.OnQueryTextListener {
    private static final String ARG_TYPE = "type";
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String DEFAULT_FILE_PREFIX = "chucker-export-";
    private static final int NUMBER_OF_IGNORED_SYMBOLS = 1;
    private static final String TRANSACTION_EXCEPTION = "Transaction not ready";
    private int backgroundSpanColor;
    private int foregroundSpanColor;
    private final TransactionBodyAdapter payloadAdapter;
    private ChuckerFragmentTransactionPayloadBinding payloadBinding;
    private final Lazy payloadType$delegate;
    private final Lazy viewModel$delegate;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002R\u0016\u0010\u0007\u001a\u00020\u00068\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\u00020\u00068\u0006@\u0006X\u0086T¢\u0006\u0006\n\u0004\b\t\u0010\bR\u0016\u0010\u000b\u001a\u00020\n8\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\u00020\u00068\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\r\u0010\b¨\u0006\u0010"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadFragment$Companion;", "", "Lcom/chuckerteam/chucker/internal/ui/transaction/PayloadType;", TransactionPayloadFragment.ARG_TYPE, "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionPayloadFragment;", "newInstance", "", "ARG_TYPE", "Ljava/lang/String;", "DEFAULT_FILE_PREFIX", "", "NUMBER_OF_IGNORED_SYMBOLS", "I", "TRANSACTION_EXCEPTION", "<init>", "()V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final TransactionPayloadFragment newInstance(@NotNull PayloadType type) {
            Intrinsics.checkNotNullParameter(type, "type");
            TransactionPayloadFragment transactionPayloadFragment = new TransactionPayloadFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(TransactionPayloadFragment.ARG_TYPE, type);
            Unit unit = Unit.INSTANCE;
            transactionPayloadFragment.setArguments(bundle);
            return transactionPayloadFragment;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[PayloadType.values().length];
            $EnumSwitchMapping$0 = iArr;
            PayloadType payloadType = PayloadType.REQUEST;
            iArr[payloadType.ordinal()] = 1;
            PayloadType payloadType2 = PayloadType.RESPONSE;
            iArr[payloadType2.ordinal()] = 2;
            int[] iArr2 = new int[PayloadType.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[payloadType.ordinal()] = 1;
            iArr2[payloadType2.ordinal()] = 2;
        }
    }

    public TransactionPayloadFragment() {
        Lazy lazy;
        Function0 function0 = TransactionPayloadFragment$viewModel$2.INSTANCE;
        this.viewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(TransactionViewModel.class), new TransactionPayloadFragment$$special$$inlined$activityViewModels$1(this), function0 == null ? new TransactionPayloadFragment$$special$$inlined$activityViewModels$2(this) : function0);
        lazy = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new TransactionPayloadFragment$payloadType$2(this));
        this.payloadType$delegate = lazy;
        this.payloadAdapter = new TransactionBodyAdapter();
        this.backgroundSpanColor = InputDeviceCompat.SOURCE_ANY;
        this.foregroundSpanColor = SupportMenu.CATEGORY_MASK;
    }

    public static final /* synthetic */ ChuckerFragmentTransactionPayloadBinding access$getPayloadBinding$p(TransactionPayloadFragment transactionPayloadFragment) {
        ChuckerFragmentTransactionPayloadBinding chuckerFragmentTransactionPayloadBinding = transactionPayloadFragment.payloadBinding;
        if (chuckerFragmentTransactionPayloadBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("payloadBinding");
        }
        return chuckerFragmentTransactionPayloadBinding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(19)
    public final void createFileToSaveBody() {
        Intent intent = new Intent("android.intent.action.CREATE_DOCUMENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.putExtra("android.intent.extra.TITLE", DEFAULT_FILE_PREFIX + System.currentTimeMillis());
        intent.setType("*/*");
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        if (intent.resolveActivity(requireActivity.getPackageManager()) != null) {
            startActivityForResult(intent, 43);
        } else {
            Toast.makeText(requireContext(), R.string.chucker_save_failed_to_open_document, 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PayloadType getPayloadType() {
        return (PayloadType) this.payloadType$delegate.getValue();
    }

    private final TransactionViewModel getViewModel() {
        return (TransactionViewModel) this.viewModel$delegate.getValue();
    }

    private final boolean shouldShowSaveIcon(HttpTransaction httpTransaction) {
        Long responsePayloadSize;
        if (Build.VERSION.SDK_INT >= 19) {
            if (getPayloadType() == PayloadType.REQUEST) {
                responsePayloadSize = httpTransaction != null ? httpTransaction.getRequestPayloadSize() : null;
                if (responsePayloadSize == null || 0 != responsePayloadSize.longValue()) {
                    return true;
                }
            } else if (getPayloadType() != PayloadType.RESPONSE) {
                return true;
            } else {
                responsePayloadSize = httpTransaction != null ? httpTransaction.getResponsePayloadSize() : null;
                if (responsePayloadSize == null || 0 != responsePayloadSize.longValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    private final boolean shouldShowSearchIcon(HttpTransaction httpTransaction) {
        int i2 = WhenMappings.$EnumSwitchMapping$0[getPayloadType().ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                throw new NoWhenBranchMatchedException();
            }
            if (httpTransaction == null || true != httpTransaction.isResponseBodyPlainText()) {
                return false;
            }
            Long responsePayloadSize = httpTransaction.getResponsePayloadSize();
            if (responsePayloadSize != null && 0 == responsePayloadSize.longValue()) {
                return false;
            }
        } else if (httpTransaction == null || true != httpTransaction.isRequestBodyPlainText()) {
            return false;
        } else {
            Long requestPayloadSize = httpTransaction.getRequestPayloadSize();
            if (requestPayloadSize != null && 0 == requestPayloadSize.longValue()) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showEmptyState() {
        ChuckerFragmentTransactionPayloadBinding chuckerFragmentTransactionPayloadBinding = this.payloadBinding;
        if (chuckerFragmentTransactionPayloadBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("payloadBinding");
        }
        TextView emptyPayloadTextView = chuckerFragmentTransactionPayloadBinding.emptyPayloadTextView;
        Intrinsics.checkNotNullExpressionValue(emptyPayloadTextView, "emptyPayloadTextView");
        emptyPayloadTextView.setText(getString(getPayloadType() == PayloadType.RESPONSE ? R.string.chucker_response_is_empty : R.string.chucker_request_is_empty));
        Group emptyStateGroup = chuckerFragmentTransactionPayloadBinding.emptyStateGroup;
        Intrinsics.checkNotNullExpressionValue(emptyStateGroup, "emptyStateGroup");
        emptyStateGroup.setVisibility(0);
        RecyclerView payloadRecyclerView = chuckerFragmentTransactionPayloadBinding.payloadRecyclerView;
        Intrinsics.checkNotNullExpressionValue(payloadRecyclerView, "payloadRecyclerView");
        payloadRecyclerView.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showPayloadState() {
        ChuckerFragmentTransactionPayloadBinding chuckerFragmentTransactionPayloadBinding = this.payloadBinding;
        if (chuckerFragmentTransactionPayloadBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("payloadBinding");
        }
        Group emptyStateGroup = chuckerFragmentTransactionPayloadBinding.emptyStateGroup;
        Intrinsics.checkNotNullExpressionValue(emptyStateGroup, "emptyStateGroup");
        emptyStateGroup.setVisibility(8);
        RecyclerView payloadRecyclerView = chuckerFragmentTransactionPayloadBinding.payloadRecyclerView;
        Intrinsics.checkNotNullExpressionValue(payloadRecyclerView, "payloadRecyclerView");
        payloadRecyclerView.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final /* synthetic */ Object a(@NotNull PayloadType payloadType, @NotNull HttpTransaction httpTransaction, boolean z, @NotNull Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getDefault(), new TransactionPayloadFragment$processPayload$2(this, payloadType, httpTransaction, z, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final /* synthetic */ Object b(@NotNull PayloadType payloadType, @NotNull Uri uri, @NotNull HttpTransaction httpTransaction, @NotNull Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new TransactionPayloadFragment$saveToFile$2(this, uri, payloadType, httpTransaction, null), continuation);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i2, int i3, @Nullable Intent intent) {
        if (i2 == 43 && i3 == -1) {
            Uri data = intent != null ? intent.getData() : null;
            HttpTransaction value = getViewModel().getTransaction().getValue();
            if (data == null || value == null) {
                Toast.makeText(getContext(), R.string.chucker_file_not_saved, 0).show();
            } else {
                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new TransactionPayloadFragment$onActivityResult$1(this, data, value, null), 3, null);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        this.backgroundSpanColor = ContextCompat.getColor(context, R.color.chucker_background_span_color);
        this.foregroundSpanColor = ContextCompat.getColor(context, R.color.chucker_foreground_span_color);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    @Override // androidx.fragment.app.Fragment
    @SuppressLint({"NewApi"})
    public void onCreateOptionsMenu(@NotNull final Menu menu, @NotNull MenuInflater inflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        HttpTransaction value = getViewModel().getTransaction().getValue();
        if (shouldShowSearchIcon(value)) {
            MenuItem searchMenuItem = menu.findItem(R.id.search);
            Intrinsics.checkNotNullExpressionValue(searchMenuItem, "searchMenuItem");
            searchMenuItem.setVisible(true);
            View actionView = searchMenuItem.getActionView();
            Objects.requireNonNull(actionView, "null cannot be cast to non-null type androidx.appcompat.widget.SearchView");
            SearchView searchView = (SearchView) actionView;
            searchView.setOnQueryTextListener(this);
            searchView.setIconifiedByDefault(true);
        }
        if (shouldShowSaveIcon(value)) {
            MenuItem findItem = menu.findItem(R.id.save_body);
            findItem.setVisible(true);
            findItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.chuckerteam.chucker.internal.ui.transaction.TransactionPayloadFragment$onCreateOptionsMenu$$inlined$apply$lambda$1
                @Override // android.view.MenuItem.OnMenuItemClickListener
                public final boolean onMenuItemClick(MenuItem menuItem) {
                    TransactionPayloadFragment.this.createFileToSaveBody();
                    return true;
                }
            });
        }
        if (getPayloadType() == PayloadType.REQUEST) {
            getViewModel().getDoesRequestBodyRequireEncoding().observe(getViewLifecycleOwner(), new Observer<Boolean>() { // from class: com.chuckerteam.chucker.internal.ui.transaction.TransactionPayloadFragment$onCreateOptionsMenu$2
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Boolean it) {
                    MenuItem findItem2 = menu.findItem(R.id.encode_url);
                    Intrinsics.checkNotNullExpressionValue(findItem2, "menu.findItem(R.id.encode_url)");
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    findItem2.setVisible(it.booleanValue());
                }
            });
        } else {
            MenuItem findItem2 = menu.findItem(R.id.encode_url);
            Intrinsics.checkNotNullExpressionValue(findItem2, "menu.findItem(R.id.encode_url)");
            findItem2.setVisible(false);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ChuckerFragmentTransactionPayloadBinding inflate = ChuckerFragmentTransactionPayloadBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "ChuckerFragmentTransacti…          false\n        )");
        this.payloadBinding = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("payloadBinding");
        }
        return inflate.getRoot();
    }

    @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
    public boolean onQueryTextChange(@NotNull String newText) {
        boolean isBlank;
        Intrinsics.checkNotNullParameter(newText, "newText");
        isBlank = StringsKt__StringsJVMKt.isBlank(newText);
        if (!(!isBlank) || newText.length() <= 1) {
            this.payloadAdapter.resetHighlight$com_github_ChuckerTeam_Chucker_library();
        } else {
            this.payloadAdapter.highlightQueryWithColors$com_github_ChuckerTeam_Chucker_library(newText, this.backgroundSpanColor, this.foregroundSpanColor);
        }
        return true;
    }

    @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
    public boolean onQueryTextSubmit(@NotNull String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        ChuckerFragmentTransactionPayloadBinding chuckerFragmentTransactionPayloadBinding = this.payloadBinding;
        if (chuckerFragmentTransactionPayloadBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("payloadBinding");
        }
        RecyclerView recyclerView = chuckerFragmentTransactionPayloadBinding.payloadRecyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(this.payloadAdapter);
        LiveDataUtilsKt.combineLatest(getViewModel().getTransaction(), getViewModel().getFormatRequestBody()).observe(getViewLifecycleOwner(), new Observer<Pair<? extends HttpTransaction, ? extends Boolean>>() { // from class: com.chuckerteam.chucker.internal.ui.transaction.TransactionPayloadFragment$onViewCreated$2

            /* JADX INFO: Access modifiers changed from: package-private */
            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
            @DebugMetadata(c = "com.chuckerteam.chucker.internal.ui.transaction.TransactionPayloadFragment$onViewCreated$2$1", f = "TransactionPayloadFragment.kt", i = {0}, l = {90}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
            /* renamed from: com.chuckerteam.chucker.internal.ui.transaction.TransactionPayloadFragment$onViewCreated$2$1  reason: invalid class name */
            /* loaded from: classes.dex */
            public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

                /* renamed from: a  reason: collision with root package name */
                Object f4994a;

                /* renamed from: b  reason: collision with root package name */
                int f4995b;

                /* renamed from: d  reason: collision with root package name */
                final /* synthetic */ HttpTransaction f4997d;

                /* renamed from: e  reason: collision with root package name */
                final /* synthetic */ boolean f4998e;
                private CoroutineScope p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(HttpTransaction httpTransaction, boolean z, Continuation continuation) {
                    super(2, continuation);
                    this.f4997d = httpTransaction;
                    this.f4998e = z;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
                    Intrinsics.checkNotNullParameter(completion, "completion");
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.f4997d, this.f4998e, completion);
                    anonymousClass1.p$ = (CoroutineScope) obj;
                    return anonymousClass1;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended;
                    PayloadType payloadType;
                    TransactionBodyAdapter transactionBodyAdapter;
                    coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i2 = this.f4995b;
                    if (i2 == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.p$;
                        ContentLoadingProgressBar contentLoadingProgressBar = TransactionPayloadFragment.access$getPayloadBinding$p(TransactionPayloadFragment.this).loadingProgress;
                        Intrinsics.checkNotNullExpressionValue(contentLoadingProgressBar, "payloadBinding.loadingProgress");
                        contentLoadingProgressBar.setVisibility(0);
                        TransactionPayloadFragment transactionPayloadFragment = TransactionPayloadFragment.this;
                        payloadType = transactionPayloadFragment.getPayloadType();
                        HttpTransaction httpTransaction = this.f4997d;
                        boolean z = this.f4998e;
                        this.f4994a = coroutineScope;
                        this.f4995b = 1;
                        obj = transactionPayloadFragment.a(payloadType, httpTransaction, z, this);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        CoroutineScope coroutineScope2 = (CoroutineScope) this.f4994a;
                        ResultKt.throwOnFailure(obj);
                    }
                    List<? extends TransactionPayloadItem> list = (List) obj;
                    if (list.isEmpty()) {
                        TransactionPayloadFragment.this.showEmptyState();
                    } else {
                        transactionBodyAdapter = TransactionPayloadFragment.this.payloadAdapter;
                        transactionBodyAdapter.setItems(list);
                        TransactionPayloadFragment.this.showPayloadState();
                    }
                    TransactionPayloadFragment.this.requireActivity().invalidateOptionsMenu();
                    ContentLoadingProgressBar contentLoadingProgressBar2 = TransactionPayloadFragment.access$getPayloadBinding$p(TransactionPayloadFragment.this).loadingProgress;
                    Intrinsics.checkNotNullExpressionValue(contentLoadingProgressBar2, "payloadBinding.loadingProgress");
                    contentLoadingProgressBar2.setVisibility(8);
                    return Unit.INSTANCE;
                }
            }

            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(Pair<? extends HttpTransaction, ? extends Boolean> pair) {
                onChanged2((Pair<HttpTransaction, Boolean>) pair);
            }

            /* renamed from: onChanged  reason: avoid collision after fix types in other method */
            public final void onChanged2(Pair<HttpTransaction, Boolean> pair) {
                HttpTransaction component1 = pair.component1();
                boolean booleanValue = pair.component2().booleanValue();
                if (component1 == null) {
                    return;
                }
                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(TransactionPayloadFragment.this), null, null, new AnonymousClass1(component1, booleanValue, null), 3, null);
            }
        });
    }
}
