package com.capillary.mixedhuffman.compressor;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GenerateHash {
    String getHashValue(Byte[] fileData) throws NoSuchAlgorithmException {
        byte[] data = new byte[fileData.length];
        int idx = 0;
        for(byte b: fileData){
            data[idx++] = b;
        }
        byte[] hash = MessageDigest.getInstance("MD5").digest(data);
        String checkSum = new BigInteger(1, hash).toString(16);
        return checkSum;
    }
}
