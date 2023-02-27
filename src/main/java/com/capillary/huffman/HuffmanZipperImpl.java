package com.capillary.huffman;

import com.capillary.IZipper;
import com.capillary.huffman.compressor.HuffmanCompressionImpl;
import com.capillary.huffman.compressor.ICompressor;
import com.capillary.huffman.compressor.IReadData;
import com.capillary.huffman.compressor.ReadDataImpl;
import com.capillary.huffman.decompressor.HuffmanDecompressionImpl;
import com.capillary.huffman.decompressor.IDecompressor;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class HuffmanZipperImpl implements IZipper {
    IReadData rw;
    ICompressor hc;

    public HuffmanZipperImpl(ReadDataImpl rw, HuffmanCompressionImpl hc) {
        this.hc=hc;
        this.rw=rw;
    }
    public HuffmanZipperImpl(){
        rw=new ReadDataImpl();
        hc=new HuffmanCompressionImpl();

    }

    @Override
    public void compression(String source, String destination) throws IOException, ExecutionException, InterruptedException {
        Byte[] b=rw.read(source);
        hc.compress(b,destination);
    }



    @Override
    public void decompression(String source, String destination) {
        IDecompressor huffmanDecompressor=new HuffmanDecompressionImpl();
        huffmanDecompressor.decompress(source,destination);
    }
}
