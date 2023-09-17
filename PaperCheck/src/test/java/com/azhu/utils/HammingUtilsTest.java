package test.java.com.azhu.utils;

import main.java.com.azhu.utils.SimHashUtils;
import org.junit.Test;//Test����JUnit����е�һ�������࣬�����ڱ�д�����е�Ԫ����

public class HammingUtilsTest {

    @Test//��ע�����ڱ�ʶ���·�����һ��Junit���Է���
    public void getHammingDistanceTest() {
        String str0 = com.azhu.utils.TxtIOUtils.readTxt("D:/test/orig.txt");
        String str1 = com.azhu.utils.TxtIOUtils.readTxt("D:/test/orig_0.8_add.txt");
        int distance = com.azhu.utils.HammingUtils.getHammingDistance(SimHashUtils.getSimHash(str0), SimHashUtils.getSimHash(str1));
        System.out.println("�������룺" + distance);
        System.out.println("���ƶ�: " + (100 - distance * 100 / 128) + "%");
    }

    @Test
    public void getHammingDistanceFailTest() {
        // ����str0.length()!=str1.length()�����
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
        System.out.println("str0��str1�ĺ�������: " + distance);
        System.out.println("str0��str1�����ƶ�:" + similarity);
    }

}
