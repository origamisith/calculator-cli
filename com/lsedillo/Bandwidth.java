package com.lsedillo;

public class Bandwidth {
    private DataUnits data;
    private TimeUnits time;

    public Bandwidth(DataUnits data, TimeUnits time) {
        this.data = data;
        this.time = time;
    }

    public static String downUpTime(double n1, DataUnits fileUnit, double n2, DataUnits bandwidthUnit) {
        double bits = DataUnits.convert(n1, fileUnit, DataUnits.BITS);
        double bits2 = DataUnits.convert(n2, bandwidthUnit, DataUnits.BITS);
        return TimeUnits.readableTime((bits) / bits2);
    }

    public static String webBandwidth(double views, TimeUnits time, double pageSize, DataUnits sizeUnit, double redundancy) {
        double seconds = TimeUnits.convert(time, TimeUnits.SECOND);
        double months = TimeUnits.convert(time, TimeUnits.MONTH);
        double megabits = DataUnits.convert(pageSize, sizeUnit, DataUnits.MBITS);
        double gigabytes = DataUnits.convert(pageSize, sizeUnit, DataUnits.GIGABYTES);
        String result = "Actual bandwidth need is " + (views * megabits / seconds) + " Mbits/s or "
                + (views * gigabytes / months) + "GB per month";
        result += "\nWith redundancy factor of 2, the bandwidth need is " + (views * megabits * redundancy) / seconds + "Mbits/s or" +
                (views * gigabytes * redundancy) / months + " GB per month";
        return result;
    }

    public static String hostBandwidth(double dataSize, DataUnits dataUnit, double bandSize, DataUnits bandwidthUnit) {
        if (bandSize == -1) {
            double newData = DataUnits.convert(dataSize, dataUnit, bandwidthUnit);
            double newTime = TimeUnits.convert(TimeUnits.MONTH, TimeUnits.SECOND);
            double result = newData / newTime;
            return dataSize + " " + dataUnit.name + " per month is equivalent to "
                    + result + " " + bandwidthUnit.name + "/s.";
        } else if (dataSize == -1) {
            double newData = DataUnits.convert(bandSize, bandwidthUnit, dataUnit);
            double newTime = TimeUnits.convert(TimeUnits.SECOND, TimeUnits.MONTH);
            double result = newData / newTime;
            return bandSize + " " + bandwidthUnit.name + "/s" + " is equivalent to " +
                    result + " " + dataUnit.name + " per month.";
        }
        return "Please specify either the monthly usage or the bandwidth as -1";
    }

    public DataUnits getData() {
        return data;
    }

    public TimeUnits getTime() {
        return time;
    }


    public static void main(String[] args) {
        System.out.println(downUpTime(500, DataUnits.MEGABYTES, 5, DataUnits.MBITS));
        System.out.println(webBandwidth(455, TimeUnits.HOUR, 10, DataUnits.MEGABYTES, 3));
        System.out.println(hostBandwidth(-1, DataUnits.MEGABYTES, 8, DataUnits.KBITS));

    }
}
