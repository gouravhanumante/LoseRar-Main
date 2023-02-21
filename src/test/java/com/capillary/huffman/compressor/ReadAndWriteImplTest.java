package com.capillary.huffman.compressor;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class ReadAndWriteImplTest {

    IReadData rw=new ReadDataImpl();
    @Test
    public void read_WhenStandardInputIsProvided_ThenDesiredByteArrayShouldBeFormed() throws FileNotFoundException {

        String path="/Users/gouravhanumante/IdeaProjects/Test Files/Real Test Files/TestFiles/test1.txt";
        Byte[] expected={'a','b','c','d'};
        Byte[] result= rw.read(path);
        System.out.println(Arrays.toString(expected));
        System.out.println(Arrays.toString(result));

        Assert.assertArrayEquals(expected,result);
    }
    @Test
    public void read_WhenFileIsEmpty_ThenEmptyByteArrayShouldBeFormed() throws FileNotFoundException {

        String path="/Users/gouravhanumante/IdeaProjects/Test Files/Real Test Files/TestFiles/test2.txt";
        Byte[] expected={};
        Byte[] result= rw.read(path);

        Assert.assertArrayEquals(expected,result);
    }

    @Test
    public void read_WhenFileContainsSpecialCharacters_ThenDesiredByteArrayShouldBeFormed() throws FileNotFoundException {

        String path="/Users/gouravhanumante/IdeaProjects/Test Files/Real Test Files/TestFiles/test3.txt";
        Byte[] expected={32,32,32,10};
        Byte[] result= rw.read(path);

        Assert.assertArrayEquals(expected,result);
    }



}