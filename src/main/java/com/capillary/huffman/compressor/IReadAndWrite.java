package com.capillary.huffman.compressor;

import com.capillary.huffman.mydefines.Container;

import java.util.Map;

public interface IReadAndWrite {
    public byte[] read(String source);
    void write(String destination, Container huffmanBytes, Map<Byte,String> lookupMap);
}
