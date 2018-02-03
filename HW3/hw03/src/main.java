import java.util.Map;
import java.util.Random;
/**
 * Created by mustafa on 10.05.2017.
 * Bazı methodlarda internette pesudo kod yardımı aldım.
 */

public class main {
    public static void main(String[] args){

        double[] xD = new double[5];
        double[] yD = new double[5];
        double[] xL=new double[10];
        double[] yL=new double[10];
        xD[0]=1.0;
        xD[1]=1.3;
        xD[2]=1.6;
        xD[3]=1.9;
        xD[4]=2.2;

        yD[0]=0.7651;
        yD[1]=0.62;
        yD[2]=0.4554;
        yD[3]=0.2818;
        yD[4]=0.110;

        xL[0]=1;
        xL[1]=2;
        xL[2]=3;
        xL[3]=4;
        xL[4]=5;
        xL[5]=6;
        xL[6]=7;
        xL[7]=8;
        xL[8]=9;
        xL[9]=10;

        yL[0]=1.3;
        yL[1]=3.5;
        yL[2]=4.2;
        yL[3]=5.0;
        yL[4]=7.0;
        yL[5]=8.8;
        yL[6]=10.1;
        yL[7]=12.5;
        yL[8]=13.0;
        yL[9]=15.6;

        //dividedDifferences(xD,yD);
        leastSquare(xL,yL,1);
        //selectStrategy(3,2);


    }
    public static void selectStrategy(int s,int ZoomFact){
        double[] Mat= new double[100];
        Random rand = new Random();
        for(int i=0; i<Mat.length; ++i){
            Mat[i]=rand.nextInt(100);
        }

        if(s==1){
            nearestInterpolation(Mat,ZoomFact);
        }
        if(s==2){
            bilinearInterpolation(Mat,ZoomFact);
        }
        if(s==3){
            System.out.println("I can not do this. Bicubic Interpolation.");
        }
    }
    public static double[] leastSquare(double[] X,double[] Y, int m){
        int size = X.length;
        double[] sigma = new double[2*m+1];
        for(int i=0; i<2*m+1; ++i){
            sigma[i] = 0;
            for(int j=0; j<size; ++j){
                sigma[i]=sigma[i]+Math.pow(X[j],i);
            }
        }
        double augMatrix[][] = new double[m+1][m+2];
        double[] coeff = new double[m+1];
        for(int i=0;i<=m; ++i){
            for(int j=0; j<=m;++j){
                augMatrix[i][j]=sigma[i+j];
            }
        }
        double[] sigma2 = new double[m+1];
        for(int i=0; i<m+1; ++i){
            sigma2[i]=0;
            for(int j=0; j<size; ++j){
                sigma2[i] = sigma2[i]+Math.pow(X[j],i)*Y[j];
            }
        }
        for(int i=0; i<=m; ++i){
            augMatrix[i][m+1]=sigma2[i];
        }
        m=m+1;
        for(int i=0; i<m; ++i){
            for(int k=i+1; k<m; ++k){
                if(augMatrix[i][i]<augMatrix[k][i]){
                    for(int j=0; j<=m; ++j){
                        double t = augMatrix[i][j];
                        augMatrix[i][j]=augMatrix[k][j];
                        augMatrix[k][j]=t;
                    }
                }
            }
        }
        for(int i=0; i<m-1; i++){
            for(int k=i+1; k<m; ++k){
                double T=augMatrix[k][i]/augMatrix[i][i];
                for(int j=0; j<=m; ++j){
                    augMatrix[k][j]=augMatrix[k][j]-T*augMatrix[i][j];
                }
            }
        }
        for(int i=m-1; i>=0; i--){
            coeff[i]=augMatrix[i][m];
            for(int j=0; j<m; ++j){
                if(j!=i){
                    coeff[i]=coeff[i]-augMatrix[i][j]*coeff[j];
                }
            }
            coeff[i]=coeff[i]/augMatrix[i][i];
        }
        for(int i=0; i<m; ++i){
            System.out.printf(" +(%3.3f)x^%d",coeff[i],i);
        }
        return coeff;
    }

    public static double[][] dividedDifferences(double[] Xarray, double[] Yarray){
        int k=0;
        int n=Xarray.length;
        double[][] dArray = new double[Xarray.length][Xarray.length];
        for(int i=0; i<n; ++i){
            dArray[i][0]=Yarray[i];
        }
        for(int j=1; j<n; ++j){
            for(int i=0; i<n-j;++i){
                dArray[i][j]=dArray[i+1][j-1]-dArray[i][j-1];
            }
        }
        k=n;
        System.out.println("   X   F(x) ");
        for(int i=0; i<n; ++i){
            System.out.printf(" %2.2f",Xarray[i]);
            for(int j=0; j<k;++j){
                System.out.printf(" %2.2f",dArray[i][j]);
            }
            System.out.println();
            k--;
        }
        return dArray;
    }

    public static double[] bilinearInterpolation(double[] array, int zoomingFactor){
        int nSize= (int) (zoomingFactor*Math.sqrt(array.length));
        double[] newArray = new double[nSize*nSize];
        double xR =(double)(Math.sqrt(array.length)-1)/Math.sqrt(newArray.length);
        double yR =(double)(Math.sqrt(array.length)-1)/Math.sqrt(newArray.length);
        int x,y;
        double v1,v2,v3,v4;
        double value;
        int count=0;
        int index;
        double xD,yD;
        for(int i=0; i<Math.sqrt(newArray.length); ++i){
            for(int j=0; j<Math.sqrt(newArray.length); ++j){
                x=(int)(xR*j);
                y=(int)(yR*i);
                xD = (xR*j)-x;
                yD = (yR*i)-y;
                index = y*(int)Math.sqrt(array.length)+x;
                v1 = array[index];
                v2 = array[index+1];
                v3 = array[index+(int)Math.sqrt(array.length)];
                v4 = array[index+ (int)Math.sqrt(array.length)+1];
                value =  (v1*(1-xD)*(1-yD)+v2*(xD)*(1-yD)+v3*(yD)*(1-xD)+v4*(xD*yD));
                newArray[count++] = value;
            }
        }
        int c=0;
        for(int k=0; k<Math.sqrt(newArray.length);++k){
            for(int l=0; l<Math.sqrt(newArray.length);++l){
                System.out.printf(" %2.2f ",newArray[c++]);
            }
            System.out.println();
        }
        return newArray;

    }
    public static void nearestInterpolation(double[] array, int zoomingFactor){
        int nSize= (int) (zoomingFactor*Math.sqrt(array.length));
        double[] newArray = new double[nSize*nSize];
        double x,y;
        double xR = Math.sqrt(array.length)/(double)Math.sqrt(newArray.length);
        double yR = Math.sqrt(array.length)/(double)Math.sqrt(newArray.length);
        for(int i=0; i<Math.sqrt(newArray.length); ++i){
            for(int j=0; j<Math.sqrt(newArray.length); ++j){
                x=Math.floor(j*xR);
                y=Math.floor(i*yR);
                newArray[(i*(int)Math.sqrt(newArray.length))+j] = array[(int) ((y*Math.sqrt(array.length))+x)];
                System.out.printf(" %2.2f ",newArray[(i*(int)Math.sqrt(newArray.length))+j]);
            }
            System.out.println();
        }
    }
}

