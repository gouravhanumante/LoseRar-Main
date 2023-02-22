package com.capillary.mixedhuffman;

import com.capillary.IZipper;
import com.capillary.huffman.compressor.ICompressor;
import com.capillary.huffman.compressor.IReadData;
import com.capillary.huffman.compressor.ReadDataImpl;
import com.capillary.huffman.decompressor.HuffmanDecompressionImpl;
import com.capillary.huffman.decompressor.IDecompressor;
import com.capillary.mixedhuffman.compressor.MixedCompressionImpl;
import com.capillary.mixedhuffman.decompressor.MixedDecompressionImpl;

import java.io.IOException;

public class MixedZipperImpl implements IZipper
{
    @Override
    public void compression(String source, String destination) {
        IReadData r=new ReadDataImpl();
        ICompressor compressor=new MixedCompressionImpl();

        Byte[] fileData=r.read(source);
        try {
            compressor.compress(fileData,destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void decompression(String source, String destination) {
        MixedDecompressionImpl decompress=new MixedDecompressionImpl();
        decompress.decompress(source,destination);
    }
}
