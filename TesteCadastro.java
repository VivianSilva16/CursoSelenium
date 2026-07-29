// Define o pacote onde a classe TesteCadastro está localizada
package br.ce.wcaquino.test;

// Importa o método getDriver() de forma estática,
// permitindo chamar getDriver() diretamente sem escrever DriverFactory.getDriver()
import static br.ce.wcaquino.core.DriverFactory.getDriver;

// Importa a classe Assert do JUnit, usada para fazer as validações dos resultados
import org.junit.Assert;

// Importa a anotação @Before, usada para executar um método antes de cada teste
import org.junit.Before;

// Importa a anotação @Test, usada para identificar um método como um teste
import org.junit.Test;

// Importa a classe BaseTest, que provavelmente contém configurações
// comuns para os testes automatizados
import br.ce.wcaquino.core.BaseTest;

// Importa a Page Object responsável por interagir com a página
// de campos de treinamento
import br.ce.wcaquino.page.CampoTreinamentoPage;


// Declara a classe de teste TesteCadastro
// "extends BaseTest" significa que essa classe herda as funcionalidades
// existentes na classe BaseTest
public class TesteCadastro extends BaseTest {
	
	// Declara uma variável do tipo CampoTreinamentoPage.
	// Essa variável será usada para acessar os elementos e métodos
	// disponíveis na página que está sendo testada
	private CampoTreinamentoPage page;

	
	// A anotação @Before indica que o método abaixo será executado
	// automaticamente antes de cada método de teste (@Test)
	@Before
	public void inicializa(){
		
		// Abre o arquivo componentes.html no navegador.
		// "file:///" indica que estamos acessando um arquivo local.
		//
		// System.getProperty("user.dir") retorna o diretório raiz
		// do projeto que está sendo executado.
		//
		// O operador + é usado para juntar os caminhos:
		// diretório do projeto + caminho até o arquivo HTML
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		// Cria uma nova instância da classe CampoTreinamentoPage.
		// Essa classe representa a página que será utilizada durante o teste.
		// O objeto criado é armazenado na variável "page".
		page = new CampoTreinamentoPage();
	}

	
	// A anotação @Test indica que o método abaixo é um teste automatizado.
	// O objetivo desse teste é verificar se o cadastro é realizado com sucesso.
	@Test
	public void deveRealizarCadastroComSucesso(){
		
		// Preenche o campo Nome com o valor "Wagner"
		page.setNome("Wagner");
		
		// Preenche o campo Sobrenome com o valor "Costa"
		page.setSobrenome("Costa");
		
		// Seleciona a opção de sexo Masculino
		page.setSexoMasculino();
		
		// Seleciona a opção de comida Pizza
		page.setComidaPizza();
		
		// Seleciona a opção de escolaridade "Mestrado"
		page.setEscolaridade("Mestrado");
		
		// Seleciona o esporte "Natação"
		page.setEsporte("Natacao");
		
		// Clica no botão responsável por realizar o cadastro
		page.cadastrar();
		
		
		// Verifica se a mensagem apresentada após o cadastro
		// é exatamente igual a "Cadastrado!"
		//
		// O primeiro parâmetro é o valor esperado
		// O segundo parâmetro é o valor encontrado na aplicação
		Assert.assertEquals("Cadastrado!", page.obterResultadoCadastro());
		
		
		// Verifica se o nome cadastrado é "Wagner"
		Assert.assertEquals("Wagner", page.obterNomeCadastro());
		
		
		// Verifica se o sobrenome cadastrado é "Costa"
		Assert.assertEquals("Costa", page.obterSobrenomeCadastro());
		
		
		// Verifica se o sexo cadastrado é "Masculino"
		Assert.assertEquals("Masculino", page.obterSexoCadastro());
		
		
		// Verifica se a comida selecionada foi "Pizza"
		Assert.assertEquals("Pizza", page.obterComidaCadastro());
		
		
		// Verifica se a escolaridade cadastrada foi "mestrado"
		Assert.assertEquals("mestrado", page.obterEscolaridadeCadastro());
		
		
		// Verifica se o esporte cadastrado foi "Natacao"
		Assert.assertEquals("Natacao", page.obterEsportesCadastro());
	}
}
