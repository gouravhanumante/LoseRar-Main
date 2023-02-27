import com.capillary.huffman.HuffmanZipperImpl;
import com.capillary.IZipper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.ExecutionException;

public class Main {


    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        IZipper loseRar=new HuffmanZipperImpl();
//        System.out.println(System.currentTimeMillis());
        long val1=System.currentTimeMillis();

        loseRar.compression("/home/sarthakjain/IdeaProjects/files/orgFile.txt",
                "/home/sarthakjain/IdeaProjects/files/compressedTest.txt");
//
//
//        long val2=System.currentTimeMillis();
//
        loseRar.decompression("/home/sarthakjain/IdeaProjects/files/compressedTest.txt",
                "/home/sarthakjain/IdeaProjects/files/ans.txt");
//
////        boolean areEqual = sameContent()
////        System.out.println(areEqual);
//        File file1 = new File("/home/sarthakjain/IdeaProjects/Files/today.txt");
//        File file2 = new File("/home/sarthakjain/IdeaProjects/Files/input.txt");
////        boolean isTwoEqual = Files(file1, file2);
////        Files.mismatch(file1.toPath(), file2.toPath());
//        System.out.println(Files.mismatch(file1.toPath(), file2.toPath()));
//        long val3=System.currentTimeMillis();
//
//        System.out.println("Time for compression=" + (val2-val1)+"ms");
//        System.out.println("Time for decompression=" + (val3-val2)+"ms");

    }
}