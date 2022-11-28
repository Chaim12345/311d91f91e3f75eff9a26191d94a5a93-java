package com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.p000interface.OnDialogProfilePhotoClickListener;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ProfilePhotoSelectionDlgFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "ProfilePhotoSelectionDlgFragment";
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private OnDialogProfilePhotoClickListener listener;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final ProfilePhotoSelectionDlgFragment newInstance() {
            ProfilePhotoSelectionDlgFragment profilePhotoSelectionDlgFragment = new ProfilePhotoSelectionDlgFragment();
            profilePhotoSelectionDlgFragment.setArguments(new Bundle());
            return profilePhotoSelectionDlgFragment;
        }
    }

    @JvmStatic
    @NotNull
    public static final ProfilePhotoSelectionDlgFragment newInstance() {
        return Companion.newInstance();
    }

    private final void setListeners() {
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivDelete)).setOnClickListener(this);
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvDeletePhoto)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivCamera)).setOnClickListener(this);
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvCameraPhoto)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivGallery)).setOnClickListener(this);
        ((AppCompatTextView) _$_findCachedViewById(R.id.tvGalleryPhoto)).setOnClickListener(this);
        ((AppCompatImageView) _$_findCachedViewById(R.id.ivClose)).setOnClickListener(this);
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

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        String str;
        OnDialogProfilePhotoClickListener onDialogProfilePhotoClickListener = null;
        if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivDelete)) ? true : Intrinsics.areEqual(view, (AppCompatTextView) _$_findCachedViewById(R.id.tvDeletePhoto))) {
            dismiss();
            OnDialogProfilePhotoClickListener onDialogProfilePhotoClickListener2 = this.listener;
            if (onDialogProfilePhotoClickListener2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            } else {
                onDialogProfilePhotoClickListener = onDialogProfilePhotoClickListener2;
            }
            str = "DELETE";
        } else {
            if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivCamera)) ? true : Intrinsics.areEqual(view, (AppCompatTextView) _$_findCachedViewById(R.id.tvCameraPhoto))) {
                dismiss();
                OnDialogProfilePhotoClickListener onDialogProfilePhotoClickListener3 = this.listener;
                if (onDialogProfilePhotoClickListener3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
                } else {
                    onDialogProfilePhotoClickListener = onDialogProfilePhotoClickListener3;
                }
                str = AppConstants.PROFILE_CAMERA;
            } else {
                if (!(Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivGallery)) ? true : Intrinsics.areEqual(view, (AppCompatTextView) _$_findCachedViewById(R.id.tvGalleryPhoto)))) {
                    if (Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivClose))) {
                        dismiss();
                        return;
                    }
                    return;
                }
                dismiss();
                OnDialogProfilePhotoClickListener onDialogProfilePhotoClickListener4 = this.listener;
                if (onDialogProfilePhotoClickListener4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
                } else {
                    onDialogProfilePhotoClickListener = onDialogProfilePhotoClickListener4;
                }
                str = AppConstants.PROFILE_GALLERY;
            }
        }
        onDialogProfilePhotoClickListener.onDialogProfilePhotoClickListener(str);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        getArguments();
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
        return inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.fragment_profile_photo_selection_dlg, viewGroup, false);
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
        setListeners();
    }

    public final void setListener(@NotNull OnDialogProfilePhotoClickListener lis) {
        Intrinsics.checkNotNullParameter(lis, "lis");
        this.listener = lis;
    }
}
