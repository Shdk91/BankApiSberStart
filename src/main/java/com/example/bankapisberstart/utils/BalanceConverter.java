package com.example.bankapisberstart.utils;

public class BalanceConverter {

    /**
     * Данный метод предназначен для конвертации баланса из числа в строку.
     * Так как в БД мы храним баланс в виде количества копеек в формате Long,
     * то клиенту баланс возвращается в виде строки формата "1.00".
     * @param balance
     * @return
     */
    public static String convertBalanceFromOutDto(Long balance) {
        if (balance == 0) {
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
