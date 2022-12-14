package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
@Beta
@GwtIncompatible
/* loaded from: classes2.dex */
public final class Resources {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class UrlByteSource extends ByteSource {
        private final URL url;

        private UrlByteSource(URL url) {
            this.url = (URL) Preconditions.checkNotNull(url);
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() {
            return this.url.openStream();
        }

        public String toString() {
            return "Resources.asByteSource(" + this.url + ")";
        }
    }

    private Resources() {
    }

    public static ByteSource asByteSource(URL url) {
        return new UrlByteSource(url);
    }

    public static CharSource asCharSource(URL url, Charset charset) {
        return asByteSource(url).asCharSource(charset);
    }

    public static void copy(URL url, OutputStream outputStream) {
        asByteSource(url).copyTo(outputStream);
    }

    @CanIgnoreReturnValue
    public static URL getResource(Class<?> cls, String str) {
        URL resource = cls.getResource(str);
        Preconditions.checkArgument(resource != null, "resource %s relative to %s not found.", str, cls.getName());
        return resource;
    }

    @CanIgnoreReturnValue
    public static URL getResource(String str) {
        URL resource = ((ClassLoader) MoreObjects.firstNonNull(Thread.currentThread().getContextClassLoader(), Resources.class.getClassLoader())).getResource(str);
        Preconditions.checkArgument(resource != null, "resource %s not found.", str);
        return resource;
    }

    @CanIgnoreReturnValue
    public static <T> T readLines(URL url, Charset charset, LineProcessor<T> lineProcessor) {
        return (T) asCharSource(url, charset).readLines(lineProcessor);
    }

    public static List<String> readLines(URL url, Charset charset) {
        return (List) readLines(url, charset, new LineProcessor<List<String>>() { // from class: com.google.common.io.Resources.1

            /* renamed from: a  reason: collision with root package name */
            final List f9316a = Lists.newArrayList();

            @Override // com.google.common.io.LineProcessor
            public List<String> getResult() {
                return this.f9316a;
            }

            @Override // com.google.common.io.LineProcessor
            public boolean processLine(String str) {
                this.f9316a.add(str);
                return true;
            }
        });
    }

    public static byte[] toByteArray(URL url) {
        return asByteSource(url).read();
    }

    public static String toString(URL url, Charset charset) {
        return asCharSource(url, charset).read();
    }
}
