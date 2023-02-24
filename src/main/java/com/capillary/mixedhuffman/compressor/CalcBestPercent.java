package com.capillary.mixedhuffman.compressor;

import com.capillary.huffman.compressor.*;
import com.capillary.huffman.mydefines.HuffmanData;
import com.capillary.huffman.mydefines.Node;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import static java.lang.Long.MAX_VALUE;
import static java.lang.Long.min;

public class CalcBestPercent {

    IMapCreationUtils mapCreationUtils;
    MixedCreationUtils utils;
    ITreeCreationUtils treeCreationUtils;
    ICompressionUtils compressionUtils;
    IWriteData writeData;
    public Map<String, Integer> getBestPercent(String[] words){
        mapCreationUtils=new MapCreationUtilsImpl();
        utils=new MixedCreationUtils();
        treeCreationUtils=new TreeCreationUtils();
        compressionUtils=new CompressionUtils();
        writeData = new WriteDataImpl();

        Map<String, Integer> resultFreqMap = null;
        double minFileSize = Long.MAX_VALUE;
        double bestPercent = 100;

        for(int i=0; i<=100; i++) {
            Map<String,Integer> freqMap = mapCreationUtils.getFrequencyMap(words, (double)i/100);
            String[] finalFileData=utils.getFinalFileData(words,freqMap);
            Node root=treeCreationUtils.createTreeUsingMinHeap(freqMap);
            Map<String,String> huffmanCodes=compressionUtils.buildLookupRecursive(root);
            double val1 = System.currentTimeMillis();
//            int totalFileSize =  (getCompressedArraySize(huffmanCodes, freqMap)) + getFreqSize(freqMap);
            double val2 = System.currentTimeMillis();
            HuffmanData huffmanData = compressionUtils.createCompressedArray(finalFileData,huffmanCodes);
            String destination="/home/sarthakjain/IdeaProjects/files/decompressedInput.txt";
//            writeData.write(destination, huffmanData, freqMap);
//            File compressed = new File(destination);
//            long totalFileSize = compressed.length();
            int totalFileSize = getFreqSize(freqMap, huffmanData.getHuffmanByte());
            System.out.println(totalFileSize + " for " + i + "%");

            if(totalFileSize < minFileSize){
                resultFreqMap = freqMap;
                minFileSize = totalFileSize;
                bestPercent = i;
            }
        }

        System.out.println("Best Percent:" +bestPercent + ", MinFileSize:" + minFileSize);
        return resultFreqMap;
    }

    int getCompressedArraySize(Map<String,String> huffmancodes, Map<String,Integer> freqMap){

    }

    public int getFreqSize(Map<String, Integer> freqMap) {
//        int size = 0;
//        try {
//            ByteArrayOutputStream output = new ByteArrayOutputStream();
//            ObjectOutputStream writer = new ObjectOutputStream(output);
//            writer.writeObject(freqMap);
//            writer.flush();
//            writer.close();
//            size = output.toByteArray().length;
//        }catch(IOException e){
//            System.out.println("I/O Error occurred!!!");
//        }
//        return size;
        try{
//            System.out.println("Index Size: " + freqMap.size());
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            ObjectOutputStream oos=new ObjectOutputStream(baos);
            oos.writeObject(freqMap);
//            oos.writeObject(compressedArray);
            oos.close();
            System.out.println("Map Size: " + baos.size());
            return baos.size();
        }catch(IOException e){
            e.printStackTrace();
        }
        return -1;
    }
}
