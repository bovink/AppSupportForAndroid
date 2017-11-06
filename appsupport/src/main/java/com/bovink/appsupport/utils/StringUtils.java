package com.bovink.appsupport.utils;

/**
 * Created by Retina975 on 17/6/5.
 * <p>
 * 字符串工具类
 */

public class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static String getTime(String time) {
        String s = time.replaceAll(" ", "").trim();

        String year = s.substring(0, 4);//substring包括开始不包括结尾
        String month = s.substring(5, 7).replaceFirst("^0*", "");
        String day = s.substring(8, 10).replaceFirst("^0*", "");
        String m = s.substring(10, 15);
        return year + "年" + month + "月" + day + "日" + " " + m;
    }

    /**
     * 判断电话号码是否符合规范
     *
     * @param number 电话号码
     * @return 电话号码是否符合规范
     */
    public static boolean isMobileNumber(String number) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		 * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
		 * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		 */
        String telRegex = "[1][3578]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (android.text.TextUtils.isEmpty(number)) {
            return false;
        } else {
            return number.matches(telRegex);
        }
    }
}
