package QKART_SANITY_LOGIN.Module1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Checkout {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app/checkout";

    public Checkout(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToCheckout() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    /*
     * Return Boolean denoting the status of adding a new address
     */
    public Boolean addNewAddress(String addresString) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Click on the "Add new address" button, enter the addressString in the address
             * text box and click on the "ADD" button to save the address
             */        
            WebElement addnewButton= driver.findElement(By.xpath("//button[@id='add-new-btn']"));
                  addnewButton.click();
            WebElement addressTextbox = driver.findElement(By.xpath("//textarea[@placeholder='Enter your complete address']"));
                  addressTextbox.sendKeys(addresString);
            WebElement clickonAdd= driver.findElement(By.xpath("//button[contains(@type,'button')]"));
                  clickonAdd.click();
            System.out.println("address added "+ addresString );
            return true;
        } catch (Exception e) {
            System.out.println("Exception occurred while entering address: " + e.getMessage());
            return false;

        }
    }

    /*
     * Return Boolean denoting the status of selecting an available address
     */
    public Boolean selectAddress(String addressToSelect) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Iterate through all the address boxes to find the address box with matching
             * text, addressToSelect and click on it
             */
            List<WebElement> addressList = 
            driver.findElements(By.xpath("//div[contains(@class,'address-item')]//p"));
             for (WebElement address :addressList){
                if(address.getText().equalsIgnoreCase(addressToSelect))
                address.click();
                System.out.println("Selected the correct address" + addressToSelect);
               Thread.sleep(3000);
                break;
             }

            //System.out.println("Unable to find the given address");
            return false;
        } catch (Exception e) {
            System.out.println("Exception Occurred while selecting the given address: " + e.getMessage());
            return false;
        }

    }

    /*
     * Return Boolean denoting the status of place order action
     */
    public Boolean placeOrder() {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            // Find the "PLACE ORDER" button and click on it
          WebElement placeOrderButton =driver.findElement(By.xpath("//button[contains(text(),'PLACE ORDER')]"));
           placeOrderButton.click();
            return false;

        } catch (Exception e) {
            System.out.println("Exception while clicking on PLACE ORDER: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the insufficient balance message is displayed
     */
    public Boolean verifyInsufficientBalanceMessage() {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 08: MILESTONE 7
            Boolean flag ;
            WebElement msg=driver.findElement(By.xpath("//*[@id='notistack-snackbar']"));  
            flag=msg.isDisplayed();     
            if (flag=true){
                System.out.println(msg.getText());
            }
            return flag;
        } catch (Exception e) {
            System.out.println("Exception while verifying insufficient balance message: " + e.getMessage());
            return false;
        }
    }
}
