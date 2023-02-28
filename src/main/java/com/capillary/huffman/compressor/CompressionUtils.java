package com.capillary.huffman.compressor;

import com.capillary.huffman.mydefines.HuffmanData;
import com.capillary.huffman.mydefines.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        /////

        List<String> huffcodesList=new ArrayList<>();
        StringBuilder temp=new StringBuilder();
        for (String s:fileData){
            if(lookupMap.containsKey(s)){
                temp.append(lookupMap.get(s));
                ///
                if (temp.length()==8){
                    huffcodesList.add(temp.toString());
                    temp.delete(0,temp.length());
                }else if (temp.length()>8){
                    huffcodesList.add(temp.substring(0,8));
                    temp.delete(0,9);
                }
            }
            else{
                for(char c: s.toCharArray()){
                    temp.append(lookupMap.get(String.valueOf(c)));
                    //
                    if (temp.length()==8){
                        huffcodesList.add(temp.toString());
                        temp.delete(0,temp.length());
                    }else if (temp.length()>8){
                        huffcodesList.add(temp.substring(0,8));
                        temp.delete(0,9);
                    }

                }
            }


        }
        if (!temp.isEmpty())
            huffcodesList.add(temp.toString());



        int length=huffcodesList.size();
//        Byte[] huffCodeBytes = new Byte[length];
        Byte[] huffCodeBytes=new Byte[length];
        int counter=0;
        int idx = 0;

        for (String x:huffcodesList){
            if (x==huffcodesList.get(huffcodesList.size()-1)){
                if (x.length()==8){
                    huffCodeBytes[idx]=(byte) Integer.parseInt(x,2);
                }else{
                    for (int j=0;j<x.length();j++){
                        if (x.charAt(j)=='0'){
                            counter++;
                        }else break;
                    }
                }
            }
            huffCodeBytes[idx++]= (byte) Integer.parseInt(x,2);
        }




        /////


//        StringBuilder sb = new StringBuilder();
//        StringBuilder sb2 = new StringBuilder();
//        int i = fileData.length;

        /////////here
//        int size=0;
//        for (String b : fileData){
//            if(lookupMap.containsKey(b)){
//                huffcodesList.add(lookupMap.get(b));
//                size+=lookupMap.get(b).length();
//            }
////                sb.append(lookupMap.get(b));
//            else{
//                for(char c: b.toCharArray()){
//                    huffcodesList.add(lookupMap.get(String.valueOf(c)));
//                    size+=lookupMap.get(String.valueOf(c)).length();
////                    sb.append(lookupMap.get(String.valueOf(c)));
//                }
//            }
//        }
//        int length=(huffcodesList.size()+7)/8;
////        Byte[] huffCodeBytes = new Byte[length];
//        Byte[] huffCodeBytes=new Byte[size];
//        int counter=0;
//        int idx = 0;
//        for (int i = 0; i < sb.length(); i += 8) {
//            String s;
//            if (i + 8 >= sb.length()){
//                s = sb.substring(i);
//                for (int j=0;j<s.length();j++){
//                    if (s.charAt(j)=='0'){
//                        counter++;
//                    }else break;
//                }
//
//            }
//            else s = sb.substring(i, i + 8);
//            huffCodeBytes[idx] = (byte) Integer.parseInt(s , 2);
//            idx++;
//        }
//
//
        ///yaha tak
        return new HuffmanData(huffCodeBytes, (byte) counter);
    }
}
