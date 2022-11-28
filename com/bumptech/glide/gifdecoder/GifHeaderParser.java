package com.bumptech.glide.gifdecoder;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
/* loaded from: classes.dex */
public class GifHeaderParser {
    private static final int DESCRIPTOR_MASK_INTERLACE_FLAG = 64;
    private static final int DESCRIPTOR_MASK_LCT_FLAG = 128;
    private static final int DESCRIPTOR_MASK_LCT_SIZE = 7;
    private static final int EXTENSION_INTRODUCER = 33;
    private static final int GCE_DISPOSAL_METHOD_SHIFT = 2;
    private static final int GCE_MASK_DISPOSAL_METHOD = 28;
    private static final int GCE_MASK_TRANSPARENT_COLOR_FLAG = 1;
    private static final int IMAGE_SEPARATOR = 44;
    private static final int LABEL_APPLICATION_EXTENSION = 255;
    private static final int LABEL_COMMENT_EXTENSION = 254;
    private static final int LABEL_GRAPHIC_CONTROL_EXTENSION = 249;
    private static final int LABEL_PLAIN_TEXT_EXTENSION = 1;
    private static final int LSD_MASK_GCT_FLAG = 128;
    private static final int LSD_MASK_GCT_SIZE = 7;
    private static final int MASK_INT_LOWEST_BYTE = 255;
    private static final int MAX_BLOCK_SIZE = 256;
    private static final String TAG = "GifHeaderParser";
    private static final int TRAILER = 59;
    private final byte[] block = new byte[256];
    private int blockSize = 0;
    private GifHeader header;
    private ByteBuffer rawData;

    private boolean err() {
        return this.header.f4693b != 0;
    }

    private int read() {
        try {
            return this.rawData.get() & 255;
        } catch (Exception unused) {
            this.header.f4693b = 1;
            return 0;
        }
    }

    private void readBitmap() {
        this.header.f4695d.f4681a = readShort();
        this.header.f4695d.f4682b = readShort();
        this.header.f4695d.f4683c = readShort();
        this.header.f4695d.f4684d = readShort();
        int read = read();
        boolean z = (read & 128) != 0;
        int pow = (int) Math.pow(2.0d, (read & 7) + 1);
        GifFrame gifFrame = this.header.f4695d;
        gifFrame.f4685e = (read & 64) != 0;
        if (z) {
            gifFrame.f4691k = readColorTable(pow);
        } else {
            gifFrame.f4691k = null;
        }
        this.header.f4695d.f4690j = this.rawData.position();
        skipImageData();
        if (err()) {
            return;
        }
        GifHeader gifHeader = this.header;
        gifHeader.f4694c++;
        gifHeader.f4696e.add(gifHeader.f4695d);
    }

    private void readBlock() {
        int read = read();
        this.blockSize = read;
        if (read <= 0) {
            return;
        }
        int i2 = 0;
        int i3 = 0;
        while (true) {
            try {
                i3 = this.blockSize;
                if (i2 >= i3) {
                    return;
                }
                i3 -= i2;
                this.rawData.get(this.block, i2, i3);
                i2 += i3;
            } catch (Exception unused) {
                if (Log.isLoggable(TAG, 3)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Error Reading Block n: ");
                    sb.append(i2);
                    sb.append(" count: ");
                    sb.append(i3);
                    sb.append(" blockSize: ");
                    sb.append(this.blockSize);
                }
                this.header.f4693b = 1;
                return;
            }
        }
    }

    @Nullable
    private int[] readColorTable(int i2) {
        byte[] bArr = new byte[i2 * 3];
        int[] iArr = null;
        try {
            this.rawData.get(bArr);
            iArr = new int[256];
            int i3 = 0;
            int i4 = 0;
            while (i3 < i2) {
                int i5 = i4 + 1;
                int i6 = i5 + 1;
                int i7 = i6 + 1;
                int i8 = i3 + 1;
                iArr[i3] = ((bArr[i4] & 255) << 16) | ViewCompat.MEASURED_STATE_MASK | ((bArr[i5] & 255) << 8) | (bArr[i6] & 255);
                i4 = i7;
                i3 = i8;
            }
        } catch (BufferUnderflowException unused) {
            Log.isLoggable(TAG, 3);
            this.header.f4693b = 1;
        }
        return iArr;
    }

    private void readContents() {
        readContents(Integer.MAX_VALUE);
    }

