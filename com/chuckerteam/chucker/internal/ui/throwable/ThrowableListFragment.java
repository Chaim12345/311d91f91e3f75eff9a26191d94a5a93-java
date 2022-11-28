package com.chuckerteam.chucker.internal.ui.throwable;

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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import com.chuckerteam.chucker.R;
import com.chuckerteam.chucker.databinding.ChuckerFragmentThrowableListBinding;
import com.chuckerteam.chucker.internal.data.entity.RecordedThrowableTuple;
import com.chuckerteam.chucker.internal.data.model.DialogData;
import com.chuckerteam.chucker.internal.support.ContextExtKt;
import com.chuckerteam.chucker.internal.ui.MainViewModel;
import com.chuckerteam.chucker.internal.ui.throwable.ThrowableActivity;
import com.chuckerteam.chucker.internal.ui.throwable.ThrowableAdapter;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 +2\u00020\u00012\u00020\u0002:\u0001+B\u0007¢\u0006\u0004\b)\u0010*J\b\u0010\u0004\u001a\u00020\u0003H\u0002J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016J&\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\t\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016J\u001a\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\f2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016J\u0018\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0012H\u0016J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0014H\u0016J\u0018\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001aH\u0016R\u001d\u0010\"\u001a\u00020\u001d8B@\u0002X\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R\u0016\u0010$\u001a\u00020#8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b$\u0010%R\u0016\u0010'\u001a\u00020&8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b'\u0010(¨\u0006,"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/throwable/ThrowableListFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/chuckerteam/chucker/internal/ui/throwable/ThrowableAdapter$ThrowableClickListListener;", "", "askForConfirmation", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/view/View;", "onCreateView", "view", "onViewCreated", "Landroid/view/Menu;", "menu", "Landroid/view/MenuInflater;", "onCreateOptionsMenu", "Landroid/view/MenuItem;", "item", "", "onOptionsItemSelected", "", "throwableId", "", AppConstants.ARG_POSITION, "onThrowableClick", "Lcom/chuckerteam/chucker/internal/ui/MainViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "getViewModel", "()Lcom/chuckerteam/chucker/internal/ui/MainViewModel;", "viewModel", "Lcom/chuckerteam/chucker/databinding/ChuckerFragmentThrowableListBinding;", "errorsBinding", "Lcom/chuckerteam/chucker/databinding/ChuckerFragmentThrowableListBinding;", "Lcom/chuckerteam/chucker/internal/ui/throwable/ThrowableAdapter;", "errorsAdapter", "Lcom/chuckerteam/chucker/internal/ui/throwable/ThrowableAdapter;", "<init>", "()V", "Companion", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class ThrowableListFragment extends Fragment implements ThrowableAdapter.ThrowableClickListListener {
    public static final Companion Companion = new Companion(null);
    private ThrowableAdapter errorsAdapter;
    private ChuckerFragmentThrowableListBinding errorsBinding;
    private final Lazy viewModel$delegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(MainViewModel.class), new ThrowableListFragment$$special$$inlined$viewModels$2(new ThrowableListFragment$$special$$inlined$viewModels$1(this)), null);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\u0006"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/throwable/ThrowableListFragment$Companion;", "", "Lcom/chuckerteam/chucker/internal/ui/throwable/ThrowableListFragment;", "newInstance", "<init>", "()V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final ThrowableListFragment newInstance() {
            return new ThrowableListFragment();
        }
    }

    public static final /* synthetic */ ThrowableAdapter access$getErrorsAdapter$p(ThrowableListFragment throwableListFragment) {
        ThrowableAdapter throwableAdapter = throwableListFragment.errorsAdapter;
        if (throwableAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("errorsAdapter");
        }
        return throwableAdapter;
    }

    public static final /* synthetic */ ChuckerFragmentThrowableListBinding access$getErrorsBinding$p(ThrowableListFragment throwableListFragment) {
        ChuckerFragmentThrowableListBinding chuckerFragmentThrowableListBinding = throwableListFragment.errorsBinding;
        if (chuckerFragmentThrowableListBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("errorsBinding");
        }
        return chuckerFragmentThrowableListBinding;
    }

    private final void askForConfirmation() {
        int i2 = R.string.chucker_clear;
        String string = getString(i2);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.chucker_clear)");
        String string2 = getString(R.string.chucker_clear_throwable_confirmation);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.chuck…r_throwable_confirmation)");
        DialogData dialogData = new DialogData(string, string2, getString(i2), getString(R.string.chucker_cancel));
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ContextExtKt.showDialog(requireContext, dialogData, new ThrowableListFragment$askForConfirmation$1(this), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MainViewModel getViewModel() {
        return (MainViewModel) this.viewModel$delegate.getValue();
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
        inflater.inflate(R.menu.chucker_throwables_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ChuckerFragmentThrowableListBinding inflate = ChuckerFragmentThrowableListBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "ChuckerFragmentThrowable…flater, container, false)");
        this.errorsBinding = inflate;
        this.errorsAdapter = new ThrowableAdapter(this);
        ChuckerFragmentThrowableListBinding chuckerFragmentThrowableListBinding = this.errorsBinding;
        if (chuckerFragmentThrowableListBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("errorsBinding");
        }
        TextView tutorialLink = chuckerFragmentThrowableListBinding.tutorialLink;
        Intrinsics.checkNotNullExpressionValue(tutorialLink, "tutorialLink");
        tutorialLink.setMovementMethod(LinkMovementMethod.getInstance());
        RecyclerView recyclerView = chuckerFragmentThrowableListBinding.errorsRecyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), 1));
        ThrowableAdapter throwableAdapter = this.errorsAdapter;
        if (throwableAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("errorsAdapter");
        }
        recyclerView.setAdapter(throwableAdapter);
        ChuckerFragmentThrowableListBinding chuckerFragmentThrowableListBinding2 = this.errorsBinding;
        if (chuckerFragmentThrowableListBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("errorsBinding");
        }
        return chuckerFragmentThrowableListBinding2.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == R.id.clear) {
            askForConfirmation();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override // com.chuckerteam.chucker.internal.ui.throwable.ThrowableAdapter.ThrowableClickListListener
    public void onThrowableClick(long j2, int i2) {
        ThrowableActivity.Companion companion = ThrowableActivity.Companion;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        companion.start(requireActivity, j2);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        getViewModel().getThrowables().observe(getViewLifecycleOwner(), new Observer<List<? extends RecordedThrowableTuple>>() { // from class: com.chuckerteam.chucker.internal.ui.throwable.ThrowableListFragment$onViewCreated$1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(List<? extends RecordedThrowableTuple> list) {
                onChanged2((List<RecordedThrowableTuple>) list);
            }

            /* renamed from: onChanged  reason: avoid collision after fix types in other method */
            public final void onChanged2(List<RecordedThrowableTuple> throwables) {
                ThrowableAdapter access$getErrorsAdapter$p = ThrowableListFragment.access$getErrorsAdapter$p(ThrowableListFragment.this);
                Intrinsics.checkNotNullExpressionValue(throwables, "throwables");
                access$getErrorsAdapter$p.setData(throwables);
                LinearLayout linearLayout = ThrowableListFragment.access$getErrorsBinding$p(ThrowableListFragment.this).tutorialView;
                Intrinsics.checkNotNullExpressionValue(linearLayout, "errorsBinding.tutorialView");
                linearLayout.setVisibility(throwables.isEmpty() ? 0 : 8);
            }
        });
    }
}
