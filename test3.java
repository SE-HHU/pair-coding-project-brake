
import java.util.Scanner;
import java.util.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class test3 {
		
	     
		
		public static String op1(int m) throws FileNotFoundException{//1个运算符的表达式
			Random r = new Random();
			String op = operate[r.nextInt(4)]; 
			
			int x,y;
			x = r.nextInt(m)+1;
			y = r.nextInt(m)+1;
			String c;
			c = CreatNum(x) + op + CreatNum(y);
			return c;			
	     }
		
		public static String op2(int m) throws FileNotFoundException{//2个运算符的表达式
			Random r = new Random();
			String op1 = operate[r.nextInt(4)];
			String op2 = operate[r.nextInt(4)];
			int x,y,z;
			x = r.nextInt(m)+1;
			y = r.nextInt(m)+1;
			z = r.nextInt(m)+1;
			String c = "";
			int temp = r.nextInt(2);
			int flag = r.nextInt(3);
			if(flag == 0)//生成括号的概率为1/3
			{
				switch (temp) { // 括号的位置
		        case 0:
		            c = "("+ CreatNum(x) + op1 + CreatNum(y) + ")" + op2 + CreatNum(z);
		            break;
		        case 1:
		            c = CreatNum(x) + op1 + "(" + CreatNum(y) + op2 + CreatNum(z) + ")";
		            break;
		        }
				return c;
			}
			else
				c = CreatNum(x) + op1 + CreatNum(y) + op2 + CreatNum(z);
			return c;
	     }
		
		public static String op3(int m) throws FileNotFoundException{ //3个运算符的表达式
			Random r = new Random();
			String op1 = operate[r.nextInt(4)];
			String op2 = operate[r.nextInt(4)];
			String op3 = operate[r.nextInt(4)];
			int x,y,z,o;          
			x = r.nextInt(m)+1;
			y = r.nextInt(m)+1;
			z = r.nextInt(m)+1;
			o = r.nextInt(m)+1;
			String c = "";
			int flag = r.nextInt(3);
			int temp = r.nextInt(5);
			if(flag == 0)                     //生成括号的概率为1/3
			{
	        switch (temp) {                   // 括号的位置
	        case 0:
	            c = "("+ CreatNum(x) + op1 + CreatNum(y) +")" + op2 + CreatNum(z) + op3 + CreatNum(o);
	            break;
	        case 1:
	            c = CreatNum(x) + op1 + "("+ CreatNum(y) + op2 + CreatNum(z) +")" + op3 + CreatNum(o);
	            break;
	        case 2:
	        	c = CreatNum(x) + op1 + CreatNum(y) + op2 + "("+ CreatNum(z) + op3 + CreatNum(o) +")";
	        	break;
	        case 3:
	        	c = "("+ CreatNum(x) + op1 + CreatNum(y) + op2 + CreatNum(z) +")" + op3 + CreatNum(o);
	        	break;
	        case 4:
	        	c = CreatNum(x) + op1 + "("+ CreatNum(y) + op2 + CreatNum(z) + op3 + CreatNum(o) +")";
	        	break;
	        }
			return c;
			}
			else
				c = CreatNum(x) + op1 + CreatNum(y) + op2 + CreatNum(z) + op3 + CreatNum(m);
			return c;
	     }
		

		public static String CreatNum(int m){         //生成随机数
			String s="";
			Random rd=new Random();
			switch(rd.nextInt(2)){                    //随机类型：整数，分数        
				case 0:                               //整数
					s=Integer.toString(rd.nextInt(m)+1);    
					break;
				case 1:                                 //分数      
					int a,b;
					a=rd.nextInt(m)+1;
					b=rd.nextInt(m)+a;
					s=Dating(a,b);                  //分数约分处理          
					break;
			}
			return s;
		}

		public static String Dating(int a,int b){    //分数约分
			String s="";
			int gongyinshu=1;
			int c=a/b;
			a=a%b;
			if(c<0){                                   
				a=a*-1;
			}
			for (int i = 1; i <= a; i++) {              //求最大公约数 
	            if (a % i == 0 && b % i == 0) {  
	                gongyinshu = i;  
	            }  
	        }
			a=a/gongyinshu;                            //生成最简分数
			b=b/gongyinshu;
			if(a==0){
				s=Integer.toString(c);
			}else if(c==0){
				s=Integer.toString(a)+"/"+Integer.toString(b);
			}else{
				s=Integer.toString(c)+"'"+Integer.toString(a)+"/"+Integer.toString(b);
			}
			return s;
		}
		
        private static String[] operate = new String[] { "+", "-", "*", "÷" };
		
		public static String infixToSuffix(String exp) {        //中缀表达式转后缀表达式
	    	Stack<Character> s = new Stack<Character>();        // 创建操作符堆栈        
	        String suffix = "";                                 // 要输出的后缀表达式字符串        
	        int length = exp.length();                          // 输入的中缀表达式的长度        
	        for (int i = 0; i < length; i++) {
	             char temp;
	             char ch = exp.charAt(i);                       // 获取该中缀表达式的每一个字符并进行判断        
	             switch (ch) {
	             	case '(':
	             		s.push(ch);
	             		break;
	             	case '+':                                  // 碰到'+' '-'，将栈中的所有运算符全部弹出去，直至碰到左括号为止，输出到队列中去
	             	case '-':
	             		suffix += " ";
	             		while (s.size() != 0) {
	             			temp = s.pop();
	             			if (temp == '(') {
	             				s.push('(');
	             				break;
	             			}
	             			suffix += temp;
	             			suffix += " ";
	             		}
	             		s.push(ch);
	             		break;
	             	case '*':                               // 如果是乘号或者除号，则弹出所有序列，直到碰到加好、减号、左括号为止，最后将该操作符压入堆栈
	             	case '÷':
	             		suffix += " ";
	             		while (s.size() != 0) {
		                    temp = s.pop();
		                    if (temp == '+' || temp == '-' || temp == '(') {
		                         s.push(temp);
		                         break;
		                     } else {
		                         suffix += temp;
		                         suffix += " ";
		                    }
	             		}
	             		s.push(ch);
	             		break;
	             	case ')':
		                 while (!s.isEmpty()) {
		                    temp = s.pop();
		                    if (temp == '(') {
		                         break;
		                    } else {
		                    	suffix += " ";
		                        suffix += temp;
		                    }
		                 }
		                 break;
	             	default:
		                 suffix += ch;
		                 break;
	             }
	        }
	        while (s.size() != 0) {                            // 如果堆栈不为空，则把剩余运算符一次弹出，送至输出序列
	        	suffix += " ";
	            suffix += s.pop();
	        }
	        return suffix;
	     }

		/*计算后缀表达式*/
	     public static String suffixToArithmetic(String exp) {  
	         String[] strings = exp.split(" ");                  //按空格分解字符串
	         Stack<String> stack = new Stack<String>();         //操作数栈
	         for (int i = 0; i < strings.length; i++) {
	             if(strings[i].equals("+")||strings[i].equals("-")||strings[i].equals("*")||strings[i].equals("÷")){
	            	 String y=stack.pop();                     //读取到运算符，提取栈顶的两个操作数，先出的操作数为运算符后的数   
	            	 String x=stack.pop();
	            	 String rus=calculate(x, y, strings[i]);      //调用自定义的四则运算法则
	            	 stack.push(rus);                             
	            	 
	             }else{
	            	 stack.push(strings[i]);
	             }   
	         }
	         return stack.pop();
	     }
	     
	         
	     public static String calculate(String x, String y, String ch) { //四则运算
	    	 String rus="";
	    	
	    	 boolean flag1=false,flag2=false;              //判断两个参数是否为分数，标记
	    	 if(x.indexOf("/")!=-1)
	    		 flag1=true;
	    	 if(y.indexOf("/")!=-1)
	    		 flag2=true;
	    	 
	    	 int a=1,b=1,c=1,d=1,f1=0,f2=0;                //对两个参数的数字进行转换，String转int,x=f1'a/c,y=f2'b/d
	    	 
	    	 if(flag1==false&&flag2==false){               //类型1，都为整数
	    		 a=Integer.parseInt(x);
	    		 b=Integer.parseInt(y);
	    	 }      
	    	 
	    	 if(flag1==false&&flag2==true){                //类型2，x为整数，y为分数
	    		 a=Integer.parseInt(x);
	    		 
	    		 int lenf2=y.indexOf("'");                          //判断是否带分数，是则提取所带整数
	    		 if(lenf2!=-1){
	    			 f2=Integer.parseInt(y.substring(0,lenf2));
	    		 }
	    		 
	    		 int len=y.indexOf("/");                            //提取分号前后的整数
	    		 b=Integer.parseInt(y.substring(lenf2+1, len));
	    		 d=Integer.parseInt(y.substring(len+1, y.length()));
	    		 if(f2<0){                                          //判断带分数是否负数，处理方式不同
	    			 b=f2*d-b;
	    		 }else{
	    			 b=f2*d+b;
	    		 }
	    	 }
	    	 
	    	 if(flag1==true&&flag2==false){                        //类型3，x为分数，y为整数
	    		 int lenf1=x.indexOf("'");                         //判断是否带分数，是则提取所带整数
	    		 if(lenf1!=-1){
	    			 f1=Integer.parseInt(x.substring(0,lenf1));
	    		 }
	    		 
	    		 int len=x.indexOf("/");                           //提取分号前后的整数
	    		 a=Integer.parseInt(x.substring(lenf1+1, len));
	    		 c=Integer.parseInt(x.substring(len+1, x.length()));
	    		 if(f1<0){                                         //判断带分数是否负数，处理方式不同
	    			 a=f1*c-a;
	    		 }else{
	    			 a=f1*c+a;
	    		 }
	    		 
	    		 b=Integer.parseInt(y);
	    	 }
	    	 
	    	 if(flag1==true&&flag2==true){                          //类型4，x为分数，y为分数
	    		 int lenf1=x.indexOf("'");                          //判断是否带分数，是则提取所带整数
	    		 if(lenf1!=-1){
	    			 f1=Integer.parseInt(x.substring(0,lenf1));
	    		 }
	    		 int len1=x.indexOf("/");                           //提取分号前后的整数
	    		 a=Integer.parseInt(x.substring(lenf1+1, len1));
	    		 c=Integer.parseInt(x.substring(len1+1, x.length()));
	    		 if(f1<0){                                          //判断带分数是否负数，处理方式不同
	    			 a=f1*c-a;
	    		 }else{
	    			 a=f1*c+a;
	    		 }
	    		 
	    		 int lenf2=y.indexOf("'");                          //判断是否带分数，是则提取所带整数
	    		 if(lenf2!=-1){
	    			 f2=Integer.parseInt(y.substring(0,lenf2));
	    		 }
	    		 int len2=y.indexOf("/");                           //提取分号前后的整数
	    		 b=Integer.parseInt(y.substring(lenf2+1, len2));
	    		 d=Integer.parseInt(y.substring(len2+1, y.length()));
	    		 if(f2<0){                                          //判断带分数是否负数，处理方式不同
	    			 b=f2*d-b;
	    		 }else{
	    			 b=f2*d+b;
	    		 }
	    	 }
	    	 
	    	 switch(ch){                                    //使用上述提取的四个整数进行运算并约分
	    	 	case "+":
	    	 		rus=Dating(a*d+c*b, c*d);
	    	 		break;
	    	 	case "-":
	    	 		rus=Dating(a*d-c*b, c*d);
	    	 		break;
	    	 	case "*":
	    	 		rus=Dating(a*b, c*d);
	    	 		break;
	    	 	case "÷":
	    	 		if(c*b==0){
	    	 			rus="无解";
	    	 			break;
	    	 		}else{
	    	 			rus=Dating(a*d, c*b);
	    	 		}
	    	 		break;
	    	 }
	    	 return rus;
	     }
	     
	    static List<String> exp =new ArrayList<String>();
	 	static List<String> ans =new ArrayList<String>();
	 	
	 	public static List checkAnswer(){                             //比较答案
	 		File file1 = new File("D:\\hahahahha\\Exercises.txt");
	 		File file2 = new File("D:\\hahahahha\\Answers.txt");
	 		
	 		List<String> find=new ArrayList<String>();         //用于存取重复表达式的信息
	 		try{                                               //将题目和答案扫描存入list表中
	             BufferedReader br1 = new BufferedReader(new FileReader(file1));
	             String s=null;
	             while((s = br1.readLine())!=null){
	             	s=s.substring(s.indexOf(":")+1,s.length());
	             	exp.add(s);
	             }       
	             
	             BufferedReader br2 = new BufferedReader(new FileReader(file2));
	             while((s = br2.readLine())!=null){
	             	s=s.substring(s.indexOf(":")+1,s.length());
	             	ans.add(s);
	             }
	             
	          }catch (IOException e) {
	              e.printStackTrace();
	          }
	 		
	 		for(int i=0;i<ans.size()-1;i++){                    //冒泡排序查找相同答案，再进行下一步判断
	 			String s="";
	 			for(int j=i+1;j<ans.size();j++)
	 			{
	 				if(ans.get(i).equals(ans.get(j)))
	 				{
	 					if(checkExp(exp.get(i),exp.get(j)))
	 						s+=(i+1)+","+exp.get(i)+" Repeat "+(j+1)+","+exp.get(j)+"  ";
	 				}
	 			}
	 			if(s.length()>0)
	 				find.add(s);	
	 		}
	 		return find;
	 	}
	 	
	 	public static boolean checkExp(String exp1,String exp2){       //中缀转后缀，通过比较两表达式字符判断

	 		exp1=infixToSuffix(exp1);
	 		exp2=infixToSuffix(exp2);		
	 		
	 		String[] strings = exp1.split(" ");
	 		
	 		for(int i=0;i<strings.length;i++){                 
	 			if(exp2.indexOf(strings[i])==-1)
	 				return false;
	 		}
	 		return true;               
	 	}

	 	public static void FileW(File file,String s){                   //文件写入函数
			try {  
	            FileWriter  out=new FileWriter (file,true);
	            BufferedWriter bw= new BufferedWriter(out); 
	            bw.write(s);   
	            bw.flush();
	            bw.close();                     
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}	
	 	
	 	public static void FileC(File file1,File file2,File file3){                 //比较两个答案的文件，将结果写入另一个文件
	 		List<String> correct =new ArrayList<String>();
	 		List<String> wrong =new ArrayList<String>();
	 		try{
	             BufferedReader br1 = new BufferedReader(new FileReader(file1));
	             BufferedReader br2 = new BufferedReader(new FileReader(file2));
	             int i=1;
	             String s = null; 
	             while((s = br1.readLine())!=null){
	             	if(s.equals(br2.readLine())){                           
	             		correct.add(Integer.toString(i));
	             	}else{
	             		wrong.add(Integer.toString(i));
	             	}
	             	i++;
	             }
	             
	             FileW(file3, "Correct:"+correct.size()+"(");
	             for(int j=0;j<correct.size();j++){
	             	if(j==correct.size()-1){
	             		FileW(file3, correct.get(j));
	             	}else{
	             		FileW(file3, correct.get(j)+",");
	             	}
	             }
	             FileW(file3, ")"+"\r\n");
	             
	             FileW(file3, "Wrong:"+wrong.size()+"(");
	             for(int j=0;j<wrong.size();j++){
	             	if(j==wrong.size()-1){
	             		FileW(file3, wrong.get(j));
	             	}else{
	             		FileW(file3, wrong.get(j)+",");
	             	}
	             }
	             FileW(file3, ")"+"\r\n");
	             
	     		List<String> find=new ArrayList<String>();
	     		find=checkAnswer();
	     		FileW(file3,"Repeat:"+find.size()+"\r\n");
	     		FileW(file3,"RepeatDetail:"+"\r\n");
	     		for(int k=1;k<=find.size();k++){
	     			FileW(file3,"("+k+") "+find.get(k-1)+"\r\n");
	     		}
	     			            
	             br1.close();  
	             br2.close();  

	 		}catch (IOException e) {
	 	        e.printStackTrace();
	 	    }
	 	}
	 	public static void main(String[] args)throws IOException {//主方法
		    Scanner in = new Scanner(System.in);		
			System.out.println("输入生成个数:");
			int n = in.nextInt();
			System.out.println("输入参数范围:");
			int m = in.nextInt();                    
			op1(m);                            //调用op(1),op(2),op(3)函数
			op2(m);
			op3(m);
			Random r = new Random();
			String str[]=new String[n];
			String ans[]=new String[n];
			start:                                   //for循环，生成1个或2个或3个运算符的表达式，存入数组str[n-1]中
		        for(int x=0;x<n;) 
		        {
		        	
		        	int i = r.nextInt(3) + 1;
		            if(i == 1)                     //随机生成数字
		                 str[x] = op1(m);
			        else if(i == 2)
			        	 str[x] = op2(m);
			        else if(i == 3)
			        	str[x] = op3(m);
		            x++;
		            boolean z = false;
		            for (int y = 0; y < x - 1; y++) {
		                z |= str[x - 1].equals(str[y]);
		                if (z) {
		                    x--;
		                    
		                }
		            }  
		           
			
				    String s = "";                                 //将用后缀计算得出的答案存入数组ans[n-1]中
				    s=infixToSuffix(str[x-1]);
				    ans[x-1]=suffixToArithmetic(s);
				    int size1=ans[x-1].indexOf("'");               //-1说明不是带分数
				    int size2=ans[x-1].indexOf("-");
		            if(size1!=-1||size2!=-1)//若是负数或带分数则重新生成随机数
					    {
						    x--; continue start;
						
					    }
		            boolean flag=false;                             //判断是否为整数
		            if(ans[x-1].indexOf("/")!=-1)
		                flag=true;
		            int a=0,b=0;
		            if(flag==false)
		            {
		                a=Integer.parseInt(ans[x-1].trim());
		            }
		            if(flag==true)
		            {
		                int len=ans[x-1].indexOf("/");
		                a=Integer.parseInt(ans[x-1].substring(0, len));
		                b=Integer.parseInt(ans[x-1].substring(len+1, ans[x-1].length()));
		            }
		            if(a>m||b>m)                                  //若分子分母大于m则重新生成随机数
		            {             
		                x--;  continue start;
		            }
			}
			
			File file1=new File("D:\\hahahahha\\Exercises.txt");//将数组str[n-1]和ans[n-1]中的题目
			File file2=new File("D:\\hahahahha\\Answers.txt");//和答案分别写入Exercises.txt和Answers.txt
			File file3 = new File("D:\\hahahahha\\MyAnswers.txt");
			File file4 = new File("D:\\hahahahha\\Grade.txt");
			
			for(int i=0;i<n;i++){
				String fstr;
				fstr = i+1+"."+str[i]+"\r\n";            
				FileW(file1, fstr);            //表达式写入文档
				fstr=i+1+"." + ans[i]+"\r\n";
				FileW(file2, fstr);		//答案写入文档	
				
			
			}

			
	        System.out.println("请输入指令：");
	        while(in.hasNext()){
				switch(in.next()){
					case "go":
						FileC(file2, file3, file4);        //答案和做题文档对比，结果写入Grade文档
						break;
					case "end":
						break;	
				}
				break;
			}		
		}
}






