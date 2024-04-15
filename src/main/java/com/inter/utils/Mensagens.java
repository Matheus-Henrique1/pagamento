package com.inter.utils;

public class Mensagens {

    //Mensagens sobre o cliente
    public static final String SUCESSO_CADASTRAR_CLIENTE = "Cliente cadastrado com sucesso! Segue abaixo o número da conta do usuário cadastrado.";
    public static final String ERRO_CADASTRAR_CLIENTE = "Erro ao tentar cadastrar cliente.";
    public static final String CLIENTE_JA_EXISTE = "Em nossos sistemas já existe uma conta com esse CPF cadastrado.";

    //Mensagens de validações
    public static final String NOME_OBRIGATORIO = "O campo nome é obrigatório.";
    public static final String CPF_OBRIGATORIO = "O campo CPF é obrigatório.";
    public static final String CPF_NUMERO_CARACTERES = "O campo CPF deve conter 11 caracteres.";
    public static final String DT_NASCIMENTO_OBRIGATORIO = "O campo data de nascimento é obrigatório.";
    public static final String CODIGO_MES_INVALIDO = "Código do mês inválido";
    public static final String NUMERO_PARCELAS_MENOR_QUE_UM = "O número de parcelas não pode ser menor que 1.";
    public static final String NUMERO_PARCELAS_EXCEDIDAS = "A compra só pode ser parcelada no máximo em até 12 vezes.";

    //Mensagens sobre transações
    public static final String TRANSACAO_SUCESSO = "Transação processada com sucesso. Abaixo id da Fatura!";
    public static final String DATA_TRANSACAO_OBRIGATORIA = "O campo data transação é obrigatório.";
    public static final String NOME_ESTABELECIMENTO_OBRIGATORIO = "O campo data nome estabelecimento é obrigatório.";
    public static final String VALOR_TRANSACAO_OBRIGATORIO = "O campo data valor da transação é obrigatório.";
    public static final String VALOR_PARCELA_OBRIGATORIO = "O campo data valor da parcela é obrigatório.";
    public static final String NUMERO_PARCELAS_OBRIGATORIO = "O campo data numero de parcelas é obrigatório.";
    public static final String NUMERO_CONTA_CORRENTE_OBRIGATORIO = "O campo data numero conta corrente é obrigatório.";
    public static final String DATA_IVALIDA = "A data informada não pode ser maior ou superior a data/mês atual!";

    //Mensagens sobre fatura
    public static final String FATURA_NAO_ENCONTRADA = "Não foi encontrada fatura aberta com o id e conta corrente informados.";

}
