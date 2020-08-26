package uk.co.ivandimitrov;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import uk.co.ivandimitrov.subject.SystemInfoSubject;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of an HTML page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
@JavaScript({ "https://www.gstatic.com/charts/loader.js" })
@Push(PushMode.MANUAL)
public class MyUI extends UI implements OshiObserver {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    final VerticalLayout layout = new VerticalLayout();
    final Label chartDiv = new Label("");
    final Label cpuLoad = new Label("");
    final Label dataLabel = new Label("");
    final SystemInfoSubject sot = SystemInfoSubject.getInstance();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        sot.addObserver(this);
        sot.start();

        cpuLoad.setContentMode(ContentMode.HTML);
        cpuLoad.setValue("<input type='number' id='cpu_load' value='0' style='visibility: hidden;'>");

        chartDiv.setContentMode(ContentMode.HTML);
        chartDiv.setValue("<div id='chart_div' style='width: 400px; height: 120px;'></div>");

        dataLabel.setContentMode(ContentMode.PREFORMATTED);

        // This is a Google JS code for generating gauges. Read more here:
        // https://developers.google.com/chart/interactive/docs/gallery/gauge
        Page.getCurrent().getJavaScript().execute("google.charts.load('current', {'packages':['gauge']});"
                + "google.charts.setOnLoadCallback(drawChart);" +

                "function drawChart() {" +

                "var data = google.visualization.arrayToDataTable([" + "['Label', 'Value']," + "['CPU', 0]" + "]);" +

                "var options = {" + "width: 400, height: 120," + "redFrom: 90, redTo: 100,"
                + "yellowFrom:75, yellowTo: 90," + "minorTicks: 5" + "};" +

                "var chart = new google.visualization.Gauge(document.getElementById('chart_div'));" +

                "chart.draw(data, options);" +

                "setInterval(function() {" + "data.setValue(0, 1, Number(document.getElementById('cpu_load').value));"
                + "chart.draw(data, options);" + "}, 1000);}");

        layout.addComponents(chartDiv, cpuLoad, dataLabel);
        setContent(layout);
    }

    @Override
    public void update(final List<String> oshi) {
        StringBuilder sb = new StringBuilder();
        for (String s : oshi) {
            sb.append(s + "\n");
        }
        dataLabel.setValue(sb.toString());
        cpuLoad.setValue(
                "<input type='number' id='cpu_load' value='" + extractData(oshi) + "' style='visibility: hidden;'>");
        if (this.isAttached())
            this.push();
        else
            sot.removeObserver(this);
    }

    /**
     * Extracts the CPU load parameter and converts it into int from the OSHI cpu
     * load data.
     * 
     * @param data The OSHI data you get from calling SystemInfoTest.getOshi();
     * @return int representation of the machine's CPU load this code is running on.
     */
    private static int extractData(List<String> data) {
        String result = "";
        for (String s : data) {
            if (s.contains("CPU load:")) {
                result = s.replaceAll("\\D+", "");
            }
        }
        return result.length() > 0 ? Integer.parseInt(result.substring(0, result.length() - 1)) : 0;
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {

        /**
         *
         */
        private static final long serialVersionUID = 1L;
    }

}
