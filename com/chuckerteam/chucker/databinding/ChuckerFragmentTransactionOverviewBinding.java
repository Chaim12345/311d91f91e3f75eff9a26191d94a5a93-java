package com.chuckerteam.chucker.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.Group;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import com.chuckerteam.chucker.R;
/* loaded from: classes.dex */
public final class ChuckerFragmentTransactionOverviewBinding implements ViewBinding {
    @NonNull
    public final Barrier barrierRequestSize;
    @NonNull
    public final Barrier barrierRequestTime;
    @NonNull
    public final Barrier barrierResponseSize;
    @NonNull
    public final Barrier barrierResponseTime;
    @NonNull
    public final TextView cipherSuite;
    @NonNull
    public final Group cipherSuiteGroup;
    @NonNull
    public final TextView cipherSuiteValue;
    @NonNull
    public final TextView duration;
    @NonNull
    public final TextView method;
    @NonNull
    public final Guideline overviewGuideline;
    @NonNull
    public final TextView protocol;
    @NonNull
    public final TextView requestSize;
    @NonNull
    public final TextView requestSizeLabel;
    @NonNull
    public final TextView requestTime;
    @NonNull
    public final TextView requestTimeLabel;
    @NonNull
    public final TextView response;
    @NonNull
    public final TextView responseSize;
    @NonNull
    public final TextView responseSizeLabel;
    @NonNull
    public final TextView responseTime;
    @NonNull
    public final TextView responseTimeLabel;
    @NonNull
    private final ScrollView rootView;
    @NonNull
    public final TextView ssl;
    @NonNull
    public final Group sslGroup;
    @NonNull
    public final TextView sslValue;
    @NonNull
    public final TextView status;
    @NonNull
    public final Group tlsGroup;
    @NonNull
    public final TextView tlsVersion;
    @NonNull
    public final TextView tlsVersionValue;
    @NonNull
    public final TextView totalSize;
    @NonNull
    public final TextView url;

    private ChuckerFragmentTransactionOverviewBinding(@NonNull ScrollView scrollView, @NonNull Barrier barrier, @NonNull Barrier barrier2, @NonNull Barrier barrier3, @NonNull Barrier barrier4, @NonNull TextView textView, @NonNull Group group, @NonNull TextView textView2, @NonNull TextView textView3, @NonNull TextView textView4, @NonNull Guideline guideline, @NonNull TextView textView5, @NonNull TextView textView6, @NonNull TextView textView7, @NonNull TextView textView8, @NonNull TextView textView9, @NonNull TextView textView10, @NonNull TextView textView11, @NonNull TextView textView12, @NonNull TextView textView13, @NonNull TextView textView14, @NonNull TextView textView15, @NonNull Group group2, @NonNull TextView textView16, @NonNull TextView textView17, @NonNull Group group3, @NonNull TextView textView18, @NonNull TextView textView19, @NonNull TextView textView20, @NonNull TextView textView21) {
        this.rootView = scrollView;
        this.barrierRequestSize = barrier;
        this.barrierRequestTime = barrier2;
        this.barrierResponseSize = barrier3;
        this.barrierResponseTime = barrier4;
        this.cipherSuite = textView;
        this.cipherSuiteGroup = group;
        this.cipherSuiteValue = textView2;
        this.duration = textView3;
        this.method = textView4;
        this.overviewGuideline = guideline;
        this.protocol = textView5;
        this.requestSize = textView6;
        this.requestSizeLabel = textView7;
        this.requestTime = textView8;
        this.requestTimeLabel = textView9;
        this.response = textView10;
        this.responseSize = textView11;
        this.responseSizeLabel = textView12;
        this.responseTime = textView13;
        this.responseTimeLabel = textView14;
        this.ssl = textView15;
        this.sslGroup = group2;
        this.sslValue = textView16;
        this.status = textView17;
        this.tlsGroup = group3;
        this.tlsVersion = textView18;
        this.tlsVersionValue = textView19;
        this.totalSize = textView20;
        this.url = textView21;
    }

