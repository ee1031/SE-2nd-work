package test.java.com.azhu.utils;

import main.java.com.azhu.utils.SimHashUtils;
import org.junit.Test;

public class ShortStringExceptionTest {

    @Test
    public void shortStringExceptionTest(){
        //����str.length()<200�����
        System.out.println(SimHashUtils.getSimHash("һλ���������"));
    }

}
