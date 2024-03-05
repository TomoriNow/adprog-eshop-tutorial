package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    OrderRepository orderRepository;

    Map<String, String> IdHash = new HashMap<>();

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        String status;
        Order specifiedOrder = orderRepository.findById(order.getId());

        if (specifiedOrder == null){
            throw new NoSuchElementException();
        }

        status = switch (method) {
            case "voucherCode" -> paymentByVoucherCode(paymentData);
            case "cashOnDelivery" -> paymentByCOD(paymentData);
            default -> throw new IllegalArgumentException();
        };

        Payment payment = new Payment(UUID.randomUUID().toString(), method, paymentData, status);

        switch (status) {
            case "SUCCESS" -> specifiedOrder.setStatus(OrderStatus.SUCCESS.getValue());
            case "REJECTED" -> specifiedOrder.setStatus(OrderStatus.FAILED.getValue());
        }

        orderRepository.save(specifiedOrder);
        IdHash.put(payment.getId(), specifiedOrder.getId());
        paymentRepository.save(payment);

        return payment;

    }

    private String paymentByVoucherCode(Map<String, String> paymentData) {
        String voucherStatus;

        if (!paymentData.containsKey("voucherCode")) {
            throw new IllegalArgumentException();
        }

        if (isValidVoucherCode(paymentData.get("voucherCode"))) {
            voucherStatus = PaymentStatus.SUCCESS.getValue();
        } else {
            voucherStatus = PaymentStatus.REJECTED.getValue();
        }

        return voucherStatus;
    }

    private String paymentByCOD(Map<String, String> paymentData) {
        if (!paymentData.containsKey("address") || !paymentData.containsKey("deliveryFee")) {
            throw new IllegalArgumentException();
        }

        String address = paymentData.get("address");
        String deliveryFee = paymentData.get("deliveryFee");

        if (isEmptyOrNullOrWhiteSpace(address) || isEmptyOrNullOrWhiteSpace(deliveryFee)) {
            return PaymentStatus.REJECTED.getValue();
        }

        return PaymentStatus.SUCCESS.getValue();
    }

    private boolean isValidVoucherCode(String voucherCode) {
        return voucherCode != null &&
                voucherCode.length() == 16 &&
                voucherCode.startsWith("ESHOP") &&
                getDigitCount(voucherCode) == 8;
    }

    private int getDigitCount(String input) {
        return (int) input.chars().filter(Character::isDigit).count();
    }

    private boolean isEmptyOrNullOrWhiteSpace(String str) {
        return str == null || str.isEmpty();
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        Payment existingPayment = paymentRepository.findById(payment.getId());

        if (existingPayment == null) {
            throw new NoSuchElementException();
        }

        existingPayment.setStatus(status);
        paymentRepository.save(existingPayment);

        String orderId = paymentIdToOrderId().get(payment.getId());
        Order associatedOrder = orderRepository.findById(orderId);

        if (PaymentStatus.SUCCESS.getValue().equals(status)) {
            associatedOrder.setStatus(OrderStatus.SUCCESS.getValue());
        } else if (PaymentStatus.REJECTED.getValue().equals(status)) {
            associatedOrder.setStatus(OrderStatus.FAILED.getValue());
        }

        orderRepository.save(associatedOrder);
        return existingPayment;
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {return paymentRepository.findAllPayments();}

    public Map<String,String> paymentIdToOrderId() {return IdHash;}
}
