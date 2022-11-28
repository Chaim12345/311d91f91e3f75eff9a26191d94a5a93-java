package com.chuckerteam.chucker.internal.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import com.chuckerteam.chucker.api.Chucker;
import com.chuckerteam.chucker.databinding.ChuckerActivityMainBinding;
import com.chuckerteam.chucker.internal.ui.throwable.ThrowableActivity;
import com.chuckerteam.chucker.internal.ui.throwable.ThrowableAdapter;
import com.chuckerteam.chucker.internal.ui.transaction.TransactionActivity;
import com.chuckerteam.chucker.internal.ui.transaction.TransactionAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0007\b\u0000\u0018\u0000 \u00182\u00020\u00012\u00020\u00022\u00020\u0003:\u0001\u0018B\u0007¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0018\u0010\f\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0018\u0010\u000e\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0016R\u0016\u0010\u0010\u001a\u00020\u000f8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0015\u001a\u00020\u00128B@\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0019"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/MainActivity;", "Lcom/chuckerteam/chucker/internal/ui/BaseChuckerActivity;", "Lcom/chuckerteam/chucker/internal/ui/transaction/TransactionAdapter$TransactionClickListListener;", "Lcom/chuckerteam/chucker/internal/ui/throwable/ThrowableAdapter$ThrowableClickListListener;", "Landroid/content/Intent;", "intent", "", "consumeIntent", "", "throwableId", "", AppConstants.ARG_POSITION, "onThrowableClick", "transactionId", "onTransactionClick", "Lcom/chuckerteam/chucker/databinding/ChuckerActivityMainBinding;", "mainBinding", "Lcom/chuckerteam/chucker/databinding/ChuckerActivityMainBinding;", "", "getApplicationName", "()Ljava/lang/CharSequence;", "applicationName", "<init>", "()V", "Companion", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class MainActivity extends BaseChuckerActivity implements TransactionAdapter.TransactionClickListListener, ThrowableAdapter.ThrowableClickListListener {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String EXTRA_SCREEN = "EXTRA_SCREEN";
    private ChuckerActivityMainBinding mainBinding;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004¨\u0006\u0007"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/MainActivity$Companion;", "", "", MainActivity.EXTRA_SCREEN, "Ljava/lang/String;", "<init>", "()V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final void consumeIntent(Intent intent) {
        int intExtra = intent.getIntExtra(EXTRA_SCREEN, 1);
        ChuckerActivityMainBinding chuckerActivityMainBinding = this.mainBinding;
        if (chuckerActivityMainBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainBinding");
        }
        ViewPager viewPager = chuckerActivityMainBinding.viewPager;
        Intrinsics.checkNotNullExpressionValue(viewPager, "mainBinding.viewPager");
        viewPager.setCurrentItem(intExtra == 1 ? 0 : 1);
    }

    private final CharSequence getApplicationName() {
        CharSequence loadLabel = getApplicationInfo().loadLabel(getPackageManager());
        Intrinsics.checkNotNullExpressionValue(loadLabel, "applicationInfo.loadLabel(packageManager)");
        return loadLabel;
    }

    @Override // com.chuckerteam.chucker.internal.ui.BaseChuckerActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        final ChuckerActivityMainBinding inflate = ChuckerActivityMainBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "ChuckerActivityMainBinding.inflate(layoutInflater)");
        this.mainBinding = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainBinding");
        }
        setContentView(inflate.getRoot());
        setSupportActionBar(inflate.toolbar);
        MaterialToolbar toolbar = inflate.toolbar;
        Intrinsics.checkNotNullExpressionValue(toolbar, "toolbar");
        toolbar.setSubtitle(getApplicationName());
        ViewPager viewPager = inflate.viewPager;
        Intrinsics.checkNotNullExpressionValue(viewPager, "viewPager");
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        viewPager.setAdapter(new HomePageAdapter(this, supportFragmentManager));
        inflate.tabLayout.setupWithViewPager(inflate.viewPager);
        ViewPager viewPager2 = inflate.viewPager;
        final TabLayout tabLayout = inflate.tabLayout;
        viewPager2.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(inflate, tabLayout, this) { // from class: com.chuckerteam.chucker.internal.ui.MainActivity$onCreate$$inlined$with$lambda$1

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ MainActivity f4939a;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(tabLayout);
                this.f4939a = this;
            }

            @Override // com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i2) {
                super.onPageSelected(i2);
                if (i2 == 0) {
                    Chucker.dismissTransactionsNotification(this.f4939a);
                } else {
                    Chucker.dismissErrorsNotification(this.f4939a);
                }
            }
        });
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "intent");
        consumeIntent(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(@NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onNewIntent(intent);
        consumeIntent(intent);
    }

    @Override // com.chuckerteam.chucker.internal.ui.throwable.ThrowableAdapter.ThrowableClickListListener
    public void onThrowableClick(long j2, int i2) {
        ThrowableActivity.Companion.start(this, j2);
    }

    @Override // com.chuckerteam.chucker.internal.ui.transaction.TransactionAdapter.TransactionClickListListener
    public void onTransactionClick(long j2, int i2) {
        TransactionActivity.Companion.start(this, j2);
    }
}
