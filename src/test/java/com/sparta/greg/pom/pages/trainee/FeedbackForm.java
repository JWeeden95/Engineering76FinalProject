package com.sparta.greg.pom.pages.trainee;

import com.sparta.greg.pom.pages.templates.Page;
import com.sparta.greg.pom.pages.fragments.SideBarTrainee;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FeedbackForm extends Page {

    private By stop = By.name("stopTrainee");
    private By start = By.name("startTrainee");
    private By continueTrainee = By.name("continueTrainee");
    private By input = By.tagName("input");
    private List<WebElement> radioButtons;
    private SideBarTrainee sideBarTrainee;

    public FeedbackForm(WebDriver webDriver){
        super(webDriver);
        sideBarTrainee = new SideBarTrainee(webDriver);
        PageFactory.initElements(webDriver, this);

    }

    public SideBarTrainee getSideBarTrainee(){
        return sideBarTrainee;
    }

    public boolean enterStart(String sentence, int numberOfBackSpaces){

        if (sentence != null && !sentence.equals(" ") && numberOfBackSpaces >=0){
            pressBackSpace(numberOfBackSpaces, start);
            webDriver.findElement(start).sendKeys(sentence);
            return true;
        }else{
            System.out.println("Enter a non null or non empty string");
            return false;
        }


    }

    public boolean enterStop(String sentence, int numberOfBackSpaces){
        if (sentence != null && !sentence.equals(" ") && numberOfBackSpaces >=0){
            pressBackSpace(numberOfBackSpaces, stop);
            webDriver.findElement(stop).sendKeys(sentence);
            return true;
        }else{
            System.out.println("Enter a non null or non empty string");
            return false;
        }

    }

    public boolean enterContinue(String sentence, int numberOfBackSpaces){
        if (sentence != null && !sentence.equals(" ") && numberOfBackSpaces >=0){

            pressBackSpace(numberOfBackSpaces, continueTrainee);
            webDriver.findElement(continueTrainee).sendKeys(sentence);
            return true;
        }else{
            System.out.println("Enter a non null or non empty string");
            return false;
        }

    }

    public boolean isTechnicalGradeSelected(String grade){

        if (grade != null && !grade.equals(" ")) {
            grade = grade.toUpperCase();
            WebElement element = accessRadioButton(grade, "tech"+grade);
            return checkElement(element);
        }else{
            System.out.println("Enter a non null or non empty string");
        }
        return false;
    }


    public boolean isConsultantGradeSelected(String grade){

        if (grade != null && !grade.equals(" ")) {
            grade = grade.toUpperCase();
            WebElement element = accessRadioButton(grade, "consul"+grade);
            return checkElement(element);
        }else{
            System.out.println("Enter a non null or non empty string");
        }
        return false;
    }

    public void clickSubmitButton(){
        webDriver.findElement(By.name("btnStatus")).click();
    }


    private WebElement accessRadioButton(String letter, String id){
        radioButtons = webDriver.findElements(input);
        for (WebElement webElement : radioButtons) {
            if (webElement.getAttribute("id").equals(id)) {
                return webElement;
            }
        }
        return null;
    }

    public boolean checkElement(WebElement element){
        if (element == null){
            System.out.println("Could not find element");
            return false;
        }
        Actions actions = new Actions(webDriver);
        actions.moveToElement(element).click().perform();
        if (element.isSelected()){
            return true;
        }
        return false;
    }

    private void pressBackSpace(int numberOfPresses, By element){
        for(int i=0; i<numberOfPresses; i++){
            webDriver.findElement(element).sendKeys(Keys.BACK_SPACE);
        }

    }















}
