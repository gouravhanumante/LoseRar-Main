package com.capillary.huffman.decompressor;

import java.util.Map;

public interface IDecompressionUtils {
    Byte[] decompress(Byte[] huffmanBytes, Map<Byte, String> lookupMap, byte counter);



}
