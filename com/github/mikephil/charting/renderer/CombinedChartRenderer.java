package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class CombinedChartRenderer extends DataRenderer {

    /* renamed from: f  reason: collision with root package name */
    protected List f5396f;

    /* renamed from: g  reason: collision with root package name */
    protected WeakReference f5397g;

    /* renamed from: h  reason: collision with root package name */
    protected List f5398h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.github.mikephil.charting.renderer.CombinedChartRenderer$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f5399a;

        static {
            int[] iArr = new int[CombinedChart.DrawOrder.values().length];
            f5399a = iArr;
            try {
                iArr[CombinedChart.DrawOrder.BAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5399a[CombinedChart.DrawOrder.BUBBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f5399a[CombinedChart.DrawOrder.LINE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f5399a[CombinedChart.DrawOrder.CANDLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f5399a[CombinedChart.DrawOrder.SCATTER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public CombinedChartRenderer(CombinedChart combinedChart, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.f5396f = new ArrayList(5);
        this.f5398h = new ArrayList();
        this.f5397g = new WeakReference(combinedChart);
        createRenderers();
    }

    public void createRenderers() {
        List list;
        DataRenderer barChartRenderer;
        this.f5396f.clear();
        CombinedChart combinedChart = (CombinedChart) this.f5397g.get();
        if (combinedChart == null) {
            return;
        }
        for (CombinedChart.DrawOrder drawOrder : combinedChart.getDrawOrder()) {
            int i2 = AnonymousClass1.f5399a[drawOrder.ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 != 4) {
                            if (i2 == 5 && combinedChart.getScatterData() != null) {
                                list = this.f5396f;
                                barChartRenderer = new ScatterChartRenderer(combinedChart, this.f5400b, this.f5436a);
                                list.add(barChartRenderer);
                            }
                        } else if (combinedChart.getCandleData() != null) {
                            list = this.f5396f;
                            barChartRenderer = new CandleStickChartRenderer(combinedChart, this.f5400b, this.f5436a);
                            list.add(barChartRenderer);
                        }
                    } else if (combinedChart.getLineData() != null) {
                        list = this.f5396f;
                        barChartRenderer = new LineChartRenderer(combinedChart, this.f5400b, this.f5436a);
                        list.add(barChartRenderer);
                    }
                } else if (combinedChart.getBubbleData() != null) {
                    list = this.f5396f;
                    barChartRenderer = new BubbleChartRenderer(combinedChart, this.f5400b, this.f5436a);
                    list.add(barChartRenderer);
                }
            } else if (combinedChart.getBarData() != null) {
                list = this.f5396f;
                barChartRenderer = new BarChartRenderer(combinedChart, this.f5400b, this.f5436a);
                list.add(barChartRenderer);
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas canvas) {
        for (DataRenderer dataRenderer : this.f5396f) {
            dataRenderer.drawData(canvas);
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas canvas) {
        for (DataRenderer dataRenderer : this.f5396f) {
            dataRenderer.drawExtras(canvas);
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        Chart chart = (Chart) this.f5397g.get();
        if (chart == null) {
            return;
        }
        for (DataRenderer dataRenderer : this.f5396f) {
            Object obj = null;
            if (dataRenderer instanceof BarChartRenderer) {
                obj = ((BarChartRenderer) dataRenderer).f5387g.getBarData();
            } else if (dataRenderer instanceof LineChartRenderer) {
                obj = ((LineChartRenderer) dataRenderer).f5413g.getLineData();
            } else if (dataRenderer instanceof CandleStickChartRenderer) {
                obj = ((CandleStickChartRenderer) dataRenderer).f5395g.getCandleData();
            } else if (dataRenderer instanceof ScatterChartRenderer) {
                obj = ((ScatterChartRenderer) dataRenderer).f5437g.getScatterData();
            } else if (dataRenderer instanceof BubbleChartRenderer) {
                obj = ((BubbleChartRenderer) dataRenderer).f5394g.getBubbleData();
            }
            int indexOf = obj == null ? -1 : ((CombinedData) chart.getData()).getAllData().indexOf(obj);
            this.f5398h.clear();
            for (Highlight highlight : highlightArr) {
                if (highlight.getDataIndex() == indexOf || highlight.getDataIndex() == -1) {
                    this.f5398h.add(highlight);
                }
            }
            List list = this.f5398h;
            dataRenderer.drawHighlighted(canvas, (Highlight[]) list.toArray(new Highlight[list.size()]));
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas canvas) {
        for (DataRenderer dataRenderer : this.f5396f) {
            dataRenderer.drawValues(canvas);
        }
    }

    public DataRenderer getSubRenderer(int i2) {
        if (i2 >= this.f5396f.size() || i2 < 0) {
            return null;
        }
        return (DataRenderer) this.f5396f.get(i2);
    }

    public List<DataRenderer> getSubRenderers() {
        return this.f5396f;
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
        for (DataRenderer dataRenderer : this.f5396f) {
            dataRenderer.initBuffers();
        }
    }

    public void setSubRenderers(List<DataRenderer> list) {
        this.f5396f = list;
    }
}
