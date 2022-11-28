package com.chuckerteam.chucker.internal.support;

import java.io.File;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bà\u0080\u0001\u0018\u00002\u00020\u0001J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&¨\u0006\u0004"}, d2 = {"Lcom/chuckerteam/chucker/internal/support/CacheDirectoryProvider;", "", "Ljava/io/File;", "provide", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public interface CacheDirectoryProvider {
    @Nullable
    File provide();
}
