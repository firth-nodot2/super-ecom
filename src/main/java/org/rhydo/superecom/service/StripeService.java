package org.rhydo.superecom.service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.rhydo.superecom.dto.StripePaymentDTO;

public interface StripeService {
    PaymentIntent paymentIntent(StripePaymentDTO stripePaymentDTO) throws StripeException;
}
