//*****************************************
//   Name: Michael Brown
//   CTP 150 – section 005
//   Lab Assignment 11
//***************************************** 


/*************************************************************/
//all import statements can only be written here:   
import java.util.Scanner; 
import java.util.Arrays;
import java.text.DecimalFormat;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;



/***************************************************************
Super class: BankAccount
***************************************************************/ 
abstract class BankAccount implements Serializable //abstract class type super class 
{

 //--------fields 
   protected double balance;
   protected int numDeposits;
   protected int numWithdrawals;
   protected double interestRate; // annual intrest rate 
   protected double msg; // monthly service charges 

 //--------Constructors
 /**
     Constructer 1
     @param nBalance
     @param nInterestRate
     @exception InvalidDepositAmount
 */
   public BankAccount(double nBalance, double nInterestRate)
   {
      setBalance(nBalance);
      interestRate = nInterestRate;    
   }
 
 /**
     Constructer 2
     @param nBalance
     @param nInterestRate
     @param nMSG 
     @exception InvalidDepositAmount
 */
   public BankAccount(double nBalance, double nInterestRate, double nMSG)
   {
      setBalance(nBalance);
      interestRate = nInterestRate;
      msg = nMSG;
   }
  
 //-----------Mutators
 /**
     Mutator for balance
     @param nBalance
     @exception InvalidDepositAmount
 */
   public void setBalance(double nBalance) 
   {
      if(nBalance> 0.0 && 10000.0 >= nBalance ){
         balance = nBalance;
      }else{
         JOptionPane.showMessageDialog(null, "The Program has had a critical and cannot create your bank account");
      }
   
     
   }
 
 /**
     Mutator for intrestRate
     @param nInterestRate
 */
   public void setIntrestRate(double nInterestRate){
      interestRate = nInterestRate;
   }
 
 /**
     Mutator for MSG // Monthly Service Charges
     @param nMSG
 */
   public void setMonthlyServiceCharges(double nMSG){
      msg = nMSG;
   }

 
 //----------Accessors
 /**
     Accessor for numWithdrawals
     @return numWithdrawals
 */
   public int getNumWithdrawals(){
      return numWithdrawals;
   }
 
 /**
     Accessor for numDeposits
     @return numDeposits
 */
   public int getNumDeposits(){
      return numDeposits;
   }
 /**
     Accessor for  balance
     @return  balance 
 */
   public double getBalance(){
      return balance;
   }
 

 
 //----------Other--Methods 
 
 /**
    method for deposit 
    @param nDeposit
    @exception InvalidDepositAmount
 */
   public void deposit(double nDeposit)
   {
      if(nDeposit > 0.0 && 10000.0 >=  nDeposit ){
         balance = nDeposit + balance;
         numDeposits++;  
      }else{
         JOptionPane.showMessageDialog(null, "You have not entered a real number that is within the requirments please try again");

      }
   
   }
 
 /**
   method for withdraw
   @param Withdraw  
   @exception InvalidWithdrawalAmount
 */
   public void withdraw(double nWithdraw)
   {
      if(nWithdraw > 0.0 && 10000.0 >=  nWithdraw){
         balance = balance - nWithdraw;
         numWithdrawals++; 
      }else{
          JOptionPane.showMessageDialog(null, "You have not entered a real number that is within the requirments please try again");
      }
   
   
    
   }
 
 /**
    method for calculating monthly intrest   
 */
   public void calcInterest(){
      double mir = 0.0; // MIA monthly interest rate
      double mi = 0.0; // monthly interest
      mir = interestRate/12;
      mi = balance * mir;
      balance = balance + mi;
   
   } 
 
 /**
   method that does the monthly process 
 */
   public void monthlyProcess(){
      balance = balance - msg;
      calcInterest();
      numDeposits = 0;
      numWithdrawals = 0;
      msg = 0;
   }
 
   public void transfer(BankAccount b, double d)
   {
      withdraw(d);
      b.deposit(d);
   }
   
}

