package uk.co.ivandimitrov.subject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.TickType;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.Sensors;
import oshi.util.FormatUtil;
import oshi.util.Util;
import uk.co.ivandimitrov.OshiObserver;

/**
 * This is a class I got from here:
 * https://github.com/oshi/oshi/blob/master/oshi-core/src/test/java/oshi/SystemInfoTest.java
 * <p>
 * I've implemented the Singleton pattern so that the methods here won't be
 * called more times than needed.
 */
public final class SystemInfoSubject {

    private static final List<String> oshi = new ArrayList<>();
    private static final List<OshiObserver> OBSERVERS = new ArrayList<>();
    private static final SystemInfoSubject instance = new SystemInfoSubject();

    private static final SystemInfo systemInfo = new SystemInfo();
    private static final HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
    private static boolean hasStarted = false;

    private SystemInfoSubject() {
    }

    public static SystemInfoSubject getInstance() {
        return instance;
    }

    public void addObserver(OshiObserver observer) {
        OBSERVERS.add(observer);
    }

    public void removeObserver(OshiObserver observer) {
        OBSERVERS.remove(observer);
    }

    private void notifyObservers() {
        for (OshiObserver obs : OBSERVERS) {
            obs.update(oshi);
        }
    }

    public void start() {
        if (!hasStarted)
            synchronized (this) {
                new Thread(() -> {
                    hasStarted = true;
                    while (true)
                        updateData();
                }).start();
            }
    }

    private synchronized void updateData() {
        List<String> temp = new ArrayList<>();
        getCpuInfo(hardwareAbstractionLayer.getProcessor(), temp);
        getSensorsInfo(hardwareAbstractionLayer.getSensors(), temp);
        oshi.clear();
        oshi.addAll(temp);
        notifyObservers();
    }

    private void getCpuInfo(CentralProcessor processor, List<String> oshi) {
        oshi.add("Context Switches/Interrupts: " + processor.getContextSwitches() + " / " + processor.getInterrupts());

        long[] prevTicks = processor.getSystemCpuLoadTicks();
        long[][] prevProcTicks = processor.getProcessorCpuLoadTicks();
        oshi.add("CPU, IOWait, and IRQ ticks @ 0 sec:" + Arrays.toString(prevTicks));
        // Wait a second...
        Util.sleep(1000);
        long[] ticks = processor.getSystemCpuLoadTicks();
        oshi.add("CPU, IOWait, and IRQ ticks @ 1 sec:" + Arrays.toString(ticks));
        long user = ticks[TickType.USER.getIndex()] - prevTicks[TickType.USER.getIndex()];
        long nice = ticks[TickType.NICE.getIndex()] - prevTicks[TickType.NICE.getIndex()];
        long sys = ticks[TickType.SYSTEM.getIndex()] - prevTicks[TickType.SYSTEM.getIndex()];
        long idle = ticks[TickType.IDLE.getIndex()] - prevTicks[TickType.IDLE.getIndex()];
        long iowait = ticks[TickType.IOWAIT.getIndex()] - prevTicks[TickType.IOWAIT.getIndex()];
        long irq = ticks[TickType.IRQ.getIndex()] - prevTicks[TickType.IRQ.getIndex()];
        long softirq = ticks[TickType.SOFTIRQ.getIndex()] - prevTicks[TickType.SOFTIRQ.getIndex()];
        long steal = ticks[TickType.STEAL.getIndex()] - prevTicks[TickType.STEAL.getIndex()];
        long totalCpu = user + nice + sys + idle + iowait + irq + softirq + steal;

        oshi.add(String.format(
                "User: %.1f%% Nice: %.1f%% System: %.1f%% Idle: %.1f%% IOwait: %.1f%% IRQ: %.1f%% SoftIRQ: %.1f%% Steal: %.1f%%",
                100d * user / totalCpu, 100d * nice / totalCpu, 100d * sys / totalCpu, 100d * idle / totalCpu,
                100d * iowait / totalCpu, 100d * irq / totalCpu, 100d * softirq / totalCpu, 100d * steal / totalCpu));
        oshi.add(String.format("CPU load: %.1f%%", processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100));
        double[] loadAverage = processor.getSystemLoadAverage(3);
        oshi.add("CPU load averages:" + (loadAverage[0] < 0 ? " N/A" : String.format(" %.2f", loadAverage[0]))
                + (loadAverage[1] < 0 ? " N/A" : String.format(" %.2f", loadAverage[1]))
                + (loadAverage[2] < 0 ? " N/A" : String.format(" %.2f", loadAverage[2])));
        // per core CPU
        StringBuilder procCpu = new StringBuilder("CPU load per processor:");
        double[] load = processor.getProcessorCpuLoadBetweenTicks(prevProcTicks);
        for (double avg : load) {
            procCpu.append(String.format(" %.1f%%", avg * 100));
        }
        oshi.add(procCpu.toString());
        long freq = processor.getProcessorIdentifier().getVendorFreq();
        if (freq > 0) {
            oshi.add("Vendor Frequency: " + FormatUtil.formatHertz(freq));
        }
        freq = processor.getMaxFreq();
        if (freq > 0) {
            oshi.add("Max Frequency: " + FormatUtil.formatHertz(freq));
        }
        long[] freqs = processor.getCurrentFreq();
        if (freqs[0] > 0) {
            StringBuilder sb = new StringBuilder("Current Frequencies: ");
            for (int i = 0; i < freqs.length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(FormatUtil.formatHertz(freqs[i]));
            }
            oshi.add(sb.toString());
        }
    }

    private void getSensorsInfo(Sensors sensors, List<String> oshi) {
        oshi.add("Sensors: " + sensors.toString());
    }

}