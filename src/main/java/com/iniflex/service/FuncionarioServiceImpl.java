package com.iniflex.service;

import com.iniflex.App;
import com.iniflex.model.Funcionario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class FuncionarioServiceImpl implements FuncionarioService {
    private List<Funcionario> funcionarios = new ArrayList<>();

    @Override
    public void inserirFuncionariosDoArquivo(String filePath) {
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

    @Override
    public void removerFuncionarioPorNome(String nome) {
        if (!validarExistenciaFuncionarios()) {
            return;
        }

        boolean removido = funcionarios.removeIf(funcionario -> funcionario.getNome().equalsIgnoreCase(nome));
    
        if (removido) {
            System.out.println("Funcionário " + nome + " removido com sucesso!");
        } else {
            System.out.println("Funcionário " + nome + " não encontrado.");
        }
    }

    @Override
    public void imprimirFuncionarios() {
        if (!validarExistenciaFuncionarios()) {
            return;
        }

        funcionarios.forEach(this::imprimirFuncionario);
    }

    @Override
    public void aumentarSalario(BigDecimal percentual) {
        if (!validarExistenciaFuncionarios()) {
            return;
        }

        funcionarios.forEach(funcionario -> {
            BigDecimal salarioAtual = funcionario.getSalario();
            BigDecimal aumento = salarioAtual.multiply(percentual);
            BigDecimal novoSalario = salarioAtual.add(aumento);
            funcionario.setSalario(novoSalario);
        });

        System.out.println("Salários aumentados em " + percentual.multiply(BigDecimal.valueOf(100)) + "% com sucesso!");
    }

    @Override
    public void imprimirFuncionariosAgrupadosPorFuncao() {
        if (!validarExistenciaFuncionarios()) {
            return;
        }

        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
            .collect(Collectors.groupingBy(Funcionario::getFuncao));

        funcionariosPorFuncao.forEach((funcao, listaFuncionarios) -> {
            System.out.println("Função: " + funcao);
            listaFuncionarios.forEach(this::imprimirFuncionario);
        });
    }

    @Override
    public void imprimirFuncionariosAniversariantes(int... meses) {
        if (!validarExistenciaFuncionarios()) {
            return;
        }

        List<Funcionario> aniversariantes = funcionarios.stream()
        .filter(funcionario -> Arrays.stream(meses)
            .anyMatch(mes -> mes == funcionario.getDataNascimento().getMonthValue()))
        .collect(Collectors.toList());

        if (aniversariantes.isEmpty()) {
            System.out.println("Nenhum funcionário faz aniversário nos meses especificados.");
        } else {
            System.out.println("Funcionários que fazem aniversário nos meses especificados:");
            aniversariantes.forEach(this::imprimirFuncionario);
        }
    }

    @Override
    public void imprimirFuncionarioMaisVelho() {
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

    @Override
    public void imprimirFuncionariosPorOrdemAlfabetica() {
        if (!validarExistenciaFuncionarios()) {
            return;
        }

        List<Funcionario> funcionariosOrdenados = funcionarios.stream()
            .sorted(Comparator.comparing(Funcionario::getNome))
            .collect(Collectors.toList());

        System.out.println("Funcionários em ordem alfabética:");
        funcionariosOrdenados.forEach(this::imprimirFuncionario);
    }

    @Override
    public void imprimirTotalSalarios() {
        if (!validarExistenciaFuncionarios()) {
            return;
        }

        BigDecimal totalSalarios = funcionarios.stream()
            .map(Funcionario::getSalario)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        String totalSalariosFormatado = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(totalSalarios);

        System.out.println("Total dos salários dos funcionários: " + totalSalariosFormatado);
        System.out.println("----------------------------------------------");
    }

    @Override
    public void imprimirSalariosMinimos() {
        if (!validarExistenciaFuncionarios()) {
            return;
        }
        
        BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));
        
        funcionarios.forEach(funcionario -> {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(SALARIO_MINIMO, 2, BigDecimal.ROUND_HALF_UP);
            String salariosMinimosFormatado = numberFormat.format(salariosMinimos);
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Salários Mínimos: " + salariosMinimosFormatado);
            System.out.println("-----------------------------");
        });
    }

    private boolean validarExistenciaFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário encontrado.");
            return false;
        }
        return true;
    }

    private void imprimirFuncionario(Funcionario funcionario) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));
        String dataNascimento = funcionario.getDataNascimento().format(dateFormat);
        String salario = numberFormat.format(funcionario.getSalario());
        System.out.println("Nome: " + funcionario.getNome());
        System.out.println("Data de Nascimento: " + dataNascimento);
        System.out.println("Salário: " + salario);
        System.out.println("Função: " + funcionario.getFuncao());
        System.out.println("-----------------------------");
    }
}