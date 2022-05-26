package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class SampleController implements Initializable{

	@FXML
	private TabPane MonPane;
	
	@FXML
    private Tab Accueil;

    @FXML
    private Tab LongeurTab;
    
    @FXML
    private Tab MasseTab;
   
    @FXML
    private Tab TempsTab;
	
	@FXML
    private Button HomeLong;
	
	@FXML
    private Button HomeMasse;
	
    @FXML
    private Button HomeTemps;
	

	@FXML
	void changer(ActionEvent e)
	{
		Button b = (Button)e.getSource();
		String a=b.getText(); 
		
		switch (a)
		{
		case "Longueur":
			MonPane.getSelectionModel().select(1);
			break;
		case "Masse":
			MonPane.getSelectionModel().select(2);
			break;
		case "Temps":
			MonPane.getSelectionModel().select(3);
			break;
		
		}
	}
	
	@FXML
	void quitter()
	{
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("SORTIE");
		alert.setContentText("Vous voulez fermer l'appplication?");
		Optional<ButtonType> result=alert.showAndWait();
		if(result.get()==ButtonType.OK)
			System.exit(0);
	}
	

    
    @FXML
    private TextField txtlong1;
    
    @FXML
    private TextField txtlong2;
    
    @FXML
    private ComboBox<String> cboLong1;
    
    @FXML
    private ComboBox<String> cboLong2;
    
    
    private ObservableList <String> listLongueur = FXCollections.observableArrayList("km", "dm", "m", "cm","mm", "mile", "yard", "foot", "inch");
    private ObservableList <String> listMasse = FXCollections.observableArrayList("kg", "g", "mg", "lbs", "ounce");
    private ObservableList <String> listTemps = FXCollections.observableArrayList("h", "min", "s", "ms", "jour", "semaine");

    
    private double []Longueur = {1.0,100,1000,100000,1000000, 0.621371, 1093.61, 3280.84, 39370.1};
    private double []Masse = {1.0,1000.0, 1000000.0, 2.205, 0.00595238};
    private double []Temps = {1.0,60.0, 3600.0, 3600000.00, 0.0416667, 0.00595238};

    
    public void initialize (URL arg0, ResourceBundle arg1)
	{
		cboLong1.setItems(listLongueur);
		cboLong2.setItems(listLongueur);
		
		cboMasse1.setItems(listMasse);
		cboMasse2.setItems(listMasse);
		
		cbotemps1.setItems(listTemps);
		cbotemps2.setItems(listTemps);
		
		
		cboLong1.getSelectionModel().selectFirst();
		cboLong2.getSelectionModel().selectFirst();
		
		cboMasse1.getSelectionModel().selectFirst();
		cboMasse2.getSelectionModel().selectFirst();
		
		cbotemps1.getSelectionModel().selectFirst();
		cbotemps2.getSelectionModel().selectFirst();
	}

    
    @FXML
    private void verifNum(KeyEvent e)  
    {
    TextField txt=(TextField)e.getSource();

    txt.textProperty().addListener((observable,oldValue,newValue)->

    {
    if(!newValue.matches("^-?[0-9](\\.[0-9]+)?$"))
    {
    txt.setText(newValue.replaceAll("[^\\d*\\.\\-]",""));
    }

    });
    }
   
    private double SetTaux(ComboBox a, double tbl[])
    {
    	int item = a.getSelectionModel().getSelectedIndex();
    	double val = tbl[item];
    	return val;
    }
    
    private void convert(ComboBox a , ComboBox b , TextField c , TextField d , double tbl[])
    {
    	double from = SetTaux(a,tbl);
    	double depart = 0;
    	if(c.getText().equals(" "))
    		depart = 0;
    	else
    		depart = Double.parseDouble(c.getText());
    	double to = SetTaux(b,tbl);
    	double dest = (to/from)*depart;
    	d.setText(String.valueOf(dest));
    	
    	
    }
    
    @FXML
    private TextField txtmasse1;
    
    @FXML
    private TextField txtmasse2;

    @FXML
    private ComboBox<String> cboMasse1;

    @FXML
    private ComboBox<String> cboMasse2;
    
    @FXML
    private void ConvertLong1() 
    {
    
    	convert(cboLong1, cboLong2, txtlong1, txtlong2, Longueur);
    }
    @FXML
    private void ConvertLong2() 
    {
    	convert(cboLong2, cboLong1, txtlong2, txtlong1, Longueur);
    }
	
	@FXML	
    private void ConvertMasse1() 
    {
    
    	convert(cboMasse1, cboMasse2, txtmasse1, txtmasse2, Masse);
    }
    @FXML
    private void ConvertMasse2() 
    {
    
    	convert(cboMasse2, cboMasse1, txtmasse2, txtmasse1, Masse);
    }
    @FXML
    private void ConvertTemps1() 
    {
    
    	convert(cbotemps1, cbotemps2, txttemps1, txttemps2, Temps);
    }
    @FXML
    private void ConvertTemps2() 
    {
    
    	convert(cbotemps2, cbotemps1, txttemps2, txttemps1, Temps);
    }



    @FXML
    private ComboBox<String> cbotemps1;

    @FXML
    private ComboBox<String> cbotemps2;

    
    @FXML
    private TextField txttemps1;

    @FXML
    private TextField txttemps2;
    
    
   
}







