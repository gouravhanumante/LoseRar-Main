//package com.capillary.huffman.compressor;
//
//import com.capillary.huffman.mydefines.Node;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RunWith(MockitoJUnitRunner.class)
//public class wordsTreeCreationUtilsTest {
//
//    @InjectMocks
//    ITreeCreationUtils utils=new TreeCreationUtils();
//
//
//    // functionName_WhenSomethingHappens_ThenThisShouldHappen
//    @Test
//    public void createFrequencyMap_WhenStandardInputWithMultipleDifferentCharactersIsPassed_ThenFrequencyCountShouldBeRight(){
//        byte[] bytes={97,97,97,98,98,99,10};
//        Map<Byte,Integer> expected=new HashMap<>();
//        expected.put((byte) 97,3);
//        expected.put((byte) 98,2);
//        expected.put((byte) 99,1);
//        expected.put((byte) 10,1);
//
//        Map<Byte,Integer> result=utils.createFrequencyMap(bytes);
//
//        Assert.assertEquals(expected,result);
//    }
//    @Test
//    public void createFrequencyMap_WhenSingleElementIsPresent_ThenFrequencyCountShouldBeOne(){
//
//        byte[] bytes={'a'};
//        Map<Byte,Integer> expected=new HashMap<>();
//        expected.put((byte) 'a',1);
//
//        Map<Byte,Integer> result=utils.createFrequencyMap(bytes);
//
//        Assert.assertEquals(expected,result);
//    }
//
//    @Test
//    public void createFrequencyMap_WhenBothUpperCaseAndLowerCaseCharactersArePresent_ThenFrequencyCountShouldBeRight(){
//        byte[] bytes={'a','A'};
//        Map<Byte,Integer> expected=new HashMap<>();
//        expected.put((byte) 'a',1);
//        expected.put((byte) 'A',1);
//
//        Map<Byte,Integer> result=utils.createFrequencyMap(bytes);
//
//        Assert.assertEquals(expected,result);
//    }
//
//    @Test
//    public void createFrequencyMap_WhenSpecialCharactersArePresent_ThenFrequencyCountShouldBeRight(){
//        byte[] bytes={'\0','\n','\t'};
//        Map<Byte,Integer> expected=new HashMap<>();
//        expected.put((byte) '\0',1);
//        expected.put((byte) '\n',1);
//        expected.put((byte) '\t',1);
//
//        Map<Byte,Integer> result=utils.createFrequencyMap(bytes);
//
//        Assert.assertEquals(expected,result);
//    }
//
//    @Test
//    public void createTreeUsingMinHeap_WhenStandardInputIsProvided_ThenNormalTreeShouldBeFormed() {
//
//        Map<Byte,Integer> expected=new HashMap<>();
//        expected.put((byte) 97,3);
//        expected.put((byte) 98,2);
//        expected.put((byte) 99,1);
//        expected.put((byte) 10,1);
//
//        Node resultRoot =utils.createTreeUsingMinHeap(expected);
//
//        Node expectedRoot=new Node(null,7);
//        expectedRoot.left=new Node((byte) 97,3);
//        expectedRoot.right=new Node(null,4);
//        expectedRoot.right.left=new Node(null,2);
//        expectedRoot.right.left.left=new Node((byte) 10,1);
//        expectedRoot.right.left.right=new Node((byte) 99,1);
//        expectedRoot.right.right=new Node((byte) 98,2);
//
//
//        Assert.assertTrue(compareTreeUtil(expectedRoot,resultRoot));
//    }
//
//    @Test
//    public void createTreeUsingMinHeap_WhenSingleElementIsPresent_ThenTreeWithOneNodeShouldBeFormed() {
//
//        Map<Byte,Integer> expected=new HashMap<>();
//        expected.put((byte) 'a',1);
//        Node expectedRoot=new Node((byte) 'a',1);
//
//        Node resultRoot =utils.createTreeUsingMinHeap(expected);
//
//        Assert.assertTrue(compareTreeUtil(expectedRoot,resultRoot));
//    }
//    @Test
//    public void createTreeUsingMinHeap_WhenSpecialCharactersArePresent_ThenCorrectTreeShouldBeFormed() {
//
//        Map<Byte,Integer> expected=new HashMap<>();
//        expected.put((byte) '\n',2);
//        expected.put((byte) '\t',1);
//        expected.put((byte) '\0',1);
//
//
//
//        Node expectedRoot=new Node(null,4);
//        expectedRoot.left=new Node(null,2);
//        expectedRoot.right=new Node((byte) 10,2);
//        expectedRoot.left.left=new Node((byte) 9,1);
//        expectedRoot.left.right=new Node((byte) 0,1);
//
//
//
//        Node resultRoot =utils.createTreeUsingMinHeap(expected);
//
//
//
//        Assert.assertTrue(compareTreeUtil(expectedRoot,resultRoot));
//    }
//
//    public boolean compareTreeUtil(Node r1,Node r2){
//        if (r1==null && r2==null) return true;
//        if (r1.data!=r2.data) return false;
//        if (r1.frequency!=r2.frequency) return false;
//
//        boolean val2= compareTreeUtil(r1.left,r2.left);
//        boolean val1= compareTreeUtil(r1.right,r2.right);
//
//
//        return val1&&val2;
//    }
////    public void preorder(Node r1){
////        if (r1==null)return;
////
////        System.out.print(r1.frequency +" ");
////        preorder(r1.left);
////        preorder(r1.right);
////
////
////
////    }
//
//}
