package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@GwtIncompatible
/* loaded from: classes2.dex */
final class JdkPattern extends CommonPattern implements Serializable {
    private static final long serialVersionUID = 0;
    private final Pattern pattern;

    /* loaded from: classes2.dex */
    private static final class JdkMatcher extends CommonMatcher {

        /* renamed from: a  reason: collision with root package name */
        final Matcher f8148a;

        JdkMatcher(Matcher matcher) {
            this.f8148a = (Matcher) Preconditions.checkNotNull(matcher);
        }

        @Override // com.google.common.base.CommonMatcher
        public int end() {
            return this.f8148a.end();
        }

        @Override // com.google.common.base.CommonMatcher
        public boolean find() {
            return this.f8148a.find();
        }

        @Override // com.google.common.base.CommonMatcher
        public boolean find(int i2) {
            return this.f8148a.find(i2);
        }

        @Override // com.google.common.base.CommonMatcher
        public boolean matches() {
            return this.f8148a.matches();
        }

        @Override // com.google.common.base.CommonMatcher
        public String replaceAll(String str) {
            return this.f8148a.replaceAll(str);
        }

        @Override // com.google.common.base.CommonMatcher
        public int start() {
            return this.f8148a.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JdkPattern(Pattern pattern) {
        this.pattern = (Pattern) Preconditions.checkNotNull(pattern);
    }

    @Override // com.google.common.base.CommonPattern
    public int flags() {
        return this.pattern.flags();
    }

    @Override // com.google.common.base.CommonPattern
    public CommonMatcher matcher(CharSequence charSequence) {
        return new JdkMatcher(this.pattern.matcher(charSequence));
    }

    @Override // com.google.common.base.CommonPattern
    public String pattern() {
        return this.pattern.pattern();
    }

    @Override // com.google.common.base.CommonPattern
    public String toString() {
        return this.pattern.toString();
    }
}
