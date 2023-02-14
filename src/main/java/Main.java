import java.nio.file.Watchable;
import java.util.Timer;

public class Main {

    public static void main(String[] args) {
        ILoseRar loseRar=new HuffmanLoseRarImpl();
//        System.out.println(System.currentTimeMillis());
        long val1=System.currentTimeMillis();
        loseRar.compression("/home/gauravhanumante/Files/temp.txt",
                "/home/gauravhanumante/Files/c.txt");

//        System.out.println(String.format("%8s", Integer.toBinaryString(((byte)1) & 0xFF)).replace(' ', '0'));
        long val2=System.currentTimeMillis();

        loseRar.decompression("/home/gauravhanumante/Files/c.txt",
                "/home/gauravhanumante/Files/resultmain.txt");

        long val3=System.currentTimeMillis();

        System.out.println("Time for compression=" + (val2-val1)+"ms");
        System.out.println("Time for decompression=" + (val3-val2)+"ms");

    }
}