    private void readContents(int i2) {
        boolean z = false;
        while (!z && !err() && this.header.f4694c <= i2) {
            int read = read();
            if (read == 33) {
                int read2 = read();
                if (read2 != 1) {
                    if (read2 == LABEL_GRAPHIC_CONTROL_EXTENSION) {
                        this.header.f4695d = new GifFrame();
                        readGraphicControlExt();
                    } else if (read2 != LABEL_COMMENT_EXTENSION && read2 == 255) {
                        readBlock();
                        StringBuilder sb = new StringBuilder();
                        for (int i3 = 0; i3 < 11; i3++) {
                            sb.append((char) this.block[i3]);
                        }
                        if (sb.toString().equals("NETSCAPE2.0")) {
                            readNetscapeExt();
                        }
                    }
                }
                skip();
            } else if (read == 44) {
                GifHeader gifHeader = this.header;
                if (gifHeader.f4695d == null) {
                    gifHeader.f4695d = new GifFrame();
                }
                readBitmap();
            } else if (read != 59) {
                this.header.f4693b = 1;
            } else {
                z = true;
            }
        }
    }

    private void readGraphicControlExt() {
        read();
        int read = read();
        GifFrame gifFrame = this.header.f4695d;
        int i2 = (read & 28) >> 2;
        gifFrame.f4687g = i2;
        if (i2 == 0) {
            gifFrame.f4687g = 1;
        }
        gifFrame.f4686f = (read & 1) != 0;
        int readShort = readShort();
        if (readShort < 2) {
            readShort = 10;
        }
        GifFrame gifFrame2 = this.header.f4695d;
        gifFrame2.f4689i = readShort * 10;
        gifFrame2.f4688h = read();
        read();
    }

    private void readHeader() {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < 6; i2++) {
            sb.append((char) read());
        }
        if (!sb.toString().startsWith("GIF")) {
            this.header.f4693b = 1;
            return;
        }
        readLSD();
        if (!this.header.f4699h || err()) {
            return;
        }
        GifHeader gifHeader = this.header;
        gifHeader.f4692a = readColorTable(gifHeader.f4700i);
        GifHeader gifHeader2 = this.header;
        gifHeader2.f4703l = gifHeader2.f4692a[gifHeader2.f4701j];
    }

    private void readLSD() {
        this.header.f4697f = readShort();
        this.header.f4698g = readShort();
        int read = read();
        GifHeader gifHeader = this.header;
        gifHeader.f4699h = (read & 128) != 0;
        gifHeader.f4700i = (int) Math.pow(2.0d, (read & 7) + 1);
        this.header.f4701j = read();
        this.header.f4702k = read();
    }

    private void readNetscapeExt() {
        do {
            readBlock();
            byte[] bArr = this.block;
            if (bArr[0] == 1) {
                this.header.f4704m = ((bArr[2] & 255) << 8) | (bArr[1] & 255);
            }
            if (this.blockSize <= 0) {
                return;
            }
        } while (!err());
    }

    private int readShort() {
        return this.rawData.getShort();
    }

    private void reset() {
        this.rawData = null;
        Arrays.fill(this.block, (byte) 0);
        this.header = new GifHeader();
        this.blockSize = 0;
    }

    private void skip() {
        int read;
        do {
            read = read();
            this.rawData.position(Math.min(this.rawData.position() + read, this.rawData.limit()));
        } while (read > 0);
    }

    private void skipImageData() {
        read();
        skip();
    }

    public void clear() {
        this.rawData = null;
        this.header = null;
    }

    public boolean isAnimated() {
        readHeader();
        if (!err()) {
            readContents(2);
        }
        return this.header.f4694c > 1;
    }

    @NonNull
    public GifHeader parseHeader() {
        if (this.rawData != null) {
            if (err()) {
                return this.header;
            }
            readHeader();
            if (!err()) {
                readContents();
                GifHeader gifHeader = this.header;
                if (gifHeader.f4694c < 0) {
                    gifHeader.f4693b = 1;
                }
            }
            return this.header;
        }
        throw new IllegalStateException("You must call setData() before parseHeader()");
    }

    public GifHeaderParser setData(@NonNull ByteBuffer byteBuffer) {
        reset();
        ByteBuffer asReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        this.rawData = asReadOnlyBuffer;
        asReadOnlyBuffer.position(0);
        this.rawData.order(ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    public GifHeaderParser setData(@Nullable byte[] bArr) {
        if (bArr != null) {
            setData(ByteBuffer.wrap(bArr));
        } else {
            this.rawData = null;
            this.header.f4693b = 2;
        }
        return this;
    }
}
