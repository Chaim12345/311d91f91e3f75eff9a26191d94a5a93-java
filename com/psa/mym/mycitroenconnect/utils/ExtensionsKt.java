package com.psa.mym.mycitroenconnect.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.BlendModeColorFilterCompat;
import androidx.core.graphics.BlendModeCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputLayout;
import com.psa.mym.mycitroenconnect.App;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.AddContactConfirmationFragment;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.CarAccessConfirmationDialog;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.ChildAccountInvitationDialog;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.ContactSupportDialog;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.DeleteCarConfirmationDialog;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.DeleteConfirmationFragment;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.ExitFragment;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.LogoutFragment;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.OtpSuccessBottomSheetFragment;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.PinChangedFragment;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.ProfilePhotoSelectionDlgFragment;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.QuickControlFragment;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.SelectCarForViewAccessDialog;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.SpeedSettingFragment;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.TermsAndConditionsFragment;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.TimePickerFragment;
import com.psa.mym.mycitroenconnect.controller.bottom_sheet_dialog.share.ShareBottomSheetDialog;
import com.psa.mym.mycitroenconnect.controller.dialog.FullScreenAlertFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.AboutCitroenFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.AppInfoFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.ECallFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.FeedbackFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.HomeFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.NotificationSettingsFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.NotificationsFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.RoadSideAssistanceFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.SOSFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.SecurityFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.WebViewFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.charging_station.ChargingStationFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.charging_station.ChargingStationListFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.geo_fence.ChooseGeoFenceTypeFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.geo_fence.GeoFenceMapFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.geo_fence.GeoFenceMapSettingsFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.geo_fence.GeoFenceSetTimeFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.geo_fence.GeoFenceSummaryFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.geo_fence.SetFenceForFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.geo_fence.SetLocationFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.guest_user.MyProfileFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.guest_user.NonVinHomeFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.guest_user.NotificationFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.intro_screen.IntroScreenFive;
import com.psa.mym.mycitroenconnect.controller.fragments.intro_screen.IntroScreenFour;
import com.psa.mym.mycitroenconnect.controller.fragments.intro_screen.IntroScreenOne;
import com.psa.mym.mycitroenconnect.controller.fragments.intro_screen.IntroScreenThree;
import com.psa.mym.mycitroenconnect.controller.fragments.intro_screen.IntroScreenTwo;
import com.psa.mym.mycitroenconnect.controller.fragments.my_car.CarFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.my_car.MyCarHealthFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.my_car.MyCarStatusFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.my_trips.DrivingBehaviourFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.my_trips.TripFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.my_trips.TripSummaryFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.profile.ChildAccountFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.profile.MyCarsFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.profile.PersonalDetailsFragment;
import com.psa.mym.mycitroenconnect.controller.fragments.profile.ProfileFragment;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.Skeleton;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonConfig;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonLayout;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.mask.SkeletonShimmerDirection;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt__SequencesKt;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class ExtensionsKt {
    public static final void bottomToTopAnimation(@NotNull View view, @NotNull Context context, @NotNull final Function0<Unit> animationStart, @NotNull final Function0<Unit> animationEnd, @NotNull final Function0<Unit> animationRepeat) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(animationStart, "animationStart");
        Intrinsics.checkNotNullParameter(animationEnd, "animationEnd");
        Intrinsics.checkNotNullParameter(animationRepeat, "animationRepeat");
        Animation loadAnimation = AnimationUtils.loadAnimation(context, R.anim.bottom_to_original);
        loadAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.psa.mym.mycitroenconnect.utils.ExtensionsKt$bottomToTopAnimation$1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(@Nullable Animation animation) {
                animationEnd.invoke();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(@Nullable Animation animation) {
                animationRepeat.invoke();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(@Nullable Animation animation) {
                Function0.this.invoke();
            }
        });
        view.setAnimation(loadAnimation);
    }

    public static final void changeCardBackgroundColor(@NotNull MaterialCardView materialCardView, @NotNull Context context, @ColorRes int i2) {
        Intrinsics.checkNotNullParameter(materialCardView, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        materialCardView.setCardBackgroundColor(ContextCompat.getColor(context, i2));
    }

    public static final void clearDrawables(@NotNull AppCompatButton appCompatButton) {
        Intrinsics.checkNotNullParameter(appCompatButton, "<this>");
        appCompatButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }

    public static final void disableEmoji(@NotNull EditText editText) {
        Object[] plus;
        Intrinsics.checkNotNullParameter(editText, "<this>");
        InputFilter[] filters = editText.getFilters();
        Intrinsics.checkNotNullExpressionValue(filters, "filters");
        plus = ArraysKt___ArraysJvmKt.plus((c[]) filters, c.f10752a);
        editText.setFilters((InputFilter[]) plus);
    }

    /* renamed from: disableEmoji$lambda-1 */
    public static final CharSequence m163disableEmoji$lambda1(CharSequence source, int i2, int i3, Spanned spanned, int i4, int i5) {
        Intrinsics.checkNotNullExpressionValue(source, "source");
        StringBuilder sb = new StringBuilder();
        int length = source.length();
        for (int i6 = 0; i6 < length; i6++) {
            char charAt = source.charAt(i6);
            if ((Character.getType(charAt) == 19 || Character.getType(charAt) == 28) ? false : true) {
                sb.append(charAt);
            }
        }
        return sb;
    }

    public static final void drawableBottom(@NotNull TextView textView, @DrawableRes int i2) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, i2);
    }

    public static final void drawableBottom(@NotNull TextView textView, @DrawableRes int i2, @DimenRes int i3, @ColorInt int i4, @ColorRes int i5) {
        ColorFilter createBlendModeColorFilterCompat;
        Intrinsics.checkNotNullParameter(textView, "<this>");
        Drawable drawable = ContextCompat.getDrawable(textView.getContext(), i2);
        if (i3 != 0) {
            int dimensionPixelSize = textView.getResources().getDimensionPixelSize(i3);
            if (drawable != null) {
                drawable.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            }
        }
        if (i4 != 0) {
            if (drawable != null) {
                createBlendModeColorFilterCompat = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(i4, BlendModeCompat.SRC_ATOP);
                drawable.setColorFilter(createBlendModeColorFilterCompat);
            }
        } else if (i5 != 0) {
            int color = ContextCompat.getColor(textView.getContext(), i5);
            if (drawable != null) {
                createBlendModeColorFilterCompat = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(color, BlendModeCompat.SRC_ATOP);
                drawable.setColorFilter(createBlendModeColorFilterCompat);
            }
        }
        textView.setCompoundDrawables(null, null, null, drawable);
    }

    public static /* synthetic */ void drawableBottom$default(TextView textView, int i2, int i3, int i4, int i5, int i6, Object obj) {
        if ((i6 & 1) != 0) {
            i2 = 0;
        }
        if ((i6 & 2) != 0) {
            i3 = 0;
        }
        if ((i6 & 4) != 0) {
            i4 = 0;
        }
        if ((i6 & 8) != 0) {
            i5 = 0;
        }
        drawableBottom(textView, i2, i3, i4, i5);
    }

    public static /* synthetic */ void drawableBottom$default(TextView textView, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = 0;
        }
        drawableBottom(textView, i2);
    }

    public static final void drawableLeft(@NotNull TextView textView, @DrawableRes int i2) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        textView.setCompoundDrawablesWithIntrinsicBounds(i2, 0, 0, 0);
    }

    public static final void drawableLeft(@NotNull TextView textView, @DrawableRes int i2, @DimenRes int i3, @ColorInt int i4, @ColorRes int i5) {
        ColorFilter createBlendModeColorFilterCompat;
        Intrinsics.checkNotNullParameter(textView, "<this>");
        Drawable drawable = ContextCompat.getDrawable(textView.getContext(), i2);
        if (i3 != 0) {
            int dimensionPixelSize = textView.getResources().getDimensionPixelSize(i3);
            if (drawable != null) {
                drawable.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            }
        }
        if (i4 != 0) {
            if (drawable != null) {
                createBlendModeColorFilterCompat = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(i4, BlendModeCompat.SRC_ATOP);
                drawable.setColorFilter(createBlendModeColorFilterCompat);
            }
        } else if (i5 != 0) {
            int color = ContextCompat.getColor(textView.getContext(), i5);
            if (drawable != null) {
                createBlendModeColorFilterCompat = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(color, BlendModeCompat.SRC_ATOP);
                drawable.setColorFilter(createBlendModeColorFilterCompat);
            }
        }
        textView.setCompoundDrawables(drawable, null, null, null);
    }

    public static /* synthetic */ void drawableLeft$default(TextView textView, int i2, int i3, int i4, int i5, int i6, Object obj) {
        if ((i6 & 1) != 0) {
            i2 = 0;
        }
        if ((i6 & 2) != 0) {
            i3 = 0;
        }
        if ((i6 & 4) != 0) {
            i4 = 0;
        }
        if ((i6 & 8) != 0) {
            i5 = 0;
        }
        drawableLeft(textView, i2, i3, i4, i5);
    }

    public static /* synthetic */ void drawableLeft$default(TextView textView, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = 0;
        }
        drawableLeft(textView, i2);
    }

    public static final void drawableRight(@NotNull TextView textView, @DrawableRes int i2) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, i2, 0);
    }

    public static final void drawableRight(@NotNull TextView textView, @DrawableRes int i2, @DimenRes int i3, @ColorInt int i4, @ColorRes int i5) {
        ColorFilter createBlendModeColorFilterCompat;
        Intrinsics.checkNotNullParameter(textView, "<this>");
        Drawable drawable = ContextCompat.getDrawable(textView.getContext(), i2);
        if (i3 != 0) {
            int dimensionPixelSize = textView.getResources().getDimensionPixelSize(i3);
            if (drawable != null) {
                drawable.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            }
        }
        if (i4 != 0) {
            if (drawable != null) {
                createBlendModeColorFilterCompat = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(i4, BlendModeCompat.SRC_ATOP);
                drawable.setColorFilter(createBlendModeColorFilterCompat);
            }
        } else if (i5 != 0) {
            int color = ContextCompat.getColor(textView.getContext(), i5);
            if (drawable != null) {
                createBlendModeColorFilterCompat = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(color, BlendModeCompat.SRC_ATOP);
                drawable.setColorFilter(createBlendModeColorFilterCompat);
            }
        }
        textView.setCompoundDrawables(null, null, drawable, null);
    }

    public static /* synthetic */ void drawableRight$default(TextView textView, int i2, int i3, int i4, int i5, int i6, Object obj) {
        if ((i6 & 1) != 0) {
            i2 = 0;
        }
        if ((i6 & 2) != 0) {
            i3 = 0;
        }
        if ((i6 & 4) != 0) {
            i4 = 0;
        }
        if ((i6 & 8) != 0) {
            i5 = 0;
        }
        drawableRight(textView, i2, i3, i4, i5);
    }

    public static /* synthetic */ void drawableRight$default(TextView textView, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = 0;
        }
        drawableRight(textView, i2);
    }

    public static final void drawableTop(@NotNull TextView textView, @DrawableRes int i2) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        textView.setCompoundDrawablesWithIntrinsicBounds(0, i2, 0, 0);
    }

    public static final void drawableTop(@NotNull TextView textView, @DrawableRes int i2, @DimenRes int i3, @ColorInt int i4, @ColorRes int i5) {
        ColorFilter createBlendModeColorFilterCompat;
        Intrinsics.checkNotNullParameter(textView, "<this>");
        Drawable drawable = ContextCompat.getDrawable(textView.getContext(), i2);
        if (i3 != 0) {
            int dimensionPixelSize = textView.getResources().getDimensionPixelSize(i3);
            if (drawable != null) {
                drawable.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            }
        }
        if (i4 != 0) {
            if (drawable != null) {
                createBlendModeColorFilterCompat = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(i4, BlendModeCompat.SRC_ATOP);
                drawable.setColorFilter(createBlendModeColorFilterCompat);
            }
        } else if (i5 != 0) {
            int color = ContextCompat.getColor(textView.getContext(), i5);
            if (drawable != null) {
                createBlendModeColorFilterCompat = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(color, BlendModeCompat.SRC_ATOP);
                drawable.setColorFilter(createBlendModeColorFilterCompat);
            }
        }
        textView.setCompoundDrawables(null, drawable, null, null);
    }

    public static /* synthetic */ void drawableTop$default(TextView textView, int i2, int i3, int i4, int i5, int i6, Object obj) {
        if ((i6 & 1) != 0) {
            i2 = 0;
        }
        if ((i6 & 2) != 0) {
            i3 = 0;
        }
        if ((i6 & 4) != 0) {
            i4 = 0;
        }
        if ((i6 & 8) != 0) {
            i5 = 0;
        }
        drawableTop(textView, i2, i3, i4, i5);
    }

    public static /* synthetic */ void drawableTop$default(TextView textView, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = 0;
        }
        drawableTop(textView, i2);
    }

    public static final void fadeIn(@NotNull View view, @NotNull final Function0<Unit> animationStart, @NotNull final Function0<Unit> animationEnd) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(animationStart, "animationStart");
        Intrinsics.checkNotNullParameter(animationEnd, "animationEnd");
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f);
        ofFloat.setDuration(2500L);
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.psa.mym.mycitroenconnect.utils.ExtensionsKt$fadeIn$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(@Nullable Animator animator) {
                super.onAnimationEnd(animator);
                animationEnd.invoke();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(@Nullable Animator animator) {
                super.onAnimationStart(animator);
                Function0.this.invoke();
            }
        });
        ofFloat.start();
    }

    public static final void fadeOut(@NotNull View view, @NotNull final Function0<Unit> animationStart, @NotNull final Function0<Unit> animationEnd) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(animationStart, "animationStart");
        Intrinsics.checkNotNullParameter(animationEnd, "animationEnd");
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f);
        ofFloat.setDuration(SkeletonLayout.DEFAULT_SHIMMER_DURATION_IN_MILLIS);
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.psa.mym.mycitroenconnect.utils.ExtensionsKt$fadeOut$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(@Nullable Animator animator) {
                super.onAnimationEnd(animator);
                animationEnd.invoke();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(@Nullable Animator animator) {
                super.onAnimationStart(animator);
                Function0.this.invoke();
            }
        });
        ofFloat.start();
    }

    @Nullable
    public static final Fragment findCurrentFragment(@NotNull ViewPager2 viewPager2, @NotNull FragmentManager fragmentManager) {
        Intrinsics.checkNotNullParameter(viewPager2, "<this>");
        Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
        StringBuilder sb = new StringBuilder();
        sb.append('f');
        sb.append(viewPager2.getCurrentItem());
        return fragmentManager.findFragmentByTag(sb.toString());
    }

    @Nullable
    public static final Fragment findFragmentAtPosition(@NotNull ViewPager2 viewPager2, @NotNull FragmentManager fragmentManager, int i2) {
        Intrinsics.checkNotNullParameter(viewPager2, "<this>");
        Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
        StringBuilder sb = new StringBuilder();
        sb.append('f');
        sb.append(i2);
        return fragmentManager.findFragmentByTag(sb.toString());
    }

    @NotNull
    public static final String format(double d2, int i2) {
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(i2);
        decimalFormat.setRoundingMode(RoundingMode.HALF_EVEN);
        String format = decimalFormat.format(d2);
        Intrinsics.checkNotNullExpressionValue(format, "df.format(this)");
        return format;
    }

    public static final int getDp(int i2) {
        return (int) (i2 / Resources.getSystem().getDisplayMetrics().density);
    }

    @NotNull
    public static final String getFenceStatus(@NotNull SwitchCompat switchCompat) {
        Intrinsics.checkNotNullParameter(switchCompat, "<this>");
        return switchCompat.isChecked() ? ExifInterface.GPS_MEASUREMENT_IN_PROGRESS : "D";
    }

    @NotNull
    public static final String getLastUpdatedDate(@NotNull Date date) {
        Intrinsics.checkNotNullParameter(date, "<this>");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
        return toSimpleString(date, true == isToday(calendar) ? AppConstants.TIME_FORMAT_HH_mm_a : true == isCurrentYear(calendar) ? AppConstants.DISPLAY_DATE_FORMAT_WITHOUT_YEAR : AppConstants.DATE_FORMAT_DD_MM_YY_HH_mm_a);
    }

    @NotNull
    public static final App getMyApp(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Context applicationContext = context.getApplicationContext();
        Objects.requireNonNull(applicationContext, "null cannot be cast to non-null type com.psa.mym.mycitroenconnect.App");
        return (App) applicationContext;
    }

    @Nullable
    public static final String getName(@Nullable Fragment fragment) {
        return fragment != null ? fragment instanceof AboutCitroenFragment ? "AboutCitroenFragment" : fragment instanceof AppInfoFragment ? "AppInfoFragment" : fragment instanceof CarFragment ? "CarFragment" : fragment instanceof ChildAccountFragment ? "ChildAccountFragment" : fragment instanceof DrivingBehaviourFragment ? "DrivingBehaviourFragment" : fragment instanceof ECallFragment ? "ECallFragment" : fragment instanceof FeedbackFragment ? "FeedbackFragment" : fragment instanceof HomeFragment ? "HomeFragment" : fragment instanceof IntroScreenFive ? "IntroScreenFive" : fragment instanceof IntroScreenFour ? "IntroScreenFour" : fragment instanceof IntroScreenOne ? "IntroScreenOne" : fragment instanceof IntroScreenThree ? "IntroScreenThree" : fragment instanceof IntroScreenTwo ? "IntroScreenTwo" : fragment instanceof MyCarHealthFragment ? "MyCarHealthFragment" : fragment instanceof MyCarsFragment ? "MyCarsFragment" : fragment instanceof MyCarStatusFragment ? "MyCarStatusFragment" : fragment instanceof MyProfileFragment ? "MyProfileFragment" : fragment instanceof NonVinHomeFragment ? "NonVinHomeFragment" : fragment instanceof NotificationFragment ? "NotificationFragment" : fragment instanceof NotificationSettingsFragment ? "NotificationSettingsFragment" : fragment instanceof NotificationsFragment ? "NotificationsFragment" : fragment instanceof PersonalDetailsFragment ? "PersonalDetailsFragment" : fragment instanceof ProfileFragment ? "ProfileFragment" : fragment instanceof RoadSideAssistanceFragment ? "RoadSideAssistanceFragment" : fragment instanceof SecurityFragment ? "SecurityFragment" : fragment instanceof SOSFragment ? "SOSFragment" : fragment instanceof TripFragment ? "TripFragment" : fragment instanceof TripSummaryFragment ? "TripSummaryFragment" : fragment instanceof WebViewFragment ? "WebViewFragment" : fragment instanceof ChargingStationFragment ? "ChargingStationFragment" : fragment instanceof ChargingStationListFragment ? "ChargingStationListFragment" : fragment instanceof GeoFenceMapFragment ? "GeoFenceMapFragment" : fragment instanceof GeoFenceMapSettingsFragment ? "GeoFenceMapSettingsFragment" : fragment instanceof GeoFenceSummaryFragment ? "GeoFenceSummaryFragment" : fragment instanceof ChooseGeoFenceTypeFragment ? "NewChooseGeoFenceTypeFragment" : fragment instanceof GeoFenceSetTimeFragment ? "NewGeoFenceSetTimeFragment" : fragment instanceof SetFenceForFragment ? "SetFenceForFragment" : fragment instanceof SetLocationFragment ? "SetLocationFragment" : fragment instanceof FullScreenAlertFragment ? "FullScreenAlertFragment" : fragment instanceof AddContactConfirmationFragment ? AddContactConfirmationFragment.TAG : fragment instanceof CarAccessConfirmationDialog ? CarAccessConfirmationDialog.TAG : fragment instanceof ChildAccountInvitationDialog ? ChildAccountInvitationDialog.TAG : fragment instanceof ContactSupportDialog ? ContactSupportDialog.TAG : fragment instanceof DeleteCarConfirmationDialog ? DeleteCarConfirmationDialog.TAG : fragment instanceof DeleteConfirmationFragment ? DeleteConfirmationFragment.TAG : fragment instanceof ExitFragment ? ExitFragment.TAG : fragment instanceof LogoutFragment ? LogoutFragment.TAG : fragment instanceof OtpSuccessBottomSheetFragment ? OtpSuccessBottomSheetFragment.TAG : fragment instanceof PinChangedFragment ? PinChangedFragment.TAG : fragment instanceof ProfilePhotoSelectionDlgFragment ? ProfilePhotoSelectionDlgFragment.TAG : fragment instanceof QuickControlFragment ? "QuickControlFragment" : fragment instanceof SelectCarForViewAccessDialog ? SelectCarForViewAccessDialog.TAG : fragment instanceof SpeedSettingFragment ? SpeedSettingFragment.TAG : fragment instanceof ShareBottomSheetDialog ? ShareBottomSheetDialog.TAG : fragment instanceof TermsAndConditionsFragment ? "TermsAndConditionsFragment" : fragment instanceof TimePickerFragment ? TimePickerFragment.TAG : Reflection.getOrCreateKotlinClass(fragment.getClass()).getSimpleName() : "NullFragment";
    }

    @NotNull
    public static final String getNotificationDate(@NotNull Date date) {
        Intrinsics.checkNotNullParameter(date, "<this>");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
        return toSimpleString(date, true == isToday(calendar) ? AppConstants.TIME_FORMAT_HH_mm_a : true == isCurrentYear(calendar) ? AppConstants.SERVER_DATE_FORMAT_WITHOUT_YEAR : AppConstants.DATE_FORMAT_DD_MM_YY);
    }

    public static final int getPx(int i2) {
        return (int) (i2 * Resources.getSystem().getDisplayMetrics().density);
    }

    public static final int getResponseCount(@NotNull Response response) {
        Sequence generateSequence;
        int count;
        Intrinsics.checkNotNullParameter(response, "<this>");
        generateSequence = SequencesKt__SequencesKt.generateSequence(response, ExtensionsKt$responseCount$1.INSTANCE);
        count = SequencesKt___SequencesKt.count(generateSequence);
        return count;
    }

    @NotNull
    public static final String getTimeAgo(@NotNull Date date, @NotNull Context context) {
        String quantityString;
        String str;
        Intrinsics.checkNotNullParameter(date, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        float currentTimeMillis = ((float) (System.currentTimeMillis() - date.getTime())) / 1000.0f;
        if (true == (currentTimeMillis <= 0.0f)) {
            return "Just Now";
        }
        if (true == (currentTimeMillis < 60.0f)) {
            int i2 = (int) currentTimeMillis;
            quantityString = context.getResources().getQuantityString(R.plurals.seconds_ago, i2, Integer.valueOf(i2));
            str = "context.resources.getQua…seconds.toInt()\n        )";
        } else {
            if (true == (currentTimeMillis < 3600.0f)) {
                int i3 = (int) (currentTimeMillis / 60.0f);
                quantityString = context.getResources().getQuantityString(R.plurals.minutes_ago, i3, Integer.valueOf(i3));
                str = "{\n            val minute…)\n            )\n        }";
            } else {
                if (true == (currentTimeMillis < 86400.0f)) {
                    int i4 = (int) (currentTimeMillis / 3600.0f);
                    quantityString = context.getResources().getQuantityString(R.plurals.hours_ago, i4, Integer.valueOf(i4));
                    str = "{\n            val hours … hours.toInt())\n        }";
                } else {
                    if (true == (currentTimeMillis < 84600.0f)) {
                        quantityString = new SimpleDateFormat("hh:mm aa", AppUtil.Companion.getDefaultLocale()).format(Long.valueOf(System.currentTimeMillis()));
                        str = "{\n            val sdf = …ntTimeMillis())\n        }";
                    } else {
                        if (true == (currentTimeMillis < 604800.0f)) {
                            int i5 = (int) (currentTimeMillis / 86400.0f);
                            quantityString = context.getResources().getQuantityString(R.plurals.days_ago, i5, Integer.valueOf(i5));
                            str = "{\n            val days =…, days.toInt())\n        }";
                        } else {
                            if (true == (currentTimeMillis < 2628000.0f)) {
                                int i6 = (int) (currentTimeMillis / 604800.0f);
                                quantityString = context.getResources().getQuantityString(R.plurals.weeks_ago, i6, Integer.valueOf(i6));
                                str = "{\n            val weeks … weeks.toInt())\n        }";
                            } else {
                                if (true == (currentTimeMillis < 3.1536E7f)) {
                                    int i7 = (int) (currentTimeMillis / 2628000.0f);
                                    quantityString = context.getResources().getQuantityString(R.plurals.months_ago, i7, Integer.valueOf(i7));
                                    str = "{\n            val months…)\n            )\n        }";
                                } else {
                                    int i8 = (int) (currentTimeMillis / 3.1536E7f);
                                    quantityString = context.getResources().getQuantityString(R.plurals.years_ago, i8, Integer.valueOf(i8));
                                    str = "{\n            val years …)\n            )\n        }";
                                }
                            }
                        }
                    }
                }
            }
        }
        Intrinsics.checkNotNullExpressionValue(quantityString, str);
        return quantityString;
    }

    public static final void hide(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(8);
    }

    public static final void hideKeyboard(@NotNull Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        Object systemService = activity.getSystemService("input_method");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        ((InputMethodManager) systemService).toggleSoftInput(1, 0);
    }

    public static final void inFromLeftAnimation(@NotNull View view, @Nullable Animation.AnimationListener animationListener) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        TranslateAnimation translateAnimation = new TranslateAnimation(2, -1.0f, 2, 0.0f, 2, 0.0f, 2, 0.0f);
        translateAnimation.setDuration(1000L);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        translateAnimation.setAnimationListener(animationListener);
        view.startAnimation(translateAnimation);
    }

    public static /* synthetic */ void inFromLeftAnimation$default(View view, Animation.AnimationListener animationListener, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            animationListener = null;
        }
        inFromLeftAnimation(view, animationListener);
    }

    public static final void inFromRightAnimation(@NotNull View view, @Nullable Animation.AnimationListener animationListener) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        TranslateAnimation translateAnimation = new TranslateAnimation(2, 1.0f, 2, 0.0f, 2, 0.0f, 2, 0.0f);
        translateAnimation.setDuration(500L);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        translateAnimation.setAnimationListener(animationListener);
        view.startAnimation(translateAnimation);
    }

    public static /* synthetic */ void inFromRightAnimation$default(View view, Animation.AnimationListener animationListener, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            animationListener = null;
        }
        inFromRightAnimation(view, animationListener);
    }

    public static final void invisible(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(4);
    }

    public static final boolean isCurrentYear(@NotNull Calendar calendar) {
        Intrinsics.checkNotNullParameter(calendar, "<this>");
        return Calendar.getInstance().get(1) == calendar.get(1);
    }

    public static final boolean isFirstPage(@NotNull ViewPager viewPager) {
        Intrinsics.checkNotNullParameter(viewPager, "<this>");
        return viewPager.getCurrentItem() == 0;
    }

    public static final boolean isLastPage(@NotNull ViewPager viewPager) {
        Intrinsics.checkNotNullParameter(viewPager, "<this>");
        PagerAdapter adapter = viewPager.getAdapter();
        if (adapter != null) {
            if (viewPager.getCurrentItem() == adapter.getCount() - 1) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isMaxProgress(@NotNull SeekBar seekBar) {
        Intrinsics.checkNotNullParameter(seekBar, "<this>");
        return seekBar.getProgress() >= seekBar.getMax();
    }

    public static final boolean isMinProgress(@NotNull SeekBar seekBar) {
        Intrinsics.checkNotNullParameter(seekBar, "<this>");
        return seekBar.getProgress() <= 0;
    }

    public static final boolean isPastDate(@NotNull Calendar calendar) {
        Intrinsics.checkNotNullParameter(calendar, "<this>");
        return calendar.getTime().before(Calendar.getInstance().getTime());
    }

    public static final boolean isScreenOn(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Object systemService = context.getSystemService("keyguard");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.app.KeyguardManager");
        if (((KeyguardManager) systemService).isKeyguardLocked()) {
            return true;
        }
        Object systemService2 = context.getSystemService("power");
        Objects.requireNonNull(systemService2, "null cannot be cast to non-null type android.os.PowerManager");
        return !((PowerManager) systemService2).isInteractive();
    }

    public static final boolean isToday(long j2) {
        return DateUtils.isToday(j2);
    }

    public static final boolean isToday(@NotNull Calendar calendar) {
        Intrinsics.checkNotNullParameter(calendar, "<this>");
        Calendar calendar2 = Calendar.getInstance();
        return calendar2.get(1) == calendar.get(1) && calendar2.get(2) == calendar.get(2) && calendar2.get(5) == calendar.get(5);
    }

    public static final boolean isVisible(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return view.getVisibility() == 0;
    }

    @NotNull
    public static final String myText(@NotNull EditText editText) {
        CharSequence trim;
        Intrinsics.checkNotNullParameter(editText, "<this>");
        trim = StringsKt__StringsKt.trim((CharSequence) editText.getText().toString());
        return trim.toString();
    }

    public static final void onChange(@NotNull EditText editText, @NotNull final Function1<? super String, Unit> cb) {
        Intrinsics.checkNotNullParameter(editText, "<this>");
        Intrinsics.checkNotNullParameter(cb, "cb");
        editText.addTextChangedListener(new TextWatcher() { // from class: com.psa.mym.mycitroenconnect.utils.ExtensionsKt$onChange$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
                Function1.this.invoke(String.valueOf(editable));
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
            }
        });
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public static final void onRightDrawableClicked(@NotNull final AppCompatEditText appCompatEditText, @NotNull final Function1<? super AppCompatEditText, Unit> onClicked) {
        Intrinsics.checkNotNullParameter(appCompatEditText, "<this>");
        Intrinsics.checkNotNullParameter(onClicked, "onClicked");
        appCompatEditText.setOnTouchListener(new View.OnTouchListener() { // from class: com.psa.mym.mycitroenconnect.utils.d
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                boolean m164onRightDrawableClicked$lambda2;
                m164onRightDrawableClicked$lambda2 = ExtensionsKt.m164onRightDrawableClicked$lambda2(Function1.this, appCompatEditText, view, motionEvent);
                return m164onRightDrawableClicked$lambda2;
            }
        });
    }

    /* renamed from: onRightDrawableClicked$lambda-2 */
    public static final boolean m164onRightDrawableClicked$lambda2(Function1 onClicked, AppCompatEditText this_onRightDrawableClicked, View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(onClicked, "$onClicked");
        Intrinsics.checkNotNullParameter(this_onRightDrawableClicked, "$this_onRightDrawableClicked");
        if (view instanceof EditText) {
            EditText editText = (EditText) view;
            if (motionEvent.getX() >= editText.getWidth() - editText.getTotalPaddingRight()) {
                if (motionEvent.getAction() == 1) {
                    onClicked.invoke(this_onRightDrawableClicked);
                    return true;
                }
                return true;
            }
        }
        return false;
    }

    public static final void onSquareBtnDeselect(@NotNull AppCompatButton appCompatButton, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(appCompatButton, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        appCompatButton.setSelected(false);
        appCompatButton.setBackground(AppCompatResources.getDrawable(context, R.drawable.rounded_button_background_white));
        appCompatButton.setTextColor(context.getColor(R.color.dark_grey));
    }

    public static final void onSquareBtnSelected(@NotNull AppCompatButton appCompatButton, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(appCompatButton, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        appCompatButton.setSelected(true);
        appCompatButton.setBackground(AppCompatResources.getDrawable(context, R.drawable.ic_btn_white_select_red_tint_pressed));
        appCompatButton.setTextColor(context.getColor(R.color.primary_color_1));
    }

    public static final void outToLeftAnimation(@NotNull View view, @Nullable Animation.AnimationListener animationListener) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        TranslateAnimation translateAnimation = new TranslateAnimation(2, 0.0f, 2, -1.0f, 2, 0.0f, 2, 0.0f);
        translateAnimation.setDuration(500L);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        translateAnimation.setAnimationListener(animationListener);
        view.startAnimation(translateAnimation);
    }

    public static /* synthetic */ void outToLeftAnimation$default(View view, Animation.AnimationListener animationListener, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            animationListener = null;
        }
        outToLeftAnimation(view, animationListener);
    }

    public static final void outToRightAnimation(@NotNull View view, @Nullable Animation.AnimationListener animationListener) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        TranslateAnimation translateAnimation = new TranslateAnimation(2, 0.0f, 2, 1.0f, 2, 0.0f, 2, 0.0f);
        translateAnimation.setDuration(1000L);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        translateAnimation.setAnimationListener(animationListener);
        view.startAnimation(translateAnimation);
    }

    public static /* synthetic */ void outToRightAnimation$default(View view, Animation.AnimationListener animationListener, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            animationListener = null;
        }
        outToRightAnimation(view, animationListener);
    }

    public static final void popBackCancel(@NotNull Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intent intent = new Intent(fragment.requireContext(), fragment.getClass());
        fragment.requireActivity().getSupportFragmentManager().popBackStack();
        Fragment targetFragment = fragment.getTargetFragment();
        Intrinsics.checkNotNull(targetFragment);
        targetFragment.onActivityResult(fragment.getTargetRequestCode(), 0, intent);
    }

    public static final void popBackOk(@NotNull Fragment fragment, @NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.checkNotNullParameter(intent, "intent");
        fragment.requireActivity().getSupportFragmentManager().popBackStack();
        Fragment targetFragment = fragment.getTargetFragment();
        if (targetFragment != null) {
            targetFragment.onActivityResult(fragment.getTargetRequestCode(), -1, intent);
        }
    }

    public static /* synthetic */ void popBackOk$default(Fragment fragment, Intent intent, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            intent = new Intent(fragment.requireContext(), fragment.getClass());
        }
        popBackOk(fragment, intent);
    }

    public static final void removeError(@NotNull EditText editText, @NotNull final TextInputLayout inputLayout) {
        Intrinsics.checkNotNullParameter(editText, "<this>");
        Intrinsics.checkNotNullParameter(inputLayout, "inputLayout");
        editText.addTextChangedListener(new TextWatcher() { // from class: com.psa.mym.mycitroenconnect.utils.ExtensionsKt$removeError$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i2, int i3, int i4) {
                if (TextInputLayout.this.getError() != null) {
                    TextInputLayout.this.setError(null);
                }
            }
        });
    }

    public static final void replaceFragment(@NotNull Fragment fragment, boolean z, int i2, @NotNull FragmentManager childSupportFragment) {
        StringBuilder sb;
        String str;
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.checkNotNullParameter(childSupportFragment, "childSupportFragment");
        Logger logger = Logger.INSTANCE;
        logger.i(getName(fragment) + "CURRENT_FRAGMENT:" + fragment);
        FragmentTransaction beginTransaction = childSupportFragment.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(beginTransaction, "childSupportFragment.beginTransaction()");
        beginTransaction.replace(i2, fragment);
        String name = getName(fragment);
        if (z) {
            beginTransaction.addToBackStack(getName(fragment));
            sb = new StringBuilder();
            sb.append(getName(fragment));
            sb.append("Fragment Back Stack : ");
            sb.append(name);
            str = " Added";
        } else {
            sb = new StringBuilder();
            sb.append(getName(fragment));
            sb.append("Fragment Back Stack : ");
            sb.append(name);
            str = " Not Added";
        }
        sb.append(str);
        logger.d(sb.toString());
        beginTransaction.commit();
    }

    public static /* synthetic */ void replaceFragment$default(Fragment fragment, boolean z, int i2, FragmentManager fragmentManager, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            z = false;
        }
        replaceFragment(fragment, z, i2, fragmentManager);
    }

    public static final void rotateAnimation(@NotNull View view, long j2, int i2, @NotNull final Function0<Unit> animationStart, @NotNull final Function0<Unit> animationEnd, @NotNull final Function0<Unit> animationRepeat) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(animationStart, "animationStart");
        Intrinsics.checkNotNullParameter(animationEnd, "animationEnd");
        Intrinsics.checkNotNullParameter(animationRepeat, "animationRepeat");
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 359.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(j2);
        rotateAnimation.setRepeatCount(i2);
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.psa.mym.mycitroenconnect.utils.ExtensionsKt$rotateAnimation$1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(@Nullable Animation animation) {
                animationEnd.invoke();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(@Nullable Animation animation) {
                animationRepeat.invoke();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(@Nullable Animation animation) {
                Function0.this.invoke();
            }
        });
        view.startAnimation(rotateAnimation);
    }

    public static /* synthetic */ void rotateAnimation$default(View view, long j2, int i2, Function0 function0, Function0 function02, Function0 function03, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            j2 = 250;
        }
        long j3 = j2;
        if ((i3 & 2) != 0) {
            i2 = 5;
        }
        rotateAnimation(view, j3, i2, function0, function02, function03);
    }

    @NotNull
    public static final String roundOffTo1(double d2) {
        boolean contains$default;
        String format;
        CharSequence trim;
        contains$default = StringsKt__StringsKt.contains$default((CharSequence) String.valueOf(d2), (CharSequence) ".0", false, 2, (Object) null);
        if (contains$default) {
            format = StringsKt__StringsJVMKt.replace$default(String.valueOf(d2), ".0", "", false, 4, (Object) null);
        } else {
            format = new DecimalFormat("##.#").format(d2);
            Intrinsics.checkNotNullExpressionValue(format, "df.format(this)");
        }
        trim = StringsKt__StringsKt.trim((CharSequence) format);
        return trim.toString();
    }

    public static final void setDivider(@NotNull RecyclerView recyclerView, @DrawableRes int i2) {
        Intrinsics.checkNotNullParameter(recyclerView, "<this>");
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), 1);
        Drawable drawable = ContextCompat.getDrawable(recyclerView.getContext(), i2);
        if (drawable != null) {
            dividerItemDecoration.setDrawable(drawable);
            recyclerView.addItemDecoration(dividerItemDecoration);
        }
    }

    public static final void setDrawable(@NotNull ImageView imageView, @DrawableRes int i2) {
        Intrinsics.checkNotNullParameter(imageView, "<this>");
        imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), i2));
    }

    public static final void setMaxLength(@NotNull EditText editText, int i2) {
        Object[] plus;
        Intrinsics.checkNotNullParameter(editText, "<this>");
        InputFilter[] filters = editText.getFilters();
        Intrinsics.checkNotNullExpressionValue(filters, "filters");
        plus = ArraysKt___ArraysJvmKt.plus((InputFilter.LengthFilter[]) filters, new InputFilter.LengthFilter(i2));
        editText.setFilters((InputFilter[]) plus);
    }

    public static /* synthetic */ void setMaxLength$default(EditText editText, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = 25;
        }
        setMaxLength(editText, i2);
    }

    public static final void show(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(0);
    }

    public static final void showData(@NotNull final Skeleton skeleton, long j2) {
        Intrinsics.checkNotNullParameter(skeleton, "<this>");
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.psa.mym.mycitroenconnect.utils.e
            @Override // java.lang.Runnable
            public final void run() {
                ExtensionsKt.m165showData$lambda4(Skeleton.this);
            }
        }, j2);
    }

    public static /* synthetic */ void showData$default(Skeleton skeleton, long j2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j2 = 5;
        }
        showData(skeleton, j2);
    }

    /* renamed from: showData$lambda-4 */
    public static final void m165showData$lambda4(Skeleton this_showData) {
        Intrinsics.checkNotNullParameter(this_showData, "$this_showData");
        this_showData.showOriginal();
    }

    public static final void showKeyboard(@NotNull Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        Object systemService = activity.getSystemService("input_method");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        ((InputMethodManager) systemService).toggleSoftInput(2, 0);
    }

    public static final void showToast(@NotNull Context context, @NotNull String msg) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(msg, "msg");
        Toast.makeText(context, msg, 0).show();
    }

    @NotNull
    public static final SkeletonConfig skeletonConfig(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return new SkeletonConfig(ContextCompat.getColor(context, R.color.skeleton_mask), 25.0f, true, ContextCompat.getColor(context, R.color.skeleton_shimmer), SkeletonLayout.DEFAULT_SHIMMER_DURATION_IN_MILLIS, SkeletonShimmerDirection.LEFT_TO_RIGHT, 45);
    }

    public static final void tint(@NotNull Drawable drawable, @ColorInt int i2) {
        Intrinsics.checkNotNullParameter(drawable, "<this>");
        drawable.setColorFilter(BlendModeColorFilterCompat.createBlendModeColorFilterCompat(i2, BlendModeCompat.SRC_ATOP));
    }

    public static /* synthetic */ void tint$default(Drawable drawable, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = 0;
        }
        tint(drawable, i2);
    }

    @NotNull
    public static final String toKM(double d2) {
        try {
            StringBuilder sb = new StringBuilder();
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("%.0f", Arrays.copyOf(new Object[]{Double.valueOf(d2)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            sb.append(format);
            sb.append(" KM");
            return sb.toString();
        } catch (Exception unused) {
            return "0 KM";
        }
    }

    @NotNull
    public static final String toOneDecimal(double d2) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%.1f", Arrays.copyOf(new Object[]{Double.valueOf(d2)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return String.valueOf(Double.parseDouble(format));
    }

    @NotNull
    public static final String toSimpleString(@NotNull Date date, @NotNull String pattern) {
        Intrinsics.checkNotNullParameter(date, "<this>");
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        String format = new SimpleDateFormat(pattern, AppUtil.Companion.getDefaultLocale()).format(date);
        Intrinsics.checkNotNullExpressionValue(format, "format.format(this)");
        return format;
    }

    @NotNull
    public static final String toTwoDecimal(double d2) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%.2f", Arrays.copyOf(new Object[]{Double.valueOf(d2)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return String.valueOf(Double.parseDouble(format));
    }

    public static final void turnScreenOffAndKeyguardOn(@NotNull Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        if (Build.VERSION.SDK_INT < 27) {
            activity.getWindow().clearFlags(129);
            return;
        }
        activity.setShowWhenLocked(false);
        activity.setTurnScreenOn(false);
    }

    public static final void turnScreenOnAndKeyguardOff(@NotNull Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 27) {
            activity.setShowWhenLocked(true);
            activity.setTurnScreenOn(true);
        } else {
            activity.getWindow().addFlags(129);
        }
        Object systemService = activity.getSystemService("keyguard");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.app.KeyguardManager");
        KeyguardManager keyguardManager = (KeyguardManager) systemService;
        if (i2 >= 26) {
            keyguardManager.requestDismissKeyguard(activity, null);
        }
    }

    public static final void zoomOutAnimation(@NotNull View view, @NotNull Context context, @NotNull final Function0<Unit> animationStart, @NotNull final Function0<Unit> animationEnd, @NotNull final Function0<Unit> animationRepeat) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(animationStart, "animationStart");
        Intrinsics.checkNotNullParameter(animationEnd, "animationEnd");
        Intrinsics.checkNotNullParameter(animationRepeat, "animationRepeat");
        Animation loadAnimation = AnimationUtils.loadAnimation(context, R.anim.zoom_out);
        loadAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.psa.mym.mycitroenconnect.utils.ExtensionsKt$zoomOutAnimation$1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(@Nullable Animation animation) {
                animationEnd.invoke();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(@Nullable Animation animation) {
                animationRepeat.invoke();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(@Nullable Animation animation) {
                Function0.this.invoke();
            }
        });
        view.setAnimation(loadAnimation);
    }
}
