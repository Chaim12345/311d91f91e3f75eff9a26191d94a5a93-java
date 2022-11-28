package com.chuckerteam.chucker.internal.support;

import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.bouncycastle.i18n.TextBundle;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\u001a$\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0000\u001a\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0002\u001a6\u0010\f\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00002\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\b2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0002¨\u0006\r"}, d2 = {"", FirebaseAnalytics.Event.SEARCH, "", "backgroundColor", "foregroundColor", "Landroid/text/SpannableStringBuilder;", "highlightWithDefinedColors", TextBundle.TEXT_ENTRY, "", "indexesOf", "indexes", "length", "applyColoredSpannable", "com.github.ChuckerTeam.Chucker.library"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class SearchHighlightUtilKt {
    private static final SpannableStringBuilder applyColoredSpannable(String str, List<Integer> list, int i2, int i3, int i4) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        for (Number number : list) {
            int intValue = number.intValue();
            int i5 = intValue + i2;
            spannableStringBuilder.setSpan(new UnderlineSpan(), intValue, i5, 33);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(i4), intValue, i5, 33);
            spannableStringBuilder.setSpan(new BackgroundColorSpan(i3), intValue, i5, 33);
        }
        return spannableStringBuilder;
    }

    @NotNull
    public static final SpannableStringBuilder highlightWithDefinedColors(@NotNull String highlightWithDefinedColors, @NotNull String search, int i2, int i3) {
        Intrinsics.checkNotNullParameter(highlightWithDefinedColors, "$this$highlightWithDefinedColors");
        Intrinsics.checkNotNullParameter(search, "search");
        return applyColoredSpannable(highlightWithDefinedColors, indexesOf(highlightWithDefinedColors, search), search.length(), i2, i3);
    }

    private static final List<Integer> indexesOf(String str, String str2) {
        int indexOf;
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (true) {
            indexOf = StringsKt__StringsKt.indexOf((CharSequence) str, str2, i2, true);
            if (indexOf < 0) {
                return arrayList;
            }
            arrayList.add(Integer.valueOf(indexOf));
            i2 = indexOf + 1;
        }
    }
}
