package amazonTestSuite;

import amazonTestScript.AmazonProductList;
import amazonTestScript.AmazonUserSignOut;

public class executeTestSuite {

    public static void main(String args[]) throws Exception {
        AmazonProductList amazonProductList = new AmazonProductList();
        AmazonUserSignOut amazonUserSignOut = new AmazonUserSignOut();

        amazonProductList.getProductList();
        amazonUserSignOut.doSignOut();
    }
}
