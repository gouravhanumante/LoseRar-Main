package com.capillary.huffman.compressor;

import com.capillary.huffman.mydefines.MinPriorityQueue;
import com.capillary.huffman.mydefines.Node;

import java.util.HashMap;
import java.util.Map;

public class TreeCreationUtils implements ITreeCreationUtils{

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

        MinPriorityQueue<Node> pq=new MinPriorityQueue<>();

        for (Map.Entry<String, Integer> entry:freq.entrySet()){
            pq.add(new Node( entry.getKey(),entry.getValue()));
        }

        //create tree using pq
        while (pq.len()>1){
            Node left=pq.poll();
            Node right=pq.poll();

            Node parent=new Node(null,left.frequency+right.frequency);
            parent.left=left;
            parent.right=right;
            pq.add(parent);
        }

        return pq.poll();
    }
}
