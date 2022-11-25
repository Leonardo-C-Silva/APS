package APS;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class APS_N1_FloydWarshall {
	
	public void grafoInput(int distancia[][], int menorDistancia[][], int V) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite as distancia para geracao da Matriz Distancias para o grafo: [digite 99 caso a distancia seja infinita]");
		System.out.println();

		for(int i = 0; i < V; i++) {
			for(int j = 0; j < V; j++) {
				
				if(i == j) {
					distancia[i][j] = 0;
					}
				else {
					System.out.println("Digite a distancia do Vertice "+(i+1)+" ate o Vertice "+(j+1));
					distancia[i][j] = sc.nextInt();
					System.out.println();

				}
				
				menorDistancia[i][j] = distancia[i][j];
				
			}
		}
	}
	
	public void imprimeMatriz(int distancia[][], int V) {
	
		for(int i = 0; i < V; i++) {
			for(int j = 0; j < V; j++) {
	
	//substituindo 99 por i - infinito

	if(distancia[i][j] == 99)
		System.out.print("i ");
	else 
	System.out.print(distancia[i][j]+" ");
			}
	System.out.println();
		}
	}
	
	//usando o algoritimo floyd-warshall
	public void calculaCaminhoMaisCurto (int menorDistancia[][], int V) {
		//Considera cada vertice como um vertice intermediario e encontra o caminho mais curto incluindo o vert intermediario

		//interVert  representa o vertice intermediario
		for (int interVert = 0; interVert < V; interVert++) {
		//interendo os vertices origem 
			for (int i = 0; i < V; i++) {
		//interando vertices destinos para cada vertice origem
				for (int j = 0; j < V; j++) {
		
		//Ai se interVert esta no caminho masi curto, atualizamos o mesmo

					if (menorDistancia[i][interVert] + menorDistancia [interVert][j] < menorDistancia[i][j]) {
						menorDistancia[i][j] = menorDistancia[i][interVert] + menorDistancia[interVert][j];
					}
		
				}
			}
		}
	}
		
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\leonardo\\Downloads\\data (1).txt"));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    int count = 0;
		    int V = 0;

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		        count++;
		        if(count == 1) {
		        	V = Integer.parseInt(line);
		        }
		    }
		    String doc = sb.toString();
		    
			System.out.println("Conferindo documento: " + doc);
			System.out.println();
		
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Lendo numero de vertices: " + V);
		System.out.println();

		//Armazenando numero de vertices
		
		int distancia[][] = new int[V][V];
		int menorDistancia[][] = new int[V][V];
		
		APS_N1_FloydWarshall fw = new APS_N1_FloydWarshall();
		
		fw.grafoInput(distancia, menorDistancia, V); 
		System.out.println("Matriz Distancias: [i -> infinito]");
		
		System.out.println();

		fw.imprimeMatriz(distancia, V);
		fw.calculaCaminhoMaisCurto(menorDistancia, V);
		System.out.println("Matriz de Caminhos Mais Curtos: [i -> infinito]"); fw.imprimeMatriz (menorDistancia, V);
		System.out.println();

		
		boolean r = true;
		
		while (r == true) {
	
		System.out.println("Digite o Vertice Origem: ");
		int lin = (sc.nextInt())-1;
		System.out.println();

		System.out.println("Digite o Vertice Destino: ");
		int col = (sc.nextInt())-1;
		System.out.println();

        System.out.println("A Distancia mais curta é: " + menorDistancia[lin][col]);
		System.out.println();

        System.out.println("Gostaria de pesquisar mais alguam distancia entre Vertices: S ou N");
        String resp = sc.next();
		System.out.println();

        if (resp.contains("N")||resp.contains("n"))
        	r = false;
		}

		} finally {
		    br.close();
		}
	}

}