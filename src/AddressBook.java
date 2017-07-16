import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 * This is the AddressBook class which is-a JPanel.
 * This is where the user can enter data, save the fields,
 * create new entries, and navigate through the entries.
 * <p>
 * <b> Instance Variables:</b>
 * <p>
 * <b> currentRecordNumber </b> Stores what the current record number is.
 * <p>
 * <b> MAX_RECORD </b> This is a private final integer holding the amount of maximum records (50)
 * <p>
 * <b> personRecords </b> This is an array of PersonRecord objects.
 * <p>
 * <b> entrySaved </b> This is a boolean value that determines whether or not the entry is saved.
 * <p>
 * <b> fileSaved </b> This is a boolean value that determines whether or not the file is saved.
 * <p>
 * <b> fromFile </b> This is a boolean value that keeps track of if the array is from a file or isn't
 * <p>
 * <b> insets </b> This is an object of the Insets method, used for aligning the components on the screen.
 * <p>
 * <b> records </b> This is an instance of the JLabel class which will be the record counter.
 * <p>
 * <b> labels </b> This is an array of JLabel objects for the panel.
 * <p>
 * <b> textFields </b> This is an array of JTextField objects for the panel.
 * <p>
 * <b> buttons </b> This is an array of JButton objects for the panel.
 * </p>
 * 
 * @author K Zaveri
 * @version 1 3.20.2015
 */
public class AddressBook extends JPanel implements ActionListener
{
  static int currentRecordNumber;
  ArrayList <PersonRecord> personRecords;  
  private boolean entrySaved = true;
  private boolean fileSaved = true;
  private boolean fromFile;
  private boolean entryDeleted;
  private Insets insets = getInsets();   
  
