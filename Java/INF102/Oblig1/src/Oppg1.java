import edu.princeton.cs.algs4.Stack;

public class Oppg1 {
	public static void  main(String args[]){
		
		String p = "1+2=*3+4==";
		Stack<Integer> tall = new Stack<Integer>();
		Stack<String> operator = new Stack<String>();
		String temp = "";
		
		for(int i = 0; i < p.length()-1;i++){
			char tegn = p.charAt(i);

			if (tegn != '+' && tegn != '*' && tegn !='='){
				temp = temp + tegn;
			}
			if(tegn == '+'){
				tall.push(Integer.parseInt(temp));
				operator.push(Character.toString(tegn));
				temp="";
			}
			if(tegn == '*'){

				operator.push(Character.toString(tegn));
				temp="";
			}
			if(tegn == '='){
				tall.push(Integer.parseInt(temp));

				while(!operator.isEmpty()){
					String op = operator.pop();
					int v1 = tall.pop();
					int v2 = tall.pop();

					if(op.equals("+"))
						tall.push(v1+v2);

					if(op.equals("*"))
						tall.push(v1*v2);

				}
			}

		}

		System.out.println(p+" "+tall);

	}
}