/***************************************************************
Sub class: CheckingAccount
***************************************************************/ 
class CheckingAccount extends BankAccount implements Serializable
{
//-------fields
   private final double FEE = 2.0; 
   private int transactionCount;
   private boolean status;
   private final double THRESH_HOLD = 25.0;
//-----Constructors
   public CheckingAccount()   {
      super(0.0, 0.0, 0.0);
      transactionCount = 0;
   }
   
   public CheckingAccount(double nBalance, double nInterestRate, double nMSG)
   {
      super(nBalance, nInterestRate, nMSG);
      transactionCount = 0;
   }
//----------other----methods
    /**
         module for transaction count operations
    */
             

   public void transactionCheck(){
      transactionCount  = super.numWithdrawals + super.numDeposits;
   
   }
   
   /**
         balance check version for checking account
   */

   public void balanceCheck(){
      if(super.balance >= THRESH_HOLD){
         status = true; 
      }else{
         status = false; 
      }
   } 
   
   /**
       deposit for checking account
   */

   public void deposit(double d)
   {
      balanceCheck();
      transactionCheck();
   
      if(status){
         super.deposit(d);
      }else{
       JOptionPane.showMessageDialog(null, "Your account is inactive you can't deposit!");
 
      }
   
      if (transactionCount > 3 ){
         super.balance = super.balance - FEE;
      }
   
   
   }

   /**
        withdrawl for checking account
   */

   public void withdraw(double w)
   {
      balanceCheck();
      transactionCheck();
   
      if(status){
         super.withdraw(w);
      }else{
         JOptionPane.showMessageDialog(null, "Your account is inactive you can't withdraw!");
      }
   
      if (transactionCount > 3 ){
         super.balance = super.balance - FEE;
      }
   
    
   
   } 
   /**
     standard monthly process that also clears transactions    
   */

  
   public void monthlyProcess(){
      super.monthlyProcess();
      transactionCount = 0;
   }
   
   /**
     to string for checking account  
   */

   public String toString(){
      DecimalFormat dollar = new DecimalFormat("#,##0.00");
   
      String str = ""; 
    
      str = "Balance: $" + dollar.format(super.balance);
    
      balanceCheck();
    
      if(status == false){
          str = "Balance: $" + dollar.format(super.balance) + "This Account is inactive!"; 
      }
    
      return str; 
   }


}



/***************************************************************
Sub class: SavingsAccount
***************************************************************/ 
class SavingsAccount extends BankAccount implements Serializable
{
 //--------fields
   private boolean status; // status variable
   private final double THRESH_HOLD = 25.0; // constant for account activity threshhold 
   private final int MPC = 4;// monthly proccess constant  
 
 //--------Constructors
 
 /**
     Constructer 1 from super class 
     @param nBalance
     @param nInterestRate 
     @exception InvalidDepositAmount
 */
   public SavingsAccount(double nBalance, double nInterestRate)
   {
      super(nBalance, nInterestRate);
   }
 
 /**
     Constructer 2 from super class
     @param nBalance
     @param nInterestRate
     @param nMSG 
     @exception InvalidDepositAmount
     @exception FileNotFoundException
 */
   public SavingsAccount(double nBalance, double nInterestRate, double nMSG)
   {
      super(nBalance, nInterestRate, nMSG);
   } 
 
 //----------Accessors
 
 /**
     Accessor for status
     @return status  
 */
   public boolean getStatus(){
      return  status;
   }

 //----------Other--Methods
 
 /**
 this is a method to check if the account has falled below $25 threshold     
 */
   public void balanceCheck(){
      if(super.balance >= THRESH_HOLD){
         status = true; 
      }else{
         status = false; 
      }
   } 
 
 /**
 this method will overide the super classes withdraw method to do an account threshold check 
 @param w
 @exception InvalidWithdrawalAmount
 */
   public void withdraw(double w)
   {
      balanceCheck();
   
      if(status){
         super.withdraw(w);
      }else{
          JOptionPane.showMessageDialog(null, "Your account is inactive you can't withdraw!");
      }
      balanceCheck();
   
   }  
 
