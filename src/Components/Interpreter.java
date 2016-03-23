package Components;

public class Interpreter {
	static int PC;
	static int AC;
	static int instr;
	static int instr_type;
	static int data_loc;
	static int data;
	static int memory[];
	static boolean run_bit = true;

	static final int SUM = 0;
	static final int SUB = 1;
	static final int MULT = 2;
	static final int LOAD = 3;
	static final int CLEAN = 4;
	static final int NONE = -1;
	
	public static void interpret(int memory[], int starting_adress) {
		run_bit = true;
		PC = starting_adress;
		AC = 0; data_loc = -1;
		Interpreter.memory = memory;
		while(run_bit) {
			instr = Interpreter.memory[PC];
			PC++;
			instr_type = get_instr_type(instr);
			data_loc = find_data(instr, instr_type);
			if(data_loc >= 0) data = Interpreter.memory[data_loc];
			execute(instr_type,data);
		}
	}
	private static int get_instr_type(int addr) { 
		switch (addr) {
		case 0:
			return SUM;
		case 1:
			return SUB;
		case 2:
			return MULT;
		case 3:
			return LOAD;
		case 4:
			return CLEAN;
		default:
			return NONE;
		}
	}
	private static int find_data(int instr, int type) { 
		if(type == NONE) return -1;
		else  if(type == CLEAN) return data_loc;
		else return 1+data_loc;
	}
	private static void execute(int type, int data) {
		switch (type) {
		case SUM:
			AC += data;
			break;
		case SUB:
			AC -= data;
			break;
		case MULT:
			AC *= data;
			break;
		case LOAD:
			AC = data;
			break;
		case CLEAN:
			AC = 0;
			break;
		default:
			run_bit = false;
			System.out.println("Resultado :" + AC);
			break;
		}
//		if(run_bit)
//			System.out.println("ACUMULADOR AC= " + AC + "\n PC = " + PC + "\n INSTR = " + instr + "\n Data = " + data);
	}
}
