package com.capillary.mixedhuffman.compressor;

import com.capillary.huffman.compressor.CompressionUtils;
import com.capillary.huffman.compressor.ICompressionUtils;
import com.capillary.huffman.compressor.ITreeCreationUtils;
import com.capillary.huffman.compressor.TreeCreationUtils;
import com.capillary.huffman.mydefines.HuffmanData;
import com.capillary.huffman.mydefines.Node;

import java.util.ArrayList;
import java.util.Map;

public class MixedCreationUtils {
    double calculate(Map<String,String> mp,Map<String ,Integer> mp2){
        int asl=0;
        int f=0;
        for (Map.Entry<String,Integer> entry: mp2.entrySet()){
            f+=entry.getValue();
            int len=mp.get(entry.getKey()).length();
            asl+=len*entry.getValue();
        }
        return (1.0*asl)/f;
    }
    public String[] getFinalFileData(String[] words, Map<String, Integer> mp) {
//        System.out.println(mp.size());
//        System.out.println(mp);
        ArrayList<String> ans = new ArrayList<>();
        for (String s : words) {
            if (!mp.containsKey(s)) {
                for (char c : s.toCharArray()) {
                    ans.add(String.valueOf(c));
                }
            } else {
                ans.add(s);
            }
        }
        return ans.toArray(ans.toArray(new String[ans.size()]));
    }

    public String[] createWordsArray(Byte[] fileData) {
        int j=0;

        ArrayList<String> words = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (byte b : fileData) {
            if ((b <= 90 && b >= 65) || (b <= 122 && b >= 97)) {
//            if(Character.isLetter((char) b)){
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


//    HuffmanData multithreading(String []words,Map<String,Integer> mp){
//        double p1=.2;
//        double p2=.3;
//        double p3=.4;
//
//        IMapCreationUtils mapCreationUtils=new MapCreationUtilsImpl();
//        //objects
//        MixedCreationUtils utils=new MixedCreationUtils();
//        ITreeCreationUtils treeCreationUtils=new TreeCreationUtils();
//        ICompressionUtils compressionUtils=new CompressionUtils();
//
//
//        //
//
//        Map<String,Integer> mp1=mapCreationUtils.createFinalFrequencyMap(words,mp,p1);
//        Map<String,Integer> mp2=mapCreationUtils.createFinalFrequencyMap(words,mp,p2);
//        Map<String,Integer> mp3=mapCreationUtils.createFinalFrequencyMap(words,mp,p3);
//
//
//        //p3
//        String[] finalFileData1=utils.getFinalFileData(words,mp1);
//        String[] finalFileData2=utils.getFinalFileData(words,mp2);
//        String[] finalFileData3=utils.getFinalFileData(words,mp3);
//
//
//
//        //
//
//        Node root1=treeCreationUtils.createTreeUsingMinHeap(mp1);
//        Node root2=treeCreationUtils.createTreeUsingMinHeap(mp2);
//        Node root3=treeCreationUtils.createTreeUsingMinHeap(mp3);
//
//        //
//
//        Map<String,String> h1=compressionUtils.buildLookupRecursive(root1);
//        Map<String,String> h2=compressionUtils.buildLookupRecursive(root2);
//        Map<String,String> h3=compressionUtils.buildLookupRecursive(root3);
//
//        int sum1=cal(h1,mp1);
//        int sum2=cal(h2,mp2);
//        int sum3=cal(h3,mp3);
//
//        System.out.println("sum 1:"+sum1);
//        System.out.println("sum 2:"+sum2);
//        System.out.println("sum 3:"+sum3);
//
//        int max=Math.min(Math.min(sum1,sum2),sum3);
//        if (max==sum1){
//            return compressionUtils.createCompressedArray(finalFileData1,h1);
//
//        }
//        if (max==sum2) {
//            return compressionUtils.createCompressedArray(finalFileData2,h2);
//        }
//        if (max==sum3) {
//            return compressionUtils.createCompressedArray(finalFileData3,h3);
//        }
//
//        return null;
//
////    }
//    HuffmanData multithreading(String []words,Map<String,Integer> mp,double per){
//
//
//
//        IMapCreationUtils mapCreationUtils=new MapCreationUtilsImpl();
//        MixedCreationUtils utils=new MixedCreationUtils();
//        ITreeCreationUtils treeCreationUtils=new TreeCreationUtils();
//        ICompressionUtils compressionUtils=new CompressionUtils();
//
//
//        //
//
////        Map<String,Integer> mp1=mapCreationUtils.createFinalFrequencyMap(words,mp,per);
//
//
//        //p3
//        String[] finalFileData1=utils.getFinalFileData(words,mp);
//
////        System.out.println("Created final file data");
//
//        //
//
//        Node root1=treeCreationUtils.createTreeUsingMinHeap(mp);
////        System.out.println("Created tree");
//
//
//        //
//
//        Map<String,String> h1=compressionUtils.buildLookupRecursive(root1);
////        System.out.println("Created huffmap");
//
////        int sum1=cal(h1,mp);
//
////        System.out.println(sum1);
//
////
////        int max=Math.min(Math.min(sum1,sum2),sum3);
////        if (max==sum1){
////            return compressionUtils.createCompressedArray(finalFileData1,h1);
////
////        }
////        if (max==sum2) {
////            return compressionUtils.createCompressedArray(finalFileData2,h2);
////        }
////        if (max==sum3) {
////            return compressionUtils.createCompressedArray(finalFileData3,h3);
////        }
//
//        return compressionUtils.createCompressedArray(finalFileData1,h1);
//
//    }
//
//    int cal(Map<String,String> huffcode,Map<String,Integer> frequency){
//        int sum=0;
//
//        for (Map.Entry<String,String> entry:huffcode.entrySet()){
//            sum+=frequency.get(entry.getKey())*entry.getValue().length();
//        }
//
//        return sum;
//    }


}
