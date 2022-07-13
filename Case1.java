import java.applet.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Case1 extends Applet {
    //declaring variables
    TextField inputHeight = new TextField("");  
    TextField inputWeight = new TextField("");
    Button computeBMI = new Button("Compute BMI");
    Label questionMark = new Label("?");
    JFrame classifications = new JFrame();
    
    boolean displayResult = false;
    boolean noInputValue = false;
    double bmi;
    
    DecimalFormat formatter = new DecimalFormat("#0.0");
    FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 30,10);
    
    String overweight = "Uh-oh, you're OVERWEIGHT. Just a few more and you'll have a BMI of normal. ";
    String healthy = "Great! You're in great shape and healthy, with the BMI of NORMAL. Keep up the good work!";
    String underweight = "Oh no, you're UNDERWEIGHT! Try to exercise and have a balanced diet. You can do it!";
    
    //drawing the texts
    public void paint(Graphics graphic){      
       //font
        graphic.setFont(new Font("MS UI Gothic", Font.PLAIN, 13));
        
       //texts for height and its position
       
        graphic.drawString("Enter your height:", 55, 40); 
        graphic.drawString("Meters", 245, 40); 
       
       //text for weight and its position
        graphic.drawString("Enter your weight:", 55, 75); 
        graphic.drawString("Kilograms", 245, 75);           
       
       //decide and give result based on bmi
        if (displayResult == true && noInputValue == false) {
            if (bmi >= 29) {
                graphic.drawString(formatter.format(bmi) + " is your bmi", 160, 155); 
                graphic.drawString("Bad news, you're OVERWEIGHT! Maintain a balanced diet and try", 25, 175);
                graphic.drawString("to exercise. Have a balanced workout and maintain your diet.", 25, 190);            
                graphic.drawString("You're nearly there!", 25, 205);       
            }
            else if (bmi >= 27) {
                graphic.drawString(formatter.format(bmi) + " is your bmi, ", 160, 155); 
                graphic.drawString("Uh-oh, you're OVERWEIGHT. Just a few more and you'll have a", 25, 175);
                graphic.drawString("BMI of normal. Have a balanced workout and maintain your diet.", 25, 190);            
                graphic.drawString("You're nearly there!", 25, 205); 
            }
            else if (bmi >= 26){
                graphic.drawString(formatter.format(bmi) + " is your bmi, ", 160, 155); 
                graphic.drawString("Nice! Your BMI is NORMAL but you're near being overweight.", 25, 175);
                graphic.drawString("Exercise regularly and maintain a balanced diet.", 25, 190);            
            }
            else if (bmi >= 20){
                graphic.drawString(formatter.format(bmi) + " is your bmi, ", 160, 155); 
                graphic.drawString("Great! You're in great shape and healthy, with the BMI of", 25, 175);
                graphic.drawString("NORMAL. Exercise regularly and maintain a balanced diet.", 25, 190); 
            }
            else if (bmi >= 18){
                graphic.drawString(formatter.format(bmi) + " is your bmi, ", 160, 155); 
                graphic.drawString("Just a little bit more and you're out of the UNDERWEIGHT.", 25, 175);
                graphic.drawString("category You're almost there, keep it up!", 25, 190); 
            }
            else {
                graphic.drawString(formatter.format(bmi) + " is your bmi, ", 160, 155); 
                graphic.drawString("Oh no, you're UNDERWEIGHT! Try to exercise and have a", 25, 175);
                graphic.drawString("balanced diet. You can do it!", 25, 190); 
            }
        }
        
        if (noInputValue == true) {
            graphic.drawString("Input values in weight/height", 25, 155);      
        }
    }
     
    //initializing
    public void init() {
        setSize(400, 250); //re-scale the size of the applet
        setBackground(new Color(255,242,219));    
        setLayout(null); //to remove collisions of the buttons, text, textfield, etc.
        
        add(inputHeight);
        inputHeight.setBounds(160, 25, 80, 25);
        
        add(inputWeight);
        inputWeight.setBounds(160, 60, 80, 25);
         
        add(computeBMI);
        computeBMI.setBounds(160, 100, 80, 25);
        computeBMI.setFocusable(false);
        
        add(questionMark);
        questionMark.setBounds(250, 100, 25, 25);
        questionMark.setFocusable(false);
        questionMark.setFont(new Font("MS UI Gothic", Font.PLAIN, 18));
        questionMark.setAlignment(1);
        questionMark.setForeground(Color.BLACK);
        questionMark.setBackground(new Color(153,204,255));
        
        
        //when button compute clicked
        this.computeBMI.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e) {
               //used a try-catch to avoid error when button is clicked even if no value in input
               try {
                   compute(); 
               }
               catch(Exception error) {
                   noInputValue = true;
                   repaint();
               }
            }
        }); 
        
        //modifying the hover frame
            hoverFrame();
              
        //mouse enters
        this.questionMark.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {  
                //get the mouse location
                Point p = MouseInfo.getPointerInfo().getLocation();
                int x = p.x;
                int y = p.y;
                
                try {
                    classifications.setUndecorated(true); //to avoid error and continue running the program
                }
                catch(Exception error){
                    //no need to print the error
                }
                
                //set location and visibility
                classifications.setLocation(x + 15, y + 10);
                classifications.setVisible(true);
                 
            }
        });
        
        //mouse leaves
        this.questionMark.addMouseListener(new MouseAdapter(){
            public void mouseExited(MouseEvent e) {  
                classifications.setVisible(false);
            }
        });
    }

   //computing bmi
    public void compute(){
        float height = Float.parseFloat(inputHeight.getText()); //get the value of height from the textfield and convert to float
        float weight = Float.parseFloat(inputWeight.getText()); //get the value of weight from the textfield and convert to float
        bmi = weight / Math.pow(height, 2);
        displayResult = true;
        noInputValue = false;
        repaint(); //call the method paint 
    }
    
   //setting up hoverframe window
    public void hoverFrame(){
        JButton bmiArea = new JButton();
        JButton statusArea = new JButton();
        JButton underweightNumber = new JButton();
        JButton underweightText = new JButton();
        JButton normalNumber = new JButton();
        JButton normalText = new JButton();
        JButton overWeightNumber = new JButton();
        JButton overWeightText = new JButton();
        
        JButton[] jbuttons = {bmiArea, statusArea, underweightNumber, underweightText,
                              normalNumber, normalText, overWeightNumber, overWeightText};
        
        for (JButton button: jbuttons) {
            classifications.add(button);
        }
        
        classifications.setAlwaysOnTop(true);
        classifications.getContentPane().setBackground(Color.lightGray);
        classifications.setSize(215, 140);
        classifications.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        for (JButton button: jbuttons) {
            classifications.add(button);
        }       
        
        for (JButton button: jbuttons) {
            button.setFocusable(false);
        } 
        
        JButton[] leftButtons = {bmiArea, underweightNumber, normalNumber, overWeightNumber};
        JButton[] rightButtons = {statusArea, underweightText, normalText, overWeightText};
        
        for (JButton button: leftButtons) {
            button.setPreferredSize(new Dimension(100, 35));
        }
        
        for (JButton button: rightButtons) {
            button.setPreferredSize(new Dimension(115, 35));
        }
        
        bmiArea.setBackground(Color.GRAY);
        bmiArea.setText("BMI");
        
        statusArea.setBackground(Color.GRAY);
        statusArea.setText("Status");
        
        underweightNumber.setBackground(Color.yellow);
        underweightNumber.setText("< 20");
        
        underweightText.setBackground(Color.white);
        underweightText.setText("Underweight");
        
        normalNumber.setBackground(new Color(153,255,153));
        normalNumber.setText("<= 26");
        
        normalText.setBackground(Color.white);
        normalText.setText("Normal");
        
        overWeightNumber.setBackground(new Color(255,102,102));
        overWeightNumber.setText("> 26");
        
        overWeightText.setBackground(Color.white);
        overWeightText.setText("Overweight");
    }
    
}
