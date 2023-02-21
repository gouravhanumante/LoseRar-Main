package com.capillary.huffman.compressor;

import com.capillary.huffman.mydefines.HuffmanData;
import com.capillary.huffman.mydefines.Node;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;


//        Mockito.doReturn(null).when(iTreeCreationUtils).createTreeUsingMinHeap(ArgumentMatchers.any())
@RunWith(MockitoJUnitRunner.class)
public class CompressionUtilsTest {


    @InjectMocks
    ICompressionUtils utils=new CompressionUtils();


    // functionName_WhenSomethingHappens_ThenThisShouldHappen
    @Test
    public void createCompressedArray_WhenNoOfBitsAre8_ThenCheckingIfCounterAndCompressedArrayIsCorrect() {
        Byte[] fileData={'b','a','b','a','b','a','b','a'};
        Map<Byte,String> mp=new HashMap<>();
        mp.put((byte) 'a',"0");
        mp.put((byte) 'b',"1");

        Byte[] expected=new Byte[1];
        expected[0]= (byte) Integer.parseInt("10101010", 2);
        byte counter=0;


        HuffmanData result=utils.createCompressedArray(fileData,mp);


        Assert.assertArrayEquals("Check if compressed array is created!",expected,result.getHuffmanByte());
        Assert.assertEquals("Check if counter's value is correct!!",counter,result.getCounter());


    }
    @Test

    public void createCompressedArray_WhenNoOfBitsAreLessThan8_ThenCheckingIfCounterAndCompressedArrayIsCorrect() {
        Byte[] fileData={'a','b','a','b','a','b','a'};
        Map<Byte,String> mp=new HashMap<>();
        mp.put((byte) 'a',"0");
        mp.put((byte) 'b',"1");

        Byte[] expected=new Byte[1];
        expected[0]= (byte) Integer.parseInt("0101010", 2);
        int count=1;

        HuffmanData result= utils.createCompressedArray(fileData,mp);
        System.out.println(Arrays.toString(expected));
        System.out.println(Arrays.toString(result.getHuffmanByte()));
        ;
        Assert.assertArrayEquals("Check if compressed array is created!",expected,result.getHuffmanByte());
        Assert.assertEquals(count,result.getCounter());

    }



    @Test
    public void createCompressedArray_WhenNoOfBitsAreGreaterThan8_ThenCheckingIfCounterAndCompressedArrayIsCorrect() {
        Byte[] fileData={'a','b','a','b','a','b','a','b','a'};
        Map<Byte,String> mp=new HashMap<>();
        mp.put((byte) 'a',"0");
        mp.put((byte) 'b',"1");

        Byte[] expected=new Byte[2];
        expected[0]= (byte) Integer.parseInt("01010101", 2);
        expected[1]=0;



        byte counter=1;


        HuffmanData result=utils.createCompressedArray(fileData,mp);


        Assert.assertArrayEquals("Check if compressed array is created!",expected,result.getHuffmanByte());
        Assert.assertEquals(counter,result.getCounter());

    }

    @Test

//    public void checkIfLookupMapIsCreated_For() {
    public void buildLookupRecursive_WhenStandardInpput_abc_IsProvided_ThenLookupMapShouldContain4Value() {
       Map<Byte,String> expected=new HashMap<>();
       Map<Byte,String> result=new HashMap<>();


       //

        Node root=new Node(null,7);
        root.left=new Node((byte) 'a',3);
        root.right=new Node(null,4);
        root.right.left=new Node(null,2);
        root.right.left.left=new Node((byte) '\n',1);
        root.right.left.right=new Node((byte) 'c',1);
        root.right.right=new Node((byte) 'b',2);


        expected.put((byte) 'a',"0");
        expected.put((byte) '\n',"100");
        expected.put((byte) 'c',"101");
        expected.put((byte) 'b',"11");
        //
       result=utils.buildLookupRecursive(root);

        Assert.assertEquals(expected,result);


    }



    @Test
    public void buildLookupRecursive_WhenSingleElementIsProvided_ThenLookupMapShouldContain1Value() {
       Map<Byte,String> expected=new HashMap<>();
       Map<Byte,String> result=new HashMap<>();

        Node root=new Node((byte) 'a',7);

        expected.put((byte) 'a',"1");

      result= utils.buildLookupRecursive(root);

        Assert.assertEquals(expected,result);
    }
    @Test
    public void br() {
       Map<Byte,String> expected=new HashMap<>();
       Map<Byte,String> result=new HashMap<>();


        Node root=new Node((byte) 'a',7);

        expected.put((byte) 'a',"1");




        CompressionUtils utilsMock=mock(CompressionUtils.class);
//        doNothing().when(utilsMock).buildLookupRecursive(root,"",result);
//        utilsMock.buildLookupRecursive(root,"",expected);

//        verify(utilsMock,times(1)).buildLookupRecursive(root,"",expected);




//       utils.buildLookupRecursive(root,"",result);

//        Assert.assertEquals(expected,result);
    }
}
