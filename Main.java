
import java.io.*;
import java.util.*;
/*
运算式
 */
class Expression
{
    private String exercise; //运算式
    private Score answer; //答案
    public Expression(String exercise,Score answer)
    {
        this.exercise=exercise;
        this.answer=answer;
    }
    public String getExercise()
    {
        return this.exercise;
    }
    public Score getAnswer()
    {
        return this.answer;
    }
}
class Score
{
    private int numerator; //分子
    private int denominator;  //分母
    public Score(int numerator,int denominator)
    {
        this.numerator=numerator;
        this.denominator=denominator;
    }
    public int getNumerator()
    {
        return this.numerator;
    }
    public int getDenominator()
    {
        return this.denominator;
    }
    public String toString()
    {
        if(this.getNumerator()<=this.getDenominator())
        {
            return this.getNumerator()+(this.getDenominator()==1?"":("/"+this.getDenominator()));
        }
        else if(this.getNumerator()%this.getDenominator()!=0)
        {
            return (this.getNumerator()/this.getDenominator())+"+"+(this.getNumerator()%this.getDenominator())+"/"+this.getDenominator();
        }
        else
        {
            return ""+(this.getNumerator()/this.getDenominator());
        }
    }
}
public class Main
{
    /*
    将随机数转换成运算符
     */
    public static String Transform(int r)
    {
        String s;
        switch (r)
        {
            case 0:s="+";break;
            case 1:s="-";break;
            case 2:s="*";break;
            case 3:s="/";break;
            default:s="";
        }
        return s;
    }
    /*
    将数字转换成分数
     */
    public static Score Transmit(int r)
    {
        return new Score(r,1);
    }

    /*
    最大公约数
     */
    public static int gcd(int a,int b)
    {
        return a%b==0?b:gcd(b,a%b);
    }

