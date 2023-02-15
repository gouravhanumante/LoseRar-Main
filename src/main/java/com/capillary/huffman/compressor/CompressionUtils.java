package com.capillary.huffman.compressor;

import com.capillary.huffman.mydefines.HuffmanData;
import com.capillary.huffman.mydefines.Node;

import java.util.HashMap;
import java.util.Map;

public class CompressionUtils implements ICompressionUtils{

    @Override
    public <Byte> Node createHuffmanTree(Byte[] fileData) {
        ITreeCreationUtils utils=new TreeCreationUtils();


        Map<java.lang.Byte, Integer> mp=utils.createFrequencyMap(fileData);

        return utils.createTreeUsingMinHeap(mp);
    }

    @Override
    public Map<?, String> buildLookupRecursive(Node root) {
        Map<Byte,String> mp=new HashMap<>();
        if (root==null) return mp;

        buildLookupRecursive(root,"",mp);
        return mp;



    }

//    @Override
    private void buildLookupRecursive(Node root, String s, Map<Byte, String> lookupMap) {
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
    public <Byte> HuffmanData createCompressedArray(Byte[] fileData, Map<?, String> lookupMap) {

        StringBuilder sb = new StringBuilder();

        for (Byte b : fileData){
                sb.append(lookupMap.get(b));
        }

        int length=(sb.length()+7)/8;
//        Byte[] huffCodeBytes = new Byte[length];
        java.lang.Byte[] huffCodeBytes=new java.lang.Byte[length];
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

        return new HuffmanData(huffCodeBytes, (byte) counter);
    }
}
