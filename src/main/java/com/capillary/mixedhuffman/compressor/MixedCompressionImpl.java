package com.capillary.mixedhuffman.compressor;




import com.capillary.huffman.compressor.*;
import com.capillary.huffman.mydefines.HuffmanData;
import com.capillary.huffman.mydefines.Node;
import name.finsterwalder.fileutils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class MixedCompressionImpl implements ICompressor
{
    HuffmanData huffmanData;
    IWriteData writeData =new WriteDataImpl();

    IMapCreationUtils mapCreationUtils=new MapCreationUtilsImpl();

    ITreeCreationUtils treeCreationUtils=new TreeCreationUtils();

    MixedCreationUtils mixedCreationUtils =new MixedCreationUtils();



    ICompressionUtils compressionUtils=new CompressionUtils();

    @Override
    public <T> void compress(Byte[] fileData, String destination) throws IOException {
        Map<String,String> temp=new HashMap<>();
        if (fileData.length==0){
            huffmanData =new HuffmanData( fileData, (byte) 0);
            writeData.write(destination, huffmanData,temp);
            return;
        }

        long val1 = System.currentTimeMillis();
        String[] words = mixedCreationUtils.createWordsArray(fileData);
        long val2 = System.currentTimeMillis();
        System.out.println(val2 - val1 + "createWordsArray");

        val1 = System.currentTimeMillis();
        CalcBestPercent calcBestPercent = new CalcBestPercent();
        Map<String, Integer> freqMap = calcBestPercent.getBestPercent(words);
        val2 = System.currentTimeMillis();
        System.out.println(val2 - val1 + "CalcBestPercent");

        val1 = System.currentTimeMillis();
        String[] finalFileData=mixedCreationUtils.getFinalFileData(words,freqMap);
        val2 = System.currentTimeMillis();
        System.out.println(val2 - val1 + "getFinalFileData after best percent");

        Node root=treeCreationUtils.createTreeUsingMinHeap(freqMap);
        val1 = System.currentTimeMillis();
        System.out.println(val1 - val2 + "createTreeUsingMinHeap");

        Map<T,String> huffmanCodes=compressionUtils.buildLookupRecursive(root);
        val2 = System.currentTimeMillis();
        System.out.println(val2 - val1 + "buildLookupRecursive");

        huffmanData = compressionUtils.createCompressedArray(finalFileData,huffmanCodes);
        val1 = System.currentTimeMillis();
        System.out.println(val1 - val2 + "createCompressedArray");

        writeData.write(destination, huffmanData, freqMap);
        val2 = System.currentTimeMillis();
        System.out.println(val2 - val1 + "write");


    }
}