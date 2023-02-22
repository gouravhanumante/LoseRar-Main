package com.capillary.mixedhuffman.compressor;




import com.capillary.huffman.compressor.*;
import com.capillary.huffman.mydefines.HuffmanData;
import com.capillary.huffman.mydefines.Node;

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
    public <T> void compress(Byte[] fileData, String destination) {
        Map<String,String> temp=new HashMap<>();
        if (fileData.length==0){
            huffmanData =new HuffmanData( fileData, (byte) 0);
            writeData.write(destination, huffmanData,temp);
            return;
        }

        Map<T, String> huffmanCodes=new HashMap<>();
        //1
//        String[] words=mapCreationUtils.createWordsArray(fileData);

        //2
//        Map<String,Integer> freqMap= (Map<String, Integer>) treeCreationUtils.createFrequencyMap(words);
        long val1 = System.currentTimeMillis();
        String[] words = mixedCreationUtils.createWordsArray(fileData);
        long val2 = System.currentTimeMillis();
        System.out.println(val2 - val1 + "createWordsArray");
        Map<String,Integer> finalMap=mapCreationUtils.getFrequencyMap(words);
        long val3 = System.currentTimeMillis();
        System.out.println(val3 - val2 + "getFrequencyMap");
        String[] finalFileData = mixedCreationUtils.getFinalFileData(words,finalMap);
        long val4 = System.currentTimeMillis();
        System.out.println(val4 - val3 + "getFinalFileData");
        Node root = treeCreationUtils.createTreeUsingMinHeap(finalMap);
        long val5 = System.currentTimeMillis();
        System.out.println(val5 - val4 + "createTreeUsingMinHeap");
        Map<String, String> huffCodes = compressionUtils.buildLookupRecursive(root);
        long val6 = System.currentTimeMillis();
        System.out.println(val6 - val5 + "buildLookupRecursive");

        HuffmanData huffmanData = compressionUtils.createCompressedArray(finalFileData,huffCodes);
        long val7 = System.currentTimeMillis();
        System.out.println(val7 - val6 + "createCompressedArray");

        writeData.write(destination, huffmanData, finalMap);
        long val8 = System.currentTimeMillis();
        System.out.println(val8 - val7 + "write");

        MixedCreationUtils utils=new MixedCreationUtils();
        System.out.println(utils.calculate(huffCodes,finalMap));
    }
}