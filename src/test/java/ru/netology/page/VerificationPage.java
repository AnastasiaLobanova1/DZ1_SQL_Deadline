package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeFiled = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id = action-verify]");
    private SelenideElement errorNotification = $("[data-test-id=error-notification]");

    public void verifyVerificationPageVisible() {
        codeFiled.shouldBe(visible);
    }

    public void verifyErrorNotificationVisible() {
        errorNotification.shouldBe(visible);
    }

    public DashboardPage validVerify(String verificationCode) {
        verifyButtonClick(verificationCode);
        return new DashboardPage();
    }

    public void verifyButtonClick(String verificationCode) {
        codeFiled.setValue(verificationCode);
        verifyButton.click();
    }
}
