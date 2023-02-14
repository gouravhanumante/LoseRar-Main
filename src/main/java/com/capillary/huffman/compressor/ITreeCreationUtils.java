package com.capillary.huffman.compressor;

import com.capillary.huffman.mydefines.Node;

import java.util.Map;

public interface ITreeCreationUtils {

    Map<Byte,Integer> createFrequencyMap(byte[] fileData);

    Node createTreeUsingMinHeap(Map<Byte,Integer> mp);
}
