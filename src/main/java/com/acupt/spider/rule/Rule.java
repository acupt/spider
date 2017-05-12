package com.acupt.spider.rule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by liujie on 2017/5/11.
 */
public interface Rule {

    int WEB_BILIBILI = 1;

    int WEB_ACFUN = 2;

    /**
     * 根据用户生日计算年龄
     */
    static Integer getAgeByBirthday(String yyyyMMdd) {
        Date birthday = null;
        SimpleDateFormat sdfBirthday = new SimpleDateFormat("yyyy-MM-dd");
        try {
            birthday = sdfBirthday.parse(yyyyMMdd);
        } catch (ParseException e) {
            System.out.println("getAgeByBirthday error:" + yyyyMMdd);
        }
        if (birthday == null)
            return null;
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthday)) {
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthday);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                // monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                // monthNow>monthBirth
                age--;
            }
        }
        return age;
    }

}
