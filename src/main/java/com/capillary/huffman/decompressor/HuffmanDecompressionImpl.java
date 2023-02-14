package com.capillary.huffman.decompressor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.util.Map;
public class HuffmanDecompressionImpl implements IDecompressor{
    @Override
    public void decompress(String source, String destination) {
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
            Map<Byte,String> lookupMap= (Map<Byte, String>) objectInputStream.readObject();
            byte counter=(byte) objectInputStream.readObject();

            byte[] finalRes=getDecompressedData(huffmanBytes,lookupMap,counter);

            oStream.write(finalRes);

            objectInputStream.close();
            oStream.close();
            iStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public byte[] getDecompressedData(byte[] huffmanBytes, Map<Byte, String> lookupMap, byte counter) {
        IDecompressionUtils util=new DecompressionUtilsImpl();
        byte[] decodedData=util.decompress(huffmanBytes,lookupMap,counter);
        return decodedData;
    }
}
