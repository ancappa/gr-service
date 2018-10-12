package it.tim.gr.validation;

import java.util.function.Predicate;

import org.springframework.http.HttpHeaders;

import it.tim.gr.model.configuration.Constants;
import it.tim.gr.model.domain.CreditCardData;
import it.tim.gr.model.exception.BadRequestException;

/**
 * Created by alongo on 30/04/18.
 */
public class GRControllerValidator {

    GRControllerValidator() {}

    public static void validateScratchCardRequest(String cardNumber, String fromMsisdn, String toMsisdn, String subsystem) {
        boolean valid = validateStrings(CommonValidators.validScratchCard, cardNumber);
        valid = valid && validateStrings(CommonValidators.validPhoneNumber, fromMsisdn, toMsisdn);
        valid = valid && Constants.Subsystems.contains(subsystem);
        if(!valid)
            throw new BadRequestException("Missing/Wrong parameters in ScratchCardRequest");

    }

    public static void validateCheckoutRequest(Integer amount, CreditCardData cdc, String fromMsisdn, String toMsisdn, String subsystem, HttpHeaders headers) {

        boolean valid = amount != null && amount > 0
                && validateStrings(CommonValidators.validPhoneNumber, fromMsisdn, toMsisdn)
                && Constants.Subsystems.contains(subsystem)
                && CreditCardValidator.isValid.test(cdc);
        if(!valid)
            throw new BadRequestException("Missing/Wrong parameters in CheckoutRequest");

        if(headers == null)
            throw new BadRequestException("Missing user reference");

        String deviceType = headers.getFirst("deviceType");
        if(!Constants.DeviceType.contains(deviceType))
            throw new BadRequestException("Bad device type in header: "+deviceType);
    }

    //UTIL

    private static boolean validateStrings(Predicate<String> predicate, String... strings){
        for(String s : strings){
            if(!predicate.test(s))
                return false;
        }
        return true;
    }

}
