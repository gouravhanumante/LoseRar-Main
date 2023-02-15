package com.capillary.huffman.compressor;

public interface ICompressor {
    <T> void compress(T[] b, String destination);
}