  private JLabel records;
  private JLabel[] labels = {
    new JLabel ("First Name: "),
    new JLabel ("Last Name: "), 
    new JLabel ("Phone Number: "),
    new JLabel ("Email Address: ")};  
  private JTextField[] textFields = {
    new JTextField (30),
    new JTextField (30),
    new JTextField (30),
    new JTextField (30)};
  private JButton[] buttons = {
    new JButton("<"),
    new JButton (">"),
    new JButton ("Save Current Entry"),
    new JButton ("Create New Entry"),
    new JButton ("Delete Entry")};
  /**
   * This is the first constructor of the AddressBook class.
   * It takes no parameters. It creates a new PersonRecord
   * array, sets the currentRecordNumber to 0, sets the 
   * totalRecords amount to 0, and sets the fromFile variable
   * to false.
   */
  public AddressBook()
  {
    personRecords =new ArrayList <PersonRecord>();
    currentRecordNumber = 0;
    PersonRecord.totalRecords = 0;
    fromFile = false;
  }
  /**
   * This is the second constructor of the AddressBook class.
   * It takes 2 parameters, a PersonRecord array and an integer.
   * The array is the new PersonRecord created from the file.
   * The integer holds the amount of total records. The constructor
   * sets the current personRecords array to match arr and the static
   * variable to match the amount of totalRecords. currentRecordNumber
   * is set to 1 and fromFile is set to be true.
   * 
   * @param arr Is the array of PersonRecord objects which is to hold the values which will be transferred to the PersonRecord array in this class.
   * @param totalRecords Stores the totalAmount of records in this specific array.
   */
  public AddressBook (ArrayList <PersonRecord> arr, int totalRecords)
  {
    personRecords = arr;
    PersonRecord.totalRecords = totalRecords;
    currentRecordNumber = 1;
    fromFile = true;
  }
  /**
   * This method returns the value of fileSaved.
   * @return The current value of fileSaved is returned.
   */
  public boolean getFileSaved()
  {
    return fileSaved;
  }
  /**
   * This method is to set the value of fileSaved.
   * @param fileSaved This will be the new vaue of fileSaved.
   */
  public void setFileSaved (boolean fileSaved)
  {
    this.fileSaved = fileSaved;
  }
  /**
   * This method sets up the JPanel with the JTextFields,
   * JLabels, and JButtons. They are all put in the appropriate
   * location on the screen. The actionListeners for the buttons 
   * are also added.
   * 
   * <p>
   * <b> Local Variables: </b>
   * <p>
   * <b> size </b> This is an array of Dimension objects which holds the preferredSize of the objects on the panel.
   * <p>
   * <b> offsetsX </b> This is an int array which holds the x offsets of the corresponding component (labels, text fields, then buttons)
   * <p>
   * <b> offsetsY </b> This is an int array which holds the y offsets of the corresponding component (labels, text fields, then buttons)
   * <p>
   * <b> i </b> A loop variable that starts at the value of 0 and will increment (not as a part of the loop declaration) as long as it is less then the length of size.
   * <p>
   * <b> x </b> A loop variable that will start at the value of 0, increments by 1, and will increment as long as x is less than the length of the labels array.
   * <p>
   * <b> x </b> A loop variable that will start at the value of 0, increments by 1, and will increment as long as x is less than the length of the textFields array.
   * <p> 
   * <b> x </b> A loop variable that will start at the value of 0, increments by 1, and will increment as long as x is less than the length of the buttons array.
   * <p>
   * <b> i </b> A loop variable that will start at the value of 0, increments by 1, and will increment as long as x is less than the length of the textFields array.
   * </p>
   * 
   * <p>
   * <b> Purpose of Conditional Statements </b>
   * <p>
   * <b> if (i less than 4), else if (i less than 8), else </b> Will add the appropriate array of components on the screen based on the value of i.
   * <p>
   * <b> if (!fromFile) </b> If it is a file that is being opened the program will make sure that the fields are enabled.
   * </p>
   * 
   * <p>
   * <b> Purpose of loops </b>
   * <p>
   * <b>  For loop with variable i </b> This for loop goes through all the indexes of the size array. It starts at 0, there is no increment in the for loop declaration, and will stop when its equal to the length of the size array.
   * <p>
   * <b>  First for loop with variable x </b> Sets the dimensions for the labels on the screen and adds then to the panel as well. Starts at 0, increments by 1, ends when it is equal to the length of the labels array.
   * <p>
   * <b>  Second for loop with variable x </b> Sets the dimensions for the text fields on the screen and adds then to the panel as well. Starts at 0, increments by 1, ends when it is equal to the length of the textFields array.
   * <p>
   * <b>  Third for loop with variable x </b> Sets the dimensions for the buttons on the screen and adds then to the panel as well. Their ActionListeners are also added. Starts at 0, increments by 1, ends when it is equal to the length of the buttons array.
   * <p>
   * <b>  Second for loop with variable i </b> Makes all the textFields disabled. Starts at 0, increments by 1, stops when it is equal to the length of the text fields array.
   * </p>
   */
  public void setupPanel()
  {
    Dimension[] size ={
      labels[0].getPreferredSize(),
      labels[1].getPreferredSize(),
      labels[2].getPreferredSize(),
      labels[3].getPreferredSize(),
      textFields[0].getPreferredSize(),
      textFields[1].getPreferredSize(),
      textFields[2].getPreferredSize(),
      textFields[3].getPreferredSize(),
      buttons[0].getPreferredSize(),
      buttons[1].getPreferredSize(),
      buttons[2].getPreferredSize(),
      buttons [3].getPreferredSize(),
      buttons[4].getPreferredSize()};
    int [] offsetsX ={170, 170, 170, 170, 260, 260, 260, 260, 20, 580, 240, 410, 100};
    int [] offsetsY ={10, 50, 90, 130, 10, 50, 90, 130, 200, 200, 200, 200,200};
    setLayout (null);
    for (int i=0;i<size.length;)
    {
      if(i<4)
      {
        for (int x=0;x<labels.length;x++)
        {
          labels[x].setBounds(offsetsX[i]+insets.left,offsetsY[i]+insets.top, size[i].width, size[i].height);
          add (labels[x]);
          i++;
        }
      }
      else if(i<8)
      {
        for (int x=0;x<textFields.length;x++)
        {
          textFields[x].setBounds(offsetsX[i]+insets.left,offsetsY[i]+insets.top, size[i].width, size[i].height);
          add (textFields[x]);
          i++;
        }
      }      
      else
      {
        for (int x=0;x<buttons.length;x++)
        {
          buttons[x].setBounds(offsetsX[i]+insets.left,offsetsY[i]+insets.top, size[i].width, size[i].height);
          add (buttons[x]);
          buttons[x].addActionListener(this);
          i++;
        }
      }
    }
    
    rewriteRecords();
    if (!fromFile)
    {
      for (int i=0;i<textFields.length;i++)
        textFields[i].setEnabled (false);
    }
    else
    {
      setFieldsOnScreen(true);
    }
    refresh();
  }
  /**
   * This is the actionPerformed method. It is
   * responsible for getting the actionCommand,
   * evaluating it, then calling the appropriate
   * method based on the evaluation.
   * 
   * <p>
   * <b> Purpose of Conditional Statements in order </b>
   * <p>
   * <b> 1st conditional statement </b> Calls the moveBack() method if this is true.
   * <p>
   * <b> 2nd conditional statement </b> Calls the moveForward() method if this is true.
   * <p>
   * <b> 3rd conditional statement </b> Calls the saveEntry() method if this is true.
   * <p>
   * <b> 4th conditional statement </b> Calls the addEntry() method if this is true.
   * <p>
   * <b> 5th conditional statement </b> Will reset focus in the window.
   * </p>
   * 
   * @param ae This will store the data relating to the ActionEvent that just occured.
   */
  public void actionPerformed(ActionEvent ae)
  {
    if (ae.getActionCommand().equals ("<")&& PersonRecord.totalRecords!=0)
    {
      moveBack();
    }
    else if (ae.getActionCommand().equals (">") && PersonRecord.totalRecords!=0)
    {
      moveForward();
    }
    else if (ae.getActionCommand().equals ("Save Current Entry")&& currentRecordNumber!=0)
    {
      saveEntry();
    }
    // EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
    else if (ae.getActionCommand().equals ("Delete Entry")&& currentRecordNumber>0)
    {
      deleteEntry();
    }
    else
    {
      if (ae.getActionCommand().equals ("Create New Entry"))
      {
        if (entrySaved)
          addEntry();
        else
        {
          JOptionPane.showMessageDialog(null, "New entry cannot be created until the current entry is saved.");
          textFields[0].requestFocusInWindow();
        }
      }
    }
  }
  /**
   * The purpose of this method is to set the text in the
   * JTextFields on the screen. It then removes and rewrites
   * the record counter.
   * 
   * <p>
   * <b> Local Variables </b>
   * <p>
   * <b> i </b> A local loop variable.
   * </p>
   * 
   * <p>
   * <b> Purpose of conditional statements </b>
   * <p>
   * <b> First loop in method </b> This sets the textFields to be blank. Starts at 0, increments by 1, will continue as long as the loop variable is less than the length of the textFields array.
   * </p>
   * 
   * <p>
   * <b> Purpose of conditional statements </b>
   * <p>
   * <b> if (displayInfo) </b> This will output the information on the screen if it is true.
   * <p>
   * <b> else </b> This will make the textFields blank.
   * </p>
   *
   * @param displayInfo A true or false value determining whether or not to display information in the text fields.
   */
  private void setFieldsOnScreen(boolean displayInfo)
  {  

    if (displayInfo)
    {
      textFields[0].setText (personRecords.get(currentRecordNumber-1).getFirst());
      textFields[1].setText (personRecords.get(currentRecordNumber-1).getLast());
      textFields[2].setText (personRecords.get(currentRecordNumber-1).getPhone());
      textFields[3].setText (personRecords.get(currentRecordNumber-1).getEmail());
    }
    else
    {
      for (int i = 0;i<textFields.length;i++)
      {
        textFields[i].setText ("");
        if (entryDeleted)
          textFields [i].setEnabled (false);
      }
     }
    remove (records);
    rewriteRecords();
    textFields[0].requestFocusInWindow();
  }
  /**
   * The purpose of this method is to rewrite the record counter.
   * To do this it creates a new JLabel, gets the dimensions, laysout
   * the component, adds it to the screen, and then refreshes.
   */
  private void rewriteRecords()
  {
    records = new JLabel ("Record " + currentRecordNumber + " of " + PersonRecord.totalRecords);
    Dimension recordsSize = records.getPreferredSize();
    records.setBounds (270+insets.left,170+insets.top,recordsSize.width, recordsSize.height);
    add(records);
    refresh();
  }
  /**
   * This is the addEntry method which adds the entry to the screen.
   * To do this it sets the JTextFields to be enabled, increments the
   * record number so that it is one above the amount of total records,
   * sets the fields on the screen to be blank, requests focus in the window,
   * then sets the entry to be saved.
   * 
   * <p>
   * <b> Local Variables: </b>
   * <p>
   * <b> i </b>  A loop variable that will start at the value of 0, increments by 1, and will increment as long as x is less than the length of the textFields array.
   * </p>
   * <p>
   * <b> Purpose of loops: </b>
   * <p>
   * <b> First loop in method </b> Sets the textFields to be enabled. It start at the value of 0, increments by 1, and will increment as long as x is less than the length of the textFields array.
   * </p>
   * <p>
   * <b> Purpose of conditional statements </b>
   * <p>
   * <b> First conditional statement </b> Checks if there is an entry to which changes have been made.
   * <p>
   * <b> Nested conditional statement </b> Checks if the user wants a chance to save their unsaved entry.
   * </p>
   */
  private void addEntry()
  {
    if (!giveWarning())
    {
      if (JOptionPane.showConfirmDialog (null,"This entry has unsaved changes? Would you like to go back to save first?", "Unsaved Entry", JOptionPane.YES_NO_OPTION) == 0)
      {
        return; 
      }
    }
    for (int i=0;i<textFields.length;i++)
      textFields[i].setEnabled (true);
    currentRecordNumber=PersonRecord.totalRecords+1;
    setFieldsOnScreen(false);
    textFields[0].requestFocusInWindow();
    entrySaved = false;
  }
  /**
   * This methods determines whether or not the user can save their entries
   * based on if there is atleast one validly entered field.
   * 
   * <p>
   * <b> Purpose of Conditional Statements </b>
   * <p>
   * <b>if (textFields[0].getText().trim().equals("") && textFields[1].getText().trim().equals("") && textFields[2].getText().trim().equals("") && textFields[3].getText().trim().equals("")) </b> Checks if there aren't any entries
   * <p>
   * <b> else --> if((!textFields[2].getText().trim().equals("")&& !DataCheck.checkPhone(textFields[2].getText())) && (!textFields[3].getText().trim().equals("")&& !DataCheck.checkEmail(textFields[3].getText())))</b> Checks if both the phone and email Strings are formatted incorrectly
   * <p>
   * <b> else --> else if (!textFields[2].getText().trim().equals("")&&!DataCheck.checkPhone(textFields[2].getText())) </b> Checks if only the phone number is incorrectly formatted
   * <p>
   * <b> else --> else if (!textFields[3].getText().trim().equals("")&&!DataCheck.checkEmail(textFields[3].getText())) </b> Checks if only the email address is incorrectly formatted.
   * <p>
   * <b> else </b> This is the action if everything is correctly formatted and if there is atleast 1 field.
   * <p>
   * <b> if (currentRecordNumber <= PersonRecord.totalRecords) </b> Checks if a valid entry has been made so the saved value can be used to replace the bad data. Otherwise, the program will clear the field entirely.
   * </b>
   */
  private void saveEntry()
  { 
    if (textFields[0].getText().trim().equals("") &&
        textFields[1].getText().trim().equals("") &&
        textFields[2].getText().trim().equals("") &&
        textFields[3].getText().trim().equals(""))
    {
      JOptionPane.showMessageDialog(null, "You must enter at least one field of data before saving!");
      textFields[0].requestFocusInWindow();
    }     
    else
    {
      if((!textFields[2].getText().trim().equals("")&&
          !DataCheck.checkPhone(textFields[2].getText()))
           &&
         (!textFields[3].getText().trim().equals("")&&
          !DataCheck.checkEmail(textFields[3].getText())))
      {
        JOptionPane.showMessageDialog(null, "Phone number and Email Address formatted incorrectly. Please fix error before saving.");
        if (currentRecordNumber <= PersonRecord.totalRecords){
          textFields[2].setText(personRecords.get(currentRecordNumber-1).getPhone());
          textFields [3].setText(personRecords.get(currentRecordNumber-1).getEmail());
        }
        else{
          textFields[2].setText ("");
          textFields[3].setText ("");
        }         
        textFields[2].requestFocusInWindow();
        return;
      }
      else if (!textFields[2].getText().trim().equals("")&&!DataCheck.checkPhone(textFields[2].getText()))
      {
        JOptionPane.showMessageDialog(null, "Phone number is formatted incorrectly. Please fix error before saving.");
        if (currentRecordNumber <= PersonRecord.totalRecords)
          textFields[2].setText(personRecords.get(currentRecordNumber-1).getPhone());
        else
          textFields[2].setText ("");
        textFields[2].requestFocusInWindow();
        return;        
      }
      else if (!textFields[3].getText().trim().equals("")&&!DataCheck.checkEmail(textFields[3].getText()))
      {
        JOptionPane.showMessageDialog(null, "Email address formatted incorrectly. Please fix error before saving.");
        if (currentRecordNumber <= PersonRecord.totalRecords)
          textFields[3].setText(personRecords.get(currentRecordNumber-1).getEmail());
        else
          textFields[3].setText ("");
        textFields[3].requestFocusInWindow();
        return;
      }
      else
      {
        saveRecords();
        setFieldsOnScreen(true);
      }      
    }
  }
  /**
   * The purpose of this method is to create/update the records array
   * by adding/changing values in the array to the current values in 
   * the fields. Then entrySaved is set to true and fileSaved to false.
   * 
   * <p>
   * <b> Purpose of conditional statements: </b>
   * <p>
   * <b> if (currenRecordNumber>PersonRecord.totalRecords) </b> This will create a new PersonRecord object if this is true.
   * <p>
   * <b> else </b> This will reset values of the already existing PersonRecord object
   * </p>
   */
  private void saveRecords()
  {
    if(currentRecordNumber>PersonRecord.totalRecords)
    {
      personRecords.add (new PersonRecord (textFields[0].getText (),textFields[1].getText (),textFields[2].getText(),textFields[3].getText ()));
      PersonRecord.totalRecords++;
    }
    else
    {
      personRecords.get(currentRecordNumber-1).setFirst(textFields[0].getText());
      personRecords.get(currentRecordNumber-1).setLast(textFields[1].getText());
      personRecords.get(currentRecordNumber-1).setPhone(textFields[2].getText());
      personRecords.get(currentRecordNumber-1).setEmail(textFields[3].getText());
    }
    entrySaved = true;
    fileSaved = false;
  }
  /**
   * The purpose of this method is to move the records forward or to loop
   * to the beginning if they have reached to the last record. entrySaved
   * is set to true because the user would be going to revisit their previously
   * saved entries. Then the fields are reset on the screen.
   * 
   * <p>
   * <b> Purpose of conditional statements </b>
   * <p>
   * <b> First conditional statement </b> Checks if there is an entry to which changes have been made.
   * <p>
   * <b> Nested conditional statement </b> Checks if the user wants a chance to save their unsaved entry.
   * <p>
   * <b> if (currentRecordNumber<PersonRecord.totalRecords) </b> This will check if the current record is less than the total and then increment if this is true.
   * <p>
   * <b> else </b> If the above was false, the counter would loop back and be set to 1.
   * </p>
   */
  private void moveForward()
  {
    if (!giveWarning())
    {
      if (JOptionPane.showConfirmDialog (null,"This entry has unsaved changes.  Would you like to go back to save first?", "Unsaved Entry", JOptionPane.YES_NO_OPTION) == 0)
      {  
        textFields[0].requestFocusInWindow();
        return;   
      }
    }
    if (currentRecordNumber<PersonRecord.totalRecords)
      currentRecordNumber++;
    else 
      currentRecordNumber = 1;
    entrySaved = true;
    setFieldsOnScreen(true);    
  }
  
