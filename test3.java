
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
		
	     
		
		public static String op1(int m) throws FileNotFoundException{//1��������ı��ʽ
			Random r = new Random();
			String op = operate[r.nextInt(4)]; 
			
			int x,y;
			x = r.nextInt(m)+1;
			y = r.nextInt(m)+1;
			String c;
			c = CreatNum(x) + op + CreatNum(y);
			return c;			
	     }
		
		public static String op2(int m) throws FileNotFoundException{//2��������ı��ʽ
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
			if(flag == 0)//�������ŵĸ���Ϊ1/3
			{
				switch (temp) { // ���ŵ�λ��
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
		
		public static String op3(int m) throws FileNotFoundException{ //3��������ı��ʽ
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
			if(flag == 0)                     //�������ŵĸ���Ϊ1/3
			{
	        switch (temp) {                   // ���ŵ�λ��
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
		

		public static String CreatNum(int m){         //���������
			String s="";
			Random rd=new Random();
			switch(rd.nextInt(2)){                    //������ͣ�����������        
				case 0:                               //����
					s=Integer.toString(rd.nextInt(m)+1);    
					break;
				case 1:                                 //����      
					int a,b;
					a=rd.nextInt(m)+1;
					b=rd.nextInt(m)+a;
					s=Dating(a,b);                  //����Լ�ִ���          
					break;
			}
			return s;
		}

		public static String Dating(int a,int b){    //����Լ��
			String s="";
			int gongyinshu=1;
			int c=a/b;
			a=a%b;
			if(c<0){                                   
				a=a*-1;
			}
			for (int i = 1; i <= a; i++) {              //�����Լ�� 
	            if (a % i == 0 && b % i == 0) {  
	                gongyinshu = i;  
	            }  
	        }
			a=a/gongyinshu;                            //����������
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
		
        private static String[] operate = new String[] { "+", "-", "*", "��" };
		
		public static String infixToSuffix(String exp) {        //��׺���ʽת��׺���ʽ
	    	Stack<Character> s = new Stack<Character>();        // ������������ջ        
	        String suffix = "";                                 // Ҫ����ĺ�׺���ʽ�ַ���        
	        int length = exp.length();                          // �������׺���ʽ�ĳ���        
	        for (int i = 0; i < length; i++) {
	             char temp;
	             char ch = exp.charAt(i);                       // ��ȡ����׺���ʽ��ÿһ���ַ��������ж�        
	             switch (ch) {
	             	case '(':
	             		s.push(ch);
	             		break;
	             	case '+':                                  // ����'+' '-'����ջ�е����������ȫ������ȥ��ֱ������������Ϊֹ�������������ȥ
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
	             	case '*':                               // ����ǳ˺Ż��߳��ţ��򵯳��������У�ֱ�������Ӻá����š�������Ϊֹ����󽫸ò�����ѹ���ջ
	             	case '��':
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
	        while (s.size() != 0) {                            // �����ջ��Ϊ�գ����ʣ�������һ�ε����������������
	        	suffix += " ";
	            suffix += s.pop();
	        }
	        return suffix;
	     }

		/*�����׺���ʽ*/
	     public static String suffixToArithmetic(String exp) {  
	         String[] strings = exp.split(" ");                  //���ո�ֽ��ַ���
	         Stack<String> stack = new Stack<String>();         //������ջ
	         for (int i = 0; i < strings.length; i++) {
	             if(strings[i].equals("+")||strings[i].equals("-")||strings[i].equals("*")||strings[i].equals("��")){
	            	 String y=stack.pop();                     //��ȡ�����������ȡջ�����������������ȳ��Ĳ�����Ϊ����������   
	            	 String x=stack.pop();
	            	 String rus=calculate(x, y, strings[i]);      //�����Զ�����������㷨��
	            	 stack.push(rus);                             
	            	 
	             }else{
	            	 stack.push(strings[i]);
	             }   
	         }
	         return stack.pop();
	     }
	     
	         
	     public static String calculate(String x, String y, String ch) { //��������
	    	 String rus="";
	    	
	    	 boolean flag1=false,flag2=false;              //�ж����������Ƿ�Ϊ���������
	    	 if(x.indexOf("/")!=-1)
	    		 flag1=true;
	    	 if(y.indexOf("/")!=-1)
	    		 flag2=true;
	    	 
	    	 int a=1,b=1,c=1,d=1,f1=0,f2=0;                //���������������ֽ���ת����Stringתint,x=f1'a/c,y=f2'b/d
	    	 
	    	 if(flag1==false&&flag2==false){               //����1����Ϊ����
	    		 a=Integer.parseInt(x);
	    		 b=Integer.parseInt(y);
	    	 }      
	    	 
	    	 if(flag1==false&&flag2==true){                //����2��xΪ������yΪ����
	    		 a=Integer.parseInt(x);
	    		 
	    		 int lenf2=y.indexOf("'");                          //�ж��Ƿ��������������ȡ��������
	    		 if(lenf2!=-1){
	    			 f2=Integer.parseInt(y.substring(0,lenf2));
	    		 }
	    		 
	    		 int len=y.indexOf("/");                            //��ȡ�ֺ�ǰ�������
	    		 b=Integer.parseInt(y.substring(lenf2+1, len));
	    		 d=Integer.parseInt(y.substring(len+1, y.length()));
	    		 if(f2<0){                                          //�жϴ������Ƿ���������ʽ��ͬ
	    			 b=f2*d-b;
	    		 }else{
	    			 b=f2*d+b;
	    		 }
	    	 }
	    	 
	    	 if(flag1==true&&flag2==false){                        //����3��xΪ������yΪ����
	    		 int lenf1=x.indexOf("'");                         //�ж��Ƿ��������������ȡ��������
	    		 if(lenf1!=-1){
	    			 f1=Integer.parseInt(x.substring(0,lenf1));
	    		 }
	    		 
	    		 int len=x.indexOf("/");                           //��ȡ�ֺ�ǰ�������
	    		 a=Integer.parseInt(x.substring(lenf1+1, len));
	    		 c=Integer.parseInt(x.substring(len+1, x.length()));
	    		 if(f1<0){                                         //�жϴ������Ƿ���������ʽ��ͬ
	    			 a=f1*c-a;
	    		 }else{
	    			 a=f1*c+a;
	    		 }
	    		 
	    		 b=Integer.parseInt(y);
	    	 }
	    	 
	    	 if(flag1==true&&flag2==true){                          //����4��xΪ������yΪ����
	    		 int lenf1=x.indexOf("'");                          //�ж��Ƿ��������������ȡ��������
	    		 if(lenf1!=-1){
	    			 f1=Integer.parseInt(x.substring(0,lenf1));
	    		 }
	    		 int len1=x.indexOf("/");                           //��ȡ�ֺ�ǰ�������
	    		 a=Integer.parseInt(x.substring(lenf1+1, len1));
	    		 c=Integer.parseInt(x.substring(len1+1, x.length()));
	    		 if(f1<0){                                          //�жϴ������Ƿ���������ʽ��ͬ
	    			 a=f1*c-a;
	    		 }else{
	    			 a=f1*c+a;
	    		 }
	    		 
	    		 int lenf2=y.indexOf("'");                          //�ж��Ƿ��������������ȡ��������
	    		 if(lenf2!=-1){
	    			 f2=Integer.parseInt(y.substring(0,lenf2));
	    		 }
	    		 int len2=y.indexOf("/");                           //��ȡ�ֺ�ǰ�������
	    		 b=Integer.parseInt(y.substring(lenf2+1, len2));
	    		 d=Integer.parseInt(y.substring(len2+1, y.length()));
	    		 if(f2<0){                                          //�жϴ������Ƿ���������ʽ��ͬ
	    			 b=f2*d-b;
	    		 }else{
	    			 b=f2*d+b;
	    		 }
	    	 }
	    	 
	    	 switch(ch){                                    //ʹ��������ȡ���ĸ������������㲢Լ��
	    	 	case "+":
	    	 		rus=Dating(a*d+c*b, c*d);
	    	 		break;
	    	 	case "-":
	    	 		rus=Dating(a*d-c*b, c*d);
	    	 		break;
	    	 	case "*":
	    	 		rus=Dating(a*b, c*d);
	    	 		break;
	    	 	case "��":
	    	 		if(c*b==0){
	    	 			rus="�޽�";
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
	 	
	 	public static List checkAnswer(){                             //�Ƚϴ�
	 		File file1 = new File("D:\\hahahahha\\Exercises.txt");
	 		File file2 = new File("D:\\hahahahha\\Answers.txt");
	 		
	 		List<String> find=new ArrayList<String>();         //���ڴ�ȡ�ظ����ʽ����Ϣ
	 		try{                                               //����Ŀ�ʹ�ɨ�����list����
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
	 		
	 		for(int i=0;i<ans.size()-1;i++){                    //ð�����������ͬ�𰸣��ٽ�����һ���ж�
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
	 	
	 	public static boolean checkExp(String exp1,String exp2){       //��׺ת��׺��ͨ���Ƚ������ʽ�ַ��ж�

	 		exp1=infixToSuffix(exp1);
	 		exp2=infixToSuffix(exp2);		
	 		
	 		String[] strings = exp1.split(" ");
	 		
	 		for(int i=0;i<strings.length;i++){                 
	 			if(exp2.indexOf(strings[i])==-1)
	 				return false;
	 		}
	 		return true;               
	 	}

	 	public static void FileW(File file,String s){                   //�ļ�д�뺯��
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
	 	
	 	public static void FileC(File file1,File file2,File file3){                 //�Ƚ������𰸵��ļ��������д����һ���ļ�
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
	 	public static void main(String[] args)throws IOException {//������
		    Scanner in = new Scanner(System.in);		
			System.out.println("�������ɸ���:");
			int n = in.nextInt();
			System.out.println("���������Χ:");
			int m = in.nextInt();                    
			op1(m);                            //����op(1),op(2),op(3)����
			op2(m);
			op3(m);
			Random r = new Random();
			String str[]=new String[n];
			String ans[]=new String[n];
			start:                                   //forѭ��������1����2����3��������ı��ʽ����������str[n-1]��
		        for(int x=0;x<n;) 
		        {
		        	
		        	int i = r.nextInt(3) + 1;
		            if(i == 1)                     //�����������
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
		           
			
				    String s = "";                                 //���ú�׺����ó��Ĵ𰸴�������ans[n-1]��
				    s=infixToSuffix(str[x-1]);
				    ans[x-1]=suffixToArithmetic(s);
				    int size1=ans[x-1].indexOf("'");               //-1˵�����Ǵ�����
				    int size2=ans[x-1].indexOf("-");
		            if(size1!=-1||size2!=-1)//���Ǹ�������������������������
					    {
						    x--; continue start;
						
					    }
		            boolean flag=false;                             //�ж��Ƿ�Ϊ����
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
		            if(a>m||b>m)                                  //�����ӷ�ĸ����m���������������
		            {             
		                x--;  continue start;
		            }
			}
			
			File file1=new File("D:\\hahahahha\\Exercises.txt");//������str[n-1]��ans[n-1]�е���Ŀ
			File file2=new File("D:\\hahahahha\\Answers.txt");//�ʹ𰸷ֱ�д��Exercises.txt��Answers.txt
			File file3 = new File("D:\\hahahahha\\MyAnswers.txt");
			File file4 = new File("D:\\hahahahha\\Grade.txt");
			
			for(int i=0;i<n;i++){
				String fstr;
				fstr = i+1+"."+str[i]+"\r\n";            
				FileW(file1, fstr);            //���ʽд���ĵ�
				fstr=i+1+"." + ans[i]+"\r\n";
				FileW(file2, fstr);		//��д���ĵ�	
				
			
			}

			
	        System.out.println("������ָ�");
	        while(in.hasNext()){
				switch(in.next()){
					case "go":
						FileC(file2, file3, file4);        //�𰸺������ĵ��Աȣ����д��Grade�ĵ�
						break;
					case "end":
						break;	
				}
				break;
			}		
		}
}






