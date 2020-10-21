package com.lsedillo;

import java.math.BigInteger;
import java.util.Random;

public class Testing {
    public static void main(String[] args) {
        testCalculate();
    }

    private static void testCalculate() {

        String binaryMaxString = "1111111111111111";
        int maxBinaryToInt = Integer.parseInt(binaryMaxString, 2);
        String randomBinaryString = Integer.toBinaryString((new Random()).nextInt(65535));
        int randomBinaryToInt = Integer.parseInt(randomBinaryString, 2);
        assert ParseCommand.
                chooseMethod("Calculate Binary + " + binaryMaxString + " " + randomBinaryString)
                .equals(Integer.toBinaryString(maxBinaryToInt + randomBinaryToInt))
                : "CB+ Failed";
        assert ParseCommand.
                chooseMethod("Calculate Binary - " + binaryMaxString + " " + randomBinaryString)
                .equals(Integer.toBinaryString(maxBinaryToInt - randomBinaryToInt))
                : "CB- Failed";

        assert ParseCommand.
                chooseMethod("Calculate Binary * " + binaryMaxString + " " + randomBinaryString)
                .equals(Integer.toBinaryString(maxBinaryToInt * randomBinaryToInt))
                : "CB* Failed for number " + randomBinaryString;
        assert ParseCommand.
                chooseMethod("Calculate Binary / " + binaryMaxString + " " + randomBinaryString)
                .equals(Integer.toBinaryString(maxBinaryToInt / randomBinaryToInt)
                + " Remainder: " + Integer.toBinaryString(maxBinaryToInt % randomBinaryToInt))
                : " CB / Failed";
    }
}
