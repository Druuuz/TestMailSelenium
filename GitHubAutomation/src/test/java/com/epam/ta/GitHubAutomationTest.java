package com.epam.ta;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.ta.steps.Steps;

public class GitHubAutomationTest
{
	private Steps steps;
	private final String USERNAME = "test.poc";
	private final String PASSWORD = "pass666";
	private final String DOMAIN = "@bk.ru";


	@BeforeMethod(description = "Init browser")
	public void setUp()
	{
		steps = new Steps();
		steps.initBrowser();
	}

	@Test
	public void CanLogin()
	{
		steps.login(USERNAME, PASSWORD, DOMAIN);


		Assert.assertEquals(steps.getUserName(), USERNAME + DOMAIN);

		steps.writeMessage("subject", "target@mail.com", "SomeText!!");
		Assert.assertTrue(steps.isDraftSaved());
		steps.openDrafts();
		Assert.assertTrue(steps.isMessageInDraftFolder("subject", "target@mail.com", "SomeText!!"));
		Assert.assertTrue(steps.isMessageDataCorrect("subject", "target@mail.com", "SomeText!!"),"44444444");
		steps.openDraft();
		steps.sendDraft();

		steps.openDrafts();
		Assert.assertFalse(steps.isMessageInDraftFolder("subject", "target@mail.com", "SomeText!!"),"33333333");
		steps.openSentsFoldder();

		Assert.assertTrue(steps.isMessageInSentFolder(),"22222222");
		try {
			Thread.sleep(5000);
		}
		catch (Exception e){}



	}


	@AfterMethod(description = "Stop Browser")
	public void stopBrowser()
	{
		steps.closeDriver();
	}


}
