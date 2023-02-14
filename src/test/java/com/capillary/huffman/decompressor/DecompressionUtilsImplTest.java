package com.capillary.huffman.decompressor;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)

public class DecompressionUtilsImplTest {


    @InjectMocks
    DecompressionUtilsImpl utils=new DecompressionUtilsImpl();



    @Test
    public void decompress_WhenStandardInputIsProvided_ThenSomething() {
        byte[] huffmanByte=new byte[1];
        huffmanByte[0]=(byte)85;

        Map<Byte,String> mp=new HashMap<>();
        mp.put( (byte) 'a',"0");
        mp.put( (byte) 'b',"1");

        byte counter=1;

        byte[] actual=utils.decompress(huffmanByte,mp, counter);
        byte[] expected={'a','b','a','b','a','b','a','b'};


        Assert.assertArrayEquals(expected,actual);
    }










    //////////////////////////////
    @Test
    public void convertByteToBitString_WhenCounterIs0And8BitsArePresent_ThenOutputShouldBe() {
        byte[] arr=new byte[1];
        arr[0]=(byte) 170;

        byte counter=0;

        String expected="10101010";

        String actual=utils.convertBytetoBitString(arr, counter);

        Assert.assertEquals(expected,actual);

    }

    @Test
    public void convertByteToBitString_WhenCounterIs0Abd8BitsAreNotPresent_ThenOutputShouldBe() {
        byte[] arr=new byte[1];
        arr[0]=(byte) 4;

        String expected="100";

        String actual=utils.convertBytetoBitString(arr, (byte) 0);

        Assert.assertEquals(expected,actual);

    }

    @Test
    public void convertByteToBitString_WhenCounterIsNot0AndByteArrayContains0_ThenOutputShouldBe() {
        byte[] arr=new byte[1];
        arr[0]=(byte) 0;
        byte counter=6;

        String expected="000000";

        String actual=utils.convertBytetoBitString(arr, counter);

        Assert.assertEquals(expected,actual);

    }
//
//
    @Test
    public void convertByteToBitString_WhenArraySizeIsGreaterThanOne_ThenSomething() {
        byte[] arr=new byte[2];
        arr[0]=(byte) 181;
        arr[1]=(byte) 1;

        String expected="10110101001";

        String actual=utils.convertBytetoBitString(arr, (byte) 2);

        Assert.assertEquals(expected,actual);

    }
//
//
}