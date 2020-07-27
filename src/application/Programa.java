/*Fazer um programa para ler um conjunto de produtos a partir de um
arquivo em formato .csv (suponha que exista pelo menos um produto).
Em seguida mostrar o preço médio dos produtos. Depois, mostrar os
nomes, em ordem decre*/
package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entidades.Produto;

public class Programa {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite o caminho completo do arquivo");
		String pacote = sc.nextLine();
		
		try(BufferedReader br = new BufferedReader(new FileReader(pacote))){
			
			List<Produto> lista = new ArrayList<>();
			
			String linha = br.readLine();
			while(linha != null) {
				String[] campo = linha.split(",");
				lista.add(new Produto(campo[0], Double.parseDouble(campo[1])));
				linha = br.readLine();
			}
			
			double media = lista.stream().map(p -> p.getPreco()).reduce(0.0, (x, y) -> x + y) / lista.size();
			System.out.println("Preço médio: " + String.format("%.2f", media));
			
			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			
			List<String> nomes = lista.stream().filter(p -> p.getPreco() < media).map(p -> p.getNome()).sorted(comp.reversed()).collect(Collectors.toList());
			
			nomes.forEach(System.out::println);
			
		}catch(IOException e) {
			System.out.println("Erro:  " + e.getMessage());
		}
		
		sc.close();

	}

}
