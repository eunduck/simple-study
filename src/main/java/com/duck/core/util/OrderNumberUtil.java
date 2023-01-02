package com.duck.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by eunduck on 2022/11/03.
 *
 * 주문 번호 생성 util
 */
public class OrderNumberUtil {

    public static String generateNumber() {
        String orderNum = today() + getKey();
        return orderNum;
    }

    private static String today() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
        String formatString = simpleDateFormat.format(new Date());
        return formatString;
    }

    private static String getKey() {
        Random rnd = new Random();
        StringBuffer buf = new StringBuffer();
        for ( int i = 0 ; i < 6 ; i++ ) {
            if (rnd.nextBoolean())
                buf.append((char)(rnd.nextInt(26) + 65));
            else
                buf.append(rnd.nextInt(10));
        }
        return buf.toString();
    }
}
