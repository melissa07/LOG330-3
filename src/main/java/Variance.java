import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Variance {
    private final String fileName= "donnees.csv";
    private final String fileNameCorrelation = "donnees_correlation.csv";
    private File file = null;
    public static ArrayList<Double> listNumbers = null;
    public static ArrayList<Double> corrCol1 = null;
    public static ArrayList<Double> corrCol2 = null;

    public Variance() {
        listNumbers = readFile();
        double mean = calcAverage();
        System.out.println("Average: " +mean);
        double variance = calcVariance(mean);
        System.out.println("Variance: " +variance);
        double sd = calcStandardDev(variance);
        System.out.println("Standard deviation: " +sd);

        readCorrFile();
        float corr = calcCorrelation();
        String corrInterpreation = interpreterCorr(corr);

        System.out.println("Correlation interpretation: " +corrInterpreation);

    }

    /**
     * Fonction qui calcule l'ecart type des donnees provenant du fichier excel
     * @param variance Variance de la liste des donnees provenant du fichier excel
     * @return L'ecart type
     */
    public double calcStandardDev(double variance) {
//        if(Double.isNaN(Math.sqrt(variance))) {
//            return -1;
//        }
        return Math.sqrt(variance);
    }

    /**
     * Fonction qui calcule la variance des donnees provenant du fichier excel
     * @param mean Moyenne de la liste des donnees provenant du fichier excel
     * @return La variance
     */
    public double calcVariance(double mean) {
        int sumVar = 0, var = 0;
        for (double number: listNumbers) {
            if(number > Double.MAX_VALUE || number < Double.MIN_VALUE ) {
                return Double.NaN;
            }
            sumVar += (number-mean)*(number-mean);
        }
        return sumVar/(listNumbers.size()-1);
    }

    /**
     * Fonction qui calcule la moyenne des donnees provenant du fichier excel
     * @return La moyenne
     */
    public double calcAverage() {
        int total = 0;
        for (double number : listNumbers) {
            if(number > Double.MAX_VALUE || number < Double.MIN_VALUE ) {
                return Double.NaN;
            }
            total += number;
        }
        int mean = total/listNumbers.size();

        return mean;
    }

    /**
     * Lecture du fichier excel
     * @return Liste des nombres provanant du fichier excel
     */
    public ArrayList<Double> readFile() throws NumberFormatException  {
        file= new File(fileName);

        ArrayList<Double> lines = new ArrayList<Double>();
        Scanner inputStream;

        try{
            inputStream = new Scanner(file);

            while(inputStream.hasNext()){
                String line= inputStream.next();
                lines.add(Double.parseDouble(line));
            }

            inputStream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(NumberFormatException nf) {
            nf.printStackTrace();
        }
        return lines;
    }

    /**
     * Lecture du fichier contenant les donnees pour le calcul
     * de la correlation
     */
    public void readCorrFile() {
        file = null;
        corrCol1 = new ArrayList<>();
        corrCol2 = new ArrayList<>();
        file = new File(fileNameCorrelation);

        ArrayList<Double> lines = new ArrayList<Double>();
        Scanner inputStream;

        try{
            inputStream = new Scanner(file);

            while(inputStream.hasNext()){
                String line= inputStream.next();
                String[] corrDatas = line.split(";");
                corrCol1.add(Double.parseDouble(corrDatas[0].replaceAll(",", ".")));
                corrCol2.add(Double.parseDouble(corrDatas[1].replaceAll(",", ".")));
            }

            inputStream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(NumberFormatException nf) {
            nf.printStackTrace();
        }
    }

    /**
     * Calcule de la correlation avec les donnees provenant du fichier
     * csv lu precedemment
     * @return Correlation
     */
    public float calcCorrelation() {
        double somme_X = 0, somme_Y = 0, somme_XY = 0, squareSum_X = 0, squareSum_Y = 0;
        float corr = -1;

        for (int i = 0; i < corrCol1.size(); i++)
        {
            somme_X = somme_X + corrCol1.get(i);
            somme_Y = somme_Y + corrCol2.get(i);
            somme_XY = somme_XY + corrCol1.get(i) * corrCol2.get(i);

            squareSum_X = squareSum_X + corrCol1.get(i) * corrCol1.get(i);
            squareSum_Y = squareSum_Y + corrCol2.get(i) * corrCol2.get(i);
        }

        corr = (float)(corrCol1.size() * somme_XY - somme_X * somme_Y)/
                (float)(Math.sqrt((corrCol1.size() * squareSum_X -
                        somme_X * somme_X) * (corrCol1.size() * squareSum_Y -
                        somme_Y * somme_Y)));

        System.out.println("Correlation: " +corr);

        return corr;
    }

    /**
     *  Interprete la correlation
     * @param corr Correlation des nombres
     * @return L'interpretation de la correlation
     */
    public String interpreterCorr(double corr) {
        String textCorr = "Correlation ";
        if(corr < 0.2) {
            return textCorr + "nulle a faible";
        }
        else if (corr >= 0.2 && corr < 0.4) {
            return textCorr + "faible a moyenne";
        }
        else if(corr >= 0.4 && corr < 0.7) {
            return textCorr + "moyenne a forte";
        }
        else if(corr >= 0.7 && corr < 0.9) {
            return textCorr + "forte a tres forte";
        }
        else if(corr >= 0.9 && corr <= 1) {
            return textCorr + "tres forte a parfaite";
        }

        return "imposible";
    }
}