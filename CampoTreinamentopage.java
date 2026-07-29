```java
// Define o pacote onde esta classe está localizada.
package br.ce.wcaquino.page;

// Importa a classe By, utilizada para localizar elementos
// da página através de diferentes estratégias, como XPath.
import org.openqa.selenium.By;

// Importa a classe BasePage.
// CampoTreinamentoPage herda os comportamentos e atributos
// definidos na classe BasePage.
import br.ce.wcaquino.core.BasePage;


// Declara a classe CampoTreinamentoPage.
//
// Essa classe representa a página "Campo de Treinamento"
// e segue o padrão Page Object.
//
// A palavra "extends" indica que esta classe herda
// características da classe BasePage.
public class CampoTreinamentoPage extends BasePage {


	// Método responsável por preencher o campo Nome.
	//
	// O parâmetro "nome" recebe o valor que será digitado
	// no campo correspondente da página.
	public void setNome(String nome) {
		
		// Localiza o campo através do ID "elementosForm:nome"
		// e escreve o valor recebido no parâmetro "nome".
		dsl.escrever("elementosForm:nome", nome);
	}
	
	
	// Método responsável por preencher o campo Sobrenome.
	public void setSobrenome(String sobrenome) {
		
		// Localiza o campo de sobrenome e escreve
		// o valor recebido pelo parâmetro.
		dsl.escrever("elementosForm:sobrenome", sobrenome);
	}
	
	
	// Método responsável por selecionar o sexo Masculino.
	public void setSexoMasculino(){
		
		// Localiza o radio button correspondente ao sexo masculino
		// e realiza o clique.
		dsl.clicarRadio("elementosForm:sexo:0");
	}
	
	
	// Método responsável por selecionar o sexo Feminino.
	public void setSexoFeminino(){
		
		// Localiza o radio button correspondente ao sexo feminino
		// e realiza o clique.
		dsl.clicarRadio("elementosForm:sexo:1");
	}
	
	
	// Método responsável por selecionar Carne
	// como comida favorita.
	public void setComidaCarne(){
		
		// Localiza o checkbox/radio correspondente à opção Carne
		// e realiza o clique.
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
	}
	
	
	// Método responsável por selecionar Pizza
	// como comida favorita.
	public void setComidaPizza(){
		
		// Localiza o elemento correspondente à opção Pizza
		// e realiza o clique.
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
	}
	
	
	// Método responsável por selecionar Vegetariano
	// como comida favorita.
	public void setComidaVegetariano(){
		
		// Localiza o elemento correspondente à opção Vegetariano
		// e realiza o clique.
		dsl.clicarRadio("elementosForm:comidaFavorita:3");
	}
	
	
	// Método responsável por selecionar uma opção
	// no campo de escolaridade.
	//
	// O parâmetro "valor" representa a opção que será selecionada.
	public void setEscolaridade(String valor) {
		
		// Localiza o combo de escolaridade pelo ID
		// e seleciona a opção recebida no parâmetro "valor".
		dsl.selecionarCombo("elementosForm:escolaridade", valor);
	}
	
	
	// Método responsável por selecionar uma ou mais opções
	// no combo de esportes.
	//
	// O parâmetro "String... valores" é um varargs.
	// Isso significa que o método pode receber vários valores.
	//
	// Exemplos:
	// setEsporte("Futebol");
	// setEsporte("Futebol", "Natacao");
	// setEsporte("Futebol", "Natacao", "Corrida");
	public void setEsporte(String... valores) {
		
		// Percorre todos os valores recebidos pelo parâmetro "valores".
		for(String valor: valores)
			
			// Para cada esporte recebido, seleciona a opção
			// correspondente no combo de esportes.
			dsl.selecionarCombo("elementosForm:esportes", valor);
	}
	
	
	// Método responsável por realizar o cadastro.
	public void cadastrar(){
		
		// Localiza o botão "Cadastrar" através do ID
		// e realiza o clique.
		dsl.clicarBotao("elementosForm:cadastrar");
	}
	
	
	// Método responsável por obter o resultado geral do cadastro.
	//
	// O método retorna uma String contendo o texto encontrado
	// no elemento localizado pelo XPath informado.
	public String obterResultadoCadastro(){
		
		// Localiza o elemento dentro da página de resultado
		// e retorna seu texto.
		return dsl.obterTexto(
			By.xpath("//*[@id='resultado']/span")
		);
	}
	
	
	// Método responsável por obter o nome informado
	// no resultado do cadastro.
	public String obterNomeCadastro(){
		
		// Localiza o elemento que apresenta o nome cadastrado
		// e retorna seu texto.
		return dsl.obterTexto(
			By.xpath("//*[@id='descNome']/span")
		);
	}
	
	
	// Método responsável por obter o sobrenome informado
	// no resultado do cadastro.
	public String obterSobrenomeCadastro(){
		
		// Localiza o elemento que apresenta o sobrenome cadastrado
		// e retorna seu texto.
		return dsl.obterTexto(
			By.xpath("//*[@id='descSobrenome']/span")
		);
	}
	
	
	// Método responsável por obter o sexo informado
	// no resultado do cadastro.
	public String obterSexoCadastro(){
		
		// Localiza o elemento que apresenta o sexo cadastrado
		// e retorna seu texto.
		return dsl.obterTexto(
			By.xpath("//*[@id='descSexo']/span")
		);
	}
	
	
	// Método responsável por obter a comida favorita
	// informada no cadastro.
	public String obterComidaCadastro(){
		
		// Localiza o elemento que apresenta a comida cadastrada
		// e retorna seu texto.
		return dsl.obterTexto(
			By.xpath("//*[@id='descComida']/span")
		);
	}
	
	
	// Método responsável por obter a escolaridade
	// informada no cadastro.
	public String obterEscolaridadeCadastro(){
		
		// Localiza o elemento que apresenta a escolaridade cadastrada
		// e retorna seu texto.
		return dsl.obterTexto(
			By.xpath("//*[@id='descEscolaridade']/span")
		);
	}
	
	
	// Método responsável por obter os esportes
	// informados no cadastro.
	public String obterEsportesCadastro(){
		
		// Localiza o elemento que apresenta os esportes cadastrados
		// e retorna seu texto.
		return dsl.obterTexto(
			By.xpath("//*[@id='descEsportes']/span")
		);
	}
}
```
