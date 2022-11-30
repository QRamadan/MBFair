### This repository is for Windows users. For Linux users, see [branch linux](https://github.com/QRamadan/MBFair/tree/linux)
# MBFair: A Model-based Verification Methodology for Detecting Violations of Individual Fairness

In this file, we present the artifact used in our paper. The submission includes:
* the *UMLfair profile*.

* The MBFair tool.

* The UML models of three case studies namely, the Bank Management System based on the description of a decision-making process in a [banking system] (https://www.hypovereinsbank.de/hvb/privatkunden/services-und-marktinformationen/kundenprogramm-valyou), the Delivery Management System based on the incedent describtion of [Amazon's delivery-free service](https://www.bloomberg.com/graphics/2016-amazon-same-day/), and the Loan Management System based on [business process model](https://link.springer.com/chapter/10.1007/978-3-319-92901-9_19) from an [event log](https://www.win.tue.nl/bpi/doku.php?id=2012:challenge) recording the loan management process of a Dutch financial institute. 

# <section id="resources"> Recources </section>

- Profile: [The UMLfair Profile](https://github.com/confFair/project/blob/master/profile.zip)
- Source code of our MBFair tool
- Artifacts: [The delivery system case study](https://github.com/QRamadan/MBFair/tree/linux/artifacts/delivery_system)
- Artifacts: [The loan management system case study](https://github.com/QRamadan/MBFair/tree/linux/artifacts/loand_management)
- Artifacts: [Bank management system](https://github.com/QRamadan/MBFair/tree/linux/artifacts/bank_management)

The artifacts of each each case study contains: 
* A (*.uml*) file: the UML model of the system annotated with the UMLfair profile.
* A (*.xlsx*) file: the dataset of the system which used to uncover proxies for protected data. 

# How to install

## Prerequisite 
We recommend using [IntelliJ IDEA 2022.2.2](https://www.jetbrains.com/idea/download/#section=windows), with
installed [Maven](https://www.jetbrains.com/help/idea/convert-a-regular-project-into-a-maven-project.html#develop_with_maven) plug-in.

# <section id="requirements">Requirements</section>
The following list of requirements must be satisfied before the tool can be installed
- The [Spin Model Checker](https://spinroot.com/spin/whatispin.html) must be installed. Installation steps are available [here](https://spinroot.com/spin/Man/README.html#S1). After installing the spin model checker, verify that the model checker is callable from the command line using the command ```spin -V``` . You should have the version of the model checker printed in the console after executing the command on the command line or terminal
- Any java version greater than 11 is required. Various java version and installation instructions can be found [here](https://docs.oracle.com/en/java/javase/index.html). After installing java, verify that the installation was successful using the command ```java --version```. You should see the version of the currently installed JAVA printed in the console.
- Any release of PYTHON3 is also required. Installation instructions can be found [here](https://www.python.org/downloads/windows/). After installing python3, verity that the installation was successful by running the command ```python3 --version```. You should see the version of the currently installed PYTHON3 printed in the console.
* (Optional) A modeled finite state system using [Eclipse Papyrus](https://www.eclipse.org/papyrus/)

# <section id="installation">Installation</section>
After satisfying the requirements mentioned in the [requirements section](#requirements), complete the following steps to install the tool:
* Clone or [Download](https://github.com/QRamadan/MBFair/archive/refs/heads/master.zip) this git repository.
* The file is callable using the command in the [Usage](#usage) section below
If you completed the previous steps successfully, you should be able to use the tool using the commands in the next section

# <section id="usage"> Usage </section>

* Open our project package in your local IntelliJ IDEA.
### Step 1. Generating initializations
This first step is to generate initializations. You can do that using the following command
``` java -jar mbfair-0.0.1.jar -g -f="absolute-path-to-file" ```
In the above command, replace the term <strong><ins>absolute-path-to-file</ins></strong> with the absolute path to your system model with Papyrus

### Result of Step 1: 
Upon generating initializations, csv files will be automatically created by the tool in your project folder. The tool uses these csv files.

### Step 2. Checking for fairness:
To specify protected characteristics and proxies of protected characteristics, modify the generated file <strong>protected_characteristics.csv</strong>. Consider the csv file as a table where the first row in the csv represented the protected characteristics and any subsequent row data represent the proxies of the corresponding protected characteristics.
For example, consider that you have a use case with two protected characteristics - char1 and char2. Char1 has one two proxies Char1_prox_1 and Char1_prox_2. Char2 has one proxy Char2_prox_1. This information will be represented in the csv as follows
```
Char1,Char2
Char1_prox_1,Char2_prox_1
Char1_prox_2,
```

After modifying the file, run the following command.
``` java -jar target/mbfair-0.0.1.jar -r -f="absolute-path-to-file" ```
Replace the string absolute-path-to-file as in Step 1

### Result of Step 2:
The model runs and the results of the check are printed in the console and saved in <strong>statistics.csv</strong> in your project folder.

# <section id="using-artifacts">Using artifacts</section>
This section is intended to serve as a guide for using the [provided artifacts](#resources). The tool must have [successfully installed](#installation) to follow on with this section. We have provided three artifacts to serve as hands-on examples. The following steps can be completed to run an artifact:
- Locate the UML file in the artifact of interest and get the absolute path to the file.
- Generate initialization using the command below while replacing "absolute-path-to-file" in the command by the absolute path to UML file.
    ```
    java -jar mbfair-0.0.1.jar -g -f="absolute-path-to-file"
    ```
- The initializations will be automatically generated and stored in the file <strong>initializations_table.csv</strong> in the project directory.
  - One has to then specify the protected characteristics and proxies of protected characteristics. For this, the file <strong>protected_characteristics.csv</strong> has to be modified.
    - for the delivery system artifact, replace the file with the following content
      ```
      CustomerProfile_ethnicity,CustomerProfile_age
      CustomerProfile_billingAddress,
      ```
      One can observe here that ethnicity and age are protected characteristics and billing address is a proxy for ethnicity
    - for the loan management system artifact, replace the file with the following content
      ```
      LoanRequest_gender,LoanRequest_citizenship,LoanRequest_age
      ,,LoanRequest_saving
      ,,LoanRequest_creditHistoryStatus
      ```
      Here, gender, citizenship and age are protected characteristics while saving and creditHistoryStatus are both proxies for age
    - for the banking management system artifact, replace the file with the following content
      ```
      CustomerProfile_healthy,CustomerProfile_age
      ,CustomerProfile_income
      ,CustomerProfile_healthy
      ```
      It is noteworthy here that healthy and age are protected characteristics while both income and healthy are proxies for age.
  
- After modifying and saving the file <strong>protected_characteristics.csv</strong>, the following command is to be executed while replacing "absolute-path-to-file" in the command by the absolute path to UML file.
  ```
  java -jar target/mbfair-0.0.1.jar -r -f="absolute-path-to-file"
  ```
  At this point, one only has to wait for the tool to run. The result of the analysis is printed in the console upon completion of execution and saved in <strong>statistics.csv</strong> in your project folder.
