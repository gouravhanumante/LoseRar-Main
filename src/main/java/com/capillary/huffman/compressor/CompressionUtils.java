package com.capillary.huffman.compressor;

import com.capillary.huffman.mydefines.HuffmanData;
import com.capillary.huffman.mydefines.Node;

import java.util.*;

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


 public static int getListLength(Map<String,String> lm){
        int var=0;
        for (Map.Entry<String,String> entry:lm.entrySet()){
            var+=entry.getValue().length();
        }
        return (var+7)/8;
 }



    public HuffmanData createCompressedArray(String[] fileData, Map<String , String> lookupMap) {

//        int size=getListLength(lookupMap);
        List<Byte> compressedArray=new ArrayList<>();

        String str = "",curr="";
        int idx=0,counter=0;

        for (String s:fileData){
            if (lookupMap.containsKey(s)){
                str+=lookupMap.get(s);
            }else {
                for (char c:s.toCharArray()){
                    str+=lookupMap.get(String.valueOf(c));
                }
            }


//            if (idx==size-1 && str.length()<8){
//                int j=0;
//                for (j=0;j<str.length();j++){
//                    if (str.charAt(j)=='0'){
//                        counter++;
//                    }
//                }
//            }
            while (str.length()>=8){
                curr=str.substring(0,8);
                str=str.substring(8);
                compressedArray.add((byte)Integer.parseInt(curr,2));
            }
        }
        if (!str.isEmpty()){
            while (str.length()>=8){
                curr=str.substring(0,8);
                str=str.substring(8);
                compressedArray.add((byte)Integer.parseInt(curr,2));
            }

            if (str.length()<8){
                int j=0;
                for (j=0;j<str.length();j++){
                    if (str.charAt(j)=='0'){
                        counter++;
                    }else break;
                }
                compressedArray.add((byte)Integer.parseInt(str,2));
            }

        }

        Byte[] ccccc=new Byte[compressedArray.size()];
        int iz=0;
        for (Byte i:compressedArray){
            ccccc[iz++]=i;

        }
//        System.out.println(Arrays.toString(ccccc));
        return new HuffmanData(ccccc, (byte) counter);
    }

//
//    01010101 0
    public HuffmanData c(String[] fileData, Map<String , String> lookupMap) {
        /////
        int size=getListLength(lookupMap);
//        System.out.println(size);
//        ArrayList<Byte> huffcodesList=new ArrayList<String>(size);

        Byte[] huffCodeBytes = new Byte[size];
        String curr="",str="";




        int it=0;


        StringBuilder temp=new StringBuilder();
        int counter=0;
        for (String s:fileData){
            if(lookupMap.containsKey(s)){
                str+=lookupMap.get(s);
//                ///
//                if (temp.length()>=8){
//                    while (temp.length()>=8){
//                        huffcodesList.add(temp.toString().substring(0,8));
////
//                        temp.delete(0,8);
//                    }
//                }




            }
            else{
                for(char c: s.toCharArray()){
                    temp.append(lookupMap.get(Character.toString(c)));
                    //
                    str+=lookupMap.get(String.valueOf(c));

//                    if (temp.length()>=8){
//                        while (temp.length()>=8){
//                            huffcodesList.add(temp.toString().substring(0,8));
////                            it++;
//                            temp.delete(0,8);
//                        }
////                        temp = new StringBuilder(temp.substring(8));
//                    }

                }
            }



            if (it==size-1) {
                if (str.length() <= 8) {
                    int j = 0;
                    for (j = 0; j < str.length(); j++) {
                        if (str.charAt(j) == '0') {
                            counter++;
                        } else {
                            break;
                        }
                    }
                }
            }

            while (str.length()>8) {


                curr = str.substring(0, 8);
                str = str.substring(8);
                huffCodeBytes[it++] = (byte) Integer.parseInt(curr, 2);

            }
        }
        if (!str.isEmpty()){
            if (it==size-1) {
                if (str.length() <= 8) {
                    int j = 0;
                    for (j = 0; j < str.length(); j++) {
                        if (str.charAt(j) == '0') {
                            counter++;
                        } else {
                            break;
                        }
                    }
                }
            }
            while (str.length()>8){
                curr=str.substring(0,8);
                str=str.substring(8);
                huffCodeBytes[it++]=(byte)Integer.parseInt(curr,2);
            }
        }


        while (str.length()>8){
            curr=str.substring(0,8);
            str=str.substring(8);
            huffCodeBytes[it++]=(byte)Integer.parseInt(curr,2);
        }
//        if (!temp.isEmpty()){
//
//            if (temp.length()>8){
//                while (temp.length()>8){
//                    huffcodesList.add(temp.toString().substring(0,8));
//                    temp.delete(0,8);
////                    it++;
//                }
//            }else{
//                huffcodesList.add(temp.toString());
////                it++;
//            }
//
//        }
//
//
//
//
//        int length=huffcodesList.size();
////        Byte[] huffCodeBytes = new Byte[length];
//        Byte[] huffCodeBytes=new Byte[length];
//        int counter=0;
//        int idx = 0;
//
//        for (String x:huffcodesList){
//            if (x==huffcodesList.get(huffcodesList.size()-1)){
//                if (x.length()==8){
//                    huffCodeBytes[idx]=(byte) Integer.parseInt(x,2);
//                }else{
//                    for (int j=0;j<x.length();j++){
//                        if (x.charAt(j)=='0'){
//                            counter++;
//                        }else break;
//                    }
//                }
//            }
//            huffCodeBytes[idx++]= (byte) Integer.parseInt(x,2);
//        }




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
