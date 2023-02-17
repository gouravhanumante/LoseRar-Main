package com.capillary.huffman.decompressor;

import java.util.Map;

public interface IDecompressionUtils {
    <T> T[] decompress(byte[] huffmanBytes, Map<T, String> lookupMap, byte counter);



}
