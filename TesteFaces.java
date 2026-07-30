```java
// Define o pacote onde a classe de teste está localizada.
package br.ce.wcaquino.test;

// Importa de forma estática o método getDriver(),
// permitindo chamá-lo diretamente como getDriver(),
// sem precisar escrever DriverFactory.getDriver().
import static br.ce.wcaquino.core.DriverFactory.getDriver;

// Importa o recurso @After do JUnit,
// utilizado para executar um método depois de cada teste.
import org.junit.After;

// Importa a classe Assert do JUnit,
// utilizada para fazer validações (assertions) nos testes.
import org.junit.Assert;

// Importa o recurso @Before do JUnit,
// utilizado para executar um método antes de cada teste.
import org.junit.Before;

// Importa a anotação @Test,
// que identifica os métodos que são testes automatizados.
import org.junit.Test;

// Importa a classe By do Selenium,
// utilizada para localizar elementos na página,
// por exemplo, através de XPath, ID, CSS, etc.
import org.openqa.selenium.By;

// Importa a classe DSL,
// que provavelmente contém métodos personalizados
// para facilitar a interação com os elementos da página.
import br.ce.wcaquino.core.DSL;

// Importa a classe DriverFactory,
// responsável por criar e/ou controlar o WebDriver utilizado nos testes.
import br.ce.wcaquino.core.DriverFactory;


// Declara a classe responsável pelos testes da página PrimeFaces.
public class TestePrine {
	
	// Declara uma variável do tipo DSL.
	// Ela será utilizada para executar ações na página,
	// como clicar em radio buttons, selecionar opções de combo,
	// verificar se um radio está marcado, etc.
	private DSL dsl;

	// A anotação @Before indica que este método será executado
	// antes de cada método de teste (@Test).
	@Before
	public void inicializa(){
		
		// Cria uma nova instância da classe DSL
		// antes da execução de cada teste.
		dsl = new DSL();
	}
	
	// A anotação @After indica que este método será executado
	// depois de cada método de teste (@Test).
	@After
	public void finaliza(){
		
		// Obtém a instância atual do WebDriver.
		// ATENÇÃO: esse código NÃO está fechando o navegador.
		// Para fechar o navegador, normalmente seria utilizado:
		// DriverFactory.killDriver();
		DriverFactory.getDriver();
	}

	// Indica que o método abaixo é um teste automatizado.
	@Test
	public void deveInteragirComRadioPrime(){
		
		// Acessa a página do componente RadioButton
		// disponibilizada pelo PrimeFaces Showcase.
		getDriver().get(
			"https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml"
		);
		
		// Localiza e clica no primeiro Radio Button.
		// O elemento é localizado utilizando um XPath.
		dsl.clicarRadio(
			By.xpath("//input[@id='j_idt86:console:0']/../..//span")
		);
		
		// Verifica se o primeiro Radio Button está marcado.
		// O método isRadioMarcado() recebe o ID do elemento
		// e retorna true caso o Radio Button esteja selecionado.
		Assert.assertTrue(
			dsl.isRadioMarcado("j_idt86:console:0")
		);
		
		// Localiza e clica no Radio Button correspondente à opção "PS4".
		// O XPath procura um label cujo texto seja "PS4"
		// e depois localiza o span relacionado ao Radio Button.
		dsl.clicarRadio(
			By.xpath("//label[.='PS4']/..//span")
		);
		
		// Verifica se o Radio Button correspondente ao PS4
		// está marcado após o clique.
		Assert.assertTrue(
			dsl.isRadioMarcado("j_idt86:console:1")
		);
	}
	
	// Indica que o método abaixo é um teste automatizado.
	@Test
	public void deveInteragirComSelectPrime(){
		
		// Acessa a página do componente Select/ComboBox
		// disponibilizada pelo PrimeFaces Showcase.
		getDriver().get(
			"https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml"
		);
		
		// Seleciona a opção "Xbox One" no componente ComboBox.
		// O primeiro parâmetro é o ID do componente.
		// O segundo parâmetro é o texto da opção que será selecionada.
		dsl.selecionarComboPrime(
			"j_idt86:console",
			"Xbox One"
		);
		
		// Verifica se o texto exibido no componente selecionado
		// é realmente "Xbox One".
		// O método obterTexto() busca o texto do elemento
		// identificado pelo ID "j_idt86:console_label".
		Assert.assertEquals(
			"Xbox One",
			dsl.obterTexto("j_idt86:console_label")
		);
	}
}
```
