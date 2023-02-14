import com.capillary.huffman.compressor.HuffmanCompressionImpl;
import com.capillary.huffman.compressor.ICompressor;
import com.capillary.huffman.compressor.IReadAndWrite;
import com.capillary.huffman.compressor.ReadAndWriteImpl;
import com.capillary.huffman.decompressor.HuffmanDecompressionImpl;
import com.capillary.huffman.decompressor.IDecompressor;

public class HuffmanLoseRarImpl implements ILoseRar{

    @Override
    public void compression(String source, String destination) {
        ICompressor compressor=new HuffmanCompressionImpl();
        IReadAndWrite rw=new ReadAndWriteImpl();
        byte[] b=rw.read(source);
        compressor.compress(b,destination);
    }


    @Override
    public void decompression(String source, String destination) {
        IDecompressor decompressor=new HuffmanDecompressionImpl();
        decompressor.decompress(source,destination);
    }
}
