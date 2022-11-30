### This repository is for Windows users. For Linux users, see branch linux
# MBFair: A Model-based Verification Methodology for Detecting Violations of Individual Fairness

In this file, we present the artifact used in our paper. The submission includes:
* the *UMLfair profile*.

* The MBFair tool.

* The UML models of three case studies namely, the Bank Management System based on the description of a decision-making process in a [banking system] (https://www.hypovereinsbank.de/hvb/privatkunden/services-und-marktinformationen/kundenprogramm-valyou), the Delivery Management System based on the incedent describtion of [Amazon's delivery-free service](https://www.bloomberg.com/graphics/2016-amazon-same-day/), and the Loan Management System based on [business process model](https://link.springer.com/chapter/10.1007/978-3-319-92901-9_19) from an [event log](https://www.win.tue.nl/bpi/doku.php?id=2012:challenge) recording the loan management process of a Dutch financial institute. 

# Recources

* **Profile: The UMLfair Profile:** https://github.com/confFair/project/blob/master/profile.zip
* **Source code of our MBFair tool**
* **Artifacts: The bank management system case study:** 
* **Artifacts: The delivery management system case study:** https://github.com/QRamadan/MBFair/blob/master/DeliveryManagementSystem.zip
* **Artifacts: The loan management system case study:** https://github.com/QRamadan/MBFair/blob/master/LoanManagementSystem.zip

The artifacts of each each case study contains: 
* A (*.uml*) file: the UML model of the system annotated with the UMLfair profile.
* A (*.xlsx*) file: the dataset of the system which used to uncover proxies for protected data. 

# How to install

## Prerequisite 
We recommend using [IntelliJ IDEA 2022.2.2](https://www.jetbrains.com/idea/download/#section=windows), with
installed [Maven](https://www.jetbrains.com/help/idea/convert-a-regular-project-into-a-maven-project.html#develop_with_maven) plug-in.

* **Installation from online update sites**: [Spin Model Checker](https://spinroot.com/spin/whatispin.html) is a general tool for the logical verification of concurrent software in a rigorous and mostly automated fashion. After installing the Spin Model Checker, verify that the model checker is callable from the command line using the command ``` spin -V ``` . You should have the version of the model checker printed in the console after executing the command on the command line or terminal.
* Any java version greater than 11
* Any release of python3
* (Optional) A modeled finite state system using [Eclipse Papyrus](https://www.eclipse.org/papyrus/)

## Installation
* Clone or [Download](https://github.com/QRamadan/MBFair/archive/refs/heads/master.zip) this git repository.
* The file is callable using the command in the Usage section below
If you completed the previous steps successfully, you should be able to use the tool using the commands in the next section

## Usage

* Open our project package in your local IntelliJ IDEA.
### Step 1. Generating initializations
This first step is to generate initializations. You can do that using the following command
``` java -jar mbfair-0.0.1.jar -g -f="absolute-path-to-file" ```
In the above command, replace the term <strong><ins>absolute-path-to-file</ins></strong> with the absolute path to your system model with Papyrus

### Result of Step 1: 
Upon generating initializations, csv files will be automatically created by the tool in your project folder. The tool uses these csv files.

### Step 2. Checking for fairness:
To specify protected characteristics and proxies of protected characteristics, modify the generated file <strong>protected_characteristics.csv</strong>. Consider the csv file as a table where the first row in the csv represented the protected characteristics and any subsequent row data represent the proxies of the corresponding protected characteristics.

After modifying the file, run the following command.
``` java -jar target/mbfair-0.0.1.jar -r -f="absolute-path-to-file" ```
Replace the string absolute-path-to-file as in Step 1

### Result of Step 2:
The model runs and the results of the check are printed in the console and saved in <strong>statistics.csv</strong> in your project folder.

### Additional Information:
You can use the --help commmand below to see the list of acceptable commands
``` java -jar mbfair-0.0.1.jar --help ```
