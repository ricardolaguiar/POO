import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {

    static ArrayList<Musica> musicas = new ArrayList<>();
    static Usuario usuario = new Usuario("Pedro");

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        adicionarMusicasTeste();

        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);

        System.out.println("\n🎵 Até logo! 🎵");
        scanner.close();
    }

    public static void exibirMenu() {
        System.out.println("\n=== SISTEMA DE STREAMING ===");
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar músicas");
        System.out.println("3. Buscar música");
        System.out.println("4. Criar playlist");
        System.out.println("5. Gerenciar playlists");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }

    public static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void processarOpcao(int opcao) {
        switch(opcao) {
            case 1: cadastrarMusica();
                break;

            case 2: listarMusicas();
                break;

            case 3: buscarPorTitulo();
                break;
            
            case 4: criarPlaylist();
                break;
            
            case 5: gerenciarPlaylists();
                break;            
            
            case 0:
                break;

            default: System.out.println("Opção inválida!");
        }
    }

    public static void cadastrarMusica() {
        System.out.println("\n--- CADASTRAR MÚSICA ---");

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Artista: ");
        String artista = scanner.nextLine();

        System.out.print("Gênero: ");
        String genero = scanner.nextLine();

        System.out.print("Duração (segundos): ");
        int duracao = Integer.parseInt(scanner.nextLine());

        musicas.add(new Musica(titulo, artista, duracao, genero));

        System.out.println("Música cadastrada!");
    }

    public static void listarMusicas() {
        if (musicas.isEmpty()) {
            System.out.println("Nenhuma música cadastrada.");
            return;
        }

        for (int i = 0; i < musicas.size(); i++) {
            System.out.print((i + 1) + ". ");
            musicas.get(i).exibir();
        }
    }

    public static void gerenciarPlaylists() {
    int opcao;

        do {
            System.out.println("\n=== GERENCIAR PLAYLISTS ===");
            System.out.println("1. Listar playlists");
            System.out.println("2. Adicionar música");
            System.out.println("3. Remover música");
            System.out.println("4. Ver detalhes");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

        opcao = lerOpcao();

        switch(opcao) {
            case 1: usuario.listarPlaylists();
                break;
            
            case 2: adicionarMusicaNaPlaylist();
                break;
            
            case 3: removerMusicaDaPlaylist();
                break;
            
            case 4: verDetalhesPlaylist();
                break;
            
            }

        } while (opcao != 0);
    }

    public static void adicionarMusicaNaPlaylist() {
        usuario.listarPlaylists();
        System.out.print("Escolha a playlist: ");
        int pIndex = Integer.parseInt(scanner.nextLine()) - 1;

        Playlist p = usuario.getPlaylist(pIndex);
        if (p == null) return;

        listarMusicas();
        System.out.print("Escolha a música: ");
        int mIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (mIndex >= 0 && mIndex < musicas.size()) {
            p.adicionarMusica(musicas.get(mIndex));
            System.out.println("Música adicionada!");
        }
    }

    public static void removerMusicaDaPlaylist() {
        usuario.listarPlaylists();
        System.out.print("Escolha a playlist: ");
        int pIndex = Integer.parseInt(scanner.nextLine()) - 1;

        Playlist p = usuario.getPlaylist(pIndex);
        if (p == null) return;

        p.listarMusicas();
        System.out.print("Escolha a música para remover: ");
        int mIndex = Integer.parseInt(scanner.nextLine()) - 1;

        p.removerMusica(mIndex);
        System.out.println("Removida!");
    }

    public static void verDetalhesPlaylist() {
        usuario.listarPlaylists();
        System.out.print("Escolha a playlist: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        Playlist p = usuario.getPlaylist(index);
        if (p == null) return;

        System.out.println("\nPlaylist: " + p.nome);
        p.listarMusicas();

        int total = p.getDuracaoTotal();
        System.out.println("Duração total: " + (total / 60) + " min");
    }

    public static void buscarPorTitulo() {
        System.out.print("Digite o título: ");
        String busca = scanner.nextLine();

        boolean encontrou = false;

        for (Musica m : musicas) {
            if (m.contemTitulo(busca)) {
                m.exibir();
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma música encontrada.");
        }
    }

    public static void criarPlaylist() {
        System.out.print("Nome da playlist: ");
        String nome = scanner.nextLine();
        usuario.criarPlaylist(nome);
        System.out.println("Playlist criada!");
    }

    public static void adicionarMusicasTeste() {
        musicas.add(new Musica("Bohemian Rhapsody", "Queen", 354, "Rock"));
        musicas.add(new Musica("Eu Sou 157", "Racionais MC's", 529, "Rap"));
    }
}