/*package timetable;
import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
* This program demonstrates how to draw XY line chart with XYDataset
* using JFreechart library.
* @author www.codejava.net
*
*/
/*
public class XYLineChartExample extends JFrame {
	
	
public XYLineChartExample(ArrayList<Integer> best, ArrayList<Integer> worst) {
super("XY Line Chart Example with JFreechart");
JPanel chartPanel = createChartPanel(best, worst);
add(chartPanel, BorderLayout.CENTER);
setSize(640, 480);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLocationRelativeTo(null);

}
private JPanel createChartPanel(ArrayList<Integer> best, ArrayList<Integer> worst) {
String chartTitle = "Generations Chart";
String xAxisLabel = "Iterations";
String yAxisLabel = "Fitness Value";
XYDataset dataset = createDataset(best, worst);
JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
xAxisLabel, yAxisLabel, dataset);
return new ChartPanel(chart);
}
private XYDataset createDataset(ArrayList<Integer> best, ArrayList<Integer> worst) {
XYSeriesCollection dataset = new XYSeriesCollection();

XYSeries series1 = new XYSeries("Best");

XYSeries series2 = new XYSeries("Worst");
XYSeries series3 = new XYSeries("Average");
double avg=0;
for(int i=0;i<best.size();i++)
{
	series1.add(i, best.get(i));	
	series2.add(i, worst.get(i));
	avg=((best.get(i)+worst.get(i))/2.0);
	series3.add(i, avg);
}

dataset.addSeries(series1);
dataset.addSeries(series2);
dataset.addSeries(series3);
return dataset;
}
public static void draw(ArrayList<Integer> best, ArrayList<Integer> worst) {
SwingUtilities.invokeLater(new Runnable() {
@Override
public void run() {
new XYLineChartExample(best,worst).setVisible(true);
}
});
}
}*/