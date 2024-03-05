package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private Map<String, String> voucherPayment;
    private Map<String, String> CODPayment;

    @BeforeEach
    void setUp () {
        Map<String, String> voucherPayment = new HashMap<>();
        voucherPayment.put("voucherCode", "ESHOP1234ABC5678");

        Map<String, String> CODPayment = new HashMap<>();
        CODPayment.put("address", "Kyoto");
        CODPayment.put("deliveryFee", "9999");
    }

    @Test
    void testCreateEmptyPayment() {
        voucherPayment.clear();

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", "voucherCode", voucherPayment);
        });
    }

    @Test
    void testSetStatusToSuccess() {
        Payment payment = new Payment("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", "voucherCode", voucherPayment, "SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetStatusToRejected() {
        Payment payment = new Payment("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", "voucherCode", voucherPayment, "REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }


    @Test
    void testSetStatusToInvalidStatus() {
        Payment payment = new Payment("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", "voucherCode", voucherPayment);
        assertThrows(IllegalArgumentException.class, () -> voucherPayment.setStatus("MEOW"));
    }

    @Test
    void testAddPaymentWithVoucherInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", "voucherCode", voucherPayment, "MEOW");
        });
    }

    @Test
    void testAddPaymentWithCODInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", "voucherCode", CODPayment, "MEOW");
        });
    }

    @Test
    void testAddPaymentWithVoucherCodeSuccess() {
        Payment payment = new Payment("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", "voucherCode", voucherPayment, "SUCCESS");

        assertNotNull(payment);
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", payment.getId());
        assertEquals("voucherCode", payment.getMethod());
        assertEquals("ESHOP1234ABC5678", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testAddPaymentWithCODSuccess() {
        Payment payment = new Payment("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", "cashOnDelivery", CODPayment);

        assertNotNull(payment);
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", payment.getId());
        assertEquals("cashOnDelivery", payment.getMethod());
        assertEquals("Kyoto", payment.getPaymentData.get("address"));
        assertEquals("9999", payment.getPaymentData.get("deliveryFee"));
    }
}
