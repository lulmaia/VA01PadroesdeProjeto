import java.util.ArrayList;
import java.util.List;

class Livro {
    private String titulo;
    private String autor;
    private int anoPublicacao;

    public Livro(String titulo, String autor, int anoPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void exibirInformacoes() {
        System.out.println("Título: " + titulo + ", Autor: " + autor + ", Ano de Publicação: " + anoPublicacao);
    }
}

class Usuario {
    private String nome;
    private List<Livro> livrosEmprestados;

    public Usuario(String nome) {
        this.nome = nome;
        this.livrosEmprestados = new ArrayList<>();
    }

    public void emprestarLivro(Biblioteca biblioteca, Livro livro) {
        if (biblioteca.removerLivro(livro)) {
            livrosEmprestados.add(livro);
            System.out.println(nome + " emprestou o livro: " + livro.getTitulo());
        } else {
            System.out.println("Livro não disponível para empréstimo.");
        }
    }

    public void devolverLivro(Biblioteca biblioteca, Livro livro) {
        if (livrosEmprestados.remove(livro)) {
            biblioteca.adicionarLivro(livro);
            System.out.println(nome + " devolveu o livro: " + livro.getTitulo());
        } else {
            System.out.println("Este livro não está na lista de livros emprestados.");
        }
    }

    public void exibirLivrosEmprestados() {
        System.out.println("Livros emprestados por " + nome + ":");
        if (livrosEmprestados.isEmpty()) {
            System.out.println("Nenhum livro emprestado.");
        } else {
            for (Livro livro : livrosEmprestados) {
                livro.exibirInformacoes();
            }
        }
    }
}

class Biblioteca {
    private String nome;
    private List<Livro> livrosDisponiveis;

    public Biblioteca(String nome) {
        this.nome = nome;
        this.livrosDisponiveis = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        livrosDisponiveis.add(livro);
        System.out.println("Livro adicionado: " + livro.getTitulo());
    }

    public boolean removerLivro(Livro livro) {
        return livrosDisponiveis.remove(livro);
    }

    public void exibirLivrosDisponiveis() {
        System.out.println("Livros disponíveis na " + nome + ":");
        if (livrosDisponiveis.isEmpty()) {
            System.out.println("Nenhum livro disponível.");
        } else {
            for (Livro livro : livrosDisponiveis) {
                livro.exibirInformacoes();
            }
        }
    }
}

public class SistemaBiblioteca {
    public static void main(String[] args) {
        Livro livro1 = new Livro("1984", "George Orwell", 1949);
        Livro livro2 = new Livro("Dom Quixote", "Miguel de Cervantes", 1605);
        Usuario usuario1 = new Usuario("Ana");
        Biblioteca biblioteca = new Biblioteca("Biblioteca Central");
        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);
        biblioteca.exibirLivrosDisponiveis();
        usuario1.emprestarLivro(biblioteca, livro1);
        usuario1.exibirLivrosEmprestados();
        biblioteca.exibirLivrosDisponiveis();
        usuario1.devolverLivro(biblioteca, livro1);
        biblioteca.exibirLivrosDisponiveis();
    }
}
