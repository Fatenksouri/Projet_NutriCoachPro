package offregestion.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.layout.AnchorPane;
import offregestion.StatistiqueType;

public class StatController implements Initializable {
    @FXML
      private StackedBarChart<String, Number> offreChart;
    @FXML
    private StackedBarChart<String, Number> reservationChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private AnchorPane stat;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Simulez des données pour les statistiques (à remplacer par vos propres données)
        StatistiqueType coachStats = new StatistiqueType("Coach", 50, 30);
        StatistiqueType nutritionnisteStats = new StatistiqueType("Nutritionniste", 40, 25);

        // Créez une série de données pour les offres
        XYChart.Series<String, Number> offreSeries = new XYChart.Series<>();
        offreSeries.setName("Offres");
        offreSeries.getData().add(new XYChart.Data<>(coachStats.getType(), coachStats.getOffres()));
        offreSeries.getData().add(new XYChart.Data<>(nutritionnisteStats.getType(), nutritionnisteStats.getOffres()));

        // Créez une série de données pour les réservations
        XYChart.Series<String, Number> reservationSeries = new XYChart.Series<>();
        reservationSeries.setName("Réservations");
        reservationSeries.getData().add(new XYChart.Data<>(coachStats.getType(), coachStats.getReservations()));
        reservationSeries.getData().add(new XYChart.Data<>(nutritionnisteStats.getType(), nutritionnisteStats.getReservations()));

        // Ajoutez les séries de données aux StackedBarCharts
       offreChart.getData().add(offreSeries);

        reservationChart.getData().addAll(reservationSeries);
    }
}
