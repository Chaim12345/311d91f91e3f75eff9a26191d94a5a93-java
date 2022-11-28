package io.opencensus.tags;

import io.opencensus.common.Scope;
import io.opencensus.internal.NoopScope;
import io.opencensus.internal.Utils;
import io.opencensus.tags.propagation.TagContextBinarySerializer;
import io.opencensus.tags.propagation.TagContextTextFormat;
import io.opencensus.tags.propagation.TagPropagationComponent;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
/* loaded from: classes3.dex */
final class NoopTags {

    /* JADX INFO: Access modifiers changed from: private */
    @Immutable
    /* loaded from: classes3.dex */
    public static final class NoopTagContext extends TagContext {

        /* renamed from: a  reason: collision with root package name */
        static final TagContext f10995a = new NoopTagContext();

        private NoopTagContext() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.opencensus.tags.TagContext
        public Iterator a() {
            return Collections.emptySet().iterator();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Immutable
    /* loaded from: classes3.dex */
    public static final class NoopTagContextBinarySerializer extends TagContextBinarySerializer {

        /* renamed from: a  reason: collision with root package name */
        static final TagContextBinarySerializer f10996a = new NoopTagContextBinarySerializer();

        /* renamed from: b  reason: collision with root package name */
        static final byte[] f10997b = new byte[0];

        private NoopTagContextBinarySerializer() {
        }

        @Override // io.opencensus.tags.propagation.TagContextBinarySerializer
        public TagContext fromByteArray(byte[] bArr) {
            Utils.checkNotNull(bArr, "bytes");
            return NoopTags.a();
        }

        @Override // io.opencensus.tags.propagation.TagContextBinarySerializer
        public byte[] toByteArray(TagContext tagContext) {
            Utils.checkNotNull(tagContext, "tags");
            return f10997b;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Immutable
    /* loaded from: classes3.dex */
    public static final class NoopTagContextBuilder extends TagContextBuilder {

        /* renamed from: a  reason: collision with root package name */
        static final TagContextBuilder f10998a = new NoopTagContextBuilder();

        private NoopTagContextBuilder() {
        }

        @Override // io.opencensus.tags.TagContextBuilder
        public TagContext build() {
            return NoopTags.a();
        }

        @Override // io.opencensus.tags.TagContextBuilder
        public Scope buildScoped() {
            return NoopScope.getInstance();
        }

        @Override // io.opencensus.tags.TagContextBuilder
        public TagContextBuilder put(TagKey tagKey, TagValue tagValue) {
            Utils.checkNotNull(tagKey, "key");
            Utils.checkNotNull(tagValue, "value");
            return this;
        }

        @Override // io.opencensus.tags.TagContextBuilder
        public TagContextBuilder put(TagKey tagKey, TagValue tagValue, TagMetadata tagMetadata) {
            Utils.checkNotNull(tagKey, "key");
            Utils.checkNotNull(tagValue, "value");
            Utils.checkNotNull(tagMetadata, "tagMetadata");
            return this;
        }

        @Override // io.opencensus.tags.TagContextBuilder
        public TagContextBuilder remove(TagKey tagKey) {
            Utils.checkNotNull(tagKey, "key");
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Immutable
    /* loaded from: classes3.dex */
    public static final class NoopTagContextTextFormat extends TagContextTextFormat {

        /* renamed from: a  reason: collision with root package name */
        static final NoopTagContextTextFormat f10999a = new NoopTagContextTextFormat();

        private NoopTagContextTextFormat() {
        }

        @Override // io.opencensus.tags.propagation.TagContextTextFormat
        public <C> TagContext extract(C c2, TagContextTextFormat.Getter<C> getter) {
            Utils.checkNotNull(c2, "carrier");
            Utils.checkNotNull(getter, "getter");
            return NoopTags.a();
        }

        @Override // io.opencensus.tags.propagation.TagContextTextFormat
        public List<String> fields() {
            return Collections.emptyList();
        }

        @Override // io.opencensus.tags.propagation.TagContextTextFormat
        public <C> void inject(TagContext tagContext, C c2, TagContextTextFormat.Setter<C> setter) {
            Utils.checkNotNull(tagContext, "tagContext");
            Utils.checkNotNull(c2, "carrier");
            Utils.checkNotNull(setter, "setter");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Immutable
    /* loaded from: classes3.dex */
    public static final class NoopTagPropagationComponent extends TagPropagationComponent {

        /* renamed from: a  reason: collision with root package name */
        static final TagPropagationComponent f11000a = new NoopTagPropagationComponent();

        private NoopTagPropagationComponent() {
        }

        @Override // io.opencensus.tags.propagation.TagPropagationComponent
        public TagContextBinarySerializer getBinarySerializer() {
            return NoopTags.b();
        }

        @Override // io.opencensus.tags.propagation.TagPropagationComponent
        public TagContextTextFormat getCorrelationContextFormat() {
            return NoopTags.d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Immutable
    /* loaded from: classes3.dex */
    public static final class NoopTagger extends Tagger {

        /* renamed from: a  reason: collision with root package name */
        static final Tagger f11001a = new NoopTagger();

        private NoopTagger() {
        }

        @Override // io.opencensus.tags.Tagger
        public TagContextBuilder currentBuilder() {
            return NoopTags.c();
        }

        @Override // io.opencensus.tags.Tagger
        public TagContext empty() {
            return NoopTags.a();
        }

        @Override // io.opencensus.tags.Tagger
        public TagContextBuilder emptyBuilder() {
            return NoopTags.c();
        }

        @Override // io.opencensus.tags.Tagger
        public TagContext getCurrentTagContext() {
            return NoopTags.a();
        }

        @Override // io.opencensus.tags.Tagger
        public TagContextBuilder toBuilder(TagContext tagContext) {
            Utils.checkNotNull(tagContext, "tags");
            return NoopTags.c();
        }

        @Override // io.opencensus.tags.Tagger
        public Scope withTagContext(TagContext tagContext) {
            Utils.checkNotNull(tagContext, "tags");
            return NoopScope.getInstance();
        }
    }

    @ThreadSafe
    /* loaded from: classes3.dex */
    private static final class NoopTagsComponent extends TagsComponent {
        private volatile boolean isRead;

        private NoopTagsComponent() {
        }

        @Override // io.opencensus.tags.TagsComponent
        public TaggingState getState() {
            this.isRead = true;
            return TaggingState.DISABLED;
        }

        @Override // io.opencensus.tags.TagsComponent
        public TagPropagationComponent getTagPropagationComponent() {
            return NoopTags.e();
        }

        @Override // io.opencensus.tags.TagsComponent
        public Tagger getTagger() {
            return NoopTags.f();
        }

        @Override // io.opencensus.tags.TagsComponent
        @Deprecated
        public void setState(TaggingState taggingState) {
            Utils.checkNotNull(taggingState, "state");
            Utils.checkState(!this.isRead, "State was already read, cannot set state.");
        }
    }

    private NoopTags() {
    }

    static TagContext a() {
        return NoopTagContext.f10995a;
    }

    static TagContextBinarySerializer b() {
        return NoopTagContextBinarySerializer.f10996a;
    }

    static TagContextBuilder c() {
        return NoopTagContextBuilder.f10998a;
    }

    static TagContextTextFormat d() {
        return NoopTagContextTextFormat.f10999a;
    }

    static TagPropagationComponent e() {
        return NoopTagPropagationComponent.f11000a;
    }

    static Tagger f() {
        return NoopTagger.f11001a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TagsComponent g() {
        return new NoopTagsComponent();
    }
}
