package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    OrderRepository orderRepository;
    @Mock
    PaymentRepository paymentRepository;

    List<Order> orders;
    List<Payment> payments;

    Map<String, String> voucherPayment;
    Map<String, String> CODPayment;



    @BeforeEach
    void setUp() {
        Map<String, String> voucherPayment = new HashMap<>();
        voucherPayment.put("voucherCode", "ESHOP1234ABC5678");

        Map<String, String> CODPayment = new HashMap<>();
        CODPayment.put("address", "Kyoto");
        CODPayment.put("deliveryFee", "9999");

        payments = new ArrayList<>();
        Payment payment1 = new Payment("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", "voucherCode", voucherPayment, PaymentStatus.SUCCESS.getValue());
        payments.add(payment1);

        Payment payment2 = new Payment("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", "voucherCode", voucherPayment, PaymentStatus.REJECTED.getValue());
        payments.add(payment2);

        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);

        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078", products, 1708570000L, "Safira Sudrajat");
        orders.add(order2);
    }

    @Test
    void testAddPaymentSuccess() {
        Payment payment = payments.getFirst();
        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Order order = orders.getFirst();
        doReturn(order).when(orderRepository).findById(order.getId());
        order.setStatus(OrderStatus.SUCCESS.getValue());
        doReturn(order).when(orderRepository).save(order);

        Payment result = paymentService.addPayment(orders.getFirst(), payment.getMethod(), payment.getPaymentData());
        assertEquals(result.getId(), payment.getId());
        assertEquals(order.getId(), paymentService.paymentIdToOrderId().get(payment.getId()));

        verify(paymentRepository, times(1)).save(any(Payment.class));
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testAddPaymentRejected() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Order order = orders.get(1);
        doReturn(order).when(orderRepository).findById(order.getId());
        order.setStatus(OrderStatus.FAILED.getValue());
        doReturn(order).when(orderRepository).save(order);

        Payment result = paymentService.addPayment(orders.get(1), payment.getMethod(), payment.getPaymentData());
        assertEquals(result.getId(), payment.getId());
        assertEquals(order.getId(), paymentService.paymentIdToOrderId().get(payment.getId()));

        verify(paymentRepository, times(1)).save(any(Payment.class));
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testAddPaymentInvalidOrder() {
        Payment payment = payments.get(1);
        Order order = new Order("invalidOrderId", orders.getFirst().getProducts(), orders.getFirst().getOrderTime(), orders.getFirst().getAuthor());
        doReturn(null).when(orderRepository).findById(order.getId());

        assertThrows(NoSuchElementException.class, () ->
                paymentService.addPayment(order, payment.getMethod(), payment.getPaymentData())
        );

        verify(paymentRepository, times(0)).save(any(Payment.class));

    }

    @Test
    void testAddPaymentWithShortVoucherCode() {
        Order order = orders.getFirst();
        doReturn(order).when(orderRepository).findById(order.getId());

        voucherPayment.put("voucherCode", "ESHOP1234ABC");
        Payment payment = paymentService.addPayment(orders.getFirst(), "voucherCode", voucherPayment);

        assertNotNull(payment);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", payment.getId());
        assertEquals("voucherCode", payment.getMethod());
        assertEquals("ESHOP1234ABC", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testAddPaymentWithLongVoucherCode() {
        Order order = orders.getFirst();
        doReturn(order).when(orderRepository).findById(order.getId());

        voucherPayment.put("voucherCode", "ESHOP1234ABC5678ACBDEF");
        Payment payment = paymentService.addPayment(orders.getFirst(), "voucherCode", voucherPayment);

        assertNotNull(payment);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", payment.getId());
        assertEquals("voucherCode", payment.getMethod());
        assertEquals("ESHOP1234ABC5678ACBDEF", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testAddPaymentWithVoucherCodeNoESHOP() {
        Order order = orders.getFirst();
        doReturn(order).when(orderRepository).findById(order.getId());

        voucherPayment.put("voucherCode", "STEPHCURRY1234ABC5678");
        Payment payment = paymentService.addPayment(orders.getFirst(), "voucherCode", voucherPayment);

        assertNotNull(payment);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", payment.getId());
        assertEquals("voucherCode", payment.getMethod());
        assertEquals("STEPHCURRY1234ABC5678", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testAddPaymentWithVoucherCodeLowerCaseESHOP() {
        Order order = orders.getFirst();
        doReturn(order).when(orderRepository).findById(order.getId());

        voucherPayment.put("voucherCode", "eshop1234abc5678");
        Payment payment = paymentService.addPayment(orders.getFirst(), "voucherCode", voucherPayment);

        assertNotNull(payment);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", payment.getId());
        assertEquals("voucherCode", payment.getMethod());
        assertEquals("eshop1234abc5678", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testAddPaymentWithLessDigitsVoucherCode() {
        Order order = orders.getFirst();
        doReturn(order).when(orderRepository).findById(order.getId());

        voucherPayment.put("voucherCode", "ESHOP1234ABCDEFG");
        Payment payment = paymentService.addPayment(orders.getFirst(), "voucherCode", voucherPayment);

        assertNotNull(payment);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", payment.getId());
        assertEquals("voucherCode", payment.getMethod());
        assertEquals("ESHOP1234ABCDEFG", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testAddPaymentWithMoreDigitsVoucherCode() {
        Order order = orders.getFirst();
        doReturn(order).when(orderRepository).findById(order.getId());

        voucherPayment.put("voucherCode", "ESHOP12349995678");
        Payment payment = paymentService.addPayment(orders.getFirst(), "voucherCode", voucherPayment);

        assertNotNull(payment);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", payment.getId());
        assertEquals("voucherCode", payment.getMethod());
        assertEquals("ESHOP12349995678", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testAddPaymentWithNullVoucherCode() {
        Order order = orders.getFirst();
        doReturn(order).when(orderRepository).findById(order.getId());

        voucherPayment.put("voucherCode", null);
        Payment payment = paymentService.addPayment(orders.getFirst(), "voucherCode", voucherPayment);

        assertNotNull(payment);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", payment.getId());
        assertEquals("voucherCode", payment.getMethod());
        assertNull(payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testAddPaymentWithNoVoucherCode() {
        Order order = orders.getFirst();
        doReturn(order).when(orderRepository).findById(order.getId());
        assertThrows(IllegalArgumentException.class,
                () -> paymentService.addPayment(orders.getFirst(), "voucherCode", CODPayment));
    }

    @Test
    void testAddPaymentWithCODSuccess() {
        Order order = orders.getFirst();
        doReturn(order).when(orderRepository).findById(order.getId());

        Payment payment = paymentService.addPayment(orders.getFirst(), "cashOnDelivery", CODPayment);

        assertNotNull(payment);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", payment.getId());
        assertEquals("cashOnDelivery", payment.getMethod());
        assertEquals("Kyoto", payment.getPaymentData().get("address"));
        assertEquals("9999", payment.getPaymentData().get("deliveryFee"));
    }

    @Test
    void testAddPaymentWithCODEmptyAddress() {
        Order order = orders.getFirst();
        doReturn(order).when(orderRepository).findById(order.getId());

        CODPayment.put("address", "");
        Payment payment = paymentService.addPayment(orders.getFirst(), "cashOnDelivery", CODPayment);

        assertNotNull(payment);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", payment.getId());
        assertEquals("cashOnDelivery", payment.getMethod());
        assertEquals("", payment.getPaymentData().get("address"));
        assertEquals("9999", payment.getPaymentData().get("deliveryFee"));
    }

    @Test
    void testAddPaymentWithCODNullAddress() {
        Order order = orders.getFirst();
        doReturn(order).when(orderRepository).findById(order.getId());

        CODPayment.put("address", null);
        Payment payment = paymentService.addPayment(orders.getFirst(), "cashOnDelivery", CODPayment);

        assertNotNull(payment);
        assertEquals("REJECTED", payment.getStatus());
        assertEquals("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", payment.getId());
        assertEquals("cashOnDelivery", payment.getMethod());
        assertNull(payment.getPaymentData().get("address"));
        assertEquals("9999", payment.getPaymentData().get("deliveryFee"));
    }

    @Test
    void testAddPaymentWithCODEmptyDeliveryFee() {
        Order order = orders.getFirst();
        doReturn(order).when(orderRepository).findById(order.getId());

        CODPayment.put("deliveryFee", "");
        Payment payment = paymentService.addPayment(orders.getFirst(), "cashOnDelivery", CODPayment);

        assertNotNull(payment);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", payment.getId());
        assertEquals("cashOnDelivery", payment.getMethod());
        assertEquals("Kyoto", payment.getPaymentData().get("address"));
        assertEquals("", payment.getPaymentData().get("deliveryFee"));
    }

    @Test
    void testAddPaymentWithCODNullDeliveryFee() {
        Order order = orders.getFirst();
        doReturn(order).when(orderRepository).findById(order.getId());

        CODPayment.put("deliveryFee", null);
        Payment payment = paymentService.addPayment(orders.getFirst(), "cashOnDelivery", CODPayment);

        assertNotNull(payment);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals("2176d4b5-2b9f-4c21-9a58-23692ebcefbf", payment.getId());
        assertEquals("cashOnDelivery", payment.getMethod());
        assertEquals("Kyoto", payment.getPaymentData().get("address"));
        assertEquals("", payment.getPaymentData().get("deliveryFee"));
    }

    @Test
    void testAddPaymentWithNoAddressInPaymentData() {
        Order order = orders.getFirst();
        doReturn(order).when(orderRepository).findById(order.getId());

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("deliveryFee", "9999");

        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.addPayment(orders.getFirst(), "cashOnDelivery", paymentData);
        });
    }

    @Test
    void testAddPaymentWithNoDeliveryFeeInPaymentData() {
        Order order = orders.getFirst();
        doReturn(order).when(orderRepository).findById(order.getId());

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Kyoto");

        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.addPayment(orders.getFirst(), "cashOnDelivery", paymentData);
        });
    }

    @Test
    void testAddPaymentWithCODInvalidMethod() {
        Order order = orders.getFirst();
        doReturn(order).when(orderRepository).findById(order.getId());

        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.addPayment(orders.getFirst(), "cashOnBaby", CODPayment);
        });
    }

    @Test
    void testSetPaymentStatusToSuccess() {
        Payment payment = payments.get(1);
        Order order = orders.get(1);
        order.setStatus(OrderStatus.SUCCESS.getValue());

        doReturn(payment).when(paymentRepository).findById(payment.getId());
        doReturn(order).when(orderRepository).findById(order.getId());

        paymentService.addPayment(order, payment.getMethod(), payment.getPaymentData());

        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        doReturn(payment).when(paymentRepository).save(any(Payment.class));
        doReturn(order).when(orderRepository).findById(order.getId());

        Payment result = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());

        assertEquals(payment.getId(), result.getId());
        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        verify(paymentRepository, times(1)).findById(payment.getId());
        verify(paymentRepository, times(2)).save(any(Payment.class));
        verify(orderRepository, times(2)).save(order);
    }

    @Test
    void testSetPaymentStatusToReject() {
        Payment payment = payments.getFirst();
        Order order = orders.getFirst();
        order.setStatus(OrderStatus.FAILED.getValue());

        doReturn(payment).when(paymentRepository).findById(payment.getId());
        doReturn(order).when(orderRepository).findById(order.getId());

        paymentService.addPayment(order, payment.getMethod(), payment.getPaymentData());

        payment.setStatus(PaymentStatus.REJECTED.getValue());
        doReturn(payment).when(paymentRepository).save(any(Payment.class));
        doReturn(order).when(orderRepository).save(order);

        Payment result = paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());

        assertEquals(payment.getId(), result.getId());
        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
        verify(paymentRepository, times(1)).findById(payment.getId());
        verify(paymentRepository, times(2)).save(any(Payment.class));
        verify(orderRepository, times(2)).save(order);
    }

    @Test
    void testUpdatePaymentStatusInvalidStatus() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        assertThrows(IllegalArgumentException.class,
                () -> paymentService.setStatus(payment, "MEOW"));

        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testUpdatePaymentStatusInvalidPaymentId() {
        doReturn(null).when(paymentRepository).findById("invalidPaymentId");
        Payment payment = new Payment("invalidPaymentId", "voucherCode", voucherPayment);

        assertThrows(NoSuchElementException.class,
                () -> paymentService.setStatus(payment, OrderStatus.SUCCESS.getValue()));

        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testGetPaymentIfPaymentExists() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.getPayment(payment.getId());

        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testGetPaymentIfPaymentDoesNotExist() {
        doReturn(null).when(paymentRepository).findById("invalidPaymentId");

        assertNull(paymentService.getPayment("invalidPaymentId"));
    }

    @Test
    void testGetAllPayment() {
        doReturn(payments).when(paymentRepository).findAllPayments();

        List<Payment> results = paymentService.getAllPayments();

        int i = 0;
        while (i < payments.size()) {
            assertEquals(payments.get(i).getId(), results.get(i).getId());
            i++;
        }

        assertEquals(2, results.size());
    }
}
