package com.capillary.wordbasedhuffman;

import com.capillary.huffman.compressor.*;
import com.capillary.huffman.decompressor.HuffmanDecompressionImpl;
import com.capillary.huffman.mydefines.HuffmanData;
import com.capillary.huffman.mydefines.Node;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) throws IOException {

String source="";
String destination="";
        comp compress=new comp();
        compress.compressData();

        HuffmanDecompressionImpl hf=new HuffmanDecompressionImpl();
        hf.decompress("/Users/gouravhanumante/IdeaProjects/Test Files/isko.txt","/Users/gouravhanumante/IdeaProjects/Test Files/yahapar.txt");







//
//        File file1 = new File("/Users/gouravhanumante/IdeaProjects/Test Files/input.txt");
//        File file2 = new File("/Users/gouravhanumante/IdeaProjects/Test Files/inputdecompressed.txt");
////        boolean isTwoEqual = Files(file1, file2);
////        Files.mismatch(file1.toPath(), file2.toPath());
//        System.out.println(Files.mismatch(file1.toPath(), file2.toPath()));

//
//
//        System.out.println(mp);



        ///
        //we gt the 15% map
        //words //nextFullCharacter //check if it is presentin m15
        // if present do nothing else read character by character
        // create tree using m15
        // create huffcodes  hmp
        // write String (-byte-) arr and hmp
        //


        //decompression
//        read data
//        exchange key and value of hmp
//        decode


        //
        //
        //



    }

}


