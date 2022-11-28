package pl.droidsonroids.relinker.elf;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import pl.droidsonroids.relinker.elf.Elf;
/* loaded from: classes4.dex */
public class Elf64Header extends Elf.Header {
    private final ElfParser parser;

    public Elf64Header(boolean z, ElfParser elfParser) {
        this.bigEndian = z;
        this.parser = elfParser;
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.type = elfParser.c(allocate, 16L);
        this.phoff = elfParser.d(allocate, 32L);
        this.shoff = elfParser.d(allocate, 40L);
        this.phentsize = elfParser.c(allocate, 54L);
        this.phnum = elfParser.c(allocate, 56L);
        this.shentsize = elfParser.c(allocate, 58L);
        this.shnum = elfParser.c(allocate, 60L);
        this.shstrndx = elfParser.c(allocate, 62L);
    }

    @Override // pl.droidsonroids.relinker.elf.Elf.Header
    public Elf.DynamicStructure getDynamicStructure(long j2, int i2) {
        return new Dynamic64Structure(this.parser, this, j2, i2);
    }

    @Override // pl.droidsonroids.relinker.elf.Elf.Header
    public Elf.ProgramHeader getProgramHeader(long j2) {
        return new Program64Header(this.parser, this, j2);
    }

    @Override // pl.droidsonroids.relinker.elf.Elf.Header
    public Elf.SectionHeader getSectionHeader(int i2) {
        return new Section64Header(this.parser, this, i2);
    }
}
