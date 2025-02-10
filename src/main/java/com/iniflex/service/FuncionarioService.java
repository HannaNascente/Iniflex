package com.iniflex.service;

import java.math.BigDecimal;

public interface FuncionarioService {
    void inserirFuncionariosDoArquivo(String filePath);
    void removerFuncionarioPorNome(String nome);
    void imprimirFuncionarios();
    void aumentarSalario(BigDecimal percentual);
    void imprimirFuncionariosAgrupadosPorFuncao();
    void imprimirFuncionariosAniversariantes(int... meses);
    void imprimirFuncionarioMaisVelho();
    void imprimirFuncionariosPorOrdemAlfabetica();
    void imprimirTotalSalarios();
    void imprimirSalariosMinimos();
}
