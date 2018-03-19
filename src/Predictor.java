import java.io.File;
import java.util.ArrayList;

public class Predictor {

    private static Predictor instance = null;

    private ArrayList<Double> weights; //linear classifier weights

    private ArrayList<String> dictionary;   //dictionary of main opcode commands

    private Predictor(){
        //todo
        //initialize
    }

    public static Predictor getInstance(){
        if (instance==null) instance = new Predictor();

        return instance;
    }

    private String getOpcode(File file){
        //todo
        return null;
    }

    public boolean predict(File file){
        String getopcode = getOpcode(file);
        int n = weights.size()-1;
        Double[] features = new Double[n];

        double result = 0;
        for (int i = 0; i <n ; i++) {
            result += weights.get(i)*features[i];
        }
        result += weights.get(n);

        return result<0;
    }
}
