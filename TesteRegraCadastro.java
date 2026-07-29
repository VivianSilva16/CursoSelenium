```java
// Define o pacote onde esta classe de teste está localizada.
package br.ce.wcaquino.test;

// Importa de forma estática o método getDriver().
// Assim, podemos utilizar getDriver() diretamente,
// sem precisar escrever DriverFactory.getDriver().
import static br.ce.wcaquino.core.DriverFactory.getDriver;

// Importa a classe Arrays, utilizada para criar listas
// e arrays de forma simplificada.
import java.util.Arrays;

// Importa a interface Collection,
// utilizada para representar uma coleção de objetos.
import java.util.Collection;

// Importa a interface List,
// utilizada para representar uma lista de elementos.
import java.util.List;

// Importa o Assert do JUnit,
// utilizado para realizar as validações do teste.
import org.junit.Assert;

// Importa a anotação @Before,
// utilizada para executar um método antes de cada teste.
import org.junit.Before;

// Importa a anotação @Test,
// utilizada para identificar um método de teste.
import org.junit.Test;

// Importa a anotação @RunWith,
// que permite definir um executor personalizado para os testes.
import org.junit.runner.RunWith;

// Importa o Parameterized do JUnit,
// responsável por executar o mesmo teste várias vezes
// utilizando diferentes conjuntos de dados.
import org.junit.runners.Parameterized;

// Importa a anotação @Parameter,
// utilizada para indicar quais variáveis receberão
// os valores dos dados parametrizados.
import org.junit.runners.Parameterized.Parameter;

// Importa a anotação @Parameters,
// utilizada para identificar o método que fornece
// os dados utilizados nos testes parametrizados.
import org.junit.runners.Parameterized.Parameters;

// Importa a classe BaseTest.
// A classe atual herda dela comportamentos comuns
// utilizados pelos testes.
import br.ce.wcaquino.core.BaseTest;

// Importa a classe DSL,
// responsável por fornecer métodos auxiliares
// para interagir com a página.
import br.ce.wcaquino.core.DSL;

// Importa a classe CampoTreinamentoPage,
// que representa a página que será utilizada no teste.
import br.ce.wcaquino.page.CampoTreinamentoPage;


// Informa ao JUnit que esta classe utilizará
// o executor de testes parametrizados.
//
// Isso significa que o método de teste será executado
// várias vezes, uma vez para cada conjunto de dados
// definido no método getCollection().
@RunWith(Parameterized.class)
public class TesteRegrasCadastro extends BaseTest {


	// Declara uma variável para armazenar uma instância da DSL.
	//
	// A DSL possui métodos auxiliares para facilitar
	// a interação com os elementos da página.
	private DSL dsl;
	
	
	// Declara uma variável que representa
	// a página de Campo de Treinamento.
	//
	// Essa classe segue o padrão Page Object.
	private CampoTreinamentoPage page;
	
	
	// A anotação @Parameter sem valor indica que esta variável
	// receberá o valor que está na posição 0 de cada linha
	// da tabela de dados parametrizados.
	//
	// Neste caso, receberá o valor do campo "nome".
	@Parameter
	public String nome;
	
	
	// Recebe o valor que está na posição 1
	// da tabela de dados.
	//
	// Neste caso, representa o sobrenome.
	@Parameter(value=1)
	public String sobrenome;
	
	
	// Recebe o valor que está na posição 2
	// da tabela de dados.
	//
	// Neste caso, representa o sexo.
	@Parameter(value=2)
	public String sexo;
	
	
	// Recebe o valor que está na posição 3
	// da tabela de dados.
	//
	// A variável é uma List<String> porque pode conter
	// uma ou mais comidas selecionadas.
	@Parameter(value=3)
	public List<String> comidas;
	
	
	// Recebe o valor que está na posição 4
	// da tabela de dados.
	//
	// É um array de String porque o usuário pode
	// selecionar um ou mais esportes.
	@Parameter(value=4)
	public String[] esportes;
	
	
	// Recebe o valor que está na posição 5
	// da tabela de dados.
	//
	// Representa a mensagem de erro que esperamos
	// que o sistema apresente para cada cenário.
	@Parameter(value=5)
	public String msg;
	
	
	// A anotação @Before indica que este método será executado
	// antes de cada execução do teste parametrizado.
	//
	// Como temos 5 conjuntos de dados, esse método será executado
	// antes de cada um dos 5 cenários.
	@Before
	public void inicializa(){
		
		// Abre a página componentes.html no navegador.
		//
		// System.getProperty("user.dir") retorna o diretório
		// principal do projeto.
		getDriver().get(
			"file:///" 
			+ System.getProperty("user.dir") 
			+ "/src/main/resources/componentes.html"
		);
		
		// Cria uma nova instância da classe DSL.
		dsl = new DSL();
		
		// Cria uma nova instância da página CampoTreinamentoPage.
		page = new CampoTreinamentoPage();
	}
	
	
	// A anotação @Parameters indica que este método
	// é responsável por fornecer os dados para os testes.
	//
	// O método precisa ser static e retornar uma Collection
	// contendo os conjuntos de dados.
	@Parameters
	public static Collection<Object[]> getCollection(){
		
		// Arrays.asList() transforma o array de cenários
		// em uma Collection que pode ser utilizada
		// pelo JUnit Parameterized.
		//
		// Cada linha do Object[][] representa
		// uma execução diferente do teste.
		return Arrays.asList(new Object[][] {
			
			
			// CENÁRIO 1
			//
			// Nome vazio.
			// Sobrenome vazio.
			// Sexo não selecionado.
			// Nenhuma comida selecionada.
			// Nenhum esporte selecionado.
			// Mensagem esperada: "Nome eh obrigatorio".
			{"", "", "", Arrays.asList(), new String[]{}, "Nome eh obrigatorio"},
			
			
			// CENÁRIO 2
			//
			// Nome preenchido.
			// Sobrenome vazio.
			// Sexo não selecionado.
			// Nenhuma comida selecionada.
			// Nenhum esporte selecionado.
			// Mensagem esperada: "Sobrenome eh obrigatorio".
			{"Wagner", "", "", Arrays.asList(), new String[]{}, "Sobrenome eh obrigatorio"},
			
			
			// CENÁRIO 3
			//
			// Nome preenchido.
			// Sobrenome preenchido.
			// Sexo não selecionado.
			// Nenhuma comida selecionada.
			// Nenhum esporte selecionado.
			// Mensagem esperada: "Sexo eh obrigatorio".
			{"Wagner", "Costa", "", Arrays.asList(), new String[]{}, "Sexo eh obrigatorio"},
			
			
			// CENÁRIO 4
			//
			// Nome preenchido.
			// Sobrenome preenchido.
			// Sexo masculino selecionado.
			// Carne e Vegetariano selecionados ao mesmo tempo.
			// Nenhum esporte selecionado.
			// Mensagem esperada:
			// "Tem certeza que voce eh vegetariano?"
			{"Wagner", "Costa", "Masculino",
				Arrays.asList("Carne", "Vegetariano"),
				new String[]{},
				"Tem certeza que voce eh vegetariano?"},
			
			
			// CENÁRIO 5
			//
			// Nome preenchido.
			// Sobrenome preenchido.
			// Sexo masculino selecionado.
			// Apenas Carne selecionada.
			// Esportes selecionados: Karate e "O que eh esporte?".
			// Mensagem esperada:
			// "Voce faz esporte ou nao?"
			{"Wagner", "Costa", "Masculino",
				Arrays.asList("Carne"),
				new String[]{"Karate", "O que eh esporte?"},
				"Voce faz esporte ou nao?"}
		});
	}
	
	
	// Identifica o método que será executado como teste.
	//
	// Como a classe utiliza Parameterized.class,
	// este método será executado uma vez para cada
	// conjunto de dados definido no getCollection().
	@Test
	public void deveValidarRegras(){
		
		// Preenche o campo Nome com o valor recebido
		// pelo parâmetro "nome".
		page.setNome(nome);
		
		
		// Preenche o campo Sobrenome com o valor recebido
		// pelo parâmetro "sobrenome".
		page.setSobrenome(sobrenome);
		
		
		// Verifica se o valor recebido para sexo
		// é igual a "Masculino".
		if(sexo.equals("Masculino")) {
			
			// Se for masculino, seleciona a opção Masculino.
			page.setSexoMasculino();
		} 
		
		
		// Verifica se o valor recebido para sexo
		// é igual a "Feminino".
		if(sexo.equals("Feminino")) {
			
			// Se for feminino, seleciona a opção Feminino.
			page.setSexoFeminino();
		}
		
		
		// Verifica se a lista de comidas contém "Carne".
		if(comidas.contains("Carne"))
			
			// Se encontrar "Carne", seleciona essa opção.
			page.setComidaCarne(); 
		
		
		// Verifica se a lista de comidas contém "Pizza".
		if(comidas.contains("Pizza"))
			
			// Se encontrar "Pizza", seleciona essa opção.
			page.setComidaPizza(); 
		
		
		// Verifica se a lista de comidas contém "Vegetariano".
		if(comidas.contains("Vegetariano"))
			
			// Se encontrar "Vegetariano", seleciona essa opção.
			page.setComidaVegetariano(); 
		
		
		// Envia o array de esportes para o método setEsporte().
		//
		// O método setEsporte() utiliza varargs,
		// por isso pode receber diretamente o array esportes.
		//
		// Caso existam vários esportes, todos serão selecionados.
		page.setEsporte(esportes);
		
		
		// Clica no botão Cadastrar.
		page.cadastrar();
		
		
		// Exibe no console a mensagem esperada
		// para o cenário atual.
		System.out.println(msg);
		
		
		// Obtém o texto apresentado pelo alerta,
		// aceita/fecha o alerta e compara o resultado
		// com a mensagem esperada armazenada em "msg".
		//
		// Se os valores forem diferentes,
		// o teste falhará.
		Assert.assertEquals(
			msg, 
			dsl.alertaObterTextoEAceita()
		);
	}
}
```
