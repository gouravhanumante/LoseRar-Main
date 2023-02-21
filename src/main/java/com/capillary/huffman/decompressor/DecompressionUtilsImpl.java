package com.capillary.huffman.decompressor;

import java.util.*;

public class DecompressionUtilsImpl implements IDecompressionUtils{
    @Override
    public <T> byte[] decompress(byte[] huffmanBytes, Map<T, String> lookupMap, byte counter) {

        System.out.println(Arrays.toString(huffmanBytes));
        String s=convertBytetoBitString(huffmanBytes,counter);

        Map<String,T>  map=new HashMap<>();

        for (Map.Entry<T,String> entry:lookupMap.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }


        List<T> list=new ArrayList<>();

        for (int i=0;i<s.length();){
            int count=1;
            boolean flag=true;
            T b=null;
            while (flag){
                String key=s.substring(i,i+count);
                b=map.get(key);
                if (b==null){
                    count++;
                }else{
                    flag=false;
                }
            }
            list.add(b);
            i+=count;
        }

        /////list string
//        System.out.println(list.get(0).getClass().getSimpleName());
        List<Character> listc=new ArrayList<>();
        if(list.get(0).getClass().getSimpleName().equals("String")) {
//            System.out.println("hi");
            for (int i=0;i<list.size();i++){
                String val= (String) list.get(i);
                for (char c:val.toCharArray()){
                    listc.add(c);
                }
            }
            byte [] ans=new byte[listc.size()];
            int j=0;
            for (char x:listc){
                ans[j++]= (byte) x;
            }

            return ans;
        }else{

            byte[] result=  new byte[list.size()];
            for ( int i=0;i<result.length;i++){
                result[i]= (byte) list.get(i);
            }
            return result;
        }





        //list


    }

    public String convertBytetoBitString(byte[] bytes, byte counter) {
        StringBuilder sb=new StringBuilder();
        int i=0;
        for (i=0;i<bytes.length-1;i++){
            sb.append(String.format("%8s", Integer.toBinaryString(bytes[i] & 0xFF)).replace(' ', '0'));
        }
        String str0 = Integer.toBinaryString(bytes[i]);

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
