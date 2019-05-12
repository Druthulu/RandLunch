import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import java.util.*;
import java.io.*;
//created by andrew tschudy on 2/10/2019 under GPL v3
//if anyone is reading this :p i attempted to do an object to store the data, but it was just a mess lol. so txt file and scanner was the fallback.
public class RandLunch extends Application {

   @FXML TextArea output;
   @FXML TextArea listLoc;
   @FXML Button butRandLoc;
   @FXML Button butListLoc;
   @FXML Button butQuit;
   @FXML TextArea instruct;
   @FXML TextField credit;
   
   
   //lists lines in lunch locations
   @FXML protected void ListLoc(ActionEvent event){

      
      //try catch, to make sure the file exists, if not instructions to create
      try {
        File file = new File("Lunch Locations.txt");
        Scanner sc = new Scanner(file);
        //clear TextArea listLoc
        listLoc.setText(" ");
        //loop scanner each line and output
        while (sc.hasNextLine()) {
            String temp = listLoc.getText();
            listLoc.setText(temp + "\n" + sc.nextLine());
        }
        //close scanner on file
        sc.close();
      } 
      catch (FileNotFoundException e) {
        listLoc.setText("Cannot find Lunch Locations.txt \n make sure its in the same \n directory as RandLunch.jar");
      }
   }
   
   //randomly pick line in lunch locations text file
   @FXML protected void RandLoc(ActionEvent event){
      
      
      //try catch, to make sure the file exists, if not instructions to create
      try {
         FileInputStream fs = new FileInputStream("Lunch Locations.txt");
         BufferedReader br = new BufferedReader(new InputStreamReader(fs));
         ArrayList<String> array = new ArrayList<>();
         String strLine;
         //secondary try catch for the readline in the buffer reader
         try {
            while((strLine = br.readLine()) != null) {
               array.add(strLine);
            }
            //create random var
            Random ranLine = new Random();
   
            //determine random line
            int randomIndex = ranLine.nextInt(array.size());
   
            //output random line
            output.setText(array.get(randomIndex));
         } catch (IOException e) {
            listLoc.setText("Cannot find Lunch Locations.txt \n make sure its in the same \n directory as RandLunch.jar");
         }
      } catch (FileNotFoundException e) {
        listLoc.setText("Cannot find Lunch Locations.txt \n make sure its in the same \n directory as RandLunch.jar");
      }
   }
   
   //quit the program and tell user to enjoy lunch
   @FXML protected void Quit(ActionEvent event){
      new Timer().schedule(
         new TimerTask() {
            public void run () { System.exit(0); }
         }, 750); 
      output.setText("Enjoy Your Lunch");
   }    
   
   //generic java FXML load sequence
   public void start(Stage primaryStage) throws Exception {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("RandLunch.fxml"));
      loader.setController(this);
      Parent root = loader.load();
      Scene myScene = new Scene(root,800,400);
      primaryStage.setScene(myScene);
      primaryStage.show();
   }
   //generic java launch cmds
   public static void main(String[] args) {
      launch(args);
      }
}

/*

 GNU GENERAL PUBLIC LICENSE
                       Version 3, 29 June 2007

 Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/>
 Everyone is permitted to copy and distribute verbatim copies
 of this license document, but changing it is not allowed.
...
    RandLunch Copyright (C) 2019  Andrew Tschudy
    This program comes with ABSOLUTELY NO WARRANTY
...
*/