package com.capillary.huffman.compressor;

import com.capillary.huffman.mydefines.Container;
import com.capillary.huffman.mydefines.Node;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CompressionUtils implements ICompressionUtils{

    @Override
    public Node createHuffmanTree(byte[] fileData) {
        ITreeCreationUtils utils=new TreeCreationUtils();


        Map<Byte,Integer> mp=utils.createFrequencyMap(fileData);
        System.out.println(mp);
        return utils.createTreeUsingMinHeap(mp);
    }

    @Override
    public void buildLookupRecursive(Node root, String s, Map<Byte, String> lookupMap) {
        if (root.left!=null && root.right!=null){
            buildLookupRecursive(root.left,s+"0",lookupMap);
            buildLookupRecursive(root.right,s+"1",lookupMap);
        }else{
            if (s==""){
                 lookupMap.put(root.data,"1");
            }else lookupMap.put(root.data,s);
        }
    }
//
//    01010101 0
    @Override
    public Container createCompressedArray(byte[] fileData, Map<Byte, String> lookupMap) {

        StringBuilder sb = new StringBuilder();

        for (byte b : fileData){
                sb.append(lookupMap.get(b));
        }

        int length=(sb.length()+7)/8;
        byte[] huffCodeBytes = new byte[length];

        int counter=0;
        int idx = 0;
        for (int i = 0; i < sb.length(); i += 8) {
            String s;
            if (i + 8 >= sb.length()){

                s = sb.substring(i);


                for (int j=0;j<s.length();j++){
                    if (s.charAt(j)=='0'){
                        counter++;
                    }else break;
                }

            }

            else s = sb.substring(i, i + 8);
            huffCodeBytes[idx] = (byte) Integer.parseInt(s , 2);
            idx++;
        }
        System.out.println(Arrays.toString(huffCodeBytes));
        System.out.println(counter);
        return new Container(huffCodeBytes, (byte) counter);
    }
}
