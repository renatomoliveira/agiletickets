package br.com.caelum.agiletickets.controllers;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ReservasDriver {
	private static final String BASE_URL = "http://localhost:8080";

	
	@Test
	public void reservaEstabelecimento() {
		WebDriver driver = new FirefoxDriver();
		driver.navigate().to(BASE_URL);
		List<WebElement> el = driver.findElement(By.id("sessoes")).findElements(By.tagName("li"));
		el.get(3).findElement(By.tagName("a")).click();
		
		WebElement qtde = driver.findElement(By.id("qtde"));
		qtde.sendKeys("1");
		WebElement preco = driver.findElement(By.id("preco"));
		String precoTexto = preco.getText();
		qtde.submit();
		WebElement msg = driver.findElement(By.id("message"));
		Assert.assertTrue(msg.getText().contains(precoTexto));
		
	}
}
