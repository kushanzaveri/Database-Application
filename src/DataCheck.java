/**
 * The purpose of this class is to return true/false
 * when checking whether or not a phone number or
 * email address is valid. Both methods are static methods.
 * 
 * @author K Zaveri
 * @version 1 3.20.2015
 */
public class DataCheck
{
  /**
   * The purpose of this method is to return true/false depending
   * on whether or not the argument is properly formatted.
   * @param phone The phone String to be evaluated by the method.
   * @return Returns true if the phone number is valid and vice versa.
   */
  public static boolean checkPhone(String phone)
  {
    return phone.matches ("\\d{3}(\\-{1}\\d{3}\\-{1}\\d{4}|\\s{1}\\d{3}\\s{1}\\d{4}|\\d{7})");
  }
  /**
   * The purpose of this method is to return true/false depending
   * on whether or not the argument is properly formatted. There are
   * quite a few requirements for it to be a valid email:
   * The first section before the "at" sign can be alphanumeric characters, special characters and dots, if they aren't
   * at the ends.
   * The domain can have hyphens if they aren't at the ends.
   * The top-level domain cannot end with a dot and cannot contain numbers.
   * 
   * @param email The email String to be evaluated by the method.
   * @return Returns true if the email address is valid and vice versa.
   */
  public static boolean checkEmail(String email)
  {
    return email.matches ("^[a-zA-Z0-9!#$%&'*+\\-/=?^_`{|}~]+([\\.][a-zA-Z0-9!#$%&'*+\\-/=?^_`{|}~]+)*@[a-zA-Z0-9]+([a-zA-Z0-9\\-][a-zA-Z])*[.][a-zA-Z]+([.][a-zA-Z]+)*$");
  }
}