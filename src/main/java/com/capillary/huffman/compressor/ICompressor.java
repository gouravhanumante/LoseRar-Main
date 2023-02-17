package com.capillary.huffman.compressor;

public interface ICompressor {
    <T> void compress(Byte[] b, String destination);
}
