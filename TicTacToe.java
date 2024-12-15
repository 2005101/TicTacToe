import java.util.*; //imports the package for random number generation
import java.awt.*;// abstract window tooklkit for graphics: this is for.setBounds(x,y), setTitle setSize 
import java.awt.event.*;//handling events like button clicks, mouse movement, keyboard press etc 
import javax.swing.*;//swing compoenents for Graphic user interface /GUI) like JButton,JLabel, everthing with J 
public class TicTacToe implements ActionListener{// to implement an interface enbaling it to respond to button clicks 
    // declare 
    Random random=new Random(); // help us choose random 
    JFrame frame=new JFrame();// main window of the application 
    JPanel title_panel=new JPanel(); //panel for the title text  
    JPanel button_panel=new JPanel(); //store all the button 
    JLabel textfield=new JLabel();//label to display the current turn or winner
    JButton[] buttons=new JButton[9]; // array to hold 9 buttons 
    boolean player1_turn;// true if player 1 turn 
    JButton restartButton = new JButton("Restart");
    JButton quitButton = new JButton("Quit");


    TicTacToe(){ // constructor 
        // setting up the frame 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // to close the app
        frame.setSize(700, 700); // frame size 
        frame.getContentPane().setBackground(new Color( 127, 25, 23));
        frame.setLayout(new BorderLayout());// orgnize panels 
        frame.setVisible(true);// make the frame visible 

        //set background color 
        // this is mainly for the text field 
        textfield.setBackground(new Color( 25,25,25));
        textfield.setForeground(new Color( 255, 204, 203));
        textfield.setFont(new Font("Ink Free",Font.BOLD,75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic Tac Toe");
        textfield.setOpaque(true);// alows background color to show 

        //title panel layount
        title_panel.setLayout(new BorderLayout());// border layount for the title panel 
        title_panel.setBounds(0,0,700,50);// positon and size of the title panel 

        //button
        button_panel.setLayout(new GridLayout(3,3));// HERE we create the grid 
        button_panel.setBackground(new Color( 255, 204, 203)); // changes the color 

        //restart button
        restartButton.setFont(new Font("MV Boli", Font.BOLD, 30));// font
        restartButton.setFocusable(false);
        restartButton.addActionListener(e -> restartGame());

        //quit button
    
        quitButton.setFont(new Font("MV Boli", Font.BOLD, 30));
        quitButton.setFocusable(false);
        quitButton.addActionListener(e -> System.exit(0));
    

        // loop
        for (int i = 0; i< 9; i++) {
            buttons[i]=new JButton(); // create the button 
            button_panel.add(buttons[i]); // add it 
            buttons[i].setFont(new Font ("MV Boli",Font.BOLD,120));
            buttons[i].setFocusable(false);// disbales focus border on buttons 
            buttons[i].addActionListener(this);// registers the current class as the buttons action listener 
        }

        title_panel.add(textfield);// added the text to the panel 
        frame.add(title_panel,BorderLayout.NORTH); // call out add and out the text up 
        frame.add(button_panel);

        firstturn();// calls the method to decide randomly who plays first
        // add the buttons of quit and restart to the frame 
        frame.add(restartButton, BorderLayout.EAST);
        frame.add(quitButton, BorderLayout.SOUTH);
        restartButton.setVisible(false);
        quitButton.setVisible(false); 
    } 
    @Override
    public void actionPerformed(ActionEvent e){// we need to use this method as we are using an action listener, it handles button clicks 
        for (int i = 0; i < 9; i++) {
            if (e.getSource()==buttons[i]) { // check which button was clicked 
                if (player1_turn) {
                    if (buttons[i].getText()=="") {
                        buttons[i].setForeground(new Color(255,0,0));
                        buttons[i].setText("x");
                        player1_turn=false;// switch to player 2 
                        textfield.setText("o turn");// update the lable 
                        check();
                    }
                }else {          
                    if (buttons[i].getText()=="") 
                    buttons[i].setForeground(new Color(0,0,255));
                    buttons[i].setText("o");
                    player1_turn=true;
                    textfield.setText("x turn");
                    check();
                    
                }
                
            }
            
        }

    }
    // game logic here starts 
    public void firstturn(){// to determine radomly which one start first 
        try{
        Thread.sleep(2000); // it does not show up until after this amount of time 
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        if (random.nextInt(2)==0) {
            player1_turn=true;
            textfield.setText("x turn");
            
        }else {
            player1_turn=false;
            textfield.setText("o turn");
        }
    }

    public void check(){ // check who wins 
        //this is for the first row
        if ((buttons[0].getText()=="x")&&
            (buttons[1].getText()=="x")&&
            (buttons[2].getText()=="x")) {
            xWins(0, 1, 2);
        }
        if ((buttons[3].getText()=="x")&&
            (buttons[4].getText()=="x")&&
            (buttons[5].getText()=="x")) {
            xWins(3, 4, 5);
        }
        if ((buttons[6].getText()=="x")&&
            (buttons[7].getText()=="x")&&
            (buttons[8].getText()=="x")) {
            xWins(6, 7, 8);
        }
        if ((buttons[0].getText()=="x")&&
            (buttons[3].getText()=="x")&&
            (buttons[6].getText()=="x")) {
            xWins(0, 3, 6);
        }
        if ((buttons[1].getText()=="x")&&
            (buttons[4].getText()=="x")&&
            (buttons[7].getText()=="x")) {
            xWins(1, 4, 7);
        }
        if ((buttons[2].getText()=="x")&&
            (buttons[5].getText()=="x")&&
            (buttons[8].getText()=="x")) {
            xWins(2, 5, 8);
        }
        if ((buttons[0].getText()=="x")&&
            (buttons[4].getText()=="x")&&
            (buttons[8].getText()=="x")) {
            xWins(0, 4, 8);
        }
        if ((buttons[2].getText()=="x")&&
            (buttons[4].getText()=="x")&&
            (buttons[6].getText()=="x")) {
            xWins(2, 4, 6);
        }
        // same thing for o 
    if ((buttons[0].getText()=="o")&&
        (buttons[1].getText()=="o")&&
        (buttons[2].getText()=="o")) {
        oWins(0, 1, 2);
    }
    if ((buttons[3].getText()=="o")&&
        (buttons[4].getText()=="o")&&
        (buttons[5].getText()=="o")) {
        oWins(3, 4, 5);
    }
    if ((buttons[6].getText()=="o")&&
        (buttons[7].getText()=="o")&&
        (buttons[8].getText()=="o")) {
        oWins(6, 7, 8);
    }
    if ((buttons[0].getText()=="o")&&
        (buttons[3].getText()=="o")&&
        (buttons[6].getText()=="o")) {
        oWins(0, 3, 6);
    }
    if ((buttons[1].getText()=="o")&&
        (buttons[4].getText()=="o")&&
        (buttons[7].getText()=="o")) {
        oWins(1, 4, 7);
    }
    if ((buttons[2].getText()=="o")&&
        (buttons[5].getText()=="o")&&
        (buttons[8].getText()=="o")) {
        oWins(2, 5, 8);
    }
    if ((buttons[0].getText()=="o")&&
        (buttons[4].getText()=="0")&&
        (buttons[8].getText()=="o")) {
        oWins(0, 4, 8);// calls the method of oWins 
    }
    if ((buttons[2].getText()=="o")&&
        (buttons[4].getText()=="o")&&
        (buttons[6].getText()=="o")) {
        oWins(2, 4, 6);
    }
    }
    public void xWins(int a, int b, int c){
        buttons[a].setBackground(Color.GREEN);// showcase the winning line 
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);// disable all  buttons    
        }
        textfield.setText("x wins");// display the winner 
        showEndGameOptions(); // Show restart and quit buttons
    }
    // same thing for oWins method 
    public void oWins(int a, int b, int c){
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);// enable buttons    
        }
        textfield.setText("o wins");
        showEndGameOptions(); // Show restart and quit buttons
    }
    //method to show end of game options 
    private void showEndGameOptions() {
        restartButton.setVisible(true);
        quitButton.setVisible(true);
    }
    
    public void restartGame() {
        // Reset all buttons
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
            buttons[i].setBackground(new JButton().getBackground());
        }
        textfield.setText("Tic Tac Toe");
        restartButton.setVisible(false);
        quitButton.setVisible(false);
        firstturn(); // start over 
    }
 

} 
