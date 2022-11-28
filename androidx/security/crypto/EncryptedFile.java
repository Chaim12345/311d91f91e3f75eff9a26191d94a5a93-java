package androidx.security.crypto;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.StreamingAead;
import com.google.crypto.tink.integration.android.AndroidKeysetManager;
import com.google.crypto.tink.integration.android.AndroidKeystoreKmsClient;
import com.google.crypto.tink.streamingaead.AesGcmHkdfStreamingKeyManager;
import com.google.crypto.tink.streamingaead.StreamingAeadConfig;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
/* loaded from: classes.dex */
public final class EncryptedFile {
    private static final String KEYSET_ALIAS = "__androidx_security_crypto_encrypted_file_keyset__";
    private static final String KEYSET_PREF_NAME = "__androidx_security_crypto_encrypted_file_pref__";

    /* renamed from: a  reason: collision with root package name */
    final File f3965a;

    /* renamed from: b  reason: collision with root package name */
    final StreamingAead f3966b;

    /* loaded from: classes.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        File f3967a;

        /* renamed from: b  reason: collision with root package name */
        final FileEncryptionScheme f3968b;

        /* renamed from: c  reason: collision with root package name */
        final Context f3969c;

        /* renamed from: d  reason: collision with root package name */
        final String f3970d;

        /* renamed from: e  reason: collision with root package name */
        String f3971e = EncryptedFile.KEYSET_PREF_NAME;

        /* renamed from: f  reason: collision with root package name */
        String f3972f = EncryptedFile.KEYSET_ALIAS;

        @SuppressLint({"StreamFiles"})
        public Builder(@NonNull Context context, @NonNull File file, @NonNull MasterKey masterKey, @NonNull FileEncryptionScheme fileEncryptionScheme) {
            this.f3967a = file;
            this.f3968b = fileEncryptionScheme;
            this.f3969c = context.getApplicationContext();
            this.f3970d = masterKey.a();
        }

        @Deprecated
        public Builder(@NonNull File file, @NonNull Context context, @NonNull String str, @NonNull FileEncryptionScheme fileEncryptionScheme) {
            this.f3967a = file;
            this.f3968b = fileEncryptionScheme;
            this.f3969c = context.getApplicationContext();
            this.f3970d = str;
        }

        @NonNull
        public EncryptedFile build() {
            StreamingAeadConfig.register();
            AndroidKeysetManager.Builder withSharedPref = new AndroidKeysetManager.Builder().withKeyTemplate(this.f3968b.a()).withSharedPref(this.f3969c, this.f3972f, this.f3971e);
            return new EncryptedFile(this.f3967a, this.f3972f, (StreamingAead) withSharedPref.withMasterKeyUri(AndroidKeystoreKmsClient.PREFIX + this.f3970d).build().getKeysetHandle().getPrimitive(StreamingAead.class), this.f3969c);
        }

        @NonNull
        public Builder setKeysetAlias(@NonNull String str) {
            this.f3972f = str;
            return this;
        }

