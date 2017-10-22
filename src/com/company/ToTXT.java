package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;

public class ToTXT {
    private SecureRandom randomGen;
    private BufferedImage imageIn;
    private String convertedOut;
    public ToTXT(String encrypt, String path, int height, int width) {
        imageIn = null;
        try {
            imageIn = ImageIO.read(new File(path));
        } catch (IOException e) {}
        int stringLength = new Color(imageIn.getRGB(0,0)).getGreen();
        int totalCharCount = 0;
        long seed = 0;
        convertedOut = "";
        char[] encryptChar = encrypt.toCharArray();
        randomGen = new SecureRandom();
        for (int x = 0; x < encrypt.length(); x++) {
            seed += (int)encryptChar[x];
        }
        randomGen.setSeed(seed);
        for (int y = 0; y < height; y++) {
            int x = 0;
            if (y < 1) x = 1;
            for (;x < width; x++) {
                if (totalCharCount < stringLength) {
                    int tempNum = randomGen.nextInt(3);
                    int currentAscii = 0;
                    switch (tempNum) {
                        case 0:{
                            int multiplyInt = randomGen.nextInt(2);
                            if (multiplyInt == 0) {
                                currentAscii = new Color(imageIn.getRGB(x, y)).getRed();
                            } else {
                                currentAscii = new Color(imageIn.getRGB(x, y)).getRed() / 2;
                            }
                            break;
                        }
                        case 1: {
                            int multiplyInt = randomGen.nextInt(2);
                            if (multiplyInt == 0) {
                                currentAscii = new Color(imageIn.getRGB(x, y)).getGreen();
                            } else {
                                currentAscii = new Color(imageIn.getRGB(x, y)).getGreen() / 2;
                            }
                            break;
                        }
                        case 2: {
                            int multiplyInt = randomGen.nextInt(2);
                            if (multiplyInt == 0) {
                                currentAscii = new Color(imageIn.getRGB(x, y)).getBlue();
                            } else {
                                currentAscii = new Color(imageIn.getRGB(x, y)).getBlue() / 2;
                            }
                            break;
                        }
                    }
                    convertedOut += (char)currentAscii;
                    totalCharCount++;
                }
            }
        }
    }
    public String getText() {
        return convertedOut;
    }
}
