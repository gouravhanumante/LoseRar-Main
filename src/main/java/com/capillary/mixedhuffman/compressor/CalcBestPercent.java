package com.capillary.mixedhuffman.compressor;

import com.capillary.huffman.compressor.*;
import com.capillary.huffman.mydefines.Node;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.concurrent.Callable;

public class CalcBestPercent implements Callable {

    IMapCreationUtils mapCreationUtils;
    MixedCreationUtils utils;
    ITreeCreationUtils treeCreationUtils;
    ICompressionUtils compressionUtils;
    IWriteData writeData;

    private String[] words;
    Map<String ,Integer> resultFreqMap;

    Map<String ,Integer> sortedMap;

    int start;

    public CalcBestPercent(String[] words,Map<String,Integer> sortedMap, int i) {
        this.words=words;
        this.sortedMap=sortedMap;
        this.start=i;

    }


    //    public Map<String, Integer> getBestPercent(String[] words){
//
//    }
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

    private long getSize(Map<String, Integer> freqMap, Map<String, String> huffmanCodes) {
        long size=0;
        for(Map.Entry<String,Integer> ele : freqMap.entrySet()){
            size+=huffmanCodes.get(ele.getKey()).length()*ele.getValue();
        }


        return (long) Math.ceil(size/8.0);

    }


    @Override
    public Long call() throws Exception {
        mapCreationUtils=new MapCreationUtilsImpl();
        utils=new MixedCreationUtils();
        treeCreationUtils=new TreeCreationUtils();
        compressionUtils=new CompressionUtils();
        writeData = new WriteDataImpl();


        long minSize=Long.MAX_VALUE;

        double bestPercent = 100;

//        if (start!=5) return;
        for(int i=(start*20); i<=(start*20)+20; i+=10) {
            System.out.println(i+"%");
//            if (i==0) continue;
            if (i!=100) continue;


            long val1=System.currentTimeMillis();
            Map<String,Integer> freqMap=mapCreationUtils.createFinalFrequencyMap(words,sortedMap,(i/100.0));
            long val2=System.currentTimeMillis();
            //System.out.println(val2-val1+" getFrequencyMap");


            Node root=treeCreationUtils.createTreeUsingMinHeap(freqMap);
            val1=System.currentTimeMillis();
            //System.out.println(val1-val2+" createTreeUsingMinHeap");

            Map<String,String> huffmanCodes=compressionUtils.buildLookupRecursive(root);
            val2=System.currentTimeMillis();
            //System.out.println(val2-val1+" buildLookupRecursive");


            long bytesize=getSize(freqMap,huffmanCodes);
            long mapsize=getMapSize(freqMap);
            long ans=bytesize+mapsize;
            System.out.println("ans: "+ans+" for %"+i);
            //System.out.println("Map SIze at" + i +"%" + mapsize);
            if (ans<minSize){
                minSize=ans;
                resultFreqMap=freqMap;
                bestPercent=i;

            }

           // System.out.println("for "+i+"%: "+ ans);

        }

        //System.out.println("Best Percent:" +bestPercent + ", MinFileSize:" + minSize);

        return minSize;

    }

    public Map<String, Integer> getResultFreqMap() {
        return resultFreqMap;
    }
}
