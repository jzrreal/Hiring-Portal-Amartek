package com.hiringportal.utils;

import com.hiringportal.entities.EducationHistory;
import com.hiringportal.enums.EducationLevel;

import java.util.Map;

public class WordUtil {

    public static String capitalizeEachLetter(String word) {

        char[] charArray = word.toCharArray();
        boolean foundSpace = true;

        for (int i = 0; i < charArray.length; i++) {

            // if the array element is a letter
            if (Character.isLetter(charArray[i])) {

                // check space is present before the letter
                if (foundSpace) {

                    // change the letter into uppercase
                    charArray[i] = Character.toUpperCase(charArray[i]);
                    foundSpace = false;
                }
            } else {
                // if the new character is not character
                foundSpace = true;
            }
        }

        return String.valueOf(charArray);

    }

    public static String getLastEducation(Map<EducationLevel, EducationHistory> educationHistoryMap) {

        if (educationHistoryMap.isEmpty()) return null;
        if (educationHistoryMap.containsKey(EducationLevel.S3)) return "S3 " + educationHistoryMap.get(EducationLevel.S3).getName();
        if (educationHistoryMap.containsKey(EducationLevel.S2)) return "S2 " + educationHistoryMap.get(EducationLevel.S2).getName();
        if (educationHistoryMap.containsKey(EducationLevel.S1)) return "S1 " + educationHistoryMap.get(EducationLevel.S1).getName();
        if (educationHistoryMap.containsKey(EducationLevel.D3)) return "D3 " + educationHistoryMap.get(EducationLevel.D3).getName();
        if (educationHistoryMap.containsKey(EducationLevel.SMA)) return "SMA " + educationHistoryMap.get(EducationLevel.SMA).getName();

        return null;
    }

}
