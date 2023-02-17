package com.capillary.huffman.decompressor;

import java.util.Map;

public interface IDecompressionUtils {
    <T> byte[] decompress(byte[] huffmanBytes, Map<T, String> lookupMap, byte counter);



}
