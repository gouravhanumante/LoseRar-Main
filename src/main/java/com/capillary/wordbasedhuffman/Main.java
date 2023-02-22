package com.capillary.wordbasedhuffman;

import com.capillary.IZipper;
import com.capillary.huffman.compressor.*;
import com.capillary.huffman.decompressor.HuffmanDecompressionImpl;
import com.capillary.huffman.mydefines.HuffmanData;
import com.capillary.huffman.mydefines.Node;
import com.capillary.mixedhuffman.MixedZipperImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) throws IOException {


        IZipper zipper=new MixedZipperImpl();
        long val1=System.currentTimeMillis();
        zipper.compression("/home/sarthakjain/IdeaProjects/files/input.txt","/home/sarthakjain/IdeaProjects/files/compressed.txt");
        long val2=System.currentTimeMillis();
        zipper.decompression("/home/sarthakjain/IdeaProjects/files/compressed.txt","/home/sarthakjain/IdeaProjects/files/decompressed.txt");
        long val3=System.currentTimeMillis();
        System.out.println("compression: " +(val2-val1));
        System.out.println("decompression: "+(val3-val2));






//
//        File file1 = new File("/Users/gouravhanumante/IdeaProjects/Test Files/input.txt");
//        File file2 = new File("/Users/gouravhanumante/IdeaProjects/Test Files/inputdecompressed.txt");
////        boolean isTwoEqual = Files(file1, file2);
////        Files.mismatch(file1.toPath(), file2.toPath());
//        System.out.println(Files.mismatch(file1.toPath(), file2.toPath()));

    }

}


