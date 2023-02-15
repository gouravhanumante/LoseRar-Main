package com.capillary.huffman.mydefines;

public class HuffmanData {
    private Byte[] huffmanByte;
    private byte counter;
    public HuffmanData(Byte[] huffmanByte, byte counter){
        this.setHuffmanByte(huffmanByte);
        this.setCounter(counter);
    }

    public Byte[] getHuffmanByte() {
        return huffmanByte;
    }

    public void setHuffmanByte(Byte[] huffmanByte) {
        this.huffmanByte = huffmanByte;
    }

    public byte getCounter() {
        return counter;
    }

    public void setCounter(byte counter) {
        this.counter = counter;
    }
}

