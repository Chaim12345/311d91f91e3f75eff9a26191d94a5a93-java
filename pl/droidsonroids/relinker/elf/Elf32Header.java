package pl.droidsonroids.relinker.elf;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import pl.droidsonroids.relinker.elf.Elf;
/* loaded from: classes4.dex */
public class Elf32Header extends Elf.Header {
    private final ElfParser parser;

    public Elf32Header(boolean z, ElfParser elfParser) {
        this.bigEndian = z;
        this.parser = elfParser;
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.type = elfParser.c(allocate, 16L);
        this.phoff = elfParser.f(allocate, 28L);
        this.shoff = elfParser.f(allocate, 32L);
        this.phentsize = elfParser.c(allocate, 42L);
        this.phnum = elfParser.c(allocate, 44L);
        this.shentsize = elfParser.c(allocate, 46L);
        this.shnum = elfParser.c(allocate, 48L);
        this.shstrndx = elfParser.c(allocate, 50L);
    }

    @Override // pl.droidsonroids.relinker.elf.Elf.Header
    public Elf.DynamicStructure getDynamicStructure(long j2, int i2) {
        return new Dynamic32Structure(this.parser, this, j2, i2);
    }

    @Override // pl.droidsonroids.relinker.elf.Elf.Header
    public Elf.ProgramHeader getProgramHeader(long j2) {
        return new Program32Header(this.parser, this, j2);
    }

    @Override // pl.droidsonroids.relinker.elf.Elf.Header
    public Elf.SectionHeader getSectionHeader(int i2) {
        return new Section32Header(this.parser, this, i2);
    }
}
