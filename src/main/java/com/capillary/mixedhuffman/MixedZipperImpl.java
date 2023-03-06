package com.capillary.mixedhuffman;

import com.capillary.IZipper;
import com.capillary.huffman.compressor.ICompressor;
import com.capillary.huffman.compressor.IReadData;
import com.capillary.huffman.compressor.ReadDataImpl;
import com.capillary.huffman.decompressor.IDecompressor;
import com.capillary.mixedhuffman.compressor.MixedCompressionImpl;
import com.capillary.mixedhuffman.decompressor.MixedDecompressionImpl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class MixedZipperImpl implements IZipper
{
    @Override
    public void compression(String source, String destination) throws ExecutionException, InterruptedException, SQLException, NoSuchAlgorithmException, ClassNotFoundException {
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
        IDecompressor decompress=new MixedDecompressionImpl();
        decompress.decompress(source,destination);
    }

}
