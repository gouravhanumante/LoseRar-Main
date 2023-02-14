package com.capillary.huffman.mydefines;

public class Container {
    private byte [] huffmanByte;
    private byte counter;
    public Container(byte[] huffmanByte,byte counter){
        this.setHuffmanByte(huffmanByte);
        this.setCounter(counter);
    }

    public byte[] getHuffmanByte() {
        return huffmanByte;
    }

    public void setHuffmanByte(byte[] huffmanByte) {
        this.huffmanByte = huffmanByte;
    }

    public byte getCounter() {
        return counter;
    }

    public void setCounter(byte counter) {
        this.counter = counter;
    }
}

