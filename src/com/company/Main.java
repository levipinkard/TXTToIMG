package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        while (true) {
            System.out.println("Welcome to the image encryptor!\nLevi Pinkard 10/22/2017");
            System.out.print("E for Encrypt, D for Decrypt, EX to Exit: ");
            Scanner userIn = new Scanner(System.in);
            String userChoice = userIn.nextLine();
            if (userChoice.toLowerCase().equals("e")) {
                ToIMG converter;
                System.out.println("\nWhat phrase would you like to encrypt?");
                String userString = userIn.nextLine();
                System.out.println("And your key?");
                String userKey = userIn.nextLine();
                converter = new ToIMG(userString, userKey, 30, 30);
                System.out.println("Filename (must be png)?");
                String path = userIn.nextLine();
                converter.writeImage(path);
            } else if (userChoice.toLowerCase().equals("d")) {
                ToTXT decrypter;
                System.out.println("Filename?");
                String path = userIn.nextLine();
                System.out.println("What is your decryption key?");
                String userKey = userIn.nextLine();
                System.out.println("It says:");
                decrypter = new ToTXT(userKey,path, 30, 30);
                System.out.println(decrypter.getText());
            } else if (userChoice.toLowerCase().equals("ex")) {
                System.out.println();
                break;
            }
        }
    }
}
