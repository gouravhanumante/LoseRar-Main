package com.capillary.huffman.compressor;

import com.capillary.huffman.mydefines.MinPriorityQueue;
import com.capillary.huffman.mydefines.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.Callable;

public class TreeCreationUtils implements ITreeCreationUtils, Callable {

    private String[] fileData;
    private int start;
    public TreeCreationUtils(String[] fileData, int start) {
        this.fileData = fileData;
        this.start = start;
    }

    public TreeCreationUtils() {
    }


    @Override
    public Map<String, Integer> createFrequencyMap(String[] fileData) {
        Map<String, Integer> freq=new HashMap<>();
        for (String b : fileData) {
            Integer value = freq.get(b);
            if (value == null)
                freq.put(b, 1);
            else
                freq.put(b, value + 1);
        }
        return freq;
    }


    @Override
    public Node createTreeUsingMinHeap(Map<String, Integer> freq) {

//        MinPriorityQueue<Node> pq=new MinPriorityQueue<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(freq.size());
        for (Map.Entry<String, Integer> entry:freq.entrySet()){
            pq.add(new Node( entry.getKey(),entry.getValue()));
        }

        Node root = null;
        if (pq.size() == 1) {
            Node leftSideNode = pq.peek();
            pq.poll();
            Node newNode = new Node(null, leftSideNode.frequency);
            newNode.left = leftSideNode;
            newNode.right= null;
            root = newNode;
            return root;
        }
        while (pq.size()>1){
            Node left=pq.poll();
            Node right=pq.poll();

            Node parent=new Node(null,left.frequency+right.frequency);
            parent.left=left;
            parent.right=right;
            root = parent;
            pq.add(parent);
        }

        return root;
    }

    @Override
    public Map<String, Integer> call() throws Exception {
        Map<String, Integer> freq=new HashMap<>();
        int startIndex = start*(fileData.length/4);
        int endIndex = Math.min(fileData.length, (startIndex + 1)*(fileData.length)/4);
        for (int i=0; i< fileData.length; i++) {
            Integer value = freq.get(fileData[i]);
            if (value == null)
                freq.put(fileData[i], 1);
            else
                freq.put(fileData[i], value + 1);
        }
        return freq;
    }
}
