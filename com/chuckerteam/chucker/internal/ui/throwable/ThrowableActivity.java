package com.chuckerteam.chucker.internal.ui.throwable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.core.app.ShareCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelLazy;
import com.chuckerteam.chucker.R;
import com.chuckerteam.chucker.databinding.ChuckerActivityThrowableBinding;
import com.chuckerteam.chucker.internal.data.entity.RecordedThrowable;
import com.chuckerteam.chucker.internal.ui.BaseChuckerActivity;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.text.DateFormat;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0000\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0007¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0016R\u001d\u0010\u0013\u001a\u00020\u000e8B@\u0002X\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0015\u001a\u00020\u00148\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u001a\u0010\u001a\u001a\u00020\u0017*\u00020\u00028B@\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001e"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/throwable/ThrowableActivity;", "Lcom/chuckerteam/chucker/internal/ui/BaseChuckerActivity;", "Lcom/chuckerteam/chucker/internal/data/entity/RecordedThrowable;", "throwable", "", FirebaseAnalytics.Event.SHARE, "populateUI", "Landroid/view/Menu;", "menu", "", "onCreateOptionsMenu", "Landroid/view/MenuItem;", "item", "onOptionsItemSelected", "Lcom/chuckerteam/chucker/internal/ui/throwable/ThrowableViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "getViewModel", "()Lcom/chuckerteam/chucker/internal/ui/throwable/ThrowableViewModel;", "viewModel", "Lcom/chuckerteam/chucker/databinding/ChuckerActivityThrowableBinding;", "errorBinding", "Lcom/chuckerteam/chucker/databinding/ChuckerActivityThrowableBinding;", "", "getFormattedDate", "(Lcom/chuckerteam/chucker/internal/data/entity/RecordedThrowable;)Ljava/lang/String;", "formattedDate", "<init>", "()V", "Companion", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class ThrowableActivity extends BaseChuckerActivity {
    public static final Companion Companion = new Companion(null);
    private static final String EXTRA_THROWABLE_ID = "transaction_id";
    private static final String MIME_TYPE = "text/plain";
    private ChuckerActivityThrowableBinding errorBinding;
    private final Lazy viewModel$delegate = new ViewModelLazy(Reflection.getOrCreateKotlinClass(ThrowableViewModel.class), new ThrowableActivity$$special$$inlined$viewModels$2(this), new ThrowableActivity$viewModel$2(this));

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\f\u0010\rJ\u0016\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004R\u0016\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\t\u0010\nR\u0016\u0010\u000b\u001a\u00020\b8\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u000b\u0010\n¨\u0006\u000e"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/throwable/ThrowableActivity$Companion;", "", "Landroid/content/Context;", "context", "", "throwableId", "", "start", "", "EXTRA_THROWABLE_ID", "Ljava/lang/String;", "MIME_TYPE", "<init>", "()V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void start(@NotNull Context context, long j2) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, ThrowableActivity.class);
            intent.putExtra("transaction_id", j2);
            context.startActivity(intent);
        }
    }

    private final String getFormattedDate(RecordedThrowable recordedThrowable) {
        String format = DateFormat.getDateTimeInstance(3, 2).format(recordedThrowable.getDate());
        Intrinsics.checkNotNullExpressionValue(format, "DateFormat.getDateTimeIn…       .format(this.date)");
        return format;
    }

    private final ThrowableViewModel getViewModel() {
        return (ThrowableViewModel) this.viewModel$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void populateUI(RecordedThrowable recordedThrowable) {
        ChuckerActivityThrowableBinding chuckerActivityThrowableBinding = this.errorBinding;
        if (chuckerActivityThrowableBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("errorBinding");
        }
        TextView toolbarTitle = chuckerActivityThrowableBinding.toolbarTitle;
        Intrinsics.checkNotNullExpressionValue(toolbarTitle, "toolbarTitle");
        toolbarTitle.setText(getFormattedDate(recordedThrowable));
        TextView textView = chuckerActivityThrowableBinding.throwableItem.tag;
        Intrinsics.checkNotNullExpressionValue(textView, "throwableItem.tag");
        textView.setText(recordedThrowable.getTag());
        TextView textView2 = chuckerActivityThrowableBinding.throwableItem.clazz;
        Intrinsics.checkNotNullExpressionValue(textView2, "throwableItem.clazz");
        textView2.setText(recordedThrowable.getClazz());
        TextView textView3 = chuckerActivityThrowableBinding.throwableItem.message;
        Intrinsics.checkNotNullExpressionValue(textView3, "throwableItem.message");
        textView3.setText(recordedThrowable.getMessage());
        TextView throwableStacktrace = chuckerActivityThrowableBinding.throwableStacktrace;
        Intrinsics.checkNotNullExpressionValue(throwableStacktrace, "throwableStacktrace");
        throwableStacktrace.setText(recordedThrowable.getContent());
    }

    private final void share(RecordedThrowable recordedThrowable) {
        String string = getString(R.string.chucker_share_throwable_content, new Object[]{getFormattedDate(recordedThrowable), recordedThrowable.getClazz(), recordedThrowable.getTag(), recordedThrowable.getMessage(), recordedThrowable.getContent()});
        Intrinsics.checkNotNullExpressionValue(string, "getString(\n            R…rowable.content\n        )");
        startActivity(ShareCompat.IntentBuilder.from(this).setType("text/plain").setChooserTitle(getString(R.string.chucker_share_throwable_title)).setSubject(getString(R.string.chucker_share_throwable_subject)).setText(string).createChooserIntent());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chuckerteam.chucker.internal.ui.BaseChuckerActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ChuckerActivityThrowableBinding inflate = ChuckerActivityThrowableBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "ChuckerActivityThrowable…g.inflate(layoutInflater)");
        this.errorBinding = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("errorBinding");
        }
        setContentView(inflate.getRoot());
        setSupportActionBar(inflate.toolbar);
        TextView textView = inflate.throwableItem.date;
        Intrinsics.checkNotNullExpressionValue(textView, "throwableItem.date");
        textView.setVisibility(8);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        getViewModel().getThrowable().observe(this, new Observer<RecordedThrowable>() { // from class: com.chuckerteam.chucker.internal.ui.throwable.ThrowableActivity$onCreate$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(RecordedThrowable it) {
                ThrowableActivity throwableActivity = ThrowableActivity.this;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                throwableActivity.populateUI(it);
            }
        });
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(@NotNull Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        MenuInflater menuInflater = getMenuInflater();
        Intrinsics.checkNotNullExpressionValue(menuInflater, "menuInflater");
        menuInflater.inflate(R.menu.chucker_throwable, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == R.id.share_text) {
            RecordedThrowable it = getViewModel().getThrowable().getValue();
            if (it != null) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                share(it);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
