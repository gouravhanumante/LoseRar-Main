package com.capillary.huffman.compressor;

import java.io.*;
import java.util.Arrays;

public class ReadDataImpl implements IReadData {

    @Override
    public Byte[] read(String source) {
        try {
            FileInputStream inStream = new FileInputStream(source);
            byte[] b = new byte[inStream.available()];
            inStream.read(b);
            inStream.close();
            Byte []bb= new Byte[b.length];
            Arrays.setAll(bb, n->b[n]);
            return bb;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Byte[0];
    }


}