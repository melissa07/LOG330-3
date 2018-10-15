import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Variance {
    private final String fileName= "donnees.csv";
    private File file = null;
    public static ArrayList<Double> listNumbers = null;

    public Variance() {
        listNumbers = readFile();
        double mean = calcAverage();
        System.out.println("Average: " +mean);
        double variance = calcVariance(mean);
        System.out.println("Variance: " +variance);
        double sd = calcStandardDev(variance);
        System.out.println("Standard deviation: " +sd);

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
}
