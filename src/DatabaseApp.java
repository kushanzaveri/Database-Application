import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.filechooser.*;
import java.util.*;
/**
 * DatabaseApp is a JFrame to which the AddressBook(JPanel) is added to.
 * DatabaseApp creates an instance of the AddressBook class. It is also 
 * responsible for setting up the menu bar along with the menus and menu
 * items on the screen. DatabaseApp also carries out the appropriate actions
 * if one of the menu items are selected. 
 * The menu items and their appropriate actions are listed below, with a few 
 * conditions:
 * New - Create a new database entry
 * Open - Allows the user to choose a .kaz file to open
 * Save - Allows the user to save the current Database
 * Save As - Allows the user to save the current Database with a different name
 * Quit - Quits the program
 * Help - Opens a dialog regarding the correct use of the program
 * 
 * <p>
 * <b> Instance Variables: </b>
 * <p>
 * <b> a </b> This is an instance of the AddressBook class
 * <p> 
 * <b> blankFile </b> This is aboolean variable which holds if the file is to be blank or not.
 * <p>
 * <b> MAX_RECORD </b> This is a final int that has a value of 50.
 * <p>
 * <b> arr </b> This is a PersonRecord array.
 * <p>
 * <b> fileName </b> This holds the fileName as a String
 * <p>
 * <b> fc </b> This is the reference variable for the JFileChooser object.
 * <p>
 * <b> selectedFile </b> This is a variable that refers to the File object in RAM.
 * <p>
 * <b> counter </b> This counts how many entries have been read by the program.
 * </p>
 *  
 * @author K Zaveri
 * @version 1 3.20.2015
 */
public class DatabaseApp extends JFrame implements ActionListener{  
  private AddressBook a;
  private boolean blankFile = true;
  private ArrayList <PersonRecord> arr = new ArrayList <PersonRecord>();
  private String fileName;
  private JFileChooser fc = new JFileChooser (System.getProperty ("user.dir"));
  private File selectedFile;
  private int counter = 0;
  /**
   * This is the constructor for the DatabaseApp class.
   * It creates a new JFrame with a window title of "Database".
   * It then calls the setupDatabaseFrame method.
   */
  public DatabaseApp()
  {
    super ("Database");
    setupDatabaseFrame();
  }
  
