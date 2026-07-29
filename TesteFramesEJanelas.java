```java
// Define o pacote onde esta classe de teste está localizada.
package br.ce.wcaquino.test;

// Importa de forma estática o método getDriver(),
// permitindo chamá-lo diretamente como getDriver().
import static br.ce.wcaquino.core.DriverFactory.getDriver;

// Importa o After, usado para executar um método depois de cada teste.
import org.junit.After;

// Importa o Assert, utilizado para realizar validações nos testes.
import org.junit.Assert;

// Importa o Before, usado para executar um método antes de cada teste.
import org.junit.Before;

// Importa a anotação Test, que identifica os métodos como testes.
import org.junit.Test;

// Importa a classe By, utilizada para localizar elementos na página.
import org.openqa.selenium.By;

// Importa a classe WebElement, que representa um elemento HTML da página.
import org.openqa.selenium.WebElement;

// Importa a classe DSL, que contém métodos auxiliares
// para facilitar a interação com os elementos da página.
import br.ce.wcaquino.core.DSL;

// Importa a classe DriverFactory, responsável por criar e finalizar o WebDriver.
import br.ce.wcaquino.core.DriverFactory;


// Declara a classe responsável pelos testes de Frames e Janelas.
public class TesteFramesEJanelas {
	
	// Declara uma variável do tipo DSL.
	// Ela será utilizada para executar ações no navegador
	// através dos métodos criados na classe DSL.
	private DSL dsl;

	
	// A anotação @Before indica que este método será executado
	// antes de cada método de teste (@Test).
	@Before
	public void inicializa(){
		
		// Abre a página componentes.html no navegador.
		// System.getProperty("user.dir") retorna o diretório
		// principal do projeto.
		// O resultado final será algo parecido com:
		// file:///C:/meu-projeto/src/main/resources/componentes.html
		getDriver().get(
			"file:///" 
			+ System.getProperty("user.dir") 
			+ "/src/main/resources/componentes.html"
		);
		
		// Cria uma nova instância da classe DSL.
		// Essa classe possui métodos auxiliares para simplificar
		// as ações realizadas pelo Selenium.
		dsl = new DSL();
	}
	
	
	// A anotação @After indica que este método será executado
	// depois de cada método de teste (@Test).
	@After
	public void finaliza(){
		
		// Finaliza o navegador e encerra o WebDriver.
		// Isso garante que o navegador seja fechado após cada teste.
		DriverFactory.killDriver();
	}


	// Teste responsável por verificar a interação com um Frame.
	@Test
	public void deveInteragirComFrames(){
		
		// Entra no Frame identificado pelo ID "frame1".
		// Para interagir com elementos que estão dentro de um Frame,
		// primeiro é necessário mudar o contexto do Selenium para ele.
		dsl.entrarFrame("frame1");
		
		// Localiza e clica no botão identificado como "frameButton".
		dsl.clicarBotao("frameButton");
		
		// Obtém o texto exibido pelo alerta e, em seguida,
		// aceita/fecha o alerta.
		// A mensagem retornada é armazenada na variável "msg".
		String msg = dsl.alertaObterTextoEAceita();
		
		// Verifica se a mensagem exibida pelo alerta
		// é exatamente igual a "Frame OK!".
		Assert.assertEquals("Frame OK!", msg);

		// Sai do Frame e retorna o contexto para a página principal.
		dsl.sairFrame();
		
		// Localiza o campo "nome" e escreve nele o conteúdo
		// armazenado na variável "msg".
		// O elemento está fora do Frame, por isso foi necessário
		// sair do Frame antes de interagir com ele.
		dsl.escrever("elementosForm:nome", msg);
	}
	
	
	// Teste responsável por verificar a interação com um Frame
	// que inicialmente está escondido ou fora da área visível da página.
	@Test
	public void deveInteragirComFrameEscondido(){
		
		// Localiza o elemento HTML que representa o Frame "frame2".
		// O elemento é armazenado na variável "frame".
		WebElement frame = getDriver().findElement(By.id("frame2"));
		
		// Executa um código JavaScript para realizar o scroll da página.
		// O valor frame.getLocation().y representa a posição vertical
		// do Frame na página.
		// Isso faz com que a página role até a posição onde o Frame está localizado.
		dsl.executarJS(
			"window.scrollBy(0, arguments[0])", 
			frame.getLocation().y
		);
		
		// Entra no Frame identificado pelo ID "frame2".
		dsl.entrarFrame("frame2");
		
		// Clica no botão localizado dentro do Frame.
		dsl.clicarBotao("frameButton");
		
		// Obtém o texto do alerta e aceita/fecha o alerta.
		String msg = dsl.alertaObterTextoEAceita();
		
		// Valida se a mensagem apresentada pelo alerta
		// é igual a "Frame OK!".
		Assert.assertEquals("Frame OK!", msg);
	}
	
	
	// Teste responsável por verificar a interação com uma janela Pop-up
	// que possui um título.
	@Test
	public void deveInteragirComJanelas(){
		
		// Clica no botão que abre uma nova janela Pop-up.
		dsl.clicarBotao("buttonPopUpEasy");
		
		// Troca o foco do Selenium para a janela chamada "Popup".
		dsl.trocarJanela("Popup");
		
		// Localiza o elemento textarea e escreve "Deu certo?".
		dsl.escrever(By.tagName("textarea"), "Deu certo?");
		
		// Fecha a janela Pop-up que está atualmente em foco.
		getDriver().close();
		
		// Retorna o foco para a janela principal.
		// Nesse caso, uma String vazia representa a janela principal,
		// de acordo com a implementação do método trocarJanela().
		dsl.trocarJanela("");
		
		// Localiza o textarea da janela principal
		// e escreve "e agora?".
		dsl.escrever(By.tagName("textarea"), "e agora?");
	}
	
	
	// Teste responsável por verificar a interação com janelas
	// que não possuem um título definido.
	@Test
	public void deveInteragirComJanelasSemTitulo(){
		
		// Clica no botão que abre uma nova janela Pop-up.
		dsl.clicarBotao("buttonPopUpHard");
		
		// Exibe no console o identificador (handle) da janela atual.
		// Cada janela ou aba possui um identificador único.
		System.out.println(getDriver().getWindowHandle());
		
		// Exibe no console todos os identificadores das janelas abertas.
		// O método getWindowHandles() retorna um conjunto (Set)
		// contendo os identificadores de todas as janelas.
		System.out.println(getDriver().getWindowHandles());
		
		// Obtém o identificador da segunda janela aberta.
		// toArray()[1] acessa o segundo elemento do conjunto.
		// O cast (String) converte o valor para String.
		// Depois, o Selenium muda o foco para essa janela.
		dsl.trocarJanela(
			(String) getDriver().getWindowHandles().toArray()[1]
		);
		
		// Localiza o textarea da segunda janela
		// e escreve "Deu certo?".
		dsl.escrever(By.tagName("textarea"), "Deu certo?");
		
		// Obtém o identificador da primeira janela aberta
		// e retorna o foco para ela.
		dsl.trocarJanela(
			(String) getDriver().getWindowHandles().toArray()[0]
		);
		
		// Localiza o textarea da primeira janela
		// e escreve "e agora?".
		dsl.escrever(By.tagName("textarea"), "e agora?");
	}
}
```
