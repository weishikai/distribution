package com.e_dewin.app.distribution;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
 
import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;
 
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
 
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Appium Android Demo
 * @author weishikai
 *
 */
public class Demo {
	private AppiumDriver<AndroidElement> driver;
	private static int SCREEN_WIDTH;
	private static int SCREEN_HEIGHT;
	
    @BeforeClass
    public void setUp() throws Exception{
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "app");
        File app = new File(appDir,"DewinDistribution-Official.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        capabilities.setCapability(MobileCapabilityType.UDID,"APU7N16716002669");// "Android Emulator" device ID
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"HUAWEI Mate 8");// 真机名字
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"7.0");// 真机版本
        capabilities.setCapability(MobileCapabilityType.APP,app.getAbsolutePath());// 对应的被测APK文件路径
//        capabilities.setCapability("appPackage", "com.dewin.distribution");
        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4726/wd/hub"), capabilities);// 新建session
    }
    
    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
    	driver.quit();
    }
    
    /**
     * 测试步骤: 登录 
     * 
     * @throws InterruptedException
     */
    @Test
    public void firstDemo() throws InterruptedException {
    	swipeGuide();
    	login();
        // 等待登陆app
    	WebDriverWait wait = new WebDriverWait(driver, 30);
    	wait.until(ExceptedConditions.presenceOfElementLocated(By.id("")));
      
    }
    
    /**
     * 首次打开，用户引导
     */
    public void swipeGuide()throws InterruptedException {
        // GuideActivity.java
        Dimension dimension;
        dimension = driver.manage().window().getSize();
        int SCREEN_WIDTH = dimension.getWidth();
        int SCREEN_HEIGHT = dimension.getHeight();
        System.out.println("被测设备宽高：" + SCREEN_WIDTH + "," + SCREEN_HEIGHT);
    }
    
    /**
     * 登陆
     */
    private void login() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        //判断是否存在UserName输入框
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("right_edit_text")));
        driver.findElement(By.id("right_edit_text")).sendKeys("18767191571");
        driver.findElement(By.id("right_edit_text")).click();
        driver.findElement(By.id("PassWord")).sendKeys("123456");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //输入账号密码后登陆
        driver.findElement(By.id("btn_login")).click();
    }
 
} 
