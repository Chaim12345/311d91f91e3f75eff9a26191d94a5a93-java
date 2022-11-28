package org.apache.commons.cli;
/* loaded from: classes3.dex */
public interface CommandLineParser {
    CommandLine parse(Options options, String[] strArr);

    CommandLine parse(Options options, String[] strArr, boolean z);
}
