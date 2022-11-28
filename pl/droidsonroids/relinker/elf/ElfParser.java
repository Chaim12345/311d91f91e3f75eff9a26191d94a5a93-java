package pl.droidsonroids.relinker.elf;

import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.UShort;
import okhttp3.internal.ws.WebSocketProtocol;
import org.bouncycastle.asn1.cmc.BodyPartID;
import pl.droidsonroids.relinker.elf.Elf;
/* loaded from: classes4.dex */
public class ElfParser implements Closeable, Elf {
    private final int MAGIC = 1179403647;
    private final FileChannel channel;

    public ElfParser(File file) {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("File is null or does not exist");
        }
        this.channel = new FileInputStream(file).getChannel();
    }

    private long offsetFromVma(Elf.Header header, long j2, long j3) {
        for (long j4 = 0; j4 < j2; j4++) {
            Elf.ProgramHeader programHeader = header.getProgramHeader(j4);
            if (programHeader.type == 1) {
                long j5 = programHeader.vaddr;
                if (j5 <= j3 && j3 <= programHeader.memsz + j5) {
                    return (j3 - j5) + programHeader.offset;
                }
            }
        }
        throw new IllegalStateException("Could not map vma to file offset!");
    }

    protected void a(ByteBuffer byteBuffer, long j2, int i2) {
        byteBuffer.position(0);
        byteBuffer.limit(i2);
        long j3 = 0;
        while (j3 < i2) {
            int read = this.channel.read(byteBuffer, j2 + j3);
            if (read == -1) {
                throw new EOFException();
            }
            j3 += read;
        }
        byteBuffer.position(0);
    }

    protected short b(ByteBuffer byteBuffer, long j2) {
        a(byteBuffer, j2, 1);
        return (short) (byteBuffer.get() & 255);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int c(ByteBuffer byteBuffer, long j2) {
        a(byteBuffer, j2, 2);
        return byteBuffer.getShort() & UShort.MAX_VALUE;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.channel.close();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long d(ByteBuffer byteBuffer, long j2) {
        a(byteBuffer, j2, 8);
        return byteBuffer.getLong();
    }

    protected String e(ByteBuffer byteBuffer, long j2) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            long j3 = 1 + j2;
            short b2 = b(byteBuffer, j2);
            if (b2 == 0) {
                return sb.toString();
            }
            sb.append((char) b2);
            j2 = j3;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long f(ByteBuffer byteBuffer, long j2) {
        a(byteBuffer, j2, 4);
        return byteBuffer.getInt() & BodyPartID.bodyIdMax;
    }

    public Elf.Header parseHeader() {
        this.channel.position(0L);
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        if (f(allocate, 0L) == 1179403647) {
            short b2 = b(allocate, 4L);
            boolean z = b(allocate, 5L) == 2;
            if (b2 == 1) {
                return new Elf32Header(z, this);
            }
            if (b2 == 2) {
                return new Elf64Header(z, this);
            }
            throw new IllegalStateException("Invalid class type!");
        }
        throw new IllegalArgumentException("Invalid ELF Magic!");
    }

    public List<String> parseNeededDependencies() {
        long j2;
        this.channel.position(0L);
        ArrayList arrayList = new ArrayList();
        Elf.Header parseHeader = parseHeader();
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(parseHeader.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j3 = parseHeader.phnum;
        int i2 = 0;
        if (j3 == WebSocketProtocol.PAYLOAD_SHORT_MAX) {
            j3 = parseHeader.getSectionHeader(0).info;
        }
        long j4 = 0;
        while (true) {
            if (j4 >= j3) {
                j2 = 0;
                break;
            }
            Elf.ProgramHeader programHeader = parseHeader.getProgramHeader(j4);
            if (programHeader.type == 2) {
                j2 = programHeader.offset;
                break;
            }
            j4++;
        }
        if (j2 == 0) {
            return Collections.unmodifiableList(arrayList);
        }
        ArrayList<Long> arrayList2 = new ArrayList();
        long j5 = 0;
        while (true) {
            Elf.DynamicStructure dynamicStructure = parseHeader.getDynamicStructure(j2, i2);
            long j6 = dynamicStructure.tag;
            if (j6 == 1) {
                arrayList2.add(Long.valueOf(dynamicStructure.val));
            } else if (j6 == 5) {
                j5 = dynamicStructure.val;
            }
            i2++;
            if (dynamicStructure.tag == 0) {
                break;
            }
        }
        if (j5 != 0) {
            long offsetFromVma = offsetFromVma(parseHeader, j3, j5);
            for (Long l2 : arrayList2) {
                arrayList.add(e(allocate, l2.longValue() + offsetFromVma));
            }
            return arrayList;
        }
        throw new IllegalStateException("String table offset not found!");
    }
}
