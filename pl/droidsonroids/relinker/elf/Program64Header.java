package pl.droidsonroids.relinker.elf;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import pl.droidsonroids.relinker.elf.Elf;
/* loaded from: classes4.dex */
public class Program64Header extends Elf.ProgramHeader {
    public Program64Header(ElfParser elfParser, Elf.Header header, long j2) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(header.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j3 = header.phoff + (j2 * header.phentsize);
        this.type = elfParser.f(allocate, j3);
        this.offset = elfParser.d(allocate, 8 + j3);
        this.vaddr = elfParser.d(allocate, 16 + j3);
        this.memsz = elfParser.d(allocate, j3 + 40);
    }
}
