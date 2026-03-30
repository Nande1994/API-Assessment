# Selenium Java Test Automation Framework

A robust and scalable test automation framework built with **Selenium WebDriver**, **TestNG**, and **Java 21**. This framework follows the **Page Object Model (POM)** design pattern and includes features like data-driven testing, extent reporting, and screenshot capture.

---

## 📋 Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Prerequisites](#-prerequisites)
- [Installation](#-installation)
- [Configuration](#-configuration)
- [Running Tests](#-running-tests)
- [Test Reports](#-test-reports)
- [Framework Components](#-framework-components)
- [Contributing](#-contributing)

---

## ✨ Features

- **Page Object Model (POM)** - Clean separation of test logic and page elements
- **Data-Driven Testing** - Read test data from Excel files using Apache POI
- **Cross-Browser Support** - Run tests on Chrome, Firefox, Edge, Safari, and Internet Explorer
- **Extent Reports** - Beautiful HTML reports with detailed test execution results
- **Screenshot Capture** - Automatic screenshot capture for test evidence
- **TestNG Integration** - Powerful test configuration with annotations, data providers, and listeners
- **Explicit Waits** - Smart synchronization using WebDriverWait

---

## 🛠 Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 21 | Programming Language |
| Selenium WebDriver | 4.40.0 | Browser Automation |
| TestNG | 7.12.0 | Test Framework |
| Apache POI | 5.5.1 | Excel Data Reading |
| Extent Reports | 5.1.2 | HTML Test Reports |
| Maven | - | Build & Dependency Management |

---

## 📁 Project Structure

```
SeleniumJava1stGroup2026/
├── pom.xml                              # Maven configuration file
├── README.md                            # Project documentation
├── testrunner/
│   └── testng.xml                       # TestNG suite configuration
└── src/
    └── test/
        ├── java/
        │   ├── Base/
        │   │   └── BaseTest.java        # Base test class with setup/teardown
        │   ├── BasicBase/
        │   │   ├── BaseTestWithoutWaits.java
        │   │   └── BaseTestWithWaits.java
        │   ├── BasicPages/
        │   │   ├── LoginPageWithoutWaits.java
        │   │   └── LoginPageWithWaits.java
        │   ├── BasicTests/
        │   │   ├── LoginTestReadXSLData.java
        │   │   ├── LoginTestWithoutWaitsWithDataProvider.java
        │   │   ├── LoginTestWithoutWaitsWithOutDataProvider.java
        │   │   ├── LoginTestWithwaitsAndDataProvider.java
        │   │   ├── loginToNdosiWebSite.java
        │   │   └── TestNgAnnotations.java
        │   ├── ExtentReports/
        │   │   ├── ExtentReportManager.java  # Report configuration
        │   │   └── Listener.java             # TestNG listener for reporting
        │   ├── Tests/
        │   │   └── LoginTest.java            # Main login test class
        │   └── Utilities/
        │       ├── BrowserFactory.java       # Browser initialization
        │       ├── ReadXSLdata.java          # Excel data provider
        │       └── Screenshots.java          # Screenshot utility
        └── resources/
            └── testdata/
                └── testdata.xlsx             # Test data file
```

---

## 📋 Prerequisites

Before you begin, ensure you have the following installed:

- **Java JDK 21** or higher
- **Maven 3.8+**
- **Git**
- **IDE** (IntelliJ IDEA recommended)
- **Web Browser** (Chrome, Firefox, Edge, etc.)

---

## 🚀 Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/SeleniumJava1stGroup2026.git
   cd SeleniumJava1stGroup2026
   ```

2. **Install dependencies**
   ```bash
   mvn clean install -DskipTests
   ```

3. **Verify installation**
   ```bash
   mvn dependency:resolve
   ```

---

## ⚙️ Configuration

### Browser Configuration

The default browser is set to **Chrome**. To change it, modify the `browserChoice` variable in `BaseTest.java`:

```java
public final String browserChoice = "chrome";  // Options: chrome, firefox, edge, safari, internetexplore
```

### Test URL Configuration

Update the target URL in `BaseTest.java`:

```java
public final String url = "https://ndosisimplifiedautomation.vercel.app/";
```

### Test Data Configuration

Test data is stored in Excel format at:
```
src/test/resources/testdata/testdata.xlsx
```

**Note:** The sheet name must match the test method name for the data provider to work correctly.

---

## ▶️ Running Tests

### Run All Tests via TestNG XML
```bash
mvn test -DsuiteXmlFile=testrunner/testng.xml
```

### Run All Tests via Maven
```bash
mvn clean test
```

### Run Specific Test Class
```bash
mvn -Dtest=LoginTest test
```

### Run from IDE
1. Right-click on `testng.xml` file
2. Select "Run"

---

## 📊 Test Reports

### Extent Reports

After test execution, HTML reports are generated at:
```
target/reports/NdosiAutomationReport.html
```

**Report Features:**
- Test execution summary (Pass/Fail/Skip counts)
- Detailed test steps with timestamps
- System information (OS, Machine name)
- Dark theme for better readability

### Screenshots

Screenshots are automatically captured and saved at:
```
target/screenshots/
```

---

## 🧩 Framework Components

### 1. BaseTest (Base/BaseTest.java)
The foundation class that all test classes extend. Handles:
- Browser initialization and teardown
- Page object instantiation
- Common test configurations

### 2. BrowserFactory (Utilities/BrowserFactory.java)
Factory class for creating WebDriver instances. Supports:
- Chrome
- Firefox
- Edge
- Safari
- Internet Explorer

### 3. Page Objects (BasicPages/)
Page classes implementing the Page Object Model pattern with:
- `@FindBy` annotations for element locators
- `PageFactory` initialization
- `WebDriverWait` for explicit waits

### 4. ReadXSLdata (Utilities/ReadXSLdata.java)
TestNG DataProvider that reads test data from Excel files:
- Uses Apache POI for Excel parsing
- Dynamically reads sheet based on test method name
- Returns 2D String array for data-driven tests

### 5. ExtentReportManager & Listener
Generates rich HTML reports with:
- Test execution status tracking
- System information capture
- Automatic report flushing

### 6. Screenshots (Utilities/Screenshots.java)
Utility for capturing screenshots:
- Saves as JPEG format
- Custom naming support
- Stores in target directory

---

## 📝 Writing New Tests

### Step 1: Create a Page Object
```java
public class NewPage {
    WebDriver driver;
    WebDriverWait wait;

    public NewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "element-id")
    WebElement myElement;

    public void clickElement() {
        wait.until(ExpectedConditions.elementToBeClickable(myElement)).click();
    }
}
```

### Step 2: Create a Test Class
```java
public class NewTest extends BaseTest {

    @Test
    public void testNewFeature() {
        // Test implementation
    }
}
```

### Step 3: Add to testng.xml
```xml
<class name="Tests.NewTest"/>
```

---

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -m 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Open a Pull Request

---

## 📄 License

This project is for educational and training purposes.

---

## 👨‍💻 Author

**Selenium Java 1st Group 2026**

---

## 📞 Support

If you encounter any issues or have questions, please open an issue in the repository.
