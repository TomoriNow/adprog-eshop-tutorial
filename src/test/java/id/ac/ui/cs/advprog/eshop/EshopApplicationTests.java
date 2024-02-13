package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class EshopApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testMainMethod() {
		ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor));

		EshopApplication.main(new String[]{});

		System.setOut(System.out);

		String output = outputStreamCaptor.toString().trim();
		assertTrue(output.contains("Started EshopApplication"), "Expected output not found");
	}

}
