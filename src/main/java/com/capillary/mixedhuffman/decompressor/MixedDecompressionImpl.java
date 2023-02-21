package com.capillary.mixedhuffman.decompressor;

import com.capillary.huffman.decompressor.HuffmanDecompressionImpl;
import com.capillary.huffman.decompressor.IDecompressor;

public class MixedDecompressionImpl implements IDecompressor {
    @Override
    public <T> void decompress(String source, String destination) {

        IDecompressor decompressor=new HuffmanDecompressionImpl();
        decompressor.decompress(source,destination);
    }
}
