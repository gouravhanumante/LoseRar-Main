package com.capillary.mixedhuffman.compressor;

import com.capillary.huffman.compressor.ITreeCreationUtils;
import com.capillary.huffman.compressor.TreeCreationUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MapCreationUtilsImpl implements IMapCreationUtils
{

    ITreeCreationUtils treeCreationUtils=new TreeCreationUtils();
    @Override
    public Map<String, Integer> getFrequencyMap(String[] words) {

        Map<String,Integer> freqMap= (Map<String, Integer>) treeCreationUtils.createFrequencyMap(words);
        Map<String,Integer> sortedFreqMap= sortMapByValue(freqMap);
        Map<String,Integer> finalFrequencyMap=createFinalFrequencyMap(words,sortedFreqMap);

        return finalFrequencyMap;


//        String[] result=getFinalFileData(words,finalFrequencyMap);







    }

    @Override
    public Map<String, Integer> sortMapByValue(Map<String, Integer> mp) {

        HashMap<String, Integer> temp = mp.entrySet().stream()
                .sorted((i2, i1) -> i1.getValue().compareTo(i2.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return temp;
    }

    @Override
    public Map<String, Integer> createFinalFrequencyMap(String[] words, Map<String, Integer> mp) {

        int sizeForCreation = (int) (mp.size() * .20);
        int i = 0;

        Map<String, Integer> result = new HashMap<>();
        for (Map.Entry<String, Integer> entry : mp.entrySet()) {
            if (i >= sizeForCreation) break;
            result.put(entry.getKey(), entry.getValue());
            i++;
        }



        Map<String, Integer> finalMap = new HashMap<>();
//        System.out.println(f15);
        for (String s : words) {
            if (!result.containsKey(s)) {
                for (char c : s.toCharArray()) {
                    if (finalMap.containsKey(String.valueOf(c))) {
                        finalMap.put(String.valueOf(c), finalMap.get(String.valueOf(c)) + 1);
                    } else {
                        finalMap.put(String.valueOf(c), 1);
                    }
                }
            }
        }
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            finalMap.put(entry.getKey(), entry.getValue());
        }
        return finalMap;


    }
}
