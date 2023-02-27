package com.capillary.mixedhuffman.compressor;

import java.util.Map;

public interface IMapCreationUtils {
    Map<String,Integer> getFrequencyMap(String[] words);


    Map<String,Integer> sortMapByValue(Map<String,Integer> mp);

    Map<String,Integer> createFinalFrequencyMap(String[] words,Map<String ,Integer> mp,double percentage);


}
