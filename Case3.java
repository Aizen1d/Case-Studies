import java.applet.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

public class Case3 extends Applet {
   //variables
    Checkbox meal1 = new Checkbox();
    Checkbox meal2 = new Checkbox();
    Checkbox meal3 = new Checkbox();
     
    Checkbox drinks1 = new Checkbox();
    Checkbox drinks2 = new Checkbox();
    Checkbox drinks3 = new Checkbox();
    
    Checkbox rice = new Checkbox();
    
    TextField tmeal1 = new TextField();
    TextField tmeal2 = new TextField();
    TextField tmeal3 = new TextField();
    
    TextField tdrinks1 = new TextField();
    TextField tdrinks2 = new TextField();
    TextField tdrinks3 = new TextField();
    
    TextField textraRice = new TextField();
    
    TextField total = new TextField();
    
    DecimalFormat formatter = new DecimalFormat("#0.00");
    
    Checkbox[] meals = {meal1, meal2, meal3};
    Checkbox[] drinks = {drinks1, drinks2, drinks3};
    
    TextField[] tmeals = {tmeal1, tmeal2, tmeal3};
    TextField[] tdrinks = {tdrinks1, tdrinks2, tdrinks3};
  
    double[] mealPrices = {100.00,
                        120.00,
                        150.00    };
    
    double[] drinkPrices = {30.00,
                        40.00,
                        50.00    };
    
    double extraRice = 15.00;
    double totalPrice = 0.00;
    
    double[] productSubtotal = new double[7];
    
    public void paint(Graphics graphic) {
       //title
        graphic.setFont(new Font("MS UI Gothic", Font.BOLD, 25));
        graphic.drawString("MENU FOR TODAY", 90, 40);
        
       //combo meals
        graphic.setFont(new Font("MS UI Gothic", Font.PLAIN, 13));
        graphic.drawString("Combo Meal 1 Php " + String.valueOf(formatter.format(mealPrices[0])), 130, 69);
        graphic.drawString("Combo Meal 2 Php " + String.valueOf(formatter.format(mealPrices[1])), 130, 94);
        graphic.drawString("Combo Meal 3 Php " + String.valueOf(formatter.format(mealPrices[2])), 130, 119);
        
       //drinks
        graphic.drawString("Drink 1 Php " + String.valueOf(formatter.format(drinkPrices[0])), 130, 168);
        graphic.drawString("Drink 2 Php " + String.valueOf(formatter.format(drinkPrices[1])), 130, 194);
        graphic.drawString("Drink 3 Php " + String.valueOf(formatter.format(drinkPrices[2])), 130, 219);
         
       //extra-rice 
        graphic.drawString("Extra Rice Php " + String.valueOf(formatter.format(extraRice)), 130, 258);
        
       //total
        graphic.setFont(new Font("MS UI Gothic", Font.BOLD, 15));
        graphic.drawString("Total", 100, 295);
    }
    
