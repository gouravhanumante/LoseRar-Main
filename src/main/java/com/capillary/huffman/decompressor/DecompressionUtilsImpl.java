package com.capillary.huffman.decompressor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DecompressionUtilsImpl implements IDecompressionUtils{
    @Override
    public byte[] decompress(byte[] huffmanBytes, Map<Byte, String> lookupMap, byte counter) {

        String s=convertBytetoBitString(huffmanBytes,counter);

        Map<String,Byte>  map=new HashMap<>();

        for (Map.Entry<Byte,String> entry:lookupMap.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }


        List<Byte> list=new ArrayList<>();

        for (int i=0;i<s.length();){
            int count=1;
            boolean flag=true;
            Byte b=null;
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


        byte[] result=new byte[list.size()];
        for ( int i=0;i<result.length;i++){
            result[i]=list.get(i);
        }
        return result;
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
