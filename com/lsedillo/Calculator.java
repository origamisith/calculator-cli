package com.lsedillo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

/** This is the entry point class. It is responsible for displaying some helpful colorful text,
 *  reading input files, and sending the user input to <code>ParseCommand</code> to be processed.
 */
public class Calculator {
    //The following are some String constants that, when printed with System.out.print, will
    //add color to the output.
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static void main(String[] args) {
        mainLoop();
    }

    /**
     * This method is called once, initiating the REPL.
     */
    static void mainLoop() {
        Scanner s = new Scanner(System.in);
        System.out.print(ANSI_BLUE + "Welcome to the CS Students Handy Calculator! ");
        while (true) {
            System.out.println("Do you have a file to read from? (y/N)" + ANSI_RESET);
            String input = s.nextLine();
            if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
                System.out.println(ANSI_BLUE + "Please provide the absolute path to your file" + ANSI_RESET);
                input = s.nextLine();
                try {
                    Scanner file = new Scanner(Path.of(input), StandardCharsets.UTF_8);
                    System.out.println(input);
                    while(file.hasNext()) {
                        String line = file.nextLine();
                        if(!line.startsWith("//"))
                            System.out.println(line + ": " + ANSI_GREEN + ParseCommand.chooseMethod(line)+ ANSI_RESET);
                    }
                } catch( IOException e) {
                    System.out.println(ANSI_RED + "File does not exist: " + e + ANSI_RESET);
                }
            }
            System.out.println(ANSI_BLUE + "Enter a command. Type 'help' if needed" + ANSI_RESET);
            input = s.nextLine();
            if(input.equalsIgnoreCase("help")) {
                help();
                System.out.println(ANSI_BLUE + "Please enter a command" + ANSI_RESET);
                input = s.nextLine();
            }
            if(input.equalsIgnoreCase("quit")) break;
            System.out.println(ANSI_GREEN + ParseCommand.chooseMethod(input.toLowerCase()) + ANSI_RESET);
        }
    }
    static void help() {
        try {
            Scanner s = new Scanner(Path.of("com/lsedillo/Help"), StandardCharsets.UTF_8);
            while(s.hasNext()) {
                System.out.println(s.nextLine());
            }
        } catch( IOException e) {
            System.out.println("File does not exist: " + e);
        }
    }
}



