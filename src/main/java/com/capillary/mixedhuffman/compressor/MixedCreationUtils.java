package com.capillary.mixedhuffman.compressor;

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




}
