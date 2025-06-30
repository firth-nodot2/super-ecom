package org.rhydo.superecom.service;

import jakarta.transaction.Transactional;
import org.rhydo.superecom.dto.OrderDTO;

public interface OrderService {
    @Transactional
    OrderDTO placeOrder(String emailId, Long addressId, String paymentMethod, String pgName, String pgPaymentId, String pgStatus, String pgResponseMessage);
}
