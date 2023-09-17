package test.java.com.azhu.utils;

import main.java.com.azhu.utils.SimHashUtils;
import org.junit.Test;

public class ShortStringExceptionTest {

    @Test
    public void shortStringExceptionTest(){
        //测试str.length()<200的情况
        System.out.println(SimHashUtils.getSimHash("一位正真的作家"));
    }

}
