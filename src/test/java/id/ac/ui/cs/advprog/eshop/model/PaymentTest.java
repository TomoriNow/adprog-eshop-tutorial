package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
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
        Map<String, String> voucherPayment = new HashMap<>();
        voucherPayment.put("voucherCode", "ESHOP1234ABC5678");

        Map<String, String> CODPayment = new HashMap<>();
        CODPayment.put("address", "Kyoto");
        CODPayment.put("deliveryFee", "9999");

        voucherPayment.clear();
        CODPayment.clear();

        Payment payment1 = new Payment("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", "voucherCode", voucherPayment);
        Payment payment2 = new Payment("9cd6f93a-77f8-4a1a-a259-484e1b7a54dd", "cashOnDelivery", CODPayment);

        assertTrue(payment1.getPaymentData().isEmpty());
        assertTrue(payment2.getPaymentData().isEmpty());
    }

    @Test
    void testCreatePaymentSuccessStatus() {
        Payment payment = new Payment("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", "voucherCode", voucherPayment, PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentRejectedStatus() {
        Payment payment = new Payment("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", "voucherCode", voucherPayment, PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetStatusToSuccess() {
        Payment payment = new Payment("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", "voucherCode", voucherPayment);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testSetStatusToRejected() {
        Payment payment = new Payment("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", "voucherCode", voucherPayment);
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }


    @Test
    void testSetStatusToInvalidStatus() {
        Payment payment = new Payment("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", "voucherCode", voucherPayment);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("MEOW"));
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
        Map<String, String> voucherPayment = new HashMap<>();
        voucherPayment.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", "voucherCode", voucherPayment, PaymentStatus.SUCCESS.getValue());

        assertNotNull(payment);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", payment.getId());
        assertEquals("voucherCode", payment.getMethod());
        assertEquals("ESHOP1234ABC5678", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testAddPaymentWithCODSuccess() {
        Map<String, String> CODPayment = new HashMap<>();
        CODPayment.put("address", "Kyoto");
        CODPayment.put("deliveryFee", "9999");
        Payment payment = new Payment("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", "cashOnDelivery", CODPayment, PaymentStatus.SUCCESS.getValue());

        assertNotNull(payment);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", payment.getId());
        assertEquals("cashOnDelivery", payment.getMethod());
        assertEquals("Kyoto", payment.getPaymentData().get("address"));
        assertEquals("9999", payment.getPaymentData().get("deliveryFee"));
    }
}
