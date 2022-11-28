package org.bouncycastle.est;
/* loaded from: classes3.dex */
public class ESTServiceBuilder {

    /* renamed from: a  reason: collision with root package name */
    protected final String f13557a;

    /* renamed from: b  reason: collision with root package name */
    protected ESTClientProvider f13558b;

    /* renamed from: c  reason: collision with root package name */
    protected String f13559c;

    public ESTServiceBuilder(String str) {
        this.f13557a = str;
    }

    public ESTService build() {
        return new ESTService(this.f13557a, this.f13559c, this.f13558b);
    }

    public ESTServiceBuilder withClientProvider(ESTClientProvider eSTClientProvider) {
        this.f13558b = eSTClientProvider;
        return this;
    }

    public ESTServiceBuilder withLabel(String str) {
        this.f13559c = str;
        return this;
    }
}
