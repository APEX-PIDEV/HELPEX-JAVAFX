package GUI.gui_Tasks;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import utils.ConnexionJDBC;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Chart implements Initializable {
    @FXML
    private BarChart<?, ?> barChart;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chart();


    }

    public void chart () {
        String sql = "SELECT item.is_complete , COUNT(item.titre) FROM `item` GROUP by item.is_complete";
        XYChart.Series chartData = new XYChart.Series();
        try {
            PreparedStatement preparedStatement = ConnexionJDBC.getInstance().getCnx().prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
chartData.getData().add(new  XYChart.Data<>(result.getString(1), result.getInt(2)));



            }
            barChart.getData().add(chartData);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }
