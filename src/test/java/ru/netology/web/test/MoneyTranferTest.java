package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.Login;

import static org.junit.jupiter.api.Assertions.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class MoneyTranferTest {

    @Test
    void shouldTransferMoneyFromFirstToSecond() {
        //подключаемся к серверу
        open("http://localhost:9999");
        //логинимся
        val loginPage = new Login();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        //проверяем подключение к Dashboard и создаем переменную класса DashboardPage для тестов
        val expected = verificationPage.validVerify(verificationCode);
        //настройка параметров перевода
        int BalanceFirstCard=expected.getFirstCardBalance();
        int BalanceSecondCard=expected.getSecondCardBalance();
        Integer Transfer=100;
        //выбираем первую карту
        $$(".list__item .button__text").first().click();
        //заполняем поля для перевода
        $$(".input__control[type=text]").first().setValue(Transfer.toString());
        $(".input__control[type=tel]").setValue("5559000000000002");
        $(withText("Пополнить")).click();
        //проверяем результат
        int expectBalanceFirstCard = BalanceFirstCard+Transfer;
        int expectBalanceSecondCard = BalanceSecondCard-Transfer;
        assertEquals(expectBalanceFirstCard, expected.getFirstCardBalance());
        assertEquals(expectBalanceSecondCard, expected.getSecondCardBalance());
    }

    @Test
    void shouldTransferMoneyFromSecondToFirst() {
        //подключаемся к серверу
        open("http://localhost:9999");
        //логинимся
        val loginPage = new Login();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        //проверяем подключение к Dashboard и создаем переменную класса DashboardPage для тестов
        val expected = verificationPage.validVerify(verificationCode);
        //настройка параметров перевода
        int BalanceFirstCard=expected.getFirstCardBalance();
        int BalanceSecondCard=expected.getSecondCardBalance();
        Integer Transfer=100;
        //выбираем вторую карту
        $$(".list__item .button__text").last().click();
        //заполняем поля для перевода
        $$(".input__control[type=text]").first().setValue(Transfer.toString());
        $(".input__control[type=tel]").setValue("5559000000000001");
        $(withText("Пополнить")).click();
        //проверяем результат
        int expectBalanceFirstCard = BalanceFirstCard-Transfer;
        int expectBalanceSecondCard = BalanceSecondCard+Transfer;
        assertEquals(expectBalanceFirstCard, expected.getFirstCardBalance());
        assertEquals(expectBalanceSecondCard, expected.getSecondCardBalance());
    }

    @Test
    void shouldAbortButtonFromFirstToSecond() {
        //подключаемся к серверу
        open("http://localhost:9999");
        //логинимся
        val loginPage = new Login();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        //проверяем подключение к Dashboard и создаем переменную класса DashboardPage для тестов
        val expected = verificationPage.validVerify(verificationCode);
        //настройка параметров перевода
        int BalanceFirstCard=expected.getFirstCardBalance();
        int BalanceSecondCard=expected.getSecondCardBalance();
        Integer Transfer=100;
        //выбираем первую карту
        $$(".list__item .button__text").first().click();
        //заполняем поля для перевода
        $$(".input__control[type=text]").first().setValue(Transfer.toString());
        $(".input__control[type=tel]").setValue("5559000000000002");
        $(withText("Отмена")).click();
        //проверяем результат
        int expectBalanceFirstCard = BalanceFirstCard;
        int expectBalanceSecondCard = BalanceSecondCard;
        assertEquals(expectBalanceFirstCard, expected.getFirstCardBalance());
        assertEquals(expectBalanceSecondCard, expected.getSecondCardBalance());
    }

//    @Test
//    void shouldNotTransferMoneyFromFirstToSecond() {
//        //подключаемся к серверу
//        open("http://localhost:9999");
//        //логинимся
//        val loginPage = new Login();
//        val authInfo = DataHelper.getAuthInfo();
//        val verificationPage = loginPage.validLogin(authInfo);
//        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
//        //проверяем подключение к Dashboard и создаем переменную класса DashboardPage для тестов
//        val expected = verificationPage.validVerify(verificationCode);
//        //настройка параметров перевода
//        int BalanceFirstCard=expected.getFirstCardBalance();
//        int BalanceSecondCard=expected.getSecondCardBalance();
//        Integer Transfer=11000;
//        //выбираем первую карту
//        $$(".list__item .button__text").first().click();
//        //заполняем поля для перевода
//        $$(".input__control[type=text]").first().setValue(Transfer.toString());
//        $(".input__control[type=tel]").setValue("5559000000000002");
//        $(withText("Пополнить")).click();
//        //проверяем результат
//        int expectBalanceFirstCard = BalanceFirstCard;
//        int expectBalanceSecondCard = BalanceSecondCard;
//        assertEquals(expectBalanceFirstCard, expected.getFirstCardBalance());
//        assertEquals(expectBalanceSecondCard, expected.getSecondCardBalance());
//    }

}
