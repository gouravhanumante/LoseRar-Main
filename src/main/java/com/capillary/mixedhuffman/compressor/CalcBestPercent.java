package com.capillary.mixedhuffman.compressor;

import com.capillary.huffman.compressor.*;
import com.capillary.huffman.mydefines.HuffmanData;
import com.capillary.huffman.mydefines.Node;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

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

        long minSize=Long.MAX_VALUE;

        double bestPercent = 100;

        for(int i=0; i<=100; i++) {
            Map<String,Integer> freqMap = mapCreationUtils.getFrequencyMap(words, (double)i/100);
//            System.out.println(freqMap.size());
            String[] finalFileData=utils.getFinalFileData(words,freqMap);
//            System.out.println("zzz "+finalFileData.length +" " +freqMap.size());
            Node root=treeCreationUtils.createTreeUsingMinHeap(freqMap);
            Map<String,String> huffmanCodes=compressionUtils.buildLookupRecursive(root);

            double val1 = System.currentTimeMillis();

            double val2 = System.currentTimeMillis();
            HuffmanData huffmanData = compressionUtils.createCompressedArray(finalFileData,huffmanCodes);
            String destination="/home/sarthakjain/IdeaProjects/files/decompressedInput.txt";
            long bytesize=getSize(huffmanData.getHuffmanByte(),freqMap,huffmanCodes);
            long mapsize=getMapSize(freqMap);
//            System.out.println(bytesize+" "+mapsize);
//            long ans=getSize(huffmanData.getHuffmanByte(),freqMap,huffmanCodes);
            long ans=bytesize+mapsize;

            if (ans<minSize){
                minSize=ans;
                resultFreqMap=freqMap;
                bestPercent=i;

            }

//            System.out.println("for "+i+"%: "+ ans);

        }
//            System.out.println("Best percent: "bestPercent);
//        System.out.println("Best Percent:" +bestPercent + ", MinFileSize:" + minSize);

        return resultFreqMap;
    }


    private static int getMapSize(Map<String, Integer> mp) {

        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteOutputStream);
            objectOutputStream.writeObject(mp);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return byteOutputStream.toByteArray().length;
    }

    private long getSize(Byte[] huffmanByte, Map<String, Integer> freqMap, Map<String, String> huffmanCodes) {
        long size=0;
        for(Map.Entry<String,Integer> ele : freqMap.entrySet()){
//            System.out.println(ele.getKey()+" : "+ele.getValue()+" : "+ huffmanCodes.get(ele.getKey()).length());
            size+=huffmanCodes.get(ele.getKey()).length()*ele.getValue();
        }
//        size=size/8;

//        long size2=huffmanByte.length;

        return (long) Math.ceil(size/8.0);
//        System.out.println(size2);



//        byte[] arr=new byte[huffmanByte.length];
//        int x=0;
//        for (Byte b:huffmanByte){
//            arr[x++]=b;
//        }
//        size2=arr.length();

//        return size+size2;




    }

    static long fun(Map<String,Integer> mp){
        long size=0;

        for (Map.Entry<String ,Integer> entry:mp.entrySet()){
            size+=entry.getKey().length();
        }
        size+= mp.size()*4;

        return size;
    }

    }


//    int getCompressedArraySize(Map<String,String> huffmancodes, Map<String,Integer> freqMap){
//
//    }

//    public int getFreqSize(Map<String, Integer> freqMap) {
////        int size = 0;
////        try {
////            ByteArrayOutputStream output = new ByteArrayOutputStream();
////            ObjectOutputStream writer = new ObjectOutputStream(output);
////            writer.writeObject(freqMap);
////            writer.flush();
////            writer.close();
////            size = output.toByteArray().length;
////        }catch(IOException e){
////            System.out.println("I/O Error occurred!!!");
////        }
////        return size;
//        try{
////            System.out.println("Index Size: " + freqMap.size());
//            ByteArrayOutputStream baos=new ByteArrayOutputStream();
//            ObjectOutputStream oos=new ObjectOutputStream(baos);
//            oos.writeObject(freqMap);
////            oos.writeObject(compressedArray);
//            oos.close();
//            System.out.println("Map Size: " + baos.size());
//            return baos.size();
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//        return -1;
//    }

