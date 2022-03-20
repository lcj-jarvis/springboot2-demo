package com.mrlu.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test01(){
        //String str = "babjcabbac";
        //String str = "b";
        //String str = "cbbd";
        //String str = "121ac";
        String str = "abcdbbfcba";
        //String str = "ac";
        String longestString = findLongestString(str);
        System.out.println(longestString);
    }

    public String findLongestString(String str){
        if (str.length()==1 || reverse(str,0,str.length()-1).equals(str)){
            return str;
        }
        if (str.length()==2){
            return str.substring(0,1);
        }
        int end = str.length() - 1;
        int count = 0;
        int maxlength = 0;
        Map<Integer, String> stringMap = new TreeMap<>();

        for (int i = 0; i < str.length() / 2 ; i++) {

           if (i % 2 == 0){
               end = str.length() - 1 - i;
           }
           int leftPoint = end;
           int rightEnd = end ;
           if (i % 2 != 0){
               rightEnd = end - 1;
           }

            // leftPoint - 1 左边指针移动到只有一个字符的时候，不用判断了。
            // i + count + 1 右边指针移动到知道有一字符的时候，就不用判断了。
            while (i <= leftPoint - 1){

                String temp = str.substring(i,leftPoint);
                //两边同时判断.
                if (reverse(temp,0,temp.length()-1).equals(temp)){
                    if (temp.length() >= maxlength){
                        maxlength = temp.length();
                    }
                    stringMap.put(temp.length(),temp);
                }
                count++;
                temp = str.substring(i + count,rightEnd + 1);
                //两边同时判断
                if (reverse(temp,0,temp.length()-1).equals(temp) ){
                    if (temp.length() >= maxlength){
                        maxlength = temp.length();
                    }
                    stringMap.put(temp.length(),temp);
                }
                leftPoint--;
            }
            count = 0;
        }
        return stringMap.get(maxlength);
    }

    //字符串反转
    public static String reverse(String s,int begin,int end)
    {
        if (s != null)
        {
            char[] chars = s.toCharArray();
            char temp;
            while (begin <= end)
            {
                temp = chars[begin];
                chars[begin] = chars[end];
                chars[end] = temp;
                begin++;
                end --;
            }
            return String.valueOf(chars);
        }
        return null;
    }


}
