package pl.droidsonroids.relinker.elf;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import pl.droidsonroids.relinker.elf.Elf;
/* loaded from: classes4.dex */
public class Program32Header extends Elf.ProgramHeader {
    public Program32Header(ElfParser elfParser, Elf.Header header, long j2) {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(header.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j3 = header.phoff + (j2 * header.phentsize);
        this.type = elfParser.f(allocate, j3);
        this.offset = elfParser.f(allocate, 4 + j3);
        this.vaddr = elfParser.f(allocate, 8 + j3);
        this.memsz = elfParser.f(allocate, j3 + 20);
    }
}
