package com.lsedillo;

import java.math.BigInteger;
import java.util.Random;

public class Testing {
    public static void main(String[] args) {
        testCalculate();
    }

    /**
     * This method tests all the possible commands by calculating two different ways:
     * 1) Passing the maximum and random values in to the <code>ParseCommand</code>
     *    class.
     * 2) Passing these same values into Java's built-in conversion functions and doing the rest of
     *    the math in this testing class
     * There are many sections, but each one follows a pattern:
     * I) Declare variables holding the binary and/or hex values with which to test
     * II) Use Java's built-in conversion methods to convert the hex and binary numbers to decimal
     * III) Assert that passing in the command as normal yields the same result as completing the
     *      operation in this file with Java's built-in conversion methods.
     *  If an assertion fails, it yields an error message.
     *
     */
    private static void testCalculate() {

        String binaryMaxString = "1111111111111111";
        long maxBinaryToLong = Integer.parseInt(binaryMaxString, 2);
        String randomBinaryString = Integer.toBinaryString((new Random()).nextInt(65535));
        long randomBinaryToLong = Integer.parseInt(randomBinaryString, 2);
        assert ParseCommand.
                chooseMethod("Calculate Binary + " + binaryMaxString + " " + randomBinaryString)
                .equals(Long.toBinaryString(maxBinaryToLong + randomBinaryToLong))
                : "CB+ Failed";
        assert ParseCommand.
                chooseMethod("Calculate Binary - " + binaryMaxString + " " + randomBinaryString)
                .equals(Long.toBinaryString(maxBinaryToLong - randomBinaryToLong))
                : "CB- Failed";

        assert ParseCommand.
                chooseMethod("Calculate Binary * " + binaryMaxString + " " + randomBinaryString)
                .equals(Long.toBinaryString(maxBinaryToLong * randomBinaryToLong))
                : "CB* Failed for number " + randomBinaryString;
        assert ParseCommand.
                chooseMethod("Calculate Binary / " + binaryMaxString + " " + randomBinaryString)
                .equals(Long.toBinaryString(maxBinaryToLong / randomBinaryToLong)
                        + " Remainder: " + Long.toBinaryString(maxBinaryToLong % randomBinaryToLong))
                : " CB / Failed";
        //Hexadecimal operations
        String hexMaxString = "FFFF";
        long maxHexToLong = Integer.parseInt(hexMaxString, 16);
        System.out.println(maxHexToLong);
        String randomHexString = "FFFF";//Integer.toHexString((new Random()).nextInt(65535));
        System.out.println(randomHexString);
        long randomHexToLong = Integer.parseInt(randomHexString, 16);
        System.out.println(randomHexToLong);
        System.out.println();
        assert ParseCommand.
                chooseMethod("Calculate Hexadecimal + " + hexMaxString + " " + randomHexString)
                .equals(Long.toHexString(maxHexToLong + randomHexToLong))
                : "CH+ Failed";
        assert ParseCommand.
                chooseMethod("Calculate Hexadecimal - " + hexMaxString + " " + randomHexString)
                .equals(Long.toHexString(maxHexToLong - randomHexToLong))
                : "CH- Failed";

        System.out.println(Long.toHexString(maxHexToLong * randomHexToLong));
        System.out.println("Testing multiply: " + maxHexToLong * randomHexToLong);
        System.out.println("Built-in multiply: " + ParseCommand.
                chooseMethod("Calculate Hexadecimal * " + hexMaxString + " " + randomHexString));
        assert ParseCommand.
                chooseMethod("Calculate Hexadecimal * " + hexMaxString + " " + randomHexString)
                .equals(Long.toHexString(maxHexToLong * randomHexToLong))
                : "CH* Failed for number " + randomHexString;
        assert ParseCommand.
                chooseMethod("Calculate Hexadecimal / " + hexMaxString + " " + randomHexString)
                .equals(Long.toHexString(maxHexToLong / randomHexToLong)
                        + " Remainder: " + Long.toHexString(maxHexToLong % randomHexToLong))
                : " CH / Failed";
    }
}
