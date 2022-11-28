package com.chuckerteam.chucker.internal.ui.transaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelLazy;
import androidx.viewpager.widget.ViewPager;
import com.chuckerteam.chucker.R;
import com.chuckerteam.chucker.databinding.ChuckerActivityTransactionBinding;
import com.chuckerteam.chucker.internal.data.entity.HttpTransaction;
import com.chuckerteam.chucker.internal.support.Sharable;
import com.chuckerteam.chucker.internal.ui.BaseChuckerActivity;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0007¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u001c\u0010\u000b\u001a\u00020\n2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006H\u0002J\u001c\u0010\f\u001a\u00020\n2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006H\u0002J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0002J\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0011H\u0016R\u001d\u0010\u0019\u001a\u00020\u00148B@\u0002X\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u001b\u001a\u00020\u001a8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u001b\u0010\u001c¨\u0006 "}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionActivity;", "Lcom/chuckerteam/chucker/internal/ui/BaseChuckerActivity;", "Landroid/view/Menu;", "menu", "", "setUpUrlEncoding", "Lkotlin/Function1;", "Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;", "Lcom/chuckerteam/chucker/internal/support/Sharable;", "block", "", "shareTransactionAsText", "shareTransactionAsFile", "Landroidx/viewpager/widget/ViewPager;", "viewPager", "setupViewPager", "onCreateOptionsMenu", "Landroid/view/MenuItem;", "item", "onOptionsItemSelected", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "getViewModel", "()Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionViewModel;", "viewModel", "Lcom/chuckerteam/chucker/databinding/ChuckerActivityTransactionBinding;", "transactionBinding", "Lcom/chuckerteam/chucker/databinding/ChuckerActivityTransactionBinding;", "<init>", "()V", "Companion", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class TransactionActivity extends BaseChuckerActivity {
    public static final Companion Companion = new Companion(null);
    private static final String EXPORT_FILE_NAME = "transaction.txt";
    private static final String EXTRA_TRANSACTION_ID = "transaction_id";
    private static int selectedTabPosition;
    private ChuckerActivityTransactionBinding transactionBinding;
    private final Lazy viewModel$delegate = new ViewModelLazy(Reflection.getOrCreateKotlinClass(TransactionViewModel.class), new TransactionActivity$$special$$inlined$viewModels$2(this), new TransactionActivity$viewModel$2(this));

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0016\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004R\u0016\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\t\u0010\nR\u0016\u0010\u000b\u001a\u00020\b8\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u000b\u0010\nR\u0016\u0010\r\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionActivity$Companion;", "", "Landroid/content/Context;", "context", "", "transactionId", "", "start", "", "EXPORT_FILE_NAME", "Ljava/lang/String;", "EXTRA_TRANSACTION_ID", "", "selectedTabPosition", "I", "<init>", "()V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void start(@NotNull Context context, long j2) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, TransactionActivity.class);
            intent.putExtra("transaction_id", j2);
            context.startActivity(intent);
        }
    }

    public static final /* synthetic */ ChuckerActivityTransactionBinding access$getTransactionBinding$p(TransactionActivity transactionActivity) {
        ChuckerActivityTransactionBinding chuckerActivityTransactionBinding = transactionActivity.transactionBinding;
        if (chuckerActivityTransactionBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transactionBinding");
        }
        return chuckerActivityTransactionBinding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TransactionViewModel getViewModel() {
        return (TransactionViewModel) this.viewModel$delegate.getValue();
    }

    private final void setUpUrlEncoding(Menu menu) {
        final MenuItem findItem = menu.findItem(R.id.encode_url);
        findItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.chuckerteam.chucker.internal.ui.transaction.TransactionActivity$setUpUrlEncoding$1
            @Override // android.view.MenuItem.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                TransactionViewModel viewModel;
                viewModel = TransactionActivity.this.getViewModel();
                viewModel.switchUrlEncoding();
                return true;
            }
        });
        getViewModel().getEncodeUrl().observe(this, new Observer<Boolean>() { // from class: com.chuckerteam.chucker.internal.ui.transaction.TransactionActivity$setUpUrlEncoding$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean encode) {
                Intrinsics.checkNotNullExpressionValue(encode, "encode");
                findItem.setIcon(encode.booleanValue() ? R.drawable.chucker_ic_encoded_url_white : R.drawable.chucker_ic_decoded_url_white);
            }
        });
    }

    private final void setupViewPager(ViewPager viewPager) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        viewPager.setAdapter(new TransactionPagerAdapter(this, supportFragmentManager));
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() { // from class: com.chuckerteam.chucker.internal.ui.transaction.TransactionActivity$setupViewPager$1
            @Override // androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i2) {
                TransactionActivity.selectedTabPosition = i2;
            }
        });
        viewPager.setCurrentItem(selectedTabPosition);
    }

    private final boolean shareTransactionAsFile(Function1<? super HttpTransaction, ? extends Sharable> function1) {
        HttpTransaction value = getViewModel().getTransaction().getValue();
        if (value != null) {
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new TransactionActivity$shareTransactionAsFile$1(this, function1.invoke(value), null), 3, null);
            return true;
        }
        String string = getString(R.string.chucker_request_not_ready);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.chucker_request_not_ready)");
        showToast(string);
        return true;
    }

    private final boolean shareTransactionAsText(Function1<? super HttpTransaction, ? extends Sharable> function1) {
        HttpTransaction value = getViewModel().getTransaction().getValue();
        if (value != null) {
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new TransactionActivity$shareTransactionAsText$1(this, function1.invoke(value), null), 3, null);
            return true;
        }
        String string = getString(R.string.chucker_request_not_ready);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.chucker_request_not_ready)");
        showToast(string);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chuckerteam.chucker.internal.ui.BaseChuckerActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ChuckerActivityTransactionBinding inflate = ChuckerActivityTransactionBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "ChuckerActivityTransacti…g.inflate(layoutInflater)");
        this.transactionBinding = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transactionBinding");
        }
        setContentView(inflate.getRoot());
        setSupportActionBar(inflate.toolbar);
        ViewPager viewPager = inflate.viewPager;
        Intrinsics.checkNotNullExpressionValue(viewPager, "viewPager");
        setupViewPager(viewPager);
        inflate.tabLayout.setupWithViewPager(inflate.viewPager);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        getViewModel().getTransactionTitle().observe(this, new Observer<String>() { // from class: com.chuckerteam.chucker.internal.ui.transaction.TransactionActivity$onCreate$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(String str) {
                TextView textView = TransactionActivity.access$getTransactionBinding$p(TransactionActivity.this).toolbarTitle;
                Intrinsics.checkNotNullExpressionValue(textView, "transactionBinding.toolbarTitle");
                textView.setText(str);
            }
        });
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(@NotNull Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        getMenuInflater().inflate(R.menu.chucker_transaction, menu);
        setUpUrlEncoding(menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        Function1<? super HttpTransaction, ? extends Sharable> function1;
        Intrinsics.checkNotNullParameter(item, "item");
        int itemId = item.getItemId();
        if (itemId == R.id.share_text) {
            function1 = new TransactionActivity$onOptionsItemSelected$1(this);
        } else if (itemId != R.id.share_curl) {
            return itemId == R.id.share_file ? shareTransactionAsFile(new TransactionActivity$onOptionsItemSelected$3(this)) : super.onOptionsItemSelected(item);
        } else {
            function1 = TransactionActivity$onOptionsItemSelected$2.INSTANCE;
        }
        return shareTransactionAsText(function1);
    }
}
