package it.tim.gr.validation;

import org.junit.Test;
import org.springframework.http.HttpHeaders;

import it.tim.gr.model.configuration.Constants;
import it.tim.gr.model.domain.CreditCardData;
import it.tim.gr.model.exception.BadRequestException;
import it.tim.gr.model.web.ScratchCardRequest;

/**
 * Created by alongo on 30/04/18.
 */
public class GRControllerValidatorTest {

    @Test
    public void validatePrivateConstructor() throws Exception {
        new GRControllerValidator();
    }

    @Test
    public void validateScratchCardRequestOk() throws Exception {
        ScratchCardRequest request = new ScratchCardRequest(
                "1111111111111111",
                "3400000001",
                "3400000002",
                Constants.Subsystems.MYTIMAPP.name()
        );
        GRControllerValidator.validateScratchCardRequest(
                "1111111111111111",
                "3400000001",
                "3400000002",
                Constants.Subsystems.MYTIMAPP.name()
        );
    }

    @Test(expected = BadRequestException.class)
    public void validateScratchCardNoCardNumber() throws Exception {
        GRControllerValidator.validateScratchCardRequest(
                null, "3400000001", "3400000002", Constants.Subsystems.MYTIMAPP.name()
                );
    }

    @Test(expected = BadRequestException.class)
    public void validateScratchCardNoFromMsisnd() throws Exception {
        GRControllerValidator.validateScratchCardRequest(
                "1234", null, "3400000002", Constants.Subsystems.MYTIMAPP.name() );
    }

    @Test(expected = BadRequestException.class)
    public void validateScratchCardNoToMsisdn() throws Exception {
        GRControllerValidator.validateScratchCardRequest(
                "1234", "3400000001", null, Constants.Subsystems.MYTIMAPP.name() );
    }

    @Test(expected = BadRequestException.class)
    public void validateScratchCardNoSubSys() throws Exception {
        GRControllerValidator.validateScratchCardRequest(
                "1234", "3400000001", "3400000002", null );
    }

    @Test(expected = BadRequestException.class)
    public void validateScratchCardUnsupportedSubSys() throws Exception {
        GRControllerValidator.validateScratchCardRequest(
                "1234", "3400000001", "3400000002", "A_SUBSYS" );
    }

    @Test(expected = BadRequestException.class)
    public void validateScratchCardNoUserReference() throws Exception {
        GRControllerValidator.validateScratchCardRequest(
                "1234", "3400000001", "3400000002", Constants.Subsystems.MYTIMAPP.name() );
    }

    @Test(expected = BadRequestException.class)
    public void validateCheckoutRequestOk() throws Exception {
        GRControllerValidator.validateCheckoutRequest(
                20, new CreditCardData("4129339599543824", "10", "2020", "123", "Someone"), "3400000001", "3400000001", Constants.Subsystems.MYTIMAPP.name(), new HttpHeaders());
    }

    @Test(expected = BadRequestException.class)
    public void validateCheckoutRequestKoBadAmount() throws Exception {
        GRControllerValidator.validateCheckoutRequest(
                null, new CreditCardData("4129339599543824", "10", "2020", "123", "Someone"), "3400000001", "3400000001", Constants.Subsystems.MYTIMAPP.name(), new HttpHeaders());
    }

    @Test(expected = BadRequestException.class)
    public void validateCheckoutRequestKoBadCDC() throws Exception {
        GRControllerValidator.validateCheckoutRequest(
                20, null, "3400000001", "3400000001", Constants.Subsystems.MYTIMAPP.name(), new HttpHeaders());
    }

    @Test(expected = BadRequestException.class)
    public void validateCheckoutRequestKoBadFromMsisdn() throws Exception {
        GRControllerValidator.validateCheckoutRequest(
                20, new CreditCardData("4129339599543824", "10", "2020", "123", "Someone"), "123", "3400000001", Constants.Subsystems.MYTIMAPP.name(), new HttpHeaders());
    }

    @Test(expected = BadRequestException.class)
    public void validateCheckoutRequestKoBadToMsisdn() throws Exception {
        GRControllerValidator.validateCheckoutRequest(
                20, new CreditCardData("4129339599543824", "10", "2020", "123", "Someone"), "3400000001", "", Constants.Subsystems.MYTIMAPP.name(), new HttpHeaders());
    }

    @Test(expected = BadRequestException.class)
    public void validateCheckoutRequestKoBadSubsys() throws Exception {
        GRControllerValidator.validateCheckoutRequest(
                20, new CreditCardData("4129339599543824", "10", "2020", "123", "Someone"), "3400000001", "3400000002", "A Subsys", new HttpHeaders());
    }

    @Test(expected = BadRequestException.class)
    public void validateCheckoutRequestKoBadCustomer() throws Exception {
        GRControllerValidator.validateCheckoutRequest(
                20, new CreditCardData("4129339599543824", "10", "2020", "123", "Someone"), "3400000001", "3400000002", Constants.Subsystems.MYTIMAPP.name(), new HttpHeaders() );
    }

    @Test(expected = BadRequestException.class)
    public void validateCheckoutRequestKoBadDeviceType() throws Exception {
        GRControllerValidator.validateCheckoutRequest(
                20, new CreditCardData("4129339599543824", "10", "2020", "123", "Someone"), "3400000001", "3400000002", Constants.Subsystems.MYTIMAPP.name(), new HttpHeaders() );
    }


 

}