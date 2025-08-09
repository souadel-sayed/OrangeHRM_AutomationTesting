# 🧪 OrangeHRM Automation Framework  
**Selenium WebDriver | TestNG | Allure | POM**  

![Java](https://img.shields.io/badge/Java-21-%23ED8B00?logo=java)  
![POM](https://img.shields.io/badge/Design_Pattern-Page_Object_Model-%23007ACC)  
![Fluent](https://img.shields.io/badge/Coding_Style-Fluent_Interface-%23FF6F61)  

---

## 🏗️ Architecture & Design  

### 🔧 Core Patterns  
| Pattern                  | Implementation Example                | Benefits |
|--------------------------|---------------------------------------|----------|
| **Page Object Model**    | `LoginPage.java`, `DashboardPage.java` | Reduced duplication, improved maintainability |
| **Fluent Interface**     | `.enterUsername("Admin").clickSubmit()` | Readable test scripts |
| **Custom TestNG Listeners** | `TestListener.java` (screenshots on failure) | Enhanced reporting |

### 📊 Supporting Components  
```mermaid
graph TD
    A[Tests] --> B[Page Objects]
    B --> C[WebDriver]
    C --> D[DriverManager]
    A --> E[TestNG Listeners]
    E --> F[Allure Reports]
    B --> G[JSON Test Data]


## ✅ Test Coverage  
### 🔐 Authentication Module  
- ✔️ Valid credentials login  
- ✔️ Invalid credentials rejection  
- ✔️ Empty field validation  

### 👨‍💼 PIM Module  
- ✔️ Add new employee  
- ✔️ Employee-user creation  
- ✔️ Duplicate employee prevention  

### 👤 Admin Module  
- ✔️ System user creation  
- ✔️ Role-based access tests  

---

## 📂 Project Architecture  
src/
├── main/
│ ├── java/
│ │ ├── pages/ # POM Classes
│ │ ├── utils/ # DriverManager, ConfigReader
├── test/
│ ├── java/
│ │ ├── tests/ # TestNG Classes
│ │ ├── listeners/ # Custom Listeners
│ ├── resources/
│ │ ├── test-data.json # Test Data
