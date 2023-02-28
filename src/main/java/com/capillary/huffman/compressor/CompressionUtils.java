package com.capillary.huffman.compressor;

import com.capillary.huffman.mydefines.HuffmanData;
import com.capillary.huffman.mydefines.Node;

import java.util.HashMap;
import java.util.Map;

public class CompressionUtils implements ICompressionUtils{

    @Override
    public  Node createHuffmanTree(String[] fileData) {
        ITreeCreationUtils utils=new TreeCreationUtils();


        Map<String, Integer> mp=  utils.createFrequencyMap(fileData);

//        System.out.println("mppp " +mp);
        return utils.createTreeUsingMinHeap(mp);
    }

    @Override
    public  Map<String, String> buildLookupRecursive(Node root) {
        Map<String,String> mp=new HashMap<>();
        if (root==null) return mp;

        buildLookupRecursive(root,"",mp);
        return mp;



    }

//    @Override
    private  void buildLookupRecursive(Node root, String s, Map<String, String> lookupMap) {
        if (root.left!=null && root.right!=null){
            buildLookupRecursive(root.left,s+"0",lookupMap);
            buildLookupRecursive(root.right,s+"1",lookupMap);
        }else{
            if (s==""){
                 lookupMap.put( root.data,"1");
            }else lookupMap.put(root.data,s);
        }
    }







//
//    01010101 0
    public HuffmanData createCompressedArray(String[] fileData, Map<String , String> lookupMap) {

        StringBuilder sb = new StringBuilder();
//        StringBuilder sb2 = new StringBuilder();
//        int i = fileData.length;
        for (String b : fileData){
            if(lookupMap.containsKey(b))
                sb.append(lookupMap.get(b));
            else{
                for(char c: b.toCharArray()){
                    sb.append(lookupMap.get(String.valueOf(c)));
                }
            }
        }
        int length=(sb.length()+7)/8;
//        Byte[] huffCodeBytes = new Byte[length];
        Byte[] huffCodeBytes=new Byte[length];
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
