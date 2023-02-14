package com.capillary.huffman.compressor;

import com.capillary.huffman.mydefines.Container;

import java.io.*;
import java.util.Map;

public class ReadAndWriteImpl implements IReadAndWrite{

    @Override
    public byte[] read(String source) {
        try {
            FileInputStream inStream=new FileInputStream(source);
            byte[] b=new byte[inStream.available()];
            inStream.read(b);
            inStream.close();
            return b;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new byte[0];//CLTAE
    }

    @Override
    public void write(String destination, Container huffmanContainer, Map<Byte,String> lookupMap) {
        try {
            OutputStream oStream=new FileOutputStream(destination);
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(oStream);

            if (huffmanContainer.getHuffmanByte().length==0){

                byte []x=new byte[1];
                x[0]=-1;

                objectOutputStream.writeObject(x);
                oStream.close();
                objectOutputStream.close();
                return;
            }


            objectOutputStream.writeObject(huffmanContainer.getHuffmanByte());
            objectOutputStream.writeObject(lookupMap);
            objectOutputStream.writeObject(huffmanContainer.getCounter());

            oStream.close();
            objectOutputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
