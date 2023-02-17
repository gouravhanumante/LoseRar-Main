package com.capillary.wordbasedhuffman;

import com.capillary.huffman.compressor.*;
import com.capillary.huffman.mydefines.HuffmanData;
import com.capillary.huffman.mydefines.Node;

import java.util.*;
import java.util.stream.Collectors;


public class Main {


    public static void main(String[] args) {
        IReadData rw=new ReadDataImpl();
        ITreeCreationUtils treeCreationUtils=new TreeCreationUtils();



        Byte[] fileData= rw.read("/home/gauravhanumante/Files2/input.txt");

//        String arr[]=

        String words[]= createWordsArray(fileData);
       Map<String,Integer> mp= (Map<String, Integer>) treeCreationUtils.createFrequencyMap(words);
        mp= sortByValue((HashMap<String, Integer>) mp);

        Map<String,Integer> f15=create15PercentMap(mp);
//        System.out.println(f15);

        Map<String,Integer> final15=createFinalMap(words, f15);

//        System.out.println("here"+final15);
        String[] finalFileData = getFinalFileData(words, final15);
//        System.out.printf(Arrays.toString(finalFileData));

//        System.out.println(final15);

        Node root=treeCreationUtils.createTreeUsingMinHeap(final15);
//
//
        ICompressionUtils compressionUtils=new CompressionUtils();
        Map<String,String> huffcodes= compressionUtils.buildLookupRecursive(root);
//
//
        HuffmanData data= compressionUtils.createCompressedArray(finalFileData,huffcodes);
//        System.out.println(Arrays.toString(data.getHuffmanByte()));

//        5.4
//        2.2
        //60%




        HuffmanCompressionImpl c=new HuffmanCompressionImpl();

        c.write("/home/gauravhanumante/Files2/ir3.txt",data,huffcodes);

        ///compression ends here

//        dec d=new dec();
//        d.decompress("/home/gauravhanumante/Files2/decompressthisfile.txt");
//
//
//
//        System.out.println(mp);



        ///
        //we gt the 15% map
        //words //nextFullCharacter //check if it is presentin m15
        // if present do nothing else read character by character
        // create tree using m15
        // create huffcodes  hmp
        // write String (-byte-) arr and hmp
        //


        //decompression
//        read data
//        exchange key and value of hmp
//        decode


        //
        //
        //



    }

    private static String[] getFinalFileData(String[] words, Map<String, Integer> final15) {
        ArrayList<String> ans = new ArrayList<>();
        for(String s: words){
            if(!final15.containsKey(s)){
                for(char c: s.toCharArray()){
                    ans.add(String.valueOf(c));
                }
            } else {
                ans.add(s);
            }
        }
        return ans.toArray(ans.toArray(new String[ans.size()]));
//        String[] res = new String[ans.size()];
//        int i=0;
//        for(String s: ans){


    }

    private static Map<String, Integer> createFinalMap(String[] words, Map<String, Integer> f15) {

        Map<String,Integer> main=new HashMap<>();
//        System.out.println(f15);
        for (String s: words){
            if (!f15.containsKey(s)){
                for (char c:s.toCharArray()){
                    if (main.containsKey(String.valueOf(c))){
                        main.put(String.valueOf(c),main.get(String.valueOf(c))+1);
                    }else{
                        main.put(String.valueOf(c),1);
                    }
                }
            }
            for (Map.Entry<String,Integer> entry:f15.entrySet()){
                main.put(entry.getKey(),entry.getValue());
            }
        }
        return main;
    }

    private static Map<String, Integer> create15PercentMap(Map<String, Integer> mp) {
        int sizeForCreation= (int) (mp.size()*.3);
        int i=0;

        Map<String,Integer> result=new HashMap<>();
        for (Map.Entry<String,Integer> entry:mp.entrySet()){
            if (i>=sizeForCreation) break;
            result.put(entry.getKey(),entry.getValue());
            i++;
        }
        return result;


    }

    static String[] createWordsArray(Byte[] fileData){
        byte []bytes=new byte[fileData.length];
        int j=0;
        for (Byte x:fileData){
            bytes[j++]=x;
        }

        ArrayList<String> words=new ArrayList<>();

        StringBuilder sb=new StringBuilder();
        for (byte b:bytes){

            if ((b<=90 && b>=65) || (b<=122 && b>=97) ){
                sb.append((char) b);
            }else{
                if (sb.length()!=0)
                    words.add(sb.toString());

                sb.delete(0,sb.length());
                words.add(String.valueOf((char) b));
            }

        }

        String ans[]=new String[words.size()];
        int k=0;
        for (String i:words){
            ans[k++]=i;
        }
return ans;

    }



    public static Map<String, Integer>  sortByValue(HashMap<String, Integer> hm)    {

        HashMap<String,Integer> temp=hm.entrySet().stream().sorted((i2,i1)->i1.getValue().compareTo(i2.getValue())).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2)-> e1,LinkedHashMap::new ));
        return temp;
//
//        HashMap<String, Integer> temp
//
//                = hm.entrySet()
//
//                .stream()
//
//                .sorted((i1, i2)
//
//                        -> i1.getValue().compareTo(
//
//                        i2.getValue()))
//
//                .collect(Collectors.toMap(
//
//                        Map.Entry::getKey,
//
//                        Map.Entry::getValue,
//
//                        (e1, e2) -> e1, LinkedHashMap::new));


    }
}


