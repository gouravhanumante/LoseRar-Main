package com.capillary.huffman.compressor;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface ICompressor {
    <T> void compress(Byte[] b, String destination) throws IOException, ExecutionException, InterruptedException;
}
