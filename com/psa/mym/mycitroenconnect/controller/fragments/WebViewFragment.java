package com.psa.mym.mycitroenconnect.controller.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.fragment.app.Fragment;
import com.google.android.gms.common.internal.ImagesContract;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class WebViewFragment extends Fragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    private String url;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final WebViewFragment newInstance(@NotNull String url) {
            Intrinsics.checkNotNullParameter(url, "url");
            WebViewFragment webViewFragment = new WebViewFragment();
            Bundle bundle = new Bundle();
            bundle.putString(ImagesContract.URL, url);
            webViewFragment.setArguments(bundle);
            return webViewFragment;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0050, code lost:
        if ((!r1) == true) goto L26;
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:50:? A[RETURN, SYNTHETIC] */
    @SuppressLint({"SetJavaScriptEnabled"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void initView() {
        WebView webView;
        String str;
        WebView webView2;
        WebView webView3;
        WebView webView4;
        boolean z;
        boolean isBlank;
        WebSettings settings;
        View _$_findCachedViewById = _$_findCachedViewById(R.id.progress);
        if (_$_findCachedViewById != null) {
            ExtensionsKt.show(_$_findCachedViewById);
        }
        int i2 = R.id.webView;
        WebView webView5 = (WebView) _$_findCachedViewById(i2);
        if (webView5 != null) {
            ExtensionsKt.hide(webView5);
        }
        WebView webView6 = (WebView) _$_findCachedViewById(i2);
        boolean z2 = true;
        if (webView6 != null && (settings = webView6.getSettings()) != null) {
            settings.setJavaScriptEnabled(true);
            settings.setLoadWithOverviewMode(true);
            settings.setUseWideViewPort(true);
        }
        String str2 = this.url;
        if (str2 != null) {
            if (str2 != null) {
                if (str2.length() > 0) {
                    z = true;
                    if (z) {
                        String str3 = this.url;
                        if (str3 != null) {
                            isBlank = StringsKt__StringsJVMKt.isBlank(str3);
                        }
                        z2 = false;
                        if (z2) {
                            webView = (WebView) _$_findCachedViewById(i2);
                            if (webView != null) {
                                str = this.url;
                                Intrinsics.checkNotNull(str);
                                webView.loadUrl(str);
                            }
                            webView2 = (WebView) _$_findCachedViewById(i2);
                            if (webView2 != null) {
                                webView2.setWebViewClient(new WebViewClient() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.WebViewFragment$initView$2
                                    @Override // android.webkit.WebViewClient
                                    public void onPageFinished(@Nullable WebView webView7, @Nullable String str4) {
                                        super.onPageFinished(webView7, str4);
                                        View _$_findCachedViewById2 = WebViewFragment.this._$_findCachedViewById(R.id.progress);
                                        if (_$_findCachedViewById2 != null) {
                                            ExtensionsKt.hide(_$_findCachedViewById2);
                                        }
                                        WebView webView8 = (WebView) WebViewFragment.this._$_findCachedViewById(R.id.webView);
                                        if (webView8 != null) {
                                            ExtensionsKt.show(webView8);
                                        }
                                    }
                                });
                            }
                            webView3 = (WebView) _$_findCachedViewById(i2);
                            if (webView3 != null) {
                                webView3.canGoBack();
                            }
                            webView4 = (WebView) _$_findCachedViewById(i2);
                            if (webView4 == null) {
                                webView4.setOnKeyListener(new View.OnKeyListener() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.k
                                    @Override // android.view.View.OnKeyListener
                                    public final boolean onKey(View view, int i3, KeyEvent keyEvent) {
                                        boolean m136initView$lambda2;
                                        m136initView$lambda2 = WebViewFragment.m136initView$lambda2(WebViewFragment.this, view, i3, keyEvent);
                                        return m136initView$lambda2;
                                    }
                                });
                                return;
                            }
                            return;
                        }
                    }
                }
            }
            z = false;
            if (z) {
            }
        }
        webView = (WebView) _$_findCachedViewById(i2);
        if (webView != null) {
            str = "https://www.citroen.in/";
            webView.loadUrl(str);
        }
        webView2 = (WebView) _$_findCachedViewById(i2);
        if (webView2 != null) {
        }
        webView3 = (WebView) _$_findCachedViewById(i2);
        if (webView3 != null) {
        }
        webView4 = (WebView) _$_findCachedViewById(i2);
        if (webView4 == null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-2  reason: not valid java name */
    public static final boolean m136initView$lambda2(WebViewFragment this$0, View view, int i2, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i2 == 4 && keyEvent.getAction() == 1) {
            int i3 = R.id.webView;
            WebView webView = (WebView) this$0._$_findCachedViewById(i3);
            if (webView != null && webView.canGoBack()) {
                WebView webView2 = (WebView) this$0._$_findCachedViewById(i3);
                if (webView2 != null) {
                    webView2.goBack();
                }
                return true;
            }
            return false;
        }
        return false;
    }

    @JvmStatic
    @NotNull
    public static final WebViewFragment newInstance(@NotNull String str) {
        return Companion.newInstance(str);
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

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.url = arguments.getString(ImagesContract.URL);
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.fragment_web_view, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initView();
    }
}
