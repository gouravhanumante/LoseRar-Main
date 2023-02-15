import com.capillary.huffman.compressor.HuffmanCompressionImpl;
import com.capillary.huffman.compressor.ReadDataImpl;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class HuffmanLoseRarImplTest {



    @Test
    public void compression() {
        ReadDataImpl rw=mock(ReadDataImpl.class);
        HuffmanCompressionImpl compressor=mock(HuffmanCompressionImpl.class);

        ILoseRar rar=new HuffmanLoseRarImpl(rw,compressor);

        String source="/home/gauravhanumante/Files/readtestmain.txt";
        String destination="/home/gauravhanumante/Files/testtest.txt";
        byte[] b={'a','b','c'};

        InOrder inOrder= Mockito.inOrder(rw,compressor);

        doReturn(b).when(rw).read(source);
        doNothing().when(compressor).compress(b,destination);


        rar.compression(source,destination);
        inOrder.verify(rw,times(1)).read(source);
        inOrder.verify(compressor,times(1)).compress(b,destination);





    }
}