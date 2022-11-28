package com.github.mikephil.charting.utils;

import android.content.res.AssetManager;
import android.os.Environment;
import android.os.Parcelable;
import android.util.Log;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class FileUtils {
    private static final String LOG = "MPChart-FileUtils";

    public static List<BarEntry> loadBarEntriesFromAssets(AssetManager assetManager, String str) {
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader = null;
        try {
        } catch (IOException e2) {
            Log.e(LOG, e2.toString());
        }
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(assetManager.open(str), "UTF-8"));
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine == null) {
                            break;
                        }
                        String[] split = readLine.split("#");
                        arrayList.add(new BarEntry(Float.parseFloat(split[1]), Float.parseFloat(split[0])));
                    } catch (IOException e3) {
                        e = e3;
                        bufferedReader = bufferedReader2;
                        Log.e(LOG, e.toString());
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        return arrayList;
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e4) {
                                Log.e(LOG, e4.toString());
                            }
                        }
                        throw th;
                    }
                }
                bufferedReader2.close();
            } catch (IOException e5) {
                e = e5;
            }
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Type inference failed for: r2v11, types: [float[]] */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v14, types: [float] */
    public static List<Entry> loadEntriesFromAssets(AssetManager assetManager, String str) {
        ?? r2;
        Parcelable barEntry;
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader = null;
        try {
            try {
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(assetManager.open(str), "UTF-8"));
                    while (true) {
                        try {
                            String readLine = bufferedReader2.readLine();
                            if (readLine == null) {
                                break;
                            }
                            String[] split = readLine.split("#");
                            if (split.length <= 2) {
                                r2 = Float.parseFloat(split[1]);
                                barEntry = new Entry(r2, Float.parseFloat(split[0]));
                            } else {
                                int length = split.length - 1;
                                r2 = new float[length];
                                for (int i2 = 0; i2 < length; i2++) {
                                    r2[i2] = Float.parseFloat(split[i2]);
                                }
                                barEntry = new BarEntry(Integer.parseInt(split[split.length - 1]), (float[]) r2);
                            }
                            arrayList.add(barEntry);
                            bufferedReader = r2;
                        } catch (IOException e2) {
                            e = e2;
                            bufferedReader = bufferedReader2;
                            Log.e(LOG, e.toString());
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            return arrayList;
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader2;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e3) {
                                    Log.e(LOG, e3.toString());
                                }
                            }
                            throw th;
                        }
                    }
                    bufferedReader2.close();
                } catch (IOException e4) {
                    Log.e(LOG, e4.toString());
                }
            } catch (IOException e5) {
                e = e5;
            }
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static List<Entry> loadEntriesFromFile(String str) {
        Parcelable barEntry;
        File file = new File(Environment.getExternalStorageDirectory(), str);
        ArrayList arrayList = new ArrayList();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                String[] split = readLine.split("#");
                if (split.length <= 2) {
                    barEntry = new Entry(Float.parseFloat(split[0]), Integer.parseInt(split[1]));
                } else {
                    int length = split.length - 1;
                    float[] fArr = new float[length];
                    for (int i2 = 0; i2 < length; i2++) {
                        fArr[i2] = Float.parseFloat(split[i2]);
                    }
                    barEntry = new BarEntry(Integer.parseInt(split[split.length - 1]), fArr);
                }
                arrayList.add(barEntry);
            }
        } catch (IOException e2) {
            Log.e(LOG, e2.toString());
        }
        return arrayList;
    }

    public static void saveToSdCard(List<Entry> list, String str) {
        File file = new File(Environment.getExternalStorageDirectory(), str);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e2) {
                Log.e(LOG, e2.toString());
            }
        }
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            for (Entry entry : list) {
                bufferedWriter.append((CharSequence) (entry.getY() + "#" + entry.getX()));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e3) {
            Log.e(LOG, e3.toString());
        }
    }
}
