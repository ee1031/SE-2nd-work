package main.java.com.azhu.utils;

import com.hankcs.hanlp.HanLP;
import java.math.BigInteger;
import java.security.MessageDigest;//MessageDigest类的功能是将输入数据转换为固定长度的哈希值，它属于java.security包
import java.util.List;

public class SimHashUtils {

    /**
     * 传入String，计算其hash值，并以字符串形式输出
     * @param str 传入的Srting类型字符串
     * @return 返回str的hash值
     */
    public static String getHash(String str){
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            //一个MessageDigest实例只能指定一种算法
            //MD5属于Hash算法，其对任意长度的消息（关键词）进行计算，产生一个128位的消息摘要
            //消息摘要又称为数字摘要，它是唯一对应一个消息或文本的固定长度的值
            return new BigInteger(1, messageDigest.digest(str.getBytes("UTF-8"))).toString(2);
            //String.getBytes(Stringdecode)方法会根据指定的decode编码返回某字符串在该编码下的byte数组表示
            //digest()方法完成哈希计算
            //BigInteger用于表示任意大小的整数，其属于有参构造，其中，1是BigInteger的符号，BigInteger的值等于摘要计算的结果
            //该方法的结果为返回str的hash值
        }catch(Exception e){
            e.printStackTrace();
            return str;
        }
    }

    /**
     * 传入String,计算出它的simHash值，并以字符串形式输出
     * @param str 传入的Srting类型字符串
     * @return 返回str的simHash值
     */
    public static String getSimHash(String str){
        try{
            if(str.length() < 200) throw new ShortStringException("文本过短！");
            //当文本长度太短时，HanLp可能无法取得关键字
        }catch (ShortStringException e){
            e.printStackTrace();
            return null;
        }
        int[] v = new int[128];
        List<String> keywordList = HanLP.extractKeyword(str, str.length());
        //分词步骤使用了外部依赖hankcs包提供的接口
        //List指的是集合，<>是泛型
        //List<String>类型的变量keywordList用于存储提取到的关键词
        //HanLp库中的extractKeyword方法用于提取关键词，该方法接收两个参数，字符串str表示要提取关键词的文本，整数str.length()表示文本的长度
        int size = keywordList.size();
        //size()返回keywordList集合元素的数量
        int i = 0;
        for(String keyword : keywordList)
        //for-each循环是一种简化版的循环语句，它的主要作用是遍历集合中的每一个元素
        {
            String keywordHash = getHash(keyword);
            if (keywordHash.length() < 128) {
                int dif = 128 - keywordHash.length();
                for (int j = 0; j < dif; j++) {
                    keywordHash += "0";
                    //操作符“+=”把指定的字符添加到字符串末尾，这里把“0”添加到了字符串keywordHash末尾，使得该字符串的长度始终为128
                }
            }
            for (int j = 0; j <128; j++) {
                if (keywordHash.charAt(j) == '1')
                //charAt()方法返回索引处的char值
                //整型数组默认初始值为0，当hash值对应位上的值为1时加权，否则减权
                {
                    v[j] += (10 - (i / (size / 10)));
                    //较早出现的关键词可能更能代表文本的主题，于是通过i的自增，赋予较早出现的关键词更大的权重
                }
                else {
                    v[j] -= (10 - (i / (size / 10)));
                }
            }
            i++;
        }
        String simHash = "";
        for (int j = 0; j <128; j++)
        {
            //此步骤进行降维操作，方便后续不同文本之间进行比较
            if (v[j] <= 0) {
                simHash += "0";
            } else {
                simHash += "1";
            }
        }
        return simHash;
        //最终返回能够代表该文本的simHash值
    }
}
