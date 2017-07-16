/**
 * This is the person record class. Everytime a new PersonRecord object
 * is created, this class must be called. This class is responsible for
 * creating a PersonRecord object and also formatting certain attributes.
 * 
 * <p>
 * <b> Instance Variabes: </b>
 * <p>
 * <b> totalRecords </b> A static variable holding the amount of PersonRecord objects
 * <p>
 * <b> first </b> This is a String that stores the user's first name. If they haven't entered one, this is "".
 * <p>
 * <b> last </b> This is a String that stores the user's last name. If they haven't entered one, this is "".
 * <p>
 * <b> phone </b> This is a String that stores the user's phone number. If they haven't entered one, this is "".
 * <p>
 * <b> email </b> This is a String that stores the user's email address. If they haven't entered one, this is "".
 * </p>
 * 
 * @author K Zaveri
 * @version 1 3.20.2015
 */
public class PersonRecord
{
  static int totalRecords;
  private String first;
  private String last;
  private String email;
  private String phone;
  
  /**
   * This is a blank constructor that takes no parameters.
   */
  public PersonRecord()
  {
  }
  /**
   * This is the most commonly use class constructor. It sets up the value for the first, last, phone, and email variables.
   * This method also calls the format methods for the appropriate values.
   * 
   * 
   * <p>
   * <b> Purpose of Conditional Statements </b>
   * <p> 
   * <b> if (!first.equals ("")) </b> This makes sure to call the format methods only if the value isn't "" and vice versa.
   * <p>
   * <b> if (!last.equals ("")) </b> This makes sure to call the format methods only if the value isn't "" and vice versa.
   * <p>
   * <b> if (!this.phone.equals ("")) </b> This makes sure to call the format methods only if the value isn't "" and vice versa.
   * </p>
   * 
   * @param first This is a String that stores the user's first name. If they haven't entered one, this is "".
   * @param last This is a String that stores the user's last name. If they haven't entered one, this is "".
   * @param phone This is a String that stores the user's phone number. If they haven't entered one, this is "".
   * @param email This is a String that stores the user's email address. If they haven't entered one, this is "".
   */
  public PersonRecord (String first, String last, String phone, String email)
  {
    if (!first.trim().equals (""))
      this.first = formatName (first.trim());
    else
      this.first = "";
    if (!last.trim().equals (""))
      this.last = formatName (last.trim());
    else
      this.last = "";
    this.email= email;
    this.phone = phone;
    if (!this.phone.equals (""))
      this.phone = formatPhone();
  }
  /**
   * This constructor takes two String parameters. These parameters will be stored as the first name and the last name
   * in that order.
   * 
   * <p>
   * <b> Purpose of Conditional Statements </b>
   * <p> 
   * <b> if (!first.equals ("")) </b> This makes sure to call the format methods only if the value isn't "" and vice versa.
   * <p>
   * <b> if (!last.equals ("")) </b> This makes sure to call the format methods only if the value isn't "" and vice versa.
   * </p>
   * @param first This is a String that stores the user's first name. If they haven't entered one, this is "".
   * @param last This is a String that stores the user's last name. If they haven't entered one, this is "".
   *
   */
  public PersonRecord (String first, String last)
  {
    if (!first.trim().equals (""))
      this.first = formatName (first.trim());
    else
      this.first = "";
    if (!last.trim().equals (""))
      this.last = formatName (last.trim());
    else 
      this.last = "";
  }
  /** This constructor takes three String parameters. These parameters will be stored as the first name, the last name, and email.
    * in that order.
    * 
    * <p>
    * <b> Purpose of Conditional Statements </b>
    * <p> 
    * <b> if (!first.equals ("")) </b> This makes sure to call the format methods only if the value isn't "" and vice versa.
    * <p>
    * <b> if (!last.equals ("")) </b> This makes sure to call the format methods only if the value isn't "" and vice versa.
    * </p>
    * @param first This is a String that stores the user's first name. If they haven't entered one, this is "".
    * @param last This is a String that stores the user's last name. If they haven't entered one, this is "".
    * @param email This is a String that stores the user's email address. If they haven't entered one, this is "".
    */
  public PersonRecord (String first, String last, String email)
  {
    if (!first.trim().equals (""))
      this.first = formatName (first.trim());
    else
      this.first = "";
    if (!last.trim().equals (""))
      this.last = formatName (last.trim());
    else
      this.last = "";
    this.email= email;
  }
  /** 
   * This constructor takes one String parameter. It will be the first name
   * <p>
   * <b> Purpose of Conditional Statements </b>
   * <p> 
   * <b> if (!first.equals ("")) </b> This makes sure to call the format methods only if the value isn't "" and vice versa.
   * </p>
   * @param first This is a String that stores the user's first name. If they haven't entered one, this is "".
   */
  public PersonRecord (String first)
  {
    if (!first.trim().equals (""))
      this.first = formatName (first.trim());
    else
      this.first = "";
  }
   /**
   * This is the format name method which formats the name so that the first letter
   * at the beginning and after spaces/hyphens are capitalized.
   * 
   * <p>
   * <b> Local Variables </b>
   * <p>
   * <b> formattedName </b> This is the String that will hold the value of the formattedName. The method will return this String.
   * <p>
   * <b> i </b> This is the loop variable which starts at the value of 1, increments by 1, and will increment as long as it is less then the length of the parameter name.
   * </p>
   * 
   * <p>
   * <b> Purpose of loops </b>
   * <p>
   * <b>  First loop </b> This loop goes through the characters of the name (except the first one). Loop starts at 1, increments by 1 and will continue as long as it is less then the length of the parameter name.
   * </p>
   *   
   * <p>  
   * <b> Purpose of conditional statements </b>
   * <p>
   * <b> First if statement </b> The first if statement checks if the character isnt' a space nor a hyphen.
   * <p>
   * <b> Second if statement </b> The purpose of the second if statement is to check if the character isn't the last character, and that the next one is a letter so that it can be capitalized
   * </p>
   * 
   *  
   * @param name This is the name that will be formatted.
   * @return A string which is the formatted name variable that was passed in.

   */
  private String formatName (String name)
  {
    String formattedName = "" + Character.toUpperCase (name.charAt (0));
    for (int i = 1 ; i < name.length () ; i++)
    {
      if (name.charAt (i) != ' ' && name.charAt (i) != '-')
        formattedName += Character.toLowerCase (name.charAt (i));
      else
      {
          formattedName += name.charAt (i);
          if (i != name.length () - 1 && Character.isLetter (name.charAt (i + 1)))
          {
            formattedName += Character.toUpperCase (name.charAt (i + 1));
            i++;
          }
      }
    }
    return formattedName;
  }
  /**
   * The purpose of this method is to format the phone number so that there are dashes between each block of digits.
   * 
   * <p>
   * <b> Purpose of conditional statements </b>
   * <p>
   * <b> !Character.isDigit (this.phone.charAt (3)) ? 1:0 </b> This will check if the 4th digit in the original String is a number and based on that the increment for the substring will change to properly format the new phone number.
   * <p>
   * <b> !Character.isDigit (this.phone.charAt (3)) ? 1:0 </b> This will check if the 4th digit in the original String is a number and based on that the increment for the substring will change to properly format the new phone number.
   * <p>
   * <b> !Character.isDigit (this.phone.charAt (3)) ? 2:0 </b> This will check if the 4th digit in the original String is a number and based on that the increment for the substring will change to properly format the new phone number.
   * <p>
   * <b> !Character.isDigit (this.phone.charAt (3)) ? 2:0 </b> This will check if the 4th digit in the original String is a number and based on that the increment for the substring will change to properly format the new phone number.
   * </p>
   */
  private String formatPhone()
  {
    return this.phone.substring (0, 3) + 
      "-" + 
      this.phone.substring (3 + 
                            (!Character.isDigit (this.phone.charAt (3)) ? 1:0),
                            6 + 
                            (!Character.isDigit (this.phone.charAt (3)) ? 1:0)) + 
      "-" + 
      this.phone.substring (6 + 
                            (!Character.isDigit (this.phone.charAt (3)) ? 2:0),
                            10 + 
                            (!Character.isDigit (this.phone.charAt (3)) ? 2:0));
  }
  
