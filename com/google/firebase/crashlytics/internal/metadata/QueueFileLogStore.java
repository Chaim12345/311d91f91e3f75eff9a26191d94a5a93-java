package com.google.firebase.crashlytics.internal.metadata;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.metadata.QueueFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Locale;
/* loaded from: classes2.dex */
class QueueFileLogStore implements FileLogStore {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private QueueFile logFile;
    private final int maxLogSize;
    private final File workingFile;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class LogBytes {
        public final byte[] bytes;
        public final int offset;

        LogBytes(byte[] bArr, int i2) {
            this.bytes = bArr;
            this.offset = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public QueueFileLogStore(File file, int i2) {
        this.workingFile = file;
        this.maxLogSize = i2;
    }

    private void doWriteToLog(long j2, String str) {
        int i2;
        if (this.logFile == null) {
            return;
        }
        if (str == null) {
            str = "null";
        }
        try {
            if (str.length() > this.maxLogSize / 4) {
                str = "..." + str.substring(str.length() - i2);
            }
            this.logFile.add(String.format(Locale.US, "%d %s%n", Long.valueOf(j2), str.replaceAll("\r", " ").replaceAll("\n", " ")).getBytes(UTF_8));
            while (!this.logFile.isEmpty() && this.logFile.usedBytes() > this.maxLogSize) {
                this.logFile.remove();
            }
        } catch (IOException e2) {
            Logger.getLogger().e("There was a problem writing to the Crashlytics log.", e2);
        }
    }

    private LogBytes getLogBytes() {
        if (this.workingFile.exists()) {
            openLogFile();
            QueueFile queueFile = this.logFile;
            if (queueFile == null) {
                return null;
            }
            final int[] iArr = {0};
            final byte[] bArr = new byte[queueFile.usedBytes()];
            try {
                this.logFile.forEach(new QueueFile.ElementReader(this) { // from class: com.google.firebase.crashlytics.internal.metadata.QueueFileLogStore.1
                    @Override // com.google.firebase.crashlytics.internal.metadata.QueueFile.ElementReader
                    public void read(InputStream inputStream, int i2) {
                        try {
                            inputStream.read(bArr, iArr[0], i2);
                            int[] iArr2 = iArr;
                            iArr2[0] = iArr2[0] + i2;
                        } finally {
                            inputStream.close();
                        }
                    }
                });
            } catch (IOException e2) {
                Logger.getLogger().e("A problem occurred while reading the Crashlytics log file.", e2);
            }
            return new LogBytes(bArr, iArr[0]);
        }
        return null;
    }

    private void openLogFile() {
        if (this.logFile == null) {
            try {
                this.logFile = new QueueFile(this.workingFile);
            } catch (IOException e2) {
                Logger logger = Logger.getLogger();
                logger.e("Could not open log file: " + this.workingFile, e2);
            }
        }
    }

    @Override // com.google.firebase.crashlytics.internal.metadata.FileLogStore
    public void closeLogFile() {
        CommonUtils.closeOrLog(this.logFile, "There was a problem closing the Crashlytics log file.");
        this.logFile = null;
    }

    @Override // com.google.firebase.crashlytics.internal.metadata.FileLogStore
    public void deleteLogFile() {
        closeLogFile();
        this.workingFile.delete();
    }

    @Override // com.google.firebase.crashlytics.internal.metadata.FileLogStore
    public byte[] getLogAsBytes() {
        LogBytes logBytes = getLogBytes();
        if (logBytes == null) {
            return null;
        }
        int i2 = logBytes.offset;
        byte[] bArr = new byte[i2];
        System.arraycopy(logBytes.bytes, 0, bArr, 0, i2);
        return bArr;
    }

    @Override // com.google.firebase.crashlytics.internal.metadata.FileLogStore
    public String getLogAsString() {
        byte[] logAsBytes = getLogAsBytes();
        if (logAsBytes != null) {
            return new String(logAsBytes, UTF_8);
        }
        return null;
    }

    @Override // com.google.firebase.crashlytics.internal.metadata.FileLogStore
    public void writeToLog(long j2, String str) {
        openLogFile();
        doWriteToLog(j2, str);
    }
}