 /**
 this method will overide the super classes deposit method to do an account threshold check 
 @param d
 @exception InvalidDepositAmount
 */
   public void deposit(double d)
   {
      balanceCheck();
   
      if(status){
         super.deposit(d);
      }else{
         JOptionPane.showMessageDialog(null, "Your account is inactive you can't deposit!");
      }
   
   }
 
 /**
 this method overides monthly process to do a service charge check
 */
   public void monthlyProcess(){
      double asc = 0.0; // additional service charges
   
      if(super.numWithdrawals > MPC){
         asc = super.numWithdrawals - MPC;
         super.balance = super.balance - asc ; 
      } 
   
      super.monthlyProcess();
   
   }
 
   public String toString(){
    // Create a Decimalformat object for formatting output.
      DecimalFormat dollar = new DecimalFormat("#,##0.00");
   
      String str = ""; 
    
      str = "Balance: $" + dollar.format(super.balance);
    
      balanceCheck();
    
      if(status == false){
         str = "Balance: $" + dollar.format(super.balance) + "This Account is inactive!"; 
      }
    
      return str; 
   }
                                          
                                            
}                                         
   

/***************************************************************
Application class: SavingsDemo
the java file name is: SavingsDemo.java, Listner is in Application class. 
***************************************************************/
public class GUISavingsDemo implements ActionListener
{
   /**
   main method containing given application class code to problem.
   @exception InvalidDepositAmount
   @exception InvalidWithdrawalAmount
   @exception InputMismatchException
   @exception FileNotFoundException
   @exception IOException
   @exception ClassNotFoundException
   
   */
   static JTextField depositfield;
   static JTextField withdrawfield;
   static JTextField transferfield; 
   static JButton depButton; 
   static JButton withButton; 
   static JButton transButton;
   static JButton bonusButton;
   static JButton exitButton; 
   static JButton depButton_D;
   static JButton withButton_W;
   static JButton transButton_T;
   static JButton returnButton_D = new JButton("Back To Main Menu");
   static JButton returnButton_W;
   static JButton returnButton_T;
   static JRadioButton savings_D;
   static JRadioButton checking_D;
   static JRadioButton savings_W;
   static JRadioButton checking_W;
   static JComboBox account1_T;
   static JComboBox account2_T;
   static JFrame frame1; 
   static JFrame frame2; 
   static JFrame frame3;
   static JFrame frame4;  
   static SavingsAccount savingsAccount;
   static CheckingAccount checkingAccount;
   static String h1,h2,h3,h4,h5,h6;

   
   public static void main(String[] args)throws  InputMismatchException, FileNotFoundException,IOException, ClassNotFoundException
   {
      //objects 
       savingsAccount = new SavingsAccount(100, 2, 1);
       checkingAccount = new CheckingAccount(100,2,1);
       
      //file loadings 
      savingsAccount = readObj_S(savingsAccount);
      checkingAccount = readObj_C(checkingAccount);
      //panel methods
      
      windowMain();
      windowDeposit();
      windowWithdraw();
      windowTransfer();
   
      //runtime hook
      Runtime.getRuntime().addShutdownHook(new Thread()
       {
        @Override
        public void run()
        {
             savingsAccount.monthlyProcess();
             checkingAccount.monthlyProcess(); 
             writeObj_S(savingsAccount); // calls method to save object
             writeObj_C(checkingAccount);
        }
       
          });

       
      
     
     // readObj()_S; // prints out balance of saved object
     
   }// End Main
   
   
//-----------------------------OTHER MODULES------------------------------
            
        
            /**
             Module for writing object to binary file
             @param account
             @exception FileNotFoundException
             @exception IOException
             
             */
   public static void writeObj_C(CheckingAccount checkingAccount)
   {
      try{
         System.out.println("Saving Data from Checking Account........");
         FileOutputStream outStream = new FileOutputStream("checking.dat");
         ObjectOutputStream objectOutputFile = new ObjectOutputStream(outStream);
         objectOutputFile.writeObject(checkingAccount);
      }
      catch(FileNotFoundException e10){
         System.out.println("Error account data not saved" +e10);
      }
      catch(IOException e11){
         System.out.println("Error account data not saved" +e11);
      }
                 
      System.out.println("Account Data Saved");
               
   }
    
