package com.capillary.wordbasedhuffman;

import com.capillary.IZipper;
import com.capillary.mixedhuffman.MixedZipperImpl;

import java.io.IOException;



public class Main {

    public static void main(String[] args) throws IOException {



        String gsource="/Users/gouravhanumante/IdeaProjects/Test Files/input.txt";
        String gcompressed="/Users/gouravhanumante/IdeaProjects/Test Files/compressedTest.txt";
        String gdestination="/Users/gouravhanumante/IdeaProjects/Test Files/result.txt";

        IZipper zipper=new MixedZipperImpl();
        long val1=System.currentTimeMillis();
        zipper.compression(gsource,gcompressed);
        long val2=System.currentTimeMillis();
        zipper.decompression(gcompressed,gdestination);
        long val3=System.currentTimeMillis();
        System.out.println("compression: " +(val2-val1));
        System.out.println("decompression: "+(val3-val2));


//        File file1 = new File("/Users/gouravhanumante/IdeaProjects/Test Files/input.txt");
//        File file2 = new File("/Users/gouravhanumante/IdeaProjects/Test Files/inputdecompressed.txt");
////        boolean isTwoEqual = Files(file1, file2);
////        Files.mismatch(file1.toPath(), file2.toPath());
//        System.out.println(Files.mismatch(file1.toPath(), file2.toPath()));

    }

}


