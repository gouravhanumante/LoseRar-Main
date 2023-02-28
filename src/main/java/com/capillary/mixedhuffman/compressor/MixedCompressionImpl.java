package com.capillary.mixedhuffman.compressor;




import com.capillary.huffman.compressor.*;
import com.capillary.huffman.mydefines.HuffmanData;
import com.capillary.huffman.mydefines.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MixedCompressionImpl implements ICompressor
{
    HuffmanData huffmanData;
    IWriteData writeData =new WriteDataImpl();

    IMapCreationUtils mapCreationUtils=new MapCreationUtilsImpl();

    ITreeCreationUtils treeCreationUtils=new TreeCreationUtils();

    MixedCreationUtils mixedCreationUtils =new MixedCreationUtils();



    ICompressionUtils compressionUtils=new CompressionUtils();


//    Helper helper=new Helper();
    @Override
    public void compress(Byte[] fileData, String destination) throws IOException, ExecutionException, InterruptedException {
        Map<String,Integer> temp=new HashMap<>();
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



        //map creation normal
        Map<String,Integer> initialMap=mapCreationUtils.getFrequencyMap(words);


        //multi
        ExecutorService service= Executors.newFixedThreadPool(4);
        CalcBestPercent[] tasks=new CalcBestPercent[4];
        List<Future<Long>> sizes=new ArrayList<>();
        long st=System.currentTimeMillis();

        for (int i=0;i<4;i++){
            tasks[i]=new CalcBestPercent(words,initialMap,i);
            sizes.add(service.submit(tasks[i]));
        }


        Long bestSize=Long.MAX_VALUE;
        Map<String,Integer> bestMap = null;

        for (int i=0;i<4;i++){
            long size=sizes.get(i).get().longValue();
            if (size<bestSize){
                bestSize=size;
                bestMap=tasks[i].getResultFreqMap();
            }
        }
        service.shutdown();
        long et=System.currentTimeMillis();
        System.out.println("st-et:" +(et-st));
        System.out.println("bestSize:"+bestSize);


        val2 = System.currentTimeMillis();
        System.out.println(val2 - val1 + "CalcBestPercent");

        val1 = System.currentTimeMillis();
//        String[] finalFileData=mixedCreationUtils.getFinalFileData(words,bestMap);
        val2 = System.currentTimeMillis();
        System.out.println(val2 - val1 + "getFinalFileData after best percent");

        Node root=treeCreationUtils.createTreeUsingMinHeap(bestMap);
        val1 = System.currentTimeMillis();
        System.out.println(val1 - val2 + "createTreeUsingMinHeap");

        Map<String,String> huffmanCodes=compressionUtils.buildLookupRecursive(root);
        val2 = System.currentTimeMillis();
        System.out.println(val2 - val1 + "buildLookupRecursive");

//        System.out.println("best map size h yaha par "+helper.getMapSize(bestMap));

        huffmanData = compressionUtils.createCompressedArray(words,huffmanCodes);
        val1 = System.currentTimeMillis();
        System.out.println(val1 - val2 + "createCompressedArray");
        writeData.write(destination, huffmanData, bestMap);
        val2 = System.currentTimeMillis();
        System.out.println(val2 - val1 + "write");


    }
}