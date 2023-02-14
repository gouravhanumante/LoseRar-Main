package com.capillary.huffman.compressor;

import com.capillary.huffman.mydefines.Container;
import com.capillary.huffman.mydefines.Node;

import java.util.Map;

public interface ICompressionUtils {

    Node createHuffmanTree(byte[] fileData);

    void buildLookupRecursive(Node root,String s,Map<Byte,String> lookupMap);

    Container createCompressedArray(byte[] fileData, Map<Byte, String> lookupMap);


}
