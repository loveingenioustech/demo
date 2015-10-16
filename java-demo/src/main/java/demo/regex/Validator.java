package demo.regex;

import java.util.regex.Pattern;

public class Validator
{
    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^1[3|4|5|8|7|2][0-9]\\d{8}$";

    public static boolean isMobile(String mobile)
    {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }
}