  /**
   * The purpose of this method is to set the value of first to a specified String.
   * 
   * <p>
   * <b> Purpose of Conditional Statements </b>
   * <p> 
   * <b> if (!first.equals ("")) </b> This makes sure to call the format methods only if the value isn't "" and vice versa.
   * </p>
   * 
   * @param first This is a String which will be the new first name for this PersonRecord. 
   */
  public void setFirst(String first)
  {
    if (!first.trim().equals (""))
      this.first = formatName (first.trim());
    else
      this.first = "";
  }
  /**
   * The purpose of this method is to set the value of last to a specified String.
   * 
   * 
   * <p>
   * <b> Purpose of Conditional Statements </b>
   * <p> 
   * <b> if (!last.equals ("")) </b> This makes sure to call the format methods only if the value isn't "" and vice versa.
   * </p>
   * 
   * @param last This is a String which will be the new last name for this PersonRecord. 
   */
  public void setLast(String last)
  {
    if (!last.trim().equals (""))
      this.last = formatName (last.trim());
    else
      this.last = "";
  }
  /**
   * The purpose of this method is to set the value of email to a specified String.
   * 
   * @param email This is a String which will be the new email address for this PersonRecord. 
   */
  public void setEmail(String email)
  {
    this.email = email;
  }
  /**
   * The purpose of this method is to set the value of phone to a specified String.
   * 
   * @param phone This is a String which will be the new phone number for this PersonRecord. 
   */
  public void setPhone(String phone)
  {
    this.phone = phone;
    if (!phone.equals(""))
      this.phone = formatPhone();
  }
  /**
   * The purpose of this method is to get the value of first.
   * 
   * @return The String which is the value of the field first.
   */
  public String getFirst ()
  {
    return first;
  }
  /**
   * The purpose of this method is to get the value of last.
   * 
   * @return The String which is the value of the field last.
   */
  public String getLast ()
  {
    return last;
  }
  /**
   * The purpose of this method is to get the value of email.
   * 
   * @return The String which is the value of the field email.
   */
  public String getEmail ()
  {
    return email;
  }
  /**
   * The purpose of this method is to get the value of phone.
   * 
   * @return The String which is the value of the field phone.
   */
  public String getPhone ()
  {
    return phone;
  }
}