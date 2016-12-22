/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication7;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormatSymbols;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import javax.swing.JFileChooser;
import static javax.swing.JFileChooser.DIRECTORIES_ONLY;
import javax.swing.JFrame;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/*
 * @author Maks_
 */
public class Main_controller extends JFrame{
    //////////////////////////ГРафик////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private BarChart<String, Double> chart;
 
    @FXML
    private CategoryAxis xAxis;
    
    @FXML
    private Button reload_chart;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final ObservableList<User> usersData = FXCollections.observableArrayList();
    private final ObservableList<Stud_mark_table> tableDate_Predmet_mark = FXCollections.observableArrayList();
    private final ObservableList<Telefone> tableDate_Telefone = FXCollections.observableArrayList();
    private final ObservableList<Predmet_prepod> tableDate_Predmet_prepod = FXCollections.observableArrayList();
    /////////////////////////////////////////////stud///////////////////////////////////////////////////////////////////////
    private static final ObservableList<table_stud> table_stud_list = FXCollections.observableArrayList();
    /////////////////////////////////////////////Grups//////////////////////////////////////////////////////////////////////
    private static final ObservableList<table_grups> table_grups_list = FXCollections.observableArrayList();
    ////////////////////////////////////////////Marks///////////////////////////////////////////////////////////////////////
    private static final ObservableList<table_marks> table_marks_list = FXCollections.observableArrayList();
    //////////////////////////////////////////predmet///////////////////////////////////////////////////////////////////////
    private static final ObservableList<table_predmet> table_predmet_list = FXCollections.observableArrayList();
      //////////////////////////////////////////prepod///////////////////////////////////////////////////////////////////////
    private static final ObservableList<table_prepod> table_prepod_list = FXCollections.observableArrayList();
    /////////////////////////////////////Таблица преподователи//////////////////////////////////////////////////////////////
    @FXML
    public TableView<table_prepod> table_prepod_select;
 
    @FXML
    private TableColumn<table_prepod, Integer> prepod_idprepod;
 
    @FXML
    private TableColumn<table_prepod, String> prepod_fio;
    
    @FXML
    public Button prepod_add_record;
    @FXML
    public Button prepod_delete_record;
    @FXML
    public Button prepod_change_record;
    @FXML
    public Button prepod_change_record_confirm;
    
    public TextField prepod_textfield_idprepod;
    public TextField prepod_textfield_prepod_fio;
    
    /////////////////////////////////////Таблица предмет///////////////////////////////////////////////////////////////////
    @FXML
    public TableView<table_predmet> table_predmet_select;
 
    @FXML
    private TableColumn<table_predmet, Integer> predmet_idpredmet;
 
    @FXML
    private TableColumn<table_predmet, String> predmet_name;
    
    @FXML
    public Button predmet_add_record;
    @FXML
    public Button predmet_delete_record;
    @FXML
    public Button predmet_change_record;
    @FXML
    public Button predmet_change_record_confirm;
    
    public TextField predmet_textfield_idpredmet;
    public TextField predmet_textfield_predmet_name;
    
    /////////////////////////////////////Таблица оценки////////////////////////////////////////////////////////////////////
    @FXML
    public TableView<table_marks> table_marks_select;
 
    @FXML
    private TableColumn<table_marks, Integer> marks_nzach;
 
    @FXML
    private TableColumn<table_marks, Integer> marks_idprepod;
    
    @FXML
    private TableColumn<table_marks, Integer> marks_idpredmet;
    
    @FXML
    private TableColumn<table_marks, Integer> marks_mark;
    
    @FXML
    private TableColumn<table_marks, String> marks_data;
    
    @FXML
    public Button marks_add_record;
    @FXML
    public Button marks_delete_record;
    @FXML
    public Button marks_change_record;
    @FXML
    public Button marks_change_record_confirm;
    
    public TextField marks_textfield_nzach;
    public TextField marks_textfield_idprepod;
    public TextField marks_textfield_idpredmet;
    public TextField marks_textfield_mark;
    public TextField marks_textfield_data;
    
    /////////////////////////////////////Таблица группы ///////////////////////////////////////////////////////////////////
    @FXML
    public TableView<table_grups> table_grups_select;
 
    @FXML
    private TableColumn<table_grups, String> grups_name;
 
    @FXML
    private TableColumn<table_grups, Integer> grups_idgr;
    
    @FXML
    public Button delete_record_table_grups;
    @FXML
    public Button change_table_grups;
    @FXML
    public Button change_table_grups_confirm;
    @FXML
    public Button add_table_grups;
    
    public TextField textfield_idgr_table_grups;
    public TextField textfield_grups_name_table_grups;
    ////////////////////////////////////Таблица студенты добавление/////////////////////////////////////////////////////
    @FXML
    public TableView<table_stud> table_stud_select;
 
    @FXML
    private TableColumn<table_stud, String> fio;
 
    @FXML
    private TableColumn<table_stud, Integer> idgr;
    
    @FXML
    private TableColumn<table_stud, Integer> telefone;
    
    @FXML
    private TableColumn<table_stud, String> birthday;
    
    @FXML
    private TableColumn<table_stud, Integer> nzach;
    
    ////////////////////////////////////Удаление таблица stud///////////////////////////////////////////////////////////////////////
    @FXML
    public Button delete_record;
    ///////////////////////////////////Изменение таблица stud///////////////////////////////////////////////////////////////////////
    @FXML
    public Button change_record;
    @FXML
    public Button change_record_confirm;
    ////////////////////////////////////Добавление в 1-ю таблицу/////////////////////////////////////////////////////////    
    @FXML
    public Button button_add_1;
    public TextField textfield_fio_1;
    public TextField textfield_grups_1;
    public TextField textfield_birthday_1;
    public TextField textfield_tel_1;
    public TextField textfield_nzach_1;
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////Загрузка базы////////////////////////////////////////////////////////////
     @FXML
    public Button Launch_database;
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
     public AnchorPane test;
    ///////////////////////////////////////////////////////
    @FXML
    private TableView<User> tableUsers;
 
