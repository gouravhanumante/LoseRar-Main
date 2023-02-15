//package com.capillary.huffman.compressor;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.io.FileNotFoundException;
//
//public class ReadAndWriteImplTest {
//
//    IReadData rw=new ReadDataImpl();
//    @Test
//    public void read_WhenStandardInputIsProvided_ThenDesiredByteArrayShouldBeFormed() throws FileNotFoundException {
//
//        String path="/home/gauravhanumante/Files/TestFiles/test1.txt";
//        byte[] expected={'a','b','c','d','\n'};
//        byte[] result= rw.read(path);
//
//        Assert.assertArrayEquals(expected,result);
//    }
//    @Test
//    public void read_WhenFileIsEmpty_ThenEmptyByteArrayShouldBeFormed() throws FileNotFoundException {
//
//        String path="/home/gauravhanumante/Files/temp.txt";
//        byte[] expected={};
//        byte[] result= rw.read(path);
//
//        Assert.assertArrayEquals(expected,result);
//    }
//
//    @Test
//    public void read_WhenFileContainsSpecialCharacters_ThenDesiredByteArrayShouldBeFormed() throws FileNotFoundException {
//
//        String path="/home/gauravhanumante/Files/TestFiles/test3.txt";
//        byte[] expected={32,32,32,10};
//        byte[] result= rw.read(path);
//
//        Assert.assertArrayEquals(expected,result);
//    }
//
//
//
//}