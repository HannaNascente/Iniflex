package com.iniflex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {
    private static List<Funcionario> funcionarios = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("Menu:");
            System.out.println("1. Inserir todos os funcionários");
            System.out.println("2. Remover o funcionário “João” da lista");
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
                    inserirFuncionariosDoArquivo("/funcionarios.csv");
                    break;
                case 2:
                    removerFuncionarioJoao();
                    break;  
                case 3:
                    imprimirFuncionarios();
                    break;  
                case 4:
                    aumentarSalario();
                    break;
                case 5:
                    imprimirFuncionariosAgrupadosPorFuncao();
                    break;
                case 6:
                    imprimirFuncionariosAniversariantes();
                    break;
                case 7:
                    imprimirFuncionarioMaisVelho();
                    break;
                case 8:
                    imprimirFuncionariosPorOrdemAlfabetica();
                    break;
                case 9:
                    imprimirTotalSalarios();
                    break;
                case 10:
                    imprimirSalariosMinimos();
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

    private static void inserirFuncionariosDoArquivo(String filePath) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(App.class.getResourceAsStream(filePath), "UTF-8"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String nome = data[0];
                LocalDate dataNascimento = LocalDate.parse(data[1], dateFormat);
                BigDecimal salario = new BigDecimal(data[2]);
                String funcao = data[3];
                funcionarios.add(new Funcionario(nome, dataNascimento, salario, funcao));
            }
            System.out.println("Funcionários inseridos com sucesso!");
        } catch (IOException | RuntimeException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    private static boolean validarExistenciaFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário encontrado.");
            return false;
        }
        return true;
    }

    private static void removerFuncionarioJoao() {
        if (!validarExistenciaFuncionarios()) {
            return;
        }

        Iterator<Funcionario> iterator = funcionarios.iterator();
        while (iterator.hasNext()) {
            Funcionario funcionario = iterator.next();
            if (funcionario.getNome().equalsIgnoreCase("João")) {
                iterator.remove();
                System.out.println("Funcionário João removido com sucesso!");
                return;
            }
        }
        System.out.println("Funcionário João não encontrado.");
    }

    private static void imprimirFuncionarios() {
        if (!validarExistenciaFuncionarios()) {
            return;
        }
        
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));
        for (Funcionario funcionario : funcionarios) {
            String dataNascimento = funcionario.getDataNascimento().format(dateFormat);
            String salario = numberFormat.format(funcionario.getSalario());
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Data de Nascimento: " + dataNascimento);
            System.out.println("Salário: " + salario);
            System.out.println("Função: " + funcionario.getFuncao());
            System.out.println("-----------------------------");
        }
    }

    private static void aumentarSalario() {
        if (!validarExistenciaFuncionarios()) {
            return;
        }

        for (Funcionario funcionario : funcionarios) {
            BigDecimal salarioAtual = funcionario.getSalario();
            BigDecimal aumento = salarioAtual.multiply(BigDecimal.valueOf(0.10));
            BigDecimal novoSalario = salarioAtual.add(aumento);
            funcionario.setSalario(novoSalario);
        }
        System.out.println("Salários aumentados em 10% com sucesso!");
    }

    private static void imprimirFuncionariosAgrupadosPorFuncao() {
        if (!validarExistenciaFuncionarios()) {
            return;
        }

        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
            .collect(Collectors.groupingBy(Funcionario::getFuncao));

        funcionariosPorFuncao.forEach((funcao, listaFuncionarios) -> {
            System.out.println("Função: " + funcao);
            listaFuncionarios.forEach(funcionario -> {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));
                String dataNascimento = funcionario.getDataNascimento().format(dateFormat);
                String salario = numberFormat.format(funcionario.getSalario());
                System.out.println("  Nome: " + funcionario.getNome());
                System.out.println("  Data de Nascimento: " + dataNascimento);
                System.out.println("  Salário: " + salario);
                System.out.println("  Função: " + funcionario.getFuncao());
                System.out.println("  -----------------------------");
            });
        });
    }

    private static void imprimirFuncionariosAniversariantes() {
        if (!validarExistenciaFuncionarios()) {
            return;
        }

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));
        List<Funcionario> aniversariantes = funcionarios.stream()
            .filter(funcionario -> {
                int mesNascimento = funcionario.getDataNascimento().getMonthValue();
                return mesNascimento == 10 || mesNascimento == 12;
            })
            .collect(Collectors.toList());

        if (aniversariantes.isEmpty()) {
            System.out.println("Nenhum funcionário faz aniversário nos meses 10 e 12.");
        } else {
            System.out.println("Funcionários que fazem aniversário nos meses 10 e 12:");
            for (Funcionario funcionario : aniversariantes) {
                String dataNascimento = funcionario.getDataNascimento().format(dateFormat);
                String salario = numberFormat.format(funcionario.getSalario());
                System.out.println("Nome: " + funcionario.getNome());
                System.out.println("Data de Nascimento: " + dataNascimento);
                System.out.println("Salário: " + salario);
                System.out.println("Função: " + funcionario.getFuncao());
                System.out.println("-----------------------------");
            }
        }
    }

    private static void imprimirFuncionarioMaisVelho() {
        if (!validarExistenciaFuncionarios()) {
            return;
        }

        Funcionario funcionarioMaisVelho = funcionarios.stream()
            .min(Comparator.comparing(Funcionario::getDataNascimento))
            .orElse(null);

        if (funcionarioMaisVelho != null) {
            LocalDate hoje = LocalDate.now();
            Period idade = Period.between(funcionarioMaisVelho.getDataNascimento(), hoje);
            System.out.println("Funcionário com a maior idade:");
            System.out.println("Nome: " + funcionarioMaisVelho.getNome());
            System.out.println("Idade: " + idade.getYears() + " anos");
            System.out.println("-----------------------------");
        } else {
            System.out.println("Nenhum funcionário encontrado.");
        }
    }   
    
    private static void imprimirFuncionariosPorOrdemAlfabetica() {
        if (!validarExistenciaFuncionarios()) {
            return;
        }

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));
        List<Funcionario> funcionariosOrdenados = funcionarios.stream()
            .sorted(Comparator.comparing(Funcionario::getNome))
            .collect(Collectors.toList());

        System.out.println("Funcionários em ordem alfabética:");
        for (Funcionario funcionario : funcionariosOrdenados) {
            String dataNascimento = funcionario.getDataNascimento().format(dateFormat);
            String salario = numberFormat.format(funcionario.getSalario());
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Data de Nascimento: " + dataNascimento);
            System.out.println("Salário: " + salario);
            System.out.println("Função: " + funcionario.getFuncao());
            System.out.println("-----------------------------");
        }
    }

    private static void imprimirTotalSalarios() {
        if (!validarExistenciaFuncionarios()) {
            return;
        }

        BigDecimal totalSalarios = funcionarios.stream()
            .map(Funcionario::getSalario)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));
        String totalSalariosFormatado = numberFormat.format(totalSalarios);

        System.out.println("Total dos salários dos funcionários: " + totalSalariosFormatado);
        System.out.println("----------------------------------------------");
    }

    private static void imprimirSalariosMinimos() {
        if (!validarExistenciaFuncionarios()) {
            return;
        }

        BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(SALARIO_MINIMO, 2, BigDecimal.ROUND_HALF_UP);
            String salariosMinimosFormatado = numberFormat.format(salariosMinimos);
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Salários Mínimos: " + salariosMinimosFormatado);
            System.out.println("-----------------------------");
        }
    }
}