    @FXML
    private TableColumn<User, String> fioColumn;
 
    @FXML
    private TableColumn<User, String> grupsColumn;
 /////////////////////////////////////////////////////////
    ////////////////2-я таблица////////////////////////////
    @FXML
    private TableView<Stud_mark_table> tablePrepodMark;
 
    @FXML
    private TableColumn<Stud_mark_table, String> fio_studColumn;
 
    @FXML
    private TableColumn<Stud_mark_table, Integer> markColumn;
    
    @FXML
    private TableColumn<Stud_mark_table, String> predmetColumn;
 
    @FXML
    private TableColumn<Stud_mark_table, String> teacherColumn;
    /////////////////////////////////////////////////////////
    ////////////////3-я таблица////////////////////////////
    @FXML
    private TableView<Telefone> tableTelefone;
 
    @FXML
    private TableColumn<Telefone, String> grups_nameColumn;
 
    @FXML
    private TableColumn<Telefone, Integer> telefoneColumn;
    
    @FXML
    private TableColumn<Telefone, String> fio_stud_foneColumn;
    /////////////////////////////////////////////////////////
    ////////////////4-я таблица////////////////////////////
    @FXML
    private TableView<Predmet_prepod> tablePredmet_prepod;
 
    @FXML
    private TableColumn<Predmet_prepod, String> predmet_nameColumn;
 
    @FXML
    private TableColumn<Predmet_prepod, String> prepod_nameColumn;
    /////////////////////////////////////////////////////////
    
