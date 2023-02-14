import com.capillary.huffman.compressor.HuffmanCompressionImpl;
import com.capillary.huffman.compressor.ICompressor;
import com.capillary.huffman.compressor.IReadAndWrite;
import com.capillary.huffman.compressor.ReadAndWriteImpl;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class HuffmanLoseRarImplTest {



    @Test
    public void compression() {
        ReadAndWriteImpl rw=mock(ReadAndWriteImpl.class);
        HuffmanCompressionImpl compressor=mock(HuffmanCompressionImpl.class);

        ILoseRar rar=new HuffmanLoseRarImpl(rw,compressor);

        String source="/home/gauravhanumante/Files/readtestmain.txt";
        String destination="/home/gauravhanumante/Files/testtest.txt";
        byte[] b={'a','b','c'};

        doReturn(b).when(rw).read(source);
        doNothing().when(compressor).compress(b,destination);


        rar.compression(source,destination);
        verify(rw,times(1)).read(source);
        verify(compressor,times(1)).compress(b,destination);





    }
}