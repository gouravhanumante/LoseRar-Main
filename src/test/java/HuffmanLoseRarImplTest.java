import com.capillary.huffman.compressor.IReadAndWrite;
import com.capillary.huffman.compressor.ReadAndWriteImpl;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class HuffmanLoseRarImplTest {

    @Test
    public void compression() {
        IReadAndWrite rw=mock(ReadAndWriteImpl.class);
        ILoseRar rar=new HuffmanLoseRarImpl();

        byte[] b={'a','b','c'};



    }
}