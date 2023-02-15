package com.capillary.huffman.decompressor;

import java.io.*;
import java.util.Arrays;
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
    public void decompress(String source, String destination) {
        try {
            FileInputStream iStream=new FileInputStream(source);
            ObjectInputStream objectInputStream=new ObjectInputStream(iStream);

            OutputStream oStream=new FileOutputStream(destination);
//            ObjectOutputStream objectOutputStream=new ObjectOutputStream(oStream);

            Byte[] huffmanBytes= (Byte[]) objectInputStream.readObject();
//            System.out.println(Arrays.toString(huffmanBytes));

            if (huffmanBytes.length==1 && huffmanBytes[0]==-1){
                oStream.write(0);
                objectInputStream.close();
                oStream.close();
                iStream.close();
                return;
            }

            Map<Byte,String> lookupMap= (Map<Byte, String>) objectInputStream.readObject();
            byte counter=(byte) objectInputStream.readObject();



//            byte[] finalRes=getDecompressedData(huffmanBytes,lookupMap,counter);

            Byte[] finalRes=util.decompress(huffmanBytes,lookupMap,counter);

//            System.out.println(Arrays.toString(finalRes));

            byte[] fr=new byte[finalRes.length];
            int i=0;
            for (Byte b:finalRes){
                fr[i++]=b;
            }



//            objectOutputStream.writeObject(finalRes);
            oStream.write(fr);

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
