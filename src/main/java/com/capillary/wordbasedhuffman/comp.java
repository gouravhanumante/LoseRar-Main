package com.capillary.wordbasedhuffman;

import com.capillary.huffman.compressor.*;
import com.capillary.huffman.mydefines.HuffmanData;
import com.capillary.huffman.mydefines.Node;

import java.util.*;
import java.util.stream.Collectors;

public class comp {


     HuffmanData huffmanData;
//    HuffmanCompressionImpl c = new HuffmanCompressionImpl();
    IWriteData c=new WriteDataImpl();

    public void compressData(   ) {


        IReadData rw = new ReadDataImpl();
        ITreeCreationUtils treeCreationUtils = new TreeCreationUtils();


        Byte[] fileData = rw.read("/Users/gouravhanumante/IdeaProjects/Test Files/test.txt");



        String destination="/Users/gouravhanumante/IdeaProjects/Test Files/isko.txt";


        Map<String,String> temp=new HashMap<>();
        if (fileData.length==0){
            huffmanData =new HuffmanData( fileData, (byte) 0);
            c.write(destination, huffmanData,temp);
            return;
        }



        //          Byte[] fileData = rw.read("/Users/gouravhanumante/IdeaProjects/Test Files/test.txt");
//        String arr[]=
//
        String words[] = createWordsArray(fileData);


        Map<String, Integer> mp = (Map<String, Integer>) treeCreationUtils.createFrequencyMap(words);
        mp = sortMapByValue((HashMap<String, Integer>) mp);
        //
        Map<String, Integer> f15 = createXPercentMap(mp);
        //        System.out.println(f15);
//
        Map<String, Integer> final15 = createFinalMap(words, f15);
        //
////        System.out.println("here"+final15);
        String[] finalFileData = getFinalFileData(words, final15);
        ////        System.out.printf(Arrays.toString(finalFileData));
//
////        System.out.println(final15);
//
        Node root = treeCreationUtils.createTreeUsingMinHeap(final15);
        ////
////
        ICompressionUtils compressionUtils = new CompressionUtils();
        Map<String, String> huffcodes = compressionUtils.buildLookupRecursive(root);
        ////
////
        HuffmanData data = compressionUtils.createCompressedArray(finalFileData, huffcodes);
//huffcodes//        System.out.println(Arrays.toString(data.getHuffmanByte()));
//
////        5.4
//        2.2
        //60%

///Users/gouravhanumante/IdeaProjects/Test Files/test.txt



        c.write("/Users/gouravhanumante/IdeaProjects/Test Files/isko.txt", data, huffcodes);
//
//


    }


    private static String[] getFinalFileData(String[] words, Map<String, Integer> final15) {
        ArrayList<String> ans = new ArrayList<>();
        for (String s : words) {
            if (!final15.containsKey(s)) {
                for (char c : s.toCharArray()) {
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

        Map<String, Integer> main = new HashMap<>();
//        System.out.println(f15);
        for (String s : words) {
            if (!f15.containsKey(s)) {
                for (char c : s.toCharArray()) {
                    if (main.containsKey(String.valueOf(c))) {
                        main.put(String.valueOf(c), main.get(String.valueOf(c)) + 1);
                    } else {
                        main.put(String.valueOf(c), 1);
                    }
                }
            }
        }
        for (Map.Entry<String, Integer> entry : f15.entrySet()) {
            main.put(entry.getKey(), entry.getValue());
        }
        return main;
    }

    private static Map<String, Integer> createXPercentMap(Map<String, Integer> mp) {
        int sizeForCreation = (int) (mp.size() * .10);
        int i = 0;

        Map<String, Integer> result = new HashMap<>();
        for (Map.Entry<String, Integer> entry : mp.entrySet()) {
            if (i >= sizeForCreation) break;
            result.put(entry.getKey(), entry.getValue());
            i++;
        }
        return result;


    }

    static String[] createWordsArray(Byte[] fileData) {
        int j=0;

        ArrayList<String> words = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (byte b : fileData) {

            if ((b <= 90 && b >= 65) || (b <= 122 && b >= 97)) {
                sb.append((char) b);
            } else {
                if (sb.length() != 0)
                    words.add(sb.toString());

                sb.delete(0, sb.length());
                words.add(String.valueOf((char) b));
            }

        }
        if (sb.length()!=0) words.add(sb.toString());

        String ans[] = new String[words.size()];
        int k = 0;
        for (String i : words) {
            ans[k++] = i;
        }
        return ans;

    }


    public static Map<String, Integer> sortMapByValue(HashMap<String, Integer> hm) {

        HashMap<String, Integer> temp = hm.entrySet().stream().sorted((i2, i1) -> i1.getValue().compareTo(i2.getValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return temp;

    }

}