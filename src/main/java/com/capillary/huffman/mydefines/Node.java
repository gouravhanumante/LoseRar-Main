package com.capillary.huffman.mydefines;

public class Node implements Comparable<Node> {
    public String getData() {
        return data;
    }

    public String data;
    public int frequency;
    public Node left;
    public Node right;

    public Node(String data, int frequency){
        this.data=data;
        this.frequency=frequency;
    }

    @Override
    public int compareTo(Node o) {
       return this.frequency-o.frequency;
    }


//    @Override
//    public int compareTo(Node o) {
//        return this.frequency - o.frequency;
//    }


//    public boolean equals(Object r){
//        if ((r instanceof Node) && ((Node) r).data==this.data ) {
//            return true;
//        }
//        return false;
//    }

}
