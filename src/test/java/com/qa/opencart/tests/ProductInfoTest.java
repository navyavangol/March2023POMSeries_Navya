package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoTest extends BaseTest {

	@BeforeClass
	public void prodInfoSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] productTestData() {
		return new Object[][] {
			{"macbook", "MacBook Pro"},
			{"iMac","iMac"},
			{"macbook", "MacBook Air"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1"}			
		};
	}
	
	@Test(dataProvider="productTestData")
	public void productHeaderTest(String searchkey,String productName) {
		searchResPage = accPage.doSearch(searchkey);
		productInfoPage = searchResPage.selectProduct(productName);
		String actProductHeader = productInfoPage.getProductHeaderValue();
		Assert.assertEquals(actProductHeader, productName);
	}
	
	@DataProvider
	public Object[][] productData() {
		return new Object[][] {
			{"macbook", "MacBook Pro",4},
			{"iMac","iMac",3},
			{"macbook", "MacBook Air",4},
			{"samsung","Samsung SyncMaster 941BW",1},
			{"samsung","Samsung Galaxy Tab 10.1",7}			
		};
	}
	
	@Test(dataProvider="productData")
	public void productImagesCount(String searchkey,String productName,int expProductImagesCount) {
		searchResPage = accPage.doSearch(searchkey);
		productInfoPage = searchResPage.selectProduct(productName);
		int actProdImagesCount=productInfoPage.getProductImagesCount();
		Assert.assertEquals(actProdImagesCount, expProductImagesCount);
	}
	
	@Test
	public void productInfoTest() {
		searchResPage = accPage.doSearch("macbook");
		productInfoPage = searchResPage.selectProduct("MacBook Pro");
		Map<String,String> productActualData=productInfoPage.getProductData();
		System.out.println(productActualData);
	
		softAssert.assertEquals(productActualData.get("Brand"), "Apple");
		softAssert.assertEquals(productActualData.get("Availability"), "In Stock");
		softAssert.assertEquals(productActualData.get("productheader"), "MacBook Pro");
		softAssert.assertEquals(productActualData.get("price"), "$2,000.00");
		softAssert.assertEquals(productActualData.get("Reward Points"), "800");
		softAssert.assertAll();//compulsary to write this line ,otherwise if any assertion will fail will wont give the failed results		
		
	}
}
