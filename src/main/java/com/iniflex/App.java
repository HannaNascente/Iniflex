package com.iniflex;

import com.iniflex.service.FuncionarioService;
import com.iniflex.service.FuncionarioServiceImpl;

import java.math.BigDecimal;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FuncionarioService funcionarioService = new FuncionarioServiceImpl();
        int option;

        do {
            System.out.println("Menu:");
            System.out.println("1. Inserir todos os funcionários");
            System.out.println("2. Remover o funcionário \"João\" da lista");
            System.out.println("3. Imprimir todos os funcionários");
            System.out.println("4. Aumentar salário dos funcionários em 10%");
            System.out.println("5. Imprimir funcionários agrupados por função");
            System.out.println("6. Imprimir funcionários que fazem aniversário nos meses 10 e 12");
            System.out.println("7. Imprimir o funcionário com a maior idade");
            System.out.println("8. Imprimir funcionários por ordem alfabética");
            System.out.println("9. Imprimir o total dos salários dos funcionários");
            System.out.println("10. Imprimir quantos salários mínimos ganha cada funcionário");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    funcionarioService.inserirFuncionariosDoArquivo("/funcionarios.csv");
                    break;
                case 2:
                    funcionarioService.removerFuncionarioPorNome("João");
                    break;
                case 3:
                    funcionarioService.imprimirFuncionarios();
                    break;
                case 4:
                    funcionarioService.aumentarSalario(BigDecimal.valueOf(0.10));
                    break;
                case 5:
                    funcionarioService.imprimirFuncionariosAgrupadosPorFuncao();
                    break;
                case 6:
                    funcionarioService.imprimirFuncionariosAniversariantes(10, 12);
                    break;
                case 7:
                    funcionarioService.imprimirFuncionarioMaisVelho();
                    break;
                case 8:
                    funcionarioService.imprimirFuncionariosPorOrdemAlfabetica();
                    break;
                case 9:
                    funcionarioService.imprimirTotalSalarios();
                    break;
                case 10:
                    funcionarioService.imprimirSalariosMinimos();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 0);

        scanner.close();
    }
}