package com.capillary.huffman.compressor;

import java.io.IOException;

public interface ICompressor {
    <T> void compress(Byte[] b, String destination) throws IOException;
}
