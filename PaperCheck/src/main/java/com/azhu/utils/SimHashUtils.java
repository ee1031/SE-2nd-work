package main.java.com.azhu.utils;

import com.hankcs.hanlp.HanLP;
import java.math.BigInteger;
import java.security.MessageDigest;//MessageDigest��Ĺ����ǽ���������ת��Ϊ�̶����ȵĹ�ϣֵ��������java.security��
import java.util.List;

public class SimHashUtils {

    /**
     * ����String��������hashֵ�������ַ�����ʽ���
     * @param str �����Srting�����ַ���
     * @return ����str��hashֵ
     */
    public static String getHash(String str){
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            //һ��MessageDigestʵ��ֻ��ָ��һ���㷨
            //MD5����Hash�㷨��������ⳤ�ȵ���Ϣ���ؼ��ʣ����м��㣬����һ��128λ����ϢժҪ
            //��ϢժҪ�ֳ�Ϊ����ժҪ������Ψһ��Ӧһ����Ϣ���ı��Ĺ̶����ȵ�ֵ
            return new BigInteger(1, messageDigest.digest(str.getBytes("UTF-8"))).toString(2);
            //String.getBytes(Stringdecode)���������ָ����decode���뷵��ĳ�ַ����ڸñ����µ�byte�����ʾ
            //digest()������ɹ�ϣ����
            //BigInteger���ڱ�ʾ�����С���������������вι��죬���У�1��BigInteger�ķ��ţ�BigInteger��ֵ����ժҪ����Ľ��
            //�÷����Ľ��Ϊ����str��hashֵ
        }catch(Exception e){
            e.printStackTrace();
            return str;
        }
    }

    /**
     * ����String,���������simHashֵ�������ַ�����ʽ���
     * @param str �����Srting�����ַ���
     * @return ����str��simHashֵ
     */
    public static String getSimHash(String str){
        try{
            if(str.length() < 200) throw new ShortStringException("�ı����̣�");
            //���ı�����̫��ʱ��HanLp�����޷�ȡ�ùؼ���
        }catch (ShortStringException e){
            e.printStackTrace();
            return null;
        }
        int[] v = new int[128];
        List<String> keywordList = HanLP.extractKeyword(str, str.length());
        //�ִʲ���ʹ�����ⲿ����hankcs���ṩ�Ľӿ�
        //Listָ���Ǽ��ϣ�<>�Ƿ���
        //List<String>���͵ı���keywordList���ڴ洢��ȡ���Ĺؼ���
        //HanLp���е�extractKeyword����������ȡ�ؼ��ʣ��÷������������������ַ���str��ʾҪ��ȡ�ؼ��ʵ��ı�������str.length()��ʾ�ı��ĳ���
        int size = keywordList.size();
        //size()����keywordList����Ԫ�ص�����
        int i = 0;
        for(String keyword : keywordList)
        //for-eachѭ����һ�ּ򻯰��ѭ����䣬������Ҫ�����Ǳ��������е�ÿһ��Ԫ��
        {
            String keywordHash = getHash(keyword);
            if (keywordHash.length() < 128) {
                int dif = 128 - keywordHash.length();
                for (int j = 0; j < dif; j++) {
                    keywordHash += "0";
                    //��������+=����ָ�����ַ���ӵ��ַ���ĩβ������ѡ�0����ӵ����ַ���keywordHashĩβ��ʹ�ø��ַ����ĳ���ʼ��Ϊ128
                }
            }
            for (int j = 0; j <128; j++) {
                if (keywordHash.charAt(j) == '1')
                //charAt()����������������charֵ
                //��������Ĭ�ϳ�ʼֵΪ0����hashֵ��Ӧλ�ϵ�ֵΪ1ʱ��Ȩ�������Ȩ
                {
                    v[j] += (10 - (i / (size / 10)));
                    //������ֵĹؼ��ʿ��ܸ��ܴ����ı������⣬����ͨ��i�����������������ֵĹؼ��ʸ����Ȩ��
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
            //�˲�����н�ά���������������ͬ�ı�֮����бȽ�
            if (v[j] <= 0) {
                simHash += "0";
            } else {
                simHash += "1";
            }
        }
        return simHash;
        //���շ����ܹ�������ı���simHashֵ
    }
}
