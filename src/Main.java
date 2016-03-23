import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Components.Interpreter;

public class Main {
	public static ArrayList<Integer> ops = new ArrayList<>();
	public static ArrayList<Integer> data = new ArrayList<>();
	public static Interpreter it = new Interpreter();
	public static boolean exitCode = true;
	public static void printMenu() {
		System.out.println("\nComandos:");
		System.out.println("\t1 - Inserir comando;");
		System.out.println("\t2 - Executar comandos empilhados;");
		System.out.println("\t0 - Sair do programa;");
	}
	public static void printOperatorMenu() {
		System.out.println("\nOperações:");
		System.out.println("\t 0 - Soma;");
		System.out.println("\t 1 - Subtração;");
		System.out.println("\t 2 - Multiplicação;");
		System.out.println("\t 3 - Load;");
		System.out.println("\t 4 - Clean;");
		System.out.println("\t-1 - Voltar;");
	}
	public static int[] convertIntegers(ArrayList<Integer> integers)
	{
	    int[] ret = new int[integers.size()];
	    for (int i=0; i < ret.length; i++)
	    {
	        ret[i] = integers.get(i).intValue();
	    }
	    return ret;
	}
	public static void interpretAnswer(int answer) {
		Scanner reader = new Scanner(System.in);
		switch (answer) {
		case 0:
			exitCode = false;
			break;
		case 1:
			printOperatorMenu();
			addOperation(reader.nextInt());
			break;
		case 2:
			int dataSize = data.size();
			data.addAll(ops);
			data.add(-1);
			it.interpret(convertIntegers(data), dataSize);
			data = new ArrayList<>();
			ops = new ArrayList<>();
			break;
		default:
			System.out.println("Operador inválido!");
			return;
		}
	}
	public static void addOperation(int operator) {
		int param, op;
		boolean haveParam;
		String opName;
		if(operator == -1) {
			System.out.println("Retornando ao menu principal");
			return;
		}
		switch (operator) {
		case 0:
			opName = "Soma"; op = 0; haveParam = true;
			break;
		case 1:
			opName = "Subtração"; op = 1; haveParam = true;
			break;
		case 2:
			opName = "Multiplicação"; op = 2; haveParam = true;
			break;
		case 3:
			opName = "Load"; op = 3; haveParam = true;
			break;
		case 4:
			opName = "Clean"; op = 4; haveParam = false;
			break;
		default:
			System.out.println("Operador inválido!");
			return;
		}
		System.out.println("A operação selecionada foi : " + opName);
		if(haveParam) {
			System.out.println("Digite o parâmetro desejado");
			Scanner reader = new Scanner(System.in);
			try {
				param = reader.nextInt();	
				ops.add(op); data.add(param);			
			} catch (InputMismatchException e) {
				System.out.println("Por favor, insira um caractér válido!");
				return;
			}
		} else {
			System.out.println("Não exige parâmetros");
			ops.add(op);
		}
	}
	public static void main(String[] args) {
		while(exitCode) {
			printMenu();
			Scanner reader = new Scanner(System.in);
			try {
				interpretAnswer(reader.nextInt());				
			} catch (InputMismatchException e) {
		        System.out.flush();
				System.out.println("Por favor, insira um caractér válido!");
			}
		}
	}
}