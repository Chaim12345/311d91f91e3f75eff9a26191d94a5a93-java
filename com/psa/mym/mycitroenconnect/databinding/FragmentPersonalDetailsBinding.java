package com.psa.mym.mycitroenconnect.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import de.hdodenhof.circleimageview.CircleImageView;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class FragmentPersonalDetailsBinding implements ViewBinding {
    @NonNull
    public final AppCompatButton btnAddEmergencyContact;
    @NonNull
    public final ConstraintLayout clChildImageView;
    @NonNull
    public final ConstraintLayout clEmergencyContact;
    @NonNull
    public final ConstraintLayout clImageView;
    @NonNull
    public final TextInputEditText edtCity;
    @NonNull
    public final TextInputEditText edtPdBloodGroup;
    @NonNull
    public final TextInputEditText edtPdDob;
    @NonNull
    public final TextInputEditText edtPdEmailId;
    @NonNull
    public final TextInputEditText edtPdFullName;
    @NonNull
    public final TextInputEditText edtPdGender;
    @NonNull
    public final TextInputEditText edtPdPhone;
    @NonNull
    public final TextInputEditText edtStreetName;
    @NonNull
    public final CircleImageView imgProfile;
    @NonNull
    public final AppCompatImageView ivPdEdit;
    @NonNull
    public final ScrollView pdScrollView;
    @NonNull
    private final ScrollView rootView;
    @NonNull
    public final RecyclerView rvProContact;
    @NonNull
    public final AppCompatTextView tvChangeImage;
    @NonNull
    public final AppCompatTextView tvImageName;
    @NonNull
    public final AppCompatTextView tvPdEmergeSubTitle;
    @NonNull
    public final AppCompatTextView tvPdEmergencyContact;
    @NonNull
    public final AppCompatTextView tvPdEmergencyContactCount;
    @NonNull
    public final AppCompatTextView tvPdPersonalDetails;
    @NonNull
    public final TextInputLayout txtCity;
    @NonNull
    public final TextInputLayout txtEmailId;
    @NonNull
    public final TextInputLayout txtLayoutGender;
    @NonNull
    public final TextInputLayout txtPdBloodGroup;
    @NonNull
    public final TextInputLayout txtPdDob;
    @NonNull
    public final TextInputLayout txtPdLayoutFullName;
    @NonNull
    public final TextInputLayout txtPdPhone;
    @NonNull
    public final TextInputLayout txtStreetName;

    private FragmentPersonalDetailsBinding(@NonNull ScrollView scrollView, @NonNull AppCompatButton appCompatButton, @NonNull ConstraintLayout constraintLayout, @NonNull ConstraintLayout constraintLayout2, @NonNull ConstraintLayout constraintLayout3, @NonNull TextInputEditText textInputEditText, @NonNull TextInputEditText textInputEditText2, @NonNull TextInputEditText textInputEditText3, @NonNull TextInputEditText textInputEditText4, @NonNull TextInputEditText textInputEditText5, @NonNull TextInputEditText textInputEditText6, @NonNull TextInputEditText textInputEditText7, @NonNull TextInputEditText textInputEditText8, @NonNull CircleImageView circleImageView, @NonNull AppCompatImageView appCompatImageView, @NonNull ScrollView scrollView2, @NonNull RecyclerView recyclerView, @NonNull AppCompatTextView appCompatTextView, @NonNull AppCompatTextView appCompatTextView2, @NonNull AppCompatTextView appCompatTextView3, @NonNull AppCompatTextView appCompatTextView4, @NonNull AppCompatTextView appCompatTextView5, @NonNull AppCompatTextView appCompatTextView6, @NonNull TextInputLayout textInputLayout, @NonNull TextInputLayout textInputLayout2, @NonNull TextInputLayout textInputLayout3, @NonNull TextInputLayout textInputLayout4, @NonNull TextInputLayout textInputLayout5, @NonNull TextInputLayout textInputLayout6, @NonNull TextInputLayout textInputLayout7, @NonNull TextInputLayout textInputLayout8) {
        this.rootView = scrollView;
        this.btnAddEmergencyContact = appCompatButton;
        this.clChildImageView = constraintLayout;
        this.clEmergencyContact = constraintLayout2;
        this.clImageView = constraintLayout3;
        this.edtCity = textInputEditText;
        this.edtPdBloodGroup = textInputEditText2;
        this.edtPdDob = textInputEditText3;
        this.edtPdEmailId = textInputEditText4;
        this.edtPdFullName = textInputEditText5;
        this.edtPdGender = textInputEditText6;
        this.edtPdPhone = textInputEditText7;
        this.edtStreetName = textInputEditText8;
        this.imgProfile = circleImageView;
        this.ivPdEdit = appCompatImageView;
        this.pdScrollView = scrollView2;
        this.rvProContact = recyclerView;
        this.tvChangeImage = appCompatTextView;
        this.tvImageName = appCompatTextView2;
        this.tvPdEmergeSubTitle = appCompatTextView3;
        this.tvPdEmergencyContact = appCompatTextView4;
        this.tvPdEmergencyContactCount = appCompatTextView5;
        this.tvPdPersonalDetails = appCompatTextView6;
        this.txtCity = textInputLayout;
        this.txtEmailId = textInputLayout2;
        this.txtLayoutGender = textInputLayout3;
        this.txtPdBloodGroup = textInputLayout4;
        this.txtPdDob = textInputLayout5;
        this.txtPdLayoutFullName = textInputLayout6;
        this.txtPdPhone = textInputLayout7;
        this.txtStreetName = textInputLayout8;
    }

    @NonNull
    public static FragmentPersonalDetailsBinding bind(@NonNull View view) {
        int i2 = R.id.btnAddEmergencyContact;
        AppCompatButton appCompatButton = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.btnAddEmergencyContact);
        if (appCompatButton != null) {
            i2 = R.id.clChildImageView;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.clChildImageView);
            if (constraintLayout != null) {
                i2 = R.id.clEmergencyContact;
                ConstraintLayout constraintLayout2 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.clEmergencyContact);
                if (constraintLayout2 != null) {
                    i2 = R.id.clImageView;
                    ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.clImageView);
                    if (constraintLayout3 != null) {
                        i2 = R.id.edtCity;
                        TextInputEditText textInputEditText = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edtCity);
                        if (textInputEditText != null) {
                            i2 = R.id.edtPdBloodGroup;
                            TextInputEditText textInputEditText2 = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edtPdBloodGroup);
                            if (textInputEditText2 != null) {
                                i2 = R.id.edtPdDob;
                                TextInputEditText textInputEditText3 = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edtPdDob);
                                if (textInputEditText3 != null) {
                                    i2 = R.id.edtPdEmailId;
                                    TextInputEditText textInputEditText4 = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edtPdEmailId);
                                    if (textInputEditText4 != null) {
                                        i2 = R.id.edtPdFullName;
                                        TextInputEditText textInputEditText5 = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edtPdFullName);
                                        if (textInputEditText5 != null) {
                                            i2 = R.id.edtPdGender;
                                            TextInputEditText textInputEditText6 = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edtPdGender);
                                            if (textInputEditText6 != null) {
                                                i2 = R.id.edtPdPhone;
                                                TextInputEditText textInputEditText7 = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edtPdPhone);
                                                if (textInputEditText7 != null) {
                                                    i2 = R.id.edtStreetName;
                                                    TextInputEditText textInputEditText8 = (TextInputEditText) ViewBindings.findChildViewById(view, R.id.edtStreetName);
                                                    if (textInputEditText8 != null) {
                                                        i2 = R.id.imgProfile;
                                                        CircleImageView circleImageView = (CircleImageView) ViewBindings.findChildViewById(view, R.id.imgProfile);
                                                        if (circleImageView != null) {
                                                            i2 = R.id.ivPdEdit;
                                                            AppCompatImageView appCompatImageView = (AppCompatImageView) ViewBindings.findChildViewById(view, R.id.ivPdEdit);
                                                            if (appCompatImageView != null) {
                                                                ScrollView scrollView = (ScrollView) view;
                                                                i2 = R.id.rvProContact;
                                                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rvProContact);
                                                                if (recyclerView != null) {
                                                                    i2 = R.id.tvChangeImage;
                                                                    AppCompatTextView appCompatTextView = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvChangeImage);
                                                                    if (appCompatTextView != null) {
                                                                        i2 = R.id.tvImageName;
                                                                        AppCompatTextView appCompatTextView2 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvImageName);
                                                                        if (appCompatTextView2 != null) {
                                                                            i2 = R.id.tvPdEmergeSubTitle;
                                                                            AppCompatTextView appCompatTextView3 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvPdEmergeSubTitle);
                                                                            if (appCompatTextView3 != null) {
                                                                                i2 = R.id.tvPdEmergencyContact;
                                                                                AppCompatTextView appCompatTextView4 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvPdEmergencyContact);
                                                                                if (appCompatTextView4 != null) {
                                                                                    i2 = R.id.tvPdEmergencyContactCount;
                                                                                    AppCompatTextView appCompatTextView5 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvPdEmergencyContactCount);
                                                                                    if (appCompatTextView5 != null) {
                                                                                        i2 = R.id.tvPdPersonalDetails;
                                                                                        AppCompatTextView appCompatTextView6 = (AppCompatTextView) ViewBindings.findChildViewById(view, R.id.tvPdPersonalDetails);
                                                                                        if (appCompatTextView6 != null) {
                                                                                            i2 = R.id.txtCity;
                                                                                            TextInputLayout textInputLayout = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtCity);
                                                                                            if (textInputLayout != null) {
                                                                                                i2 = R.id.txtEmailId;
                                                                                                TextInputLayout textInputLayout2 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtEmailId);
                                                                                                if (textInputLayout2 != null) {
                                                                                                    i2 = R.id.txtLayoutGender;
                                                                                                    TextInputLayout textInputLayout3 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtLayoutGender);
                                                                                                    if (textInputLayout3 != null) {
                                                                                                        i2 = R.id.txtPdBloodGroup;
                                                                                                        TextInputLayout textInputLayout4 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtPdBloodGroup);
                                                                                                        if (textInputLayout4 != null) {
                                                                                                            i2 = R.id.txtPdDob;
                                                                                                            TextInputLayout textInputLayout5 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtPdDob);
                                                                                                            if (textInputLayout5 != null) {
                                                                                                                i2 = R.id.txtPdLayoutFullName;
                                                                                                                TextInputLayout textInputLayout6 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtPdLayoutFullName);
                                                                                                                if (textInputLayout6 != null) {
                                                                                                                    i2 = R.id.txtPdPhone;
                                                                                                                    TextInputLayout textInputLayout7 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtPdPhone);
                                                                                                                    if (textInputLayout7 != null) {
                                                                                                                        i2 = R.id.txtStreetName;
                                                                                                                        TextInputLayout textInputLayout8 = (TextInputLayout) ViewBindings.findChildViewById(view, R.id.txtStreetName);
                                                                                                                        if (textInputLayout8 != null) {
                                                                                                                            return new FragmentPersonalDetailsBinding(scrollView, appCompatButton, constraintLayout, constraintLayout2, constraintLayout3, textInputEditText, textInputEditText2, textInputEditText3, textInputEditText4, textInputEditText5, textInputEditText6, textInputEditText7, textInputEditText8, circleImageView, appCompatImageView, scrollView, recyclerView, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, textInputLayout, textInputLayout2, textInputLayout3, textInputLayout4, textInputLayout5, textInputLayout6, textInputLayout7, textInputLayout8);
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
    public static FragmentPersonalDetailsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FragmentPersonalDetailsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_personal_details, viewGroup, false);
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
