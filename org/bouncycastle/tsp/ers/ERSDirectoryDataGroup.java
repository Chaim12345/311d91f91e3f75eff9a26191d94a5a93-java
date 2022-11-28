package org.bouncycastle.tsp.ers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes4.dex */
public class ERSDirectoryDataGroup extends ERSDataGroup {
    public ERSDirectoryDataGroup(File file) {
        super(buildGroup(file));
    }

    private static List<ERSData> buildGroup(File file) {
        ERSCachingData eRSFileData;
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            ArrayList arrayList = new ArrayList(listFiles.length);
            for (int i2 = 0; i2 != listFiles.length; i2++) {
                if (!listFiles[i2].isDirectory()) {
                    eRSFileData = new ERSFileData(listFiles[i2]);
                } else if (listFiles[i2].listFiles().length != 0) {
                    eRSFileData = new ERSDirectoryDataGroup(listFiles[i2]);
                }
                arrayList.add(eRSFileData);
            }
            return arrayList;
        }
        throw new IllegalArgumentException("file reference does not refer to directory");
    }

    public List<ERSFileData> getFiles() {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 != this.f15098a.size(); i2++) {
            if (this.f15098a.get(i2) instanceof ERSFileData) {
                arrayList.add((ERSFileData) this.f15098a.get(i2));
            }
        }
        return arrayList;
    }

    public List<ERSDirectoryDataGroup> getSubdirectories() {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 != this.f15098a.size(); i2++) {
            if (this.f15098a.get(i2) instanceof ERSDirectoryDataGroup) {
                arrayList.add((ERSDirectoryDataGroup) this.f15098a.get(i2));
            }
        }
        return arrayList;
    }
}
