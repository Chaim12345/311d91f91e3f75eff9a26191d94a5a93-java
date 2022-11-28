package com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog;

import android.app.Dialog;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.model.MessageEvent;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.event_bus.GlobalBusUtil;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class TimePickerFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "TimePickerFragment";
    @Nullable
    private OnTimeSelectListener onTimeSelectListener;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private String title = "";
    @NotNull
    private String message = "";
    @NotNull
    private String time = "";
    @NotNull
    private String pageMode = "";

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final TimePickerFragment newInstance(@NotNull String title, @NotNull String message, @NotNull String time, @NotNull String pageMode) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(message, "message");
            Intrinsics.checkNotNullParameter(time, "time");
            Intrinsics.checkNotNullParameter(pageMode, "pageMode");
            TimePickerFragment timePickerFragment = new TimePickerFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title", title);
            bundle.putString(AppConstants.ARG_MESSAGE, message);
            bundle.putString(AppConstants.GEO_FENCE_TIME, time);
            bundle.putString(AppConstants.ARG_PAGE_MODE, pageMode);
            timePickerFragment.setArguments(bundle);
            return timePickerFragment;
        }
    }

    private final void hideKeyboardInputInTimePicker(int i2, TimePicker timePicker) {
        View childAt;
        String str;
        if (Build.VERSION.SDK_INT >= 26) {
            try {
                if (i2 == 1) {
                    View childAt2 = timePicker.getChildAt(0);
                    if (childAt2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type android.widget.LinearLayout");
                    }
                    View childAt3 = ((LinearLayout) childAt2).getChildAt(4);
                    if (childAt3 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type android.widget.LinearLayout");
                    }
                    childAt = ((LinearLayout) childAt3).getChildAt(0);
                    str = "(timePicker.getChildAt(0…           .getChildAt(0)";
                } else {
                    View childAt4 = timePicker.getChildAt(0);
                    if (childAt4 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type android.widget.LinearLayout");
                    }
                    View childAt5 = ((LinearLayout) childAt4).getChildAt(2);
                    if (childAt5 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type android.widget.LinearLayout");
                    }
                    View childAt6 = ((LinearLayout) childAt5).getChildAt(2);
                    if (childAt6 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type android.widget.LinearLayout");
                    }
                    childAt = ((LinearLayout) childAt6).getChildAt(0);
                    str = "((timePicker.getChildAt(…           .getChildAt(0)";
                }
                Intrinsics.checkNotNullExpressionValue(childAt, str);
                ExtensionsKt.hide(childAt);
            } catch (Exception unused) {
            }
        }
    }

    private final void init() {
        Resources resources;
        Configuration configuration;
        List split$default;
        if (this.title.length() > 0) {
            ((AppCompatTextView) _$_findCachedViewById(R.id.tvTitle)).setText(this.title);
        }
        if (this.message.length() > 0) {
            int i2 = R.id.tvMessage;
            ((AppCompatTextView) _$_findCachedViewById(i2)).setText(this.message);
            AppCompatTextView tvMessage = (AppCompatTextView) _$_findCachedViewById(i2);
            Intrinsics.checkNotNullExpressionValue(tvMessage, "tvMessage");
            ExtensionsKt.show(tvMessage);
        }
        if (this.time.length() > 0) {
            split$default = StringsKt__StringsKt.split$default((CharSequence) this.time, new String[]{":"}, false, 0, 6, (Object) null);
            Object[] array = split$default.toArray(new String[0]);
            Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            String[] strArr = (String[]) array;
            int i3 = R.id.timePicker;
            ((TimePicker) _$_findCachedViewById(i3)).setHour(Integer.parseInt(strArr[0]));
            ((TimePicker) _$_findCachedViewById(i3)).setMinute(Integer.parseInt(strArr[1]));
        }
        int i4 = R.id.timePicker;
        ((TimePicker) _$_findCachedViewById(i4)).setIs24HourView(Boolean.TRUE);
        FragmentActivity activity = getActivity();
        Integer valueOf = (activity == null || (resources = activity.getResources()) == null || (configuration = resources.getConfiguration()) == null) ? null : Integer.valueOf(configuration.orientation);
        Intrinsics.checkNotNull(valueOf);
        int intValue = valueOf.intValue();
        TimePicker timePicker = (TimePicker) _$_findCachedViewById(i4);
        Intrinsics.checkNotNullExpressionValue(timePicker, "timePicker");
        hideKeyboardInputInTimePicker(intValue, timePicker);
    }

    @JvmStatic
    @NotNull
    public static final TimePickerFragment newInstance(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
        return Companion.newInstance(str, str2, str3, str4);
    }

    private final void setListener() {
        ((AppCompatButton) _$_findCachedViewById(R.id.btnDone)).setOnClickListener(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.btnCancel)).setOnClickListener(this);
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
        EventBus optBus;
        MessageEvent messageEvent;
        if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnDone))) {
            OnTimeSelectListener onTimeSelectListener = this.onTimeSelectListener;
            if (onTimeSelectListener != null && onTimeSelectListener != null) {
                String str = this.pageMode;
                StringBuilder sb = new StringBuilder();
                int i2 = R.id.timePicker;
                sb.append(((TimePicker) _$_findCachedViewById(i2)).getHour());
                sb.append(AbstractJsonLexerKt.COLON);
                sb.append(((TimePicker) _$_findCachedViewById(i2)).getMinute());
                onTimeSelectListener.onTimeSelect(str, sb.toString());
            }
            EventBus optBus2 = GlobalBusUtil.Companion.optBus();
            StringBuilder sb2 = new StringBuilder();
            int i3 = R.id.timePicker;
            sb2.append(((TimePicker) _$_findCachedViewById(i3)).getHour());
            sb2.append(AbstractJsonLexerKt.COLON);
            sb2.append(((TimePicker) _$_findCachedViewById(i3)).getMinute());
            optBus2.post(new MessageEvent(sb2.toString()));
        } else {
            if (Intrinsics.areEqual(view, (AppCompatButton) _$_findCachedViewById(R.id.btnCancel))) {
                optBus = GlobalBusUtil.Companion.optBus();
                messageEvent = new MessageEvent("");
            } else if (!Intrinsics.areEqual(view, (AppCompatImageView) _$_findCachedViewById(R.id.ivClose))) {
                return;
            } else {
                optBus = GlobalBusUtil.Companion.optBus();
                messageEvent = new MessageEvent("");
            }
            optBus.post(messageEvent);
        }
        dismiss();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string = arguments.getString("title");
            Intrinsics.checkNotNull(string);
            this.title = string;
            String string2 = arguments.getString(AppConstants.ARG_MESSAGE);
            Intrinsics.checkNotNull(string2);
            this.message = string2;
            String string3 = arguments.getString(AppConstants.GEO_FENCE_TIME);
            Intrinsics.checkNotNull(string3);
            this.time = string3;
            String string4 = arguments.getString(AppConstants.ARG_PAGE_MODE);
            Intrinsics.checkNotNull(string4);
            this.pageMode = string4;
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
        return inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.fragment_time_picker, viewGroup, false);
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
        setListener();
    }

    public final void setOnTimeSelectListener(@NotNull OnTimeSelectListener onTimeSelectListener) {
        Intrinsics.checkNotNullParameter(onTimeSelectListener, "onTimeSelectListener");
        this.onTimeSelectListener = onTimeSelectListener;
    }
}
