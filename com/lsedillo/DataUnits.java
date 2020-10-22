package com.lsedillo;

/**
 * This enum stores all the data types, their sizes in terms of bits, and their abbreviated names.
 * It also holds the conversion method.
 */
public enum DataUnits {
    BITS(1, "bit"),
    KBITS(1000, "Kbit"),
    MBITS(Math.pow(10, 6), "Mbit"),
    GBITS(Math.pow(10, 9), "Gbit"),
    TBITS(Math.pow(10, 12), "Tbit"),
    BYTES(8, "Byte"),
    KILOBYTES(8*Math.pow(10,3), "KB"),
    MEGABYTES(8*Math.pow(10,6), "MB"),
    GIGABYTES(8*Math.pow(10,9), "GB"),
    TERABYTES(8*Math.pow(10,12), "TB");

    private final double numOfBits;
    public final String name;

    DataUnits(double numOfBits, String name) {
        this.numOfBits = numOfBits;
        this.name = name;
    }

    /**
     * Converts between units by comparing their equivalent sizes in bits
     * @param num The number to convert from
     * @param from The unit to convert from
     * @param to The unit to convert to
     * @return The converted number.
     */
    public static double convert(double num, DataUnits from, DataUnits to) {
        return (num * from.numOfBits) / to.numOfBits;
    }

//    public static void main(String[] args) {
//        System.out.println(DataUnits.convert(1, DataUnits.BITS, DataUnits.KBITS));
//    }
}

