package com.capillary.huffman.compressor;

import com.capillary.huffman.mydefines.HuffmanData;
import com.capillary.huffman.mydefines.Node;

import java.lang.reflect.Type;
import java.util.Map;

public interface ICompressionUtils {

     Node createHuffmanTree(String[] fileData);

     Map<String,String> buildLookupRecursive(Node root);

     HuffmanData createCompressedArray(String[] fileData, Map<String, String> lookupMap);


}
