package com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.share;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.psa.mym.mycitroenconnect.R;
import com.rd.PageIndicatorView;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.apache.http.protocol.HTTP;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ShareBottomSheetDialog extends BottomSheetDialogFragment implements View.OnClickListener, OnApplicationClickListener {
    @NotNull
    private static final String ARG_MESSAGE = "message";
    @NotNull
    private static final String ARG_SUBJECT = "subject";
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "ShareBottomSheetDialog";
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    private String message;
    @Nullable
    private String subject;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final ShareBottomSheetDialog newInstance(@NotNull String subject, @NotNull String message) {
            Intrinsics.checkNotNullParameter(subject, "subject");
            Intrinsics.checkNotNullParameter(message, "message");
            ShareBottomSheetDialog shareBottomSheetDialog = new ShareBottomSheetDialog();
            Bundle bundle = new Bundle();
            bundle.putString(ShareBottomSheetDialog.ARG_SUBJECT, subject);
            bundle.putString("message", message);
            shareBottomSheetDialog.setArguments(bundle);
            return shareBottomSheetDialog;
        }
    }

    private final void init() {
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivClose)).setOnClickListener(this);
        ApplicationListAdapter applicationListAdapter = new ApplicationListAdapter(showAllShareApp());
        applicationListAdapter.setOnApplicationClickListener(this);
        int i2 = R.id.rvAppList;
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(i2);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2, 0, false));
        recyclerView.setAdapter(applicationListAdapter);
        recyclerView.setHasFixedSize(true);
        int itemCount = applicationListAdapter.getItemCount() % 8;
        int itemCount2 = applicationListAdapter.getItemCount() / 8;
        if (itemCount != 0) {
            itemCount2++;
        }
        ((PageIndicatorView) _$_findCachedViewById(R.id.pageIndicatorView)).setCount(itemCount2);
        final Ref.IntRef intRef = new Ref.IntRef();
        final Ref.IntRef intRef2 = new Ref.IntRef();
        ((RecyclerView) _$_findCachedViewById(i2)).addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.share.ShareBottomSheetDialog$init$2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(@NotNull RecyclerView recyclerView2, int i3, int i4) {
                Intrinsics.checkNotNullParameter(recyclerView2, "recyclerView");
                super.onScrolled(recyclerView2, i3, i4);
                Ref.IntRef.this.element += i3;
                int width = recyclerView2.getChildAt(0).getWidth();
                Ref.IntRef intRef3 = Ref.IntRef.this;
                Ref.IntRef intRef4 = intRef;
                ShareBottomSheetDialog shareBottomSheetDialog = this;
                float f2 = width;
                int floor = (int) Math.floor((intRef3.element + (f2 / 2.0f)) / f2);
                int i5 = intRef4.element;
                if (i5 != floor) {
                    shareBottomSheetDialog.setCurrentItem(i5 < floor ? i5 + 1 : i5 - 1, true);
                }
                intRef4.element = floor;
            }
        });
    }

    @JvmStatic
    @NotNull
    public static final ShareBottomSheetDialog newInstance(@NotNull String str, @NotNull String str2) {
        return Companion.newInstance(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCurrentItem(int i2, boolean z) {
        ((PageIndicatorView) _$_findCachedViewById(R.id.pageIndicatorView)).setSelection(Math.abs(i2));
    }

    private final List<ResolveInfo> showAllShareApp() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.setType("*/*");
        List<ResolveInfo> queryIntentActivities = requireContext().getPackageManager().queryIntentActivities(intent, 0);
        Intrinsics.checkNotNullExpressionValue(queryIntentActivities, "requireContext().package…S\n            0\n        )");
        return queryIntentActivities;
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Nullable
    public View _$_findCachedViewById(int i2) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i2));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i2)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i2), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.share.OnApplicationClickListener
    public void onChooseApp(@NotNull ResolveInfo resolveInfo) {
        Intrinsics.checkNotNullParameter(resolveInfo, "resolveInfo");
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        if (!TextUtils.isEmpty(this.subject)) {
            intent.putExtra("android.intent.extra.SUBJECT", this.subject);
        }
        intent.putExtra("android.intent.extra.TEXT", this.message);
        ActivityInfo activityInfo = resolveInfo.activityInfo;
        intent.setComponent(new ComponentName(activityInfo.packageName, activityInfo.name));
        intent.setType(HTTP.PLAIN_TEXT_TYPE);
        requireContext().startActivity(intent);
        dismiss();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivClose))) {
            dismiss();
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.subject = arguments.getString(ARG_SUBJECT);
            this.message = arguments.getString("message");
        }
        setStyle(0, uat.psa.mym.mycitroenconnect.R.style.DialogStyle);
    }

    @Override // com.google.android.material.bottomsheet.BottomSheetDialogFragment, androidx.appcompat.app.AppCompatDialogFragment, androidx.fragment.app.DialogFragment
    @NotNull
    public Dialog onCreateDialog(@Nullable Bundle bundle) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext(), getTheme());
        bottomSheetDialog.getBehavior().setState(3);
        bottomSheetDialog.getBehavior().setSkipCollapsed(true);
        return bottomSheetDialog;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.bottom_sheet_dialog_share, viewGroup, false);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        init();
    }
}