    public static Score Add(Score a,Score b)
    {
        int m=a.getNumerator()*b.getDenominator()+b.getNumerator()*a.getDenominator();
        int n=a.getDenominator()*b.getDenominator();
        int t=Main.gcd(m,n);
        return new Score(m/t,n/t);
    }
    public static Score Minus(Score a,Score b)
    {
        int m=a.getNumerator()*b.getDenominator()-b.getNumerator()*a.getDenominator();
        int n=a.getDenominator()*b.getDenominator();
        int t=Main.gcd(m,n);
        return new Score(m/t,n/t);
    }
    public static Score Multiply(Score a,Score b)
    {
        int m=a.getNumerator()*b.getNumerator();
        int n=a.getDenominator()*b.getDenominator();
        int t=Main.gcd(m,n);
        return new Score(m/t,n/t);
    }
    public static Score Divide(Score a,Score b)
    {
        int m=a.getNumerator()*b.getDenominator();
        int n=a.getDenominator()*b.getNumerator();
        int t=Main.gcd(m,n);
        return new Score(m/t,n/t);
    }
    /*
    计算两个数
     */
    public static Score Calculate2(Score a,String op,Score b)
    {
        Score answer;
        if(b.getNumerator()==0)return new Score(0,1);
        switch (op)
        {
            case "+":answer=Add(a,b);break;
            case "-":answer=Minus(a,b);break;
            case "*":answer=Multiply(a,b);break;
            case "/":answer=Divide(a,b);break;
            default:return new Score(0,1);
        }
        return answer;
    }
    /*
    计算三个数
     */
    public static Score Calculate3(Score a,String op1,Score b,String op2,Score c)
    {
        Score answer;
        if(op2=="*"||op2=="/")
        {
            answer = Calculate2(a, op1, Calculate2(b, op2, c));
        }
        else
        {
            answer=Calculate2(Calculate2(a,op1,b),op2,c);
        }
        return answer;
    }
    /*
    计算四个数
     */
    public static Score Calculate4(Score a,String op1,Score b,String op2,Score c,String op3,Score d)
    {
        Score answer;
        if(op3=="*"||op3=="/")
        {
            answer=Calculate3(a,op1,b,op2,Calculate2(c,op3,d));
        }
        else
        {
            answer=Calculate2(Calculate3(a,op1,b,op2,c),op3,d);
        }
        return answer;
    }
    /*
    生成运算式及其答案
     */
    public static Expression express(int... k)
    {
        int n=k.length; //取得运算数的个数，一般为2,3,4
        Random r=new Random();
        int brac;
        /*控制括号，对于三个运算数的运算式，取0、1或2，对应无、前两个和后两个，例如1+2+3、（1+2）+3和1+（2+3）
        对于四个运算式的运算式，取0,1,2,3,4,5,6，对应无、前两个、中两个、后两个、前三个、后三和前后各两个，例如
        1+2+3+4、(1+2)+3+4、1+(2+3)+4、1+2+(3+4)、(1+2+3)+4、1+(2+3+4)、(1+2)+(3+4)
         */
        String op1,op2,op3; //运算符号
        String expression=""; //运算式
        Score answer=new Score(0,1); //答案
        switch (n)
        {
            case 2:
                op1=Transform(r.nextInt(4));
                expression=k[0]+op1+k[1];
                answer=Calculate2(Transmit(k[0]),op1,Transmit(k[1]));
                break;
            case 3:
                op1=Transform(r.nextInt(4));
                op2=Transform(r.nextInt(4));
                brac=r.nextInt(3);
                if(brac==0)
                {
                    expression=k[0]+op1+k[1]+op2+k[2];
                    answer=Calculate3(Transmit(k[0]),op1,Transmit(k[1]),op2,Transmit(k[2]));
                }
                else if(brac==1)
                {
                    expression="("+k[0]+op1+k[1]+")"+op2+k[2];
                    answer=Calculate2(Calculate2(Transmit(k[0]),op1,Transmit(k[1])),op2,Transmit(k[2]));
                }
                else
                {
                    expression=k[0]+op1+"("+k[1]+op2+k[2]+")";
                    answer=Calculate2(Transmit(k[0]),op1,Calculate2(Transmit(k[1]),op2,Transmit(k[2])));
                }
                break;
            case 4:
                op1=Transform(r.nextInt(4));
                op2=Transform(r.nextInt(4));
                op3=Transform(r.nextInt(4));
                brac=r.nextInt(7);
                if(brac==0)
                {
                    expression=k[0]+op1+k[1]+op2+k[2]+op3+k[3];
                    answer=Calculate4(Transmit(k[0]),op1,Transmit(k[1]),op2,Transmit(k[2]),op3,Transmit(k[3]));
                }
                else if(brac==1)
                {
                    expression="("+k[0]+op1+k[1]+")"+op2+k[2]+op3+k[3];
                    answer=Calculate3(Calculate2(Transmit(k[0]),op1,Transmit(k[1])),op2,Transmit(k[2]),op3,Transmit(k[3]));
                }
                else if(brac==2)
                {
                    expression=k[0]+op1+"("+k[1]+op2+k[2]+")"+op3+k[3];
                    answer=Calculate3(Transmit(k[0]),op1,Calculate2(Transmit(k[1]),op2,Transmit(k[2])),op3,Transmit(k[3]));
                }
                else if(brac==3)
                {
                    expression=k[0]+op1+k[1]+op2+"("+k[2]+op3+k[3]+")";
                    answer=Calculate3(Transmit(k[0]),op1,Transmit(k[1]),op2,Calculate2(Transmit(k[2]),op3,Transmit(k[3])));
                }
                else if(brac==4)
                {
                    expression="("+k[0]+op1+k[1]+op2+k[2]+")"+op3+k[3];
                    answer=Calculate2(Calculate3(Transmit(k[0]),op1,Transmit(k[1]),op2,Transmit(k[2])),op3,Transmit(k[3]));
                }
                else if(brac==5)
                {
                    expression=k[0]+op1+"("+k[1]+op2+k[2]+op3+k[3]+")";
                    answer=Calculate2(Transmit(k[0]),op1,Calculate3(Transmit(k[1]),op2,Transmit(k[2]),op3,Transmit(k[3])));
                }
                else
                {
                    expression="("+k[0]+op1+k[1]+")"+op2+"("+k[2]+op3+k[3]+")";
                    answer=Calculate2(Calculate2(Transmit(k[0]),op1,Transmit(k[1])),op2,Calculate2(Transmit(k[2]),op3,Transmit(k[3])));
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + n);
        }
        return new Expression(expression,answer);
    }
    /*
    生成n个运算式
     */
    public static void produce(int n,int min,int max) throws IOException
    {
        Random r=new Random();
        boolean judge=false; //判断运算式是否有效
        int r0;  //r0表示运算符个数，取0,1或2
        int k1,k2,k3,k4;
        Expression expression=new Expression("",new Score(0,1));  //
        HashSet<String> hs=new HashSet<String>(); //
        OutputStream os1=new FileOutputStream("Exercises.txt");
        PrintWriter pw1=new PrintWriter(os1);
        OutputStream os2=new FileOutputStream("Answers.txt");
        PrintWriter pw2=new PrintWriter(os2);
        for(int i=0;i<n;i++)
        {
            r0=r.nextInt(3);
            k1=min+r.nextInt(max-min+1);
            k2=min+r.nextInt(max-min+1);
            do {
                switch (r0)
                {
                    case 0:expression=express(k1,k2);break;
                    case 1:
                        k3=min+r.nextInt(max-min+1);
                        expression=express(k1,k2,k3);
                        break;
                    case 2:
                        k3=min+r.nextInt(max-min+1);
                        k4=min+r.nextInt(max-min+1);
                        expression=express(k1,k2,k3,k4);
                        break;
                }
                judge=hs.contains(expression.getExercise());
            }while (judge==true);
            hs.add(expression.getExercise());
            pw1.println((i+1)+"."+expression.getExercise()+"=");
            pw2.println((i+1)+"."+expression.getAnswer().toString());
        }
        pw1.close();
        pw2.close();
        os1.close();
        os2.close();
    }
    public static void main(String[] args) throws IOException {
        Scanner in=new Scanner(System.in);
        System.out.print("请输入题目的数目： ");
        int n=in.nextInt(); //n为题目的数目
        System.out.print("请输入题目中数值的最小值： ");
        int min=in.nextInt();
        System.out.print("请输入题目中数值的最大值： ");
        int max=in.nextInt();
        Main.produce(n,min,max);
    }
}