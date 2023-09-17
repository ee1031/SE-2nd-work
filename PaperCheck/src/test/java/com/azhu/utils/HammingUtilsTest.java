package test.java.com.azhu.utils;

import main.java.com.azhu.utils.SimHashUtils;
import org.junit.Test;//Test类是JUnit框架中的一个核心类，它用于编写和运行单元测试

public class HammingUtilsTest {

    @Test//该注解用于标识以下方法是一个Junit测试方法
    public void getHammingDistanceTest() {
        String str0 = com.azhu.utils.TxtIOUtils.readTxt("D:/test/orig.txt");
        String str1 = com.azhu.utils.TxtIOUtils.readTxt("D:/test/orig_0.8_add.txt");
        int distance = com.azhu.utils.HammingUtils.getHammingDistance(SimHashUtils.getSimHash(str0), SimHashUtils.getSimHash(str1));
        System.out.println("海明距离：" + distance);
        System.out.println("相似度: " + (100 - distance * 100 / 128) + "%");
    }

    @Test
    public void getHammingDistanceFailTest() {
        // 测试str0.length()!=str1.length()的情况
        String str0 = "10101010";
        String str1 = "1010101";
        System.out.println(com.azhu.utils.HammingUtils.getHammingDistance(str0, str1));
    }

    @Test
    public void getSimilarityTest() {
        String str0 = com.azhu.utils.TxtIOUtils.readTxt("D:/test/orig.txt");
        String str1 = com.azhu.utils.TxtIOUtils.readTxt("D:/test/orig_0.8_add.txt");
        int distance = com.azhu.utils.HammingUtils.getHammingDistance(SimHashUtils.getSimHash(str0), SimHashUtils.getSimHash(str1));
        double similarity = com.azhu.utils.HammingUtils.getSimilarity(SimHashUtils.getSimHash(str0), SimHashUtils.getSimHash(str1));
        System.out.println("str0和str1的海明距离: " + distance);
        System.out.println("str0和str1的相似度:" + similarity);
    }

}
