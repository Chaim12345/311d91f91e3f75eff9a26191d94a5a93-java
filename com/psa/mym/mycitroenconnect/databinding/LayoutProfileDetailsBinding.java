package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import de.hdodenhof.circleimageview.CircleImageView;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class LayoutProfileDetailsBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnConfirmNContinue;
    @NonNull
    public final AppCompatButton btnEditCancel;
    @NonNull
    public final AppCompatButton btnEditSave;
    @NonNull
    public final TextInputEditText edMobileNumber;
    @NonNull
    public final TextInputEditText edtCity;
    @NonNull
    public final TextInputEditText edtDummy;
    @NonNull
    public final TextInputEditText edtEmailId;
    @NonNull
    public final TextInputEditText edtFullName;
    @NonNull
    public final TextInputEditText edtStreetName;
    @NonNull
    public final TextInputEditText edtVRN;
    @NonNull
    public final TextInputEditText edtVehicleName;
    @NonNull
    public final AppCompatSpinner genderSpinner;
    @NonNull
    public final CircleImageView imgProfile;
    @NonNull
    public final AppCompatImageView ivBloodGroupInfo;
    @NonNull
    public final AppCompatImageView ivDatePicker;
    @NonNull
    public final AppCompatImageView ivIcon;
    @NonNull
    public final ConstraintLayout layoutBloodGroup;
    @NonNull
    public final ConstraintLayout layoutDob;
    @NonNull
    public final LinearLayoutCompat layoutEditBtn;
    @NonNull
    public final LayoutDashboardModeHeaderBinding layoutEditContactHeader;
    @NonNull
    public final ConstraintLayout layoutGender;
    @NonNull
    public final ConstraintLayout layoutImageView;
    @NonNull
    private final ScrollView rootView;
    @NonNull
    public final ScrollView scrollViewPd;
    @NonNull
    public final AppCompatSpinner spBloodGroup;
    @NonNull
    public final AppCompatTextView tvBloodGroup;
    @NonNull
    public final AppCompatTextView tvChangeImage;
    @NonNull
    public final AppCompatTextView tvConfirmDetail;
    @NonNull
    public final AppCompatTextView tvDateOfBirth;
    @NonNull
    public final AppCompatTextView tvDateOfBirthVal;
    @NonNull
    public final AppCompatTextView tvGender;
    @NonNull
    public final AppCompatTextView tvImageName;
    @NonNull
    public final TextInputLayout txtLayoutCity;
    @NonNull
    public final TextInputLayout txtLayoutDummy;
    @NonNull
    public final TextInputLayout txtLayoutEmailId;
    @NonNull
    public final TextInputLayout txtLayoutFullName;
    @NonNull
    public final TextInputLayout txtLayoutMobileNumber;
    @NonNull
    public final TextInputLayout txtLayoutStreetName;
    @NonNull
    public final TextInputLayout txtLayoutVRN;
    @NonNull
    public final TextInputLayout txtLayoutVehicleName;

    private LayoutProfileDetailsBinding(@NonNull ScrollView scrollView, @NonNull AppCompatButton appCompatButton, @NonNull AppCompatButton appCompatButton2, @NonNull AppCompatButton appCompatButton3, @NonNull TextInputEditText textInputEditText, @NonNull TextInputEditText textInputEditText2, @NonNull TextInputEditText textInputEditText3, @NonNull TextInputEditText textInputEditText4, @NonNull TextInputEditText textInputEditText5, @NonNull TextInputEditText textInputEditText6, @NonNull TextInputEditText textInputEditText7, @NonNull TextInputEditText textInputEditText8, @NonNull AppCompatSpinner appCompatSpinner, @NonNull CircleImageView circleImageView, @NonNull AppCompatImageView appCompatImageView, @NonNull AppCompatImageView appCompatImageView2, @NonNull AppCompatImageView appCompatImageView3, @NonNull ConstraintLayout constraintLayout, @NonNull ConstraintLayout constraintLayout2, @NonNull LinearLayoutCompat linearLayoutCompat, @NonNull LayoutDashboardModeHeaderBinding layoutDashboardModeHeaderBinding, @NonNull ConstraintLayout constraintLayout3, @NonNull ConstraintLayout constraintLayout4, @NonNull ScrollView scrollView2, @NonNull AppCompatSpinner appCompatSpinner2, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4, @NonNull AppCompatTextView appCompatTextView5, @NonNull AppCompatTextView appCompatTextView6, @NonNull AppCompatTextView appCompatTextView7, @NonNull TextInputLayout textInputLayout, @NonNull TextInputLayout textInputLayout2, @NonNull TextInputLayout textInputLayout3, @NonNull TextInputLayout textInputLayout4, @NonNull TextInputLayout textInputLayout5, @NonNull TextInputLayout textInputLayout6, @NonNull TextInputLayout textInputLayout7, @NonNull TextInputLayout textInputLayout8) {
        this.rootView = scrollView;
        this.btnConfirmNContinue = appCompatButton;
        this.btnEditCancel = appCompatButton2;
        this.btnEditSave = appCompatButton3;
        this.edMobileNumber = textInputEditText;
        this.edtCity = textInputEditText2;
        this.edtDummy = textInputEditText3;
        this.edtEmailId = textInputEditText4;
        this.edtFullName = textInputEditText5;
        this.edtStreetName = textInputEditText6;
        this.edtVRN = textInputEditText7;
        this.edtVehicleName = textInputEditText8;
        this.genderSpinner = appCompatSpinner;
        this.imgProfile = circleImageView;
        this.ivBloodGroupInfo = appCompatImageView;
        this.ivDatePicker = appCompatImageView2;
        this.ivIcon = appCompatImageView3;
        this.layoutBloodGroup = constraintLayout;
        this.layoutDob = constraintLayout2;
        this.layoutEditBtn = linearLayoutCompat;
        this.layoutEditContactHeader = layoutDashboardModeHeaderBinding;
        this.layoutGender = constraintLayout3;
        this.layoutImageView = constraintLayout4;
        this.scrollViewPd = scrollView2;
        this.spBloodGroup = appCompatSpinner2;
        this.tvBloodGroup = appCompatTextView;
        this.tvChangeImage = appCompatTextView2;
        this.tvConfirmDetail = appCompatTextView3;
        this.tvDateOfBirth = appCompatTextView4;
        this.tvDateOfBirthVal = appCompatTextView5;
        this.tvGender = appCompatTextView6;
        this.tvImageName = appCompatTextView7;
        this.txtLayoutCity = textInputLayout;
        this.txtLayoutDummy = textInputLayout2;
        this.txtLayoutEmailId = textInputLayout3;
        this.txtLayoutFullName = textInputLayout4;
        this.txtLayoutMobileNumber = textInputLayout5;
        this.txtLayoutStreetName = textInputLayout6;
        this.txtLayoutVRN = textInputLayout7;
        this.txtLayoutVehicleName = textInputLayout8;
    }

    @NonNull
    public static LayoutProfileDetailsBinding bind(@NonNull View view) {
        int i2 = R.id.btnConfirmNContinue;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnConfirmNContinue);
        if (appCompatButton != null) {
            i2 = R.id.btnEditCancel;
            AppCompatButton appCompatButton2 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnEditCancel);
            if (appCompatButton2 != null) {
                i2 = R.id.btnEditSave;
                AppCompatButton appCompatButton3 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnEditSave);
                if (appCompatButton3 != null) {
                    i2 = R.id.edMobileNumber;
                    TextInputEditText textInputEditText = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edMobileNumber);
                    if (textInputEditText != null) {
                        i2 = R.id.edtCity;
                        TextInputEditText textInputEditText2 = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edtCity);
                        if (textInputEditText2 != null) {
                            i2 = R.id.edtDummy;
                            TextInputEditText textInputEditText3 = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edtDummy);
                            if (textInputEditText3 != null) {
                                i2 = R.id.edtEmailId;
                                TextInputEditText textInputEditText4 = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edtEmailId);
                                if (textInputEditText4 != null) {
                                    i2 = R.id.edtFullName;
                                    TextInputEditText textInputEditText5 = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edtFullName);
                                    if (textInputEditText5 != null) {
                                        i2 = R.id.edtStreetName;
                                        TextInputEditText textInputEditText6 = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edtStreetName);
                                        if (textInputEditText6 != null) {
                                            i2 = R.id.edtVRN;
                                            TextInputEditText textInputEditText7 = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edtVRN);
                                            if (textInputEditText7 != null) {
                                                i2 = R.id.edtVehicleName;
                                                TextInputEditText textInputEditText8 = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edtVehicleName);
                                                if (textInputEditText8 != null) {
                                                    i2 = R.id.genderSpinner;
                                                    AppCompatSpinner appCompatSpinner = (AppCompatSpinner) ViewBindings.findChildViewById(view, R.id.genderSpinner);
                                                    if (appCompatSpinner != null) {
                                                        i2 = R.id.imgProfile;
                                                        CircleImageView circleImageView = (CircleImageView) ViewBindings.findChildViewById(view, R.id.imgProfile);
                                                        if (circleImageView != null) {
                                                            i2 = R.id.ivBloodGroupInfo;
                                                            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivBloodGroupInfo);
                                                            if (appCompatImageView != null) {
                                                                i2 = R.id.ivDatePicker;
                                                                AppCompatImageView appCompatImageView2 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivDatePicker);
                                                                if (appCompatImageView2 != null) {
                                                                    i2 = R.id.ivIcon;
                                                                    AppCompatImageView appCompatImageView3 = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivIcon);
                                                                    if (appCompatImageView3 != null) {
                                                                        i2 = R.id.layoutBloodGroup;
                                                                        ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.layoutBloodGroup);
                                                                        if (constraintLayout != null) {
                                                                            i2 = R.id.layoutDob;
                                                                            ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.layoutDob);
                                                                            if (constraintLayout2 != null) {
                                                                                i2 = R.id.layoutEditBtn;
                                                                                LinearLayoutCompat linearLayoutCompat = (LinearLayoutCompat) ViewBindings.findChildViewById(view, R.id.layoutEditBtn);
                                                                                if (linearLayoutCompat != null) {
                                                                                    i2 = R.id.layoutEditContactHeader;
                                                                                    View findChildViewById = ViewBindings.findChildViewById(view, R.id.layoutEditContactHeader);
                                                                                    if (findChildViewById != null) {
                                                                                        LayoutDashboardModeHeaderBinding bind = LayoutDashboardModeHeaderBinding.bind(findChildViewById);
                                                                                        i2 = R.id.layoutGender;
                                                                                        ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.layoutGender);
                                                                                        if (constraintLayout3 != null) {
                                                                                            i2 = R.id.layoutImageView;
                                                                                            ConstraintLayout constraintLayout4 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.layoutImageView);
                                                                                            if (constraintLayout4 != null) {
                                                                                                ScrollView scrollView = (ScrollView) view;
                                                                                                i2 = R.id.spBloodGroup;
                                                                                                AppCompatSpinner appCompatSpinner2 = (AppCompatSpinner) ViewBindings.findChildViewById(view, R.id.spBloodGroup);
                                                                                                if (appCompatSpinner2 != null) {
                                                                                                    i2 = R.id.tvBloodGroup;
                                                                                                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvBloodGroup);
                                                                                                    if (appCompatTextView != null) {
                                                                                                        i2 = R.id.tvChangeImage;
                                                                                                        AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvChangeImage);
                                                                                                        if (appCompatTextView2 != null) {
                                                                                                            i2 = R.id.tvConfirmDetail;
                                                                                                            AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvConfirmDetail);
                                                                                                            if (appCompatTextView3 != null) {
                                                                                                                i2 = R.id.tvDateOfBirth;
                                                                                                                AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvDateOfBirth);
                                                                                                                if (appCompatTextView4 != null) {
                                                                                                                    i2 = R.id.tvDateOfBirthVal;
                                                                                                                    AppCompatTextView appCompatTextView5 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvDateOfBirthVal);
                                                                                                                    if (appCompatTextView5 != null) {
                                                                                                                        i2 = R.id.tvGender;
                                                                                                                        AppCompatTextView appCompatTextView6 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvGender);
                                                                                                                        if (appCompatTextView6 != null) {
                                                                                                                            i2 = R.id.tvImageName;
                                                                                                                            AppCompatTextView appCompatTextView7 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvImageName);
                                                                                                                            if (appCompatTextView7 != null) {
                                                                                                                                i2 = R.id.txtLayoutCity;
                                                                                                                                TextInputLayout textInputLayout = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtLayoutCity);
                                                                                                                                if (textInputLayout != null) {
                                                                                                                                    i2 = R.id.txtLayoutDummy;
                                                                                                                                    TextInputLayout textInputLayout2 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtLayoutDummy);
                                                                                                                                    if (textInputLayout2 != null) {
                                                                                                                                        i2 = R.id.txtLayoutEmailId;
                                                                                                                                        TextInputLayout textInputLayout3 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtLayoutEmailId);
                                                                                                                                        if (textInputLayout3 != null) {
                                                                                                                                            i2 = R.id.txtLayoutFullName;
                                                                                                                                            TextInputLayout textInputLayout4 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtLayoutFullName);
                                                                                                                                            if (textInputLayout4 != null) {
                                                                                                                                                i2 = R.id.txtLayoutMobileNumber;
                                                                                                                                                TextInputLayout textInputLayout5 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtLayoutMobileNumber);
                                                                                                                                                if (textInputLayout5 != null) {
                                                                                                                                                    i2 = R.id.txtLayoutStreetName;
                                                                                                                                                    TextInputLayout textInputLayout6 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtLayoutStreetName);
                                                                                                                                                    if (textInputLayout6 != null) {
                                                                                                                                                        i2 = R.id.txtLayoutVRN;
                                                                                                                                                        TextInputLayout textInputLayout7 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtLayoutVRN);
                                                                                                                                                        if (textInputLayout7 != null) {
                                                                                                                                                            i2 = R.id.txtLayoutVehicleName;
                                                                                                                                                            TextInputLayout textInputLayout8 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtLayoutVehicleName);
                                                                                                                                                            if (textInputLayout8 != null) {
                                                                                                                                                                return new LayoutProfileDetailsBinding(scrollView, appCompatButton, appCompatButton2, appCompatButton3, textInputEditText, textInputEditText2, textInputEditText3, textInputEditText4, textInputEditText5, textInputEditText6, textInputEditText7, textInputEditText8, appCompatSpinner, circleImageView, appCompatImageView, appCompatImageView2, appCompatImageView3, constraintLayout, constraintLayout2, linearLayoutCompat, bind, constraintLayout3, constraintLayout4, scrollView, appCompatSpinner2, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, appCompatTextView7, textInputLayout, textInputLayout2, textInputLayout3, textInputLayout4, textInputLayout5, textInputLayout6, textInputLayout7, textInputLayout8);
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static LayoutProfileDetailsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static LayoutProfileDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_profile_details, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public ScrollView getRoot() {
        return this.rootView;
    }
}
