package com.capillary.huffman.compressor;

import com.capillary.huffman.mydefines.Node;

import java.util.Map;

public interface ITreeCreationUtils {

     Map<String,Integer> createFrequencyMap(String[] fileData);


//    Map<Byte,Integer> createFrequencyMap(ArrayList<String> words);

    Node createTreeUsingMinHeap(Map<String,Integer> mp);
}
