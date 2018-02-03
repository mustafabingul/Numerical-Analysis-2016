/*

    141044077 Mustafa Bingül
    Sayısal Analiz HW01

*/

#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <string.h>


double function1(double x){
    return 3 * x - exp(x);
}
double function2(double x){
    return 2 * x + 3*cos(x) - exp(x);
}
double function3(double x){
    return x * x - 4 * x + 4 - log(x);
}
double function4(double x){
    return x + 1 - 2 * sin(M_PI * x);
}


double midPoint(double x, double y){
    
     return (x+y)/2.0;   
}

void absoluteErr(double p1, double p0){

    double absErr=0.0;
    
    absErr=p1-p0;
    
    printf("   ABSOLT ERR : %lf \n",fabs(absErr));
    
}
void relativeErr(double p1, double p0){
    
    double relErr=0.0;
    
    relErr=fabs((p1-p0))/fabs(p1);
    
    printf("   RELATIVE ERR : %lf ",relErr);
}

void BisectionMethod(double a,double b,char* stoppingCrit,double epsilon,int f){

    
    double midPValue=0;
    double midPPValue=0;
    double oldPValue=0;
    int i=1;
    int N=100;
    double deger=0;
    double abs=0;
    
    const char *dist="DISTANCE_TO_ROOT";
    const char *absl="ABSOLUTE_ERROR";
    const char *relat="RELATIVE_ERROR";
    printf("            a            b            midP       F(midP)     \n");
    if(f==1){
        
            if(strcmp(dist,stoppingCrit)==0){
            
            do{
            
                midPValue=midPoint(a,b);
                
                
                printf("%3d.  %10f   %10f    %10f   %10f",i,a,b,midPValue,function1(midPValue));
                
                if(function1(a)*function1(midPValue)<0){
                    
                    b=midPValue;
                    
                }
                else{
                    a=midPValue;
                   
                }
                
                midPPValue=midPoint(a,b);
                relativeErr(midPValue,oldPValue);
                absoluteErr(midPValue,oldPValue);
                
                
                oldPValue=midPValue;
                
                ++i;
            }
            while(fabs(function1(midPValue))>epsilon && i<N);    
        
        
        }
        else if(strcmp(absl,stoppingCrit)==0){
            
            do{
            
                midPValue=midPoint(a,b);
                
                
                printf("%3d.  %10f   %10f    %10f   %10f",i,a,b,midPValue,function1(midPValue));
                
                if(function1(a)*function1(midPValue)<0){
                
                    b=midPValue;
                    
                }
                else{
                    a=midPValue;
                   
                }
                
                midPPValue=midPoint(a,b);
                relativeErr(midPValue,oldPValue);
                absoluteErr(midPValue,oldPValue);
                
                deger=fabs(midPValue-oldPValue);
                oldPValue=midPValue;
                
                ++i;
            }
            while(deger>epsilon && i<N);    
        
        
        }
        else if(strcmp(relat,stoppingCrit)==0){
            
            do{
            
                midPValue=midPoint(a,b);
                
                
                printf("%3d.  %10f   %10f    %10f   %10f",i,a,b,midPValue,function1(midPValue));
                
                if(function1(a)*function1(midPValue)<0){
                
                    b=midPValue;
                    
                }
                else{
                    a=midPValue;
                   
                }
                
                midPPValue=midPoint(a,b);
                relativeErr(midPValue,oldPValue);
                absoluteErr(midPValue,oldPValue);
                
                
                ++i;
                deger=(fabs(midPValue-oldPValue)/fabs(midPValue));
                oldPValue=midPValue;
                
            }
            
            while(deger>epsilon && i<N);
        
        }
    
    
    }
    else if(f==2){
            
            if(strcmp(dist,stoppingCrit)==0){
        
            do{
            
                midPValue=midPoint(a,b);
                
                
                printf("%3d.  %10f   %10f    %10f   %10f",i,a,b,midPValue,function2(midPValue));
                
                if(function2(a)*function2(midPValue)<0){
                    
                    b=midPValue;
                    
                }
                else{
                    a=midPValue;
                   
                }
                
                midPPValue=midPoint(a,b);
                relativeErr(midPValue,oldPValue);
                absoluteErr(midPValue,oldPValue);
                
                
                oldPValue=midPValue;
                
                ++i;
            }
            while(fabs(function2(midPValue))>epsilon && i<N);    
        
        
        }
        else if(strcmp(absl,stoppingCrit)==0){
            
            do{
            
                midPValue=midPoint(a,b);
                
                
                printf("%3d.  %10f   %10f    %10f   %10f",i,a,b,midPValue,function2(midPValue));
                
                if(function2(a)*function2(midPValue)<0){
                
                    b=midPValue;
                    
                }
                else{
                    a=midPValue;
                   
                }
                
                midPPValue=midPoint(a,b);
                relativeErr(midPValue,oldPValue);
                absoluteErr(midPValue,oldPValue);
                
                deger=fabs(midPValue-oldPValue);
                oldPValue=midPValue;
                
                ++i;
            }
            while(deger>epsilon && i<N);    
        
        
        }
        else if(strcmp(relat,stoppingCrit)==0){
            
            do{
            
                midPValue=midPoint(a,b);
                
                
                printf("%3d.  %10f   %10f    %10f   %10f",i,a,b,midPValue,function2(midPValue));
                
                if(function2(a)*function2(midPValue)<0){
                
                    b=midPValue;
                    
                }
                else{
                    a=midPValue;
                   
                }
                
                midPPValue=midPoint(a,b);
                relativeErr(midPValue,oldPValue);
                absoluteErr(midPValue,oldPValue);
                
                
                ++i;
                deger=(fabs(midPValue-oldPValue)/fabs(midPValue));
                oldPValue=midPValue;
                
            }
            
            while(deger>epsilon && i<N);
        
        }
    
    }
    else if(f==3){
            
            if(strcmp(dist,stoppingCrit)==0){
        
            do{
            
                midPValue=midPoint(a,b);
                
                
                printf("%3d.  %10f   %10f    %10f   %10f",i,a,b,midPValue,function3(midPValue));
                
                if(function3(a)*function3(midPValue)<0){
                    
                    b=midPValue;
                    
                }
                else{
                    a=midPValue;
                   
                }
                
                midPPValue=midPoint(a,b);
                relativeErr(midPValue,oldPValue);
                absoluteErr(midPValue,oldPValue);
                
                
                oldPValue=midPValue;
                
                ++i;
            }
            while(fabs(function3(midPValue))>epsilon && i<N);    
        
        
        }
        else if(strcmp(absl,stoppingCrit)==0){
            
            do{
            
                midPValue=midPoint(a,b);
                
                
                printf("%3d.  %10f   %10f    %10f   %10f",i,a,b,midPValue,function3(midPValue));
                
                if(function3(a)*function3(midPValue)<0){
                
                    b=midPValue;
                    
                }
                else{
                    a=midPValue;
                   
                }
                
                midPPValue=midPoint(a,b);
                relativeErr(midPValue,oldPValue);
                absoluteErr(midPValue,oldPValue);
                
                deger=fabs(midPValue-oldPValue);
                oldPValue=midPValue;
                
                ++i;
            }
            while(deger>epsilon && i<N);    
        
        
        }
        else if(strcmp(relat,stoppingCrit)==0){
            
            do{
            
                midPValue=midPoint(a,b);
                
                
                printf("%3d.  %10f   %10f    %10f   %10f",i,a,b,midPValue,function3(midPValue));
                
                if(function3(a)*function3(midPValue)<0){
                
                    b=midPValue;
                    
                }
                else{
                    a=midPValue;
                   
                }
                
                midPPValue=midPoint(a,b);
                relativeErr(midPValue,oldPValue);
                absoluteErr(midPValue,oldPValue);
                
                
                ++i;
                deger=(fabs(midPValue-oldPValue)/fabs(midPValue));
                oldPValue=midPValue;
                
            }
            
            while(deger>epsilon && i<N);
        
        }
    
    }
    else if(f==4){
            
            if(strcmp(dist,stoppingCrit)==0){
        
            do{
            
                midPValue=midPoint(a,b);
                
                
                printf("%3d.  %10f   %10f    %10f   %10f",i,a,b,midPValue,function4(midPValue));
                
                if(function4(a)*function4(midPValue)<0){
                    
                    b=midPValue;
                    
                }
                else{
                    a=midPValue;
                   
                }
                
                midPPValue=midPoint(a,b);
                relativeErr(midPValue,oldPValue);
                absoluteErr(midPValue,oldPValue);
                
                
                oldPValue=midPValue;
                
                ++i;
            }
            while(fabs(function4(midPValue))>epsilon && i<N);    
        
        
        }
        else if(strcmp(absl,stoppingCrit)==0){
            
            do{
            
                midPValue=midPoint(a,b);
                
                
                printf("%3d.  %10f   %10f    %10f   %10f",i,a,b,midPValue,function4(midPValue));
                
                if(function4(a)*function4(midPValue)<0){
                
                    b=midPValue;
                    
                }
                else{
                    a=midPValue;
                   
                }
                
                midPPValue=midPoint(a,b);
                relativeErr(midPValue,oldPValue);
                absoluteErr(midPValue,oldPValue);
                
                deger=fabs(midPValue-oldPValue);
                oldPValue=midPValue;
                
                ++i;
            }
            while(deger>epsilon && i<N);    
        
        
        }
        else if(strcmp(relat,stoppingCrit)==0){
            
            do{
            
                midPValue=midPoint(a,b);
                
                
                printf("%3d.  %10f   %10f    %10f   %10f",i,a,b,midPValue,function4(midPValue));
                
                if(function4(a)*function4(midPValue)<0){
                
                    b=midPValue;
                    
                }
                else{
                    a=midPValue;
                   
                }
                
                midPPValue=midPoint(a,b);
                relativeErr(midPValue,oldPValue);
                absoluteErr(midPValue,oldPValue);
                
                
                ++i;
                deger=(fabs(midPValue-oldPValue)/fabs(midPValue));
                oldPValue=midPValue;
                
            }
            
            while(deger>epsilon && i<N);
        
        }
    
    }
}

int main(int argc, char ** argv){


    double a=atof(argv[1]);
    double b=atof(argv[2]);
    char crit[50];
    strcpy(crit,argv[3]);
    double eps=atof(argv[4]);
    
    
    
    int sec=0;   
    
    printf("1-) 3x-e^x\n");
    printf("2-) 2x+3cos(x)-e^x\n");
    printf("3-) x^2-4x+4-log(x)\n");
    printf("4-) x+1-2sin(PIx)\n");
    
    scanf("%d",&sec);

    if(sec==1){BisectionMethod(a,b,crit,eps,sec);      }
    else if(sec==2){BisectionMethod(a,b,crit,eps,sec); }
    else if(sec==3){BisectionMethod(a,b,crit,eps,sec); }
    else if(sec==4){BisectionMethod(a,b,crit,eps,sec); }
    else{
        printf("Wrong Select.");
    } 
    
    
    
    return 0;
}
