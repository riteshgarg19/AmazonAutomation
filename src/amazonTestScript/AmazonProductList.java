package amazonTestScript;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AmazonProductList extends AmazonUserLogin {


    public void searchProduct() throws Exception{
        this.loginToAmazon();
        String search_text = config.getProperty("search_productValue");
        driver.findElement(By.id(locators.getProperty("product_searchbox"))).sendKeys(search_text);
        driver.findElement(By.xpath(locators.getProperty("click_searchbutton"))).click();

        String getProductListPageTitle = driver.getTitle();
        String expectedProductListPageTitle = "Amazon.in: "+search_text;

        if(expectedProductListPageTitle.equals(getProductListPageTitle)) {
            System.out.println("Navigated to Products List Page");
        }
    }

    public void getProductList() throws Exception {
        this.searchProduct();

        List<WebElement> productList = driver.findElements(By.xpath(locators.getProperty("product_list_1")));
        Thread.sleep(10000);
        String productName = "";
        String productPrice = "";
        int k;
        ArrayList<Object> newProductList = new ArrayList();
        for (int i = 0; i < productList.size(); i++) {
            newProductList.add(productList.get(i).getText());
        }
        ArrayList<Object[]> data = new ArrayList<Object[]>(newProductList.size());
        data.add(new Object[]{"Product Name"});

        for (int j = 0; j < newProductList.size(); j++) {
            String a = newProductList.get(j).toString();
            String[] productDetails = a.split("\\r?\n");
            for (k = 0; k < productDetails.length; k++) {
                if (productDetails[k].equals("Sponsored") || productDetails[k].equals("Best seller") ||
                        productDetails[k].equals("Amazon's Choice") || productDetails[k].equals("Prime Day launch")) {
                    productName = productDetails[k+1];
                    break;
                }
                else
                 {
                    productName = productDetails[k];
                    break;
                }
            }
            data.add(new Object[]{productName});
        }
        Object[][] productArray = data.toArray(new Object[data.size()][]);

        // WriteProduct Details to excelsheet
        excelReader.writeExcel(excelDataPath, excelFileName, "Amazon Product List", productArray);
    }
}