        @NonNull
        public Builder setKeysetPrefName(@NonNull String str) {
            this.f3971e = str;
            return this;
        }
    }

    /* loaded from: classes.dex */
    private static final class EncryptedFileInputStream extends FileInputStream {
        private final InputStream mEncryptedInputStream;

        EncryptedFileInputStream(FileDescriptor fileDescriptor, InputStream inputStream) {
            super(fileDescriptor);
            this.mEncryptedInputStream = inputStream;
        }

        @Override // java.io.FileInputStream, java.io.InputStream
        public int available() {
            return this.mEncryptedInputStream.available();
        }

        @Override // java.io.FileInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.mEncryptedInputStream.close();
        }

        @Override // java.io.FileInputStream
        public FileChannel getChannel() {
            throw new UnsupportedOperationException("For encrypted files, please open the relevant FileInput/FileOutputStream.");
        }

        @Override // java.io.InputStream
        public synchronized void mark(int i2) {
            this.mEncryptedInputStream.mark(i2);
        }

        @Override // java.io.InputStream
        public boolean markSupported() {
            return this.mEncryptedInputStream.markSupported();
        }

        @Override // java.io.FileInputStream, java.io.InputStream
        public int read() {
            return this.mEncryptedInputStream.read();
        }

        @Override // java.io.FileInputStream, java.io.InputStream
        public int read(@NonNull byte[] bArr) {
            return this.mEncryptedInputStream.read(bArr);
        }

        @Override // java.io.FileInputStream, java.io.InputStream
        public int read(@NonNull byte[] bArr, int i2, int i3) {
            return this.mEncryptedInputStream.read(bArr, i2, i3);
        }

        @Override // java.io.InputStream
        public synchronized void reset() {
            this.mEncryptedInputStream.reset();
        }

        @Override // java.io.FileInputStream, java.io.InputStream
        public long skip(long j2) {
            return this.mEncryptedInputStream.skip(j2);
        }
    }

    /* loaded from: classes.dex */
    private static final class EncryptedFileOutputStream extends FileOutputStream {
        private final OutputStream mEncryptedOutputStream;

        EncryptedFileOutputStream(FileDescriptor fileDescriptor, OutputStream outputStream) {
            super(fileDescriptor);
            this.mEncryptedOutputStream = outputStream;
        }

        @Override // java.io.FileOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.mEncryptedOutputStream.close();
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() {
            this.mEncryptedOutputStream.flush();
        }

        @Override // java.io.FileOutputStream
        @NonNull
        public FileChannel getChannel() {
            throw new UnsupportedOperationException("For encrypted files, please open the relevant FileInput/FileOutputStream.");
        }

        @Override // java.io.FileOutputStream, java.io.OutputStream
        public void write(int i2) {
            this.mEncryptedOutputStream.write(i2);
        }

        @Override // java.io.FileOutputStream, java.io.OutputStream
        public void write(@NonNull byte[] bArr) {
            this.mEncryptedOutputStream.write(bArr);
        }

        @Override // java.io.FileOutputStream, java.io.OutputStream
        public void write(@NonNull byte[] bArr, int i2, int i3) {
            this.mEncryptedOutputStream.write(bArr, i2, i3);
        }
    }

    /* loaded from: classes.dex */
    public enum FileEncryptionScheme {
        AES256_GCM_HKDF_4KB(AesGcmHkdfStreamingKeyManager.aes256GcmHkdf4KBTemplate());
        
        private final KeyTemplate mStreamingAeadKeyTemplate;

        FileEncryptionScheme(KeyTemplate keyTemplate) {
            this.mStreamingAeadKeyTemplate = keyTemplate;
        }

        KeyTemplate a() {
            return this.mStreamingAeadKeyTemplate;
        }
    }

    EncryptedFile(@NonNull File file, @NonNull String str, @NonNull StreamingAead streamingAead, @NonNull Context context) {
        this.f3965a = file;
        this.f3966b = streamingAead;
    }

    @NonNull
    public FileInputStream openFileInput() {
        if (this.f3965a.exists()) {
            FileInputStream fileInputStream = new FileInputStream(this.f3965a);
            return new EncryptedFileInputStream(fileInputStream.getFD(), this.f3966b.newDecryptingStream(fileInputStream, this.f3965a.getName().getBytes(StandardCharsets.UTF_8)));
        }
        throw new IOException("file doesn't exist: " + this.f3965a.getName());
    }

    @NonNull
    public FileOutputStream openFileOutput() {
        if (!this.f3965a.exists()) {
            FileOutputStream fileOutputStream = new FileOutputStream(this.f3965a);
            return new EncryptedFileOutputStream(fileOutputStream.getFD(), this.f3966b.newEncryptingStream(fileOutputStream, this.f3965a.getName().getBytes(StandardCharsets.UTF_8)));
        }
        throw new IOException("output file already exists, please use a new file: " + this.f3965a.getName());
    }
}
