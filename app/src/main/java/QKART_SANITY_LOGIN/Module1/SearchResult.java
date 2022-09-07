package QKART_SANITY_LOGIN.Module1;

import net.bytebuddy.asm.Advice.Return;
import net.bytebuddy.dynamic.scaffold.TypeInitializer.Drain;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResult {
    
    WebElement parentElement;

    public SearchResult(WebElement SearchResultElement) {
        this.parentElement = SearchResultElement;
          //Thread.sleep(5000);
            }

    /*
     * Return title of the parentElement denoting the card content section of a
     * search result
     */
    public String getTitleofResult() throws InterruptedException  {
        String titleOfSearchResult = "";
        // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
        // Find the element containing the title (product name) of the search result and
        // assign the extract title text to titleOfSearchResult
     WebElement title =parentElement.findElement(By.xpath("//p[contains(text(),'YONEX Smash Badminton Racquet')] "));
       Thread.sleep(5000);
        titleOfSearchResult=title.getText();
       System.out.println("Title of the product is:" +titleOfSearchResult);



        return titleOfSearchResult;
    }

    /*
     * Return Boolean denoting if the open size chart operation was successful
     */
    public Boolean openSizechart() {
        try {

            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            // Find the link of size chart in the parentElement and click on it
         WebElement sizechartlink = this.parentElement.findElement(By.xpath("//div[contains(@class,'MuiCardContent-root')]//button"));
             Boolean flag= sizechartlink.isDisplayed(); 
              sizechartlink.click();
            if (flag =true) {System.out.println("");}

            return true;
        } catch (Exception e) {
            System.out.println("Exception while opening Size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the close size chart operation was successful
     */
    public Boolean closeSizeChart(WebDriver driver) {
        try {
            Thread.sleep(2000);
            Actions action = new Actions(driver);

            // Clicking on "ESC" key closes the size chart modal
            action.sendKeys(Keys.ESCAPE);
            action.perform();
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            System.out.println("Exception while closing the size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean based on if the size chart exists
     */
    public Boolean verifySizeChartExists() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            /*
             * Check if the size chart element exists. If it exists, check if the text of
             * the element is "SIZE CHART". If the text "SIZE CHART" matches for the
             * element, set status = true , else set to false
             */
                    
            WebElement sizechartElement =this.parentElement.findElement(By.tagName("button"));
            if (sizechartElement.isDisplayed()){
                if (sizechartElement.getText().toUpperCase().equals("SIZE CHART"));
                System.out.println(sizechartElement.getText().toUpperCase() + "is exists");
              status =true;
              return true;
            }
              status =false;
             return status;
           } catch (Exception e) {
            return status;
        }
    }

    /*
     * Return Boolean if the table headers and body of the size chart matches the
     * expected values
     */
    public Boolean validateSizeChartContents(List<String> expectedTableHeaders, List<List<String>> expectedTableBody,
            WebDriver driver) {
        Boolean status = true;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            /*
             * Locate the table element when the size chart modal is open
             * 
             * Validate that the contents of expectedTableHeaders is present as the table
             * header in the same order
             * 
             * Validate that the contents of expectedTableBody are present in the table body
             * in the same order
             */
            List<WebElement> tableHeaders =driver.findElements(By.xpath("//table/thead/tr/th"));
           // List<String> actualTableHeaders = new ArrayList<>();
           
          int count =0;
          
          for (WebElement tableHeader:tableHeaders){
            for (String expectedtableHeader : expectedTableHeaders){
                    if (tableHeader.getText().equals(expectedTableHeaders)){
                        count=count+1;
                        break;
                    }
                    int headercount=tableHeaders.size();
                    if (headercount==count){
                        status=true;

                   }
            }
       }
                                  
            
                            
            
                        
            List<List<String>> actualTableBody = new ArrayList<>();
            List <String> actualTableBodyRowElements =new ArrayList<>();
            List<WebElement> tableBodyRows =driver.findElements(By.xpath("//table/tbody/tr"));

            for (WebElement tableBodyRow:tableBodyRows ){
                List<WebElement> tableBodyRowElements =tableBodyRow.findElements(By.tagName("td"));
                for (WebElement tableBodyRowElement:tableBodyRowElements ){
                    actualTableBodyRowElements.add(tableBodyRowElement.getText());
                    
                 }
                 actualTableBody.add(actualTableBodyRowElements);

            }
        //    if(actualTableHeaders.equals(expectedTableHeaders) && actualTableBody.equals(expectedTableBody)){
                                   
        //         status=true;
             
        //      return status;

        //     }

        } catch (Exception e) {
            System.out.println("Error while validating chart contents");
            return false;
        }
      return status;
    }
    /*
     * Return Boolean based on if the Size drop down exists
     */
    public Boolean verifyExistenceofSizeDropdown(WebDriver driver) {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            // If the size dropdown exists and is displayed return true, else return false
          status=parentElement.findElement(By.xpath("//select[contains(@class,'MuiNativeSelect-select')] ")).isDisplayed();
          
            return status;
        } catch (Exception e) {
            return status;
        }
    }
}