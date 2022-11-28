package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.hash.Funnels;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import kotlin.jvm.internal.LongCompanionObject;
@GwtIncompatible
/* loaded from: classes2.dex */
public abstract class ByteSource {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class AsCharSource extends CharSource {

        /* renamed from: a  reason: collision with root package name */
        final Charset f9289a;

        AsCharSource(Charset charset) {
            this.f9289a = (Charset) Preconditions.checkNotNull(charset);
        }

        @Override // com.google.common.io.CharSource
        public ByteSource asByteSource(Charset charset) {
            return charset.equals(this.f9289a) ? ByteSource.this : super.asByteSource(charset);
        }

        @Override // com.google.common.io.CharSource
        public Reader openStream() {
            return new InputStreamReader(ByteSource.this.openStream(), this.f9289a);
        }

        @Override // com.google.common.io.CharSource
        public String read() {
            return new String(ByteSource.this.read(), this.f9289a);
        }

        public String toString() {
            return ByteSource.this.toString() + ".asCharSource(" + this.f9289a + ")";
        }
    }

    /* loaded from: classes2.dex */
    private static class ByteArrayByteSource extends ByteSource {

        /* renamed from: a  reason: collision with root package name */
        final byte[] f9291a;

        /* renamed from: b  reason: collision with root package name */
        final int f9292b;

        /* renamed from: c  reason: collision with root package name */
        final int f9293c;

        ByteArrayByteSource(byte[] bArr) {
            this(bArr, 0, bArr.length);
        }

        ByteArrayByteSource(byte[] bArr, int i2, int i3) {
            this.f9291a = bArr;
            this.f9292b = i2;
            this.f9293c = i3;
        }

        @Override // com.google.common.io.ByteSource
        public long copyTo(OutputStream outputStream) {
            outputStream.write(this.f9291a, this.f9292b, this.f9293c);
            return this.f9293c;
        }

        @Override // com.google.common.io.ByteSource
        public HashCode hash(HashFunction hashFunction) {
            return hashFunction.hashBytes(this.f9291a, this.f9292b, this.f9293c);
        }

