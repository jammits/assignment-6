package src;


import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportService {

    private static String file1 = "model3.csv";
    private static String file2 = "modelS.csv";
    private static String file3 = "modelX.csv";
    private static Map<Integer,Integer> model3Stat;
    private static Map<Integer,Integer> modelSStat;
    private static Map<Integer,Integer> modelXStat;

    public ReportService() {
        yearlySale();
    }

    //printing of the yearly sales report
    public static void yearlySale() {
        FileService service = new FileService();
        List<TeslaCar> model3FromFile = service.teslaSales(file1);
        List<TeslaCar> modelsFromFile = service.teslaSales(file2);
        List<TeslaCar> modelxFromFile = service.teslaSales(file3);
        model3Stat = sortingByYear(model3FromFile);
        modelSStat = sortingByYear(modelsFromFile);
        modelXStat = sortingByYear(modelxFromFile);

        System.out.println("\nModel 3 Yearly Report\n----------------");
        model3Stat.entrySet()
                .stream()
                .forEach(year -> System.out.println(year.getKey() + "->" + year.getValue()));

        saleStats(model3FromFile);

        System.out.println("\nModel S Yearly Report\n----------------");
        modelSStat.entrySet()
                .stream()
                .forEach(year -> System.out.println(year.getKey() + "->" + year.getValue()));

        saleStats(modelsFromFile);

        System.out.println("\nModel X Yearly Report\n----------------");
        modelXStat.entrySet()
                .stream()
                .forEach(year -> System.out.println(year.getKey() + "->" + year.getValue()));

        saleStats(modelxFromFile);



    }

    private static Map<Integer,Integer> sortingByYear(List<TeslaCar> modelFile){

        //Sorting by year and creating list of sales in that year
        Map<Integer,List<Integer>> modelProcessing = modelFile.stream()
                .collect(Collectors.groupingBy(teslaCar -> teslaCar.getDate().getYear(),Collectors.mapping(TeslaCar::getSold,Collectors.toList())));

        //Summing list of sales per year
        Map<Integer,Integer> modelStat = modelProcessing.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, sales -> sales.getValue().stream().collect(Collectors.summingInt(Integer::intValue))));

        return modelStat;
    }

    //Manage the print of best and worst
    public static void saleStats(List<TeslaCar> modelFile) {

        Map<YearMonth,Integer> modelProcessing = modelFile.stream()
                .collect(Collectors.toMap(time -> time.getDate(), sold -> sold.getSold()));

        //Best month
        YearMonth bestMonth = Collections.max(modelProcessing.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println("\nThe best month for " + file1.substring(0,6) + " was: " + bestMonth.format(DateTimeFormatter.ofPattern("yyyy-MM")));

        //Worst Month
        YearMonth worstMonth = Collections.min(modelProcessing.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println("The worst month for "+ file1.substring(0,6) + " was: " + worstMonth.format(DateTimeFormatter.ofPattern("yyyy-MM")));

    }
}
