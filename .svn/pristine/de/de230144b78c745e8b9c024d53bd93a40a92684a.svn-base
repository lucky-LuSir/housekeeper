package com.kfwy.hkp.payment.business;


import com.kfwy.hkp.payment.entity.PaymentEntity;

import java.math.BigDecimal;


public interface IPaymentManager {

    /*public static final String ALI_SERVER_URL = "https://openapi.alipay.com/gateway.do";

    public static final String ALI_APP_ID = "2017053107388669";

    public static final String ALI_PRIVATE_KEY ="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCS9OS9unR0Y9+Si0LhM6br5slI7lbnOEV31UhGvvd9pqHA7BeHRp6tslOChsLC6txTu986cuUfKnMXX6ujTd32/0wDRIdmxLy7raYYxEj3kjOTbiz9mK2vpn4oOw8aaFw15Fue92R3ER6hrsfTqWoix/XsyOOWt7ykX6mcfwSRcgGvCyh+9KgrvbRYDn8lVmFj5Z4IjWAaobPFy/ZRBhqB5jowKxvvmCWyK2JErY72CkyVf9ivliY1WNQECLNw1XkTvVviEdhtytYJVcpf1Qr2dfOpAykOj75mc5r465Dh+yJZEBMSim10UqpQXDtBjAam1HrVLxJzGktmh4Pg2DRBAgMBAAECggEAV3FIumw75xWWbshWn1HwvOWyV6caJrMhAtDyFMaE1UtVbEoPJfsraAd4EW7GbNz7BQgBJ/I9y3Gi0gGnS6CFtvbp7/fS6Z+e/b2x24LQOB/SeKNSOgOjNaVH5ABgWEJIu/iJW+mw6fhR1br+RD/GW7PH7YELix8BLH2PmwMpYLEic0BbGJbzJ4PzaMjSsgfl5CSYLaomDArKCBbETQtgQLakbXC8k/2ooknkFQ8c9LmLM7zIA1jNw8HEAvtIZORm8V2vSZgEEfMQjfIz5S/9Q8/NEwdIIc1Yv17e5SXGM5+RrJn4MyYZBAijSpZRU8B6lW1T/QjVA7mj4x8xFm4jAQKBgQDOUCPga22qGXcv0dtmxHy5AEE9om7X2weY1MAfQhrn4pXWZK3mErNkpxnOW3eYh71RKbOLeLTawCjPqLzOyElukf8vStEtYQw1WRuez+abi3gGdyU1Hqs8gPkqN2BpGNPxYixi5B5ls6qib1snhrLBBar+APfjDJkgt/0043e92QKBgQC2WT1Dw3nkDwZz2Ei1uvsPUxvadm/pPDWE7kHuZ2NFqNpz5COFtkcrSbTQp7nSlpeO9muDH5vv6jOC0MjJHIc8mPphy+gcjhOPOC3DhxU0Lb+pD2LvOsYb4ATWWjWo+KuiOAOqoiF6x5qrWNBd82YjAe1sevSa2Vgtxn6fM17gqQKBgBA+bxS0YKYL6z5wn32v6sIGKxzXNTL5o9d8qMPNSKEJThLbSnXa8xTxsy50NS5lQZqqVeCdWGAA0TXIa1U8n8t840Kt+VpBUdzBb1NJuzZz/LdFmkBccc8JuJSCV6zur4FJ4YTHwTzwsHapndu210wrThNBsELIMyZIVj9byYnBAoGAIuTOyXHVmGObq31e5I/ETTltIg/5+FrSLv7c1P8pywG10+l7NrIbylfPZwJBl9/DXtMe3belwnJBqy3CzmFMK7dmamJ3p0c0zPpGH51w3ExI2dQc7YSEtn7WziHzFMOJLIClivh9iZ46pcFYFjJmgBp1UYXx2tPugMv0HQjahgkCgYARBumEtEoKyvEAPQ8n/0yl4GVeKheQbIuaq1dutV4bfIq558N5diFy5626/0E0qQdN4UsjEKxPCpLkNToRmghvfpl585Kqq+mJwO6XgokMgykOKJ+ksPmyM4qn1YvXKcPhrqEdjcM7KMYApdAQsJIVssMlvb88Qqy69/U91UVf+A==";

    public static final String ALI_PUBLIC_KEY ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkvTkvbp0dGPfkotC4TOm6+bJSO5W5zhFd9VIRr73faahwOwXh0aerbJTgobCwurcU7vfOnLlHypzF1+ro03d9v9MA0SHZsS8u62mGMRI95Izk24s/Zitr6Z+KDsPGmhcNeRbnvdkdxEeoa7H06lqIsf17Mjjlre8pF+pnH8EkXIBrwsofvSoK720WA5/JVZhY+WeCI1gGqGzxcv2UQYageY6MCsb75glsitiRK2O9gpMlX/Yr5YmNVjUBAizcNV5E71b4hHYbcrWCVXKX9UK9nXzqQMpDo++ZnOa+OuQ4fsiWRATEoptdFKqUFw7QYwGptR61S8ScxpLZoeD4Ng0QQIDAQAB";*/


    public static final String ALI_SERVER_URL = "https://openapi.alipaydev.com/gateway.do";

    public static final String ALI_APP_ID = "2016092700608221";

