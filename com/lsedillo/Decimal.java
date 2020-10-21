package com.lsedillo;

import java.util.ArrayList;

public class Decimal {
    private long value;

    public Decimal(long value) {
        this.value = value;
    }

    public Decimal(String valueString) {
        this.value = Long.parseLong(valueString);
    }

    public long getValue() {
        return value;
    }

    public Hexadecimal toHexadecimal() {
        ArrayList<Byte> digits = new ArrayList<>(16);
        long q = 0;
        byte r = 0;
        long whatsLeft = value;
        do {
            q = whatsLeft / 16;
            r = (byte) (whatsLeft % 16);
            digits.add(r);
            whatsLeft = q;
        } while (whatsLeft != 0);
        Hexadecimal h = new Hexadecimal(digits);
        return h;
    }

    public Binary toBinary() {
        ArrayList<Boolean> digits = new ArrayList<>(16);
        long q = 0;
        int r = 0;
        long whatsLeft = value;
        do {
            q = whatsLeft / 2;
            r = (int) whatsLeft % 2;
            digits.add(r == 1);
            whatsLeft = q;
        } while (whatsLeft != 0);
        Binary b = new Binary(digits);
        return b;
    }

    public String toString() {
        return "" + value;
    }
}
