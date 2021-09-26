package com.example.bankapisberstart.utils;

public class BalanceConverter {

    public static String convertBalanceFromOutDto(Long balance){
        if (balance == 0){
            return "0";
        }
        StringBuilder builder = new StringBuilder();
        String balanceBeforeConvert = balance.toString();
        int lastIndex = balanceBeforeConvert.length();
        builder
                .append(balanceBeforeConvert, 0, lastIndex - 2)
                .append(".")
                .append(balanceBeforeConvert, lastIndex - 2, lastIndex);
        return builder.toString();
    }
}
