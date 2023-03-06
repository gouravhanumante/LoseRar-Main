package com.capillary.huffman.decompressor;

import java.util.Map;

public interface IDecompressionUtils {
    byte[] decompress(byte[] huffmanBytes, Map<String, Integer> freqMap, byte counter);



}
