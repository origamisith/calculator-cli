package com.lsedillo;

import java.util.Arrays;

public class ParseCommand {

    public static String chooseMethod(String line) {
        String[] tokens = line.toLowerCase().split(" ");
        if (tokens[0].equals("calculate")) return calculate(Arrays.copyOfRange(tokens, 1, tokens.length));
        if (tokens[0].equals("convert")) return convert(Arrays.copyOfRange(tokens,1,tokens.length));
        else return "Invalid instruction.";
    }

    private static String calculate(String[] tokens) {
        return switch (tokens[0]) {
            case "binary" -> {
                Binary b1 = new Binary(tokens[2]);
                Binary b2 = new Binary(tokens[3]);
                Decimal d1 = b1.toDecimal();
                Decimal d2 = b2.toDecimal();
                yield switch(tokens[1]) {
                    case "+" -> (new Decimal(d1.getValue() + d2.getValue())).toBinary().toString();
                    case "-" ->(new Decimal(d1.getValue() - d2.getValue())).toBinary().toString();
                    case "*" -> (new Decimal(d1.getValue() * d2.getValue())).toBinary().toString();
                    case "/" -> {
                        String q = (new Decimal(d1.getValue() / d2.getValue())).toBinary().toString();
                        String r = (new Decimal(d1.getValue() % d2.getValue())).toBinary().toString();
                        yield q + " Remainder: " + r;
                    }
                    default -> "Invalid operator.";
                };
            }
            case "hexadecimal" -> {
                Hexadecimal h1 = new Hexadecimal(tokens[2]);
                Hexadecimal h2 = new Hexadecimal(tokens[3]);
                Decimal d1 = h1.toDecimal();
                Decimal d2 = h2.toDecimal();
                yield switch(tokens[1]) {
                    case "+" -> (new Decimal(d1.getValue() + d2.getValue())).toHexadecimal().toString();
                    case "-" ->(new Decimal(d1.getValue() - d2.getValue())).toHexadecimal().toString();
                    case "*" -> (new Decimal(d1.getValue() * d2.getValue())).toHexadecimal().toString();
                    case "/" -> {
                        String q = (new Decimal(d1.getValue() / d2.getValue())).toHexadecimal().toString();
                        String r = (new Decimal(d1.getValue() % d2.getValue())).toHexadecimal().toString();
                        yield q + " Remainder: " + r;
                    }
                    default -> "Invalid operator.";
                };
            }
            case "download/upload" -> {
                double time = Double.parseDouble(tokens[2]);
                DataUnits dataUnit1 = DataUnits.valueOf(tokens[3].toUpperCase());
                double bandwidth = Double.parseDouble(tokens[4]);
                String dataUnitString = tokens[5].substring(0, tokens[5].indexOf('/'))+ "s";
                DataUnits dataUnit2 = DataUnits.valueOf(dataUnitString.toUpperCase());
                yield Bandwidth.downUpTime(time, dataUnit1, bandwidth, dataUnit2);
            }
            case "website" -> {
                try {
                    double views = Double.parseDouble(tokens[2]);
                    TimeUnits time = TimeUnits.valueOf(tokens[4].toUpperCase());
                    double pageSize = Double.parseDouble(tokens[5]);
                    DataUnits sizeUnit = DataUnits.valueOf(tokens[6].toUpperCase());
                    double redundancy = Double.parseDouble(tokens[7]);
                    yield Bandwidth.webBandwidth(views, time, pageSize, sizeUnit, redundancy);
                } catch(ArrayIndexOutOfBoundsException e) {
                    yield Calculator.ANSI_RED + "Error: Wrong number of arguments";
                }
            }
            default -> ("Could not calculate; invalid syntax.");
        };
    }

    private static String convert(String[] tokens) {
        return switch(tokens[0]) {
            case  "binary" -> (new Binary(tokens[3])).toDecimal().toString();
            case "hexadecimal" -> (new Hexadecimal(tokens[3])).toDecimal().toString();
            case "decimal" -> {
                if(tokens[2].equals("hexadecimal")) yield (new Decimal(tokens[3])).toHexadecimal().toString();
                if(tokens[2].equals("binary")) yield (new Decimal(tokens[3])).toBinary().toString();
                else yield "Cannot convert decimal to that type";
            }
            case "data" -> {
                Double dataAmount = Double.parseDouble(tokens[4]);
                String unitsString;
                if(tokens[3].indexOf('y') < 0) {
                    unitsString = tokens[3].substring(0, 1) + tokens[3].substring(tokens[3].indexOf('b')) + "s";
                }
                else unitsString = tokens[3];
                DataUnits dataUnit = DataUnits.valueOf(unitsString.toUpperCase());
                double result  = DataUnits.convert(Double.parseDouble(tokens[4]), DataUnits.BITS, dataUnit);
                yield result + " " + dataUnit.name;
            }
            case "monthly" -> {
                double dataSize = Double.parseDouble(tokens[4]);
                DataUnits dataUnit = DataUnits.valueOf(tokens[5].toUpperCase());
                double bandwidthSize = Double.parseDouble(tokens[6]);
                DataUnits bandwidthUnit = DataUnits.valueOf(tokens[7].substring(0, tokens[7].indexOf('/')).toUpperCase()+ "S");
                yield Bandwidth.hostBandwidth(dataSize,dataUnit,bandwidthSize,bandwidthUnit);
            }
            default -> Calculator.ANSI_RED + "Could not convert; unknown keyword " + tokens[0];
        };
    }
}
