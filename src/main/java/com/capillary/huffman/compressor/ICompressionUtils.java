package com.capillary.huffman.compressor;

import com.capillary.huffman.mydefines.HuffmanData;
import com.capillary.huffman.mydefines.Node;

import java.util.Map;

public interface ICompressionUtils {

    Node createHuffmanTree(byte[] fileData);

    Map<Byte,String> buildLookupRecursive(Node root);

    HuffmanData createCompressedArray(byte[] fileData, Map<Byte, String> lookupMap);


}
