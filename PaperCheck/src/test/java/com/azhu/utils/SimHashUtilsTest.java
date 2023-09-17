package test.java.com.azhu.utils;

import main.java.com.azhu.utils.SimHashUtils;
import org.junit.Test;

public class SimHashUtilsTest {

    @Test
    public void getHashTest(){
        String[] strings = {"�໪", "��", "һλ", "����", "��", "����"};
        for (String string : strings) {
            String stringHash = SimHashUtils.getHash(string);
            System.out.println(stringHash.length());
            System.out.println(stringHash);
        }
    }

    @Test
    public void getSimHashTest(){
        String str0 = com.azhu.utils.TxtIOUtils.readTxt("D:/test/orig.txt");
        String str1 = com.azhu.utils.TxtIOUtils.readTxt("D:/test/orig_0.8_add.txt");
        System.out.println(SimHashUtils.getSimHash(str0));
        System.out.println(SimHashUtils.getSimHash(str1));
    }

}
