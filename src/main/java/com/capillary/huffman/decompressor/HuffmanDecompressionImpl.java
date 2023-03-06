package com.capillary.huffman.decompressor;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public class HuffmanDecompressionImpl implements IDecompressor{
    IDecompressionUtils util;

    public HuffmanDecompressionImpl(IDecompressionUtils util) {
        this.util = util;
    }
    public  HuffmanDecompressionImpl(){
        this.util=new DecompressionUtilsImpl();
    }

    @Override
    public <T> void decompress(String source, String destination) {
        try {
            FileInputStream iStream=new FileInputStream(source);
            ObjectInputStream objectInputStream=new ObjectInputStream(iStream);

            OutputStream oStream=new FileOutputStream(destination);

            byte[] huffmanBytes= (byte[]) objectInputStream.readObject();

            if (huffmanBytes.length==1 && huffmanBytes[0]==-1){
                oStream.write(0);
                objectInputStream.close();
                oStream.close();
                iStream.close();
                return;
            }


            Map<String,Integer> freqMap = (Map<String, Integer>) objectInputStream.readObject();

            byte counter=(byte) objectInputStream.readObject();



//            byte[] finalRes=getDecompressedData(huffmanBytes,lookupMap,counter);
            long val1=System.currentTimeMillis();
            byte[] finalRes= util.decompress(huffmanBytes,freqMap,counter);
            long val2=System.currentTimeMillis();
            System.out.println("decompress: "+(val2-val1));
//            System.out.println(Arrays.toString(finalRes));

//            System.out.println(Arrays.toString(finalRes));



//            T[] fr=new T[finalRes.length];
//            int i=0;
//            for (T b:finalRes){
//                fr[i++]=b;
//            }


//            System.out.println(Arrays.toString(finalRes));
            oStream.write(finalRes);
//            o
//            objectOutputStream.close();
            objectInputStream.close();
            oStream.close();
            iStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
//    public byte[] getDecompressedData(byte[] huffmanBytes, Map<Byte, String> lookupMap, byte counter) {
//        IDecompressionUtils util=new DecompressionUtilsImpl();
//        byte[] decodedData=util.decompress(huffmanBytes,lookupMap,counter);
//        return decodedData;
//    }
}
