package com.google.thirdparty.publicsuffix;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import java.util.List;
@GwtCompatible
/* loaded from: classes2.dex */
final class TrieParser {
    private static final Joiner PREFIX_JOINER = Joiner.on("");

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ImmutableMap a(CharSequence charSequence) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length) {
            i2 += doParseTrieToBuilder(Lists.newLinkedList(), charSequence, i2, builder);
        }
        return builder.build();
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static int doParseTrieToBuilder(List<CharSequence> list, CharSequence charSequence, int i2, ImmutableMap.Builder<String, PublicSuffixType> builder) {
        int length = charSequence.length();
        int i3 = i2;
        char c2 = 0;
        while (i3 < length && (c2 = charSequence.charAt(i3)) != '&' && c2 != '?' && c2 != '!' && c2 != ':' && c2 != ',') {
            i3++;
        }
        list.add(0, reverse(charSequence.subSequence(i2, i3)));
        if (c2 == '!' || c2 == '?' || c2 == ':' || c2 == ',') {
            String join = PREFIX_JOINER.join(list);
            if (join.length() > 0) {
                builder.put(join, PublicSuffixType.a(c2));
            }
        }
        int i4 = i3 + 1;
        if (c2 != '?' && c2 != ',') {
            while (i4 < length) {
                i4 += doParseTrieToBuilder(list, charSequence, i4, builder);
                if (charSequence.charAt(i4) == '?' || charSequence.charAt(i4) == ',') {
                    i4++;
                    break;
                }
                while (i4 < length) {
                }
            }
        }
        list.remove(0);
        return i4 - i2;
    }

    private static CharSequence reverse(CharSequence charSequence) {
        return new StringBuilder(charSequence).reverse();
    }
}
