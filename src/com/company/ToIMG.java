package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;

public class ToIMG {
    private String userString;
    private BufferedImage convertedImage;
    private SecureRandom randomGen;
    private int height;
    private int width;
    public ToIMG(String textIn, String seedIn, int height, int width) {
        this.height = height;
        this.width = width;
        userString = textIn;
        long seed = 0;
        char[] encryptChar = seedIn.toCharArray();
        for (int x = 0; x < seedIn.length(); x++) {
            seed += (int)encryptChar[x];
        }
        randomGen = new SecureRandom();
        randomGen.setSeed(seed);
        convertedImage = new BufferedImage(height,width,BufferedImage.TYPE_INT_ARGB);
        convertedImage.setRGB(0,0,new Color((int)(Math.random() * 256),userString.length(),(int)(Math.random() * 256)).getRGB());
        int totalCharCount = 0;
        for (int y = 0; y < height; y++) {
            int x = 0;
            if (y < 1) x = 1;
            for (;x < width; x++) {
                if (totalCharCount < userString.length()) {
                    int tempNum = randomGen.nextInt(3);
                    switch (tempNum) {
                        case 0: {
                            int multiplyInt = randomGen.nextInt(2);
                            if (multiplyInt == 0) {
                                convertedImage.setRGB(x, y, new Color((int)(userString.substring(totalCharCount,totalCharCount+1).toCharArray()[0]), (int) (Math.random() * 256), (int) (Math.random() * 256)).getRGB());
                            } else {
                                convertedImage.setRGB(x, y, new Color((int)(userString.substring(totalCharCount,totalCharCount+1).toCharArray()[0])*2, (int) (Math.random() * 256), (int) (Math.random() * 256)).getRGB());
                            }
                            break;
                        }
                        case 1: {
                            int multiplyInt = randomGen.nextInt(2);
                            if (multiplyInt == 0) {
                                convertedImage.setRGB(x, y, new Color((int) (Math.random() * 256),(int)(userString.substring(totalCharCount,totalCharCount+1).toCharArray()[0]), (int) (Math.random() * 256)).getRGB());
                            } else {
                                convertedImage.setRGB(x, y, new Color((int) (Math.random() * 256),(int)(userString.substring(totalCharCount,totalCharCount+1).toCharArray()[0])*2, (int) (Math.random() * 256)).getRGB());
                            }
                            break;
                        }
                        case 2: {
                            int multiplyInt = randomGen.nextInt(2);
                            if (multiplyInt == 0) {
                                convertedImage.setRGB(x, y, new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (userString.substring(totalCharCount, totalCharCount + 1).toCharArray()[0])).getRGB());
                            } else {
                                convertedImage.setRGB(x, y, new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (userString.substring(totalCharCount, totalCharCount + 1).toCharArray()[0])*2).getRGB());
                            }

                            break;
                        }
                    }
                    totalCharCount++;
                } else {
                    convertedImage.setRGB(x,y,new Color((int) (Math.random() * 256),(int) (Math.random() * 256),(int) (Math.random() * 256)).getRGB());
                }
            }
        }
    }
    public void writeImage(String path) {
        try {
            File outputFile = new File(path);
            ImageIO.write(convertedImage, "png", outputFile);
        } catch (IOException e) {
        }
    }
}
