import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {
        

        // fazer uma conexão HTTP e buscar os dados do site;
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url); // transformando a url em uma versão "mais simples";
        HttpClient client = HttpClient.newHttpClient(); // declarando o cliente que vai mandar a requisição;
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build(); //declarando a variável que irá solicitar a requisição ao cliente;
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString()); //declarando o valor recebido a variável response;
        String body = response.body();
        //System.out.println(body); //imprimindo a resposta na varíavel body que foi declarada


        // extrair os dados que interessam (título,pôster e nota);
        var parser = new JsonParser();
        List<Map<String, String >> ListaDeFilmes = parser.parse(body);
        System.out.println(ListaDeFilmes.size()); // exibindo a quantidade de itens da lista buscados com o regex

        // exibir e manipular os dados;
        for (Map<String,String> filme : ListaDeFilmes) {
            System.out.println("Título: "+filme.get("title"));
            System.out.println("Pôster: "+filme.get("image"));
            System.out.println("\u001b[37m \u001b[44mClassificação: \u001b[0m"+filme.get("imDbRating"));
            
            double classificacao = Double.parseDouble(filme.get("imDbRating"));

            int estrela = (int) classificacao;

            for (int i = 1 ;i <= estrela;i++){
                System.out.print("⭐");
            }

            System.out.print("\n");
        }

    }
}
