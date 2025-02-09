# Iniflex Project

Iniflex is a Java application that demonstrates the use of object-oriented programming principles. The project includes classes for managing people and employees, showcasing inheritance and encapsulation.

## Project Structure

```
iniflex
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── iniflex
│   │   │           ├── App.java
│   │   │           ├── Pessoa.java
│   │   │           └── Funcionario.java
│   │   └── resources
│   └── test
│       └── java
├── pom.xml
└── README.md
```

## Classes Overview

- **App.java**: The main entry point of the application. It contains methods to insert users and manage the application flow.
- **Pessoa.java**: Represents a person with properties such as `nome` (name) and `idade` (age). It includes getter and setter methods for these properties.
- **Funcionario.java**: Extends the `Pessoa` class to represent an employee. It adds properties like `cargo` (position) and `salario` (salary), along with their respective getter and setter methods.

## Getting Started

To set up and run the Iniflex project, follow these steps:

1. **Clone the repository**:
   ```
   git clone <repository-url>
   ```

2. **Navigate to the project directory**:
   ```
   cd iniflex
   ```

3. **Build the project using Maven**:
   ```
   mvn clean install
   ```

4. **Run the application**:
   ```
   mvn exec:java -Dexec.mainClass="com.iniflex.App"
   ```

## Dependencies

This project uses Maven for dependency management. Ensure you have Maven installed on your machine to build and run the project.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.