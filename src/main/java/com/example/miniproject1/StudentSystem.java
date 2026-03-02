package com.example.miniproject1;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentSystem extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Stage ApplicationStage = new Stage();
        ApplicationStage.setTitle("Student Information System");
        ApplicationStage.setResizable(false);

        GridPane grid1 = new GridPane();
        grid1.setHgap(10);
        grid1.setVgap(10);

        Text scenetitle1 = new Text("Student");
        scenetitle1.setFont(Font.font("Tahoma", FontWeight.NORMAL, 32));
        grid1.add(scenetitle1, 0, 0, 2, 1);

        Label userNameLbl = new Label("User Name:");
        grid1.add(userNameLbl, 0, 1);

        TextField userFld = new TextField();
        userFld.setPromptText("Enter your username");
        grid1.add(userFld, 1, 1);

        Label dobLbl = new Label("Date of Birth:");
        grid1.add(dobLbl, 0, 2);

        DatePicker dobPck = new DatePicker();
        dobPck.setValue(LocalDate.now());
        grid1.add(dobPck, 1, 2);


        Label genderLbl = new Label("Gender:");
        grid1.add(genderLbl, 0, 3);

        RadioButton radioButton1 = new RadioButton("Male");
        radioButton1.setSelected(true);
        RadioButton radioButton2 = new RadioButton("Female");
        ToggleGroup radioGroup = new ToggleGroup();
        radioButton1.setToggleGroup(radioGroup);
        radioButton2.setToggleGroup(radioGroup);
        HBox radioBox = new HBox(radioButton1, radioButton2);
        radioBox.setSpacing(25);
        grid1.add(radioBox, 1, 3);

        Label degreeLbl = new Label("Degree:");
        grid1.add(degreeLbl, 0, 4);

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().add("Computer Science");
        choiceBox.getItems().add("Biomedical Engineering");
        choiceBox.getItems().add("Psychology");
        choiceBox.getItems().add("Biology");
        choiceBox.getSelectionModel().selectFirst();
        grid1.add(choiceBox, 1, 4);

        Label addLbl = new Label("Address:");
        grid1.add(addLbl, 0, 5);

        TextField addrFld = new TextField();
        addrFld.setPromptText("Enter your current address");
        grid1.add(addrFld, 1, 5);

        Label ageLbl = new Label("Age :");
        grid1.add(ageLbl, 0, 6);

        TextField ageFld = new TextField();
        ageFld.setPromptText("Enter your current age");
        grid1.add(ageFld, 1, 6);

        Label gpaLbl = new Label("GPA :");
        grid1.add(gpaLbl, 0, 7);

        TextField gpaFld = new TextField();
        gpaFld.setPromptText("Enter your GPA");
        grid1.add(gpaFld, 1, 7);

        Button submitBtn = new Button("Submit");
        Text errorMsg1 = new Text();
        errorMsg1.setFill(Color.RED);
        HBox hbox = new HBox(submitBtn, errorMsg1);
        hbox.setAlignment(Pos.CENTER);


        TableView<Student> table = new TableView<>();

        TableColumn<Student, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setMinWidth(100);

        TableColumn<Student, String> dobCol = new TableColumn<>("Date of Birth");
        dobCol.setCellValueFactory(new PropertyValueFactory<>("dob"));
        dobCol.setMinWidth(100);

        TableColumn<Student, String> genderCol = new TableColumn<>("Gender");
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        genderCol.setMinWidth(100);

        TableColumn<Student, String> degreeCol = new TableColumn<>("Degree");
        degreeCol.setCellValueFactory(new PropertyValueFactory<>("degree"));
        degreeCol.setMinWidth(100);

        TableColumn<Student, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        addressCol.setMinWidth(100);

        TableColumn<Student, String> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        ageCol.setMinWidth(100);

        TableColumn<Student, String> gpaCol = new TableColumn<>("GPA");
        gpaCol.setCellValueFactory(new PropertyValueFactory<>("gpa"));
        gpaCol.setMinWidth(100);

        table.getColumns().addAll(nameCol, dobCol, genderCol, degreeCol, addressCol, ageCol, gpaCol);

        ObservableList<Student> data = FXCollections.observableArrayList();
        data.add(new Student("John", "10/10/2010", "Male", "Computer Science", "NY", "18", "81"));
        data.add(new Student("Sara", "5/5/2000","Female", "Psychology", "CA", "22", "89"));
        table.setItems(data);

        VBox vbox = new VBox(25);
        vbox.setPadding(new Insets(25));
        vbox.getChildren().addAll(grid1, hbox, table );

        Scene scene = new Scene(vbox, 800, 600);
        ApplicationStage.setScene(scene);

        submitBtn.setOnAction(evt -> {
            String username = userFld.getText();
            String dob = dobPck.getValue().toString();
            String gender = ((RadioButton)radioGroup.getSelectedToggle()).getText();
            String degree = choiceBox.getValue();
            String address = addrFld.getText();
            String age = ageFld.getText();
            String gpa = gpaFld.getText();

            if(!username.isEmpty()
                    && !dob.isEmpty()
                    && !gender.isEmpty()
                    && !degree.isEmpty()
                    && !address.isEmpty()
                    && !age.isEmpty()
                    && !gpa.isEmpty()
                    && isNumeric(gpa)
                    && isNumeric(age)) {
                data.add(new Student(username, dob, gender, degree, address, age, gpa));

                userFld.setText("");
                dobPck.setValue(LocalDate.now());
                radioButton1.setSelected(true);
                choiceBox.getSelectionModel().selectFirst();
                addrFld.setText("");
                ageFld.setText("");
                gpaFld.setText("");
            }else{
                errorMsg1.setText("Please fill all the fields and make sure that age and gpa are numbers");
            }
        });

        primaryStage.setTitle("Sign In Form");
        primaryStage.setResizable(false);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button btn = new Button("Sign in");
        grid.add(btn, 1, 4);

        Text errorMsg = new Text();
        errorMsg.setFill(Color.RED);
        grid.add(errorMsg, 0, 5, 2, 1);

        Text welcomeMsg = new Text("Welcome");
        welcomeMsg.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        welcomeMsg.setFill(Color.GREEN);
        StackPane welcomeStack = new StackPane();
        welcomeStack.getChildren().add(welcomeMsg);
        Scene welcomeScene = new Scene(welcomeStack, 300, 275);


        btn.setOnAction(evt -> {
                    String username = userTextField.getText();
                    String password = pwBox.getText();

                    if(username.equals("") || password.equals("")) {
                        errorMsg.setText("Username and password are required!");
                    }
                    else if(!username.equals("admin") || !password.equals("123456")) {
                        errorMsg.setText("Incorrect username and/or password");
                    }
                    else if(username.equals("admin") && password.equals("123456")) {
                        primaryStage.setScene(welcomeScene);
                        ApplicationStage.show();
                    }
                }
        );

        Scene scene2 = new Scene(grid, 300, 275);
        primaryStage.setScene(scene2);

        primaryStage.show();

    }

    public boolean isNumeric(String strNum) {
        try {
            Integer.parseInt(strNum);
            return true;
        }catch (NumberFormatException e) {
            return false;
        }
    }

    //Data Model for Student TableView
    public static class Student {
        private final String name;
        private final String dob;
        private final String gender;
        private final String degree;
        private final String address;
        private final String age;
        private final String gpa;
        public Student(String name, String dob, String gender, String degree, String address, String age, String gpa) {
            this.name =  name;
            this.dob = dob;
            this.gender =  gender;
            this.degree = degree;
            this.address = address;
            this.age = age;
            this.gpa = gpa;
        }
        public String getName() {return name;}
        public String getGender() {
            return gender;
        }
        public String getDob() {
            return dob;
        }
        public String getDegree() {
            return degree;
        }
        public String getAddress() {
            return address;
        }
        public String getAge() {return age;}
        public String getGpa() {return gpa;}
    }
}
