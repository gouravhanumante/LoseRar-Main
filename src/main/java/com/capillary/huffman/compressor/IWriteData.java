package com.capillary.huffman.compressor;

import com.capillary.huffman.mydefines.HuffmanData;

import java.util.Map;

public interface IWriteData {
    void write(String destination, HuffmanData huffmanData, Map<?,String> huffCodes);
}
