package sample.Controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.*;
import sample.Animations.Shake;
import sample.DataBaseConnection.Const;
import sample.DataBaseConnection.DatabaseHandler;

public class Table implements Initializable, ControlledScreen {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<UserDetails, String> groupnameCol;

    @FXML
    private TableColumn<UserDetails, String> phoneCol;

    @FXML
    private TableColumn<UserDetails, String> idCol;

    @FXML
    private TableColumn<UserDetails, String> dateofBirthCol;

    @FXML
    private TableColumn<UserDetails, String> firstnameCol;

    @FXML
    private TableColumn<UserDetails, String> lastnameCol;

    @FXML
    private TableColumn<UserDetails, String> emailCol;

    @FXML
    private TableColumn<UserDetails, String> genderCol;

    @FXML
    private TableView<UserDetails> table;
    @FXML
    private JFXButton deleteButton;

    @FXML
    private JFXButton addButton;

    @FXML
    private JFXTextField SignUpName;

    @FXML
    private JFXTextField groupName_field;

    @FXML
    private JFXRadioButton genderMale;

    @FXML
    private JFXRadioButton genderFemale;

    @FXML
    private JFXTextField SignUpLastName;

    @FXML
    private JFXTextField SignUpEmail;

    @FXML
    private JFXTextField SignUpPhone;

    @FXML
    private JFXButton loadTableBtn;


    @FXML
    public JFXDatePicker dobField;
    DatabaseHandler dbHandler = new DatabaseHandler();

    final ObservableList<UserDetails> observableList = FXCollections.observableArrayList();
    DatabaseHandler databaseHandler = new DatabaseHandler();

    ScreensController myController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addAndRefresh();
        deleteUser();
        loadTable();

    }


    ToggleGroup toggle = new ToggleGroup();


    private void addAndRefresh() {
        genderMale.setToggleGroup(toggle);
        genderFemale.setToggleGroup(toggle);


            addButton.setOnAction(event -> {
                String gender;
                if (genderMale.isSelected()) {
                    gender = genderMale.getText();
                } else {
                    gender = genderFemale.getText();
                }




                ArrayList<JFXTextField> fields = new ArrayList<>();
                fields.add(SignUpName);fields.add(SignUpLastName); fields.add(groupName_field); fields.add(SignUpEmail); fields.add(SignUpPhone);




                if (!SignUpName.getText().isEmpty() && !SignUpLastName.getText().isEmpty() && !groupName_field.getText().isEmpty() && !SignUpEmail.getText().isEmpty() && !SignUpPhone.getText().isEmpty()&&validateFirstName()&& validateLastName()) {
                    String firstname = SignUpName.getText();
                    String lastname = SignUpLastName.getText();
                    String groupname = groupName_field.getText();
                    String email = SignUpEmail.getText();
                    String phone = SignUpPhone.getText();
                    String dob = dobField.getValue().toString();
                    User user = new User(firstname, lastname, gender, groupname, email, phone, dob);
                    dbHandler.signUpUser(user);
                    refreshTable();
                    clearFields();


                } else {
                    for (JFXTextField field : fields) {
                        if (field.getText().equals("")) {
                            Shake userAnimation = new Shake(field);
                            userAnimation.playAnim();

                        }
                    }
                }
            });
    }


    private void clearFields() {
        SignUpName.clear();
        SignUpLastName.clear();
        SignUpEmail.clear();
        SignUpPhone.clear();
        dobField.setValue(null);
        groupName_field.clear();
        genderMale.setSelected(false);
        genderFemale.setSelected(false);
    }


    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    public void refreshTable() {
        observableList.clear();
        try {
            Connection connection = databaseHandler.getDbConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM " + Const.USER_TABLE);

            while (resultSet.next()) {
                observableList.add(new UserDetails(resultSet.getString("idusers"), resultSet.getString("firstname"), resultSet.getString("lastname"), resultSet.getString("gender"), resultSet.getString("groupname"), resultSet.getString("Email"), resultSet.getString("phone"), resultSet.getString("dob")));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        firstnameCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastnameCol.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        dateofBirthCol.setCellValueFactory(new PropertyValueFactory<>("dob"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        groupnameCol.setCellValueFactory(new PropertyValueFactory<>("groupname"));

        table.setItems(observableList);

    }


    public void loadTable(){
        loadTableBtn.setOnAction(event -> {
            refreshTable();
        });
    }


    public void deleteUser() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        deleteButton.setOnAction(event -> {
            UserDetails user = table.getSelectionModel().getSelectedItem();
            if (user != null) {
                try {
                    String query = "DELETE FROM " + Const.USER_TABLE + " WHERE " + Const.USERS_ID + " =?";
                    PreparedStatement prSt = null;
                    prSt = databaseHandler.getDbConnection().prepareStatement(query);
                    prSt.setString(1, user.getId());
                    prSt.executeUpdate();

                    prSt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                clearFields();
                refreshTable();
            }

        });


    }

    public boolean validateFirstName() {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(SignUpName.getText());
        if (m.find() && m.group().equals(SignUpName.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate First Name");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid First Name");
            alert.showAndWait();

            return false;
        }
    }

    public boolean validateLastName() {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(SignUpLastName.getText());
        if (m.find() && m.group().equals(SignUpLastName.getText())) {
            return true;
        } else {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Validate Last Name");
            alert2.setHeaderText(null);
            alert2.setContentText("Please Enter Valid Last Name");
            alert2.showAndWait();

            return false;
        }
    }



}