    ////////////////////////Експорт/////////////////////////////
    @FXML
    private Button Export;
    //Подключение  базе 
 public static Connection getDBConnection( boolean flag ) {
      
     if(flag == true){
     //Очистка списков 
table_stud_list.clear();
table_grups_list.clear();
table_marks_list.clear();
table_predmet_list.clear();
table_prepod_list.clear();
     }
    Connection dbConnection = null;
    try {
        Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        System.out.println(e.getMessage());
    }
    try {
        dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3307/stud?zeroDateTimeBehavior=convertToNull", "root","");
        return dbConnection;
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return dbConnection;
     }
   
    // инициализируем форму данными
    @FXML
    private void initialize() throws IOException {
        
 ///.................................Таблицца 1 .......................................
        // устанавливаем тип и значение которое должно хранится в колонке     
        fioColumn.setCellValueFactory(new PropertyValueFactory<>("fio"));
        grupsColumn.setCellValueFactory(new PropertyValueFactory<>("grups_name"));
        
         // заполняем таблицу данными
        tableUsers.setItems(usersData);
        
////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////Таблица 2 ///////////////////////////////////////////////
// устанавливаем тип и значение которое должно хранится в колонке     
        fio_studColumn.setCellValueFactory(new PropertyValueFactory<Stud_mark_table, String>("fio"));
        markColumn.setCellValueFactory(new PropertyValueFactory<>("mark"));
        teacherColumn.setCellValueFactory(new PropertyValueFactory<Stud_mark_table, String>("Teacher"));
        predmetColumn.setCellValueFactory(new PropertyValueFactory<Stud_mark_table, String>("predmet"));
    
         // заполняем таблицу данными
        tablePrepodMark.setItems(tableDate_Predmet_mark);
    
 //////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////Таблица 3 ///////////////////////////////////////////////
// устанавливаем тип и значение которое должно хранится в колонке     
        grups_nameColumn.setCellValueFactory(new PropertyValueFactory<>("grups_name"));
        telefoneColumn.setCellValueFactory(new PropertyValueFactory<>("tel"));
        fio_stud_foneColumn.setCellValueFactory(new PropertyValueFactory<>("fio"));
         // заполняем таблицу данными
        tableTelefone.setItems(tableDate_Telefone);
    
 //////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////Таблица 4 ///////////////////////////////////////////////
// устанавливаем тип и значение которое должно хранится в колонке     
        predmet_nameColumn.setCellValueFactory(new PropertyValueFactory<>("Predmet_name"));
        prepod_nameColumn.setCellValueFactory(new PropertyValueFactory<>("Prepod_name"));
  
         // заполняем таблицу данными
        tablePredmet_prepod.setItems(tableDate_Predmet_prepod);
        
  /////////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////////////////////////
  ///////////////////////////////Таблица студенты/////////////////////////////////////////////////////////
    table_stud_add_list();
   //////////////////////////Add record table stud/////////////////////////////////////////////// 
   
    table_stud_add();
    //////////////////////////////////Удаление записи таблица stud//////////////////////////////////////////
    table_stud_delete();
    ////////////////Изменение записей в таблице stud///////////////////////////////////////////
    table_stud_change();
  
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////////////////////////
  ///////////////////////////////Таблица marks/////////////////////////////////////////////////////////
    table_marks_add_list();
    /////////////////////////////добавление записи///////////////////////////////////////////////////////////
    table_marks_add();
    //////////////////////////////Изменение записи///////////////////////////////////////////////////////////
    table_marks_change();
    ////////////////////////Удаление записи///////////////////////////////////////////////////////////////
    table_marks_delete();
    
  /////////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////////////////////////
  ///////////////////////////////Таблица grups/////////////////////////////////////////////////////////
    table_grups_add_list();
   //////////////////////////Add record table grups/////////////////////////////////////////////// 
    table_grups_add();
    //////////////////////////////////Удаление записи таблица grups//////////////////////////////////////////
    table_grups_delete();
    ////////////////Изменение записей в таблице grups///////////////////////////////////////////
    table_grups_change();
    
     /////////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////////////////////////
  ///////////////////////////////Таблица предмет/////////////////////////////////////////////////////////
    table_predmet_add_list();
    ////////////////////////////////Add record //////////////////////////////////////////////////////////////
    table_predmet_add();
    ////////////////////////////////изменение записей в таблице//////////////////////////////////////////////
     table_predmet_change();
     ////////////////////////////////Удаление записи/////////////////////////////////////////////////////////
     table_predmet_delete();
   /////////////////////////////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////Отрисовка графика//////////////////////////////////////////////////
        reload_chart.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
                       draw_chart();
                       initData();
        }});
      
  /////////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////////////////////////
  ///////////////////////////////Таблица преподователь/////////////////////////////////////////////////////////
    table_prepod_add_list();
    table_prepod_add();
    table_prepod_change();
    table_prepod_delete();
    //////////////////////////////Загрузка базы////////////////////////////////////////////////
    Launch_database.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
                    initData();
        }});
    //////////////////////////////Экспорт в эксель/////////////////////////////////////////////
        Export.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            try {
                exportExcel();
            } catch (IOException ex) {
                Logger.getLogger(Main_controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }});
        
    }
 ///////   
    

  //вывод данных в какой группе какой студент
    private void initData() {                
    Connection dbConnection = null;
    Statement statement = null;
    
    String selectTable_grups_fio = "SELECT grups.name,fio from  stud natural join grups";//Запрос к 1-й вкладке 
    String selectTable_predmet_fio_mark_fioTeacher = "Select prepod.fio as prep,predmet.name,stud.fio,marks.mark FROM marks natural join prepod inner join predmet using(idpredmet) inner join stud using(nzach);";
    String selectTable_telefone = "SELECT stud.fio,grups.name,tel from stud natural join grups ";
    String selectTable_predmet_prepod = "SELECT PREDMET.NAME,PREPOD.FIO FROM PREDMET NATURAL JOIN MARKS INNER JOIN PREPOD USING(IDPREPOD)";
try {
    dbConnection = getDBConnection(true);
    statement = dbConnection.createStatement();
 
    // выбираем данные с БД
    ResultSet rs_1 = statement.executeQuery(selectTable_grups_fio);
 //Заполнение в 1-ю таблицу   
    while (rs_1.next()) {
        
        String grups_name = rs_1.getString("grups.name"); 
        String fio_stud = rs_1.getString("fio"); 
        usersData.add(new User(grups_name,fio_stud));
    }
    
    
  //Заполнение в 2-ю таблицу 
  // выбираем данные с БД
    ResultSet rs_2 = statement.executeQuery(selectTable_predmet_fio_mark_fioTeacher);
 
    // И если что то было получено то цикл while сработает   
    while (rs_2.next()) {
        
        String prepod_fio = rs_2.getString("prep"); 
        String fio_stud = rs_2.getString("stud.fio"); 
        String predmet_name = rs_2.getString("predmet.name");
        int marks = rs_2.getInt("marks.mark"); 
        tableDate_Predmet_mark.add(new Stud_mark_table(fio_stud,marks,predmet_name,prepod_fio));
    }
    
    
    //Заполнение в 3-ю таблицу 
  // выбираем данные с БД
    ResultSet rs_3 = statement.executeQuery(selectTable_telefone);
 
    // И если что то было получено то цикл while сработает   
    while (rs_3.next()) {
        
        String stud_fio = rs_3.getString("stud.fio"); 
        String grups_name = rs_3.getString("grups.name"); 
        int telefone = rs_3.getInt("tel");
        tableDate_Telefone.add(new Telefone(stud_fio,grups_name,telefone));
    }
    
  //Заполнение в 4-ю таблицу 
  // выбираем данные с БД
    ResultSet rs_4 = statement.executeQuery(selectTable_predmet_prepod);
 
    // И если что то было получено то цикл while сработает   
    int i=0;
    while (rs_4.next()) {
        String predmet_name = rs_4.getString("PREDMET.NAME"); 
        String prepod_fio = rs_4.getString("prepod.fio"); 
        tableDate_Predmet_prepod.add(new Predmet_prepod(prepod_fio,predmet_name));
    }
    
    
    
    ///////////////////////////////////Добавление данных в таблицу студенты////////////////////////////////////////////////////////////////
     table_stud_add_select_base(statement);
     ///////////////////////////////////Добавление данных в таблицу группы////////////////////////////////////////////////////////////////
     table_grups_add_select_base(statement);
     ///////////////////////////////////Добавление данных в таблицу оценки////////////////////////////////////////////////////////////////
     table_marks_add_select_base(statement);
     //////////////////////////////////Добавление данных в таблицу предмет////////////////////////////////////////////////////////////////
     table_predmet_add_select_base(statement);
     //////////////////////////////////Добавление данных в таблицу преподователи////////////////////////////////////////////////////////////////
     table_prepod_add_select_base(statement);
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
} catch (SQLException e) {
    System.out.println(e.getMessage());
}

    }
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////Экспорт данных в Exel///////////////////////////////////////////////////////////////////////////////
    // метод экспорта таблицы в Excel
