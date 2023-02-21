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
        String[] words = mixedCreationUtils.createWordsArray(fileData);
        Map<String,Integer> finalMap=mapCreationUtils.getFrequencyMap(words);

        String[] finalFileData = mixedCreationUtils.getFinalFileData(words,finalMap);

        Node root = treeCreationUtils.createTreeUsingMinHeap(finalMap);

        Map<String, String> huffCodes = compressionUtils.buildLookupRecursive(root);

        HuffmanData huffmanData = compressionUtils.createCompressedArray(finalFileData,huffCodes);

        writeData.write(destination, huffmanData, huffCodes);

        MixedCreationUtils utils=new MixedCreationUtils();
        System.out.println(utils.calculate(huffCodes,finalMap));
    }
}