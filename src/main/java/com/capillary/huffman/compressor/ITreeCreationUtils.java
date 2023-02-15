package com.capillary.huffman.compressor;

import com.capillary.huffman.mydefines.Node;

import java.util.Map;

public interface ITreeCreationUtils {

     <T> Map<Byte,Integer> createFrequencyMap(T[] fileData);


//    Map<Byte,Integer> createFrequencyMap(ArrayList<String> words);

    Node createTreeUsingMinHeap(Map<?,Integer> mp);
}
