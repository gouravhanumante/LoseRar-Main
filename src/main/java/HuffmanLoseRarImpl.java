import com.capillary.huffman.compressor.HuffmanCompressionImpl;
import com.capillary.huffman.compressor.ICompressor;
import com.capillary.huffman.compressor.IReadData;
import com.capillary.huffman.compressor.ReadDataImpl;
import com.capillary.huffman.decompressor.HuffmanDecompressionImpl;
import com.capillary.huffman.decompressor.IDecompressor;

public class HuffmanLoseRarImpl implements ILoseRar{
    IReadData rw;
    ICompressor hc;

    public HuffmanLoseRarImpl(ReadDataImpl rw, HuffmanCompressionImpl hc) {
        this.hc=hc;
        this.rw=rw;
    }
    public HuffmanLoseRarImpl(){
        rw=new ReadDataImpl();
        hc=new HuffmanCompressionImpl();

    }

    @Override
    public void compression(String source, String destination) {
        Byte[] b=rw.read(source);
        hc.compress(b,destination);
    }



    @Override
    public void decompression(String source, String destination) {
        IDecompressor huffmanDecompressor=new HuffmanDecompressionImpl();
        huffmanDecompressor.decompress(source,destination);
    }
}
