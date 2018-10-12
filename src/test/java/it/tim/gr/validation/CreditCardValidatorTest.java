package it.tim.gr.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import it.tim.gr.model.domain.CreditCardData;

@RunWith(MockitoJUnitRunner.class)
public class CreditCardValidatorTest {

    @Test
    public void isValidOk() {
        assertTrue(
            CreditCardValidator.isValid.test(new CreditCardData(
                "4129339599543824" //VISA
                , "10"
                , "2020"
                , "123"
                , "Someone"))
        );
    }

    @Test
    public void isNotValidNullCDC() {
        assertFalse(CreditCardValidator.isValid.test(null));
    }

    @Test
    public void isNotValidShortCDC() {
        assertFalse(
                CreditCardValidator.isValid.test(new CreditCardData(
                        "5555555"
                        , "10"
                        , "2020"
                        , "123"
                        , "Someone"))
        );
    }

    @Test
    public void isNotValidNoCardType() {
        assertFalse(
                CreditCardValidator.isValid.test(new CreditCardData(
                        "1111111111111111"
                        , "10"
                        , "2020"
                        , "123"
                        , "Someone"))
        );
    }

    @Test
    public void isNotValidWrongCVV() {
        assertFalse(
                CreditCardValidator.isValid.test(new CreditCardData(
                        "4129339599543824"
                        , "10"
                        , "2020"
                        , "1234"
                        , "Someone"))
        );
    }

    @Test
    public void isNotValidPassedDates() {
        assertFalse(
                CreditCardValidator.isValid.test(new CreditCardData(
                        "4129339599543824"
                        , "10"
                        , "2005"
                        , "123"
                        , "Someone"))
        );
    }

}