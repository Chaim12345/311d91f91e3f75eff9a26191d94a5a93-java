package org.bouncycastle.jsse.provider;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
/* loaded from: classes3.dex */
class JcaAlgorithmDecomposer implements AlgorithmDecomposer {
    private static final Pattern PATTERN = Pattern.compile("with|and|(?<!padd)in", 2);

    /* renamed from: a  reason: collision with root package name */
    static final JcaAlgorithmDecomposer f13888a = new JcaAlgorithmDecomposer();

    private static void ensureBothIfEither(Set<String> set, String str, String str2) {
        boolean contains = set.contains(str);
        if (set.contains(str2) ^ contains) {
            if (contains) {
                str = str2;
            }
            set.add(str);
        }
    }

    @Override // org.bouncycastle.jsse.provider.AlgorithmDecomposer
    public Set<String> decompose(String str) {
        String[] split;
        String[] split2;
        if (str.indexOf(47) < 0) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet();
        for (String str2 : str.split("/")) {
            if (str2.length() > 0) {
                for (String str3 : PATTERN.split(str2)) {
                    if (str3.length() > 0) {
                        hashSet.add(str3);
                    }
                }
            }
        }
        ensureBothIfEither(hashSet, "SHA1", "SHA-1");
        ensureBothIfEither(hashSet, "SHA224", "SHA-224");
        ensureBothIfEither(hashSet, "SHA256", "SHA-256");
        ensureBothIfEither(hashSet, "SHA384", "SHA-384");
        ensureBothIfEither(hashSet, "SHA512", "SHA-512");
        return hashSet;
    }
}
