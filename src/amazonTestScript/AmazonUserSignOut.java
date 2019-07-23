package amazonTestScript;

public class AmazonUserSignOut extends AmazonProductList {

    private String signOutUrl = "/gp/flex/sign-out.html";

    public void doSignOut() {
        driver.get("https://www.amazon.in" + signOutUrl);
        if (driver.getTitle().equalsIgnoreCase(amazon_signIn_title)) {
            System.out.println("User is logged out successfully");
            driver.close();
        } else {
            System.out.println("Error in user logout");
        }
    }
}
