package com.capillary.huffman.decompressor;

import java.util.*;

public class DecompressionUtilsImpl implements IDecompressionUtils{
    @Override
    public byte[] decompress(byte[] huffmanBytes, Map<String, String> lookupMap, byte counter) {

//        System.out.println(Arrays.toString(huffmanBytes));
        long v1=System.currentTimeMillis();
        String s=convertBytetoBitString(huffmanBytes,counter);
        long v2=System.currentTimeMillis();
        System.out.println(v2-v1+"convertBytetoBitString");

        Map<String,String>  map=new HashMap<>();

        for (Map.Entry<String,String> entry:lookupMap.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }
        v1=System.currentTimeMillis();
        System.out.println(v1-v2+"map ulta kiya");

        int size=0;


        List<String> list=new ArrayList<>();
        String curr="";

        for (int i=0;i<s.length();i++){
            curr+=s.charAt(i);
            if (map.containsKey(curr)){
                list.add(map.get(curr));
                size+=map.get(curr).length();
                curr="";
            }
        }

        System.out.println(size);
        byte[] ans=new byte[size];
        int idx=0;
        for(String x:list){
            for (char y:x.toCharArray()){
                ans[idx++]=(byte) y;
            }
        }

//        for (int i=0;i<s.length();){
//            int count=1;
//            boolean flag=true;
//            String b=null;
//
//            while (flag){
//                String key=s.substring(i,i+count);
//                b=map.get(key);
//                if (b==null){
//                    count++;
//                }else{
//                    flag=false;
//                }
//            }
//            list.add(b);
//            i+=count;
//        }

//        v2=System.currentTimeMillis();
//        System.out.println(v2-v1+"huffman codes se original array banana");

        /////list string
//        System.out.println(list.get(0).getClass().getSimpleName());
//        List<Character> listc=new ArrayList<>();
//
//            for (int i=0;i<list.size();i++){
//                String val= list.get(i);
//                for (char c:val.toCharArray()){
//                    listc.add(c);
//                }
//            }
//            byte [] ans=new byte[listc.size()];
//            int j=0;
//            for (char x:listc){
//                ans[j++]= (byte) x;
//            }
//
//            v1=System.currentTimeMillis();
//        System.out.println(v1-v2+" converting list to byte array");
            return ans;






        //list


    }



    public String convertBytetoBitString(byte[] bytes, byte counter) {
long t1=System.currentTimeMillis();
        System.out.println();
        StringBuilder sb= new StringBuilder();
        int i=0;
        for (i=0;i<bytes.length-1;i++){
//            System.out.println("check:"+i);
            //playground
            sb.append(Integer.toBinaryString((bytes[i] & 0xFF)+0x100).substring(1));


            //
//            sb.append(String.format("%8s", Integer.toBinaryString(bytes[i] & 0xFF)).replace(' ', '0'));
        }
        long t2=System.currentTimeMillis();
        System.out.println(t2-t1+"part 1");
        String str0 = Integer.toBinaryString(bytes[i]);
        System.out.println("str0"+str0);

        //conditon when str0 is already 0 and counter is not zero
        if (str0.equals("0")) {
            counter--;
        }
        //


        if (counter!=0) {

            while (counter != 0) {
                str0 = "0" + str0;
                counter--;
            }
            sb.append(str0);
            return sb.toString();
        }

        if (bytes[i] < 0){
            sb.append(str0.substring(str0.length() - 8));
        }
        else {
            sb.append(str0);
        }
        return sb.toString();
    }
}
