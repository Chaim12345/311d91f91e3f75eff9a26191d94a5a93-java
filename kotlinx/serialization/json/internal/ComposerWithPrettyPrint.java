package kotlinx.serialization.json.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;
import org.apache.http.message.TokenParser;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class ComposerWithPrettyPrint extends Composer {
    @NotNull
    private final Json json;
    private int level;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposerWithPrettyPrint(@NotNull JsonStringBuilder sb, @NotNull Json json) {
        super(sb);
        Intrinsics.checkNotNullParameter(sb, "sb");
        Intrinsics.checkNotNullParameter(json, "json");
        this.json = json;
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void indent() {
        a(true);
        this.level++;
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void nextItem() {
        int i2 = 0;
        a(false);
        print("\n");
        int i3 = this.level;
        while (i2 < i3) {
            i2++;
            print(this.json.getConfiguration().getPrettyPrintIndent());
        }
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void space() {
        print(TokenParser.SP);
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void unIndent() {
        this.level--;
    }
}