    public static final String ALI_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDgmzMAm8n0FerZBFfU9oXhyP+Q/8T8QCpazyegbuYgRrETlu/ouvfvFPjx1Cct5bNSZ3mjQbM3nXKLvd77fmJI8FqMJJFddEuaUbnAGqI28L/gxIrFjGNQojnPMbQ42IzeLMhQeKTnK3wiOm00stA5t+zTyR8fzk0tLjyh4KBf1PUUb6vPBARCTS10uGpEoZBgDaMk4xTeZ+VHhg/eIwZ5rS0oN/4wFw7m+m+NngaxXC1gqjzRwMh/GFulii6ihvFbiPHHbMSFyxW87hg8T0S+MW9vvjxzg555+lof7q3xx2dItfqeaFJYH3GaZuLR0UcqZrg6l6hpIt/KTYDVxm6/AgMBAAECggEAaUhWn/JmuAOOEiv2/9yO5UdShKVfsN5i/C4eZRWWyYpZdtVJn84Eag1qo/DObLU/WWEno18ooukqyFY4L6sOZyJDT23UtfnNdojYiTPy1c4EI1f9tJwZ8bIZZwXldMANSBe1zgK/SZ0YkvLQPeOTt3p/iMa90CrkAkmqIjco9cH8P3HKJ1R/1lapcD74eusJrQ47euGcvjH1GJAvctYgG5OPsA8HLXvQi/U6pFJrCwTyRhnKcgg8qhOSKEnINGYig/THXEEAUyGEcNzedTPTUSyDOPeU/xE5hexy/BnXXcadnQ0ugKFGHBnM44i735wAzLg+E7wOJ1YvPStu3afAAQKBgQD19bAH1ZvZHJya8BUCicaDTTF8pO4cEFkjkW//UZ03NOqdon47d1iIIVvX+jcucseEXrNlz85OANL8C+GxDZLXoI9vHaVKSbfnpvj2Nw3K+9kkR9whG65p0G3YFh4tESUUHLFIpjol+36liySwMLRb3/Mt5v7MtPiDSKFXOI0G9QKBgQDpxl1tXWsKyk1fyzwh6ga/AZgUSmGTNN5USk8QhhOEuJiZoCLme+tXxhrNw0r7u4OdtzJvcUIW37xWkWzi7irXx5SduKSSmajZqnrfNAlnlYvbYHW5SaXNALL3kSybnS8pztyZUi6mhFy44b68bS4/wGBAF+mkebq04Oq6mucGYwKBgQCMnuwGfqT8yzPqDUslyVl6pj6d0bnkx5//Ct6qbaHh91oPx+A95WzzX4cgAwlbf/n7/xy4H8gGpsf5nF1rmaQTrdNzptJil6USR09djXpQTA8IhCEhuK7OBoFWosab44p8Zi8hpw/luVTeFqBiL2ZehLqGyuNwbCMNBBBiSo2upQKBgBRLBOcuedFI+Gh9Yu3qu8Uu5T21qzy1FSup+60Khn057P1A+maZkTdgWglMj6DEiUtSkM09vqfvqhC1tQcJ96wdhhge5CJZtl+Wve3MPZjBUY+skuuPqhXenqqA7qdfgK2XaGzixmvsB4SBDCadL6uXDoPhz4VYj82NzLKAKdfTAoGAH3BJRsgNlEAY/931HvDPg84eQfw0tgkvCnVo7UEwCDmU9I4TszjaVTCvzBjrVH9RtSf9zcteGnoJGRYB3QejSxSbqMq7e1YgzoSrhuc8W+QT1xdTzsIjzHhw6jCrw2NsK3aPL8cd8e/7K5dGX7+BgsEJN9oIaJk4JkD5N8wNFoo=";

    public static final String ALI_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4JszAJvJ9BXq2QRX1PaF4cj/kP/E/EAqWs8noG7mIEaxE5bv6Lr37xT48dQnLeWzUmd5o0GzN51yi73e+35iSPBajCSRXXRLmlG5wBqiNvC/4MSKxYxjUKI5zzG0ONiM3izIUHik5yt8IjptNLLQObfs08kfH85NLS48oeCgX9T1FG+rzwQEQk0tdLhqRKGQYA2jJOMU3mflR4YP3iMGea0tKDf+MBcO5vpvjZ4GsVwtYKo80cDIfxhbpYouoobxW4jxx2zEhcsVvO4YPE9EvjFvb748c4OeefpaH+6t8cdnSLX6nmhSWB9xmmbi0dFHKma4OpeoaSLfyk2A1cZuvwIDAQAB";

    public static final String ALI_CHARSET = "UTF-8";

    public static final String ALI_SIGN_TYPE = "RSA2";

    public static final String ALI_FORMAT = "json";

    public static final String ALI_NOTIFY_URL="http://192.168.0.160:6789/housekeeper/paymentNotify/alipayNotify";


    public static final String WX_SERVER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    public static final String WX_MCH_ID = "1452333502";
    public static final String WX_APP_ID = "wx85a2ef913590f267";

    public static final String WX_APP_SECRET = "c03271f3708a75f42e2ce220c5de39c4";


    public String pay(String paymentType, PaymentEntity paymentEntity);


    /**
     * 创建并且支付订单
     *
     * @param odType
     * @param payType
     * @param payMoney
     * @param gdCode
     * @return
     */
    public String createAndPayOrder(String odType, String payType, BigDecimal payMoney, String gdCode);

    /**
     *
     * @param paymentType
     * @param tradeNo 支付时返回的支付宝交易号，与 outTradeNo 必填一个,支付宝28位交易号
     * @param outTradeNo 支付时传入的商户订单号，与 tradeNo 必填一个,支付时传入的商户订单号
     * @return
     */
    public String queryPayInfo(String paymentType,String tradeNo,String outTradeNo);
}
