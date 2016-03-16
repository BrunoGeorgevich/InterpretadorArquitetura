import Components.Interpreter;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Interpreter it = new Interpreter();
		int memory[] = {1,2,4,0,2,1,-1};
		it.interpret(memory, 3);
	}
}
