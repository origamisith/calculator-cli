package com.lsedillo;

import java.util.ArrayList;

public class Binary {
    private final ArrayList<Boolean> digits;
    private final int length;

    public Binary(String digitString) {
        length = digitString.length();
        digits = new ArrayList<>(length);
        this.makeDigits(digitString);
    }

   public Binary(ArrayList<Boolean> digits) {
        this.digits = digits;
        this.length = digits.size();
   }

   public Decimal toDecimal() {
        long result = 0;
        for(int i = 0; i < length; i++ ) {
           result += Math.pow(2,i) * (digits.get(i) ? 1: 0);
        }
        return new Decimal(result);
    }


    private void makeDigits(String digitString) {
        for (int i = length - 1; i >= 0; i--) digits.add(digitString.charAt(i) != '0');
    }

    public String toString() {
        char[] chars = new char[length];
        for (int i = 0; i < length; i++) chars[i] = (digits.get(length - i - 1) ? '1' : '0');
        return new String(chars);
    }

    public static void main(String[] args) {
        Binary bin = new Binary("1101100110101010101111010101");
        Decimal dec = new com.lsedillo.Decimal (1415);
        System.out.println(bin);
        System.out.println(bin.toDecimal());
        System.out.println(dec.toBinary());
    }
}