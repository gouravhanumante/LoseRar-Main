import java.nio.file.Watchable;
import java.util.Timer;

public class Main {


    public static void main(String[] args) {
        ILoseRar loseRar=new HuffmanLoseRarImpl();
//        System.out.println(System.currentTimeMillis());
        long val1=System.currentTimeMillis();

        loseRar.compression("/home/gauravhanumante/Files2/input.txt",
                "/home/gauravhanumante/Files2/ir1.txt");


        long val2=System.currentTimeMillis();

//        loseRar.decompression("/home/gauravhanumante/Files2/compressedTest.txt",
//                "/home/gauravhanumante/Files2/today.txt");

        long val3=System.currentTimeMillis();

        System.out.println("Time for compression=" + (val2-val1)+"ms");
        System.out.println("Time for decompression=" + (val3-val2)+"ms");

    }
}