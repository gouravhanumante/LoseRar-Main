package com.capillary;

import com.capillary.IZipper;
import com.capillary.huffman.HuffmanZipperImpl;
import com.capillary.mixedhuffman.MixedZipperImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException, SQLException, NoSuchAlgorithmException, ClassNotFoundException {

//        String gsource="/Users/gouravhanumante/IdeaProjects/Test Files/bigfile.txt";
//        String gcompressed="/Users/gouravhanumante/IdeaProjects/Test Files/mobile.txt";
//        String gdestination="/Users/gouravhanumante/IdeaProjects/Test Files/result.txt";
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        String gsource="/home/sarthakjain/IdeaProjects/files/input.txt";
        String gcompressed="/home/sarthakjain/IdeaProjects/files/compressedInputfile.txt";
        String gdestination="/home/sarthakjain/IdeaProjects/files/decompressedInput.txt";




        IZipper zipper=new MixedZipperImpl();
//        IZipper zipper=new HuffmanZipperImpl();
        long val1=System.currentTimeMillis();

        zipper.compression(gsource,gcompressed);
        long val2=System.currentTimeMillis();
        logger.log(Level.INFO,"Compression done");
        zipper.decompression(gcompressed,gdestination);
        long val3=System.currentTimeMillis();
        logger.log(Level.INFO,"Compression done in:"+(val2-val1)+"ms");
        logger.log(Level.INFO,"Decompression done in:"+(val3-val2)+"ms");

        File file1 = new File(gsource);
        File file2 = new File(gdestination);
        logger.log(Level.INFO, "" + Files.mismatch(file1.toPath(), file2.toPath()));
    }

}


