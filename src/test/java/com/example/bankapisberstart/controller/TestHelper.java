package com.example.bankapisberstart.controller;

public class TestHelper {

    public static final String CREATE_CARD_NORMAL =
            "{"
                    + "\"login\" : \"kozlovda\","
                    + "\"accountNumber\" : \"22222222222222222222\""
            +"}";

    public static final String CREATE_CARD_FALSE_NAME =
            "{"
                    + "\"login\" : \"kozlova\","
                    + "\"accountNumber\" : \"22222222222222222222\""
                    +"}";

    public static final String CREATE_CARD_FALSE_NUMBER =
            "{"
                    + "\"login\" : \"kozlovda\","
                    + "\"accountNumber\" : \"2222222222222222222\""
                    +"}";

    public static final String ADD_CASH_NORMAL =
            "{"
                    + "\"login\" : \"kozlovda\","
                    + "\"number\" : \"22222222222222222222\","
                    + "\"sum\" : 100000"
                    +"}";
    public static final String ADD_CASH_FALSE_NAME =
            "{"
                    + "\"login\" : \"kozlova\","
                    + "\"number\" : \"22222222222222222222\","
                    + "\"sum\" : 100000"
                    +"}";
    public static final String ADD_CASH_FALSE_NUMBER =
            "{"
                    + "\"login\" : \"kozlovda\","
                    + "\"number\" : \"22222222222222222221\","
                    + "\"sum\" : 100000"
                    +"}";
}
