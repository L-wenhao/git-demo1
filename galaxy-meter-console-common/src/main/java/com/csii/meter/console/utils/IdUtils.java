package com.csii.meter.console.utils;

import org.apache.commons.lang3.RandomStringUtils;


public class IdUtils {


    public static String getRandomScrect(int count) {
        return RandomStringUtils.random(count, new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 'e', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'});
    }

}