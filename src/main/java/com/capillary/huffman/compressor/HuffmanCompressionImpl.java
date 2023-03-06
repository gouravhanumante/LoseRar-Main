package com.capillary.huffman.compressor;

import com.capillary.huffman.mydefines.HuffmanData;
import com.capillary.huffman.mydefines.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class HuffmanCompressionImpl implements ICompressor{



    //
    IReadData rw;
    ICompressionUtils utils;
    ITreeCreationUtils treeCreationUtils;

    IWriteData w=new WriteDataImpl();


    public HuffmanCompressionImpl(IReadData rw, ICompressionUtils utils, ITreeCreationUtils treeCreationUtils) {
        this.rw=rw;
        this.utils=utils;
        this.treeCreationUtils = treeCreationUtils;
    }
    public HuffmanCompressionImpl() {
        this.rw=new ReadDataImpl();
        this.utils=new CompressionUtils();
        this.treeCreationUtils = new TreeCreationUtils();
    }


    public void compress(Byte[] b, String destination) throws ExecutionException, InterruptedException {
        Map<String, Integer> freqMap=new HashMap<>();
        HuffmanData huffmanData;
        if (b.length==0){
            huffmanData =new HuffmanData( b, (byte) 0);
            w.write(destination, huffmanData,freqMap, null);
            return;
        }
        String []fileData=new String[b.length];

        int idx=0;
        for (byte bb:b){
            fileData[idx++]=String.valueOf((char)bb);
        }
        System.out.println(Arrays.toString(fileData));

        freqMap=treeCreationUtils.createFrequencyMap(fileData);
        Node root=utils.createHuffmanTree(fileData);
//
        Map<String,String> huffmanCodes=  utils.buildLookupRecursive(root);

        huffmanData =utils.createCompressedArray(fileData,huffmanCodes);

        w.write(destination,huffmanData,freqMap,null);

    }

}

