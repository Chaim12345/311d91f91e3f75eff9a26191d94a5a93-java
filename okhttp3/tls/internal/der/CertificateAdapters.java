package okhttp3.tls.internal.der;

import java.net.ProtocolException;
import java.util.List;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import okhttp3.tls.internal.der.DerAdapter;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class CertificateAdapters {
    @NotNull
    public static final CertificateAdapters INSTANCE = new CertificateAdapters();
    @NotNull
    private static final BasicDerAdapter<AlgorithmIdentifier> algorithmIdentifier;
    @NotNull
    private static final DerAdapter<Object> algorithmParameters;
    @NotNull
    private static final BasicDerAdapter<AttributeTypeAndValue> attributeTypeAndValue;
    @NotNull
    private static final BasicDerAdapter<BasicConstraints> basicConstraints;
    @NotNull
    private static final BasicDerAdapter<Certificate> certificate;
    @NotNull
    private static final BasicDerAdapter<Extension> extension;
    @NotNull
    private static final BasicDerAdapter<Object> extensionValue;
    @NotNull
    private static final DerAdapter<Pair<DerAdapter<?>, Object>> generalName;
    @NotNull
    private static final BasicDerAdapter<String> generalNameDnsName;
    @NotNull
    private static final BasicDerAdapter<ByteString> generalNameIpAddress;
    @NotNull
    private static final DerAdapter<Pair<DerAdapter<?>, Object>> name;
    @NotNull
    private static final BasicDerAdapter<PrivateKeyInfo> privateKeyInfo;
    @NotNull
    private static final BasicDerAdapter<List<List<AttributeTypeAndValue>>> rdnSequence;
    @NotNull
    private static final BasicDerAdapter<List<Pair<DerAdapter<?>, Object>>> subjectAlternativeName;
    @NotNull
    private static final BasicDerAdapter<SubjectPublicKeyInfo> subjectPublicKeyInfo;
    @NotNull
    private static final BasicDerAdapter<TbsCertificate> tbsCertificate;
    @NotNull
    private static final DerAdapter<Long> time;
    @NotNull
    private static final BasicDerAdapter<Validity> validity;

    static {
        List emptyList;
        DerAdapter<Long> derAdapter = new DerAdapter<Long>() { // from class: okhttp3.tls.internal.der.CertificateAdapters$time$1
            @Override // okhttp3.tls.internal.der.DerAdapter
            @NotNull
            public BasicDerAdapter<List<Long>> asSequenceOf(@NotNull String str, int i2, long j2) {
                return DerAdapter.DefaultImpls.asSequenceOf(this, str, i2, j2);
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            @NotNull
            public BasicDerAdapter<List<Long>> asSetOf() {
                return DerAdapter.DefaultImpls.asSetOf(this);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // okhttp3.tls.internal.der.DerAdapter
            @NotNull
            public Long fromDer(@NotNull DerReader reader) {
                BasicDerAdapter<Long> generalized_time;
                Intrinsics.checkNotNullParameter(reader, "reader");
                DerHeader peekHeader = reader.peekHeader();
                if (peekHeader != null) {
                    int tagClass = peekHeader.getTagClass();
                    Adapters adapters = Adapters.INSTANCE;
                    if (tagClass == adapters.getUTC_TIME().getTagClass() && peekHeader.getTag() == adapters.getUTC_TIME().getTag()) {
                        generalized_time = adapters.getUTC_TIME();
                    } else if (peekHeader.getTagClass() != adapters.getGENERALIZED_TIME().getTagClass() || peekHeader.getTag() != adapters.getGENERALIZED_TIME().getTag()) {
                        throw new ProtocolException("expected time but was " + peekHeader + " at " + reader);
                    } else {
                        generalized_time = adapters.getGENERALIZED_TIME();
                    }
                    return Long.valueOf(generalized_time.fromDer(reader).longValue());
                }
                throw new ProtocolException(Intrinsics.stringPlus("expected time but was exhausted at ", reader));
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // okhttp3.tls.internal.der.DerAdapter
            @NotNull
            public Long fromDer(@NotNull ByteString byteString) {
                return (Long) DerAdapter.DefaultImpls.fromDer(this, byteString);
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            public boolean matches(@NotNull DerHeader header) {
                Intrinsics.checkNotNullParameter(header, "header");
                Adapters adapters = Adapters.INSTANCE;
                return adapters.getUTC_TIME().matches(header) || adapters.getGENERALIZED_TIME().matches(header);
            }

            @NotNull
            public ByteString toDer(long j2) {
                return DerAdapter.DefaultImpls.toDer(this, Long.valueOf(j2));
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            public /* bridge */ /* synthetic */ ByteString toDer(Long l2) {
                return toDer(l2.longValue());
            }

            public void toDer(@NotNull DerWriter writer, long j2) {
                Intrinsics.checkNotNullParameter(writer, "writer");
                boolean z = false;
                if (-631152000000L <= j2 && j2 < 2524608000000L) {
                    z = true;
                }
                Adapters adapters = Adapters.INSTANCE;
                (z ? adapters.getUTC_TIME() : adapters.getGENERALIZED_TIME()).toDer(writer, Long.valueOf(j2));
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            public /* bridge */ /* synthetic */ void toDer(DerWriter derWriter, Long l2) {
                toDer(derWriter, l2.longValue());
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            @NotNull
            public BasicDerAdapter<Long> withExplicitBox(int i2, long j2, @Nullable Boolean bool) {
                return DerAdapter.DefaultImpls.withExplicitBox(this, i2, j2, bool);
            }
        };
        time = derAdapter;
        Adapters adapters = Adapters.INSTANCE;
        BasicDerAdapter<Validity> sequence = adapters.sequence("Validity", new DerAdapter[]{derAdapter, derAdapter}, CertificateAdapters$validity$1.INSTANCE, CertificateAdapters$validity$2.INSTANCE);
        validity = sequence;
        DerAdapter<?> usingTypeHint = adapters.usingTypeHint(CertificateAdapters$algorithmParameters$1.INSTANCE);
        algorithmParameters = usingTypeHint;
        BasicDerAdapter<AlgorithmIdentifier> sequence2 = adapters.sequence("AlgorithmIdentifier", new DerAdapter[]{adapters.getOBJECT_IDENTIFIER().asTypeHint(), usingTypeHint}, CertificateAdapters$algorithmIdentifier$1.INSTANCE, CertificateAdapters$algorithmIdentifier$2.INSTANCE);
        algorithmIdentifier = sequence2;
        BasicDerAdapter<Boolean> basicDerAdapter = adapters.getBOOLEAN();
        Boolean bool = Boolean.FALSE;
        basicConstraints = adapters.sequence("BasicConstraints", new DerAdapter[]{basicDerAdapter.optional(bool), BasicDerAdapter.optional$default(adapters.getINTEGER_AS_LONG(), null, 1, null)}, CertificateAdapters$basicConstraints$1.INSTANCE, CertificateAdapters$basicConstraints$2.INSTANCE);
        BasicDerAdapter<String> withTag$default = BasicDerAdapter.withTag$default(adapters.getIA5_STRING(), 0, 2L, 1, null);
        generalNameDnsName = withTag$default;
        BasicDerAdapter<ByteString> withTag$default2 = BasicDerAdapter.withTag$default(adapters.getOCTET_STRING(), 0, 7L, 1, null);
        generalNameIpAddress = withTag$default2;
        DerAdapter<Pair<DerAdapter<?>, Object>> choice = adapters.choice(withTag$default, withTag$default2, adapters.getANY_VALUE());
        generalName = choice;
        subjectAlternativeName = DerAdapter.DefaultImpls.asSequenceOf$default(choice, null, 0, 0L, 7, null);
        BasicDerAdapter<Object> withExplicitBox = adapters.usingTypeHint(CertificateAdapters$extensionValue$1.INSTANCE).withExplicitBox(adapters.getOCTET_STRING().getTagClass(), adapters.getOCTET_STRING().getTag(), bool);
        extensionValue = withExplicitBox;
        BasicDerAdapter<Extension> sequence3 = adapters.sequence("Extension", new DerAdapter[]{adapters.getOBJECT_IDENTIFIER().asTypeHint(), adapters.getBOOLEAN().optional(bool), withExplicitBox}, CertificateAdapters$extension$1.INSTANCE, CertificateAdapters$extension$2.INSTANCE);
        extension = sequence3;
        BasicDerAdapter<AttributeTypeAndValue> sequence4 = adapters.sequence("AttributeTypeAndValue", new DerAdapter[]{adapters.getOBJECT_IDENTIFIER(), Adapters.any$default(adapters, new Pair[]{TuplesKt.to(Reflection.getOrCreateKotlinClass(String.class), adapters.getUTF8_STRING()), TuplesKt.to(Reflection.getOrCreateKotlinClass(Void.class), adapters.getPRINTABLE_STRING()), TuplesKt.to(Reflection.getOrCreateKotlinClass(AnyValue.class), adapters.getANY_VALUE())}, false, null, 6, null)}, CertificateAdapters$attributeTypeAndValue$1.INSTANCE, CertificateAdapters$attributeTypeAndValue$2.INSTANCE);
        attributeTypeAndValue = sequence4;
        BasicDerAdapter<List<List<AttributeTypeAndValue>>> asSequenceOf$default = DerAdapter.DefaultImpls.asSequenceOf$default(sequence4.asSetOf(), null, 0, 0L, 7, null);
        rdnSequence = asSequenceOf$default;
        DerAdapter<Pair<DerAdapter<?>, Object>> choice2 = adapters.choice(asSequenceOf$default);
        name = choice2;
        BasicDerAdapter<SubjectPublicKeyInfo> sequence5 = adapters.sequence("SubjectPublicKeyInfo", new DerAdapter[]{sequence2, adapters.getBIT_STRING()}, CertificateAdapters$subjectPublicKeyInfo$1.INSTANCE, CertificateAdapters$subjectPublicKeyInfo$2.INSTANCE);
        subjectPublicKeyInfo = sequence5;
        BasicDerAdapter withExplicitBox$default = DerAdapter.DefaultImpls.withExplicitBox$default(DerAdapter.DefaultImpls.asSequenceOf$default(sequence3, null, 0, 0L, 7, null), 0, 3L, null, 5, null);
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        BasicDerAdapter<TbsCertificate> sequence6 = adapters.sequence("TBSCertificate", new DerAdapter[]{DerAdapter.DefaultImpls.withExplicitBox$default(adapters.getINTEGER_AS_LONG(), 0, 0L, null, 5, null).optional(0L), adapters.getINTEGER_AS_BIG_INTEGER(), sequence2, choice2, sequence, choice2, sequence5, BasicDerAdapter.optional$default(BasicDerAdapter.withTag$default(adapters.getBIT_STRING(), 0, 1L, 1, null), null, 1, null), BasicDerAdapter.optional$default(BasicDerAdapter.withTag$default(adapters.getBIT_STRING(), 0, 2L, 1, null), null, 1, null), withExplicitBox$default.optional(emptyList)}, CertificateAdapters$tbsCertificate$1.INSTANCE, CertificateAdapters$tbsCertificate$2.INSTANCE);
        tbsCertificate = sequence6;
        certificate = adapters.sequence("Certificate", new DerAdapter[]{sequence6, sequence2, adapters.getBIT_STRING()}, CertificateAdapters$certificate$1.INSTANCE, CertificateAdapters$certificate$2.INSTANCE);
        privateKeyInfo = adapters.sequence("PrivateKeyInfo", new DerAdapter[]{adapters.getINTEGER_AS_LONG(), sequence2, adapters.getOCTET_STRING()}, CertificateAdapters$privateKeyInfo$1.INSTANCE, CertificateAdapters$privateKeyInfo$2.INSTANCE);
    }

    private CertificateAdapters() {
    }

    @NotNull
    public final BasicDerAdapter<AlgorithmIdentifier> getAlgorithmIdentifier$okhttp_tls() {
        return algorithmIdentifier;
    }

    @NotNull
    public final BasicDerAdapter<Certificate> getCertificate$okhttp_tls() {
        return certificate;
    }

    @NotNull
    public final BasicDerAdapter<Extension> getExtension$okhttp_tls() {
        return extension;
    }

    @NotNull
    public final DerAdapter<Pair<DerAdapter<?>, Object>> getGeneralName$okhttp_tls() {
        return generalName;
    }

    @NotNull
    public final BasicDerAdapter<String> getGeneralNameDnsName$okhttp_tls() {
        return generalNameDnsName;
    }

    @NotNull
    public final BasicDerAdapter<ByteString> getGeneralNameIpAddress$okhttp_tls() {
        return generalNameIpAddress;
    }

    @NotNull
    public final DerAdapter<Pair<DerAdapter<?>, Object>> getName$okhttp_tls() {
        return name;
    }

    @NotNull
    public final BasicDerAdapter<PrivateKeyInfo> getPrivateKeyInfo$okhttp_tls() {
        return privateKeyInfo;
    }

    @NotNull
    public final BasicDerAdapter<List<List<AttributeTypeAndValue>>> getRdnSequence$okhttp_tls() {
        return rdnSequence;
    }

    @NotNull
    public final BasicDerAdapter<SubjectPublicKeyInfo> getSubjectPublicKeyInfo$okhttp_tls() {
        return subjectPublicKeyInfo;
    }

    @NotNull
    public final BasicDerAdapter<TbsCertificate> getTbsCertificate$okhttp_tls() {
        return tbsCertificate;
    }

    @NotNull
    public final DerAdapter<Long> getTime$okhttp_tls() {
        return time;
    }
}
