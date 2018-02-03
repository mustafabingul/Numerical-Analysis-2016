import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.DoubleBinaryOperator;

/**
 * Mustafa BİNGÜL
 * 141044077
 */
public class main {

    final static private int MAXITER = 50;
    final static private double TOL = 0.001;
    static private double[][] matrix;
    static private double[] Xmatrix;
    static private double[] Bmatrix;


    public static void main(String args[]){



        System.out.println();
        if(args.length!=4){
            System.out.print("USAGE::::\n" +
                    "./solver -i system.txt -m <METHOD>");
            return ;
        }
        if(args[3].contentEquals("GESP")){
            scaledGauss(args[1]);
            for(int i=0; i<matrix.length; ++i){
                System.out.println(Xmatrix[i]+"  X"+i);
            }
        }
        else if(args[3].contentEquals("JCB")){
            jacobiMethod(args[1]);
            for(int i=0; i<matrix.length; ++i){
                System.out.println(Xmatrix[i]+"  X"+i);
            }
        }


        /*jacobiMethod("system.txt");

        for (int i = 0; i < matrix.length ; i++) {

            System.out.println(Xmatrix[i]+"   "+i);

        }*/
    }

    public static int read(String fileName){
        String COMMA = ",";
        BufferedReader fileReader = null;
        BufferedReader fileReader2 = null;
        int pcs = 0;
        try{
            String line = "";
            fileReader = new BufferedReader(new FileReader(fileName));
            fileReader2 = new BufferedReader(new FileReader(fileName));

            while((line = fileReader.readLine()) != null){
                String[] tok = line.split(COMMA);
                pcs = tok.length;
            }

            matrix = new double[pcs-1][pcs-1];
            Bmatrix = new double[pcs-1];
            int i=0;
            while((line = fileReader2.readLine())!=null){

                String[] data = line.split(COMMA);
                for(int j=0; j<pcs-1; ++j){
                    matrix[i][j] = Double.parseDouble(data[j]);
                }
                Bmatrix[i]= Double.parseDouble(data[pcs-1]);

                ++i;

            }

            /*for(i=0; i<pcs-1; ++i){
                for(int j=0; j<pcs-1; ++j){
                    System.out.print("   "+matrix[i][j]);
                }
                *//*System.out.println();
                System.out.println(Bmatrix[i]);
                System.out.println();*//*
            }*/
        }
        catch (IOException e){
            System.out.print(e.toString());
        }
        return 1;
    }
    public static int jacobiMethod(String fileName){

        read(fileName);
        Xmatrix = new double[matrix.length];
        double xArrNew[] = new double[matrix.length];
        double xArrOld[] = new double[matrix.length];
        int mI=1;
        double custom=0;
        while(MAXITER>=mI){

            for(int i=0; i<matrix.length; ++i){
                custom=0;
                for(int j=0; j<matrix.length; ++j){

                    if(mI!=i){

                        custom = custom + matrix[i][j] * xArrOld[j];

                    }
                }
                xArrNew[i] = (Bmatrix[i]-custom)/matrix[i][i];
            }


            if(stoppingCrit(xArrOld,xArrNew)){
                break;
            }
            for(int x=0; x<xArrOld.length; ++x){
                xArrOld[x]=xArrNew[x];
            }
            mI++;
        }
        for(int i=0; i<matrix.length; ++i){
            Xmatrix[i]=xArrNew[i];
            /*System.out.println(Xmatrix[i]);*/
        }
        return 1;
    }
    public static int scaledGauss(String fileName){
        read(fileName);
        Xmatrix = new double[matrix.length];
        double sArr[] = new double[matrix.length];
        int pArr[] = new int[matrix.length];
        int j=0;
        double rMax=0;
        double r=0;
        int tmp=0;
        double coeff = 0;
        double result=0;
        for(int i=0; i<matrix.length; ++i){
            sArr[i]=maxValue(matrix[i]);
        }
        for (int i=0; i<matrix.length;++i){
            pArr[i] = i;
        }

        /**
         * Pivot satırı bulurnu.
         */
        for (int k=0; k<matrix.length-1; ++k){
            j=k;
            for (int i=k; i<matrix.length; ++i){
                r=Math.abs(matrix[pArr[i]][k]/sArr[pArr[i]]);
                if(r>rMax){
                    rMax = r;
                    j=i;
                }
            }
            tmp=pArr[k];
            pArr[k]=pArr[j];
            pArr[j]=tmp;

            for(int i= k+1; i<matrix.length; ++i){
                coeff = matrix[pArr[i]][k]/matrix[pArr[k]][k];
                for(int l=k; l<matrix.length; ++l){
                    matrix[pArr[i]][l] = matrix[pArr[i]][l] - coeff*matrix[pArr[k]][l];
                }
                Bmatrix[pArr[i]]=Bmatrix[pArr[i]] - coeff * Bmatrix[pArr[k]];
            }

        }

        Xmatrix[matrix.length-1] = Bmatrix[pArr[matrix.length-1]] /
                                matrix[pArr[matrix.length-1]][matrix.length-1];
        for(int i=matrix.length-2; i>=0; i--){
            result=Bmatrix[pArr[i]];
            for(j=i; j<matrix.length; ++j){
                result = result - matrix[pArr[i]][j] * Xmatrix[j];
            }
            Xmatrix[i] = result / matrix[pArr[i]][i];
            /*System.out.println(Xmatrix[i]+"   X "+i);*/
        }

        return 1;


    }
    private static boolean stoppingCrit(double[] xOldArr, double[] xNewArr){

        for(int i=0; i<xNewArr.length;++i ){
            if(Math.abs(xNewArr[i]-xOldArr[i])/Math.abs(xNewArr[i])<TOL){
                return true;
            }
        }

        return false;
    }
    public static double maxValue(double lineOfmatrix[]){
        double Max=0;
        for (int i=0; i<lineOfmatrix.length; ++i)
        {
            if(Max < Math.abs(lineOfmatrix[i])){
                Max = Math.abs(lineOfmatrix[i]);
            }
        }
        return Max;
    }
}