        @Override // com.google.common.io.ByteSource
        public boolean isEmpty() {
            return this.f9293c == 0;
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openBufferedStream() {
            return openStream();
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() {
            return new ByteArrayInputStream(this.f9291a, this.f9292b, this.f9293c);
        }

        @Override // com.google.common.io.ByteSource
        public <T> T read(ByteProcessor<T> byteProcessor) {
            byteProcessor.processBytes(this.f9291a, this.f9292b, this.f9293c);
            return byteProcessor.getResult();
        }

        @Override // com.google.common.io.ByteSource
        public byte[] read() {
            byte[] bArr = this.f9291a;
            int i2 = this.f9292b;
            return Arrays.copyOfRange(bArr, i2, this.f9293c + i2);
        }

        @Override // com.google.common.io.ByteSource
        public long size() {
            return this.f9293c;
        }

        @Override // com.google.common.io.ByteSource
        public Optional<Long> sizeIfKnown() {
            return Optional.of(Long.valueOf(this.f9293c));
        }

        @Override // com.google.common.io.ByteSource
        public ByteSource slice(long j2, long j3) {
            Preconditions.checkArgument(j2 >= 0, "offset (%s) may not be negative", j2);
            Preconditions.checkArgument(j3 >= 0, "length (%s) may not be negative", j3);
            long min = Math.min(j2, this.f9293c);
            return new ByteArrayByteSource(this.f9291a, this.f9292b + ((int) min), (int) Math.min(j3, this.f9293c - min));
        }

        public String toString() {
            return "ByteSource.wrap(" + Ascii.truncate(BaseEncoding.base16().encode(this.f9291a, this.f9292b, this.f9293c), 30, "...") + ")";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class ConcatenatedByteSource extends ByteSource {

        /* renamed from: a  reason: collision with root package name */
        final Iterable f9294a;

        ConcatenatedByteSource(Iterable iterable) {
            this.f9294a = (Iterable) Preconditions.checkNotNull(iterable);
        }

        @Override // com.google.common.io.ByteSource
        public boolean isEmpty() {
            for (ByteSource byteSource : this.f9294a) {
                if (!byteSource.isEmpty()) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() {
            return new MultiInputStream(this.f9294a.iterator());
        }

        @Override // com.google.common.io.ByteSource
        public long size() {
            long j2 = 0;
            for (ByteSource byteSource : this.f9294a) {
                j2 += byteSource.size();
                if (j2 < 0) {
                    return LongCompanionObject.MAX_VALUE;
                }
            }
            return j2;
        }

        @Override // com.google.common.io.ByteSource
        public Optional<Long> sizeIfKnown() {
            Long valueOf;
            Iterable iterable = this.f9294a;
            if (iterable instanceof Collection) {
                Iterator it = iterable.iterator();
                long j2 = 0;
                while (true) {
                    if (!it.hasNext()) {
                        valueOf = Long.valueOf(j2);
                        break;
                    }
                    Optional<Long> sizeIfKnown = ((ByteSource) it.next()).sizeIfKnown();
                    if (!sizeIfKnown.isPresent()) {
                        return Optional.absent();
                    }
                    j2 += sizeIfKnown.get().longValue();
                    if (j2 < 0) {
                        valueOf = Long.valueOf((long) LongCompanionObject.MAX_VALUE);
                        break;
                    }
                }
                return Optional.of(valueOf);
            }
            return Optional.absent();
        }

        public String toString() {
            return "ByteSource.concat(" + this.f9294a + ")";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class EmptyByteSource extends ByteArrayByteSource {

        /* renamed from: d  reason: collision with root package name */
        static final EmptyByteSource f9295d = new EmptyByteSource();

        EmptyByteSource() {
            super(new byte[0]);
        }

        @Override // com.google.common.io.ByteSource
        public CharSource asCharSource(Charset charset) {
            Preconditions.checkNotNull(charset);
            return CharSource.empty();
        }

        @Override // com.google.common.io.ByteSource.ByteArrayByteSource, com.google.common.io.ByteSource
        public byte[] read() {
            return this.f9291a;
        }

        @Override // com.google.common.io.ByteSource.ByteArrayByteSource
        public String toString() {
            return "ByteSource.empty()";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public final class SlicedByteSource extends ByteSource {

        /* renamed from: a  reason: collision with root package name */
        final long f9296a;

        /* renamed from: b  reason: collision with root package name */
        final long f9297b;

        SlicedByteSource(long j2, long j3) {
            Preconditions.checkArgument(j2 >= 0, "offset (%s) may not be negative", j2);
            Preconditions.checkArgument(j3 >= 0, "length (%s) may not be negative", j3);
            this.f9296a = j2;
            this.f9297b = j3;
        }

        private InputStream sliceStream(InputStream inputStream) {
            long j2 = this.f9296a;
            if (j2 > 0) {
                try {
                    if (ByteStreams.b(inputStream, j2) < this.f9296a) {
                        inputStream.close();
                        return new ByteArrayInputStream(new byte[0]);
                    }
                } finally {
                }
            }
            return ByteStreams.limit(inputStream, this.f9297b);
        }

        @Override // com.google.common.io.ByteSource
        public boolean isEmpty() {
            return this.f9297b == 0 || super.isEmpty();
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openBufferedStream() {
            return sliceStream(ByteSource.this.openBufferedStream());
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() {
            return sliceStream(ByteSource.this.openStream());
        }

        @Override // com.google.common.io.ByteSource
        public Optional<Long> sizeIfKnown() {
            Optional<Long> sizeIfKnown = ByteSource.this.sizeIfKnown();
            if (sizeIfKnown.isPresent()) {
                long longValue = sizeIfKnown.get().longValue();
                return Optional.of(Long.valueOf(Math.min(this.f9297b, longValue - Math.min(this.f9296a, longValue))));
            }
            return Optional.absent();
        }

        @Override // com.google.common.io.ByteSource
        public ByteSource slice(long j2, long j3) {
            Preconditions.checkArgument(j2 >= 0, "offset (%s) may not be negative", j2);
            Preconditions.checkArgument(j3 >= 0, "length (%s) may not be negative", j3);
            long j4 = this.f9297b - j2;
            return j4 <= 0 ? ByteSource.empty() : ByteSource.this.slice(this.f9296a + j2, Math.min(j3, j4));
        }

        public String toString() {
            return ByteSource.this.toString() + ".slice(" + this.f9296a + ", " + this.f9297b + ")";
        }
    }

    public static ByteSource concat(Iterable<? extends ByteSource> iterable) {
        return new ConcatenatedByteSource(iterable);
    }

    public static ByteSource concat(Iterator<? extends ByteSource> it) {
        return concat(ImmutableList.copyOf(it));
    }

    public static ByteSource concat(ByteSource... byteSourceArr) {
        return concat(ImmutableList.copyOf(byteSourceArr));
    }

    private long countBySkipping(InputStream inputStream) {
        long j2 = 0;
        while (true) {
            long b2 = ByteStreams.b(inputStream, 2147483647L);
            if (b2 <= 0) {
                return j2;
            }
            j2 += b2;
        }
    }

    public static ByteSource empty() {
        return EmptyByteSource.f9295d;
    }

    public static ByteSource wrap(byte[] bArr) {
        return new ByteArrayByteSource(bArr);
    }

    public CharSource asCharSource(Charset charset) {
        return new AsCharSource(charset);
    }

    public boolean contentEquals(ByteSource byteSource) {
        int read;
        Preconditions.checkNotNull(byteSource);
        byte[] a2 = ByteStreams.a();
        byte[] a3 = ByteStreams.a();
        Closer create = Closer.create();
        try {
            InputStream inputStream = (InputStream) create.register(openStream());
            InputStream inputStream2 = (InputStream) create.register(byteSource.openStream());
            do {
                read = ByteStreams.read(inputStream, a2, 0, a2.length);
                if (read == ByteStreams.read(inputStream2, a3, 0, a3.length) && Arrays.equals(a2, a3)) {
                }
                return false;
            } while (read == a2.length);
            return true;
        } finally {
        }
    }

    @CanIgnoreReturnValue
    public long copyTo(ByteSink byteSink) {
        Preconditions.checkNotNull(byteSink);
        Closer create = Closer.create();
        try {
            return ByteStreams.copy((InputStream) create.register(openStream()), (OutputStream) create.register(byteSink.openStream()));
        } finally {
        }
    }

    @CanIgnoreReturnValue
    public long copyTo(OutputStream outputStream) {
        Preconditions.checkNotNull(outputStream);
        try {
            return ByteStreams.copy((InputStream) Closer.create().register(openStream()), outputStream);
        } finally {
        }
    }

    public HashCode hash(HashFunction hashFunction) {
        Hasher newHasher = hashFunction.newHasher();
        copyTo(Funnels.asOutputStream(newHasher));
        return newHasher.hash();
    }

    public boolean isEmpty() {
        Optional<Long> sizeIfKnown = sizeIfKnown();
        if (sizeIfKnown.isPresent()) {
            return sizeIfKnown.get().longValue() == 0;
        }
        Closer create = Closer.create();
        try {
            return ((InputStream) create.register(openStream())).read() == -1;
        } catch (Throwable th) {
            try {
                throw create.rethrow(th);
            } finally {
                create.close();
            }
        }
    }

    public InputStream openBufferedStream() {
        InputStream openStream = openStream();
        return openStream instanceof BufferedInputStream ? (BufferedInputStream) openStream : new BufferedInputStream(openStream);
    }

    public abstract InputStream openStream();

    @CanIgnoreReturnValue
    @Beta
    public <T> T read(ByteProcessor<T> byteProcessor) {
        Preconditions.checkNotNull(byteProcessor);
        try {
            return (T) ByteStreams.readBytes((InputStream) Closer.create().register(openStream()), byteProcessor);
        } finally {
        }
    }

    public byte[] read() {
        Closer create = Closer.create();
        try {
            InputStream inputStream = (InputStream) create.register(openStream());
            Optional<Long> sizeIfKnown = sizeIfKnown();
            return sizeIfKnown.isPresent() ? ByteStreams.c(inputStream, sizeIfKnown.get().longValue()) : ByteStreams.toByteArray(inputStream);
        } catch (Throwable th) {
            try {
                throw create.rethrow(th);
            } finally {
                create.close();
            }
        }
    }

    public long size() {
        Optional<Long> sizeIfKnown = sizeIfKnown();
        if (sizeIfKnown.isPresent()) {
            return sizeIfKnown.get().longValue();
        }
        Closer create = Closer.create();
        try {
            return countBySkipping((InputStream) create.register(openStream()));
        } catch (IOException unused) {
            create.close();
            try {
                return ByteStreams.exhaust((InputStream) Closer.create().register(openStream()));
            } finally {
            }
        } finally {
        }
    }

    @Beta
    public Optional<Long> sizeIfKnown() {
        return Optional.absent();
    }

    public ByteSource slice(long j2, long j3) {
        return new SlicedByteSource(j2, j3);
    }
}
