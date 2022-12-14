package com.github.mikephil.charting.data;

import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.renderer.scatter.ChevronDownShapeRenderer;
import com.github.mikephil.charting.renderer.scatter.ChevronUpShapeRenderer;
import com.github.mikephil.charting.renderer.scatter.CircleShapeRenderer;
import com.github.mikephil.charting.renderer.scatter.CrossShapeRenderer;
import com.github.mikephil.charting.renderer.scatter.IShapeRenderer;
import com.github.mikephil.charting.renderer.scatter.SquareShapeRenderer;
import com.github.mikephil.charting.renderer.scatter.TriangleShapeRenderer;
import com.github.mikephil.charting.renderer.scatter.XShapeRenderer;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class ScatterDataSet extends LineScatterCandleRadarDataSet<Entry> implements IScatterDataSet {
    private int mScatterShapeHoleColor;
    private float mScatterShapeHoleRadius;
    private float mShapeSize;
    protected IShapeRenderer x;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.github.mikephil.charting.data.ScatterDataSet$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f5344a;

        static {
            int[] iArr = new int[ScatterChart.ScatterShape.values().length];
            f5344a = iArr;
            try {
                iArr[ScatterChart.ScatterShape.SQUARE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5344a[ScatterChart.ScatterShape.CIRCLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f5344a[ScatterChart.ScatterShape.TRIANGLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f5344a[ScatterChart.ScatterShape.CROSS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f5344a[ScatterChart.ScatterShape.X.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f5344a[ScatterChart.ScatterShape.CHEVRON_UP.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f5344a[ScatterChart.ScatterShape.CHEVRON_DOWN.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public ScatterDataSet(List<Entry> list, String str) {
        super(list, str);
        this.mShapeSize = 15.0f;
        this.x = new SquareShapeRenderer();
        this.mScatterShapeHoleRadius = 0.0f;
        this.mScatterShapeHoleColor = ColorTemplate.COLOR_NONE;
    }

    public static IShapeRenderer getRendererForShape(ScatterChart.ScatterShape scatterShape) {
        switch (AnonymousClass1.f5344a[scatterShape.ordinal()]) {
            case 1:
                return new SquareShapeRenderer();
            case 2:
                return new CircleShapeRenderer();
            case 3:
                return new TriangleShapeRenderer();
            case 4:
                return new CrossShapeRenderer();
            case 5:
                return new XShapeRenderer();
            case 6:
                return new ChevronUpShapeRenderer();
            case 7:
                return new ChevronDownShapeRenderer();
            default:
                return null;
        }
    }

    @Override // com.github.mikephil.charting.data.DataSet
    public DataSet<Entry> copy() {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.f5338n.size(); i2++) {
            arrayList.add(((Entry) this.f5338n.get(i2)).copy());
        }
        ScatterDataSet scatterDataSet = new ScatterDataSet(arrayList, getLabel());
        h(scatterDataSet);
        return scatterDataSet;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IScatterDataSet
    public int getScatterShapeHoleColor() {
        return this.mScatterShapeHoleColor;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IScatterDataSet
    public float getScatterShapeHoleRadius() {
        return this.mScatterShapeHoleRadius;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IScatterDataSet
    public float getScatterShapeSize() {
        return this.mShapeSize;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IScatterDataSet
    public IShapeRenderer getShapeRenderer() {
        return this.x;
    }

    protected void h(ScatterDataSet scatterDataSet) {
        super.g(scatterDataSet);
        scatterDataSet.mShapeSize = this.mShapeSize;
        scatterDataSet.x = this.x;
        scatterDataSet.mScatterShapeHoleRadius = this.mScatterShapeHoleRadius;
        scatterDataSet.mScatterShapeHoleColor = this.mScatterShapeHoleColor;
    }

    public void setScatterShape(ScatterChart.ScatterShape scatterShape) {
        this.x = getRendererForShape(scatterShape);
    }

    public void setScatterShapeHoleColor(int i2) {
        this.mScatterShapeHoleColor = i2;
    }

    public void setScatterShapeHoleRadius(float f2) {
        this.mScatterShapeHoleRadius = f2;
    }

    public void setScatterShapeSize(float f2) {
        this.mShapeSize = f2;
    }

    public void setShapeRenderer(IShapeRenderer iShapeRenderer) {
        this.x = iShapeRenderer;
    }
}