            /**
             Module for writing object to binary file
             @param account
             @exception FileNotFoundException
             @exception IOException
             
             */
   public static void writeObj_S(SavingsAccount savingsAccount)
   {
      try{
         System.out.println("Saving Data from Saving Account.............");
         FileOutputStream outStream = new FileOutputStream("savings.dat");
         ObjectOutputStream objectOutputFile = new ObjectOutputStream(outStream);
         objectOutputFile.writeObject(savingsAccount);
      }
      catch(FileNotFoundException e10){
         System.out.println("Error account data not saved" +e10);
      }
      catch(IOException e11){
         System.out.println("Error account data not saved" +e11);
      }
                 
      System.out.println("Account Data Saved");
               
   }
            
             
             
             /**
             Module for reading object and printing data. 
             @exception FileNotFoundException
             @exception IOException
             @exception ClassNotFoundException
             */
             
   public static CheckingAccount readObj_C(CheckingAccount checkingAccount) 
   {
      CheckingAccount h = new CheckingAccount(100,1,2);
      try{
         
         FileInputStream inStream = new FileInputStream("checking.dat");
         ObjectInputStream objectInputFile = new ObjectInputStream(inStream);
                 
         CheckingAccount savedAccount_C= (CheckingAccount)objectInputFile.readObject();
         h = savedAccount_C;
         
      }
                 
      catch(FileNotFoundException e12){
         System.out.println("Creating file......." +e12);
         writeObj_C(checkingAccount);
      }
      catch(IOException e13){
         System.out.println("Error account data corrupted or internal program error" +e13);
      }
      catch(ClassNotFoundException e14){
         System.out.println("Error account data corrupted or internal program error" +e14);
      }
               
      return h;      
   }
             

             
             /**
             Module for reading object and printing data. 
             @exception FileNotFoundException
             @exception IOException
             @exception ClassNotFoundException
             */
             
   public static SavingsAccount readObj_S(SavingsAccount savingsAccount) 
   {
      SavingsAccount h = new SavingsAccount(100,1,2);
      try{
         
         FileInputStream inStream = new FileInputStream("savings.dat");
         ObjectInputStream objectInputFile = new ObjectInputStream(inStream);
                 
         SavingsAccount savedAccount_S = (SavingsAccount)objectInputFile.readObject();
         h = savedAccount_S;
         
      }
                 
      catch(FileNotFoundException e12){
         System.out.println("Creating file......." +e12);
         writeObj_S(savingsAccount);
      }
      catch(IOException e13){
         System.out.println("Error account data corrupted or internal program error" +e13);
      }
      catch(ClassNotFoundException e14){
         System.out.println("Error account data corrupted or internal program error" +e14);
      }
               
      return h;      
   }
             
             
   //---------------------panels----------------------------------------------------------------------------------------------------------------------- 
   /**
      I decided to brake up my panels into modules instead  of having a mess in main. 
      Menu window
   */

   private static void windowMain(){
     
      
      //Declarations 
      
      frame1 = new JFrame(); 
               
      //settings
      frame1.setTitle("ACME Account Access");
      frame1.setSize(310, 125);
      frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              
      //panel             
      makePanel_1();
               
               
               
      //visibility
      frame1.setVisible(true);
   
            
   } 
   
   /**
      window for deposit
   */
   private static void windowDeposit()
   {
       //Declarations 
      
      frame2 = new JFrame(); 
               
      //settings
      frame2.setTitle("ACME Account Access Deposits");
      frame2.setSize(800, 200);
      frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              
      //panel
      makePanel_2();
     
   }
   
