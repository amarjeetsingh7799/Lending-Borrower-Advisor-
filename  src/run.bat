@echo off
cd /d "C:\Users\nisha\Downloads\MY JAVA FOLDER\LoanProject"
javac -cp ".;lib\weka-3-8-0-monolithic.jar" LendingAdvisor.java
java -cp ".;lib\weka-3-8-0-monolithic.jar" LendingAdvisor
pause
