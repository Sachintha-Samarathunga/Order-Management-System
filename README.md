# 🛒 Order Management System - Automated Test Suite

This repository contains the automated test suite for the **Order Management System (OMS)** developed using the **JTS Automation Framework Template**. It covers functional, UI, and regression testing for critical workflows across the system using **Java**, **Selenium WebDriver**, and **TestNG**.

---

## 🧰 Tech Stack

| Tool/Framework       | Description                                          |
|----------------------|------------------------------------------------------|
| Java                 | Core programming language                            |
| Selenium WebDriver   | UI automation testing                                |
| TestNG               | Test execution and assertions                        |
| ExtentReports        | Custom HTML reporting with screenshots               |
| Apache POI           | Excel-based data and XPath management                |
| Property File Reader | Test data and config management                      |
| Maven                | Project build and dependency management              |
| Chrome/Firefox/Edge  | Supported browsers for cross-browser testing         |


---

## ✅ Features of the JTS Framework Template

- 🔁 **Reusable Web Steps**: Centralized actions like `click()`, `sendKeys()`, `selectDropdown()`
- 🗂 **Excel-driven XPaths**: Maintain all object locators in a single spreadsheet
- 🧪 **TestNG Integration**: Grouping, prioritization, and parallel execution
- 🌐 **Cross-Browser Testing**: Supports Chrome, Firefox, and Edge browsers
- 📄 **Advanced Reporting**: Customized ExtentReports with screenshots and logs
- 🔐 **Config Management**: Store test data and credentials in property files
- ♻ **OOP-based Page Objects**: Clean, maintainable code structure

---

## 🧪 How to Run the Tests

### Prerequisites

- Java 11+
- Maven 3.6+
- Chrome / Firefox / Edge browser installed

### Steps

```bash
# Clone the repository
git clone https://github.com/your-org/oms-automation.git
cd oms-automation

# Install dependencies
mvn clean install

# Run tests on default browser (configured in config.properties)
mvn test

# Run tests on specific browser
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge
```

Sachintha Samarathunga – QA Engineer

Framework Base: JTS Automation Framework Template

