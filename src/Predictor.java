import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.DoubleStream;

public class Predictor {

    private final static Logger LOGGER = Logger.getLogger(Predictor.class.getName());

    private static Predictor instance = null;

    private ArrayList<Double> weights=new ArrayList<>(); //linear classifier weights

    private ArrayList<String> dictionary=new ArrayList<>();   //dictionary of main opcode commands

    private Predictor(){
        SQLiteConnection connection = SQLiteConnection.getInstance();
        SQLiteConnection.ResTuple res = connection.exec("select * from weights;");

        try{
            while (res.resultSet.next()){
                String comm = res.resultSet.getString("command");
                if (!comm.equals("offset")) dictionary.add(comm);
                weights.add(res.resultSet.getDouble("weight"));
            }
            res.close();
            connection.close();
        }
        catch (SQLException e){
            LOGGER.log( Level.SEVERE, e.toString(), e);
        }
        System.out.println(dictionary);
        System.out.println(weights);
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
        String opcode = getOpcode(file);
        assert opcode != null;
        int n = weights.size()-1;
        double[] features = new double[n];

        //count occurrences of commands
        for (int i = 0; i <n ; i++) {
            features[i] = (double) ((opcode.length() - opcode.replace(dictionary.get(i), "").length())
                    / dictionary.get(i).length());
        }

        //normalize
        double sum = DoubleStream.of(features).sum();
        for (int i = 0; i <n ; i++) features[i] /= sum;

        //calculate scalar product
        double result = 0;
        for (int i = 0; i <n ; i++) {
            result += weights.get(i)*features[i];
        }
        result += weights.get(n);

        return result<0;
    }
}
