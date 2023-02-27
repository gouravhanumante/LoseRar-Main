package com.capillary.mixedhuffman.compressor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

public class Helper {


    public int getMapSize(Map<String, Integer> mp) {

        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteOutputStream);
            objectOutputStream.writeObject(mp);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return byteOutputStream.toByteArray().length;
    }

    public long getSize(Map<String, Integer> freqMap, Map<String, String> huffmanCodes) {
        long size=0;
        for(Map.Entry<String,Integer> ele : freqMap.entrySet()){
            size+=huffmanCodes.get(ele.getKey()).length()*ele.getValue();
        }


        return (long) Math.ceil(size/8.0);

    }

}
