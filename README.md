### Stack

Java 8, Selenium, TestNG, Maven, Allure Report, Log4j

---

### Run tests

mvn clean install -DsuiteXmlFile=src/test/resources/testng.xml 

---

### Generate Allure Report

mvn allure:serve

---

### Task

Your automation script will test an online calculator. It must do these steps 1 to 5:<br>

1. Go to the page http://web2.0calc.com/ <br>
2. Calculate 35*999+(100/4)= and assert the correct result 34990.<br>
3. Calculate cos(pi) with the rad radio button and assert the correct result -1.<br>
4. Calculate sqrt(81) and assert the correct result 9.<br>
5. Press history dropdown and assert that the list contains the 3 operations executed e.g. 35*999+(100/4)=, cos(pi), sqrt(81)<br>

-   Please use POM (Page Object Model) for your implementation.
-   Use Git as a VCS (Version Control System)

---

### Note

Branch feature-buttons contains task variant with clicking buttons instead of passing expression string right to the input field.