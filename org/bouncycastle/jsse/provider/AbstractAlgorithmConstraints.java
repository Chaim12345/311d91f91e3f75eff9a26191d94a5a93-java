package org.bouncycastle.jsse.provider;

import java.security.Key;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import org.bouncycastle.jsse.java.security.BCAlgorithmConstraints;
/* loaded from: classes3.dex */
abstract class AbstractAlgorithmConstraints implements BCAlgorithmConstraints {

    /* renamed from: a  reason: collision with root package name */
    protected final AlgorithmDecomposer f13852a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractAlgorithmConstraints(AlgorithmDecomposer algorithmDecomposer) {
        this.f13852a = algorithmDecomposer;
    }

    protected static Set a(String[] strArr) {
        HashSet hashSet = new HashSet();
        if (strArr != null) {
            for (String str : strArr) {
                if (str != null) {
                    hashSet.add(str);
                }
            }
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Set b(String[] strArr) {
        if (strArr != null && strArr.length > 0) {
            Set a2 = a(strArr);
            if (!a2.isEmpty()) {
                return Collections.unmodifiableSet(a2);
            }
        }
        return Collections.emptySet();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void c(String str) {
        if (!JsseUtils.Q(str)) {
            throw new IllegalArgumentException("No algorithm name specified");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void d(Key key) {
        Objects.requireNonNull(key, "'key' cannot be null");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void e(Set set) {
        if (!h(set)) {
            throw new IllegalArgumentException("No cryptographic primitive specified");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean f(Set set, String str) {
        if (set.isEmpty()) {
            return false;
        }
        if (g(set, str)) {
            return true;
        }
        AlgorithmDecomposer algorithmDecomposer = this.f13852a;
        if (algorithmDecomposer != null) {
            for (String str2 : algorithmDecomposer.decompose(str)) {
                if (g(set, str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean g(Set set, String str) {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (((String) it.next()).equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    protected boolean h(Set set) {
        return (set == null || set.isEmpty()) ? false : true;
    }
}