    @NonNull
    public static ChuckerFragmentTransactionOverviewBinding bind(@NonNull View view) {
        int i2 = R.id.barrierRequestSize;
        Barrier barrier = (Barrier) view.findViewById(i2);
        if (barrier != null) {
            i2 = R.id.barrierRequestTime;
            Barrier barrier2 = (Barrier) view.findViewById(i2);
            if (barrier2 != null) {
                i2 = R.id.barrierResponseSize;
                Barrier barrier3 = (Barrier) view.findViewById(i2);
                if (barrier3 != null) {
                    i2 = R.id.barrierResponseTime;
                    Barrier barrier4 = (Barrier) view.findViewById(i2);
                    if (barrier4 != null) {
                        i2 = R.id.cipherSuite;
                        TextView textView = (TextView) view.findViewById(i2);
                        if (textView != null) {
                            i2 = R.id.cipherSuiteGroup;
                            Group group = (Group) view.findViewById(i2);
                            if (group != null) {
                                i2 = R.id.cipherSuiteValue;
                                TextView textView2 = (TextView) view.findViewById(i2);
                                if (textView2 != null) {
                                    i2 = R.id.duration;
                                    TextView textView3 = (TextView) view.findViewById(i2);
                                    if (textView3 != null) {
                                        i2 = R.id.method;
                                        TextView textView4 = (TextView) view.findViewById(i2);
                                        if (textView4 != null) {
                                            i2 = R.id.overviewGuideline;
                                            Guideline guideline = (Guideline) view.findViewById(i2);
                                            if (guideline != null) {
                                                i2 = R.id.protocol;
                                                TextView textView5 = (TextView) view.findViewById(i2);
                                                if (textView5 != null) {
                                                    i2 = R.id.requestSize;
                                                    TextView textView6 = (TextView) view.findViewById(i2);
                                                    if (textView6 != null) {
                                                        i2 = R.id.requestSizeLabel;
                                                        TextView textView7 = (TextView) view.findViewById(i2);
                                                        if (textView7 != null) {
                                                            i2 = R.id.requestTime;
                                                            TextView textView8 = (TextView) view.findViewById(i2);
                                                            if (textView8 != null) {
                                                                i2 = R.id.requestTimeLabel;
                                                                TextView textView9 = (TextView) view.findViewById(i2);
                                                                if (textView9 != null) {
                                                                    i2 = R.id.response;
                                                                    TextView textView10 = (TextView) view.findViewById(i2);
                                                                    if (textView10 != null) {
                                                                        i2 = R.id.responseSize;
                                                                        TextView textView11 = (TextView) view.findViewById(i2);
                                                                        if (textView11 != null) {
                                                                            i2 = R.id.responseSizeLabel;
                                                                            TextView textView12 = (TextView) view.findViewById(i2);
                                                                            if (textView12 != null) {
                                                                                i2 = R.id.responseTime;
                                                                                TextView textView13 = (TextView) view.findViewById(i2);
                                                                                if (textView13 != null) {
                                                                                    i2 = R.id.responseTimeLabel;
                                                                                    TextView textView14 = (TextView) view.findViewById(i2);
                                                                                    if (textView14 != null) {
                                                                                        i2 = R.id.ssl;
                                                                                        TextView textView15 = (TextView) view.findViewById(i2);
                                                                                        if (textView15 != null) {
                                                                                            i2 = R.id.sslGroup;
                                                                                            Group group2 = (Group) view.findViewById(i2);
                                                                                            if (group2 != null) {
                                                                                                i2 = R.id.sslValue;
                                                                                                TextView textView16 = (TextView) view.findViewById(i2);
                                                                                                if (textView16 != null) {
                                                                                                    i2 = R.id.status;
                                                                                                    TextView textView17 = (TextView) view.findViewById(i2);
                                                                                                    if (textView17 != null) {
                                                                                                        i2 = R.id.tlsGroup;
                                                                                                        Group group3 = (Group) view.findViewById(i2);
                                                                                                        if (group3 != null) {
                                                                                                            i2 = R.id.tlsVersion;
                                                                                                            TextView textView18 = (TextView) view.findViewById(i2);
                                                                                                            if (textView18 != null) {
                                                                                                                i2 = R.id.tlsVersionValue;
                                                                                                                TextView textView19 = (TextView) view.findViewById(i2);
                                                                                                                if (textView19 != null) {
                                                                                                                    i2 = R.id.totalSize;
                                                                                                                    TextView textView20 = (TextView) view.findViewById(i2);
                                                                                                                    if (textView20 != null) {
                                                                                                                        i2 = R.id.url;
                                                                                                                        TextView textView21 = (TextView) view.findViewById(i2);
                                                                                                                        if (textView21 != null) {
                                                                                                                            return new ChuckerFragmentTransactionOverviewBinding((ScrollView) view, barrier, barrier2, barrier3, barrier4, textView, group, textView2, textView3, textView4, guideline, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, group2, textView16, textView17, group3, textView18, textView19, textView20, textView21);
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
    public static ChuckerFragmentTransactionOverviewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ChuckerFragmentTransactionOverviewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.chucker_fragment_transaction_overview, viewGroup, false);
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