   /**
      window for withdraws
   */
    private static void windowWithdraw()
    {
       //Declarations 
      
      frame3 = new JFrame(); 
               
      //settings
      frame3.setTitle("ACME Account Access Withdrawls");
      frame3.setSize(800, 200);
      frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              
      //panel
      makePanel_3();
     
   }
   /**
      window for transfers
   */
   private static void windowTransfer()
    {
       //Declarations 
      
      frame4 = new JFrame(); 
               
      //settings
      frame4.setTitle("ACME Account Access Transfers");
      frame4.setSize(800, 200);
      frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              
      //panel
      makePanel_4();
     
   }

            
    //-----------------------building-------panels----------------------------------------------------------------
    /**
      building panel for main windows
   */
    private static void makePanel_1()
     {
               // label
      JLabel messageLabel = new JLabel("                              Choose an option                           ");
               
            
               // buttons
      depButton = new JButton("Deposit");
      withButton = new JButton("Withdraw"); 
      transButton = new JButton("Transfer");
      bonusButton = new JButton(" View Current Balance"); 
      exitButton = new JButton("Exit"); 
       

            
               // action listeners
      depButton.addActionListener(new GUISavingsDemo());
      withButton.addActionListener(new GUISavingsDemo());
      transButton.addActionListener(new GUISavingsDemo());
      bonusButton.addActionListener(new GUISavingsDemo());
      exitButton.addActionListener(new GUISavingsDemo());
               
       // JPanel object 
      JPanel panel = new JPanel();
            
       
      // components to the panel.
      panel.add(messageLabel);
               
      panel.add(depButton);
      panel.add(withButton);
      panel.add(transButton);
      panel.add(bonusButton);
      panel.add(exitButton);
      frame1.add(panel);
   
   }
   /**
      building panel for deposit
   */
   private static void makePanel_2()
   {
      //radio buttons
      savings_D = new JRadioButton("Savings Account");
      checking_D = new JRadioButton("Checking Account");
      
      //label 
      JLabel messageLabel = new JLabel("Enter the amount you would like to Deposit without a dollar sign. Remember ACME only accepts Deposits below $10,000");
      JLabel messageLabelh = new JLabel("                                                                                                                                                                          Choose an Account. Selecting both will deposit the same amount to each account                                                                                                                                                                                                                    ");
      //text field
      depositfield = new JTextField(7);
      
       // JPanel object 
      JPanel panel = new JPanel();

      
      // action buttons 
       depButton_D = new JButton("Deposit");
       returnButton_D = new JButton("Back To Main Menu");
       
       
      //listners
      savings_D.addActionListener(new GUISavingsDemo());
      checking_D.addActionListener(new GUISavingsDemo());
      depButton_D.addActionListener(new GUISavingsDemo());
      returnButton_D.addActionListener(new GUISavingsDemo());
      
      //panel
      panel.add(messageLabelh);
      panel.add(savings_D);
      panel.add(checking_D);
      panel.add(messageLabel);
      panel.add(depositfield);
      panel.add(depButton_D);
      panel.add(returnButton_D);
      frame2.add(panel); 
      
   }
   /**
     building panel for withdrawls   
   */
   private static void makePanel_3()
   {
      //radio buttons
      savings_W = new JRadioButton("Savings Account");
      checking_W = new JRadioButton("Checking Account");
      
      //label 
      JLabel messageLabel = new JLabel("Enter the amount you would like to Withdraw without a dollar sign. Remember ACME only accepts Withdrawls below $10,000");
      JLabel messageLabelh = new JLabel("                                                                                                                                                                         Choose an Account. Selecting both will deposit the same amount to each account                                                                                                                                                                                                                    ");
      //text field
      withdrawfield = new JTextField(7);
      
       // JPanel object 
      JPanel panel = new JPanel();

      
      // action buttons 
       withButton_W = new JButton("Withdraw");
       returnButton_W = new JButton("Back To Main Menu");
       
       
      //listners
      savings_W.addActionListener(new GUISavingsDemo());
      checking_W.addActionListener(new GUISavingsDemo());
      withButton_W.addActionListener(new GUISavingsDemo());
      returnButton_W.addActionListener(new GUISavingsDemo());
      
      //panel
      panel.add(messageLabelh);
      panel.add(savings_W);
      panel.add(checking_W);
      panel.add(messageLabel);
      panel.add(withdrawfield);
      panel.add(withButton_W);
      panel.add(returnButton_W);
      frame3.add(panel); 
      
   }
   /**
   building panel for transfers   
   */
   private static void makePanel_4()
   {
      //declarations
      String[] options = new String[] {"Savings Account", "Checking Account"};
      //label
      JLabel header = new JLabel("Please choose an account to transfer to.");
      

      //combo boxes
       account1_T = new JComboBox(options);
       account2_T = new JComboBox(options);
      
      //label 
      JLabel messageLabel = new JLabel("Enter the amount you would like to Transfers without a dollar sign. Remember ACME only accepts Transfers below $10,000");
      
      //text field
      transferfield = new JTextField(7);
      
       // JPanel object 
      JPanel panel = new JPanel();

      
      // action buttons 
       transButton_T = new JButton("Transfer");
       returnButton_T = new JButton("Back To Main Menu");
       
       
      //listners
      account1_T.addActionListener(new GUISavingsDemo());
      account2_T.addActionListener(new GUISavingsDemo());
      transButton_T.addActionListener(new GUISavingsDemo());
      returnButton_T.addActionListener(new GUISavingsDemo());
      
      //panel
      panel.add(header);
      panel.add(account1_T);
      panel.add(account2_T);
      panel.add(messageLabel);
      panel.add(transferfield);
      panel.add(transButton_T);
      panel.add(returnButton_T);
      frame4.add(panel); 
      
   }



              
   //-----------------------------event---listner---------------------------------------------------------------
   /**
      main event listner 
   */
   @Override
   public void actionPerformed(ActionEvent e)  
   {
      //holding value declaration 
      
      h1 = h2 = h3 = h4 = h5 = h6 = "";
     
      
      //windowMain
      if ( e.getSource() ==  depButton){
        frame1.setVisible(false);
        frame2.setVisible(true);
      }
        
      if ( e.getSource() ==  withButton){
         frame1.setVisible(false);
         frame3.setVisible(true);
      }

      
      if ( e.getSource() ==  transButton){
         frame1.setVisible(false);
         frame4.setVisible(true);
      }
      
      if ( e.getSource() ==  bonusButton){
         JOptionPane.showMessageDialog(null, " Your current Savings Account " +savingsAccount+"\n Your current Checking Account "+checkingAccount+"\n"+"\n"+"\n"+"            Account Activity     "+"\n Withdrawls from Savings: "+savingsAccount.numWithdrawals+"\n Withdrawls from Checking: " +checkingAccount.numWithdrawals +"\n Deposits made into savings: "+savingsAccount.numDeposits+"\n Deposits made into checking: "+checkingAccount.numDeposits+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n copyright 2017@ACME");
      }


      if ( e.getSource() == exitButton ){
         System.exit(0);
      }
      
     
      
      //windowDeposit
       // return button
       if ( e.getSource() == returnButton_D  ){
         frame1.setVisible(true);
         frame2.setVisible(false);
       } 
       
       if ( e.getSource() == depButton_D ){
          
         try
         {
              
         if (savings_D.isSelected()){
            h1 = depositfield.getText(); // h1 stands for  holding value 1
             JOptionPane.showMessageDialog(null, "Your are depositing $" + h1 + " in your Savings account."+" *To undo this transaction withdraw the amount on you have deposited");
              savingsAccount.deposit(Double.parseDouble(h1)); 
               JOptionPane.showMessageDialog(null,savingsAccount+" is your current savings account balance");
            }    
         if (checking_D.isSelected()){
            h1 = depositfield.getText(); // h1 stands for  holding value 1
             JOptionPane.showMessageDialog(null, "Your are depositing $" + h1 + " in your checking account."+" *To undo this transaction withdraw the amount you have deposited");
              checkingAccount.deposit(Double.parseDouble(h1)); 
               JOptionPane.showMessageDialog(null, checkingAccount +" is your current checking account balance");
          
             }  
         
          }
          catch(NumberFormatException e1)
            {
               JOptionPane.showMessageDialog(null, "You have not entered a depositable  number please try again. Remember it must be a real and between 0 and 10000, remember to exclude commas. For help vist www.ACME.com ");

            }

       }
      //windowWithdraw
      if ( e.getSource() == returnButton_W  ){
         frame1.setVisible(true);
         frame3.setVisible(false);
       } 
       
       if ( e.getSource() == withButton_W ){
          
         try
         {
              
         if (savings_W.isSelected()){
            h2 = withdrawfield.getText(); // h2 stands for  holding value 1
             JOptionPane.showMessageDialog(null, "Your are withdrawing $" + h2 + " from your Savings account."+" *To undo this transaction deposit the amount you have withdrawn");
              savingsAccount.withdraw(Double.parseDouble(h2)); 
               JOptionPane.showMessageDialog(null, savingsAccount +" is your current savings account balance");
            }    
         if (checking_W.isSelected()){
            h2 = withdrawfield.getText(); 
             JOptionPane.showMessageDialog(null, "Your are withdrawing $" + h2 + " from your checking account."+" *To undo this transaction deposit the amount you have withdrawn");
              checkingAccount.withdraw(Double.parseDouble(h2)); 
               JOptionPane.showMessageDialog(null, checkingAccount +" is your current checking account balance");
          
             }  
         
          }
          catch(NumberFormatException e2)
            {
               JOptionPane.showMessageDialog(null, "You have not entered a withdrawable number please try again. Remember it must be a real and between 0 and 10000, remember to exclude commas. For help vist www.ACME.com ");

            }

      }
      
      //transactionWindow
       if ( e.getSource() == returnButton_T  ){
         frame1.setVisible(true);
         frame4.setVisible(false);
       } 
       ///**
       if ( e.getSource() == transButton_T ){
          
         try
         {
         
        //  h3  = (String) account1_T.getSelectedItem();
         if (account1_T.getSelectedItem().equals("Savings Account")  && account2_T.getSelectedItem().equals("Checking Account") ){
           h3 = transferfield.getText(); 
             JOptionPane.showMessageDialog(null, "Your are Transfering $" + h3 + " from your Savings Account to your Checking Account. "+" *To undo this transaction transfer the money to the original account");
              savingsAccount.transfer(checkingAccount,Double.parseDouble(h3)); 
               JOptionPane.showMessageDialog(null, checkingAccount +" is your current checking account balance $"+savingsAccount.balance+" is your savings account balance");

           
           }    
         if (account1_T.getSelectedItem().equals("Checking Account")  && account2_T.getSelectedItem().equals("Savings Account")){
             h4 = transferfield.getText(); 
             JOptionPane.showMessageDialog(null, "Your are Transfering $" + h4 + " from your Checking Account to your Savings Account. "+" *To undo this transaction transfer the money to the original account");
              checkingAccount.transfer(savingsAccount,Double.parseDouble(h4)); 
               JOptionPane.showMessageDialog(null, savingsAccount +" is your current savings account balance $"+checkingAccount.balance+" is your checking account balance");

           

          
          }  
         
          if (account1_T.getSelectedItem().equals("Checking Account")  && account2_T.getSelectedItem().equals("Checking Account")){
            
          JOptionPane.showMessageDialog(null, "Error! Accounts can not the be the same try again. ");
          }  
         
          if (account1_T.getSelectedItem().equals("Savings Account")  && account2_T.getSelectedItem().equals("Savings Account")){
            
          JOptionPane.showMessageDialog(null, "Error! Accounts can not the be the same try again. ");
          }  
         

          }
          catch(NumberFormatException e2)
            {
               JOptionPane.showMessageDialog(null, "You have not entered a transferable number please try again. Remember it must be a real and between 0 and 10000, remember to exclude commas. For help vist www.ACME.com ");

            }

      }
              
    }
    
        
        
}

