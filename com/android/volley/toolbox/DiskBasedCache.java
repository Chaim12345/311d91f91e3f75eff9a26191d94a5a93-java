package com.android.volley.toolbox;

import android.os.SystemClock;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.android.volley.Cache;
import com.android.volley.Header;
import com.android.volley.VolleyLog;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class DiskBasedCache implements Cache {
    private static final int CACHE_MAGIC = 538247942;
    private static final int DEFAULT_DISK_USAGE_BYTES = 5242880;
    private final Map<String, CacheHeader> mEntries;
    private final int mMaxCacheSizeInBytes;
    private final FileSupplier mRootDirectorySupplier;
    private long mTotalSize;

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes.dex */
    public static class CacheHeader {

        /* renamed from: a  reason: collision with root package name */
        long f4539a;

        /* renamed from: b  reason: collision with root package name */
        final String f4540b;

        /* renamed from: c  reason: collision with root package name */
        final String f4541c;

        /* renamed from: d  reason: collision with root package name */
        final long f4542d;

        /* renamed from: e  reason: collision with root package name */
        final long f4543e;

        /* renamed from: f  reason: collision with root package name */
        final long f4544f;

        /* renamed from: g  reason: collision with root package name */
        final long f4545g;

        /* renamed from: h  reason: collision with root package name */
        final List f4546h;

        CacheHeader(String str, Cache.Entry entry) {
            this(str, entry.etag, entry.serverDate, entry.lastModified, entry.ttl, entry.softTtl, getAllResponseHeaders(entry));
        }

        private CacheHeader(String str, String str2, long j2, long j3, long j4, long j5, List<Header> list) {
            this.f4540b = str;
            this.f4541c = "".equals(str2) ? null : str2;
            this.f4542d = j2;
            this.f4543e = j3;
            this.f4544f = j4;
            this.f4545g = j5;
            this.f4546h = list;
        }

        static CacheHeader a(CountingInputStream countingInputStream) {
            if (DiskBasedCache.d(countingInputStream) == DiskBasedCache.CACHE_MAGIC) {
                return new CacheHeader(DiskBasedCache.f(countingInputStream), DiskBasedCache.f(countingInputStream), DiskBasedCache.e(countingInputStream), DiskBasedCache.e(countingInputStream), DiskBasedCache.e(countingInputStream), DiskBasedCache.e(countingInputStream), DiskBasedCache.c(countingInputStream));
            }
            throw new IOException();
        }

        private static List<Header> getAllResponseHeaders(Cache.Entry entry) {
            List<Header> list = entry.allResponseHeaders;
            return list != null ? list : HttpHeaderParser.d(entry.responseHeaders);
        }

        Cache.Entry b(byte[] bArr) {
            Cache.Entry entry = new Cache.Entry();
            entry.data = bArr;
            entry.etag = this.f4541c;
            entry.serverDate = this.f4542d;
            entry.lastModified = this.f4543e;
            entry.ttl = this.f4544f;
            entry.softTtl = this.f4545g;
            entry.responseHeaders = HttpHeaderParser.e(this.f4546h);
            entry.allResponseHeaders = Collections.unmodifiableList(this.f4546h);
            return entry;
        }

        boolean c(OutputStream outputStream) {
            try {
                DiskBasedCache.i(outputStream, DiskBasedCache.CACHE_MAGIC);
                DiskBasedCache.k(outputStream, this.f4540b);
                String str = this.f4541c;
                if (str == null) {
                    str = "";
                }
                DiskBasedCache.k(outputStream, str);
                DiskBasedCache.j(outputStream, this.f4542d);
                DiskBasedCache.j(outputStream, this.f4543e);
                DiskBasedCache.j(outputStream, this.f4544f);
                DiskBasedCache.j(outputStream, this.f4545g);
                DiskBasedCache.h(this.f4546h, outputStream);
                outputStream.flush();
                return true;
            } catch (IOException e2) {
                VolleyLog.d("%s", e2.toString());
                return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes.dex */
    public static class CountingInputStream extends FilterInputStream {
        private long bytesRead;
        private final long length;

        CountingInputStream(InputStream inputStream, long j2) {
            super(inputStream);
            this.length = j2;
        }

        long a() {
            return this.length - this.bytesRead;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read() {
            int read = super.read();
            if (read != -1) {
                this.bytesRead++;
            }
            return read;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read(byte[] bArr, int i2, int i3) {
            int read = super.read(bArr, i2, i3);
            if (read != -1) {
                this.bytesRead += read;
            }
            return read;
        }
    }

    /* loaded from: classes.dex */
    public interface FileSupplier {
        File get();
    }

    public DiskBasedCache(FileSupplier fileSupplier) {
        this(fileSupplier, (int) DEFAULT_DISK_USAGE_BYTES);
    }

    public DiskBasedCache(FileSupplier fileSupplier, int i2) {
        this.mEntries = new LinkedHashMap(16, 0.75f, true);
        this.mTotalSize = 0L;
        this.mRootDirectorySupplier = fileSupplier;
        this.mMaxCacheSizeInBytes = i2;
    }

    public DiskBasedCache(File file) {
        this(file, (int) DEFAULT_DISK_USAGE_BYTES);
    }

    public DiskBasedCache(final File file, int i2) {
        this.mEntries = new LinkedHashMap(16, 0.75f, true);
        this.mTotalSize = 0L;
        this.mRootDirectorySupplier = new FileSupplier(this) { // from class: com.android.volley.toolbox.DiskBasedCache.1
            @Override // com.android.volley.toolbox.DiskBasedCache.FileSupplier
            public File get() {
                return file;
            }
        };
        this.mMaxCacheSizeInBytes = i2;
    }

    static List c(CountingInputStream countingInputStream) {
        int d2 = d(countingInputStream);
        if (d2 < 0) {
            throw new IOException("readHeaderList size=" + d2);
        }
        List emptyList = d2 == 0 ? Collections.emptyList() : new ArrayList();
        for (int i2 = 0; i2 < d2; i2++) {
            emptyList.add(new Header(f(countingInputStream).intern(), f(countingInputStream).intern()));
        }
        return emptyList;
    }

    static int d(InputStream inputStream) {
        return (read(inputStream) << 24) | (read(inputStream) << 0) | 0 | (read(inputStream) << 8) | (read(inputStream) << 16);
    }

    static long e(InputStream inputStream) {
        return ((read(inputStream) & 255) << 0) | 0 | ((read(inputStream) & 255) << 8) | ((read(inputStream) & 255) << 16) | ((read(inputStream) & 255) << 24) | ((read(inputStream) & 255) << 32) | ((read(inputStream) & 255) << 40) | ((read(inputStream) & 255) << 48) | ((255 & read(inputStream)) << 56);
    }

    static String f(CountingInputStream countingInputStream) {
        return new String(g(countingInputStream, e(countingInputStream)), "UTF-8");
    }

    @VisibleForTesting
    static byte[] g(CountingInputStream countingInputStream, long j2) {
        long a2 = countingInputStream.a();
        if (j2 >= 0 && j2 <= a2) {
            int i2 = (int) j2;
            if (i2 == j2) {
                byte[] bArr = new byte[i2];
                new DataInputStream(countingInputStream).readFully(bArr);
                return bArr;
            }
        }
        throw new IOException("streamToBytes length=" + j2 + ", maxLength=" + a2);
    }

    private String getFilenameForKey(String str) {
        int length = str.length() / 2;
        String valueOf = String.valueOf(str.substring(0, length).hashCode());
        return valueOf + String.valueOf(str.substring(length).hashCode());
    }

    static void h(List list, OutputStream outputStream) {
        if (list == null) {
            i(outputStream, 0);
            return;
        }
        i(outputStream, list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Header header = (Header) it.next();
            k(outputStream, header.getName());
            k(outputStream, header.getValue());
        }
    }

    static void i(OutputStream outputStream, int i2) {
        outputStream.write((i2 >> 0) & 255);
        outputStream.write((i2 >> 8) & 255);
        outputStream.write((i2 >> 16) & 255);
        outputStream.write((i2 >> 24) & 255);
    }

    private void initializeIfRootDirectoryDeleted() {
        if (this.mRootDirectorySupplier.get().exists()) {
            return;
        }
        VolleyLog.d("Re-initializing cache after external clearing.", new Object[0]);
        this.mEntries.clear();
        this.mTotalSize = 0L;
        initialize();
    }

    static void j(OutputStream outputStream, long j2) {
        outputStream.write((byte) (j2 >>> 0));
        outputStream.write((byte) (j2 >>> 8));
        outputStream.write((byte) (j2 >>> 16));
        outputStream.write((byte) (j2 >>> 24));
        outputStream.write((byte) (j2 >>> 32));
        outputStream.write((byte) (j2 >>> 40));
        outputStream.write((byte) (j2 >>> 48));
        outputStream.write((byte) (j2 >>> 56));
    }

    static void k(OutputStream outputStream, String str) {
        byte[] bytes = str.getBytes("UTF-8");
        j(outputStream, bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }

    private void pruneIfNeeded() {
        if (this.mTotalSize < this.mMaxCacheSizeInBytes) {
            return;
        }
        if (VolleyLog.DEBUG) {
            VolleyLog.v("Pruning old cache entries.", new Object[0]);
        }
        long j2 = this.mTotalSize;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Iterator<Map.Entry<String, CacheHeader>> it = this.mEntries.entrySet().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            CacheHeader value = it.next().getValue();
            if (getFileForKey(value.f4540b).delete()) {
                this.mTotalSize -= value.f4539a;
            } else {
                String str = value.f4540b;
                VolleyLog.d("Could not delete cache entry for key=%s, filename=%s", str, getFilenameForKey(str));
            }
            it.remove();
            i2++;
            if (((float) this.mTotalSize) < this.mMaxCacheSizeInBytes * 0.9f) {
                break;
            }
        }
        if (VolleyLog.DEBUG) {
            VolleyLog.v("pruned %d files, %d bytes, %d ms", Integer.valueOf(i2), Long.valueOf(this.mTotalSize - j2), Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
        }
    }

    private void putEntry(String str, CacheHeader cacheHeader) {
        if (this.mEntries.containsKey(str)) {
            this.mTotalSize += cacheHeader.f4539a - this.mEntries.get(str).f4539a;
        } else {
            this.mTotalSize += cacheHeader.f4539a;
        }
        this.mEntries.put(str, cacheHeader);
    }

    private static int read(InputStream inputStream) {
        int read = inputStream.read();
        if (read != -1) {
            return read;
        }
        throw new EOFException();
    }

    private void removeEntry(String str) {
        CacheHeader remove = this.mEntries.remove(str);
        if (remove != null) {
            this.mTotalSize -= remove.f4539a;
        }
    }

    @VisibleForTesting
    InputStream a(File file) {
        return new FileInputStream(file);
    }

    @VisibleForTesting
    OutputStream b(File file) {
        return new FileOutputStream(file);
    }

    @Override // com.android.volley.Cache
    public synchronized void clear() {
        File[] listFiles = this.mRootDirectorySupplier.get().listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                file.delete();
            }
        }
        this.mEntries.clear();
        this.mTotalSize = 0L;
        VolleyLog.d("Cache cleared.", new Object[0]);
    }

    @Override // com.android.volley.Cache
    public synchronized Cache.Entry get(String str) {
        CacheHeader cacheHeader = this.mEntries.get(str);
        if (cacheHeader == null) {
            return null;
        }
        File fileForKey = getFileForKey(str);
        try {
            CountingInputStream countingInputStream = new CountingInputStream(new BufferedInputStream(a(fileForKey)), fileForKey.length());
            try {
                CacheHeader a2 = CacheHeader.a(countingInputStream);
                if (TextUtils.equals(str, a2.f4540b)) {
                    return cacheHeader.b(g(countingInputStream, countingInputStream.a()));
                }
                VolleyLog.d("%s: key=%s, found=%s", fileForKey.getAbsolutePath(), str, a2.f4540b);
                removeEntry(str);
                return null;
            } finally {
                countingInputStream.close();
            }
        } catch (IOException e2) {
            VolleyLog.d("%s: %s", fileForKey.getAbsolutePath(), e2.toString());
            remove(str);
            return null;
        }
    }

    public File getFileForKey(String str) {
        return new File(this.mRootDirectorySupplier.get(), getFilenameForKey(str));
    }

    @Override // com.android.volley.Cache
    public synchronized void initialize() {
        File file = this.mRootDirectorySupplier.get();
        if (!file.exists()) {
            if (!file.mkdirs()) {
                VolleyLog.e("Unable to create cache dir %s", file.getAbsolutePath());
            }
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return;
        }
        for (File file2 : listFiles) {
            try {
                long length = file2.length();
                CountingInputStream countingInputStream = new CountingInputStream(new BufferedInputStream(a(file2)), length);
                try {
                    CacheHeader a2 = CacheHeader.a(countingInputStream);
                    a2.f4539a = length;
                    putEntry(a2.f4540b, a2);
                    countingInputStream.close();
                } catch (Throwable th) {
                    countingInputStream.close();
                    throw th;
                    break;
                }
            } catch (IOException unused) {
                file2.delete();
            }
        }
    }

    @Override // com.android.volley.Cache
    public synchronized void invalidate(String str, boolean z) {
        Cache.Entry entry = get(str);
        if (entry != null) {
            entry.softTtl = 0L;
            if (z) {
                entry.ttl = 0L;
            }
            put(str, entry);
        }
    }

    @Override // com.android.volley.Cache
    public synchronized void put(String str, Cache.Entry entry) {
        BufferedOutputStream bufferedOutputStream;
        CacheHeader cacheHeader;
        long j2 = this.mTotalSize;
        byte[] bArr = entry.data;
        long length = j2 + bArr.length;
        int i2 = this.mMaxCacheSizeInBytes;
        if (length <= i2 || bArr.length <= i2 * 0.9f) {
            File fileForKey = getFileForKey(str);
            try {
                bufferedOutputStream = new BufferedOutputStream(b(fileForKey));
                cacheHeader = new CacheHeader(str, entry);
            } catch (IOException unused) {
                if (!fileForKey.delete()) {
                    VolleyLog.d("Could not clean up file %s", fileForKey.getAbsolutePath());
                }
                initializeIfRootDirectoryDeleted();
            }
            if (!cacheHeader.c(bufferedOutputStream)) {
                bufferedOutputStream.close();
                VolleyLog.d("Failed to write header for %s", fileForKey.getAbsolutePath());
                throw new IOException();
            }
            bufferedOutputStream.write(entry.data);
            bufferedOutputStream.close();
            cacheHeader.f4539a = fileForKey.length();
            putEntry(str, cacheHeader);
            pruneIfNeeded();
        }
    }

    @Override // com.android.volley.Cache
    public synchronized void remove(String str) {
        boolean delete = getFileForKey(str).delete();
        removeEntry(str);
        if (!delete) {
            VolleyLog.d("Could not delete cache entry for key=%s, filename=%s", str, getFilenameForKey(str));
        }
    }
}
