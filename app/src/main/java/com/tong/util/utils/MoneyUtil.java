package com.tong.util.utils;

import java.text.DecimalFormat;

public class MoneyUtil
{

    public static String changeIntoDisplayMoney(String moneyLabel, double payMoney)
    {
        return moneyLabel + formatMoneyByTwoPoint(payMoney);
    }

    public static String formatMoneyByTwoPoint(double payMoney)
    {
        // 设置字体的format，0.00意思是小数点后两位，位数不够则用0来补足，
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(payMoney);
    }

    /**
     * 方法表述 把元转换成分。比如0.02元转成2分
     */
    public static int convertYuanToFen(String amount)
    {
        int amountFen = -1;
        try {
            double amountD = Double.parseDouble(amount);
            amountFen = (int) Math.round(amountD * 100d);
        }
        catch (NumberFormatException e) {
            amountFen = -1;
        }
        return amountFen;
    }

    /**
     * 方法表述 分转换为元，保留小数点两位
     */
    public static String convertFenToYuan(long amount)
    {
        long total = amount;
        //若为负数，则记录到标志位中
        boolean isPositive = (total >= 0L);
        if (!isPositive) {
            total = total * -1L;
        }

        long fen = total % 100;
        long yuan = total / 100;
        String fenStr = null;
        if (fen < 10) {
            fenStr = "0" + String.valueOf(fen);
        }
        else {
            fenStr = String.valueOf(fen);
        }

        if (isPositive) {
            return String.valueOf(yuan) + "." + fenStr;
        }
        else {
            return "-" + String.valueOf(yuan) + "." + fenStr;
        }
    }

    /**
     * 分转换为元，保留小数点两位
     */
    public static String convertFenToYuan(String amount)
    {
        long total = 0L;
        try {
            total = Long.parseLong(amount);
        }
        catch (NumberFormatException e) {
            return "";
        }

        return convertFenToYuan(total);
    }
}