    public void init() {
        setSize(400, 400); //re-scale the size of the applet
        setLayout(null);
        
       //properties
        int mealYPosition = 0;
        for (Checkbox check: meals) {
            add(check);
            check.setBounds(100, 51 + mealYPosition , 30, 30);
            mealYPosition = mealYPosition + 25;
        }
        
        int drinkYPosition = 0;
        for (Checkbox drink: drinks) {
            add(drink);
            drink.setBounds(100, 150 + drinkYPosition , 30, 30);
            drinkYPosition = drinkYPosition + 25;
        }
        
        add(rice);
        rice.setBounds(100, 239, 30, 30);
             
        int tMealYPosition = 0;
        for (TextField check: tmeals) {
            add(check);
            check.setBounds(50, 56 + tMealYPosition , 40, 20);
            check.setEnabled(false);
            tMealYPosition = tMealYPosition + 25;
        }
        
        int tDrinkYPosition = 0;
        for (TextField drink: tdrinks) {
            add(drink);
            drink.setBounds(50, 155 + tDrinkYPosition , 40, 20);
            drink.setEnabled(false);
            tDrinkYPosition = tDrinkYPosition + 25;
        }
        
        add(textraRice);
        textraRice.setBounds(50, 244, 40, 20);
        textraRice.setEnabled(false);
        
        add(total);
        total.setBounds(145, 281, 70, 20);
        total.setText("0.00");
     
    //actionListeners
    for (Checkbox meal: meals) {
        meal.addItemListener(new ItemListener() {   
            public void itemStateChanged(ItemEvent e) { 
                if (e.getStateChange() == 1) { //when checked
                    
                    if (e.getSource() == meal1) {
                        tmeal1.setEnabled(true);
                    }
                    if (e.getSource() == meal2) {
                        tmeal2.setEnabled(true);
                    }
                    if (e.getSource() == meal3) {
                        tmeal3.setEnabled(true);
                    }
                    
                }
                
                else {
                    if (e.getSource() == meal1) {
                        tmeal1.setEnabled(false);
                        tmeal1.setText("");
                        calculate();
                    } 
                    if (e.getSource() == meal2) {
                        tmeal2.setEnabled(false);
                        tmeal2.setText("");
                        calculate();
                    }
                    if (e.getSource() == meal3) {
                        tmeal3.setEnabled(false);
                        tmeal3.setText("");
                        calculate();
                    }
                }
                
            }
        });
    }
            
    for (Checkbox drink: drinks) {
        drink.addItemListener(new ItemListener() {   
            public void itemStateChanged(ItemEvent e) { 
                if (e.getStateChange() == 1) { //when checked
                    
                    if (e.getSource() == drinks1) {
                        tdrinks1.setEnabled(true);
                    }
                    if (e.getSource() == drinks2) {
                        tdrinks2.setEnabled(true);
                    }
                    if (e.getSource() == drinks3) {
                        tdrinks3.setEnabled(true);
                    }
                }
                
                else {
                    if (e.getSource() == drinks1) {
                        tdrinks1.setEnabled(false);
                        tdrinks1.setText("");
                        calculate();
                    }
                    if (e.getSource() == drinks2) {
                        tdrinks2.setEnabled(false);
                        tdrinks2.setText("");
                        calculate();
                    }
                    if (e.getSource() == drinks3) {
                        tdrinks3.setEnabled(false);
                        tdrinks3.setText("");
                        calculate();
                    }
                }
                
            }
        });
    }   
        
    rice.addItemListener(new ItemListener() {   
        public void itemStateChanged(ItemEvent e) {     
            if (e.getStateChange() == 1) { //when checked
                textraRice.setEnabled(true);
                textraRice.setText("");
                calculate();
            }
            else{
                textraRice.setEnabled(false);
                textraRice.setText("");
                calculate();
            }
        }
    });
       
    for (TextField meals: tmeals) {
        meals.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) { 
                if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) { //when pressed backspace 
                   calculate();
                }
                else { //when inputting
                   calculate();
                }              
            }
        });
    }
    
    for (TextField drinks: tdrinks) {
        drinks.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) { 
                if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) { //when pressed backspace 
                   calculate();
                }
                else { //when inputting
                   calculate();
                }              
            }
        });
    }
        
    textraRice.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) { 
            if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) { //when pressed backspace 
                  calculate();
                }
            else { //when inputting
                 calculate();
            }              
        }
    });
    
    }
    
   //handles the calculation, sum up all and display the total
    public void calculate() {
        double meals = 0;
        
    try {
        if (!tmeal1.getText().isEmpty()) {
            meals = meals + (Double.parseDouble(tmeal1.getText()) * mealPrices[0]);
        }
        if (!tmeal2.getText().isEmpty()) {
            meals = meals + (Double.parseDouble(tmeal2.getText()) * mealPrices[1]);
        }
        if (!tmeal3.getText().isEmpty()) {
            meals = meals + (Double.parseDouble(tmeal3.getText()) * mealPrices[2]);
        }
        
        if (!tdrinks1.getText().isEmpty()) {
            meals = meals + (Double.parseDouble(tdrinks1.getText()) * drinkPrices[0]);
        }
        if (!tdrinks2.getText().isEmpty()) {
            meals = meals + (Double.parseDouble(tdrinks2.getText()) * drinkPrices[1]);
        }
        if (!tdrinks3.getText().isEmpty()) {
            meals = meals + (Double.parseDouble(tdrinks3.getText()) * drinkPrices[2]);
        }
        
        if (!textraRice.getText().isEmpty()) {
            meals = meals + (Double.parseDouble(textraRice.getText()) * extraRice);
        }
    }
    catch(Exception e) {
        System.out.println("Enter digit(s) only");
    }
        
        total.setText(String.valueOf(formatter.format(meals)));
    }
      
}
