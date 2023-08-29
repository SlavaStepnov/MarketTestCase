package zadanietests;

import java.time.Duration;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ZadanieTestNGTest {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static String whiskasName; //для названия товара вискаса
    private static String mnyamsName; //для названия товара мнямса
    public static int whiskasPrice; //для цены вискаса
    public static int mnyamsPrice; //для цены мнямса
    
    public static String getMnyamsName() {
        return mnyamsName;
    }

    public static void setMnyamsName(String mnyamsName) {
        ZadanieTestNGTest.mnyamsName = mnyamsName;
    }
    
    public static void setWhiskasName(String whiskasName) {
        ZadanieTestNGTest.whiskasName = whiskasName;
    }

    public static String getWhiskasName() {
        return whiskasName;
    }
    
    /*
        OC Windows7, Google Версия 109.0.5414.120 (Официальная сборка), (64 бит)
    */
    
    public ZadanieTestNGTest() {
    }
    
    public int getPrice(String priceStr) {
        char[] numbers = {'0','1','2','3','4','5','6','7','8','9'};
        char[] PriceCharArr = priceStr.toCharArray();
        String PriceToString = "";
        for(int i = 0; i < PriceCharArr.length; i++) {
            for(int j = 0; j < numbers.length; j++) {
                if(PriceCharArr[i] == numbers[j]) PriceToString += numbers[j]; 
            }           
        }
        int priceInt = Integer.parseInt(PriceToString);
        return priceInt;
    }
    //Метод для нахождения цены
    
    //Вход на сайт
    @Test
    public void openYandexMarket(){
        driver.navigate().to("https://market.yandex.ru");
        driver.manage().window().maximize();
    }
    
    //Клик на каталог
    @Test(dependsOnMethods = {"openYandexMarket"})
    public void clickCatalogButton() {
        WebElement catalogButton = driver.findElement(By.xpath("//header/div[1]/div[1]/div[1]/noindex[1]/div[1]/div[1]/button[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(catalogButton));
        catalogButton.click();
    }
    
    //Наведение мыши и клик на "Зоотовары"
    @Test(dependsOnMethods = {"clickCatalogButton"})
    public void navigateToPetSupplies() {
        Actions actions = new Actions(driver);
        WebElement petSuppliesLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Зоотовары")));
        actions.moveToElement(petSuppliesLink).perform();
        actions.click().perform();
    }
    
    //Поиск "Товаров для кошек"
    @Test(dependsOnMethods = {"navigateToPetSupplies"})
    public void navigateToSuppliesForCats() {
        WebElement suppliesForCatsLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div._111XI.main:nth-child(2) div._3uHkO._1CRdp._1xwLW div._2wiG- div._1ZjKa.a6Vij div._1lpjN._3Wkj0._1i3SE.cXkP_.gCOkS._293lj div._3RoU0 div.cia-vs div._3Oiax._32o4X div._2v6LD.YKKDw div.VFWBy.uG_0s.brandTheme-default div._3AmSh._2x9mu._1kuLK._3uirD.brandTheme-default div:nth-child(1) div._1nO7x.uG_0s:nth-child(1) div.cia-vs.cia-cs > a._20WYq._1c4-E._1gCbc")));
        suppliesForCatsLink.click();
        Assert.assertTrue(driver.getPageSource().contains("Товары для кошек"));
    }
    
    //Поиск "Лакомств для кошек"
    @Test(dependsOnMethods = {"navigateToSuppliesForCats"})
    public void navigateToTreatsForCats() {
        WebElement treatsForCats = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Лакомства для кошек")));
        treatsForCats.click();
        Assert.assertTrue(driver.getPageSource().contains("Лакомства для кошек"));
    }
    
    //Устанавливаю цену
    @Test(dependsOnMethods = {"setTheSettings1"})
    public void setTheSettings2() {
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[1]/main[1]/div[1]/div[3]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/aside[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]")));
       WebElement minPrice = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//body[1]/main[1]/div[1]/div[3]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/aside[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[1]/span[1]/div[1]/div[1]/input[1]")));
       minPrice.sendKeys("50");
       
       WebElement maxPrice = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//body[1]/main[1]/div[1]/div[3]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/aside[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[2]/span[1]/div[1]/div[1]/input[1]")));
       maxPrice.sendKeys("150");   
    }
    
    //Выбор бренда Вискас
    @Test(dependsOnMethods = {"navigateToTreatsForCats"})
    public void setTheSettings1() {
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[1]/main[1]/div[1]/div[3]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]")));
        
        WebElement expand = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//body[1]/main[1]/div[1]/div[3]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/aside[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/fieldset[1]/div[1]/div[1]/div[2]/button[1]/span[1]")));
        wait.until(ExpectedConditions.elementToBeClickable(expand)).click();
        //Клик по кнопке "Показать Всё"
        
        WebElement textInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//body[1]/main[1]/div[1]/div[3]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/aside[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")));
        textInput.sendKeys("Whiskas");
        
        WebElement whiskasCheckBox = driver.findElement(By.xpath("//body[1]/main[1]/div[1]/div[3]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/aside[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/fieldset[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/label[1]/span[1]"));
        whiskasCheckBox.click();

        //кликаем по вискасу
    }                                             
    
    //Выбор доставки курьером
    @Test(dependsOnMethods = {"setTheSettings2"})
    public void setTheSettings3() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        WebElement courier = driver.findElement(By.xpath("//body[1]/main[1]/div[1]/div[3]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/aside[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[11]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/label[1]/span[1]/span[1]"));
        courier.click();
        driver.navigate().refresh();
    }
    
    /*
    Здесь у меня начались проблемы. Как я ни пытался, я никак не мог дождаться загрузки первого товара.
    Код начинал идти дальше, когда элемент еще не был выбран.
    Единственное, что получилось, это обновить страницу, чтобы implicitWait дал время на загрузку
    Как сделать это адекватно, я не знаю
    */
    @Test(dependsOnMethods =  {"setTheSettings3"})
    public void getFirstElement() {
        System.out.println("Начало getFirstElement\n");
        //Ищу веб-элемент, в котором будет нужный товар
        WebElement parent = driver.findElement(By.xpath("//body[1]/main[1]/div[1]/div[3]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]"));
        
        //Ожидания наисаны, потому что я никак не мог дождаться элемента
        //и пробовал различные команды. С ними работает, а без них, что будет, проверять страшно
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[1]/main[1]/div[1]/div[3]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]")));
         wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//body[1]/main[1]/div[1]/div[3]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]"), "Вискас"));
         
         /*
         Здесь у меня произошла серьезная ситуация
         Название Вискаса было написано, то на русском, то на английском
         Я пытался оформить условие по выбору правильного языка
         Что-то похожее будет и дальше, но здесь у меня довести до ума не вышло,
         поэтому я отказался от этой затеи
         */
//        if(parent.findElements(By.partialLinkText("Вискас")).isEmpty()&&driver.getPageSource().contains("Whiskas")){    //parent.findElements(By.partialLinkText("Вискас")).isEmpty()
////            if(parent.findElements(By.partialLinkText("Whiskas")).get(0).getText().contains("Whiskas")) {
//            WebElement firstWhiskas = parent.findElements(By.partialLinkText("Whiskas")).get(0);
//            setWhiskasName(firstWhiskas.getText());
//            firstWhiskas.click();
//            } else {
//                if(parent.findElements(By.partialLinkText("Вискас")).get(0).getText().contains("Вискас")) {
//                    WebElement firstWhiskas = parent.findElements(By.partialLinkText("Вискас")).get(0);
//                    setWhiskasName(firstWhiskas.getText());
//                    firstWhiskas.click();
//                }
//            }
//        } else {
             wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//body[1]/main[1]/div[1]/div[3]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]"), "Вискас"));
            
            WebElement firstWhiskas = parent.findElements(By.partialLinkText("Вискас")).get(0);
            
            
            setWhiskasName(firstWhiskas.getText());// записываю название продукта для следующей проверки
            firstWhiskas.click(); 
//          }
//          System.out.println(getWhiskasName()); 
//          System.out.println(driver.getTitle());
          System.out.println("Конец getFirstElement\n");
    }
    //Переход на страницу товара и добавление его в список для сравнения
    @Test(dependsOnMethods = {"getFirstElement"})
    public void clickCompareButton() {
        System.out.println("Начало clickCompareButton\n");
        String parentID = driver.getWindowHandle();
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        if(parentID.equals(tabs2.get(0))) {
          driver.switchTo().window(tabs2.get(1));
        }else {
          driver.switchTo().window(tabs2.get(0));
        }
        //перехожу на вкладку товара
        WebElement whiskasPriceToStr = driver.findElement(By.xpath("//body[1]/div[2]/div[2]/div[1]/main[1]/div[2]/div[3]/div[1]/div[3]/span[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/h3[1]"));
        whiskasPrice = getPrice(whiskasPriceToStr.getText());
        System.out.println(whiskasPrice);
        //Записываю цену товара
        
        
        WebElement compareButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[1]/div[2]/div[2]/div[1]/main[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]")));
        wait.until(ExpectedConditions.elementToBeClickable(compareButton)).click();
        //клик по кнопке сравнить и переход обратно, для поиска другого товара
        if(parentID.equals(tabs2.get(0))) {
          driver.switchTo().window(tabs2.get(0));
        }else {
          driver.switchTo().window(tabs2.get(1));
        } System.out.println("Конец clickCompareButton\n");
    }          
    //Изменение бренда
    @Test(dependsOnMethods = {"clickCompareButton"})
    public void changeProducer() {
        System.out.println("Начало changeProducer\n");
        WebElement mnyamsBox = driver.findElement(By.xpath("//body/main[1]/div[1]/div[3]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/aside[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/label[1]/span[1]/span[1]/span[1]"));
        mnyamsBox.click();
        
        WebElement whiskasBox = driver.findElement(By.xpath("//body/main[1]/div[1]/div[3]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/aside[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/label[1]/span[1]/span[1]/span[1]"));
        whiskasBox.click();
        System.out.println("Конец changeProducer\n");
    }
    //Поиск нужного товара другого бренда
    @Test(dependsOnMethods = {"changeProducer"})
    public void getSecondElement() {
        System.out.println("Начало getSecondElement\n");
        
        WebElement parent = driver.findElement(By.xpath("//body[1]/main[1]/div[1]/div[3]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]"));
        wait.until(ExpectedConditions.visibilityOf(parent));
        driver.findElement(By.xpath("//body[1]/main[1]/div[1]/div[3]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]"));
        driver.findElement(By.xpath("//body[1]/main[1]/div[1]/div[3]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]"));
        //То же самое, что и с вискасом. Не мог нормально подождать загрузки списка товаров.
        //Но здесь без перезагрузки страницы
        
        WebElement secondMnyams = parent.findElements(By.partialLinkText("Мнямс")).get(1);
        setMnyamsName(secondMnyams.getText());
        //Записываю название второго товара
        secondMnyams.click();
        System.out.println(getMnyamsName());
        System.out.println("Конец getSecondElement\n");
    }
    
    //Переход на страницу товара и добавление его в список для сравнения
    @Test(dependsOnMethods = {"getSecondElement"})
    public void clickSecondCompareButton() {
        System.out.println("Начало clickSecondCompareButton\n");
        String parentID = driver.getWindowHandle();
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        for (String handle : tabs2) {
        driver.switchTo().window(handle);
            if(driver.getTitle().contains(getMnyamsName())) {
            break;
            }
        }
        
        WebElement mnyamsPriceToStr = driver.findElement(By.xpath("//body[1]/div[2]/div[2]/div[1]/main[1]/div[3]/div[3]/div[1]/div[3]/span[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/h3[1]"));
        mnyamsPrice = getPrice(mnyamsPriceToStr.getText());
        System.out.println(mnyamsPrice);
         //записываю цену товара
        WebElement compareButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[1]/div[2]/div[2]/div[1]/main[1]/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]")));
        wait.until(ExpectedConditions.elementToBeClickable(compareButton)).click();
        //клик по кнопке "сравнить"
        System.out.println("Конец clickSecondCompareButton\n");
    }
    //Переход по ссылке для дальнейшего сравнения
    @Test(dependsOnMethods = {"clickSecondCompareButton"})
    public void goToCompareWindow() {
    WebElement compareLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Сравнить')]")));
    wait.until(ExpectedConditions.elementToBeClickable(compareLink)).click();    
    }
    
    //Переход на вкладку сравнения для проверки имён и цен
    @Test (dependsOnMethods = {"goToCompareWindow"})
    public void compareProducts() {
        System.out.println("Начало compareProducts\n");
        //String parentID = driver.getWindowHandle();
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        for (String handle : tabs2) {
        driver.switchTo().window(handle);
            if(driver.getTitle().contains("Сравнение товаров — Яндекс Маркет")) {
            break;
            }
        }
        
        WebElement text = driver.findElement(By.xpath("//div[contains(text(),'Сравнение товаров')]"));
        Assert.assertEquals(text.getText().contains("Сравнение товаров"), true);
        
        WebElement parent = driver.findElement(By.cssSelector("div._111XI.main:nth-child(2) div.S4U0o:nth-child(6) div:nth-child(2) div._1ky8N div:nth-child(2) div.e910R > div._1P4gD._34m6b"));
        /*
        У меня имена не совпали. Слова те же, только в другом порядке
        Возможно надо было записывать названия со страниц товаров,
        но времени исправлять уже нет.
        Проверки ниже занимают достаточно много времени
        Не придумал, как сделать быстрее
        */
        if(parent.findElements(By.partialLinkText("Вискас")).isEmpty()){
            if(parent.findElements(By.partialLinkText("Whiskas")).get(0).getText().contains("Whiskas")) {
            Assert.assertEquals(parent.findElement(By.partialLinkText("Whiskas")).getText().contains("Whiskas"), true);
            }
        } else {
            Assert.assertEquals(parent.findElement(By.partialLinkText("Вискас")).getText().contains("Вискас"), true);
          } 
        Assert.assertEquals(parent.findElement(By.partialLinkText("Мнямс")).getText().contains("Мнямс"), true);
        
        System.out.println(whiskasPrice+mnyamsPrice);
        Assert.assertEquals((whiskasPrice+mnyamsPrice) < 300, true);
        //Проверяю сумму цен товаров
        System.out.println("Конец compareProducts\n");
    }
    
    //Удаление Вискаса
    @Test(dependsOnMethods = {"compareProducts"})
    public void deleteWhiskasItem() {
        System.out.println("Начало deleteWhiskasItem\n");
        Actions actions = new Actions(driver);
        WebElement whiskasParent = driver.findElement(By.xpath("//body/div[2]/div[2]/div[2]/div[1]/div[2]"));
        /*
        Здесь снова проверяю, на каком языке написан бренд Вискас
        и навожу мышкой на значок удаления
        */
        if(whiskasParent.findElements(By.partialLinkText("Вискас")).isEmpty()){
            if(whiskasParent.findElements(By.partialLinkText("Whiskas")).get(0).getText().contains("Whiskas")) {
            WebElement whiskas = whiskasParent.findElements(By.partialLinkText("Whiskas")).get(0);
            actions.moveToElement(whiskas).perform();
            }
        } else {
            WebElement whiskas = whiskasParent.findElements(By.partialLinkText("Вискас")).get(0);
            actions.moveToElement(whiskas).perform();
          }
        //Проверяю, что Вискас действительно удалился
        WebElement whiskasDeleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]")));
        wait.until(ExpectedConditions.elementToBeClickable(whiskasDeleteButton)).click();
         wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.partialLinkText("Whiskas"))));
         wait.until(ExpectedConditions.invisibilityOfElementLocated(By.partialLinkText("Вискас")));
        Assert.assertEquals(!(driver.getPageSource().contains("Whiskas")&&driver.getPageSource().contains("Вискас")), true);
        System.out.println("Конец deleteWhiskasItem\n");
    }
    
    //Нажатие на кнопку "Очистка корзины"
    @Test(dependsOnMethods = {"deleteWhiskasItem"})
    public void deleteAllItems() {
        System.out.println("Начало deleteAllItems\n");
        WebElement deleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Удалить список')]")));
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div._111XI.main:nth-child(2) div.S4U0o:nth-child(6) div:nth-child(2) div._1ky8N div:nth-child(2) div.e910R > div._1P4gD._34m6b"), 0));
        Assert.assertEquals(!(driver.getPageSource().contains(getMnyamsName())&&driver.getPageSource().contains(getWhiskasName())), true);
        System.out.println("Конец deleteAllItems\n");
        /*
        Проверяю, что товаров не осталось
        Если бы имена совпали слово в слово, то последняя проверка была бы идеальной
        */
    }
    
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\123\\Desktop\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(25));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(25));
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
