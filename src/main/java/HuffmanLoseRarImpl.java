import com.capillary.huffman.compressor.HuffmanCompressionImpl;
import com.capillary.huffman.compressor.ICompressor;
import com.capillary.huffman.compressor.IReadAndWrite;
import com.capillary.huffman.compressor.ReadAndWriteImpl;
import com.capillary.huffman.decompressor.HuffmanDecompressionImpl;
import com.capillary.huffman.decompressor.IDecompressor;

public class HuffmanLoseRarImpl implements ILoseRar{
    ReadAndWriteImpl rw;
    HuffmanCompressionImpl hc;

    public HuffmanLoseRarImpl(ReadAndWriteImpl rw,HuffmanCompressionImpl hc) {
        this.hc=hc;
        this.rw=rw;
    }
    public HuffmanLoseRarImpl(){
        rw=new ReadAndWriteImpl();
        hc=new HuffmanCompressionImpl();

    }

    @Override
    public void compression(String source, String destination) {
        byte[] b=rw.read(source);
        hc.compress(b,destination);
    }



    @Override
    public void decompression(String source, String destination) {
        IDecompressor huffmanDecompressor=new HuffmanDecompressionImpl();
        huffmanDecompressor.decompress(source,destination);
    }
}
