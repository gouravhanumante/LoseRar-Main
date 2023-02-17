package com.capillary.huffman.compressor;

import com.capillary.huffman.mydefines.Node;

import java.util.Map;

public interface ITreeCreationUtils {

     <T> Map<?,Integer> createFrequencyMap(T[] fileData);


//    Map<Byte,Integer> createFrequencyMap(ArrayList<String> words);

    <T extends Comparable<T>> Node<T> createTreeUsingMinHeap(Map<?,Integer> mp);
}
