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

    public static final String ADD_COUNTERPARTY_NORMAL =
            "{"
                    + "\"login\" : \"kozlovda\","
                    + "\"name\" : \"ИП пупкин\","
                    + "\"accountNumber\" : \"11111333334444400000\","
                    + "\"taxNumber\" : \"9998887770\""
                    +"}";

    public static final String ADD_COUNTERPARTY_FALSE_NAME =
            "{"
                    + "\"login\" : \"kozlova\","
                    + "\"name\" : \"ИП пупкин\","
                    + "\"accountNumber\" : \"11111333334444400000\","
                    + "\"taxNumber\" : \"9998887770\""
                    +"}";

    public static final String ADD_COUNTERPARTY_NOT_VALID_TAXNUMBER =
            "{"
                    + "\"login\" : \"kozlovda\","
                    + "\"name\" : \"ИП пупкин\","
                    + "\"accountNumber\" : \"11111333334444400000\","
                    + "\"taxNumber\" : \"99980\""
                    +"}";

    public static final String TRANSLATION_WITH_NORMAL_DATA =
            "{"
                    + "\"login\" : \"kozlovda\","
                    + "\"counterpartyId\" : 1,"
                    + "\"accountId\" : 1,"
                    + "\"sum\" : 5000"
                    +"}";

    public static final String TRANSLATION_WITH_FALSE_NAME =
            "{"
                    + "\"login\" : \"kozlova\","
                    + "\"counterpartyId\" : 1,"
                    + "\"accountId\" : 1,"
                    + "\"sum\" : 5000"
                    +"}";

    public static final String TRANSLATION_WITH_FALSE_COUNTERPARTIES =
            "{"
                    + "\"login\" : \"kozlovda\","
                    + "\"counterpartyId\" : 4,"
                    + "\"accountId\" : 1,"
                    + "\"sum\" : 5000"
                    +"}";

    public static final String TRANSLATION_WITH_FALSE_ACCOUNT =
            "{"
                    + "\"login\" : \"kozlovda\","
                    + "\"counterpartyId\" : 1,"
                    + "\"accountId\" : 5,"
                    + "\"sum\" : 5000"
                    +"}";

}
