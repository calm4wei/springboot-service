package org.apache.commons.validator;

import org.apache.commons.validator.routines.*;
import org.apache.commons.validator.routines.DateValidator;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by weifeng on 2017/5/23.
 */
public class ValidatorTest {

    static String patternYMdHms = "yyyy-MM-dd HH:mm:ss";

    @Test
    public void test_valid_date() {
        DateValidator dateValidator = DateValidator.getInstance();
        String value = "2017/05/23";
        String pattern = "yyyy/MM/dd";
        Date fooDate = dateValidator.validate(value, pattern);
        boolean isValid = dateValidator.isValid(value, pattern);
        System.out.println(isValid);
        System.out.println(fooDate);
    }

    @Test
    public void test_valid_format() {
        DateValidator validator = DateValidator.getInstance();
        String value = "2017/05/23";
        String pattern = "yyyy-MM-dd";
        Date date = new Date();
        String pv = validator.format(date, pattern);
        System.out.println(pv);
    }

    @Test
    public void test_valid_compare_date_time() {
        DateValidator validator = DateValidator.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        Date date = calendar.getTime();
        System.out.println(validator.format(date, patternYMdHms));
        int comResult = validator.compareMonths(date, new Date(), null);
        System.out.println(comResult);
    }

    @Test
    public void test_valid_numeric() {
        IntegerValidator validator = IntegerValidator.getInstance();
        String fooString = "123";
        int validRes = validator.validate(fooString, "#,##0.00");
        System.out.println(validRes);

        IntegerValidator integerValidator =
                new IntegerValidator(true, IntegerValidator.CURRENCY_FORMAT);

        String pattern = "#,###" + '\u00A4' + '\u00A4';  // Use international symbol

        Integer fooAmount = integerValidator.validate("10.100EUR", pattern, Locale.GERMAN);
        System.out.println(fooAmount);
    }

    @Test
    public void test_valid_numeric_currency() {
        BigDecimalValidator validator = CurrencyValidator.getInstance();
        BigDecimal fooAmount = validator.validate("$12,500.00", Locale.US);
        System.out.println(fooAmount);
    }

    @Test
    public void test_valid_ip_address() {
        InetAddressValidator validator = InetAddressValidator.getInstance();
        boolean isValid = validator.isValid("192.168.0.255");
        System.out.println(isValid);
    }

    @Test
    public void test_valid_email_address() {
        // Get an EmailValidator
        EmailValidator validator = EmailValidator.getInstance();
        // Validate an email address
        boolean isAddressValid = validator.isValid("user@apache.org");
        System.out.println(isAddressValid);
    }

    @Test
    public void test_valid_domain() {
        // Get a DomainValidator
        DomainValidator validator = DomainValidator.getInstance();

        // Validate a domain name
        if (validator.isValid("www.apache.org")) {
            System.out.println("isValid");
        }
        if (!validator.isValid("www.apache.wrong")) {
            System.out.println("inValid");
        }
    }

}
