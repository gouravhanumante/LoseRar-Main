package com.capillary.huffman.compressor;

import com.capillary.huffman.mydefines.HuffmanData;
import com.capillary.huffman.mydefines.Node;

import java.lang.reflect.Type;
import java.util.Map;

public interface ICompressionUtils {

    <T> Node createHuffmanTree(T[] fileData);

    <T> Map<T,String> buildLookupRecursive(Node root);

    <T> HuffmanData createCompressedArray(T[] fileData, Map<?, String> lookupMap);


}