  /**
   * This method sets up the Database JFrame. First of all a new instance of the AddressBook class
   * is created. Next, the panel is setup by calling the setupPanel()
   * method in the AddressBook class. After that the panel is added
   * to the JFrame. After that, the setupMenu() method is called. Finally,
   * the screen is set to a specific size, is visible, closes when 'x'
   * is pressed, and is set to not be resizable.
   * 
   * <p>
   * <b> Purpose of Conditional Statements: </b>
   * <p> 
   * <b> if (blankFile) </b> If a blank file is to be created, it will call the appropriate constructor
   * <p>
   * <b> else </b> If the previous statement is false, call the other constructor.
   * </p>
   * 
   * <p>
   * Inner Classes:
   * <p>
   * This method is called when the user presses the x button. It will call the quit() method.
   * @param e WindowEvent This is the WindowEvent object which contains information relating to the action that just occured.
   * </p>
   */  
  private void setupDatabaseFrame()
  {
    if (blankFile)
      a = new AddressBook();
    else
    {
      a = new AddressBook (arr,counter);
    }
    a.setupPanel();
    add(a);
    setupMenu();
    setResizable (false);
    setVisible(true);
    setSize (650,300);
    setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
    this.addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent e)
      {
        quit();
      }});
  }
  /**
   * This is method sets up all the JMenuItems, JMenus, and the JMenu
   * bar on the screen. ActionListeners are also added for each of the 
   * JMenuItems.
   * 
   * <p>
   * <b> Local Variables </b>
   * <p>
   * <b> menuItems </b> This is a JMenuItem array which holds the 5 menu items for the file menu.
   * <p>
   * <b> menus </b> This is a JMenu array which holds the 2 menus that are a part of the menu bar.
   * <p>
   * <b> helpItem </b> This is an instance of the JMenuItem class called "Help"
   * <p>
   * <b> myMenus </b> This is an instance of the JMenuBar class which will be the default menu bar.
   * <p>
   * <b> i </b> Loop variable that starts at 0, increments by 1, will increment as long as it is less than the length of the menuItems array
   * </p>
   * 
   * <p>
   * <b> Purpose of loops: </b>
   * <p>
   * <b> for (int i=0;i<menuItems.length;i++) </b> Starts at 0, will stop when it is equal to menuItems.length, and increments by 1. Adds actionListeners to all menu items and all menu items to the file menu.
   * </p>
   */
  private void setupMenu()
  {
    JMenuItem[] menuItems ={ 
      new JMenuItem("New"),
      new JMenuItem("Open"),
      new JMenuItem("Save"),
      new JMenuItem("Save As"),
      new JMenuItem("Quit")
    };
    JMenu[] menus = {
      new JMenu("File"),
      new JMenu ("Help")
    };
    JMenuItem helpItem = new JMenuItem ("Help");
    JMenuBar myMenus = new JMenuBar();
    for (int i=0;i<menuItems.length; i++)
    {
      menus[0].add(menuItems[i]);
      menuItems[i].addActionListener (this);
    }
    menus[1].add (helpItem);
    helpItem.addActionListener(this);
   
   
    myMenus.add(menus[0]);
    myMenus.add(menus[1]);
    setJMenuBar(myMenus);
  }
  
  /**
   * This is the actionPerformed method which will determine which method to call.
   * 
   * <p>
   * <b> Purpose of conditional statements as marked in code </b>
   * <p>
   * <b> 1st conditional statement </b> If new is clicked the newFile() method will be called.
   * <p>
   * <b> 2nd conditional statement </b> If open is clicked the openFile() method will be called.
   * <p>
   * <b> 3rd conditional statement </b> If save is clicked and file isn't saved, the save() method will be called.
   * <p>
   * <b> 4th conditional statement </b> If the Save As button is clicked the saveAs() method is called.
   * <p>
   * <b> 5th conditional statement </b> If the Quit button is clicked then the quit() method will be called.
   * <p>
   * <b> 6th conditional statement </b> If the Help button is clicked the helpDialog method will be called.
   * </p>
   */
  public void actionPerformed (ActionEvent ae)
  {
    // 1st
    if (ae.getActionCommand().equals("New"))
      newFile();
    // 2nd
    else if(ae.getActionCommand().equals("Open"))
      openFile();
    // 3rd
    else if(ae.getActionCommand().equals("Save") && !a.getFileSaved())
      save();
    // 4th
    else if (ae.getActionCommand().equals("Save As"))
      saveAs();
    // 5th
    else if (ae.getActionCommand().equals("Quit"))
      quit();
    else
    {
      // 6th
      if (ae.getActionCommand().equals("Help"))
      {
        helpDialog();
      }
    }
  }
  
  /**
   * The purpose of this method is to make the appropriate decisions before the user actually creates a new file.
   * 
   * <p>
   * <b> Local Variables: </b>
   * <p>
   * <b> decision </b> This is an integer that holds the value of the choice from the JOptionPane.
   * </p>
   * 
   * <p>
   * <b> Purpose of Conditional Statements </b>
   * <p>
   * <b> if (!a.getFileSaved()&&PersonRecord.totalRecords>=1) </b> This will check if there is an unsaved file.
   * <p>
   * <b> if (decision==0) </b> This will check if the user chose 'Yes' in the JOptionPane
   * <p>
   * <b> else if (decision==1) </b> This will check if the user chose 'No' in the JOptionPane
   * <p> 
   * <b> else ----->if (decision == 2 || decision == -1)</b> This will check if the user chose 'Cancel' in the JOptionPane or closed the JOptionPane.
   * <p> 
   * <b> else </b> This will be the case if there isn't an unsaved file open.
   * </p>
   */
  private void newFile()
  {
    int decision = 1;
    if (!a.getFileSaved()&&PersonRecord.totalRecords>=1)
    {
      decision = JOptionPane.showConfirmDialog (null,"You have an unsaved file, would you like to save first?", "Unsaved File", JOptionPane.YES_NO_CANCEL_OPTION);
      if (decision==0)
        save();
      else if (decision==1)
        createNewFile();
      else
      {
        if (decision == 2 || decision == -1)
          return;
      }
    }
    else
      createNewFile();
  }
  /**
   * The purpose of this method is to create a new file but setting blankFile to true, removing the original panel,
   * setting fileName to null, and the calling the setupDatabaseFrame() method to do the remaining work.
   */
  private void createNewFile()
  {
    blankFile = true;
    remove(a);
    fileName = null;
    setupDatabaseFrame();
  }
  
  /**
   * The purpose of this method is to make the appropriate decisions before opening a new file.
   * 
   * <p>
   * <b> Local Variables </b>
   * <p>
   * <b> decision </b> This is an integer that holds the value of the choice from the JOptionPane.
   * </p>
   * 
   * <p>
   * <b> Purpose of Conditional Statements </b>
   * <p>
   * <b> if (!a.getFileSaved()&&PersonRecord.totalRecords>=1) </b> This will check if there is an unsaved file.
   * <p>
   * <b> if (decision==0) </b> This will check if the user chose 'Yes' in the JOptionPane
   * <p>
   * <b> else if (decision==1) </b> This will check if the user chose 'No' in the JOptionPane
   * <p> 
   * <b> else ----->if (decision == 2 || decision == -1)</b> This will check if the user chose 'Cancel' in the JOptionPane or closed the JOptionPane.
   * <p> 
   * <b> else </b> This will be the case if there isn't an unsaved file open.
   * </p>
   */
  private void openFile()
  {
    int decision = 1;
    if (!a.getFileSaved()&&PersonRecord.totalRecords>=1)
    {
      decision = JOptionPane.showConfirmDialog (null,"You have an unsaved file, would you like to save first?", "Unsaved File", JOptionPane.YES_NO_CANCEL_OPTION);
      if (decision == 0)
        save();
      else if (decision == 1)
        chooseFileToOpen();
      else
      {
        if (decision == 2 || decision ==-1)
          return;
      }      
    }
    else
      chooseFileToOpen();
  }
  /**
   * The purpose of this method is to allow the user to choose what file to open and opens it. It also makes
   * the appropriate actions based on what file is chosen and whether or not it is valid to open.
   * 
   * <p>
   * <b> Local Variables </b>
   * <p>  
   * <b> first </b> This will store the String in the first name location read from the file.
   * <p>  
   * <b> last </b> This will store the String in the last name location read from the file.
   * <p>   
   * <b> phone </b> This will store the String in the phone number location read from the file.
   * <p>  
   * <b> email </b> This will store the String in the email address location read from the file.
   * <p>  
   * <b> br </b> This is an instance of the BufferedReader class in order to read the file.
   * </p>
   * 
   * <p>
   * <b> Purpose of conditional statements </b>
   * <p>
   * <b> if (result == JFileChooser.APPROVE_OPTION) </b> This if statement checks if the user clicked the approve option on the JFileChooser window. Otherwise leave
   * <p>
   * <b> if (br.readLine().equals("VALID FILE")) </b> Checks if the header is valid.
   * <p>
   * <b> if ((first == null) && last ==null && phone ==null && email==null) </b> Checks if there are no more entries then breaks.
   * </p>
   * 
   * <p>
   * <b> Purpose of loops </b>
   * <p>
   * <b> for (int x = 0;x<MAX_RECORD ;x++) </b> x starts at the value of 0, increments by 1, and stops when it is equal to 50 (MAX_RECORD)
   * </p>
   * 
   * 
   * <p>
   * <b> Purpose of Try-Catch blocks </b>
   * <p>
   * <b> Block 1 </b> This try-catch block is to try opening a file. If any error occurs, the exception will be caught and an error will be displayed.
   * </p>
   */
  private void chooseFileToOpen()  {    
    String first = "",last = "",phone = "",email = "";
    fc.setFileFilter(new FileNameExtensionFilter("AddressBook files", "kaz"));
    if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){        
      try{
        BufferedReader br = new BufferedReader (new FileReader (fc.getSelectedFile().getAbsolutePath() + (fc.getSelectedFile().getAbsolutePath().toString().endsWith(".kaz")?"": ".kaz")));
        if (br.readLine().equals("VALID FILE")){
          fileName = fc.getSelectedFile().getAbsolutePath() + (fc.getSelectedFile().toString().endsWith(".kaz")?"": ".kaz");
          br.readLine();
          arr.clear();
          for (int x = 0;;x++){
            first = br.readLine();
            last = br.readLine();
            phone = br.readLine();
            email = br.readLine();
            if (first == null && last ==null && phone ==null && email==null){
              counter = x;
              break;
            }
            arr.add(new PersonRecord(first,last,phone,email));
          }
          loadNewAddressBook();
          return;
        }
        else{
          if (JOptionPane.showConfirmDialog (null,"File was corrupted. Try another file name?", "Corrupted File", JOptionPane.YES_NO_OPTION) == 0)
            chooseFileToOpen();
          else
            return;
        }
      }
      catch (IOException e){
        if (JOptionPane.showConfirmDialog (null,"File was corrupted. Try another file name?", "Corrupted File", JOptionPane.YES_NO_OPTION) == 0)
          chooseFileToOpen();
        else
          return;
      }         
    }
    else
      return;
  }
  /**
   * This method is meant to load a new address book that is from a file.
   * It removes the old JPanel, and sets blankFile to false. setupDatabaseFrame() is called to set up the rest.
   */
  private void loadNewAddressBook()
  {
    blankFile = false;
    remove(a);
    setupDatabaseFrame();
  }
  
  /**
   * This method is responsible for saving the file.
   * 
   * <p>
   * <b> Local variables </b>
   * <p>
   * <b> output </b> This is a new PrintWriter which is used to save the file.
   * <p>
   * <b> i </b> A local loop variable which is explained below.
   * </p>
   * 
   * <p>
   * <b> Purpose of conditional statements </b>
   * <p> 
   * <b> if (fileName == null) </b> Checks if there isn't a file name and will ask for a name if there isn't.
   * <p>
   * <b> else </b> Otherwise, if there is a fileName, it will save.
   * </p>
   * 
   * <p> 
   * <b> Purpose of loops </b>
   * <p>
   * <b> for (int i=0;i<PersonRecord.totalRecords;i++) </b> This loop will start at 0, increment by 1, and will stop at the total number of records.
   * </p>
   * 
   * <p> 
   * <b> Purpose of Segment Blocks </b>
   * <p>
   * <b> try block </b> This try block is here to catch the exception in the case that the file cannot be written to.
   * </p>
   */
  private void save()
  {
    if (fileName == null)
      saveAs();
    else
    {
      try
      {
        PrintWriter output = new PrintWriter (new FileWriter (fileName));
        output.println ("VALID FILE");
        output.println ();
        arr = a.getPersonRecords();
        for (int i=0;i<PersonRecord.totalRecords;i++)
        {
          output.println (arr.get(i).getFirst());
          output.println (arr.get(i).getLast());
          output.println (arr.get(i).getPhone());
          output.println (arr.get(i).getEmail());
        }
        a.setFileSaved (true);
        output.close();
      }
      catch (IOException e)
      {
      }
    }
  }
  /**
   * The purpose of this method is to get a valid fileName to save the PersonRecord array as.
   * <p>
   * <b> Purpose of Local Variables </b>
   * <p> 
   * <b> temp </b> This is the temporary file name.
   * <p>
   * <b> pw </b> This is a PrintWriter used for testing if the file already exists
   * </p>
   * 
   * <p>
   * <b> Purpose of Loops </b>
   * <p>
   * <b> while (true) </b> This loop continues until the user no longer wants to try names or until a valid name is entered.
   * </p>
   * 
   * <p>
   * <b> Purpose of Conditional Statements </b>
   * <p>
   * <b> if (PersonRecord.totalRecords==0) </b> Makes sure the user can't save if they don't have enough entries
   * <p>
   * <b> if (result == JFileChooser.APPROVE_OPTION) </b> Checks if the user clicked Save in the JFileChooser
   * <p>
   * <b> if (new File(selectedFile.getAbsolutePath()).exists())</b> Checks if the file already exists
   * <p>
   * <b> temp += !temp.endsWith (".kaz") ? ".kaz":"" </b> Adds the extension to the file name
   * <p>
   * <b> if (result==0) </b> Checks if the 'Yes' option was clicked in the JOptionPane. Otherwise 'No' was clicked
   * </p>
   */
  private void saveAs(){
    String temp = "";
    if (PersonRecord.totalRecords==0)
      return;
    while (true)
    {
      fc.setFileFilter(new FileNameExtensionFilter("AddressBook files", "kaz"));
      if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
      {
        selectedFile = fc.getSelectedFile();
        temp = fc.getSelectedFile().getAbsolutePath()+ (!selectedFile.getAbsolutePath().endsWith (".kaz") ? ".kaz":"");     
        if (new File(temp).exists())
        {
          if (JOptionPane.showConfirmDialog (null,"This file already exists, would you like to overwrite?", "File Exists", JOptionPane.YES_NO_OPTION) == 0)
          {
            fileName = temp;
            save();
            break;
          }
          else
            return;
        }
        else
        {
          try{
            PrintWriter pw = new PrintWriter (new FileWriter (temp));
            pw.close();
          }
          catch (IOException e){
            if (JOptionPane.showConfirmDialog (null,"Invalid File Name! Would you like to enter another name?", "Invalid File Name", JOptionPane.YES_NO_OPTION) == 0)
              continue;
            else
              return;
          }
        }
        fileName = temp;
        save();
        break;
      }
      else
        return ;
    }
  } 

  /**
   * The purpose of this method is allow the user to quit while also checking for unsaved files before they do quit.
   * 
   * <p>
   * <b> Purpose of local variables: </b>
   * <p>
   * <b> decision </b> This will hold the value of the choice from the JOptionPane 
   * </p>
   * 
   * <p>
   * <b> Purpose of Conditional Statements
   * <p>
   * <b> if (!a.getFileSaved()) </b> Checks if the file is unsaved
   * <p> 
   * <b> if (decision == 0) </b> Checks if the user pressed 'Yes' in the JOptionPane
   * <p>
   * <b> else ---> if (decision == 2 || decision ==-1) </b> Checks if the user quit or pressed cancel.
   * <p>
   * <b> Last if statement </b> Ensures that the user actually want to quit.
   * </p>
   */
  private void quit()
  {
    int decision = 1;
    if (!a.getFileSaved())
      decision = JOptionPane.showConfirmDialog (null,"You have an unsaved file, would you like to save first?", "Unsaved File", JOptionPane.YES_NO_CANCEL_OPTION);
    if (decision == 0)
      save();
    else
    {
      if (decision == 2 || decision ==-1)
        return;
    }
    if (fileName != null|| decision ==1)
      System.exit(0);
  }
  /**
   * The prupose of this method is to create a help dialog that outlines rules for 
   * entering data to the user. This dialog consists of JLabels and one button.
   * The components are then added to the dialog and the dialog is set to be visible.
   * An action listener is also added to the JButton.
   * 
   * <p>
   * <b> Local Variables: </b>
   * <p>
   * <b> helpDialog </b> This creates an instance of the JDialog class. It is called "Help".
   * <p>
   * <b> text </b> This creates an array of JLabel objects which are part of the dialog.
   * <p>
   * <b> button </b> This creates an instance of the JButton class. It is called "Close".
   * </p>
   * 
   * <p>
   * <b> Purpose of loops: </b>
   * <p>
   * <b> First loop (loop variable i) </b> This is to add JLabels to the panel. It starts at 0, incremments by 1, continues as long as i is less than the length of the text array.
   * </p>
   * 
   * 
   * Inner Classes:
   * 
   * This method is called when the user presses the button. The dialog will then close.
   * @param e ActionEvent This is the ActionEvent object which contains information relating to the action that just occured.
   */
  private void helpDialog()
  {
    final JDialog helpDialog = new JDialog (this, "Help");
    JLabel [] text = {
      new JLabel ("Correct User Input"),
      new JLabel ("The phone number must be ten digits in length."),
      new JLabel (" These ten digits may be seperated by dashes, "),
      new JLabel ("spaces, or nothing at all. These seperations "),
      new JLabel ("may  only be between the 3rd and 4th character"),
      new JLabel ("and between the 6th and 7th characters."),
      new JLabel ("The email address may be as follows:"),
      new JLabel ("Text before the @ symbol must only contain: "),
      new JLabel ("Any characters in the alphabet, numbers, "),
      new JLabel ("specific characters: !#$%&'*+-/=?^_`{|}~},"),
      new JLabel ("and dots if they aren't at the end of this section."),
      new JLabel ("After the @ sign, you can have numbers and characters"),
      new JLabel ("in the alphabet. Hyphens are allowed, as long as they"),
      new JLabel ("aren't at the end of the section. Next would come a dot"),
      new JLabel ("which can be followed by a domain. This part can only "),
      new JLabel ("have characters from the alphabet and can be a compound "),
      new JLabel ("ending (.___.___)\n")};
    JButton button = new JButton ("Close");
    helpDialog.setSize (350, 450);
    helpDialog.setResizable (false);
    helpDialog.setLayout (new FlowLayout ());
    button.addActionListener (new ActionListener ()
                                {
      public void actionPerformed (ActionEvent e)
      {
        helpDialog.dispose ();
      }
    }
    );
    for (int i=0;i<text.length;i++)
      helpDialog.add(text[i]);
    helpDialog.add (button);
    helpDialog.setLocationRelativeTo (this);
    helpDialog.setVisible (true);
  }
   /** This method calls the DatabaseApp constructor to
    * create the application.
    *
    * @param args [ ]  String array that allows command line
    * parameters to be used when executing the program.
    */
  public static void main(String [] args)
  {
    new DatabaseApp();
  }
}