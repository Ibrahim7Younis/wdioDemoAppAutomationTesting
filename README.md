# 📱 Appium Java Automation Framework

This is an automation framework for Android application testing built with **Appium**, **Java**, and **TestNG**, using the **Page Object Model (POM)** design pattern.

---

## 📁 Project Structure

```
.gitignore
pom.xml

.idea/

src/
├── main/java/wdioDemoApp/
│   ├── constants/
│   │   └── GeneralConstants.java
│   ├── DataModels/
│   │   └── LoginDM.java
│   ├── ExcelIndices/
│   │   └── LoginIndices.java
│   ├── pages/
│   │   ├── FormsPage.java
│   │   ├── LoginPage.java
│   │   ├── MainPage.java
│   │   └── SwipePage.java
│   └── utils/
│       └── PropertiesFilesHandler.java
│
├── test/java/
│   ├── testSuit.xml
│   └── wdioDemoTest/
│       ├── BaseTest.java
│       ├── FormsTest.java
│       ├── LoginTest.java
│       └── SwipeTest.java
│
└── test/resources/
    ├── configFiles/
    │   ├── Caps.json
    │   ├── GeneralConfigs.properties
    │   └── TestData.properties
    └── testDataFiles/
        └── DDTestCases.xlsx
```

---

## ✅ Prerequisites

- Java JDK 11 or higher
- Maven
- Android Studio (with Emulator or real device)
- Appium Server (running locally or remotely)
- Node.js & npm
- Android SDK & Environment variables set (`ANDROID_HOME`)

---

## 🔧 Installation & Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/Ibrahim7Younis/wdioDemoAppAutomationTesting.git
   cd wdioDemoAppAutomationTesting
   ```

2. Configure your environment:
  - **install appium gestures**:
 use the command: appium plugin install --source=npm appium-gestures-plugin
  - **include appium gestures in start appium server command**:
    appium --use-plugins=gestures
  - **Device Capabilities**:  
    Update your device name and platform version in  
    `src/test/resources/configFiles/Caps.json`

  - **Appium Server URL**:  
    Set your Appium server URL in  
    `src/test/resources/configFiles/GeneralConfigs.properties`

---

## 🧪 Running the Tests

You can run the tests using:

```bash
mvn test
```

Or by specifying the TestNG suite file:

```bash
mvn test -DsuiteXmlFile=src/test/java/testSuit.xml
```
