package kz.alibek.core;

import java.util.Random;

/**
 * This class provides functionality for generating an International Bank Account Number (IBAN) for a bank in Kazakhstan.
 * The generated IBAN consists of a country code ("KZ"), control digits, a bank code and an account number.
 */
public class IBANGenerator {

  /**
   * Generates an IBAN for a bank in Kazakhstan.
   *
   * @return a String representing the generated IBAN
   */
  public static String generateIBAN() {
    String bankCode = "125";
    String accountNumber = generateRandomDigits(13);
    String bban = bankCode + accountNumber;

    String controlDigits = "00";

    return "KZ" + controlDigits + bban;
  }

  /**
   * Generates a string of random digits of the given length.
   *
   * @param n the number of random digits to generate
   * @return a String of n random digits
   */
  private static String generateRandomDigits(int n) {
    Random random = new Random();
    StringBuilder sb = new StringBuilder(n);
    for (int i = 0; i < n; i++) {
      sb.append((char) ('0' + random.nextInt(10)));
    }
    return sb.toString();
  }
}
