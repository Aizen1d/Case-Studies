import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Case2 extends Applet {
   //declaring variables
    Button addButton = new Button("+");
    Button subtractButton = new Button("-");
    Button multiplyButton = new Button("*");
    Button divideButton = new Button("/");
    Button equalButton = new Button("=");
    
    Button onButton = new Button("ON");
    Button offButton = new Button("OFF");
    Button clearButton = new Button("Clear");
    
    TextField firstInput = new TextField();
    TextField secondInput = new TextField();
    TextField resultOutput = new TextField();
    
   //storing operation in a variable
    String operation = "";
    
   //array of objects
    Button[] operationButtons = {addButton, subtractButton, multiplyButton, divideButton};
    Button[] miscButtons = {offButton, onButton, clearButton};
    TextField[] textfields = {firstInput, secondInput, resultOutput};
    
    public void init() {
        setSize(400, 250); //re-scale the size of the applet
        setLayout(null);
        
       //setting up the button
        int buttonPositionX = 0;
        for (Button button: operationButtons) {
            button.setBounds(140 + buttonPositionX, 40, 25, 25);
            add(button).setFocusable(false);
            buttonPositionX = buttonPositionX + 30;
            button.setEnabled(false);
        }
        
        add(equalButton).setBounds(185, 100, 25, 25);
        equalButton.setFocusable(false);
        equalButton.setEnabled(false);
        
        //setting up the textfields
        int textfieldPositionY = 0;
        for (TextField textfield: textfields) {
            add(textfield).setBounds(10, 10 + textfieldPositionY, 380, 25);
            textfieldPositionY = textfieldPositionY + 60;
            textfield.setEditable(false);
        }
        
        resultOutput.setEditable(false);
        
        int button2PositionX = 0;
        for (Button button: miscButtons){
            button.setBounds(105 + button2PositionX, 160, 55, 25);
            button2PositionX = button2PositionX + 65;
            add(button).setFocusable(false);
            button.setEnabled(false);
        } 
        
        onButton.setEnabled(true);
        
       //it handles when the button on is clicked
        onButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                for (Button button: miscButtons) {
                    button.setEnabled(true);
                }
                
                for (Button button: operationButtons) {
                    button.setEnabled(true);
                }
                
                for (TextField textfield: textfields) {
                    textfield.setEditable(true);
                    textfield.setEnabled(true);
                }
                
                resultOutput.setEditable(false);
                equalButton.setEnabled(true);
                onButton.setEnabled(false);
            }
        });
        
        offButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                for (Button button: miscButtons) {
                    button.setEnabled(false);
                }
                
                for (Button button: operationButtons) {
                    button.setEnabled(false);
                    button.setBackground(new Color(240, 240, 240));
                }
                
                for (TextField textfield: textfields) {
                    textfield.setEnabled(false);
                    textfield.setEditable(false);
                    textfield.setText(" ");
                    textfield.setText("");
                }
                
                operation = "";
                equalButton.setEnabled(false);
                onButton.setEnabled(true);
            }
        });
        
        clearButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                for (TextField textfield: textfields) {
                    textfield.setText(" ");  
                    textfield.setText("");  
                }  
                
                for (Button button: operationButtons) {
                    button.setBackground(new Color(240, 240, 240));
                }
                
                operation = "";
            }
        });
        
        addButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                operation = "Add";
                
                //set other buttons to normal color
                for (Button button: operationButtons) {
                    button.setBackground(new Color(240, 240, 240));
                }
                
                addButton.setBackground(new Color(153,255,153));
                
            }
        });
        
        subtractButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                operation = "Subtract";
                
                //set other buttons to normal color
                for (Button button: operationButtons) {
                    button.setBackground(new Color(240, 240, 240));
                }
                
                subtractButton.setBackground(new Color(153,255,153));
            }
        });
        
        multiplyButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                operation = "Multiply";
                
                //set other buttons to normal color
                for (Button button: operationButtons) {
                    button.setBackground(new Color(240, 240, 240));
                }
                
                multiplyButton.setBackground(new Color(153,255,153));
            }
        });
        
        divideButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                operation = "Divide";
                
                //set other buttons to normal color
                for (Button button: operationButtons) {
                    button.setBackground(new Color(240, 240, 240));
                }
                
                divideButton.setBackground(new Color(153,255,153));
            }
        });
          
        equalButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) { 
                compute();
            }
        });
        
    }
    
   //handles the computation for each operation
    public void compute(){
        //initializing variables to be used later
        float input1 = 0;
        float input2 = 0;
        float result = 0;
        
        try {
            input1 = Float.parseFloat(firstInput.getText());  
        }
        catch(Exception e){
            if (operation.equals("Divide")) {
                resultOutput.setText("Syntax Error");
            return;
            }
        }
        
        try {
            input2 = Float.parseFloat(secondInput.getText());
        }
        catch(Exception e){
            if (operation.equals("Divide")) {
                resultOutput.setText("Syntax Error");
            return;
            }
        }
        
        if (operation.equals("Add")) {
            result = input1 + input2;
        }
        else if (operation.equals("Subtract")) {
            result = input1 - input2;
                }
        else if (operation.equals("Multiply")) {
            result = input1 * input2;
        }
        else if (operation.equals("Divide")) {
            result = input1 / input2;
            if (input2 == 0) {
                resultOutput.setText("Syntax Error");
            return;
            }
        }
        else if (operation.equals("") && firstInput.getText().equals("")) {
            resultOutput.setText(secondInput.getText());
            return;
        }
        else if (operation.equals("") && secondInput.getText().equals("")) {
            resultOutput.setText(firstInput.getText());
            return;
        }
        else {
            resultOutput.setText("Syntax Error");
            return;
        }
        
        if (result % 1 == 0) {
            int integerResult = (int)result;
            resultOutput.setText(String.valueOf(integerResult));
        }
        else {
           resultOutput.setText(String.valueOf(result));
        }
    }
}
