package com.capillary.huffman.compressor;

import java.io.*;

public class ReadDataImpl implements IReadData {

    @Override
    public byte[] read(String source) {
        try {
            FileInputStream inStream = new FileInputStream(source);
            byte[] b = new byte[inStream.available()];
            inStream.read(b);
            inStream.close();
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];//CLTAE
    }


}