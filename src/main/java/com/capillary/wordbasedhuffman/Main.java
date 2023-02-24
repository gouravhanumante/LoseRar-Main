package com.capillary.wordbasedhuffman;

import com.capillary.IZipper;
import com.capillary.mixedhuffman.MixedZipperImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;


public class Main {

    public static void main(String[] args) throws IOException {
        Map<String ,Integer> mp1=new HashMap<>();
        Map<String ,Integer> mp2=new HashMap<>();



//        mp2.merge(mp1,mp2);

        System.out.println(mp2);

        String gsource="/Users/gouravhanumante/IdeaProjects/Test Files/orgFile.txt";
        String gcompressed="/Users/gouravhanumante/IdeaProjects/Test Files/compressedTest.txt";
        String gdestination="/Users/gouravhanumante/IdeaProjects/Test Files/result.txt";

//        String gsource="/home/sarthakjain/IdeaProjects/files/input.txt";
//        String gcompressed="/home/sarthakjain/IdeaProjects/files/compressedInputfile.txt";
//        String gdestination="/home/sarthakjain/IdeaProjects/files/decompressedInput.txt";

        IZipper zipper=new MixedZipperImpl();
        long val1=System.currentTimeMillis();


//        zipper.compression(gsource,gcompressed);
        long val2=System.currentTimeMillis();


//        zipper.decompression(gcompressed,gdestination);
        long val3=System.currentTimeMillis();
        System.out.println("compression: " +(val2-val1));
        System.out.println("decompression: "+(val3-val2));


        File file1 = new File(gsource);
        File file2 = new File(gdestination);
        System.out.println(Files.mismatch(file1.toPath(), file2.toPath()));

    }

}