private void exportExcel() throws FileNotFoundException, IOException   {
        setBounds(0,0,500,500);
        JFileChooser dialog = new JFileChooser();
        dialog.setFileSelectionMode(DIRECTORIES_ONLY);
        dialog.showOpenDialog(this);
        File file =dialog.getSelectedFile();
        setVisible(true);
        
        System.out.println(file);
        
        if(file != null){
            setVisible(false);
    Workbook wb = new HSSFWorkbook();
    //Workbook wb = new XSSFWorkbook();
    CreationHelper createHelper = wb.getCreationHelper();
    Sheet sheet = wb.createSheet("new sheet");

    // Create a row and put some cells in it. Rows are 0 based.
    for(int i=0;i<usersData.size();i++){
    Row row = sheet.createRow((short)i);
      // Or do it on one line. 
    row.createCell(0).setCellValue(
         createHelper.createRichTextString(String.valueOf("P-41")));
    row.createCell(1).setCellValue(true);
    }

    // Write the output to a file
    FileOutputStream fileOut = new FileOutputStream(file+"/Export.xls");
    wb.write(fileOut);
    fileOut.close();
        }
	}
    
    
    
   
    
    ////////////////////////////////////Рисовка графика///////////////////////////////////////////////////////////////////////////////////
   private ObservableList grupsNames = FXCollections.observableArrayList();//Имена групп
   private ObservableList excellence = FXCollections.observableArrayList();//Количество людей по группам  
   
   public  void draw_chart(){  
       grupsNames.clear();
       excellence.clear();
       chart.getData().clear();
       
       String selectTable_grups = "SELECT grups.name from grups;";//Запрос на получение имен групп
       String[] grups = new String[10];
       try {
           Connection dbConnection = getDBConnection(true);
           Statement statement = dbConnection.createStatement();
 
    // выбираем данные с БД
    ResultSet rs_1 = statement.executeQuery(selectTable_grups);
    int i=0;
 //Получение имен групп  
    while (rs_1.next()) {       
       String grups_name = rs_1.getString("grups.name"); 
         grups[i]=grups_name;
          System.out.println(grups[i]);
          grupsNames.add(grups[i]);
        i++;  
    }
    
    //Получение кол отличников по группам 
    for(i=0;i<grupsNames.size();i++){
    String select_excellence = "Select count(stud.fio) from grups natural join stud where grups.name like '%"+grupsNames.get(i)+"%' GROUP BY grups.name  ";
    ResultSet rs_count = statement.executeQuery(select_excellence);
    while (rs_count.next()) {       
       int count_excellence = rs_count.getInt("count(stud.fio)"); 
           excellence.add(count_excellence);       
    }      
    }
    
    for(i=0;i<excellence.size();i++){System.out.println(excellence.get(1)); }
    String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
   // Назначаем имена групп категориями для горизонтальной оси.
         xAxis.setCategories(grupsNames);
        
         XYChart.Series series = new XYChart.Series<>();
       for (i = 0; i < grupsNames.size(); i++) {
            series.getData().add(new XYChart.Data<>(grupsNames.get(i),excellence.get(i)));
        }
       chart.getData().add(series);
    }catch (SQLException e) {
    System.out.println(e.getMessage());
}    
   }
    
    
    
    
     /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////Методы по изменению таблицы prepod//////////////////////////////////////////////////////////////////////////////////
    public void table_prepod_add(){
        prepod_add_record.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if(prepod_textfield_idprepod.getText()!=null && prepod_textfield_prepod_fio.getText()!=null)
            {
              
                try {
                    Connection dbConnection = getDBConnection(true);
                    Statement statement = dbConnection.createStatement();
    
                    String prepod_fio = prepod_textfield_prepod_fio.getText();
                    int idprepod = Integer.parseInt(prepod_textfield_idprepod.getText());
                                   
                        String selectTable_add_stud_fio = "Insert into prepod(idprepod,fio) values("+idprepod+",'"+prepod_fio+"');";
                        statement.executeUpdate(selectTable_add_stud_fio);                                      
                } catch (SQLException ex) {
                    Logger.getLogger(Main_controller.class.getName()).log(Level.SEVERE, null, ex);                
                }
                
            }
        }
        });
    }
    public void table_prepod_change(){
     prepod_change_record.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            TablePosition pos = table_prepod_select.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            TableColumn col = pos.getTableColumn();

                // this gives the value in the selected cell:
            int idprepod = (int) col.getCellObservableValue(table_prepod_select.getItems().get(row)).getValue();
            
                Connection dbConnection = getDBConnection(false);
                try {
                    Statement statement = dbConnection.createStatement();
                    String selectTable_stud = "SELECT idprepod,fio from prepod where idprepod="+idprepod+";";//Запрос к 2-й вкладке на проверку наличия группы
                    
                    ResultSet rs_stud_change = statement.executeQuery(selectTable_stud);
                   
                   while (rs_stud_change.next()) {
                       prepod_textfield_idprepod.setText(rs_stud_change.getString("idprepod"));
                       prepod_textfield_prepod_fio.setText(rs_stud_change.getString("fio"));
                    }                                            
                } catch (SQLException ex) {
                    Logger.getLogger(Main_controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
    });
    /////////////////////////////Потверждение изменения////////////////////////////////////////
    ////////////////Изменение записей в таблице stud///////////////////////////////////////////
    prepod_change_record_confirm.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Connection dbConnection = getDBConnection(false);
            Statement statement;
            try {
                statement = dbConnection.createStatement();                  
                //////////////Запрос на изменение//////////////////////////////////////////////////////////////////////
                 String selectTable_change = "UPDATE prepod SET idprepod="+Integer.parseInt(prepod_textfield_idprepod.getText())+",fio='"+prepod_textfield_prepod_fio.getText()+"' WHERE idprepod="+Integer.parseInt(prepod_textfield_idprepod.getText())+";";
                 statement.executeUpdate(selectTable_change);
            } catch (SQLException ex) {
                Logger.getLogger(Main_controller.class.getName()).log(Level.SEVERE, null, ex);
            }                            
        }
    });
    }
    
 public void table_prepod_delete(){
        prepod_delete_record.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            
            TablePosition pos = table_prepod_select.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            TableColumn col = pos.getTableColumn();

                // this gives the value in the selected cell:
            int idprepod = (int) col.getCellObservableValue(table_prepod_select.getItems().get(row)).getValue();
            
            Connection dbConnection = getDBConnection(true);
            try {
                Statement statement = dbConnection.createStatement();
                String remove_table = "Delete from prepod where idprepod="+idprepod+";";
                statement.executeUpdate(remove_table);initData();
            } catch (SQLException ex) {
                Logger.getLogger(Main_controller.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        });
        }
        
    public void table_prepod_add_list(){
       prepod_idprepod.setCellValueFactory(new PropertyValueFactory<>("Idprepod"));
       prepod_fio.setCellValueFactory(new PropertyValueFactory<>("Prepod_fio"));
         // заполняем таблицу данными
       table_prepod_select.setItems(table_prepod_list);
    }
    
    public void table_prepod_add_select_base(Statement statement) throws SQLException{
    String tableSelect_marks = "SELECT idprepod,prepod.fio FROM prepod;";
    ResultSet rs_marks = statement.executeQuery(tableSelect_marks);
 
    // И если что то было получено то цикл while сработает   
    while (rs_marks.next()) {
        int idprepod = rs_marks.getInt("idprepod");
        String  prepod_fio = rs_marks.getString("prepod.fio");  
      
        table_prepod_list.add(new table_prepod(idprepod, prepod_fio));
    }
}
    
    
    
    
    
    
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////Методы по изменению таблицы predmet//////////////////////////////////////////////////////////////////////////////////
    public void table_predmet_delete(){
        predmet_delete_record.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            
            TablePosition pos = table_predmet_select.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            TableColumn col = pos.getTableColumn();

                // this gives the value in the selected cell:
            int idpredmet = (int) col.getCellObservableValue(table_predmet_select.getItems().get(row)).getValue();
            
            Connection dbConnection = getDBConnection(true);
            try {
                Statement statement = dbConnection.createStatement();
                String remove_table = "Delete from predmet where idpredmet="+idpredmet+";";
                statement.executeUpdate(remove_table);initData();
            } catch (SQLException ex) {
                Logger.getLogger(Main_controller.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        });
        }
    
    public void table_predmet_change(){
     predmet_change_record.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            TablePosition pos = table_predmet_select.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            TableColumn col = pos.getTableColumn();

                // this gives the value in the selected cell:
            int idpredmet = (int) col.getCellObservableValue(table_predmet_select.getItems().get(row)).getValue();
            
                Connection dbConnection = getDBConnection(true);
                try {
                    Statement statement = dbConnection.createStatement();
                    String selectTable_stud = "SELECT idpredmet,name from predmet where idpredmet="+idpredmet+";";//Запрос к 2-й вкладке на проверку наличия группы
                    
                    ResultSet rs_stud_change = statement.executeQuery(selectTable_stud);
                   
                   while (rs_stud_change.next()) {
                       predmet_textfield_idpredmet.setText(rs_stud_change.getString("idpredmet"));
                       predmet_textfield_predmet_name.setText(rs_stud_change.getString("name"));
                    }                                            
                } catch (SQLException ex) {
                    Logger.getLogger(Main_controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
    });
    /////////////////////////////Потверждение изменения////////////////////////////////////////
    ////////////////Изменение записей в таблице stud///////////////////////////////////////////
    predmet_change_record_confirm.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Connection dbConnection = getDBConnection(true);
            Statement statement;
            try {
                statement = dbConnection.createStatement();                  
                //////////////Запрос на изменение//////////////////////////////////////////////////////////////////////
                 String selectTable_change = "UPDATE predmet SET idpredmet="+Integer.parseInt(predmet_textfield_idpredmet.getText())+",name='"+predmet_textfield_predmet_name.getText()+"' WHERE idpredmet="+Integer.parseInt(predmet_textfield_idpredmet.getText())+";";
                 statement.executeUpdate(selectTable_change);
                 initData();
            } catch (SQLException ex) {
                Logger.getLogger(Main_controller.class.getName()).log(Level.SEVERE, null, ex);
            }                            
        }
    });
    }
    
    public void table_predmet_add(){
        predmet_add_record.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if(predmet_textfield_idpredmet.getText()!=null && predmet_textfield_predmet_name.getText()!=null)
            {
              
                try {
                    Connection dbConnection = getDBConnection(true);
                    Statement statement = dbConnection.createStatement();
    
                    String predmet_name = predmet_textfield_predmet_name.getText();
                    int idpredmet = Integer.parseInt(predmet_textfield_idpredmet.getText());
                                   
                        String selectTable_add_stud_fio = "Insert into predmet(idpredmet,name) values("+idpredmet+",'"+predmet_name+"');";
                        statement.executeUpdate(selectTable_add_stud_fio);                                      
                } catch (SQLException ex) {
                    Logger.getLogger(Main_controller.class.getName()).log(Level.SEVERE, null, ex);                
                }
                
            }
        }
        
    });
    }
    public void table_predmet_add_list(){
       predmet_idpredmet.setCellValueFactory(new PropertyValueFactory<>("Idpredmet"));
       predmet_name.setCellValueFactory(new PropertyValueFactory<>("Predmet_name"));
         // заполняем таблицу данными
       table_predmet_select.setItems(table_predmet_list);
    }
    
    
    public void table_predmet_add_select_base(Statement statement) throws SQLException{
    String tableSelect_marks = "SELECT idpredmet,name FROM predmet;";
    ResultSet rs_marks = statement.executeQuery(tableSelect_marks);
 
    // И если что то было получено то цикл while сработает   
    while (rs_marks.next()) {
        int predmet_idpredmet = rs_marks.getInt("idpredmet");
        String  predmet_name = rs_marks.getString("name");  
      
        table_predmet_list.add(new table_predmet(predmet_idpredmet, predmet_name));
    }
    }
    
    
    
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////Методы по изменению таблицы marks//////////////////////////////////////////////////////////////////////////////////
    public void table_marks_delete(){
        marks_delete_record.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            
            TablePosition pos = table_marks_select.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            TableColumn col = pos.getTableColumn();

                // this gives the value in the selected cell:
            int nzach = (int) col.getCellObservableValue(table_marks_select.getItems().get(row)).getValue();
            
            Connection dbConnection = getDBConnection(true);
            try {
                Statement statement = dbConnection.createStatement();
                String remove_table = "Delete from marks where nzach="+nzach+";";
                statement.executeUpdate(remove_table);initData();
            } catch (SQLException ex) {
                Logger.getLogger(Main_controller.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        });
        }
    
    public void table_marks_change(){
        marks_change_record.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            TablePosition pos = table_marks_select.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            TableColumn col = pos.getTableColumn();

                // this gives the value in the selected cell:
            int nzach = (int) col.getCellObservableValue(table_marks_select.getItems().get(row)).getValue();
            
                Connection dbConnection = getDBConnection(false);
                try {
                    Statement statement = dbConnection.createStatement();
                    String selectTable = "SELECT nzach,idpredmet,idprepod,mark,data from marks where nzach="+nzach+";";//Запрос к 2-й вкладке на проверку наличия группы
                    
                    ResultSet rs_stud_change = statement.executeQuery(selectTable);
                   
                   while (rs_stud_change.next()) {
                       marks_textfield_nzach.setText(rs_stud_change.getString("nzach"));
                       marks_textfield_idprepod.setText(rs_stud_change.getString("idprepod"));
                       marks_textfield_idpredmet.setText(rs_stud_change.getString("idpredmet"));
                       marks_textfield_mark.setText(rs_stud_change.getString("mark"));
                       marks_textfield_data.setText(rs_stud_change.getString("data"));
                    }                                            
                } catch (SQLException ex) {
                    Logger.getLogger(Main_controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
    });
    /////////////////////////////Потверждение изменения////////////////////////////////////////
    ////////////////Изменение записей в таблице stud///////////////////////////////////////////
    marks_change_record_confirm.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Connection dbConnection = getDBConnection(false);
            Statement statement;
            try {
                statement = dbConnection.createStatement();                  
                //////////////Запрос на изменение//////////////////////////////////////////////////////////////////////
                 String selectTable_change = "UPDATE marks SET nzach="+Integer.parseInt(marks_textfield_nzach.getText())+",idpredmet="+Integer.parseInt(marks_textfield_idpredmet.getText())+",idprepod="+Integer.parseInt(marks_textfield_idprepod.getText())+", mark="+Integer.parseInt(marks_textfield_mark.getText())+", data='"+marks_textfield_data.getText()+"' WHERE nzach="+Integer.parseInt(marks_textfield_nzach.getText())+";";
                 statement.executeUpdate(selectTable_change);
                 initData();
            } catch (SQLException ex) {
                Logger.getLogger(Main_controller.class.getName()).log(Level.SEVERE, null, ex);
            }                            
        }
    });
    
    }
    
    public void table_marks_add(){
        marks_add_record.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
           if( marks_textfield_mark.getText()!=null && marks_textfield_nzach.getText()!=null)
            {
              
               try {
                    Connection dbConnection = getDBConnection(true);
                    Statement statement = dbConnection.createStatement();
    
                    int nzach = Integer.parseInt(marks_textfield_nzach.getText());
                    int idprepod = Integer.parseInt(marks_textfield_idprepod.getText());
                    int idpredmet = Integer.parseInt(marks_textfield_idpredmet.getText());
                    int mark = Integer.parseInt(marks_textfield_mark.getText());   
                    String data = marks_textfield_data.getText(); 
                                   

                        String selectTable_add_stud_fio = "Insert into marks(nzach,idprepod,idpredmet,mark,data) values("+nzach+","+idprepod+","+idpredmet+","+mark+",'"+data+"');";
                        statement.executeUpdate(selectTable_add_stud_fio); 
                        initData();
                    
                    
                  
                } catch (SQLException ex) {
                    Logger.getLogger(Main_controller.class.getName()).log(Level.SEVERE, null, ex);                
                }         
            }
    }
    });
    }
    
    public void table_marks_add_list(){
       marks_nzach.setCellValueFactory(new PropertyValueFactory<>("Nzach"));
       marks_idprepod.setCellValueFactory(new PropertyValueFactory<>("Idprepod"));
       marks_idpredmet.setCellValueFactory(new PropertyValueFactory<>("Idpredmet"));
       marks_mark.setCellValueFactory(new PropertyValueFactory<>("Marks"));
       marks_data.setCellValueFactory(new PropertyValueFactory<>("Data"));
         // заполняем таблицу данными
       table_marks_select.setItems(table_marks_list);
    }
    
    public void table_marks_add_select_base(Statement statement) throws SQLException{
    String tableSelect_marks = "SELECT nzach,idprepod,idpredmet,mark,data FROM marks;";
    ResultSet rs_marks = statement.executeQuery(tableSelect_marks);
 
    // И если что то было получено то цикл while сработает   
    while (rs_marks.next()) {
        int marks_nzach = rs_marks.getInt("nzach");
        int marks_idprepod = rs_marks.getInt("idprepod");  
        int marks_idpredmet = rs_marks.getInt("idpredmet");  
        int marks_mark = rs_marks.getInt("mark");  
        String marks_data = rs_marks.getString("data");  
        table_marks_list.add(new table_marks(marks_nzach,marks_idprepod,marks_idpredmet,marks_mark,marks_data));
    }
    }
    
    
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////Методы по изменению таблицы группы//////////////////////////////////////////////////////////////////////////////////
    public void table_grups_change(){
        change_table_grups.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            TablePosition pos = table_grups_select.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            TableColumn col = pos.getTableColumn();

                // this gives the value in the selected cell:
            int idgr = (int) col.getCellObservableValue(table_grups_select.getItems().get(row)).getValue();
            
                Connection dbConnection = getDBConnection(false);
                try {
                    Statement statement = dbConnection.createStatement();
                    String selectTable_stud = "SELECT name,idgr from grups where idgr="+idgr+";";//Запрос к 2-й вкладке на проверку наличия группы
                    
                    ResultSet rs_stud_change = statement.executeQuery(selectTable_stud);
                   
                   while (rs_stud_change.next()) {
                       textfield_idgr_table_grups.setText(rs_stud_change.getString("idgr"));
                       textfield_grups_name_table_grups.setText(rs_stud_change.getString("name"));
                    }                                            
                } catch (SQLException ex) {
                    Logger.getLogger(Main_controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
    });
    /////////////////////////////Потверждение изменения////////////////////////////////////////
    ////////////////Изменение записей в таблице stud///////////////////////////////////////////
    change_table_grups_confirm.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Connection dbConnection = getDBConnection(false);
            Statement statement;
            try {
                statement = dbConnection.createStatement();                  
                //////////////Запрос на изменение//////////////////////////////////////////////////////////////////////
                 String selectTable_change = "UPDATE grups SET idgr="+Integer.parseInt(textfield_idgr_table_grups.getText())+",name='"+textfield_grups_name_table_grups.getText()+"' WHERE idgr="+Integer.parseInt(textfield_idgr_table_grups.getText())+";";
                 statement.executeUpdate(selectTable_change);
                 initData();
            } catch (SQLException ex) {
                Logger.getLogger(Main_controller.class.getName()).log(Level.SEVERE, null, ex);
            }                            
        }
    });
    
    }
    public void table_grups_delete(){
        delete_record_table_grups.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            
            TablePosition pos = table_grups_select.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            TableColumn col = pos.getTableColumn();

                // this gives the value in the selected cell:
            int idgr = (int) col.getCellObservableValue(table_grups_select.getItems().get(row)).getValue();
            
            Connection dbConnection = getDBConnection(true);
            try {
                Statement statement = dbConnection.createStatement();
                String remove_table = "Delete from grups where idgr="+idgr+";";//Запрос к 1-й вкладке на проверку наличия группы
                statement.executeUpdate(remove_table);initData();
            } catch (SQLException ex) {
                Logger.getLogger(Main_controller.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        });
        }

    public void table_grups_add(){
        add_table_grups.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if(textfield_idgr_table_grups.getText()!=null && textfield_grups_name_table_grups.getText()!=null)
            {
              
                try {
                    Connection dbConnection = getDBConnection(true);
                    Statement statement = dbConnection.createStatement();
    
                    String grups_name = textfield_grups_name_table_grups.getText();
                    int grups_idgr = Integer.parseInt(textfield_idgr_table_grups.getText());
                                   
                        String selectTable_add_stud_fio = "Insert into grups(idgr,name) values("+grups_idgr+",'"+grups_name+"');";
                        statement.executeUpdate(selectTable_add_stud_fio);                                      
                } catch (SQLException ex) {
                    Logger.getLogger(Main_controller.class.getName()).log(Level.SEVERE, null, ex);                
                }
                
            }
        }
        
    });
    }
    public void  table_grups_add_list(){
       grups_name.setCellValueFactory(new PropertyValueFactory<>("Grups_name"));
       grups_idgr.setCellValueFactory(new PropertyValueFactory<>("idgr"));
         // заполняем таблицу данными
        table_grups_select.setItems(table_grups_list);
   }
    protected void table_grups_add_select_base(Statement statement) throws SQLException{
    String tableSelect_stud = "SELECT grups.name,idgr FROM grups;";
    ResultSet rs_stud = statement.executeQuery(tableSelect_stud);
 
    // И если что то было получено то цикл while сработает   
    while (rs_stud.next()) {
        String grups_name = rs_stud.getString("grups.name"); 
        int idgr = rs_stud.getInt("idgr"); 
        table_grups_list.add(new table_grups(grups_name,idgr));
    }
    }
    
    
    
    
    
    
    
 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
 ///////////////////////////////МЕтоды по изменению таблицы студенты ////////////////////////////////////////////////////////////////////
    public void table_stud_add_list(){
        fio.setCellValueFactory(new PropertyValueFactory<>("Fio"));
        idgr.setCellValueFactory(new PropertyValueFactory<>("idgr"));
        telefone.setCellValueFactory(new PropertyValueFactory<>("Telefone"));
        birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        nzach.setCellValueFactory(new PropertyValueFactory<>("Nzach"));
         // заполняем таблицу данными
        table_stud_select.setItems(table_stud_list);
    }
    
    protected void table_stud_add_select_base(Statement statement) throws SQLException{
    String tableSelect_stud = "SELECT fio,idgr,tel,birthday,nzach FROM stud;";
    ResultSet rs_stud = statement.executeQuery(tableSelect_stud);
 
    // И если что то было получено то цикл while сработает   
    while (rs_stud.next()) {
        String fio = rs_stud.getString("fio"); 
        int idgr = rs_stud.getInt("idgr"); 
        int telefone = rs_stud.getInt("tel"); 
        String birthday = rs_stud.getString("birthday"); 
        int nzach = rs_stud.getInt("nzach"); 
        table_stud_list.add(new table_stud(fio,idgr,telefone,birthday,nzach));
    }
    }
    //Добавление записи
    protected void table_stud_add(){
        button_add_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if(textfield_fio_1.getText()!=null && textfield_grups_1.getText()!=null)
            {
              
                try {
                    Connection dbConnection = getDBConnection(true);
                    Statement statement = dbConnection.createStatement();
    
                    String fio = textfield_fio_1.getText();
                    String grups = textfield_grups_1.getText();
                    String birthday = textfield_birthday_1.getText();
                    int tel = Integer.parseInt(textfield_tel_1.getText());   
                    int nzach = Integer.parseInt(textfield_nzach_1.getText()); 
                 
                    String selectTable_ID = "SELECT idgr from grups where grups.name like '%"+grups+"%';";//Запрос к 1-й вкладке на проверку наличия группы
                    
                    ///Проверка есть ли такая группа и определяет Id//////////////////////////////////////////////////////////
                    ResultSet rs_1 = statement.executeQuery(selectTable_ID);
                    int grups_idgr_final=0;
                    
                    while (rs_1.next()) {
                        int grups_idgr=rs_1.getInt("idgr");
                        grups_idgr_final = grups_idgr;
                    }           
                   
                    System.out.println(grups_idgr_final+" ");
                   
                        
                    if(grups_idgr_final!=0){
                        String selectTable_add_stud_fio = "Insert into stud(idgr,nzach,fio,birthday,tel) values("+grups_idgr_final+",'"+nzach+"','"+fio+"','"+birthday+"',"+tel+");";
                        statement.executeUpdate(selectTable_add_stud_fio); 
                        initData();
                    }
                    
                  
                } catch (SQLException ex) {
                    Logger.getLogger(Main_controller.class.getName()).log(Level.SEVERE, null, ex);                
                }
                
            }
        }
        
    });
    }
    //Удаление изменений 
    protected void table_stud_delete(){
        delete_record.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            
            TablePosition pos = table_stud_select.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            TableColumn col = pos.getTableColumn();

                // this gives the value in the selected cell:
            String fio = (String) col.getCellObservableValue(table_stud_select.getItems().get(row)).getValue();
            
            Connection dbConnection = getDBConnection(true);
            try {
                Statement statement = dbConnection.createStatement();
                String remove_table = "Delete from stud where fio like '%"+fio+"%';";//Запрос к 1-й вкладке на проверку наличия группы
                statement.executeUpdate(remove_table);initData();
            } catch (SQLException ex) {
                Logger.getLogger(Main_controller.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        });
    }
    
    /////Принятие изменений 
    protected void table_stud_change(){
        change_record.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
                TablePosition pos = table_stud_select.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();

            TableColumn col = pos.getTableColumn();

                // this gives the value in the selected cell:
            String fio_stud = (String) col.getCellObservableValue(table_stud_select.getItems().get(row)).getValue();
            
                Connection dbConnection = getDBConnection(false);
                try {
                    Statement statement = dbConnection.createStatement();
                    String selectTable_stud = "SELECT fio,idgr,tel,birthday,nzach from stud where fio like '%"+fio_stud+"%';";//Запрос к 1-й вкладке на проверку наличия группы
                    
                    ResultSet rs_stud_change = statement.executeQuery(selectTable_stud);
                   int idgr = 0;
                   
                   while (rs_stud_change.next()) {
                       textfield_fio_1.setText(rs_stud_change.getString("fio"));
                       idgr=rs_stud_change.getInt("idgr");
                       textfield_tel_1.setText(rs_stud_change.getString("tel"));
                       textfield_birthday_1.setText(rs_stud_change.getString("birthday"));
                       textfield_nzach_1.setText(rs_stud_change.getString("nzach"));
                    }           
                    ////////////////////Преобразование введеной группы из ID в название группы///////////////////////////////////////////////
                    if(idgr!=0){
                    String selectTable_grup = "SELECT name from grups where idgr="+idgr+";";//Запрос к 1-й вкладке на проверку наличия группы                   
                    ResultSet rs_grups = statement.executeQuery(selectTable_grup);
                    while (rs_grups.next()) {
                    textfield_grups_1.setText(rs_grups.getString("name"));
                    }
                    }
                    
                    String fio = textfield_fio_1.getText();
                    String grups = textfield_grups_1.getText();
                    String birthday = textfield_birthday_1.getText();
                    int tel = Integer.parseInt(textfield_tel_1.getText());   
                    int nzach = Integer.parseInt(textfield_nzach_1.getText()); 
                                  
                } catch (SQLException ex) {
                    Logger.getLogger(Main_controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
    });
    /////////////////////////////Потверждение изменения////////////////////////////////////////
    ////////////////Изменение записей в таблице stud///////////////////////////////////////////
    change_record_confirm.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Connection dbConnection = getDBConnection(false);
            Statement statement;
            int id=0;
            try {
                statement = dbConnection.createStatement();    
                ///////////////////////Перевод в idgr имени группы/////////////////////////////////////////////////////
                 String selectTable_name_grup = "Select idgr from grups where grups.name like '%"+textfield_grups_1.getText()+"%'";
                 ResultSet rs_grups = statement.executeQuery(selectTable_name_grup);
                    while (rs_grups.next()) {
                    id = rs_grups.getInt("idgr");
                    }                   
                //////////////Запрос на изменение//////////////////////////////////////////////////////////////////////
                if(id!=0){
                 String selectTable_change = "UPDATE stud SET Nzach="+Integer.parseInt(textfield_nzach_1.getText())+",fio='"+textfield_fio_1.getText()+"',idgr="+id+",`birthday`='"+ textfield_birthday_1.getText()+"',tel="+Integer.parseInt(textfield_tel_1.getText())+" WHERE fio like '%"+textfield_fio_1.getText()+"%'";
                 statement.executeUpdate(selectTable_change);
                 initData();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Main_controller.class.getName()).log(Level.SEVERE, null, ex);
            }                            
        }
    });
    
    }
}