package BD;

import java.util.Objects;

public class Produto {
    private String prodNome;
    private String prodCodigo;
    private String prodAplic;
    private int prodQtd;
    
    
    public Produto(String nome, String codigo, String aplic, int qtd) throws Exception {
        setNome(nome);
        setCodigo(codigo);
        setAplicacao(aplic);
        setQuantidade(qtd);
    }
    
    /**
     Incrementa ou decrementa o parâmetro na quantidade do produto.
     * @param quantidade Quantidade a ser incrementada ou decrementada
     * @param operacao Operacao a ser realizada (adicao = positivo ou subtracao = negativo)
     * @throws java.lang.Exception Impede que a quantidade do produto fique negativa
     */
    public void alterarQuantidade(int quantidade, int operacao) throws Exception{
        if(operacao < 0 && this.prodQtd < quantidade)
            throw new Exception ("quantidade a ser decrementada invalida");
        
        setQuantidade(this.prodQtd + quantidade);
    }
    
    public void setNome (String nome) throws Exception {
        if(nome.equals(""))
            throw new Exception ("nome em branco");
        
        this.prodNome = nome;
    }
    
    public void setCodigo (String codigo) throws Exception {
        if(codigo.equals(""))
            throw new Exception ("codigo em branco");
        
        this.prodCodigo = codigo;
    }
    
    public void setAplicacao (String aplicacao) throws Exception {
        if (aplicacao.equals(""))
            throw new Exception ("aplicacao em branco");
        
        this.prodAplic = aplicacao;
    }
    
    public void setQuantidade (int quantidade) throws Exception {
        if (quantidade < 0)
            throw new Exception ("quantidade invalida: negativa");
        
        this.prodQtd = quantidade;
    }
    
    public String getNome () {
        return this.prodNome;
    }
    
    public String getCodigo () {
        return this.prodCodigo;
    }
    
    public String getAplicacao () {
        return this.prodAplic;
    }
    
    public int getQuantidade () {
        return this.prodQtd;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.prodNome);
        hash = 17 * hash + Objects.hashCode(this.prodCodigo);
        hash = 17 * hash + Objects.hashCode(this.prodAplic);
        hash = 17 * hash + this.prodQtd;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (this.prodQtd != other.prodQtd) {
            return false;
        }
        if (!Objects.equals(this.prodNome, other.prodNome)) {
            return false;
        }
        if (!Objects.equals(this.prodCodigo, other.prodCodigo)) {
            return false;
        }
        if (!Objects.equals(this.prodAplic, other.prodAplic)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produto{" + "prodNome=" + prodNome + ", prodCodigo=" + prodCodigo + ", prodAplic=" + prodAplic + ", prodQtd=" + prodQtd + '}';
    }
    
}
