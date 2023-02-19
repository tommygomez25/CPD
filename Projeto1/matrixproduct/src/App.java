import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class App {

    public static void write_result(int lin, int col, double time, String filename) {
        try {
            PrintWriter writer = new PrintWriter( new FileWriter(filename,true));
            writer.printf("\nLines: %d, Cols: %d, Block size:1, Time:%3.3f",lin,col,time);
            writer.close();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    public static void onMult(int m_ar, int m_br) {
        double[][] a = new double[m_ar][m_br];
        double[][] b = new double[m_ar][m_br];
        double[][] c = new double[m_ar][m_br];

        for (int i = 0; i < m_ar;i++){
            for (int j = 0; j < m_br; j++){
                a[i][j] = 1.0;
            }
        }

        for (int i = 0; i < m_ar;i++) {
            for (int j = 0; j < m_br;j++){
                double d = i+1;
                b[i][j] = d;
            }
        }

        //multiplty
        long startTime = System.nanoTime();

        for (int i = 0; i < m_ar;i++){
            for (int j = 0; j < m_br;j++){
                double temp = 0;
                for (int k = 0; k < m_ar;k++){
                    temp += a[i][k] * b[k][j];
                }
                c[i][j] = temp;
            }
        }

        long endTime = System.nanoTime();

        long processingTime = (endTime - startTime)/1000000;

        double processingTimeDouble = (double) processingTime / 1000;

        System.out.printf("%3.3f seconds\n",processingTimeDouble);

        write_result(m_ar, m_br, processingTimeDouble,"1_results_java.txt");

        System.out.println("RESULT MATRIX:");
        for (int i = 0; i < 1;i++){
            for (int j = 0; j < Math.min(10,m_br);j++){
                System.out.print(c[i][j] + " ");
            }
        }
    }

    public static void onMultLine(int m_ar, int m_br) {
        double[][] a = new double[m_ar][m_br];
        double[][] b = new double[m_ar][m_br];
        double[][] c = new double[m_ar][m_br];

        for (int i = 0; i < m_ar;i++){
            for (int j = 0; j < m_br; j++){
                a[i][j] = 1.0;
            }
        }

        for (int i = 0; i < m_ar;i++) {
            for (int j = 0; j < m_br;j++){
                double d = i+1;
                b[i][j] = d;
            }
        }

        //multiplty
        long startTime = System.nanoTime();

        for (int i = 0; i < m_ar;i++) {
            for (int k = 0; k < m_br; k++) {
                for (int j = 0; j < m_ar; j++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        long endTime = System.nanoTime();

        long processingTime = (endTime - startTime)/1000000;

        double processingTimeDouble = (double) processingTime / 1000;

        System.out.printf("%3.3f seconds\n",processingTimeDouble);

        write_result(m_ar, m_br, processingTimeDouble,"2_results_java.txt");

        System.out.println("RESULT MATRIX:");
        for (int i = 0; i < 1;i++){
            for (int j = 0; j < Math.min(10,m_br);j++){
                System.out.print(c[i][j] + " ");
            }
        }
    }
    public static void main(String[] args) throws Exception {
        int lin,col;
        int op = 1;
        do {
            System.out.println("1. Multiplication");
            System.out.println("2. Line Multiplication");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Selection?");
            op = scanner.nextInt();
            if (op == 0) break;


            System.out.println("Dimensions: lins= cols ? ");
            lin = scanner.nextInt();
            col = lin;

            switch (op) {
                case 1:
                    onMult(lin,col);
                    break;
                case 2:
                    onMultLine(lin,col);
                    break;
            }
        
        } while (op != 0);

    }
}