  /**
   * The purpose of this method is to move the records backwards or to loop
   * to the end if they have reached to the first record. entrySaved
   * is set to true because the user would be going to revisit their previously
   * saved entries. Then the fields are reset on the screen.
   * 
   * <p>
   * <b> Purpose of conditional statements </b>
   * <p>
   * <b> First conditional statement </b> Checks if there is an entry to which changes have been made.
   * <p>
   * <b> Nested conditional statement </b> Checks if the user wants a chance to save their unsaved entry.
   * <p>
   * <b> if (currentRecordNumber==1) </b> This will check if the current record is 1 then set the number of records to 1.
   * <p>
   * <b> else </b> If the previous was false it will regularly decrement.
   * </p>
   */
  private void moveBack()
  {
    if (!giveWarning())
    {
      if (JOptionPane.showConfirmDialog (null,"This entry has unsaved changes. Would you like to go back to save first?", "Unsaved Entry", JOptionPane.YES_NO_OPTION) == 0)
      {
        textFields[0].requestFocusInWindow();
        return;  
      }
    }
    if (currentRecordNumber==1)
      currentRecordNumber=PersonRecord.totalRecords;
    else
      currentRecordNumber--;
    entrySaved = true;
    setFieldsOnScreen(true);
  } 
  
  private void deleteEntry()
  {
    if (currentRecordNumber>PersonRecord.totalRecords)
    {
      JOptionPane.showMessageDialog (null, "You cannot delete an unsaved entry!");
      textFields[0].requestFocusInWindow();
      return;
    }
    personRecords.remove(currentRecordNumber-1);

    if (currentRecordNumber <= PersonRecord.totalRecords)
    {
      if (currentRecordNumber == PersonRecord.totalRecords)
        currentRecordNumber-=1;
      PersonRecord.totalRecords -=1;
    }
    if (currentRecordNumber ==0 && PersonRecord.totalRecords ==0)
      fileSaved = true;
    else 
      fileSaved = false;
    entryDeleted = true;
    setFieldsOnScreen(currentRecordNumber >= 1);
    entryDeleted = false;
   
  }
  /**
   * This method checks if there are any unsaved changes to the text fields.
   * 
   * <p>
   * <b> Purpose of Conditional Statements </b>
   * <p>
   * <b> First conditional statement </b> Checks if the current record is less than or equal to the total records indicating that the file has been saved at least once.
   * <p>
   * <b> Second conditional statement </b> Checks if the current entry number is not 0.
   * <p>
   * <b> Third conditional statement </b> Checks if the entry really is unsaved and if it has gotten this far, false will be returned.
   * </p>
   */
  private boolean giveWarning()
  {
    if (currentRecordNumber <=PersonRecord.totalRecords)
      if(currentRecordNumber!=0)
      if (!textFields[0].getText().trim().equals( personRecords.get(currentRecordNumber-1).getFirst())||
          !textFields[1].getText().trim().equals( personRecords.get(currentRecordNumber-1).getLast())||
          !textFields[2].getText().trim().equals( personRecords.get(currentRecordNumber-1).getPhone())||
          !textFields[3].getText().trim().equals( personRecords.get(currentRecordNumber-1).getEmail()))
        return false;  

    return true;
  }
  /**
   * This method returns the current personRecords array.
   * @return The current array of PersonRecord objects will be returned.
   */
  public ArrayList <PersonRecord> getPersonRecords()
  {
    return personRecords;
  }
  /**
   * This method refreshes the panel.
   */
  private void refresh()
  {
    revalidate();
    repaint();
  }
}